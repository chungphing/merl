package ruppy3e1.merl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ruppy3e1.merl.model.MovieShowTime;

/**
 * Created by chunpghing
 */


public class detailAdapter extends  RecyclerView.Adapter<detailAdapter.MovieViewHolder> {
    private ArrayList<MovieShowTime> mShowTimes;
    public detailAdapter(Context context, ArrayList<MovieShowTime> showTimes){
        mShowTimes = showTimes;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mShowTimes.get(position));
    }

    @Override
    public int getItemCount() {
        return mShowTimes.size();
    }

    class  MovieViewHolder extends  RecyclerView.ViewHolder{

        TextView theater;
        TextView date;
        TextView time;
        MovieViewHolder(View itemView) {
            super(itemView);

            theater = (TextView) itemView.findViewById(R.id.theater);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);

        }
        void bindMovie(MovieShowTime showTime){
            String[] showtime = showTime.getLegendShowTime().split("~");
            theater.setText(showtime[0]);
            date.setText(showtime[1]);
            time.setText(showtime[2]);
        }

    }

}