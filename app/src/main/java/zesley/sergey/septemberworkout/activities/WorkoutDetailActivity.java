package zesley.sergey.septemberworkout.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;

public class WorkoutDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Result";
    public static final String TAG = "WorkoutDetailActivity";

    private TextView title;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private TextView weight;
    private TextView description;
    private SeekBar weightSeekBar;
    private ImageView image;
    private EditText repsCountEditText;
    private Button saveRecordButton;
    private Button shareRecordButton;
    private Workout workout;
    private int workout_index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");

        workout_index = getIntent().getIntExtra("workout_index", 0);
        workout = WorkoutList.getInstance().getWorkouts().get(workout_index);
        setContentView(R.layout.activity_workout_detail);

        initGUI(workout);
        addListeners();
    }

    private void addListeners() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weight.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rep;
                int wei;

                try {


                    rep = Integer.parseInt(repsCountEditText.getText().toString());
                    wei = weightSeekBar.getProgress();
                } catch (Exception e) {
                    return;
                }

                if ((rep > workout.getRecordRepsCount()) || (wei > workout.getRecordWeight())) {
                    workout.setRecordDate(new Date());
                    workout.setRecordRepsCount(rep);
                    workout.setRecordWeight(wei);
                    initGUI(workout);

                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_MESSAGE, String.format(getString(R.string.record_message),
                            workout.getRecordRepsCount(), workout.getRecordWeight()));
                    setResult(RESULT_OK, intent);

                }
            }
        });

        shareRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePullupRecord();
            }
        });
    }

    private void sharePullupRecord() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.record_subject_string));
        intent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.record_message),
                workout.getRecordRepsCount(), workout.getRecordWeight()));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), getString(R.string.send_exception_message), Toast.LENGTH_SHORT);
        }
    }

    private void initGUI(Workout workout) {

        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedDate());
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        description = findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());

        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);
        shareRecordButton = findViewById(R.id.workout_detail_share);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                sharePullupRecord();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_quit:
// Передаем право убить приложение стартовой активити
                Intent intent = new Intent();
                intent.putExtra(EXTRA_MESSAGE, "Good-bye");
                finish();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putSerializable("workout", workout);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
