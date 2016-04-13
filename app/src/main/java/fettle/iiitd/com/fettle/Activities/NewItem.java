package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 30/03/16.
 */
public class NewItem extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        findViewById(R.id.fab).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        EditText editText;
        int ids[] = {R.id.input_name, R.id.input_calories, R.id.input_unit, R.id.input_quantity, R.id.input_grams, R.id.input_fat, R.id.input_fibre, R.id.input_carb, R.id.input_protein};
        for (int i = 0; i < ids.length; i++) {
            editText = (EditText) findViewById(ids[i]);
            if (editText.getText().toString().trim().equals("")) {
                Toast.makeText(NewItem.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        ParseObject parseObject = new ParseObject("IndianDishes");
        editText = (EditText) findViewById(R.id.input_name);
        parseObject.put("name", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_calories);
        parseObject.put("cal", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_unit);
        parseObject.put("description", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_quantity);
        parseObject.put("measure", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_grams);
        parseObject.put("gram", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_fat);
        parseObject.put("fat", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_fibre);
        parseObject.put("fiber", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_carb);
        parseObject.put("carb", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_protein);
        parseObject.put("protein", editText.getText().toString());
        parseObject.put("image", "not available");
        try {
            parseObject.save();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            finish();
        }
    }
}
