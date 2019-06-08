package com.bignerdranch.android.musicplay.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.musicplay.R;
import com.bignerdranch.android.musicplay.SongBaseHelper;
import com.bignerdranch.android.musicplay.SongCursorWrapper;
import com.bignerdranch.android.musicplay.SongDbSchema.SongTable;
import com.bignerdranch.android.musicplay.dao.Song;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SongLab {
    private static SongLab sSongLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SongLab get(Context context) {
        if (sSongLab == null) {
            sSongLab = new SongLab(context);
        }
        return sSongLab;
    }

    /**
     * 从raw中读取String
     */
    private String readFromRaw(int rawId) {
        String string = "";
        try {
            InputStream is = mContext.getResources().openRawResource(rawId);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            string = new String(buffer, "UTF-8");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 从assets中读取String
     */
    private String readFromAssets(String filename) {
        String string = "";
        try {
            InputStream is = mContext.getAssets().open(filename);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            string = new String(buffer, "UTF-8");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return string;
    }

    private void initSongs() {
        String songString = "";
        try {
            songString = this.readFromRaw(R.raw.songs);

            JSONArray jsonArray = new JSONArray(songString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String order = jsonObject.getString("order");
                String singer = jsonObject.getString("singer");
                String songName = jsonObject.getString("songName");
                String songUnit = jsonObject.getString("songUnit");
                String duration = jsonObject.getString("duration");

                String songWordsAssetsFileName = jsonObject.getString("songWordsAssetsFileName");
                String songWords = this.readFromAssets("songWords/" + songWordsAssetsFileName);
                String music = jsonObject.getString("songMusicAssetsFileName");

                Song song = new Song();
                song.setOrder(order);
                song.setSinger(singer);
                song.setDuration(duration);
                song.setSongName(songName);
                song.setSongUnit(songUnit);
                song.setSongWords(songWords);
                song.setMusic("music/" + music);
                addSong(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteByOrder(String order) {
        String whereClause = "order=?";
        String[] whereArgs = {order};
        mDatabase.delete(SongTable.NAME, whereClause, whereArgs);
    }

    private SongLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SongBaseHelper(mContext).getWritableDatabase();
        mDatabase.delete(SongTable.NAME, null, null);
        this.initSongs();
    }

    public void addSong(Song s) {
        ContentValues values = getContentValues(s);

        mDatabase.insert(SongTable.NAME, null, values);
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();

        SongCursorWrapper cursor = querySongs(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                songs.add(cursor.getSong());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return songs;
    }

    public Song getSong(UUID id) {
        SongCursorWrapper cursor = querySongs(
                SongTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSong();
        } finally {
            cursor.close();
        }
    }

    public void updateSong(Song song) {
        String uuidString = song.getId().toString();
        ContentValues values = getContentValues(song);
        mDatabase.update(SongTable.NAME, values,
                SongTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private SongCursorWrapper querySongs(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                SongTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new SongCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Song song) {
        ContentValues values = new ContentValues();
        values.put(SongTable.Cols.UUID, song.getId().toString());
        values.put(SongTable.Cols.SONG_ORDER, song.getOrder());
        values.put(SongTable.Cols.SINGER, song.getSinger());
        ;
        values.put(SongTable.Cols.SONG_NAME, song.getSongName());
        values.put(SongTable.Cols.SONG_UNIT, song.getSongUnit());
        values.put(SongTable.Cols.DURATION, song.getDuration());
        values.put(SongTable.Cols.SONG_WORDS, song.getSongWords());
        values.put(SongTable.Cols.MUSIC, song.getMusic());

        return values;
    }
}
