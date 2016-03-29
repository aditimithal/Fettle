package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardIntakeNutrientFragment extends Fragment {


    public CardIntakeNutrientFragment() {
        // Required empty public constructor
    }

    String[] nutrients = {"Fiber", "Fats", "Carbs", "Protein"};
    int[] nutrientDrawables = {R.drawable.fiber, R.drawable.fats, R.drawable.carbs, R.drawable.protein};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_intake_nutrient, container, false);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.linearLayout);
        ll.setWeightSum(4);
        for (int i = 0; i < 4; i++) {
            View v = inflater.inflate(R.layout.landing_intake_nutrient_subpart, null, false);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView tv = (TextView) v.findViewById(R.id.tvNutrient);
            tv.setText(nutrients[i]);

            ImageView im = (ImageView) v.findViewById(R.id.imNutrient);
            im.setImageResource(nutrientDrawables[i]);

            ProgressBar mProgress = (ProgressBar) v.findViewById(R.id.circularProgressbar);
            mProgress.setMax(100); // Maximum Progress
            mProgress.setProgress(10 + 2 * i);   // Main Progress
            mProgress.setSecondaryProgress(20 + 3 * i); // Secondary Progress

            ll.addView(v);
        }
        return view;
    }

}
