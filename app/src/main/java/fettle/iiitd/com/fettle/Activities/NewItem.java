package fettle.iiitd.com.fettle.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText;
        ParseObject parseObject = new ParseObject("IndianDishes");
        editText = (EditText) findViewById(R.id.input_name);
        parseObject.put("name", editText.getText().toString());
        editText = (EditText) findViewById(R.id.input_calories);
        parseObject.put("calories", editText.getText().toString());
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
