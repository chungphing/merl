package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieAltID implements Serializable {

    private int mId = -1;
    private String LegendID;
    private String MajorID;
    private String PlatinumID;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getLegendID() {
        return LegendID;
    }

    public void setLegendID(String legendID) {
        LegendID = legendID;
    }

    public String getMajorID() {
        return MajorID;
    }

    public void setMajorID(String majorID) {
        MajorID = majorID;
    }

    public String getPlatinumID() {
        return PlatinumID;
    }

    public void setPlatinumID(String platinumID) {
        PlatinumID = platinumID;
    }

    public MovieAltID(){

    }

    public MovieAltID(int id, String lId , String mayId, String pId){
        mId = id;
        LegendID = lId;
        MajorID = mayId;
        PlatinumID = pId;
    }



    public int getId() { return mId; }
    public boolean hasBeenSaved() { return (getId() != -1); }



}
