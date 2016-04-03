package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.Adapters.FoodCategoryAdapter;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 31-Mar-16.
 */
public class FoodOrderCategory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_category);

        RecyclerView breakfastRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        //TODO Manan:Change this adapter
        FoodCategoryAdapter cList;
        breakfastRecyclerView = (RecyclerView) findViewById(R.id.breakfast_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManager = new LinearLayoutManager(this);
        breakfastRecyclerView.setLayoutManager(mLayoutManager);
        cList = new FoodCategoryAdapter(this, new ArrayList<>());
//        breakfastRecyclerView.setAdapter(cList);

    }
}
