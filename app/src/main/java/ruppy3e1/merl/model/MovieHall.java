package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieHall implements Serializable {

    private int mId = -1;
    private String LegendHall;
    private String MajorHall;
    private String PlatinumHall;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getLegendHall() {
        return LegendHall;
    }

    public void setLegendHall(String legendHall) {
        LegendHall = legendHall;
    }

    public String getMajorHall() {
        return MajorHall;
    }

    public void setMajorHall(String majorHall) {
        MajorHall = majorHall;
    }

    public String getPlatinumHall() {
        return PlatinumHall;
    }

    public void setPlatinumHall(String platinumHall) {
        PlatinumHall = platinumHall;
    }

    public MovieHall(){

    }

    public MovieHall(int id, String lHall , String mHall, String pHall){
        mId = id;
        LegendHall = lHall;
        MajorHall = mHall;
        PlatinumHall = pHall;
    }

}
