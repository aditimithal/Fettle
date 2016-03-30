package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fettle.iiitd.com.fettle.R;

/**
 * Created by Manan on 30-03-2016.
 */
public class FoodTypeSelection extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_type_selectoin);

        Button indian, fastfood;
        indian = (Button) findViewById(R.id.indian);
        fastfood = (Button) findViewById(R.id.fastfood);
        indian.setOnClickListener(this);
        fastfood.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.indian) {
            Intent myIntent = new Intent(FoodTypeSelection.this, AddFoodActivity.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.fastfood) {
            Intent myIntent = new Intent(FoodTypeSelection.this, RestrauntList.class);
            startActivity(myIntent);
        }
    }
}
