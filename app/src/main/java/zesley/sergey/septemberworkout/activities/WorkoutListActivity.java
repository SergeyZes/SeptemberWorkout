package zesley.sergey.septemberworkout.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.list.WorkoutAdapter;

public class WorkoutListActivity extends AppCompatActivity {
    public static final String TAG = "WorkoutListActivity";
    private WorkoutAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        Button new_rec_button = findViewById(R.id.workout_list_new_record);
        new_rec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                intent.putExtra("new_workout", 1);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

}
