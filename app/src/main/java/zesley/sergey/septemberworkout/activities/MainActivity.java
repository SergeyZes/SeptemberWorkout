package zesley.sergey.septemberworkout.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new WorkoutListFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, listFragment);
        transaction.commit();
    }

    @Override
    public void onListItemClickListener(int index) {
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.initFragment(index);
        fragmentManager.beginTransaction().add(R.id.container, detailFragment).addToBackStack("").commit();

    }

    @Override
    public void refreshAdapter() {

        listFragment.refreshAdapter();

    }
}
