package com.example.findmovies.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.findmovies.R;
import com.example.findmovies.ui.moviedetails;
import com.example.findmovies.model.popularMovieData;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class popMovieAdapter extends RecyclerView.Adapter<popMovieAdapter.MyViewHolder> {
    private final Context context;
    private final List<popularMovieData> popularMovieDataList1;
    private ViewPager2 viewPager2;

    public popMovieAdapter(Context context, List<popularMovieData> popularMovieDataList1, ViewPager2 viewPager2) {
        this.context = context;
        this.popularMovieDataList1 = popularMovieDataList1;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.moviecardpop, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popMovieAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            String url = "https://image.tmdb.org/t/p/w500" + popularMovieDataList1.get(position).getPoster();
            Glide.with(context).load(url).into(holder.imgThumbnail);
            holder.imgThumbnail.setOnClickListener(v -> {
                Intent intent = new Intent(context, moviedetails.class);
                intent.putExtra("title", popularMovieDataList1.get(position).getTitle());
                intent.putExtra("overview", popularMovieDataList1.get(position).getOverview());
                intent.putExtra("lang", popularMovieDataList1.get(position).getLang());
                intent.putExtra("date", popularMovieDataList1.get(position).getDate());
                intent.putExtra("poster", popularMovieDataList1.get(position).getPoster());
                intent.putExtra("rate", popularMovieDataList1.get(position).getRate());
                intent.putExtra("adult", popularMovieDataList1.get(position).getAdult());
                intent.putExtra("id", popularMovieDataList1.get(position).getId());
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

        RoundedImageView imgThumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = itemView.findViewById(R.id.imageslidepop);
            //cardView=itemView.findViewsWithText(R.id.);
        }
    }
}



