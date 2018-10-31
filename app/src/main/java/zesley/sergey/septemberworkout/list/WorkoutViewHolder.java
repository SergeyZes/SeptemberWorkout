package zesley.sergey.septemberworkout.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.fragments.WorkoutDetailFragment;
import zesley.sergey.septemberworkout.interfaces.OnListItemClickListener;
import zesley.sergey.septemberworkout.trans.RoundedCornersTransform;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private CardView cardView;
    private ImageView imageViewDeleteButton;
    private ImageView imageView;
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
        imageView=itemView.findViewById(R.id.list_item_image_view);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").transform(new RoundedCornersTransform()).fit().into(imageView);

    }

    public void bindView(Workout workout, final int index, final OnListItemClickListener listener) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        recordDate.setText(workout.getFormattedDate());
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClickListener(index);
//                Context context = itemView.getContext();
//                Intent intent = new Intent(context, WorkoutDetailFragment.class);
//                intent.putExtra("workout_index", index);
//                context.startActivity(intent);


            }
        });
        imageViewDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutList.getInstance().getWorkouts().remove(index);
                adapter.notifyDataSetChanged();
                listener.checkDeletedIndex(index);

            }
        });

    }
}