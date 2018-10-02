package zesley.sergey.septemberworkout.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Workout {
    private String description;
    private String title;
    private String imageUrl;
    private int recordRepsCount;
    private Date recordDate;
    private int recordWeight;

    public Workout(String description, String title, int repsCount, Date date, int weight) {
        this.description = description;
        this.title = title;
        this.recordRepsCount = repsCount;
        this.recordDate = date;
        this.recordWeight = weight;
    }

    public Workout(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRecordRepsCount() {
        return recordRepsCount;
    }

    public void setRecordRepsCount(int recordRepsCount) {
        this.recordRepsCount = recordRepsCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getRecordWeight() {
        return recordWeight;
    }

    public void setRecordWeight(int recordWeight) {
        this.recordWeight = recordWeight;
    }

    public String getFormattedDate(){
        return new SimpleDateFormat("dd MM yyyy", Locale.ROOT).format(recordDate);
    }
}
