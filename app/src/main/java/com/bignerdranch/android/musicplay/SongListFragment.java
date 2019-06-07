package com.bignerdranch.android.musicplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bignerdranch.android.musicplay.dao.Song;
import com.bignerdranch.android.musicplay.lab.SongLab;
import java.util.List;

public class SongListFragment extends Fragment {

    private RecyclerView mSongRecyclerView;
    private SongAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);
        mSongRecyclerView = (RecyclerView) view.findViewById(R.id.song_recycler_view);
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        SongLab songLab = SongLab.get(getActivity());
        List<Song> songs = songLab.getSongs();

        if (mAdapter == null) {
            mAdapter = new SongAdapter(songs);
            mSongRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setSongs(songs);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Song mSong;

        private TextView mOderTextView;
        private TextView mSongNameTextView;
        private TextView mSingerTextView;
        private TextView mDuration;

        public SongHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_song, parent,false));
            itemView.setOnClickListener(this);

            mOderTextView = (TextView) itemView.findViewById(R.id.item_song_order);
            mSongNameTextView =(TextView) itemView.findViewById(R.id.item_song_name);
            mSingerTextView =(TextView) itemView.findViewById(R.id.item_song_singer);
            mDuration = (TextView) itemView.findViewById(R.id.item_song_duration);
        }

        public void bind(Song song){
            mSong = song;
            mOderTextView.setText(mSong.getOrder());
            mSongNameTextView.setText(mSong.getSongName());
            mSingerTextView.setText(mSong.getSinger());
            mDuration.setText(mSong.getDuration());
        }

        @Override
        public void onClick(View v) {
            Intent intent = SongPagerActivity.newIntent(getActivity(),mSong.getId());
            startActivity(intent);
        }
    }

    private class SongAdapter extends RecyclerView.Adapter<SongHolder>{
        private List<Song> mSongs;

        public SongAdapter(List<Song> songs){
            mSongs = songs;
        }

        @Override
        public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SongHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(SongHolder songHolder, int position) {
            Song song = mSongs.get(position);
            songHolder.bind(song);

        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }

        public void setSongs(List<Song> songs){
            mSongs = songs;
        }
    }

}
