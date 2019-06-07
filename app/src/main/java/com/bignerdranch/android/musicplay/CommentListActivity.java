package com.bignerdranch.android.musicplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class CommentListActivity extends SingleFragmentActivity {

    private String songName;

    @Override
    protected Fragment createFragment() {
        return new CommentListFragment();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        this.songName = bundle.getString("song");
    }

    public String getSongName() {
        return songName;
    }
}