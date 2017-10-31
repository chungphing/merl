package ruppy3e1.merl.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ruppy3e1.merl.datasource.MovieDataSource;
import ruppy3e1.merl.detailAdapter;
import ruppy3e1.merl.model.Movie;
import ruppy3e1.merl.model.MovieGenre;
import ruppy3e1.merl.model.MovieShowTime;
import ruppy3e1.merl.model.MovieURL;
import ruppy3e1.merl.R;

/**
 * Created by chunpghing
 */

public class DetailFragment extends Fragment {




    public static final String TAG = "Main Fragment";

    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private int dbVersion;
    private boolean status;
    private detailAdapter adapter;
    private ArrayList<Movie> movies;
    private static final String MOVIE_INDEX = "index_movie";

    private Movie movie;

    private ImageView poster;
    private TextView name;
    private TextView rating;
    private TextView runningTime;
    private TextView genre;
    private TextView description;
    private TextView noShow;
    private Button webButton;
    private RecyclerView rvDetail;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initDataset();

    }


    @Override
    public void onResume() {
        super.onResume();



    }

    private void initDataset() {

        Bundle bundle = getArguments();
        int index = bundle.getInt(MOVIE_INDEX);

        MovieDataSource dataSource = new MovieDataSource(this.getActivity());
          movie = dataSource.readDetailMovie(index);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        poster = (ImageView) view.findViewById(R.id.image_poster);
        name = (TextView) view.findViewById(R.id.movie_name);
        rating = (TextView) view.findViewById(R.id.rating);
        runningTime = (TextView) view.findViewById(R.id.runtime);
        genre = (TextView) view.findViewById(R.id.genres);
        description = (TextView) view.findViewById(R.id.description);
        webButton = (Button) view.findViewById(R.id.web_button);
        rvDetail = (RecyclerView) view.findViewById(R.id.rvDetail);
        noShow = (TextView) view.findViewById(R.id.no_show);

        ArrayList<MovieGenre> genres = movie.getmMovieGenre();
        ArrayList<MovieURL> movieURLs = movie.getmMovieURL();
        ArrayList<MovieShowTime> showTimes = movie.getmMovieShowTime();

        String des = movie.getmDescription();
        if (des.equals("")){
            des = "No Description Available";
        }
        String rateingstr = movie.getmRating();
        if (rateingstr.equals("TBC")){
            rateingstr = "N\\A";
        }
        String time = movie.getmRunTime();
        if (time.equals("")){
            time = "N\\A";
        }

        Picasso.with(getActivity()).load(movie.getmImgLink()).placeholder(R.drawable.place_holder).error(R.drawable.placeholder_error).into(poster);
        name.setText(movie.getmName());
        rating.setText("Rating: " + rateingstr);
        runningTime.setText("Runtime: " + time);
        description.setText("      " + des);
        String str_genre = "";

        for (int i =0; i<genres.size(); i++){
            str_genre = MovieGenre.toString(genres.get(i).getmGenreCode());
        }
        genre.setText("Genre: " + str_genre);


        adapter = new detailAdapter(getActivity(), showTimes);
        rvDetail.setAdapter(adapter);


        rvDetail.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvDetail.addItemDecoration(itemDecoration);

        rvDetail.setHasFixedSize(true);


        if (movie.getComming()){
            rvDetail.setVisibility(View.GONE);
            noShow.setVisibility(View.VISIBLE);
        }

        String urlString = "";

        for (MovieURL url : movieURLs){
            urlString = url.getLegendURL();
        }

        if (!urlString.equals("")){

            if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                urlString = "http://" + urlString;
            }


        Log.i(TAG, "onCreateView: url " + urlString);

        final String finalUrlString = urlString;
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finalUrlString.equals("")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrlString));
                    startActivity(browserIntent);
                }else {
                    Toast.makeText(getActivity(), "Can't Find Url", Toast.LENGTH_SHORT).show();
                }
            }
        });



        getActivity().setTitle(movie.getmName() + "'s Detail");





        return view;
    }


    public static DetailFragment newInstance(){


        return new DetailFragment();
    }
}
