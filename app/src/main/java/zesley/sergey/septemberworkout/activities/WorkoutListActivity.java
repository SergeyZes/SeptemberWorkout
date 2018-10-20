package zesley.sergey.septemberworkout.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.list.WorkoutAdapter;
import zesley.sergey.septemberworkout.list.WorkoutViewHolder;

public class WorkoutListActivity extends AppCompatActivity {
    public static final String TAG = "WorkoutListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new WorkoutAdapter());


    }






}
