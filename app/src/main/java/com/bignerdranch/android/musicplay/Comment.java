package com.bignerdranch.android.musicplay;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private UUID mId;
    private String mSong;
    private String mCommentator;
    private Date mDate;
    private String mContent;

    public Comment(){
        this(UUID.randomUUID());
    }

    public Comment(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getSong() {
        return mSong;
    }

    public void setSong(String song) {
        mSong = song;
    }

    public String getCommentator() {
        return mCommentator;
    }

    public void setCommentator(String commentator) {
        mCommentator = commentator;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
