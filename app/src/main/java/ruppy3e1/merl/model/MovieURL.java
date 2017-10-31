package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieURL implements Serializable {

    private int mId = -1;
    private String LegendURL;
    private String MajorURL;
    private String PlatinumURL;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getLegendURL() {
        return LegendURL;
    }

    public void setLegendURL(String legendURL) {
        LegendURL = legendURL;
    }

    public String getMajorURL() {
        return MajorURL;
    }

    public void setMajorURL(String majorURL) {
        MajorURL = majorURL;
    }

    public String getPlatinumURL() {
        return PlatinumURL;
    }

    public void setPlatinumURL(String platinumURL) {
        PlatinumURL = platinumURL;
    }

    public MovieURL(){

    }

    public MovieURL(int id, String lURL , String mURL, String pURL){
        mId = id;
        LegendURL = lURL;
        MajorURL = mURL;
        PlatinumURL = pURL;
    }

}
