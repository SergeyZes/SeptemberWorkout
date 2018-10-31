package zesley.sergey.septemberworkout.activities;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.fragments.WorkoutDetailFragment;
import zesley.sergey.septemberworkout.fragments.WorkoutListFragment;
import zesley.sergey.septemberworkout.interfaces.AdapterRefreshable;
import zesley.sergey.septemberworkout.interfaces.OnListItemClickListener;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    FragmentManager fragmentManager;
    private WorkoutListFragment listFragment;
    private WorkoutDetailFragment detailFragment;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = new WorkoutListFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentIndex = 0;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(R.id.container, listFragment);
        } else {
            detailFragment = WorkoutDetailFragment.initFragment(0);
            transaction.replace(R.id.list_container, listFragment);
            transaction.add(R.id.detail_container, detailFragment);
        }
        transaction.commit();
    }

    @Override
    public void onListItemClickListener(int index) {
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.initFragment(index);
        currentIndex = index;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentManager.beginTransaction().add(R.id.container, detailFragment).addToBackStack("").commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }

    }

    @Override
    public void refreshAdapter() {

        listFragment.refreshAdapter();

    }

    @Override
    public void refreshOneInList() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            listFragment.refreshAdapter();
        }
    }

    @Override
    public void checkDeletedIndex(int index) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (index == currentIndex) onListItemClickListener(0);
        }

    }

//    private void initVerticalGui(){
//
//    }
//    private void initHorizontalGui(){
//
//    }
}
