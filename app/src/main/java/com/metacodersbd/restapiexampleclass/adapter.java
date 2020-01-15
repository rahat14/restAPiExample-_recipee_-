package com.metacodersbd.restapiexampleclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.metacodersbd.restapiexampleclass.model.recipe;

import java.util.List;



public class adapter extends RecyclerView.Adapter<adapter.recyclerViewHolder> {


    private  List<recipe>recipes ;
    private  Context context ;

    private ItemClickListenter   itemClickListenter   ;



    public  adapter (List<recipe> recipes , Context context , ItemClickListenter itemClickListenter ){


        this.recipes = recipes ;
        this.context = context ;
        this.itemClickListenter = itemClickListenter ;


    }

    @NonNull
    @Override
    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recipee_row , parent, false) ;


        return new recyclerViewHolder(view , itemClickListenter);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewHolder holder, int position) {

        //  Bind The Views here  ........


        recipe recipe = recipes.get(position) ;
        holder.title.setText(recipe.getTitle());

        holder.preperedTimeTv.setText(String.valueOf(recipe.getReadyInMinutes())  +  " Min");


        try {
            Glide.with(context)
                    .load(recipe.getImage())
                    .centerCrop()
                    .into(holder.imageView) ;
        }
        catch ( Exception e ){


        }




    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }



    public  class  recyclerViewHolder extends  RecyclerView.ViewHolder  implements View.OnClickListener
    {
         TextView title , preperedTimeTv  ;
        ImageView imageView ;

        ItemClickListenter itemClickListenter ;


        public recyclerViewHolder(@NonNull View view, ItemClickListenter itemClickListenter) {

            super(view);

            // register my click listener  ;
            this.itemClickListenter = itemClickListenter ;
            imageView  = view.findViewById(R.id.imageView) ;
            title = view.findViewById(R.id.titleTv) ;
            preperedTimeTv = view.findViewById(R.id.prepareTime) ;





        }

        @Override
        public void onClick(View view ) {

            itemClickListenter.onItemClick( view   ,  getAdapterPosition());

        }



    }

    public  interface  ItemClickListenter{

        void onItemClick(View view , int pos ) ;

    }
}


