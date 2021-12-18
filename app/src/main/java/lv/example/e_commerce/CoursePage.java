package lv.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lv.example.e_commerce.model.Order;

public class CoursePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        //zdes'budem prinimat'znacheniya peredannye v etu activity i podstavlyat''ih v razlichnye elementy v dizajne
        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);//chtob vidoizmenit'zadnij fon
        ImageView courseImage = findViewById(R.id.coursePageImage);//menyat'kartinku
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseDate = findViewById(R.id.coursePageDate);
        TextView courseLevel = findViewById(R.id.coursePageLevel);
        TextView courseText = findViewById(R.id.coursePageText);

        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0)); //0 ato default, esli ne peredadim znachenie
        courseImage.setImageResource(getIntent().getIntExtra("courseImage", 0));
        courseTitle.setText(getIntent().getStringExtra("courseTitle")); // so strings defaultnoe znachenie ne nuzhno
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        courseText.setText(getIntent().getStringExtra("courseText"));
    }

    public void addToCart(View view){

        int item_id = getIntent().getIntExtra("courseId", 0);
        Order.items_id.add(item_id);
        Toast.makeText(this, "Added!", Toast.LENGTH_LONG).show();
    }
}