package ruppy3e1.merl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ruppy3e1.merl.fragments.MainFragment;
import ruppy3e1.merl.model.Movie;

/**
 * Created by chunpghing
 */


public class moviesAdapter extends  RecyclerView.Adapter<moviesAdapter.MovieViewHolder> {
    private final MainFragment.OnMovieSelectedInterface mListener;
    private ArrayList<Movie> mMovies;
    private Context mContext;

    public moviesAdapter(Context context, ArrayList<Movie> movies, MainFragment.OnMovieSelectedInterface listener){
        mMovies = movies;
        mContext = context;
        mListener = listener;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class  MovieViewHolder extends  RecyclerView.ViewHolder
        implements View.OnClickListener{

        TextView mName;
        ImageView mImage;
         int mIndex;
        MovieViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.movie_name);
            mImage = (ImageView) itemView.findViewById(R.id.image_poster);

            itemView.setOnClickListener(this);
        }
        void bindMovie(Movie movie){
            mIndex = movie.getmId();
            mName.setText(movie.getmName());
            Picasso.with(mContext).load(movie.getmImgLink()).placeholder(R.drawable.place_holder).error(R.drawable.placeholder_error).into(mImage);
        }
        @Override
        public void onClick(View v) {
            mListener.onListMovieSelected(mIndex);
        }
    }
}
