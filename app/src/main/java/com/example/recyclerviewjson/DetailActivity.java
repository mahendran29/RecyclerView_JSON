package com.example.recyclerviewjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.recyclerviewjson.MainActivity.EXTRA_CREATOR;
import static com.example.recyclerviewjson.MainActivity.EXTRA_LIKES;
import static com.example.recyclerviewjson.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView = findViewById(R.id.details_image_view);
        TextView textView1 = findViewById(R.id.details_text_view1);
        TextView textView2 = findViewById(R.id.details_text_view2);

        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
        textView1.setText(creatorName);
        textView2.setText("Likes:"+likes);
    }
}