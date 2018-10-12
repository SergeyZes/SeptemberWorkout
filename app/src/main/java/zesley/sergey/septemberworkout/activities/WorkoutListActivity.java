package zesley.sergey.septemberworkout.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zesley.sergey.septemberworkout.R;

public class WorkoutListActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    Button buttonPullUps;
    Button buttonPushUps;
    Button buttonSquats;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        initGUI();
        addListeners();
    }

    private void addListeners() {
        buttonPullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startWorkoutDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startActivityForResult(startWorkoutDetailActivity, REQUEST_CODE);

            }
        });
    }

    private void initGUI() {
        buttonPullUps = findViewById(R.id.button_pull_up);
        buttonPushUps = findViewById(R.id.button_push_up);
        buttonSquats = findViewById(R.id.button_squats);
        resultTextView=findViewById(R.id.result_message_text_view);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra(WorkoutDetailActivity.EXTRA_MESSAGE)){
              resultTextView.setText(data.getStringExtra(WorkoutDetailActivity.EXTRA_MESSAGE));
            }
        }

    }
}
