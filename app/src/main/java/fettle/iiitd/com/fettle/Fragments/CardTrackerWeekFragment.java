package fettle.iiitd.com.fettle.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fettle.iiitd.com.fettle.Activities.DayOverview;
import fettle.iiitd.com.fettle.Activities.GoogleFit;
import fettle.iiitd.com.fettle.Activities.SignUp;
import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardTrackerWeekFragment extends Fragment implements View.OnClickListener {

    private int value1 = 5;
    private int value2 = 7;
    private String[] days = {"M", "T", "W", "T", "F", "Sa", "Su"};
    private LinearLayout[] lls = new LinearLayout[7];

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
            lls[i] = (LinearLayout) v;
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            TextView tv = (TextView) v.findViewById(R.id.tvDay);
            tv.setText(days[i]);
            Calendar calendar = Calendar.getInstance();
            if (i == (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7)
                tv.setBackgroundResource(R.drawable.circular_background_yellow);

            ProgressBar mProgress1 = (ProgressBar) v.findViewById(R.id.circularProgressbar1);
            mProgress1.setMax(100); // Maximum Progress
            mProgress1.setProgress(0);   // Main Progress
            mProgress1.setSecondaryProgress(0); // Secondary Progress

            ProgressBar mProgress2 = (ProgressBar) v.findViewById(R.id.circularProgressbar2);
            mProgress2.setMax(100); // Maximum Progress
            mProgress2.setProgress(0);   // Main Progress
            mProgress2.setSecondaryProgress(0); // Secondary Progress

            ll.addView(v);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.more_tracker_week).setOnClickListener(this);

        List<Object> exercisedate = new ArrayList<>();

        GoogleFit googlefit = new GoogleFit(SignUp.mGoogleApiClient);
        if (googlefit.getFitnessData() != null) {
            exercisedate.addAll(googlefit.getFitnessData());
        } else {
            Log.d("mclient", "null");
        }


    }

    //TODO Danish use these 2 functions to set progresses or modify accordingly. u can use fragment 4 to call this function from Landing activity
    //TODO Danish, ask Sarthak how to fill progress(percentage) here

    /**
     * Week starts from Monday => 0 is Monday
     *
     * @param progressesExercise1
     * @param progressesExercise2
     */
    public void setProgresses(int[] progressesExercise1, int[] progressesExercise2) {
        for (int i = 0; i < 7; i++) {
            setProgress(i, progressesExercise1[i], progressesExercise2[i]);
        }
    }

    public void setProgress(int day, int progress1, int progress2) {
        ((ProgressBar) lls[day].findViewById(R.id.circularProgressbar1)).setProgress(progress1);
        ((ProgressBar) lls[day].findViewById(R.id.circularProgressbar2)).setProgress(progress2);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.more_tracker_week) {
            Intent intent = new Intent(getActivity(), DayOverview.class);
            intent.putExtra("exercise", true);
            startActivity(intent);
        }
    }
}
