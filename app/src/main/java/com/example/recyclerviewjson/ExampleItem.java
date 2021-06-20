package com.example.recyclerviewjson;

public class ExampleItem
{
    private String imageURL;
    private String mAuthor;
    private int mLikes;

    ExampleItem(String imageURL,String mAuthor,int mLikes)
    {
        this.imageURL=imageURL;
        this.mAuthor=mAuthor;
        this.mLikes=mLikes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public int getmLikes() {
        return mLikes;
    }
}
