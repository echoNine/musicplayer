package com.bignerdranch.android.musicplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bignerdranch.android.musicplay.dao.Comment;
import com.bignerdranch.android.musicplay.lab.CommentLab;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CommentListFragment extends Fragment {

    private String songName = "com.bignerdranch.android.musicplay.song_id";

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mCommentRecyclerView;
    private CommentAdapter mAdapter;
    private boolean mSubtitleVisible;
    private TextView mEmptyTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);
        mCommentRecyclerView = (RecyclerView) view
                .findViewById(R.id.comment_recycler_view);
        mCommentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        this.songName = ((CommentListActivity)getActivity()).getSongName();
        mEmptyTextView = (TextView) view.findViewById(R.id.empty_comment_list);
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_comment_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_comment:
                Comment comment = new Comment();
                comment.setSong(this.songName);
                Intent intent = CommentActivity
                        .newIntent(getActivity(), comment);
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        CommentLab commentLab = CommentLab.get(getActivity());
        int commentSize = commentLab.getCommentsBySong(this.songName).size();
        String subtitle = getResources()
                .getQuantityString(R.plurals.subtitle_plural, commentSize, commentSize);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        //如何在这里获取到 CommentListActivity.EXTRA_SONG_ID

        CommentLab commentLab = CommentLab.get(getActivity());
        List<Comment> comments = commentLab.getCommentsBySong(this.songName);

        if (mAdapter == null) {
            mAdapter = new CommentAdapter(comments);
            mCommentRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setComments(comments);
            mAdapter.notifyDataSetChanged();
        }

        if (comments.size() != 0) {
            mEmptyTextView.setVisibility(View.INVISIBLE);
        } else {
            mEmptyTextView.setVisibility(View.VISIBLE);
        }

        updateSubtitle();
    }

    private class CommentHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Comment mComment;

        private TextView mCommentatorTextView;
        private TextView mContentTextView;
        private TextView mDateTextView;

        public CommentHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_comment, parent, false));
            itemView.setOnClickListener(this);

            mCommentatorTextView = (TextView) itemView.findViewById(R.id.commentator);
            mDateTextView = (TextView) itemView.findViewById(R.id.date);
            mContentTextView = (TextView) itemView.findViewById(R.id.content);
        }

        public void bind(Comment comment) {
            mComment = comment;
            mCommentatorTextView.setText(mComment.getCommentator());
            mDateTextView.setText(new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(mComment.getDate()));
            mContentTextView.setText(mComment.getContent());
        }

        @Override
        public void onClick(View view) {
            Intent intent = CommentActivity.newIntent(getActivity(), mComment);
            startActivity(intent);
        }
    }

    private class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {

        private List<Comment> mComments;

        public CommentAdapter(List<Comment> comments) {
            mComments = comments;
        }

        @Override
        public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CommentHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CommentHolder holder, int position) {
            Comment comment = mComments.get(position);
            holder.bind(comment);
        }

        @Override
        public int getItemCount() {
            return mComments.size();
        }

        public void setComments(List<Comment> comments){
            mComments = comments;
        }
    }
}
