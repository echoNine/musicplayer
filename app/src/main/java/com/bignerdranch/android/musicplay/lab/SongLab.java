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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SongLab {
    private static SongLab sSongLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SongLab get(Context context){
        if (sSongLab == null){
            sSongLab = new SongLab(context);
        }
        return sSongLab;
    }

    private SongLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new SongBaseHelper(mContext).getWritableDatabase();
        Song one = new Song();
        one.setOrder("1");
        one.setSinger("胡夏");
        one.setSongName("十万灰尘");
        one.setSongUnit("大雨文化");
        one.setDuration("04:05");
        String resone = "";
        try{
            InputStream inputTwo =  context.getResources().openRawResource(R.raw.one);
            int length = inputTwo.available();
            byte [] buffer = new byte[length];
            inputTwo.read(buffer);
            resone = new String(buffer, "UTF-8");
            inputTwo.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        one.setSongWords(resone);
        Song two = new Song();
        two.setOrder("2");
        two.setSinger("陈雪凝");
        two.setSongName("绿色");
        two.setSongUnit("坚诚文化");
        two.setDuration("04:29");
        String restwo = "";
        try{
            InputStream inputTwo =  context.getResources().openRawResource(R.raw.two);
            int length = inputTwo.available();
            byte [] buffer = new byte[length];
            inputTwo.read(buffer);
            restwo = new String(buffer, "UTF-8");
            inputTwo.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        two.setSongWords(restwo);
        Song three = new Song();
        three.setOrder("3");
        three.setSinger("郁可唯");
        three.setSongName("阳台上");
        three.setSongUnit("乐见未来");
        three.setDuration("04:58");
        String resthree = "";
        try{
            InputStream inputThree =  context.getResources().openRawResource(R.raw.three);
            int length = inputThree.available();
            byte [] buffer = new byte[length];
            inputThree.read(buffer);
            resthree = new String(buffer, "UTF-8");
            inputThree.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        three.setSongWords(resthree);
        Song four = new Song();
        four.setOrder("4");
        four.setSinger("姚贝娜");
        four.setSongName("画情");
        four.setSongUnit("华谊兄弟");
        four.setDuration("05:26");
        String resfour = "";
        try{
            InputStream inputFour =  context.getResources().openRawResource(R.raw.four);
            int length = inputFour.available();
            byte [] buffer = new byte[length];
            inputFour.read(buffer);
            resfour = new String(buffer, "UTF-8");
            inputFour.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        four.setSongWords(resfour);
        Song five = new Song();
        five.setOrder("5");
        five.setSinger("昼夜");
        five.setSongName("愿你");
        five.setSongUnit("原创民谣");
        five.setDuration("03:35");
        String resfive = "";
        try{
            InputStream inputFive =  context.getResources().openRawResource(R.raw.five);
            int length = inputFive.available();
            byte [] buffer = new byte[length];
            inputFive.read(buffer);
            resfive = new String(buffer, "UTF-8");
            inputFive.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        five.setSongWords(resfive);

        Song six = new Song();
        six.setOrder("6");
        six.setSinger("吴亦凡");
        six.setSongName("大碗宽面");
        six.setSongUnit("环球唱片");
        six.setDuration("03:41");
        String ressix = "";
        try{
            InputStream inputSix =  context.getResources().openRawResource(R.raw.six);
            int length = inputSix.available();
            byte [] buffer = new byte[length];
            inputSix.read(buffer);
            ressix = new String(buffer, "UTF-8");
            inputSix.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        six.setSongWords(ressix);

        Song seven = new Song();
        seven.setOrder("7");
        seven.setSinger("周杰伦");
        seven.setSongName("告白气球");
        seven.setSongUnit("杰威尔音乐有限公司");
        seven.setDuration("03:35");
        String resseven = "";
        try{
            InputStream inputSeven =  context.getResources().openRawResource(R.raw.seven);
            int length = inputSeven.available();
            byte [] buffer = new byte[length];
            inputSeven.read(buffer);
            resseven = new String(buffer, "UTF-8");
            inputSeven.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        seven.setSongWords(resseven);

        Song eight = new Song();
        eight.setOrder("8");
        eight.setSinger("胡夏");
        eight.setSongName("直觉");
        eight.setSongUnit("燃音乐");
        eight.setDuration("05:32");
        String reseight = "";
        try{
            InputStream inputEight =  context.getResources().openRawResource(R.raw.eight);
            int length = inputEight.available();
            byte [] buffer = new byte[length];
            inputEight.read(buffer);
            reseight = new String(buffer, "UTF-8");
            inputEight.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        eight.setSongWords(reseight);

        Song nine = new Song();
        nine.setOrder("9");
        nine.setSinger("胡夏");
        nine.setSongName("红墙叹");
        nine.setSongUnit("东阳欢娱影视文化有限公司");
        nine.setDuration("04:30");
        String resnine = "";
        try{
            InputStream inputNine =  context.getResources().openRawResource(R.raw.nine);
            int length = inputNine.available();
            byte [] buffer = new byte[length];
            inputNine.read(buffer);
            resnine = new String(buffer, "UTF-8");
            inputNine.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        nine.setSongWords(resnine);

        Song ten = new Song();
        ten.setOrder("10");
        ten.setSinger("胡夏/郁可唯");
        ten.setSongName("知否知否");
        ten.setSongUnit("世纪中腾");
        ten.setDuration("04:36");
        String resten = "";
        try{
            InputStream inputTen =  context.getResources().openRawResource(R.raw.ten);
            int length = inputTen.available();
            byte [] buffer = new byte[length];
            inputTen.read(buffer);
            resten = new String(buffer, "UTF-8");
            inputTen.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        ten.setSongWords(resten);

//        addSong(one);
//        addSong(two);
//        addSong(three);
//        addSong(four);
//        addSong(five);
//        addSong(six);
//        addSong(seven);
//        addSong(eight);
//        addSong(nine);
//        addSong(ten);
    }

    public void addSong(Song s){
        ContentValues values = getContentValues(s);

        mDatabase.insert(SongTable.NAME,null,values);
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();

        SongCursorWrapper cursor = querySongs(null,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                songs.add(cursor.getSong());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return songs;
    }

    public Song getSong(UUID id) {
        SongCursorWrapper cursor = querySongs(
                SongTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSong();
        }finally {
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

    private SongCursorWrapper querySongs(String whereClause,String[] whereArgs){
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

    private static ContentValues getContentValues(Song song){
        ContentValues values = new ContentValues();
        values.put(SongTable.Cols.UUID,song.getId().toString());
        values.put(SongTable.Cols.SONG_ORDER,song.getOrder());
        values.put(SongTable.Cols.SINGER,song.getSinger());;
        values.put(SongTable.Cols.SONG_NAME,song.getSongName());
        values.put(SongTable.Cols.SONG_UNIT,song.getSongUnit());
        values.put(SongTable.Cols.DURATION,song.getDuration());
        values.put(SongTable.Cols.SONG_WORDS,song.getSongWords());

        return values;
    }
}
