package com.example.findmovies.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmovies.R;
import com.example.findmovies.ui.moviedetails;
import com.example.findmovies.model.trendMovieData;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class trendAdapter extends RecyclerView.Adapter<trendAdapter.MyViewHolder> {
    private final Context context;
    private final List<trendMovieData> trendList;

    public trendAdapter(Context context, List<trendMovieData> trendList) {
        this.context = context;
        this.trendList = trendList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.moviecard, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trendAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            String url = "https://image.tmdb.org/t/p/w500" + trendList.get(position).getPoster();
            Glide.with(context).load(url).transform(new RoundedCornersTransformation(30, 0))

                    .into(holder.imgThumbnail);
            holder.moviename.setText(trendList.get(position).getTitle());
            holder.cardView.setOnClickListener(v -> {
                //Toast.makeText(context, trendlist.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, moviedetails.class);
                intent.putExtra("title", trendList.get(position).getTitle());
                intent.putExtra("overview", trendList.get(position).getOverview());
                intent.putExtra("lang", trendList.get(position).getLang());
                intent.putExtra("date", trendList.get(position).getDate());
                intent.putExtra("poster", trendList.get(position).getPoster());
                intent.putExtra("rate", trendList.get(position).getRate());
                intent.putExtra("adult", trendList.get(position).getAdult());
                intent.putExtra("id", trendList.get(position).getId());
                context.startActivity(intent);
            });
        } catch (Exception e) {
            Log.i("error", e.toString());
        }
        // holder.imgThumbnail.setImageResource(R.drawable.ic_banner_foreground);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThumbnail;
        TextView moviename;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = itemView.findViewById(R.id.nowplayingimageid);
            moviename = itemView.findViewById(R.id.nowplayingnameid);
            cardView = itemView.findViewById(R.id.nowplayingmoviecard);
        }
    }
}



