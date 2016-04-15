package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.Activities.CustomCalendar;
import fettle.iiitd.com.fettle.Activities.LandingActivity;
import fettle.iiitd.com.fettle.Adapters.FoodListAdaper;
import fettle.iiitd.com.fettle.Classes.Dish;
import fettle.iiitd.com.fettle.Classes.FoodItem;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 11/03/16.
 */
public class CaloriesOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_food_overview, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int sum = 0;

        List<Dish> dishes;
        dishes = LandingActivity.moreDishes;
        if (getActivity().getIntent().getBooleanExtra("calendar", false)) {
            dishes = CustomCalendar.moreDishes;
        }
        ArrayList<FoodItem> menuBreakfast = new ArrayList<>();
        ArrayList<FoodItem> menuLunch = new ArrayList<>();
        ArrayList<FoodItem> menuDinner = new ArrayList<>();
        for (Dish each : dishes) {
            FoodItem item = new FoodItem();
            item.setUnit(each.getDescription());
            if (each.getName().startsWith("Calories"))
                item.setFoodName(each.getName().substring(12));
            else
                item.setFoodName(each.getName());
            float multiplier = 1f;
            if (each.getDescription().toLowerCase().startsWith("gram")) {
                try {
                    multiplier = each.getQuantity() / Float.parseFloat(each.getGram());
                } catch (Exception e) {
                }
            } else {
                try {
                    multiplier = each.getQuantity() / Float.parseFloat(each.getMeasure());
                } catch (Exception e) {
                }
            }
            item.setQuantity(each.getQuantity());
            item.setCalories((int) (multiplier * Float.parseFloat(each.getCalories())));

            if (each.getMeal().equals("Breakfast")) {
                menuBreakfast.add(item);
            } else if (each.getMeal().equals("Lunch")) {
                menuLunch.add(item);
            } else if (each.getMeal().equals("Dinner")) {
                menuDinner.add(item);
            }
            sum += item.getCalories();
        }


        RecyclerView breakfastRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        FoodListAdaper cList;

        breakfastRecyclerView = (RecyclerView) getActivity().findViewById(R.id.breakfast_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManager = new LinearLayoutManager(getActivity());
        breakfastRecyclerView.setLayoutManager(mLayoutManager);
        cList = new FoodListAdaper(getActivity(), menuBreakfast);
        breakfastRecyclerView.setAdapter(cList);

        RecyclerView dinnerfastRecyclerView;
        FoodListAdaper cList1;

        dinnerfastRecyclerView = (RecyclerView) getActivity().findViewById(R.id.lunch_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        dinnerfastRecyclerView.setLayoutManager(mLayoutManager);
        cList1 = new FoodListAdaper(getActivity(), menuLunch);
        dinnerfastRecyclerView.setAdapter(cList1);


        RecyclerView lunchfastRecyclerView;
        FoodListAdaper cList2;

        lunchfastRecyclerView = (RecyclerView) getActivity().findViewById(R.id.dinner_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        lunchfastRecyclerView.setLayoutManager(mLayoutManager);
        cList2 = new FoodListAdaper(getActivity(), menuDinner);
        lunchfastRecyclerView.setAdapter(cList2);

        ((TextView) getActivity().findViewById(R.id.total_calories)).setText(sum + "calories");
        ((ProgressBar) getActivity().findViewById(R.id.pg)).setProgress((sum * 100) / Utils.getPref(getActivity(), Utils.DAILY_CALORIE_KEY));

    }
}
