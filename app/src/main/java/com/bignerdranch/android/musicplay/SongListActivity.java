package com.bignerdranch.android.musicplay;

import android.support.v4.app.Fragment;

public class SongListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new SongListFragment();
    }
}
