package com.example.recyclerviewjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likeCount";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON()
    {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                     for(int i=0;i< jsonArray.length();i++)
                     {
                         JSONObject jsonObject = jsonArray.getJSONObject(i);

                         String creatorName = jsonObject.getString("user");
                         String imageURL = jsonObject.getString("webformatURL");
                         int likeCount = jsonObject.getInt("likes");

                        mExampleList.add(new ExampleItem(imageURL,creatorName,likeCount));
                     }

                     mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                     mRecyclerView.setAdapter(mExampleAdapter);
                     mExampleAdapter.SetOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);


    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this,DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);

        intent.putExtra(EXTRA_URL,clickedItem.getImageURL());
        intent.putExtra(EXTRA_CREATOR,clickedItem.getmAuthor());
        intent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());

        startActivity(intent);
    }
}