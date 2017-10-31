package ruppy3e1.merl.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by chungphing
 */


/********************************
 *
 *  NOTE: LEGEND WEBSITE REMOVE POST AFTER THE MOVIES IS PLAYING SO AT NIGHT METHOD WILL RESULT IN 0 MOVIES... DON'T PANIC
 *
 *******************************/





/*
* HOW TO USE THIS METHOD
* FIRST NEED TO CREATE A VARIABLE DOCUMENT AS FIELD
* ex. Document doc;
* THEN CONNECT THE DOCUMENT TO URL
* ex. doc = Jsoup.connect("legend.com.kh/ksajlkfasjfkdj").get();
* Depending on platform may need to use Async and Surround with try catch
* In any classes just create a new variable matching datatype with method datatype
* Pass Document as parameter
*/

public class ParseMajor {




    private static final String TAG = ParseMajor.class.getSimpleName();





    public static ArrayList<String> getMovies(Document doc){
            ArrayList<String> Movies = new ArrayList<>();


            Elements spanName = doc.select("div.st_container");
            Elements spanName2 = spanName.select("div.sp_sessions_title");
            Elements text = spanName2.select("a");


            for (Element t : text){


                Movies.add(t.text());

            }

        return Movies;
    }


    public static ArrayList<String> getImgLinks(Document doc){
         ArrayList<String> ImgLinks = new ArrayList<>();


            Elements spanName = doc.select("div.st_container");
            Elements spanName2 = spanName.select("div.sp_sessions_title");
            Elements text = spanName2.select("a");

            Elements imgName2 = spanName.select("div.sp_sessions_img");
            Elements imgSpan = imgName2.select("a");

            Elements img = imgSpan.select("img");

        
            for (Element i : img){

                ImgLinks.add(i.attr("src"));

            }

        return ImgLinks;
    }

    public static String getTitle(Document doc){
            return doc.title();
    }



    public static void getTimeTable(Document doc){


        Elements post = doc.select("div.st_container");
        Elements timetables = post.select("div.sp_sessions_time");


        //TODO: get time tables



    }

    public static void getParentalRating(Document doc){

        Elements post = doc.select("div.st_container");
        Elements rating = post.select("div.bx-rtg > ul > li.rte-m > img");
        String stringRating = rating.attr("alt");
        //TODO: may need to get by ID
    }

    public static String getLegendID(Document doc){


        Elements post = doc.select("div.st_container");
        Elements links = post.select("sp_sessions_title > a");
        String stringRating = links.attr("href");


        String LegendID = stringRating.substring(stringRating.lastIndexOf('/') + 1);
        return LegendID;
    }


    public static void getRuntime(Document doc){


        Elements post = doc.select("div.st_container");
        Elements runtime = post.select("div.bx-rtg > ul > li.vle-t > span");
        String stringRuntime = runtime.text();
    }










}
