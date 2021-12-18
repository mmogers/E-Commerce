package lv.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lv.example.e_commerce.adapter.CategoryAdapter;
import lv.example.e_commerce.adapter.CourseAdapter;
import lv.example.e_commerce.model.Category;
import lv.example.e_commerce.model.Course;

public class MainActivity extends AppCompatActivity {

    //object
    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>(); //v statichnom metode udobno rabotat'v statichnyh objectah,
    static List<Course>  fullCoursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spisok iz categorij
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Games"));
        categoryList.add(new Category(2,"Websites"));
        categoryList.add(new Category(3,"Languages"));
        categoryList.add(new Category(4,"Other"));

        setCategoryRecycler(categoryList);

        //spisok iz tovarov-kursov

        courseList.add(new Course(1,"java", "Profession Java\ndeveloper", "01/01/2022", "beginner", "#424345", "test", 3));
        courseList.add(new Course(2,"python", "Profession Python\ndeveloper", "10/01/2022", "beginner", "#9FA52D", "test",3));
        //courseList.add(new Course(2,"unity5", "Profession Unity\ndeveloper", "10/03/2022", "beginner", "#708090"));

        fullCoursesList.addAll(courseList);

        setCourseRecycler(courseList);

    }

    //obrabotchik sobytij dlya korziny
    public void openShoppingCart(View view){
        //perehod na novyju stranicy s zakazanymi tovarami
        Intent intent = new Intent(this, OrderPage.class);// perehod s this stranicy v OrderPage
        startActivity(intent);
    }



    private void setCourseRecycler(List<Course> courseList) {
        //nastrojki dlya vyvoda informacii, po umolchaniju vertikalņo, a nam nado gorizontalņo
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);//false - elementy ne dolzhny idti v obratnoj posledovatelņosti , context - this- na etoj stranicy, horizontalņaya posledovatelņost
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager); //ustanavlivaem nastroiki

        //esche nuzhen adapter
        //object
        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);//ustanavlivaem adapter v nash recycler
    }

    private void setCategoryRecycler(List<Category> categoryList){

        //nastrojki dlya vyvoda informacii, po umolchaniju vertikalņo, a nam nado gorizontalņo
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);//false - elementy ne dolzhny idti v obratnoj posledovatelņosti , context - this- na etoj stranicy, horizontalņaya posledovatelņost
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager); //ustanavlivaem nastroiki

        //esche nuzhen adapter
        //object
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);//ustanavlivaem adapter v nash recycler

    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCoursesList);

        List<Course> filterCourses = new ArrayList<>();//tolķo te jursy kot budut otobrazheny polžovatelju

        for(Course c:courseList){
            if(c.getCategory() == category){
                filterCourses.add(c);
            }
            courseList.clear();
            courseList.addAll(filterCourses);

            //static, poetomu znachenie ne menyaet
            courseAdapter.notifyDataSetChanged();//novoe znachenie spiska obnovlyaet recycler view


        }


    }

}