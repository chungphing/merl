package ruppy3e1.merl.model;

import java.io.Serializable;

/**
 * Created by chunpghing
 */

public class MovieGenre implements Serializable{

    private int mId = -1;
    private int mGenreCode;

    public static final int ACTION = 1;
    public static final int ADVENTURE = 2;
    public static final int ANIMATION = 3;
    public static final int BIOGRAPHY = 4;
    public static final int COMEDY = 5;
    public static final int CRIME = 6;
    public static final int  DOCUMENTARY = 7;
    public static final int DRAMA = 8;
    public static final int FAMILY = 9;
    public static final int FANTASY = 10;
    public static final int FILM_NOIR = 11;
    public static final int HISTORY = 12;
    public static final int HORROR = 13;
    public static final int MUSIC = 14;
    public static final int MUSICAL = 15;
    public static final int MYSTERY = 16;
    public static final int ROMANCE = 17;
    public static final int SCI_FI = 18;
    public static final int SPORT = 19;
    public static final int THRILLER = 20;
    public static final int WAR = 21;
    public static final int WESTERN = 22;
    public static final int ANIME = 23;
    public static final int OTHER = 24;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmGenreCode() {
        return mGenreCode;
    }

    public void setmGenreCode(int mGenreCode) {
        this.mGenreCode = mGenreCode;
    }

    public MovieGenre(int id, int code){

        mId = id;
        mGenreCode = code;


    }

    public MovieGenre(){

    }



    public static int toCode(String label){
        switch (label.toUpperCase()){
            case "ACTION"  :    return ACTION;
            case "ADVENTURE":   return ADVENTURE;
            case "ANIMATION" :  return ANIMATION;
            case "BIOGRAPHY"  : return BIOGRAPHY;
            case "COMEDY"  :    return COMEDY;
            case "CRIME"  :     return CRIME;
            case "DOCUMENTARY": return DOCUMENTARY;
            case "DRAMA"  :     return DRAMA;
            case "FAMILY":      return FAMILY;
            case "FANTASY":     return FANTASY;
            case "FILM_NOIR":   return FILM_NOIR;
            case "HISTORY"  :   return HISTORY;
            case "HORROR"  :    return HORROR;
            case "MUSIC"  :     return MUSIC;
            case "MUSICAL" :    return MUSICAL;
            case "MYSTERY"  :   return MYSTERY;
            case "ROMANCE"  :   return ROMANCE;
            case "SCI_FI"  :    return SCI_FI;
            case "SPORT"  :     return SPORT;
            case "THRILLER":    return THRILLER;
            case "WAR"  :       return WAR;
            case "WESTERN":     return WESTERN;
            case "ANIME"  :     return ANIME;
            case "OTHER"  :     return OTHER;
            default: return 0;
        }



    }

    public static String toString(int code){
        switch (code){
            case ACTION: return "ACTION"  ;
            case ADVENTURE:   return "ADVENTURE";
            case ANIMATION:   return "ANIMATION" ;
            case BIOGRAPHY:   return "BIOGRAPHY"  ;
            case COMEDY:   return "COMEDY"  ;
            case CRIME:   return "CRIME"  ;
            case DOCUMENTARY:   return "DOCUMENTARY";
            case DRAMA:   return "DRAMA"  ;
            case FAMILY:   return "FAMILY";
            case FANTASY:   return "FANTASY";
            case FILM_NOIR:   return "FILM_NOIR";
            case HISTORY:   return "HISTORY"  ;
            case HORROR:   return "HORROR"  ;
            case MUSIC:   return "MUSIC"  ;
            case MUSICAL:   return "MUSICAL" ;
            case MYSTERY:   return "MYSTERY"  ;
            case ROMANCE:   return "ROMANCE"  ;
            case SCI_FI:   return "SCI_FI"  ;
            case SPORT:   return "SPORT"  ;
            case THRILLER:   return "THRILLER";
            case WAR:   return "WAR"  ;
            case WESTERN:   return "WESTERN";
            case ANIME:   return "ANIME"  ;
            case OTHER:   return "OTHER"  ;
            default: return "N\\A";
        }



    }

}
