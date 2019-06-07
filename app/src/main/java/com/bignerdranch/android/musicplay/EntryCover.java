package com.bignerdranch.android.musicplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class EntryCover extends AppCompatActivity {
    private TextView mEnrtyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        mEnrtyView = (TextView) findViewById(R.id.entry);
        mEnrtyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryCover.this, SongListActivity.class);
                startActivity(intent);
            }
        });
    }
}
