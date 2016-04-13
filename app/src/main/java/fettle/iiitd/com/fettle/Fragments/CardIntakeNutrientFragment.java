package fettle.iiitd.com.fettle.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import fettle.iiitd.com.fettle.Activities.LandingActivity;
import fettle.iiitd.com.fettle.Activities.RecommendationActivity;
import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardIntakeNutrientFragment extends Fragment implements View.OnClickListener {


    public static final int limitFiber = 30;
    public static final int limitFats = 70;
    public static final int limitCarbs = 310;
    public static final int limitProteins = 50;
    String[] nutrients = {"Fiber", "Fats", "Carbs", "Protein"};
    int[] nutrientDrawables = {R.drawable.fiber_g, R.drawable.fats_g, R.drawable.carbs_g, R.drawable.protein_g};
    private LinearLayout lFiber, lFats, lCarbs, lProteins;
    private LandingActivity.AddedListener mAddedListener;

    @SuppressLint("ValidFragment")
    public CardIntakeNutrientFragment(LandingActivity.AddedListener mAddedListener) {
        this.mAddedListener = mAddedListener;
    }

    public CardIntakeNutrientFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_intake_nutrient, container, false);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.linearLayout);
        ll.setWeightSum(4);
        for (int i = 0; i < 4; i++) {
            View v = inflater.inflate(R.layout.landing_intake_nutrient_subpart, null, false);
            if (i == 0)
                lFiber = (LinearLayout) v;
            if (i == 1)
                lFats = (LinearLayout) v;
            if (i == 2)
                lCarbs = (LinearLayout) v;
            if (i == 3)
                lProteins = (LinearLayout) v;

            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView tv = (TextView) v.findViewById(R.id.tvNutrient);
            tv.setText(nutrients[i]);

            ImageView im = (ImageView) v.findViewById(R.id.imNutrient);
            im.setImageResource(nutrientDrawables[i]);

            ProgressBar mProgress = (ProgressBar) v.findViewById(R.id.circularProgressbar);
            mProgress.setMax(100); // Maximum Progress
            mProgress.setProgress(10 + 2 * i);   // Main Progress
            mProgress.setSecondaryProgress(0); // Secondary Progress

            ll.addView(v);
        }
        return view;
    }

    public void updateNutrients(int fiber, int fats, int carbs, int protein) {
        TextView tv;
        ProgressBar progressBar;
        tv = (TextView) lFiber.findViewById(R.id.tvGram);
        progressBar = (ProgressBar) lFiber.findViewById(R.id.circularProgressbar);
        tv.setText(fiber + "g");
        progressBar.setProgress(fiber * 100 / limitFiber);

        tv = (TextView) lFats.findViewById(R.id.tvGram);
        progressBar = (ProgressBar) lFats.findViewById(R.id.circularProgressbar);
        tv.setText(fats + "g");
        progressBar.setProgress(fats * 100 / limitFats);

        tv = (TextView) lCarbs.findViewById(R.id.tvGram);
        progressBar = (ProgressBar) lCarbs.findViewById(R.id.circularProgressbar);
        tv.setText(carbs + "g");
        progressBar.setProgress(carbs * 100 / limitCarbs);

        tv = (TextView) lProteins.findViewById(R.id.tvGram);
        progressBar = (ProgressBar) lProteins.findViewById(R.id.circularProgressbar);
        tv.setText(protein + "g");
        progressBar.setProgress(protein * 100 / limitProteins);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button more = (Button) getActivity().findViewById(R.id.nutrient_more_btn);
        more.setOnClickListener(this);
        LandingActivity.added3 = true;
        mAddedListener.isAdded(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nutrient_more_btn) {
//            Intent myIntent = new Intent(getActivity(), DayOverview.class);
            Intent myIntent = new Intent(getActivity(), RecommendationActivity.class);
            myIntent.putExtra("nutrient", "protein");
            startActivity(myIntent);
        }
    }

    @Override
    public void onStop() {
        LandingActivity.added3 = false;
        mAddedListener.isAdded(false);
        super.onStop();
    }
}
