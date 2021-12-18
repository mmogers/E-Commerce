package lv.example.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lv.example.e_commerce.CoursePage;
import lv.example.e_commerce.R;
import lv.example.e_commerce.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses; //spisok tovarov- courses

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    public CourseAdapter() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    //kakoj dizajn dlya otobrazhenija kazhdogo elementa
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems =  LayoutInflater.from(context).inflate(R.layout.course_item, parent, false); //category item eto dizajn etogo itema , class roditelya, false- ne prikreplyaem dizain k roditel'skomu objectu
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    //chto konkretno budem podstavlyat'v šam dizajn
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.courseBg.setCardBackgroundColor(Color.parseColor(courses.get(position).getColor())); //color zapisan kak stroka v course.java poetomu parse

        //podstavlyat' opredelennoe izobrazhenie
        int imageId = context.getResources().getIdentifier("ic_" + courses.get(position).getImg(), "drawable", context.getPackageName()); //poluchaem izobrazhenie v formate nazvaniya
        holder.courseImage.setImageResource(imageId); // poluchaem tip dannogo

        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseDate.setText(courses.get(position).getDate());
        holder.courseLevel.setText(courses.get(position).getLevel());

        //novyj obrabotchik sobytija, itemView- otdelņyj element v etom obschem spiske
        holder.itemView.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursePage.class); //coursepage - etp na kakuju stranicy pereaddresovyvaem

                //opcii pri perehode iz activity v activity
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity)context,
                        new Pair<View,String>(holder.courseImage, "courseImage"));

                //chtob peredat'dopolnitelņye znacheniya v otkryvajuschujusya activity
                // pri nazhatii na tovar - course budem perehodit'na novuju activity i peredavat'tuda eti znacheniya
                intent.putExtra("courseBg", Color.parseColor(courses.get(position).getColor())); //backgroung i cbet kartochki sverhu
                intent.putExtra("courseImage", imageId);
                intent.putExtra("courseTitle", courses.get(position).getTitle());
                intent.putExtra("courseDate", courses.get(position).getDate());
                intent.putExtra("courseText", courses.get(position).getText()); //text kotoryj budet otobrazhatšya
                intent.putExtra("courseId", courses.get(position).getId());


                context.startActivity(intent, options.toBundle()); // v intent nahodyatsya vse nastrojki perehoda na drugujustranicy

            }
        });



    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        //s kakimi elementami v dizajne budem rabotat
        CardView courseBg; //cherez etot object ustanovim zadnij fon
        ImageView courseImage;
        TextView courseTitle, courseDate, courseLevel; //vse nadpisi
        
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            //ustanavlivaem znacheniya dlya polej
            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);

        }
    }

}
