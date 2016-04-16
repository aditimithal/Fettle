package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fettle.iiitd.com.fettle.Adapters.RestrauntMenuAdapter;
import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 21/03/16.
 */
public class RestrauntMenuList extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RestaurnatMenuList";
    public static List<ParseObject> addedFood = new ArrayList<>();
    RestrauntMenuAdapter cList;
    List<Menu> menu = new ArrayList<>();
    String restaurant;
    Button fab;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onRestart() {
        super.onRestart();
//        fab.setText(addedFood.size());
    }

    @Override
    protected void onResume() {
        fab.setText(addedFood.size() + "");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restraunt_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        restaurant = getIntent().getStringExtra("restaurant");
        String category = getIntent().getStringExtra("category");
        Log.d(TAG, initList(restaurant, category).toString());
        menu = initList(restaurant, category);

        mRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RestrauntMenuAdapter(this, restaurant, menu, fab);
        mRecyclerView.setAdapter(cList);

        final EditText filterrestraunt = (EditText) findViewById(R.id.filter_menu);
        filterrestraunt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = filterrestraunt.getText().toString().toLowerCase(Locale.getDefault());
                if (text.equals("")) {
                    cList = new RestrauntMenuAdapter(RestrauntMenuList.this, restaurant, menu, fab);
                    mRecyclerView.setAdapter(cList);
                } else {
                    List<Menu> filtered_menu = new ArrayList<Menu>();
                    filtered_menu.addAll(returnfilteredList(text, menu));
                    cList = new RestrauntMenuAdapter(RestrauntMenuList.this, restaurant, filtered_menu, fab);
                    mRecyclerView.setAdapter(cList);
                }
//                cList.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
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

    public List<Menu> returnfilteredList(String text, List<Menu> menu) {
        List<Menu> filtered_menu = new ArrayList<>();
        for (Menu me : menu) {
            if (me.getName() != null) {
                if (me.getName().toLowerCase().contains(text.toLowerCase())) {
                    filtered_menu.add(me);
                }
            }
        }
        return filtered_menu;

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
            Menu menu = new Menu(each.getString("name"), each.getInt("cal"));
            menu.setCarb(each.getInt("carb"));
            menu.setFat(each.getInt("fat"));
            menu.setFiber(each.getInt("fiber"));
            menu.setProtein(each.getInt("protein"));
            lMenu.add(menu);
        }
        return lMenu;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            v.startAnimation(shake);
            addedFood.clear();
            addedFood.addAll(cList.getAddedFood());
            startActivity(new Intent(RestrauntMenuList.this, AddedFoodOrderItemsActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if (cList.getAddedFood().size() > 0)
            onClick(fab);
        super.onBackPressed();
    }
}
