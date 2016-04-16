package fettle.iiitd.com.fettle.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.parse.ParseObject;

import fettle.iiitd.com.fettle.Adapters.AddedFoodOrderAdapter;
import fettle.iiitd.com.fettle.Adapters.RestrauntMenuAdapter;
import fettle.iiitd.com.fettle.R;

/**
 * Created by Danish on 15-Apr-16.
 */
public class AddedFoodOrderItemsActivity extends AppCompatActivity {

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

    @Override
    public void onBackPressed() {
        createDialog();
//        super.onBackPressed(); // instead there is a finish in dialog
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Upload items?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ParseObject.saveAll(RestrauntMenuList.addedFood);
                            ParseObject.pinAll("today", RestrauntMenuList.addedFood);
                            LandingActivity.updateData = true;
                            Toast.makeText(AddedFoodOrderItemsActivity.this, "Food added", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        resetList();
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetList();
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .create().show();
    }

    private void resetList() {
        RestrauntMenuList.addedFood.clear();
        RestrauntMenuAdapter.addedFood.clear();
    }


}
