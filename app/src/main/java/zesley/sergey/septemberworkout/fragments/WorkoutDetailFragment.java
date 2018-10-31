package zesley.sergey.septemberworkout.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Date;
import java.util.List;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.interfaces.OnListItemClickListener;

public class WorkoutDetailFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "Result";
    public static final String TAG = "WorkoutDetailFragment";
    public static final String WORKOUT_INDEX = "workoutIndex";

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
    private int workout_index = 0;

    private OnListItemClickListener listener;

    @Override
    public void onAttach(Context context) {
        if (context instanceof OnListItemClickListener) {
            listener = (OnListItemClickListener) context;
        }
        super.onAttach(context);
    }


    public static WorkoutDetailFragment initFragment(int workoutIndex) {
        WorkoutDetailFragment fragment = new WorkoutDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(WORKOUT_INDEX, workoutIndex);
        fragment.setArguments(arguments);
        return fragment;

    }
//    public Workout(String description, String title, int repsCount, Date date, int weight) {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        workout_index=getArguments().getInt(WORKOUT_INDEX,0);

        List<Workout> workouts = WorkoutList.getInstance().getWorkouts();

        if (workout_index==-1)
        {
            //добавили новую запись
            workout_index = workouts.size();
            workout = new Workout("Описание упражнение №" + (workout_index + 1), "Упражнение №" + (workout_index + 1), 0, new Date(), 0);
            workouts.add(workout);

        } else {
            workout = workouts.get(workout_index);
        }
        initGUI(root, workout);
        addListeners();
        return root;
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
                    listener.refreshOneInList();
                    initGUI(getView(), workout);
                }
            }
        });

        shareRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(shareRecordButton);

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
            Toast.makeText(getView().getContext(), getString(R.string.send_exception_message), Toast.LENGTH_SHORT);
        }
    }

    private void initGUI(View view, Workout workout) {

        title = view.findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = view.findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedDate());
        recordRepsCount = view.findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = view.findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        description = view.findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());

        weight = view.findViewById(R.id.workout_detail_weight);
        weightSeekBar = view.findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = view.findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = view.findViewById(R.id.workout_detail_save_button);
        shareRecordButton = view.findViewById(R.id.workout_detail_share);


    }

    @Override
    public void onStop() {
        super.onStop();
        listener.refreshAdapter();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.workout_detail_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_share:
//                sharePullupRecord();
//                return true;
//            case R.id.action_settings:
//                return true;
//            case R.id.action_quit:
//// Передаем право убить приложение стартовой активити
//                Intent intent = new Intent();
//                intent.putExtra(EXTRA_MESSAGE, "Good-bye");
//                finish();
//
//                return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
