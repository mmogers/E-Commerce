package lv.example.e_commerce.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lv.example.e_commerce.MainActivity;
import lv.example.e_commerce.R;
import lv.example.e_commerce.model.Category;

//standartnaya struktura opisaniya recyclerView
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context; //suda peredaem stranicu
    List<Category> categories; // spisok vseh nashih elementov

    //constructor
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    //budem dlya vseh nashih elementov ispol'zovat' opredelennyj dizajn i kazhdyj element budet opisan chere category view holder
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems =  LayoutInflater.from(context).inflate(R.layout.category_item, parent, false); //category item eto dizajn etogo itema , class roditwkya, false- ne prikreplyaem dizain k roditel'skomu objectu
        return new CategoryViewHolder(categoryItems);
    }

    //chto konkretno v kazhdoe pole my budem peredavat'
    //sozdajem object na osnove vlozhennogo klassa, cherez etot object obraschaemsya k nuzhnym polyam i ustanavlivaem text etogo polya
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryTitle.setText(categories.get(position).getTitle());


        //prin nazhatii na kategoriju
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MainActivity.showCoursesByCategory(categories.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size(); //kollichestvo kategorij v spiske
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        //vse polya s kotorymi my budem vzaimodejstvovat'
        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView); //constructor
            //znachenie nashego polya
            categoryTitle = itemView.findViewById(R.id.categoryTitle);  // categoryTitle kak id v textView, itemView - konkretnyj element v etom spiske
        }


    }

}
