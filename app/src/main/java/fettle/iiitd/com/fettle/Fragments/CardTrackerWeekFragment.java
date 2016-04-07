package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardTrackerWeekFragment extends Fragment implements View.OnClickListener {

    private int value1 = 5;
    private int value2 = 7;
    private String[] days = {"M", "T", "W", "T", "F", "Sa", "Su"};


    public CardTrackerWeekFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_tracker_week, container, false);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.linearLayout);
        ll.setWeightSum(7);
        for (int i = 0; i < 7; i++) {
            View v = inflater.inflate(R.layout.landing_daily_fitness_tracker_subpart, null, false);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            TextView tv = (TextView) v.findViewById(R.id.tvDay);
            tv.setText(days[i]);
            Calendar calendar = Calendar.getInstance();
            if (i == (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7)
                tv.setBackgroundResource(R.drawable.circular_background_yellow);

            ProgressBar mProgress1 = (ProgressBar) v.findViewById(R.id.circularProgressbar1);
            mProgress1.setMax(100); // Maximum Progress
            mProgress1.setProgress(10 + 2 * i);   // Main Progress
            mProgress1.setSecondaryProgress(0); // Secondary Progress

            ProgressBar mProgress2 = (ProgressBar) v.findViewById(R.id.circularProgressbar2);
            mProgress2.setMax(100); // Maximum Progress
            mProgress2.setProgress(10);   // Main Progress
            mProgress2.setSecondaryProgress(20); // Secondary Progress

            ll.addView(v);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.more_tracker_week) {

        }
    }
}
