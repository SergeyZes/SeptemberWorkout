package zesley.sergey.septemberworkout.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zesley.sergey.septemberworkout.R;

public class WorkoutListActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    public static final String TAG="WorkoutListActivity";
    public static final int RESULT_KILL_CODE=5;

    Button buttonPullUps;
    Button buttonPushUps;
    Button buttonSquats;
    TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
//        setContentView(R.layout.activity_workout_list);
//
//        initGUI();
//        addListeners();
//        Log.d(TAG,"onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
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
        } else
        if (requestCode == REQUEST_CODE && resultCode == RESULT_KILL_CODE) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

//в зависимости от версии оси намертво убиваем приложение
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                finishAndRemoveTask();
            }
            else
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                {
                    finishAffinity();
                } else
                {
                    finish();
                }
            }

        }

    }
}
