package com.example.findmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class trendadapter extends RecyclerView.Adapter<trendadapter.MyViewHolder> {
    private  Context context;
    private  List<trendmoviedata> trendlist;


    public trendadapter( Context context,  List<trendmoviedata> trendlist){
        this.context=context;
        this.trendlist=trendlist;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view=mInflater.inflate(R.layout.moviecard,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trendadapter.MyViewHolder holder, int position) {
        try {
            String url = "https://image.tmdb.org/t/p/w500" + trendlist.get(position).getPoster();
            Glide.with(context).load(url)        .transform(new RoundedCornersTransformation(30, 0))

                    .into(holder.imgThumbnail);
            holder.moviename.setText(trendlist.get(position).getTitle());

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

        ImageView imgThumbnail;
        TextView moviename;
        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbnail=itemView.findViewById(R.id.nowplayingimageid);
            moviename=itemView.findViewById(R.id.nowplayingnameid);


        }
    }
}



