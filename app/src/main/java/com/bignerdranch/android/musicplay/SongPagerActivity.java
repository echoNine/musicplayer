package com.bignerdranch.android.musicplay;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bignerdranch.android.musicplay.dao.Song;
import com.bignerdranch.android.musicplay.lab.SongLab;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SongPagerActivity extends AppCompatActivity {
    private static final String EXTRA_SONG_ID = "com.bignerdranch.android.musicplay.song_id";

    private ViewPager mViewPager;
    private List<Song> mSongs;
    private static MediaPlayer mPlayer;

    public static MediaPlayer getPlayer () {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }

        return mPlayer;
    }

    public void replay (UUID songId) {
        Song mSong = SongLab.get(this).getSong(songId);
        if (getPlayer().isPlaying()) {
            getPlayer().stop();
            destroyPlayer();
        }

        try {
            Log.e(getClass().getName(), mSong.getMusic());
            AssetFileDescriptor fd = getAssets().openFd(mSong.getMusic());
            getPlayer().setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            getPlayer().prepare();
            getPlayer().setLooping(true);
            getPlayer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void destroyPlayer () {
        mPlayer = null;
    }

    public static Intent newIntent(Context packageContext, UUID songId, Boolean replaynNew) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext, SongPagerActivity.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        bundle.putBoolean("replayOnNew", replaynNew);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_pager);
        UUID songId = (UUID) getIntent().getSerializableExtra(EXTRA_SONG_ID);
        Bundle bundle = getIntent().getExtras();
        Log.d(getClass().getName(), bundle.getBoolean("notReplay") + "");
        if (!bundle.getBoolean("notReplay")) {
            this.replay(songId);
        }
        mViewPager = (ViewPager) findViewById(R.id.song_view_pager);

        mSongs = SongLab.get(this) .getSongs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Log.v(this.getClass().getName(), position + "");
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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * 滑动事件结束时
             * @param i
             */
            @Override
            public void onPageSelected(int i) {
                SongPagerActivity.this.replay(mSongs.get(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onDestroy() {
        SongPagerActivity.getPlayer().stop();
        SongPagerActivity.destroyPlayer();
        super.onDestroy();
    }
}
