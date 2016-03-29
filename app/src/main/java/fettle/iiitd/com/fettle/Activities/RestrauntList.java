package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.Adapters.RestrauntListAdapter;
import fettle.iiitd.com.fettle.Adapters.RestrauntMenuAdapter;
import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.Classes.Restraunt;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class RestrauntList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RestrauntListAdapter cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restraunt_list);

        ArrayList<Restraunt> menu = new ArrayList<>();
        Restraunt r1 = new Restraunt();
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);

        mRecyclerView = (RecyclerView) findViewById(R.id.restraunt_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RestrauntListAdapter(this, menu);
        mRecyclerView.setAdapter(cList);


    }
}