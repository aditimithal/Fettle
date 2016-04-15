package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 29/02/16.
 */
public class RecommendationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String nutrient;
    int imageid;
    private List<Menu> messages;
    private LayoutInflater inflater = null;
    private Context context;

    public RecommendationAdapter(Context context, String nutrient, int id, List<Menu> messages) {
//        messages.add(0, new Menu());
        this.messages = messages;
        inflater = LayoutInflater.from(context);
        this.nutrient = nutrient;
        this.context = context;
        this.imageid = id;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MenuItemViewHolder(inflater.inflate(R.layout.recommendation_food_card, parent, false));
    }

    //TODO Danish: onBindViewHolder not getting called
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Menu menu = messages.get(position);
        Log.d("bindview", messages.toString() + position);
        int walk10 = Utils.getPref(context, Utils.WALK_10_CALORIES_KEY);
        int run10 = Utils.getPref(context, Utils.RUN_10_CALORIES_KEY);
        ((MenuItemViewHolder) holder).tvDishName.setText(menu.getName());
        ((MenuItemViewHolder) holder).tvCalories.setText(menu.getCalories() + " calories");
        ((MenuItemViewHolder) holder).tvPrice.setText("200");
        ((MenuItemViewHolder) holder).tvNutrientName.setText(nutrient);
        ((MenuItemViewHolder) holder).tvWalkingTime.setText(((menu.getCalories() * walk10) / 10 / 60) + "Min");
        ((MenuItemViewHolder) holder).tvRunningTime.setText(((menu.getCalories() * run10) / 10 / 60) + "Min");
        ((MenuItemViewHolder) holder).ivnutrientImage.setImageResource(imageid);
        /*((MenuItemViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LandingActivity.updateData = true;
                ParseObject parseObject = new ParseObject("FoodIntake");
                parseObject.put("user", ParseUser.getCurrentUser());
                parseObject.put("quantity", 1);
                parseObject.put("cal", menu.getCalories() + "");
                parseObject.put("carb", menu.getCarb() + "");
                parseObject.put("measure", 1 + "");
                parseObject.put("fat", menu.getFat() + "");
                parseObject.put("fiber", menu.getFiber() + "");
                parseObject.put("gram", 0 + "");
//                    parseObject.put("image", dish.getImage());
                parseObject.put("description", "unit");
                parseObject.put("name", menu.getName() + "");
                parseObject.put("protein", menu.getProtein() + "");
                parseObject.put("meal", LandingActivity.meal);
                parseObject.put("CreatedAt", Calendar.getInstance().getTime());
                try {
                    parseObject.saveEventually();
                    parseObject.pin("today");
                } catch (Exception e) {
                    Log.e("MenuItemClick", e.getMessage(), e);
                    e.printStackTrace();
                } finally {
                    Toast.makeText(context, "Food item added", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {

        if (messages == null) {
            return 0;
        } else {
            return messages.size();
        }
    }

    private static class MenuHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRestrauntName;
        public TextView tvLocation;
        public RatingBar ratingBar;
        public View view;

        public MenuHeaderViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvRestrauntName = (TextView) itemView.findViewById(R.id.restraunt_name);
            tvLocation = (TextView) itemView.findViewById(R.id.location);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
//            ivAskerPicture = (ImageView) itemView.findViewById(R.id.askerPicture);
        }
    }

    private static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDishName;
        public TextView tvPrice;
        public TextView tvCalories;
        public TextView tvWalkingTime;
        public TextView tvRunningTime;
        public ImageView ivnutrientImage;
        public TextView tvNutrientName;
        public View view;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvDishName = (TextView) itemView.findViewById(R.id.dish_name);
            tvPrice = (TextView) itemView.findViewById(R.id.price);
            tvCalories = (TextView) itemView.findViewById(R.id.calories);
            tvWalkingTime = (TextView) itemView.findViewById(R.id.walking_time);
            tvRunningTime = (TextView) itemView.findViewById(R.id.running_time);
            tvNutrientName = (TextView) itemView.findViewById(R.id.nutrient_name);
            ivnutrientImage = (ImageView) itemView.findViewById(R.id.nutrient_image);
        }
    }
}
