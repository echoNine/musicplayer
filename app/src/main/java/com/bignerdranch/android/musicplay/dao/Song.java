package com.bignerdranch.android.musicplay.dao;

import java.util.UUID;

public class Song {
    /**
     * 序号
     */
    private UUID mId;
    /**
     * 次序
     */
    private String mOrder;
    /**
     * 歌手
     */
    private String mSinger;
    /**
     * 歌曲名
     */
    private String mSongName;
    /**
     * 唱片公司
     */
    private String mSongUnit;
    /**
     * 歌曲时长
     */
    private String duration;
    /**
     * 歌词
     */
    private String mSongWords;

    /**
     * 歌曲文件地址
     */
    private String music;

    public Song() {
        this(UUID.randomUUID());
    }

    public Song(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String order) {
        mOrder = order;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String songName) {
        mSongName = songName;
    }

    public String getSongUnit() {
        return mSongUnit;
    }

    public void setSongUnit(String songUnit) {
        mSongUnit = songUnit;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongWords() {
        return mSongWords;
    }

    public void setSongWords(String songWords) {
        mSongWords = songWords;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
