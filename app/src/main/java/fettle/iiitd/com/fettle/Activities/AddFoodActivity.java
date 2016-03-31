package fettle.iiitd.com.fettle.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fettle.iiitd.com.fettle.Classes.Dish;
import fettle.iiitd.com.fettle.R;

public class AddFoodActivity extends AppCompatActivity {

    CardArrayRecyclerViewAdapter rcAdapter;
    ArrayList<String> stringlist;
    List<Dish> dishes;
    private List<String> meals = Arrays.asList(new String[]{"Breakfast", "Lunch", "Dinner"});
    private int[] drawables = new int[]{R.drawable.breakfast, R.drawable.lunch, R.drawable.dinner};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        dishes = new ArrayList<>();

        GridLayoutManager lLayout = new GridLayoutManager(AddFoodActivity.this, 2);

        RecyclerView rView = (RecyclerView) findViewById(R.id.myList);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new CardArrayRecyclerViewAdapter(AddFoodActivity.this, dishes);
        rView.setAdapter(rcAdapter);
    }

    private void createDialog(int position) {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.quant_dialog);
        dialog.setTitle("Add quantity");

        final Dish dish = dishes.get(position);

        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);

        final NumberPicker numberPicker1 = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        numberPicker1.setMinValue(0);
        numberPicker1.setMaxValue(1);
        numberPicker1.setDisplayedValues(new String[]{"grams", dish.getDescription()});

        dialog.show();

        Button discard, add;
        TextView tvDish;
        TextView tvUnits;
        /*tvDish = (TextView) dialog.findViewById(R.id.tvDish);
        tvUnits = (TextView) dialog.findViewById(R.id.tvUnits);*/
//        if(dish.getName().startsWith("Calories"))
//            tvDish.setText(dish.getName().substring(12));
//        else
//            tvDish.setText(dish.getName());
//        tvUnits.setText(dish.getMeasure()+" "+dish.getDescription()+" = "+dish.getGram()+" grams");

        discard = (Button) dialog.findViewById(R.id.discard);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        add = (Button) dialog.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject parseObject = new ParseObject("FoodIntake");
                parseObject.put("user", ParseUser.getCurrentUser());
                parseObject.put("quantity", numberPicker.getValue());
                parseObject.put("cal", dish.getCalories());
                parseObject.put("carb", dish.getCarbs());
                parseObject.put("description", dish.getDescription());
                parseObject.put("fat", dish.getFat());
                parseObject.put("fiber", dish.getFiber());
                parseObject.put("gram", dish.getGram());
                parseObject.put("image", dish.getImage());
                parseObject.put("measure", numberPicker1.getValue() == 0 ? "grams" : dish.getDescription());
                parseObject.put("name", dish.getName());
                parseObject.put("protein", dish.getProtein());
                parseObject.put("meal", ((TextView) findViewById(R.id.tvMeal)).getText().toString());
                try {
                    parseObject.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();
                }

            }
        });
    }

    public void onLeftClick(View view) {
        changeMeal(-1);
    }

    public void onRightClick(View view) {
        changeMeal(1);
    }

    private void changeMeal(int delta) {
        TextView tv = (TextView) findViewById(R.id.tvMeal);
        String meal = (String) tv.getText();
        int requiredIndex = meals.indexOf(meal) + delta;
        requiredIndex += 3;
        requiredIndex %= 3;
        tv.setText(meals.get(requiredIndex));
        ImageView im = (ImageView) findViewById(R.id.imMeal);
        im.setImageResource(drawables[requiredIndex]);
    }

    public void onSearchClick(View view) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("IndianDishes");
        String subString = ((EditText) findViewById(R.id.etSearch)).getText().toString();
        query.whereContains("name", subString);
        query.setLimit(20);
        List<ParseObject> lPo = new ArrayList<>();
        try {
            lPo = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dishes.clear();
        for (ParseObject each : lPo) {
            dishes.add(new Dish(each));
        }
        rcAdapter.notifyDataSetChanged();
    }

    public void addNewItem(View view) {
        startActivity(new Intent(this, NewItem.class));
    }

    public class CardArrayRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> //adapter for recyclerview to load all listvenue
    {

        private List<Dish> foods;
        private LayoutInflater inflater = null;

        public CardArrayRecyclerViewAdapter(Context context, List<Dish> listDishes) {
            this.foods = listDishes;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CardListViewHolder(inflater.inflate(R.layout.addfood_item_cell, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            if (foods.get(position).getName().startsWith("Calories"))
                ((CardListViewHolder) holder).tvName.setText(foods.get(position).getName().substring(12));
            else
                ((CardListViewHolder) holder).tvName.setText(foods.get(position).getName());


            ((CardListViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() //open venuepage of particular foods
            {
                @Override
                public void onClick(View v) {
                    createDialog(position);
                    //listhotposition = position;
                    //Intent i = new Intent(manangrid.this, venuepaage.class);
                    //startActivity(i);
                }
            });

        }


        @Override
        public int getItemCount() {
            return foods.size();
        }

        private class CardListViewHolder extends RecyclerView.ViewHolder {
            public TextView tvName;
            public ImageView imageView;
            public View view;

            public LinearLayout linearLayout;

            public CardListViewHolder(View convertView) {
                super(convertView);
                view = convertView;
                imageView = (ImageView) convertView.findViewById(R.id.iv); //imageview of card
                tvName = (TextView) convertView.findViewById(R.id.foodName); //name of foods of card
                linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout); //Linearlayout of whole card to open venuepage
            }
        }

    }

}
