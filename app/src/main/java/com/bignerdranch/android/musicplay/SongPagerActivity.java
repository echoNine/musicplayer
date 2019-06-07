package com.bignerdranch.android.musicplay;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.musicplay.dao.Song;
import com.bignerdranch.android.musicplay.lab.SongLab;

import java.util.List;
import java.util.UUID;

public class SongPagerActivity extends AppCompatActivity {
    private static final String EXTRA_SONG_ID = "com.bignerdranch.android.musicplay.song_id";
    public static int[] musicList = {R.raw.one_music,R.raw.two_music,R.raw.three_music,R.raw.four_music,R.raw.five_music,R.raw.six_music,R.raw.seven_music,R.raw.eight_music,R.raw.nine_music,R.raw.ten_music};

    private ViewPager mViewPager;
    private List<Song> mSongs;
    public static MediaPlayer mPlayer;
    private String songOrder;

    public static Intent newIntent(Context packageContext, UUID songId) {
        Intent intent = new Intent(packageContext, SongPagerActivity.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_pager);
        UUID songId = (UUID) getIntent().getSerializableExtra(EXTRA_SONG_ID);

        mViewPager = (ViewPager) findViewById(R.id.song_view_pager);

        mSongs = SongLab.get(this) .getSongs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Song song = mSongs.get(position);
                return SongFragment.newInstance(song.getId());
            }

            @Override
            public int getCount() {
                return mSongs.size();
            }
        });

        for (int i = 0; i < mSongs.size(); i++) {
            if (mSongs.get(i).getId().equals(songId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
