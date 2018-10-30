package zesley.sergey.septemberworkout.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zesley.sergey.septemberworkout.Model.Workout;
import zesley.sergey.septemberworkout.Model.WorkoutList;
import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.interfaces.OnListItemClickListener;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    List<Workout> workoutList = WorkoutList.getInstance().getWorkouts();
    private OnListItemClickListener itemClickListener;

    public WorkoutAdapter(OnListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new WorkoutViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder workoutViewHolder, int i) {
        workoutViewHolder.bindView(workoutList.get(i),i,itemClickListener);


    }

    @Override
    public int getItemCount() {
        return workoutList != null ? workoutList.size() : 0;
    }
}