package com.example.findmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findmovies.R;
import com.example.findmovies.adapter.popMovieAdapter;
import com.example.findmovies.adapter.trendAdapter;
import com.example.findmovies.model.popularMovieData;
import com.example.findmovies.model.trendMovieData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //trending movie
    popularMovieData popularmoviedata;
    ViewPager2 viewPager2;
    public static List<popularMovieData> popmovielist = new ArrayList<>();

    //now playing movie
    trendMovieData trendmoviedata;
    RecyclerView nowplayrecycle;
    public static List<trendMovieData> nowplaylist = new ArrayList<>();

    //pop movie
    RecyclerView trendrecycle;
    public static List<trendMovieData> trendlist = new ArrayList<>();

    //top rated
    RecyclerView topratedrecycle;
    public static List<trendMovieData> topratedlist = new ArrayList<>();

    //upcoming
    RecyclerView upcomingrecycler;
    public static List<trendMovieData> upcominglist = new ArrayList<>();

    public void trendMovies(View view) {
        Intent intent = new Intent(this, moviefullliste.class);
        intent.putExtra("title", "Trending Movie");
        intent.putExtra("url", "https://api.themoviedb.org/3/trending/movie/day?api_key=d3bf2aee718b2374edaa0b9a3b477cf2");
        startActivity(intent);
    }

    public void npMovies(View view) {
        Intent intent = new Intent(this, moviefullliste.class);
        intent.putExtra("title", "NowPlaying Movie");
        intent.putExtra("url", "https://api.themoviedb.org/3/movie/now_playing?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1");
        startActivity(intent);
    }

    public void topMovies(View view) {
        Intent intent = new Intent(this, moviefullliste.class);
        intent.putExtra("title", "Top Rated Movie");
        intent.putExtra("url", "https://api.themoviedb.org/3/movie/top_rated?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1");
        startActivity(intent);
    }

    public void upComingMovies(View view) {
        Intent intent = new Intent(this, moviefullliste.class);
        intent.putExtra("title", "upcoming Movie");
        intent.putExtra("url", "https://api.themoviedb.org/3/movie/upcoming?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1");
        startActivity(intent);
    }

    public void popMovies(View view) {
        Intent intent = new Intent(this, moviefullliste.class);
        intent.putExtra("title", "Popular Movie");
        intent.putExtra("url", "https://api.themoviedb.org/3/movie/popular?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nowplayrecycle = findViewById(R.id.nowplayingrecycle);
        trendrecycle = findViewById(R.id.trendingrecycle);
        viewPager2 = findViewById(R.id.recycletrending);
        topratedrecycle = findViewById(R.id.topratedgrecycle);
        upcomingrecycler = findViewById(R.id.upcomingrecycle);

        String url = "https://api.themoviedb.org/3/trending/movie/day?api_key=d3bf2aee718b2374edaa0b9a3b477cf2";
        String urlnp = "https://api.themoviedb.org/3/movie/now_playing?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";
        String urltr = "https://api.themoviedb.org/3/movie/popular?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";
        String urltop = "https://api.themoviedb.org/3/movie/top_rated?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";
        String urlupcoming = "https://api.themoviedb.org/3/movie/upcoming?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";

        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    // Log.i("name",jsonObject1.getString("title"));
                    String title = jsonObject1.getString("title");
                    String date = jsonObject1.getString("release_date");
                    String lang = jsonObject1.getString("original_language");
                    String poster = jsonObject1.getString("poster_path");
                    String overview = jsonObject1.getString("overview");
                    Integer count = jsonObject1.getInt("vote_count");
                    Integer rate = jsonObject1.getInt("vote_average");
                    Boolean adult = jsonObject1.getBoolean("adult");
                    Integer id = jsonObject1.getInt("id");

                    popularmoviedata = new popularMovieData(title, date, lang, poster, overview, count, rate, adult, id);
                    popmovielist.add(popularmoviedata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        // popmovieadapter popmovieadapter = new popmovieadapter(this,popmovielist,viewPager2);
        // LinearLayoutManager horizontalLayoutManagaer
        // = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        // trendingview.setLayoutManager(horizontalLayoutManagaer);

        viewPager2.setAdapter(new popMovieAdapter(this, popmovielist, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);

        }));

        viewPager2.setPageTransformer(compositePageTransformer);
        fetchData(urlnp, nowplaylist, nowplayrecycle);
        fetchData(urltr, trendlist, trendrecycle);
        fetchData(urltop, topratedlist, topratedrecycle);
        fetchData(urlupcoming, upcominglist, upcomingrecycler);
    }

    public void search(View view) {
        Intent search = new Intent(this, com.example.findmovies.ui.search.class);
        startActivity(search);
    }

    public void fetchData(String url, final List list, RecyclerView recycle) {
        //now playing
        StringRequest requestNp = new StringRequest(Request.Method.GET, url, response -> {

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    // Log.i("name",jsonObject1.getString("title"));
                    String title = jsonObject1.getString("title");
                    String date = jsonObject1.getString("release_date");
                    String lang = jsonObject1.getString("original_language");
                    String poster = jsonObject1.getString("poster_path");
                    String overview = jsonObject1.getString("overview");
                    Integer count = jsonObject1.getInt("vote_count");
                    Integer rate = jsonObject1.getInt("vote_average");
                    Boolean adult = jsonObject1.getBoolean("adult");
                    Integer id = jsonObject1.getInt("id");

                    trendmoviedata = new trendMovieData(title, date, lang, poster, overview, count, rate, id, adult);
                    list.add(trendmoviedata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueuenp = Volley.newRequestQueue(this);
        requestQueuenp.add(requestNp);
        try {
            trendAdapter trendadapter = new trendAdapter(MainActivity.this, list);
            recycle.setAdapter(trendadapter);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recycle.setLayoutManager(horizontalLayoutManager);
        } catch (Exception e) {
            Log.i("error", e.toString());
        }
    }
}

//pop movie
/**
 * String urltrend="https://api.themoviedb.org/3/movie/popular?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";
 * StringRequest requesttrend = new StringRequest(Request.Method.GET, urltrend, new Response.Listener<String>() {
 *
 * @Override public void onResponse(String response) {
 * <p>
 * try {
 * JSONObject jsonObject = new JSONObject(response);
 * JSONArray jsonArray=jsonObject.getJSONArray("results");
 * for(int i=0;i<jsonArray.length();i++){
 * JSONObject jsonObject1= jsonArray.getJSONObject(i);
 * // Log.i("name",jsonObject1.getString("title"));
 * String title=jsonObject1.getString("title");
 * String date=jsonObject1.getString("release_date");
 * String lang=jsonObject1.getString("original_language");
 * String poster=jsonObject1.getString("poster_path");
 * String overview=jsonObject1.getString("overview");
 * Integer count=jsonObject1.getInt("vote_count");
 * Integer rate=jsonObject1.getInt("vote_average");
 * Boolean adult =jsonObject1.getBoolean("adult");
 * Integer id = jsonObject1.getInt("id");
 * <p>
 * trendmoviedata =new trendmoviedata(title,date,lang,poster,overview,count,rate,id,adult);
 * trendlist.add(trendmoviedata);
 * }
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * } catch (JSONException e) {
 * e.printStackTrace();
 * <p>
 * }
 * <p>
 * <p>
 * }
 * }, new Response.ErrorListener() {
 * @Override public void onErrorResponse(VolleyError error) {
 * <p>
 * Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
 * }
 * });
 * <p>
 * RequestQueue requestQueuetrend = Volley.newRequestQueue(this);
 * requestQueuetrend.add(requesttrend);
 * try {
 * trendadapter trendadapter = new trendadapter(MainActivity.this, trendlist);
 * trendrecycle.setAdapter(trendadapter);
 * LinearLayoutManager horizontalLayoutManagaer
 * = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
 * trendrecycle.setLayoutManager(horizontalLayoutManagaer);
 * }
 * catch (Exception e){
 * Log.i("error",e.toString());
 * }
 * <p>
 * <p>
 * }
 * }
 **/