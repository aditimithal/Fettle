package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.Adapters.FoodCategoryAdapter;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 31-Mar-16.
 */
public class FoodOrderCategory extends AppCompatActivity {
    private static final String TAG = "FoodOrderCategory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Log.d("abc", "activity started");

        RecyclerView recyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        FoodCategoryAdapter cList;

        List<Utils.FoodCategory> listCategories = new ArrayList<>();
//        listCategories.add(new Utils.FoodCategory("abcd"));
//        listCategories.add(new Utils.FoodCategory("abcd"));
        listCategories = initList();
        Log.d(TAG, initList().toString());
        cList = new FoodCategoryAdapter(this, listCategories);

        recyclerView = (RecyclerView) findViewById(R.id.category_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cList);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Utils.FoodCategory> initList() {
        List<Utils.FoodCategory> listCategories = new ArrayList<>();
        List<ParseObject> listParseObjects = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Categories");
        query.fromLocalDatastore();
        query.fromPin("categories");
        query.orderByAscending("category");
        try {
            listParseObjects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ParseObject each : listParseObjects) {
            listCategories.add(new Utils.FoodCategory(each.getString("category")));
        }

        if (listCategories.size() != 0)
            return listCategories;

        query = new ParseQuery<ParseObject>("Categories");
        query.orderByAscending("category");
        try {
            listParseObjects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (ParseObject each : listParseObjects) {
            listCategories.add(new Utils.FoodCategory(each.getString("category")));
        }
        ParseObject.pinAllInBackground("categories", listParseObjects);

        return listCategories;
    }

}
