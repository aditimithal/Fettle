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
import java.util.List;

import fettle.iiitd.com.fettle.Adapters.RestrauntMenuAdapter;
import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 21/03/16.
 */
public class RestrauntMenuList extends AppCompatActivity {
    private static final String TAG = "RestaurnatMenuList";
    RestrauntMenuAdapter cList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restraunt_menu);

        ArrayList<Menu> menu = new ArrayList<>();
        Menu r1 = new Menu();
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);


        String restaurant = getIntent().getStringExtra("restaurant");
        String category = getIntent().getStringExtra("category");
        Log.d(TAG, initList(restaurant, category).toString());

        mRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RestrauntMenuAdapter(this, menu);
        mRecyclerView.setAdapter(cList);

    }

    private List<Menu> initList(String restaurant, String category) {
        if (restaurant == null)
            restaurant = "Tubby's";
        if (category == null)
            category = "Breads";
        List<ParseObject> lPo = new ArrayList<>();
        List<Menu> lMenu = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(category);
        query.whereEqualTo("restaurant", restaurant);
        query.orderByAscending("name");
        try {
            lPo = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ParseObject each : lPo) {
            lMenu.add(new Menu(each.getString("name")));
        }
        return lMenu;
    }
}
