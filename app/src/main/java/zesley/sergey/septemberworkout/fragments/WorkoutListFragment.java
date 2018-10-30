package zesley.sergey.septemberworkout.fragments;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import zesley.sergey.septemberworkout.R;
import zesley.sergey.septemberworkout.interfaces.AdapterRefreshable;
import zesley.sergey.septemberworkout.interfaces.OnListItemClickListener;
import zesley.sergey.septemberworkout.list.WorkoutAdapter;

public class WorkoutListFragment extends Fragment implements AdapterRefreshable {
    public static final String TAG = "WorkoutListFragment";
    private WorkoutAdapter adapter;
    private OnListItemClickListener listener;

    @Override
    public void onAttach(Context context) {
        if (context instanceof OnListItemClickListener) {
            listener = (OnListItemClickListener) context;
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_workout_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        adapter = new WorkoutAdapter(listener);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        Button new_rec_button = root.findViewById(R.id.workout_list_new_record);
        new_rec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClickListener(-1);

            }
        });
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }
}
