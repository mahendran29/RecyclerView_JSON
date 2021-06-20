package com.example.recyclerviewjson;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.recyclerviewjson.MainActivity.EXTRA_CREATOR;
import static com.example.recyclerviewjson.MainActivity.EXTRA_LIKES;
import static com.example.recyclerviewjson.MainActivity.EXTRA_URL;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
{
    private Context mContext;
    private ArrayList<ExampleItem> exampleItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public  ExampleAdapter(Context context,ArrayList<ExampleItem> list)
    {
        mContext=context;
        exampleItems=list;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  ExampleAdapter.ExampleViewHolder holder, int position) {
          ExampleItem currentItem = exampleItems.get(position);

          String imageURL = currentItem.getImageURL();
          String author = currentItem.getmAuthor();
          int likes = currentItem.getmLikes();

          holder.mAuthor.setText(author);
          holder.mLikes.setText("Likes:"+likes);
          Picasso.with(mContext).load(imageURL).fit().centerInside().into(holder.mImage);

          holder.cardview.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(mContext,DetailActivity.class);
                  ExampleItem clickedItem = exampleItems.get(position);

                  intent.putExtra(EXTRA_URL,clickedItem.getImageURL());
                  intent.putExtra(EXTRA_CREATOR,clickedItem.getmAuthor());
                  intent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());

                  mContext.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return exampleItems.size();
    }

    public class ExampleViewHolder extends  RecyclerView.ViewHolder
    {
        public ImageView mImage;
        public TextView mAuthor;
        public TextView mLikes;
        public CardView cardview;
        public ExampleViewHolder(@NonNull  View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image_view);
            mAuthor=itemView.findViewById(R.id.textView1);
            mLikes=itemView.findViewById(R.id.textView2);
            cardview=itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }

}
