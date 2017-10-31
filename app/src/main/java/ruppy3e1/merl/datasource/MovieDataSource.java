package ruppy3e1.merl.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

import ruppy3e1.merl.database.MovieSQLiteHelper;
import ruppy3e1.merl.model.Movie;
import ruppy3e1.merl.model.MovieAltID;
import ruppy3e1.merl.model.MovieDate;
import ruppy3e1.merl.model.MovieGenre;
import ruppy3e1.merl.model.MovieHall;
import ruppy3e1.merl.model.MovieShowTime;
import ruppy3e1.merl.model.MovieURL;

import static android.content.ContentValues.TAG;

/**
 * Created by chunpghing
 */

public class MovieDataSource {

    private Context mContext;
    private MovieSQLiteHelper mMovieSQLiteHelper;

    public MovieDataSource(Context context){
        mContext = context;
        mMovieSQLiteHelper = new MovieSQLiteHelper(context);
//        SQLiteDatabase database = mMovieSQLiteHelper.getReadableDatabase();
//        database.close();
    }

    public void create(Movie movie){
        SQLiteDatabase database = open();

        int count =0;
        database.beginTransaction();
        //implement detail
        ContentValues movieValues = new ContentValues();
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_NAME, movie.getmName());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_ALT_NAME, movie.getmAltName());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_RATING, movie.getmRating());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_RUNTIME, movie.getmRunTime());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DESCRIPTION, movie.getmDescription());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_AIRED , movie.getAired());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_SHOWING , movie.getShowing());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_COMMING , movie.getComming());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIES_TRAILER_LINK , movie.getmTrailerLink());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK , movie.getmImgLink());
        long movieID = database.insert(MovieSQLiteHelper.MOVIES_TABLE, null, movieValues);

        for (MovieAltID movieAltID : movie.getmMovieAltID()){
            ContentValues altIDValues = new ContentValues();
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_ID, movieAltID.getLegendID());
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_ID, movieAltID.getMajorID());
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_ID, movieAltID.getPlatinumID());
            altIDValues.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);
            database.insert(MovieSQLiteHelper.ALT_ID_TABLE, null, altIDValues);
        }

        for (MovieGenre movieGenre: movie.getmMovieGenre()){
            ContentValues genreValue = new ContentValues();
            genreValue.put(MovieSQLiteHelper.COLUMN_MOVIE_GENRE, movieGenre.getmGenreCode());
            genreValue.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);
            database.insert(MovieSQLiteHelper.GENRES_TABLE, null , genreValue);
            //// TODO: 2/17/17  have not yet implement
        }
        for (MovieShowTime movieShowTime : movie.getmMovieShowTime()){
            ContentValues movieShowTimeValues = new ContentValues();
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_SHOWTIME, movieShowTime.getLegendShowTime());
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_SHOWTIME, movieShowTime.getMajorShowTime());
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_SHOWTIME, movieShowTime.getPlatinumShowTime());
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);


            database.insert(MovieSQLiteHelper.MOVIE_SHOWTIMES_TABLE, null, movieShowTimeValues);

        }

        for (MovieHall movieHall : movie.getmMovieHall()){


            ContentValues movieHallValues = new ContentValues();
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_HALL, movieHall.getLegendHall());
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_HALL, movieHall.getMajorHall());
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_HALL, movieHall.getPlatinumHall());
            movieHallValues.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);


            database.insert(MovieSQLiteHelper.MOVIE_HALLS_TABLE, null, movieHallValues);
        }


        for (MovieDate movieDate : movie.getmMovieDate()){
            ContentValues movieDateValues = new ContentValues();
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_DATE, movieDate.getLegendDate());
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_DATE, movieDate.getMajorDate());
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_DATE, movieDate.getPlatinumDate());
            movieDateValues.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);
            database.insert(MovieSQLiteHelper.MOVIE_DATES_TABLE, null, movieDateValues);

        }
        for (MovieURL movieURL : movie.getmMovieURL()){
            ContentValues movieURLValues = new ContentValues();
            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_LEGEND, movieURL.getLegendURL());
            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_MAJOR, movieURL.getMajorURL());
            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_PLATINUM, movieURL.getLegendURL());
            movieURLValues.put(MovieSQLiteHelper.COLUMN_FOREIGN_KEY_MOVIE, movieID);
            database.insert(MovieSQLiteHelper.MOVIE_DETAIL_URLS_TABLE,null,movieURLValues);

        }


        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);


        Log.i(TAG, "create: total count " + count);

    }

//
//    public ArrayList<Movie> read(){
//        ArrayList<Movie> movies = readMovie();
//        addMovieDetails(movies);
//        return  movies;
//    }





    public Movie readDetailMovie(int index){

        SQLiteDatabase database = open();
        Cursor cursor = database.rawQuery("SELECT * FROM " + MovieSQLiteHelper.MOVIES_TABLE + " WHERE _ID LIKE " + index, null);
        Movie movie;

        if (cursor.moveToFirst()){
            do {
                movie = new Movie(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_ALT_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_DESCRIPTION),
                        convertIntToBool(getIntFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IS_AIRED)),
                        convertIntToBool(getIntFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IS_SHOWING)),
                        convertIntToBool(getIntFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IS_COMMING)),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_RATING),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_RUNTIME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIES_TRAILER_LINK),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

            }while (cursor.moveToNext());

            cursor.close();


           //start


            ArrayList<MovieGenre> genre = new ArrayList<>();
            ArrayList<MovieHall> movieHall = new ArrayList<>();
            ArrayList<MovieDate> movieDate = new ArrayList<>();
            ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
            ArrayList<MovieURL> movieURLs = new ArrayList<>();



            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_SHOWTIMES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieShowTime showTime = new MovieShowTime(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_SHOWTIME),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_SHOWTIME),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_SHOWTIME));

                    movieShowTime.add(showTime);
                }while (cursor.moveToNext());


            }




            movie.setmMovieShowTime(movieShowTime);


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_HALLS_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieHall hall = new MovieHall(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_HALL),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_HALL),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_HALL));

                    movieHall.add(hall);
                }while (cursor.moveToNext());


            }

            movie.setmMovieHall(movieHall);
            //


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_DATES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieDate date = new MovieDate(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_DATE),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_DATE),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_DATE));

                    movieDate.add(date);
                }while (cursor.moveToNext());


            }

            movie.setmMovieDate(movieDate);
            //


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.GENRES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieGenre genres = new MovieGenre(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getIntFromColumnName(cursor, (MovieSQLiteHelper.COLUMN_MOVIE_GENRE)));

                    genre.add(genres);
                }while (cursor.moveToNext());


            }

            movie.setmMovieGenre(genre);


            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_DETAIL_URLS_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieURL url = new MovieURL(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, (MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_LEGEND)),
                            getStringFromColumnName(cursor, (MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_LEGEND)),
                            getStringFromColumnName(cursor, (MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_LEGEND)));

                    movieURLs.add(url);
                }while (cursor.moveToNext());


            }

            movie.setmMovieURL(movieURLs);

            //end






            close(database);

            return movie;
            }
        return null;
    }

    private Boolean convertIntToBool(int value){
        return value == 1;
    }

    public  ArrayList<Movie> readName(){
        return readNameMovie();
    }

    public  ArrayList<Movie> readNowShowing(){
        return readNowShowingMovie();
    }

    public  ArrayList<Movie> readComingSoon(){
        return readComingSoonMovie();
    }

    public ArrayList<Movie> readMovie(){
        SQLiteDatabase database = open();

        Cursor cursor = database.query(
                MovieSQLiteHelper.MOVIES_TABLE,
                new String[]{ MovieSQLiteHelper.COLUMN_MOVIE_NAME, BaseColumns._ID, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK},
                null,   //selection
                null,   //selectiion args
                null,   //group by
                null,   //having
                null);   //order

        ArrayList<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {

                Movie movie = new Movie(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor,MovieSQLiteHelper.COLUMN_MOVIE_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK));
                movies.add(movie);


            }while (cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return movies;
    }

    public void addMovieDetails(ArrayList<Movie> movies){
        SQLiteDatabase database = open();




        for (Movie movie: movies) {

            ArrayList<MovieGenre> genre = new ArrayList<>();
            ArrayList<MovieHall> movieHall = new ArrayList<>();
            ArrayList<MovieDate> movieDate = new ArrayList<>();
            ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
            ArrayList<MovieAltID> movieAltID = new ArrayList<>();
            ArrayList<MovieURL> movieURLs = new ArrayList<>();


            Cursor cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_SHOWTIMES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieShowTime showTime = new MovieShowTime(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_SHOWTIME),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_SHOWTIME),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_SHOWTIME));

                    movieShowTime.add(showTime);
                }while (cursor.moveToNext());


            }




            movie.setmMovieShowTime(movieShowTime);


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_HALLS_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieHall hall = new MovieHall(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_HALL),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_HALL),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_HALL));

                    movieHall.add(hall);
                }while (cursor.moveToNext());


            }

            movie.setmMovieHall(movieHall);
            //


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.MOVIE_DATES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieDate date = new MovieDate(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_DATE),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_DATE),
                            getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_DATE));

                    movieDate.add(date);
                }while (cursor.moveToNext());


            }

            movie.setmMovieDate(movieDate);
            //


            //
            cursor = database.rawQuery(
                    "SELECT * FROM " + MovieSQLiteHelper.GENRES_TABLE + " WHERE MOVIE_ID = " + movie.getmId(), null);
            if (cursor.moveToFirst()){
                do {
                    MovieGenre genres = new MovieGenre(
                            getIntFromColumnName(cursor, BaseColumns._ID),
                            getIntFromColumnName(cursor, (MovieSQLiteHelper.COLUMN_MOVIE_GENRE)));

                    genre.add(genres);
                }while (cursor.moveToNext());


            }

            movie.setmMovieGenre(genre);

            cursor.close();


        }
        close(database);
    }


    private ArrayList<Movie> readNowShowingMovie(){
        SQLiteDatabase database = open();


        String whereClause = MovieSQLiteHelper.COLUMN_MOVIE_IS_SHOWING + " = 1";

        Cursor cursor = database.query(
                MovieSQLiteHelper.MOVIES_TABLE,
                new String[]{ MovieSQLiteHelper.COLUMN_MOVIE_NAME, BaseColumns._ID, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK},
                whereClause,   //selection
                null,   //selectiion args
                null,   //group by
                null,   //having
                null);   //order

        ArrayList<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {

                Movie movie = new Movie(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor,MovieSQLiteHelper.COLUMN_MOVIE_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK));
                movies.add(movie);


            }while (cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return movies;
    }


    private ArrayList<Movie> readComingSoonMovie(){
        SQLiteDatabase database = open();


        String whereClause = MovieSQLiteHelper.COLUMN_MOVIE_IS_COMMING + " = 1";

        Cursor cursor = database.query(
                MovieSQLiteHelper.MOVIES_TABLE,
                new String[]{ MovieSQLiteHelper.COLUMN_MOVIE_NAME, BaseColumns._ID, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK},
                whereClause,   //selection
                null,   //selectiion args
                null,   //group by
                null,   //having
                null);   //order

        ArrayList<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {

                Movie movie = new Movie(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor,MovieSQLiteHelper.COLUMN_MOVIE_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK));
                movies.add(movie);


            }while (cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return movies;
    }


    public ArrayList<Movie> readNameMovie(){
        SQLiteDatabase database = open();


        Cursor cursor = database.query(
                MovieSQLiteHelper.MOVIES_TABLE,
                new String[]{ MovieSQLiteHelper.COLUMN_MOVIE_NAME, BaseColumns._ID, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK},
                null,   //selection
                null,   //selectiion args
                null,   //group by
                null,   //having
                null);   //order

        ArrayList<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {

                Movie movie = new Movie(
                        getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor,MovieSQLiteHelper.COLUMN_MOVIE_NAME),
                        getStringFromColumnName(cursor, MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK));
                movies.add(movie);


            }while (cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return movies;
    }



    private int getIntFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);

        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);

        return cursor.getString(columnIndex);
    }

    private SQLiteDatabase open(){
       return mMovieSQLiteHelper.getWritableDatabase();
    }


    private void close(SQLiteDatabase database){
        database.close();
    }

}
