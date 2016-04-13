package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        List<Menu> menu = new ArrayList<>();
        Menu r1 = new Menu();
        menu.add(r1);
        menu.add(r1);

        int imageid = imageID(nutrient);
        mRecyclerView = (RecyclerView) findViewById(R.id.recommendation_menu_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RecommendationAdapter(this, nutrient, imageid, menu);
        mRecyclerView.setAdapter(cList);

    }

    public int imageID(String nutrient) {
        if (nutrient.equals("fats")) {
            return R.drawable.fats_g;
        } else if (nutrient.equals("protein")) {
            return R.drawable.protein_g;
        }
        return R.drawable.fats_g;
    }
}
