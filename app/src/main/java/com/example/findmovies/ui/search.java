package com.example.findmovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class search extends AppCompatActivity {
    RecyclerView searchRecycle;
    trendMovieData trendmoviedata;
    List<trendMovieData> searchList;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchRecycle = findViewById(R.id.searchrecycle);
        searchBox = findViewById(R.id.searchbox);

    }

    public void searchIt(View view) {
        searchList = new ArrayList<>();
        String queary = searchBox.getText().toString();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&query=" + queary + "&page=1&include_adult=false";

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
                    searchList.add(trendmoviedata);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(search.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueuenp = Volley.newRequestQueue(this);
        requestQueuenp.add(requestNp);
        try {
            trendAdapter trendadapter = new trendAdapter(search.this, searchList);
            searchRecycle.setAdapter(trendadapter);
            searchRecycle.setLayoutManager(new GridLayoutManager(search.this, 2));
        } catch (Exception e) {
            Log.i("error", e.toString());
        }
    }
}
