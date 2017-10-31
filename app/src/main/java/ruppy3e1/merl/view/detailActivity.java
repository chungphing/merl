package ruppy3e1.merl.view;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ruppy3e1.merl.fragments.DetailFragment;
import ruppy3e1.merl.R;

public class detailActivity extends AppCompatActivity {
    private static final String MOVIE_INDEX = "index_movie";
    private static final String PREF_STATUS = "STATUS";
    private static final String TAG = detailActivity.class.getSimpleName();
    private static final String DETAIL_TAG = "detail_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Intent intent = getIntent();
        int index = intent.getIntExtra(MOVIE_INDEX, 0);

        DetailFragment savedFragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL_TAG);
        if (savedFragment == null){
            Bundle bundle = new Bundle();
            bundle.putInt(MOVIE_INDEX, index);
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, fragment, DETAIL_TAG);
            ft.commit();
        }
    }
}
