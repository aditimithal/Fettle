package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fettle.iiitd.com.fettle.Adapters.FoodListAdaper;
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
        FoodListAdaper cList;
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
        cList = new FoodListAdaper(this, menu);
        breakfastRecyclerView.setAdapter(cList);


    }
}
