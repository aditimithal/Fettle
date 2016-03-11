package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 11/03/16.
 */
public class CalendarActivity extends AppCompatActivity{

    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        initializeCalendar();
    }

    public void initializeCalendar() {

        calendar = (CalendarView) findViewById(R.id.calendarview);



        // sets whether to show the week number.

        calendar.setShowWeekNumber(false);



        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar

        calendar.setFirstDayOfWeek(2);

        //sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    //show the selected date as a toast

            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }

        });

    }
}
