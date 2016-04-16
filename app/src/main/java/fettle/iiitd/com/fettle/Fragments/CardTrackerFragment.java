package fettle.iiitd.com.fettle.Fragments;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import fettle.iiitd.com.fettle.Activities.LandingActivity;
import fettle.iiitd.com.fettle.Classes.Exercise;
import fettle.iiitd.com.fettle.Classes.User;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardTrackerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardTrackerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardTrackerFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressBar mProgress;
    TextView exercise1;
    TextView exercise2;
    TextView tvPercentageAchieved;
    private LandingActivity.AddedListener mAddedListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public CardTrackerFragment() {
        // Required empty public constructor
    }

    public CardTrackerFragment(LandingActivity.AddedListener mAddedListener) {
        this.mAddedListener = mAddedListener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardTrackerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardTrackerFragment newInstance(String param1, String param2) {
        CardTrackerFragment fragment = new CardTrackerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_tracker, container, false);

        ImageView im = (ImageView) view.findViewById(R.id.imExercise1);
        im.setImageResource(Utils.getExerciseImageId(User.getExercise1(), true));

        im = (ImageView) view.findViewById(R.id.imExercise2);
        im.setImageResource(Utils.getExerciseImageId(User.getExercise2(), false));

        exercise1 = (TextView) view.findViewById(R.id.tvExercise1);
        exercise2 = (TextView) view.findViewById(R.id.tvExercise2);
        tvPercentageAchieved = (TextView) view.findViewById(R.id.tvPerc);

        initProgress(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button add_btn = (Button) getActivity().findViewById(R.id.add_activity_btn);
        add_btn.setOnClickListener(this);

        LandingActivity.added1 = true;
        mAddedListener.isAdded(true);

    }

    private void initProgress(View view) {
//        Resources res = getResources();
//        Drawable drawable = res.getDrawable(R.drawable.background);
        mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
        mProgress.setMax(100); // Maximum Progress
        setProgress(view, 10, 0);
//        mProgress.setProgressDrawable(drawable);
    }

    public void setProgress(View view, int first, int second) {
        ProgressBar mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
        mProgress.setProgress(first);   // Main Progress
        mProgress.setSecondaryProgress(second); // Secondary Progress
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_activity_btn) {

            createDialog();
        }
    }

    private void createDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.add_activity_dialog);
        dialog.setTitle("Add Activity");

        final NumberPicker numberPicker1 = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        numberPicker1.setMinValue(0);
        numberPicker1.setMaxValue(100);
        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1);
        numberPicker.setDisplayedValues(new String[]{User.getExercise1(), User.getExercise2()});
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        dialog.show();

        Button discard, add;
        TextView tvDish;
        TextView tvUnits;
        /*tvDish = (TextView) dialog.findViewById(R.id.tvDish);
        tvUnits = (TextView) dialog.findViewById(R.id.tvUnits);*/
//        if(dish.getName().startsWith("Calories"))
//            tvDish.setText(dish.getName().substring(12));
//        else
//            tvDish.setText(dish.getName());
//        tvUnits.setText(dish.getMeasure()+" "+dish.getDescription()+" = "+dish.getGram()+" grams");

        discard = (Button) dialog.findViewById(R.id.discard);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        add = (Button) dialog.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise.uploadActivity(numberPicker.getValue() == 0 ? User.getExercise1() : User.getExercise2(), numberPicker1.getValue());
                LandingActivity.updateDataExercise = true;
                try {
                    ((LandingActivity) getActivity()).getCachedDataExercise(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onStop() {
        LandingActivity.added1 = false;
        mAddedListener.isAdded(false);
        super.onStop();
    }

    public void updateData(int progress, int exercise1Duration, int exercise2Duration) {
        mProgress.setProgress(progress);
        exercise1.setText(exercise1Duration + " Min");
        exercise2.setText(exercise2Duration + " Min");
        tvPercentageAchieved.setText(progress + "%\nachieved");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
