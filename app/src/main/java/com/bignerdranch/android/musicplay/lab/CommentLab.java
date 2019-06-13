package com.bignerdranch.android.musicplay.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.musicplay.SongBaseHelper;
import com.bignerdranch.android.musicplay.SongCursorWrapper;
import com.bignerdranch.android.musicplay.SongDbSchema.CommentTable;
import com.bignerdranch.android.musicplay.dao.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentLab {
    private static CommentLab sCommentLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CommentLab get(Context context) {
        if (sCommentLab == null) {
            sCommentLab = new CommentLab(context);
        }

        return sCommentLab;
    }

    private CommentLab(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new SongBaseHelper(mContext).getWritableDatabase();
      //  mComments = new ArrayList<>();
    }

    public void addComment(Comment c) {

        //mComments.add(c);
        ContentValues values = getContentValues(c);

        mDatabase.insert(CommentTable.NAME,null,values);
    }

    public List<Comment> getComments() {

        //return mComments;
        //return new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
//        String[] whereArgs = {song};
//        SongCursorWrapper cursor = queryComments("song=?",whereArgs);
        SongCursorWrapper cursor = queryComments(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                comments.add(cursor.getComment());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return comments;
    }

    public List<Comment> getCommentsBySong(String song) {


        //return mComments;
        //return new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        String[] whereArgs = {song};
        SongCursorWrapper cursor = queryComments("song=?",whereArgs);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                comments.add(cursor.getComment());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return comments;
    }


    public Comment getComment(UUID id) {
        SongCursorWrapper cursor = queryComments(
                CommentTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getComment();
        }finally {
            cursor.close();
        }
    }

    public void updateComment(Comment comment){
        String uuidString = comment.getId().toString();
        ContentValues values = getContentValues(comment);
        mDatabase.update(CommentTable.NAME,values,CommentTable.Cols.UUID + " = ?",
                new String[]{
                        uuidString
                });
    }

    private SongCursorWrapper queryComments(String whereClause,String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CommentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new SongCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Comment comment){

        ContentValues values = new ContentValues();
        values.put(CommentTable.Cols.UUID,comment.getId().toString());
        values.put(CommentTable.Cols.SONG,comment.getSong());
        values.put(CommentTable.Cols.COMMENTATOR,comment.getCommentator());
        values.put(CommentTable.Cols.CONTENT,comment.getContent());
        values.put(CommentTable.Cols.DATE,comment.getDate().getTime());

        return values;

    }

}