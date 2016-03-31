package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardIntakeFragment extends Fragment {


    public CardIntakeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_intake, container, false);
    }

    public void updateCalories(int breakfast, int lunch, int dinner, int required) {
        TextView tv;
        tv = (TextView) getActivity().findViewById(R.id.tvBreakfast);
        tv.setText(breakfast == 0 ? "..." : breakfast + "");
        tv = (TextView) getActivity().findViewById(R.id.tvLunch);
        tv.setText(lunch == 0 ? "..." : lunch + "");
        tv = (TextView) getActivity().findViewById(R.id.tvDinner);
        tv.setText(dinner == 0 ? "..." : dinner + "");
        tv = (TextView) getActivity().findViewById(R.id.tvTotal);
        int total = breakfast + lunch + dinner;
        tv.setText(total + "");
        float percentage = total * 100 / required;
        int percentageInt = (int) percentage;
        tv = (TextView) getActivity().findViewById(R.id.tvPercentage);
        tv.setText(percentageInt + "%");
        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressIntake);
        progressBar.setProgress(percentageInt);
    }

}
