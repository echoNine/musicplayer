package com.bignerdranch.android.musicplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.musicplay.dao.Song;
import com.bignerdranch.android.musicplay.lab.SongLab;

public class CommentListActivity extends SingleFragmentActivity {

    private String songName;
    private Song song;

    @Override
    protected Fragment createFragment() {
        return new CommentListFragment();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        this.songName = bundle.getString("song");
        this.song = SongLab.get(this).getSongBySongName(this.songName);
    }

    public String getSongName() {
        return songName;
    }

    public Song getSong() { return this.song; }
}