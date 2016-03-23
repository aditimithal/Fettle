package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 11/03/16.
 */
public class CaloriesOverview extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_exercise_overview, container, false);

            return rootView;
        }

}
