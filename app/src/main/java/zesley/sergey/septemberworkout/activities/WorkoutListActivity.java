package zesley.sergey.septemberworkout.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.list.WorkoutAdapter;

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
