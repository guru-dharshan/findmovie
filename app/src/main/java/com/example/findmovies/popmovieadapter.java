package com.example.findmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class popmovieadapter extends RecyclerView.Adapter<popmovieadapter.MyViewHolder> {
    private  Context context;
    private  List<popularmoviedata> popularmoviedataList1;
    private ViewPager2 viewPager2;

    public popmovieadapter( Context context, List<popularmoviedata> popularmoviedataList1,ViewPager2 viewPager2){
        this.context=context;
        this.popularmoviedataList1=popularmoviedataList1;
        this.viewPager2=viewPager2;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view=mInflater.inflate(R.layout.moviecardpop,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popmovieadapter.MyViewHolder holder, int position) {
        try {
            String url = "https://image.tmdb.org/t/p/w500" + popularmoviedataList1.get(position).getPoster();
            Glide.with(context).load(url).into(holder.imgThumbnail);
        }
        catch (Exception e){
                Log.i("error",e.toString());
        }
       // holder.imgThumbnail.setImageResource(R.drawable.ic_banner_foreground);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView imgThumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbnail=itemView.findViewById(R.id.imageslidepop);


        }
    }
}



