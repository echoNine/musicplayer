package com.bignerdranch.android.musicplay;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.musicplay.SongDbSchema.SongTable;
import com.bignerdranch.android.musicplay.dao.Song;
import com.bignerdranch.android.musicplay.SongDbSchema.CommentTable;
import com.bignerdranch.android.musicplay.dao.Comment;

import java.util.Date;
import java.util.UUID;

public class SongCursorWrapper extends CursorWrapper {
    public SongCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Song getSong(){
        String uuidString = getString(getColumnIndex(SongTable.Cols.UUID));
        String song_order = getString(getColumnIndex(SongTable.Cols.SONG_ORDER));
        String singer = getString(getColumnIndex(SongTable.Cols.SINGER));
        String song_name = getString(getColumnIndex(SongTable.Cols.SONG_NAME));
        String song_unit = getString(getColumnIndex(SongTable.Cols.SONG_UNIT));
        String duration = getString(getColumnIndex(SongTable.Cols.DURATION));
        String song_words = getString(getColumnIndex(SongTable.Cols.SONG_WORDS));
        String music = getString(getColumnIndex(SongTable.Cols.MUSIC));

        Song song = new Song(UUID.fromString(uuidString));
        song.setOrder(song_order);
        song.setSinger(singer);
        song.setSongName(song_name);
        song.setSongUnit(song_unit);
        song.setDuration(duration);
        song.setSongWords(song_words);
        song.setMusic(music);

        return song;
    }

    public Comment getComment(){
        String uuidString = getString(getColumnIndex(CommentTable.Cols.UUID));
        String song = getString(getColumnIndex(CommentTable.Cols.SONG));
        String commentator = getString(getColumnIndex(CommentTable.Cols.COMMENTATOR));
        long date = getLong(getColumnIndex(CommentTable.Cols.DATE));
        String content = getString(getColumnIndex(CommentTable.Cols.CONTENT));

        Comment comment = new Comment(UUID.fromString(uuidString));
        comment.setSong(song);
        comment.setCommentator(commentator);
        comment.setDate(new Date(date));
        comment.setContent(content);

        return comment;
    }
}
