package ruppy3e1.merl.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ruppy3e1.merl.fragments.ComingSoonFragment;
import ruppy3e1.merl.fragments.MainFragment;
import ruppy3e1.merl.fragments.NowShowingFragment;
import ruppy3e1.merl.fragments.ScheduleFragment;
import ruppy3e1.merl.R;

public class launcher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnMovieSelectedInterface, NowShowingFragment.OnMovieSelectedInterface, ComingSoonFragment.OnMovieSelectedInterface, ScheduleFragment.OnMovieSelectedInterface {

    private static final String MAIN_FRAGMENT_TAG = "mainfagment_tag";
    public static final String SCHEDULE_TAG = "schedule_tag";
    public static final String NOW_SHOWING_TAG = "now_showing_tag";
    public static final String COMING_SOON_TAG = "coming_soon";

    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private static final String TAG = launcher.class.getSimpleName();
    private static final String DETAIL_TAG = "detail_tag";
    private static final String MOVIE_INDEX = "index_movie";
    private int dbVersion;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        checkDatabaseVersion();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.placeholder, mainFragment, MAIN_FRAGMENT_TAG);
            ft.commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all) {
                MainFragment savedFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT_TAG);
                if (savedFragment == null){

                    MainFragment fragment = new MainFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.placeholder, fragment, MAIN_FRAGMENT_TAG);
                    ft.commit();


                }
        } else if (id == R.id.now_showing) {
            NowShowingFragment savedFragment = (NowShowingFragment) getSupportFragmentManager().findFragmentByTag(NOW_SHOWING_TAG);
            if (savedFragment == null){

                NowShowingFragment fragment = new NowShowingFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.placeholder, fragment, NOW_SHOWING_TAG);
                ft.commit();


            }
        } else if (id == R.id.coming_soon) {
            ComingSoonFragment savedFragment = (ComingSoonFragment) getSupportFragmentManager().findFragmentByTag(COMING_SOON_TAG);
            if (savedFragment == null){

                ComingSoonFragment fragment = new ComingSoonFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.placeholder, fragment, COMING_SOON_TAG);
                ft.commit();


            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


      private void checkDatabaseVersion() {
        String timeStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        SharedPreferences pref = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        int time = Integer.parseInt(timeStamp);
      int savedTime = pref.getInt(PREF_DATABASE_VERSION, 0);

          Log.i(TAG, "checkDatabaseVersion: current time " + time + " and save time " + savedTime);
        if (time != savedTime){
            this.deleteDatabase("movies.db");
            Intent intent = new Intent(this, downloadActivity.class);
            //Bundle bundle = new Bundle();
            Log.i(TAG, "checkDatabaseVersion: finished. try to open fragment");
            startActivity(intent);
            finish();
        }else {
            Log.i(TAG, "checkDatabaseVersion: database already exist lauching mainfragment");
        }
    }

    @Override
    public void onListMovieSelected(int index) {

        Intent intent = new Intent(launcher.this, detailActivity.class);
        intent.putExtra(MOVIE_INDEX, index);
        startActivity(intent);
    }
}
