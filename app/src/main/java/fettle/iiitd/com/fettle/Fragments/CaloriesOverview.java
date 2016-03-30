package fettle.iiitd.com.fettle.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.Adapters.FoodListAdaper;
import fettle.iiitd.com.fettle.Classes.FoodItem;
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


        ArrayList<FoodItem> menu = new ArrayList<>();
        FoodItem r1 = new FoodItem();
        menu.add(r1);
        menu.add(r1);

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
        cList = new FoodListAdaper(getActivity(), menu);
        breakfastRecyclerView.setAdapter(cList);

        RecyclerView dinnerfastRecyclerView;
        FoodListAdaper cList1;

        dinnerfastRecyclerView = (RecyclerView) getActivity().findViewById(R.id.lunch_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        dinnerfastRecyclerView.setLayoutManager(mLayoutManager);
        cList1 = new FoodListAdaper(getActivity(), menu);
        dinnerfastRecyclerView.setAdapter(cList1);


        RecyclerView lunchfastRecyclerView;
        FoodListAdaper cList2;

        lunchfastRecyclerView = (RecyclerView) getActivity().findViewById(R.id.dinner_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        lunchfastRecyclerView.setLayoutManager(mLayoutManager);
        cList2 = new FoodListAdaper(getActivity(), menu);
        lunchfastRecyclerView.setAdapter(cList2);


    }
}
