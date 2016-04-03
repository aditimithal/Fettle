package fettle.iiitd.com.fettle.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;
import com.roomorama.caldroid.CaldroidListener;
import com.roomorama.caldroid.CalendarHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fettle.iiitd.com.fettle.R;
import hirondelle.date4j.DateTime;

import static fettle.iiitd.com.fettle.R.drawable.calendar_today_border;
import static fettle.iiitd.com.fettle.R.drawable.circular_calendar_current;
import static fettle.iiitd.com.fettle.R.drawable.circular_calendar_over;
import static fettle.iiitd.com.fettle.R.drawable.circular_calendar_perfect;
import static fettle.iiitd.com.fettle.R.drawable.circular_calendar_under;

public class CustomCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);

        Map<DateTime, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        map.put(CalendarHelper.convertDateToDateTime(calendar.getTime()), 90);
        calendar.add(Calendar.DATE, -1);
        map.put(CalendarHelper.convertDateToDateTime(calendar.getTime()), 55);
        calendar.add(Calendar.DATE, -1);
        map.put(CalendarHelper.convertDateToDateTime(calendar.getTime()), 65);
        calendar.add(Calendar.DATE, -1);
        map.put(CalendarHelper.convertDateToDateTime(calendar.getTime()), 85);
        calendar.add(Calendar.DATE, -1);
        map.put(CalendarHelper.convertDateToDateTime(calendar.getTime()), 102);
        initCalendar(map);

//        CalendarHelper.convertDateToDateTime(new Date());
    }

    private void initCalendar(Map<DateTime, Integer> percentages) {
        final CaldroidFragment caldroidFragment = new CaldroidSampleCustomFragment(percentages);
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {

                Button leftButton = caldroidFragment.getLeftArrowButton();
                Button rightButton = caldroidFragment.getRightArrowButton();
                TextView textView = caldroidFragment.getMonthTitleTextView();
                leftButton.setBackgroundResource(0);
                leftButton.setText("<");
                rightButton.setBackgroundResource(0);
                rightButton.setText(">");
                super.onCaldroidViewCreated();
            }
        });

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.containerCalendar, caldroidFragment);
        t.commit();
    }

    @SuppressLint("ValidFragment")
    public static class CaldroidSampleCustomFragment extends CaldroidFragment {

        Map<DateTime, Integer> map = new HashMap<>();

        public CaldroidSampleCustomFragment(Map<DateTime, Integer> map) {
            this.map = map;
        }

        @Override
        public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
            // TODO Auto-generated method stub
            return new CaldroidSampleCustomAdapter(getActivity(), month, year, getCaldroidData(), extraData, map);
        }

    }

    public static class CaldroidSampleCustomAdapter extends CaldroidGridAdapter {

        private static final int THRESHOLD_PERFECT = 70;
        private static final int THRESHOLD_OVER = 100;
        Map<DateTime, Integer> map;

        /**
         * Constructor
         *
         * @param context
         * @param month
         * @param year
         * @param caldroidData
         * @param extraData
         * @param map
         */
        public CaldroidSampleCustomAdapter(Context context, int month, int year, Map<String, Object> caldroidData, Map<String, Object> extraData, Map<DateTime, Integer> map) {
            super(context, month, year, caldroidData, extraData);
            this.map = map;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cellView = convertView;

            // For reuse
            if (convertView == null) {
                cellView = inflater.inflate(R.layout.custom_cell, null);
            }

            DateTime dateTime = this.datetimeList.get(position);

            ProgressBar cirular = (ProgressBar) cellView.findViewById(R.id.circularProgressbar);
            if (map.containsKey(dateTime)) {
                if (dateTime.equals(getToday())) {
                    cirular.setProgressDrawable(context.getResources().getDrawable(circular_calendar_current));
                    cirular.setProgress(map.get(dateTime));
                } else if (map.get(dateTime) > THRESHOLD_OVER) {
                    cirular.setProgressDrawable(context.getResources().getDrawable(circular_calendar_over));
                    cirular.setProgress(map.get(dateTime));
                } else if (map.get(dateTime) > THRESHOLD_PERFECT) {
                    cirular.setProgressDrawable(context.getResources().getDrawable(circular_calendar_perfect));
                    cirular.setProgress(map.get(dateTime));
                } else {
                    cirular.setProgressDrawable(context.getResources().getDrawable(circular_calendar_under));
                    cirular.setProgress(map.get(dateTime));
                }
            } else
                cirular.setVisibility(View.INVISIBLE);

            TextView tvDate = (TextView) cellView.findViewById(R.id.tvDate);
            Log.d("asd", "sad");
            tvDate.setText(dateTime.getDay().toString());

            Resources resources = context.getResources();

            boolean shouldResetDiabledView = false;
            boolean shouldResetSelectedView = false;

            // Customize for disabled dates and date outside min/max dates
            if ((minDateTime != null && dateTime.lt(minDateTime))
                    || (maxDateTime != null && dateTime.gt(maxDateTime))
                    || (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

                tvDate.setTextColor(CaldroidFragment.disabledTextColor);
                if (CaldroidFragment.disabledBackgroundDrawable == -1) {
                    cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
                } else {
                    cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
                }

                if (dateTime.equals(getToday())) {
                    cellView.setBackgroundResource(R.drawable.calendar_today_border);
                }

            } else {
                shouldResetDiabledView = true;
            }

            // Customize for selected dates
            if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
                cellView.setBackgroundColor(resources
                        .getColor(com.caldroid.R.color.caldroid_sky_blue));

                tvDate.setTextColor(Color.BLACK);

            } else {
                shouldResetSelectedView = true;
            }

            if (shouldResetDiabledView && shouldResetSelectedView) {
                // Customize for today
                if (dateTime.equals(getToday())) {
                    cellView.setBackgroundResource(calendar_today_border);
                } else {
                    cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
                }
            }

            // Set custom color if required
            setCustomResources(dateTime, cellView, tvDate);

            //return super.getView(position, convertView, parent);
            return cellView;
        }
    }

}
