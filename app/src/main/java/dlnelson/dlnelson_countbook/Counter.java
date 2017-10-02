package dlnelson.dlnelson_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luke on 2017-09-25.
 */

public class Counter {
    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String comment;

    public Counter(String name, int value, String comment) {
        this.name = name;
        this.updateDate();
        this.currentValue = value;
        this.initialValue = value;
        this.comment = comment;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return this.date;
    }

    //Provide a secondary getter for the date that comes as an already formatted string.
    public String getShortDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
    }
    private void updateDate() {
        this.date = new Date();
    }

    public int getCurrentValue() {
        return this.currentValue;
    }
    public void setCurrentValue(int value) {
        this.currentValue = value;
        this.updateDate();
    }

    public int getInitialValue() {
        return this.initialValue;
    }
    public void setInitialValue(int value) {
        this.initialValue = value;
    }

    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void incrementValue() {
        this.currentValue += 1;
        this.updateDate();
    }

    public void decrementValue() {
        //Counter must always be a non-negative integer.
        if (this.currentValue > 0) {
            this.currentValue -= 1;
            this.updateDate();
        }
    }

    public void resetValue() {
        this.currentValue = this.initialValue;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return this.name + "  " + dateFormat.format(this.date) + " : " + this.currentValue;
    }


}
