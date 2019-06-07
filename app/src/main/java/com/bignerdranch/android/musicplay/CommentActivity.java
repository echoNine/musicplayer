package com.bignerdranch.android.musicplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.bignerdranch.android.musicplay.dao.Comment;

import java.util.List;

public class CommentActivity extends SingleFragmentActivity {
    private static final String EXTRA_COMMENT_ID = "com.bignerdranch.android.musicplay.comment_id";

    private List<Comment> mComments;

    private Comment mComment;

    public static Intent newIntent(Context packageContext, Comment comment) {
        Bundle bundle=new Bundle();
        bundle.putString("song", comment.getSong());
        bundle.putSerializable("comment", comment);
        Intent intent = new Intent(packageContext, CommentActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(EXTRA_COMMENT_ID,comment.getId());
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new CommentFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle bundle = getIntent().getExtras();
        mComment = (Comment) bundle.getSerializable("comment");
    }

    public Comment getmComment() {
        return mComment;
    }
}