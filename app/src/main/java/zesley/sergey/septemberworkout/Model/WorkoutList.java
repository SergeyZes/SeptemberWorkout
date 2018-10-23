package zesley.sergey.septemberworkout.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class WorkoutList {
    private static final WorkoutList ourInstance = new WorkoutList();

    public static WorkoutList getInstance() {
        return ourInstance;
    }

    private List<Workout> workouts;

    private WorkoutList() {
        workouts=new ArrayList<>();
        Random random= new Random();
        for (int i=0; i<100; i++){
            Workout workout= new Workout("Упражнение №"+(i+1));
            workout.setDescription("Описание упражнения №"+(i+1));
            workout.setRecordDate(new Date());
            workout.setRecordRepsCount(random.nextInt(101));
            workout.setRecordWeight(random.nextInt(101));
            workouts.add(workout);
        }
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
