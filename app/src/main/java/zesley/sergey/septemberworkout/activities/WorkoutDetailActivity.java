package zesley.sergey.septemberworkout.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.R;

public class WorkoutDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Result";
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        workout = new Workout("Подтягивание", "Подтягивание на перикладине", 0, new Date(), 0);
        initGUI(workout);
        addListeners();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_MESSAGE, String.format(getString(R.string.record_message),
                workout.getRecordRepsCount(), workout.getRecordWeight()));
        setResult(RESULT_OK, intent);


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
        });
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
}
