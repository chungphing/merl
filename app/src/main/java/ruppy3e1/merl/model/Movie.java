package ruppy3e1.merl.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chunpghing
 */

public class Movie implements Serializable {
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    private int mId;

    private String mName;
    private String mAltName;

    private String mRating;
    private String mRunTime;
    private String mDescription;
    private Boolean mIsAired;
    private Boolean mIshowing;
    private Boolean mIsComming;


    private String mTrailerLink;
    private String mImgLink;


    public Boolean getmIsAired() {
        return mIsAired;
    }

    public void setmIsAired(Boolean mIsAired) {
        this.mIsAired = mIsAired;
    }

    public Boolean getmIshowing() {
        return mIshowing;
    }

    public void setmIshowing(Boolean mIshowing) {
        this.mIshowing = mIshowing;
    }

    public Boolean getmIsComming() {
        return mIsComming;
    }

    public void setmIsComming(Boolean mIsComming) {
        this.mIsComming = mIsComming;
    }

    private ArrayList<MovieAltID> mMovieAltID;

    private ArrayList<MovieGenre> mMovieGenre;

    private ArrayList<MovieDate> mMovieDate;

    private ArrayList<MovieShowTime> mMovieShowTime;

    private ArrayList<MovieHall> mMovieHall;

    private ArrayList<MovieURL> mMovieURL;


    public ArrayList<MovieURL> getmMovieURL() {
        return mMovieURL;
    }

    public void setmMovieURL(ArrayList<MovieURL> mMovieURL) {
        this.mMovieURL = mMovieURL;
    }

    public ArrayList<MovieAltID> getmMovieAltID() {
        return mMovieAltID;
    }

    public void setmMovieAltID(ArrayList<MovieAltID> mMovieAltID) {
        this.mMovieAltID = mMovieAltID;
    }

    public ArrayList<MovieGenre> getmMovieGenre() {
        return mMovieGenre;
    }

    public void setmMovieGenre(ArrayList<MovieGenre> mMovieGenre) {
        this.mMovieGenre = mMovieGenre;
    }

    public ArrayList<MovieDate> getmMovieDate() {
        return mMovieDate;
    }

    public void setmMovieDate(ArrayList<MovieDate> mMovieDate) {
        this.mMovieDate = mMovieDate;
    }

    public ArrayList<MovieShowTime> getmMovieShowTime() {
        return mMovieShowTime;
    }

    public void setmMovieShowTime(ArrayList<MovieShowTime> mMovieShowTime) {
        this.mMovieShowTime = mMovieShowTime;
    }

    public ArrayList<MovieHall> getmMovieHall() {
        return mMovieHall;
    }

    public void setmMovieHall(ArrayList<MovieHall> mMovieHall) {
        this.mMovieHall = mMovieHall;
    }

    public Movie(int id, String name, String altName, String description, Boolean isAired, Boolean isShowing , Boolean isComming, String rate, String time, String trailerLink , String imageLink, ArrayList<MovieAltID> movieAltID, ArrayList<MovieHall> movieHall, ArrayList<MovieGenre> movieGenre, ArrayList<MovieDate> movieDate, ArrayList<MovieShowTime> movieShowTime , ArrayList<MovieURL> movieURLs){
        mId = id;

        mName = name;
        mAltName = altName;
        mDescription = description;
        mIsAired = isAired;
        mIshowing = isShowing;
        mIsComming = isComming;
        mTrailerLink = trailerLink;
        mImgLink = imageLink;
        mRunTime = time;
        mRating = rate;

        mMovieAltID = movieAltID;
        mMovieDate = movieDate;
        mMovieGenre = movieGenre;
        mMovieHall = movieHall;
        mMovieShowTime = movieShowTime;
        mMovieURL = movieURLs;
    }

    public Movie(int id, String name, String img){
        mId = id;

        mName = name;
        mAltName = "";
        mDescription = "";
        mIsAired = false;
        mIshowing = false;
        mIsComming = false;
        mTrailerLink = "";
        mImgLink = img;
        mRunTime = "";
        mRating = "";

        mMovieAltID = null;
        mMovieDate = null;
        mMovieGenre = null;
        mMovieHall = null;
        mMovieShowTime = null;
        mMovieURL = null;
    }





    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAltName() {
        return mAltName;
    }

    public void setmAltName(String mAltName) {
        this.mAltName = mAltName;
    }



    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(String mRunTime) {
        this.mRunTime = mRunTime;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    public Boolean getAired() {
        return mIsAired;
    }

    public void setAired(Boolean aired) {
        mIsAired = aired;
    }

    public Boolean getShowing() {
        return mIshowing;
    }

    public void setShowing(Boolean showing) {
        mIshowing = showing;
    }

    public Boolean getComming() {
        return mIsComming;
    }

    public void setComming(Boolean comming) {
        mIsComming = comming;
    }

    public String getmTrailerLink() {
        return mTrailerLink;
    }

    public void setmTrailerLink(String mTrailerLink) {
        this.mTrailerLink = mTrailerLink;
    }

    public String getmImgLink() {
        return mImgLink;
    }

    public void setmImgLink(String mImgLink) {
        this.mImgLink = mImgLink;
    }

}
