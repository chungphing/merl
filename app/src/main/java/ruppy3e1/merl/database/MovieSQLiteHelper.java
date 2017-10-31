package ruppy3e1.merl.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by chunpghing
 */

public class MovieSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "movies.db";
    private static final int DB_VERSION = 1;

    public static final String MOVIES_TABLE = "MOVIES";
    public static final String GENRES_TABLE = "GENRES";
    public static final String ALT_ID_TABLE = "ALT_ID";
    public static final String MOVIE_SHOWTIMES_TABLE  = "MOVIE_SHOWTIMES";
    public static final String MOVIE_HALLS_TABLE = "MOVIE_HALLS";
    public static final String MOVIE_DATES_TABLE = "MOVIE_DATES";
    public static final String MOVIE_DETAIL_URLS_TABLE = "MOVIE_DETAIL_URLS";


    public static final String COLUMN_FOREIGN_KEY_MOVIE ="MOVIE_ID";
    public static final String COLUMN_MOVIE_NAME = "NAME";

    //could use another table not important
    public static final String COLUMN_MOVIE_ALT_NAME = "ALT_NAME";

    //need to create another table
    public static final String COLUMN_MOVIE_GENRE = "GENRE";

    public static final String COLUMN_MOVIE_DESCRIPTION = "DESCRIPTION";


    public static final String COLUMN_MOVIE_IS_AIRED = "IS_AIRED";
    public static final String COLUMN_MOVIE_IS_SHOWING = "IS_SHOWING";
    public static final String COLUMN_MOVIE_IS_COMMING = "IS_COMMING";

    public static final String COLUMN_MOVIES_TRAILER_LINK = "TRAILER_LINK";
    public static final String COLUMN_MOVIE_IMAGE_LINK = "IMAGE_LINK";

    public static final String COLUMN_MOVIE_RUNTIME = "RUNTIME";
    public static final String COLUMN_MOVIE_RATING = "RATING";

    //need to create another table
    public static final String COLUMN_MOVIE_LEGEND_ID = "LEGEND_ID";
    public static final String COLUMN_MOVIE_MAJOR_ID = "MAJOR_ID";
    public static final String COLUMN_MOVIE_PLATINUM_ID = "PLATINUM_ID";


    //need to create another table
    public static final String COLUMN_MOVIE_LEGEND_SHOWTIME = "LEGEND_SHOWTIME";
    public static final String COLUMN_MOVIE_MAJOR_SHOWTIME = "MAJOR_SHOWTIME";
    public static final String COLUMN_MOVIE_PLATINUM_SHOWTIME = "PLATINUM_SHOWTIME";


    //need to create another table
    public static final String COLUMN_MOVIE_LEGEND_HALL = "LEGEND_HALL";
    public static final String COLUMN_MOVIE_MAJOR_HALL = "MAJOR_HALL";
    public static final String COLUMN_MOVIE_PLATINUM_HALL = "PLATINUM_HALL";


    //need to create another table
    public static final String COLUMN_MOVIE_LEGEND_DATE = "LEGEND_DATE";
    public static final String COLUMN_MOVIE_MAJOR_DATE ="MAJOR_DATE";
    public static final String COLUMN_MOVIE_PLATINUM_DATE ="PLATINUM_DATE";

    //column of table detail url

    public static final String COLUMN_MOVIE_DETAIL_URL_LEGEND = "LEGEND_DETAIL_URL";
    public static final String COLUMN_MOVIE_DETAIL_URL_MAJOR = "MAJOR_DETAIL_URL";
    public static final String COLUMN_MOVIE_DETAIL_URL_PLATINUM = "PLATINUM_DETAIL_URL";

    //QUERY



    //create movie table
    private static final String  CREATE_MOVIES =
            "CREATE TABLE " + MOVIES_TABLE + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MOVIE_NAME + " TEXT," + COLUMN_MOVIE_ALT_NAME + " TEXT, " + COLUMN_MOVIE_RATING + " TEXT, " + COLUMN_MOVIE_RUNTIME + " TEXT, " +
                    COLUMN_MOVIE_DESCRIPTION + " TEXT, " + COLUMN_MOVIE_IS_AIRED + " INTEGER, " + COLUMN_MOVIE_IS_SHOWING + " INTEGER, " + COLUMN_MOVIE_IS_COMMING + " INTEGER, " + COLUMN_MOVIES_TRAILER_LINK + " TEXT, " + COLUMN_MOVIE_IMAGE_LINK  + " TEXT) ";



    //create genre table
    private static final String CREATE_GENRES = "CREATE TABLE " + GENRES_TABLE + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_GENRE + " TEXT, " +

            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";

    //create cinemas_id table
    private static final String CREATE_ALT_ID = "CREATE TABLE " + ALT_ID_TABLE + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_LEGEND_ID + " TEXT, " + COLUMN_MOVIE_MAJOR_ID + " TEXT, " + COLUMN_MOVIE_PLATINUM_ID + " TEXT, " +
            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";
    //create showtime table
    private static final String CREATE_SHOWTIMES = "CREATE TABLE " + MOVIE_SHOWTIMES_TABLE + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_LEGEND_SHOWTIME + " TEXT, " + COLUMN_MOVIE_MAJOR_SHOWTIME + " TEXT, " + COLUMN_MOVIE_PLATINUM_SHOWTIME +" TEXT, " +

            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";
    //create hall table
    private static final String CREATE_HALLS = "CREATE TABLE " + MOVIE_HALLS_TABLE + "(" +BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_LEGEND_HALL + " TEXT, " + COLUMN_MOVIE_MAJOR_HALL + " TEXT, " + COLUMN_MOVIE_PLATINUM_HALL + " TEXT, " +

            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";
    //create date table
    private static final String CREATE_DATES = "CREATE TABLE " + MOVIE_DATES_TABLE + "(" +BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_LEGEND_DATE + " TEXT, " + COLUMN_MOVIE_MAJOR_DATE + " TEXT, " + COLUMN_MOVIE_PLATINUM_DATE + " TEXT, "+

            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";



    //create detail url table
    public static final String CREATE_DETAIL_URLS = "CREATE TABLE " + MOVIE_DETAIL_URLS_TABLE + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOVIE_DETAIL_URL_LEGEND + " TEXT, " + COLUMN_MOVIE_DETAIL_URL_MAJOR + " TEXT, " + COLUMN_MOVIE_DETAIL_URL_PLATINUM + " TEXT, " +
            COLUMN_FOREIGN_KEY_MOVIE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MOVIE + ") REFERENCES MOVIE(_ID))";









    public MovieSQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_MOVIES);
        db.execSQL(CREATE_GENRES);
        db.execSQL(CREATE_ALT_ID);
        db.execSQL(CREATE_SHOWTIMES);
        db.execSQL(CREATE_HALLS);
        db.execSQL(CREATE_DATES);
        db.execSQL(CREATE_DETAIL_URLS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
