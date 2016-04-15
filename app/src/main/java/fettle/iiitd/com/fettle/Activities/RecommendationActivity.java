package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.Adapters.RecommendationAdapter;
import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 13-Apr-16.
 */
public class RecommendationActivity extends AppCompatActivity {
    private static final String TAG = "RecommendationMenuList";
    RecommendationAdapter cList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation);

        String nutrient = getIntent().getStringExtra("nutrient");


        //TODO Danish: make it async
        List<Menu> list = getList(nutrient);


        int imageid = imageID(nutrient);
        mRecyclerView = (RecyclerView) findViewById(R.id.recommendation_menu_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RecommendationAdapter(this, nutrient, imageid, list);
        mRecyclerView.setAdapter(cList);
    }

    private List<Menu> getList(String nutrient) {
        List<Menu> list = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("IndianDishes");
        query.setLimit(15);
        if (nutrient.equals("fats")) {
            query.orderByDescending("fatFloat");
        } else if (nutrient.equals("fibers")) {
            query.orderByDescending("fiberFloat");
        } else if (nutrient.equals("proteins")) {
            query.orderByDescending("proteinFloat");
        } else if (nutrient.equals("carbohydrates")) {
            query.orderByDescending("carbFloat");
        }
        List<ParseObject> parseObjectList = new ArrayList<>();
        try {
            parseObjectList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ParseObject each : parseObjectList) {
            Menu menu = new Menu(each.getString("name"), each.getInt("cal"));
            menu.setCarb(each.getInt("carb"));
            menu.setFat(each.getInt("fat"));
            menu.setFiber(each.getInt("fiber"));
            menu.setProtein(each.getInt("protein"));
            list.add(menu);
        }
        return list;
    }

    public int imageID(String nutrient) {
        if (nutrient.equals("fats")) {
            return R.drawable.fats_g;
        } else if (nutrient.equals("fibers")) {
            return R.drawable.fiber_g;
        } else if (nutrient.equals("proteins")) {
            return R.drawable.protein_g;
        } else if (nutrient.equals("carbohydrates")) {
            return R.drawable.carbs_g;
        }
        return R.drawable.fats_g;
    }
}
