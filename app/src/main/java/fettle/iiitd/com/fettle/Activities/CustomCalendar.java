package fettle.iiitd.com.fettle.Activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.Calendar;
import java.util.Map;

import fettle.iiitd.com.fettle.R;
import hirondelle.date4j.DateTime;

public class CustomCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);

        CaldroidFragment caldroidFragment = new CaldroidSampleCustomFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.containerCalendar, caldroidFragment);
        t.commit();
    }

    public static class CaldroidSampleCustomFragment extends CaldroidFragment {

        @Override
        public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
            // TODO Auto-generated method stub
            return new CaldroidSampleCustomAdapter(getActivity(), month, year, getCaldroidData(), extraData);
        }

    }

    public static class CaldroidSampleCustomAdapter extends CaldroidGridAdapter
    {

        /**
         * Constructor
         *
         * @param context
         * @param month
         * @param year
         * @param caldroidData
         * @param extraData
         */
        public CaldroidSampleCustomAdapter(Context context, int month, int year, Map<String, Object> caldroidData, Map<String, Object> extraData) {
            super(context, month, year, caldroidData, extraData);
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

            TextView tvDate = (TextView) cellView.findViewById(R.id.tvDate);
            Log.d("asd","sad");
            tvDate.setText(this.datetimeList.get(position).getDay().toString());

            DateTime dateTime = this.datetimeList.get(position);
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
                    cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
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
                    cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
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
