package zesley.sergey.septemberworkout.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.R;

public class WorkoutDetailActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        Workout workout=new Workout("Подтягивание","Подтягивание на перикладине",0,new Date(),0);
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
    }

    private void initGUI(Workout workout) {

        title=findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate=findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedDate());
        recordRepsCount=findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight=findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        description=findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());

        weight=findViewById(R.id.workout_detail_weight);
        weightSeekBar=findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText=findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton=findViewById(R.id.workout_detail_save_button);


    }
}
