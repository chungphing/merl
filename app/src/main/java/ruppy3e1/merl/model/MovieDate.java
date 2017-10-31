package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieDate implements Serializable {


    private int mId = -1;
    private String LegendDate;
    private String MajorDate;

    public String getPlatinumDate() {
        return PlatinumDate;
    }

    public void setPlatinumDate(String platinumDate) {
        PlatinumDate = platinumDate;
    }

    public String getMajorDate() {
        return MajorDate;
    }

    public void setMajorDate(String majorDate) {
        MajorDate = majorDate;
    }

    public String getLegendDate() {
        return LegendDate;
    }

    public void setLegendDate(String legendDate) {
        LegendDate = legendDate;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    private String PlatinumDate;

    public MovieDate(){

    }

    public MovieDate(int id, String lDate , String mDate, String pDate){
        mId = id;
        LegendDate = lDate;
        MajorDate = mDate;
        PlatinumDate = pDate;
    }

}
