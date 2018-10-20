package zesley.sergey.septemberworkout.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.activities.WorkoutDetailActivity;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView description;
    TextView recordDate;
    TextView recordRepsCount;
    TextView recordWeight;
    private CardView cardView;
    ImageView imageViewDeleteButton;
    WorkoutAdapter adapter;

    public WorkoutViewHolder(@NonNull final View itemView, WorkoutAdapter adapter) {
        super(itemView);

        this.adapter = adapter;
        title = itemView.findViewById(R.id.list_item_title_text_view);
        description = itemView.findViewById(R.id.list_item_description_text_view);
        recordDate = itemView.findViewById(R.id.list_item_record_date);
        recordRepsCount = itemView.findViewById(R.id.list_item_record_reps_count);
        recordWeight = itemView.findViewById(R.id.list_item_record_weight);
        cardView = itemView.findViewById(R.id.cardView);
        imageViewDeleteButton = itemView.findViewById(R.id.list_item_delete_record);


    }

    public void bindView(Workout workout, final int index) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        recordDate.setText(workout.getFormattedDate());
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra("workout_index", index);
                context.startActivity(intent);
            }
        });
        imageViewDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutList.getInstance().getWorkouts().remove(index);
                adapter.notifyDataSetChanged();
            }
        });

    }
}