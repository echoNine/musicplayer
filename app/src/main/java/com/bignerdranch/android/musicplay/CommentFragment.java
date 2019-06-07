package com.bignerdranch.android.musicplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.bignerdranch.android.musicplay.dao.Comment;
import com.bignerdranch.android.musicplay.lab.CommentLab;


public class CommentFragment extends Fragment {
    private static final String ARG_COMMENT_ID = "comment_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int COMMENT_DATE = 0;

    private Comment mComment;
    private EditText mCommentatorField;
    private Button mDateButton;
    private Button mBackButton;
    private Button mSubmitButton;
    private EditText mContentField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComment = ((CommentActivity) getActivity()).getmComment();
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause(){
        super.onPause();

        CommentLab.get(getActivity()).updateComment(mComment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container,false);

        mCommentatorField = (EditText) v.findViewById(R.id.commentator);
        mCommentatorField.setText(mComment.getCommentator());
        mCommentatorField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mComment.setCommentator(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBackButton = (Button) v.findViewById(R.id.back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("song", mComment.getSong());
                Intent intent = new Intent(getActivity(), CommentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mSubmitButton = (Button) v.findViewById(R.id.submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("song", mComment.getSong());
                Intent intent = new Intent(getActivity(), CommentListActivity.class);
                intent.putExtras(bundle);
                CommentLab lab = CommentLab.get(getContext());
                lab.addComment(mComment);
                startActivity(intent);
            }
        });

        mDateButton = (Button) v.findViewById(R.id.date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mComment.getDate());
                dialog.setTargetFragment(CommentFragment.this,COMMENT_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });
        mContentField = (EditText) v.findViewById(R.id.content);
        mContentField.setText(mComment.getContent());
        mContentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mComment.setContent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK != requestCode && COMMENT_DATE == requestCode) {
            Date date = DatePickerFragment.getDate(data);
            mComment.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(mComment.getDate()));
    }

}