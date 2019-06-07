package com.bignerdranch.android.musicplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.musicplay.SongDbSchema.SongTable;

import com.bignerdranch.android.musicplay.SongDbSchema.CommentTable;

public class SongBaseHelper extends SQLiteOpenHelper {
    private static final int VESION = 1;
    private static final String DATABASE_NAME = "songBase.db";

    public SongBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SongTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SongTable.Cols.UUID + ", " +
                SongTable.Cols.SONG_ORDER + ", " +
                SongTable.Cols.SINGER + ", " +
                SongTable.Cols.SONG_NAME + ", " +
                SongTable.Cols.SONG_UNIT + ", " +
                SongTable.Cols.DURATION + ", " +
                SongTable.Cols.SONG_WORDS +
                ")"
        );

        db.execSQL("create table " + SongDbSchema.CommentTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CommentTable.Cols.UUID + ", " +
                CommentTable.Cols.SONG + ", " +
                CommentTable.Cols.COMMENTATOR + ", " +
                CommentTable.Cols.DATE + ", " +
                CommentTable.Cols.CONTENT +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
