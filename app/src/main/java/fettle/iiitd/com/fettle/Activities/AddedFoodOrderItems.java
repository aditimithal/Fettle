package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fettle.iiitd.com.fettle.Adapters.AddedFoodOrderAdapter;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 15-Apr-16.
 */
public class AddedFoodOrderItems extends AppCompatActivity {

    AddedFoodOrderAdapter cList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedfoodorder);


        mRecyclerView = (RecyclerView) findViewById(R.id.myList);
        // use a linear layout manager2
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new AddedFoodOrderAdapter(this, RestrauntMenuList.addedFood);
        mRecyclerView.setAdapter(cList);
    }
}
