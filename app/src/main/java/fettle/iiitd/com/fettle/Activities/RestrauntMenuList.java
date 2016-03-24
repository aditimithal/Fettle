package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.Adapters.RestrauntMenuAdapter;
import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 21/03/16.
 */
public class RestrauntMenuList extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RestrauntMenuAdapter cList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restraunt_menu);

        ArrayList<Menu> menu=new ArrayList<>();
        Menu r1=new Menu();
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);
        menu.add(r1);



        mRecyclerView = (RecyclerView) findViewById(R.id.menu_recyclerview);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cList = new RestrauntMenuAdapter(this, menu);
        mRecyclerView.setAdapter(cList);



    }
}
