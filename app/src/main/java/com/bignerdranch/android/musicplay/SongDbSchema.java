package com.bignerdranch.android.musicplay;

public class SongDbSchema {
    public static final class SongTable{
        public static final String NAME = "songs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SONG_ORDER = "song_order";
            public static final String SINGER = "singer";
            public static final String SONG_NAME = "song_name";
            public static final String SONG_UNIT = "song_unit";
            public static final String DURATION = "duration";
            public static final String SONG_WORDS = "song_words";
        }
    }

    public static final class CommentTable{
        public static final String NAME = "comments";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SONG = "song";
            public static final String COMMENTATOR = "commentator";
            public static final String DATE = "date";
            public static final String CONTENT = "content";
        }
    }
}
