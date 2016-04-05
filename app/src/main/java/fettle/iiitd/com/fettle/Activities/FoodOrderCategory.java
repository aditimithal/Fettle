package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.Adapters.FoodCategoryAdapter;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 31-Mar-16.
 */
public class FoodOrderCategory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_category);

        RecyclerView recyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        FoodCategoryAdapter cList;
        List<Utils.FoodCategory> listCategories = new ArrayList<>();
        listCategories.add(new Utils.FoodCategory("abcd"));
        listCategories.add(new Utils.FoodCategory("abcd"));
        cList = new FoodCategoryAdapter(this, listCategories);
        recyclerView = (RecyclerView) findViewById(R.id.category_recyclerview);
        mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cList);

    }

}
