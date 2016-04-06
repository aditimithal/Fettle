package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fettle.iiitd.com.fettle.Adapters.RestrauntListAdapter;
import fettle.iiitd.com.fettle.Classes.Restraunt;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class RestrauntList extends AppCompatActivity {

    private static final String TAG = "RestaurantList";
    RestrauntListAdapter cList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restraunt_list);

        List<Restraunt> menu = new ArrayList<>();
        Restraunt r1 = new Restraunt();
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);

        String category = getIntent().getStringExtra("category");
        Log.d(TAG, initList(category).toString());
        menu = initList(category);

        mRecyclerView = (RecyclerView) findViewById(R.id.restraunt_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RestrauntListAdapter(this, category, menu);
        mRecyclerView.setAdapter(cList);

    }

    private List<Restraunt> initList(String category) {
        List<Restraunt> restaurants = new ArrayList<>();
        List<ParseObject> listPos = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(category == null ? "Breads" : category);
        query.selectKeys(Arrays.asList(new String[]{"restaurant"}));
        query.orderByAscending("restaurant");
        try {
            listPos = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<String> set = new HashSet<>();
        for (ParseObject each : listPos)
            set.add(each.getString("restaurant"));
        for (String s : set)
            restaurants.add(new Restraunt(s));
        Collections.sort(restaurants, new RestaurantListComparator());
        return restaurants;
    }

    public class RestaurantListComparator implements Comparator<Restraunt> {

        @Override
        public int compare(Restraunt lhs, Restraunt rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    }


}
