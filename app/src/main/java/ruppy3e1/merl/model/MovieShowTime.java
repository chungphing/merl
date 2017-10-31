package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieShowTime implements Serializable {

    private int mId = -1;
    private String LegendShowTime;
    private String MajorShowTime;
    private String PlatinumShowTime;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getLegendShowTime() {
        return LegendShowTime;
    }

    public void setLegendShowTime(String legendShowTime) {
        LegendShowTime = legendShowTime;
    }

    public String getMajorShowTime() {
        return MajorShowTime;
    }

    public void setMajorShowTime(String majorShowTime) {
        MajorShowTime = majorShowTime;
    }

    public String getPlatinumShowTime() {
        return PlatinumShowTime;
    }

    public void setPlatinumShowTime(String platinumShowTime) {
        PlatinumShowTime = platinumShowTime;
    }

    public MovieShowTime(){

    }

    public MovieShowTime(int id, String lTime , String mTime, String pTime){
        mId = id;
        LegendShowTime = lTime;
        MajorShowTime = mTime;
        PlatinumShowTime = pTime;
    }

}
