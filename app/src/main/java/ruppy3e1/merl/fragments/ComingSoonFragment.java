package ruppy3e1.merl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ruppy3e1.merl.datasource.MovieDataSource;
import ruppy3e1.merl.model.Movie;
import ruppy3e1.merl.moviesAdapter;
import ruppy3e1.merl.R;

/**
 * Created by chunpghing
 */

public class ComingSoonFragment extends Fragment {
    public interface OnMovieSelectedInterface {
        void onListMovieSelected(int index);
    }

    public static final String TAG = "Main Fragment";

    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private int dbVersion;
    private boolean status;
        private ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainFragment.OnMovieSelectedInterface listener = (MainFragment.OnMovieSelectedInterface)getActivity();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        moviesAdapter adapter = new moviesAdapter(getActivity(), movies, listener);
        RecyclerView rvMovies = view.findViewById(R.id.rvMovies);
        rvMovies.setAdapter(adapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rvMovies.setLayoutManager(layoutManager);
        getActivity().setTitle(getString(R.string.commingsoon));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initData() {

        MovieDataSource dataSource = new MovieDataSource(this.getActivity());
        movies = new ArrayList<>();
        movies = dataSource.readComingSoon();

    }

    public static ComingSoonFragment newInstance(){
        return new ComingSoonFragment();
    }
}
