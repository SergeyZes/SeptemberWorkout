package zesley.sergey.septemberworkout.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zesley.sergey.septemberworkout.R;

public class WorkoutListActivity extends AppCompatActivity {
    Button buttonPullUps;
    Button buttonPushUps;
    Button buttonSquats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        initGUI();
    }

    private void initGUI() {
        buttonPullUps=findViewById(R.id.button_pull_up);
        buttonPushUps=findViewById(R.id.button_push_up);
        buttonSquats=findViewById(R.id.button_squats);

        buttonPullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startWorkoutDetailActivity = new Intent(WorkoutListActivity.this,WorkoutDetailActivity.class);
                startActivity(startWorkoutDetailActivity);

            }
        });
    }


}
