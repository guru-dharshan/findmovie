package com.example.findmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findmovies.R;
import com.example.findmovies.adapter.trendAdapter;
import com.example.findmovies.model.trendMovieData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class moviefullliste extends AppCompatActivity {
    trendMovieData trendmoviedata;
    RecyclerView recycle;
    int page;
    List<trendMovieData> list;
    TextView moviesTopic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullmovielistlayout);
        list = new ArrayList<>();
        moviesTopic = findViewById(R.id.topic);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        String title = intent.getExtras().getString("title");
        recycle = findViewById(R.id.fullmovierecycle);
        moviesTopic.setText(title);

        fetchData(url);
    }

    //fetch data
    public void fetchData(String url) {
        StringRequest requestNp = new StringRequest(Request.Method.GET, url, response -> {

            try {
                JSONObject jsonObject = new JSONObject(response);
                page = jsonObject.getInt("total_pages");
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
        }, error -> Toast.makeText(moviefullliste.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueueNp = Volley.newRequestQueue(this);
        requestQueueNp.add(requestNp);
        try {
            trendAdapter trendadapter = new trendAdapter(moviefullliste.this, list);
            recycle.setAdapter(trendadapter);

            recycle.setLayoutManager(new GridLayoutManager(moviefullliste.this, 2));

        } catch (Exception e) {
            Log.i("error", e.toString());
        }
    }
}
