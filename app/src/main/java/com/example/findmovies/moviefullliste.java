package com.example.findmovies;

import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class moviefullliste extends AppCompatActivity {
    trendmoviedata trendmoviedata;
    List<trendmoviedata>list = new ArrayList<>();
    RecyclerView recycle;
    int page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullmovielistlayout);
        final String[] url = {"https://api.themoviedb.org/3/movie/now_playing?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1"};
        recycle = findViewById(R.id.fullmovierecycle);

        fetchdata(url[0]);
        for(int j=2;j<=page;j++){
            url[0] = url[0].substring(0, url[0].length()-1) + "2";
            Log.i("url",url[0]);
            fetchdata(url[0]);

        }
    }
        //fetch data
        public  void fetchdata(String url){
        StringRequest requestnp = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                     page=jsonObject.getInt("total_pages");
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


                            trendmoviedata = new trendmoviedata(title, date, lang, poster, overview, count, rate, id, adult);
                            list.add(trendmoviedata);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(moviefullliste.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueuenp = Volley.newRequestQueue(this);
        requestQueuenp.add(requestnp);
        try {
            trendadapter trendadapter = new trendadapter(moviefullliste.this, list);
            recycle.setAdapter(trendadapter);

            recycle.setLayoutManager(new GridLayoutManager(moviefullliste.this, 2));

        } catch (Exception e) {
            Log.i("error", e.toString());
        }

    }
}
