package fettle.iiitd.com.fettle.Classes;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mg on 15/4/16.
 */
public class Exercise {

    ParseObject po;
    String exercise;
    int duration;
    Date date;

    public Exercise() {
    }

    public Exercise(ParseObject po) {
        this.po = po;
    }

    public static void uploadActivity(String activity, int duration) {
        ParseObject parseObject = new ParseObject("Activity");
        parseObject.put("exercise", activity);
        parseObject.put("duration", duration);
        parseObject.put("user", ParseUser.getCurrentUser());
        parseObject.put("CreatedAt", Calendar.getInstance().getTime());
        parseObject.saveEventually();
        try {
            parseObject.pin(Utils.EXERCISE_PIN);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        try {
            return po.getDate("CreatedAt");
        } catch (Exception e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    }

    public String getExercise() {
        try {
            return po.getString("exercise");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getDuration() {
        try {
            return po.getInt("duration");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
