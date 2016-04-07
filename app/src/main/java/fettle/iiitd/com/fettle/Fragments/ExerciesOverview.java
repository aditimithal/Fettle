package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 11/03/16.
 */
public class ExerciesOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_exercise_overview, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void setDataforView(int walktime, int runtime, int runcal, int walkcal, int totalcal, int calorieinfo) {
        TextView walkTime, runTime, walkCal, runCal, totalCal, calorieInfo;
        walkTime = (TextView) getActivity().findViewById(R.id.time1);
        runTime = (TextView) getActivity().findViewById(R.id.time2);
        walkCal = (TextView) getActivity().findViewById(R.id.walkCal);
        runCal = (TextView) getActivity().findViewById(R.id.runCal);
        totalCal = (TextView) getActivity().findViewById(R.id.totalCal);
        calorieInfo = (TextView) getActivity().findViewById(R.id.calorie_info);

        walkTime.setText(walktime + "Min");
        runTime.setText(runtime + "Min");
        walkCal.setText(walkcal + "Cal");
        runCal.setText(runcal + "Cal");
        totalCal.setText(totalcal + "Cal");
        calorieInfo.setText(calorieinfo + "");
    }
}
