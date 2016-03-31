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

    public void updateCalories() {
        TextView tv;
        tv = (TextView) getActivity().findViewById(R.id.tvBreakfast);
        tv = (TextView) getActivity().findViewById(R.id.tvLunch);
        tv = (TextView) getActivity().findViewById(R.id.tvDinner);
        tv = (TextView) getActivity().findViewById(R.id.tvPercentage);
        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressIntake);
    }

}
