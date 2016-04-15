package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.Classes.Dish;
import fettle.iiitd.com.fettle.Classes.FoodItem;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 26/03/16.
 */
public class FoodListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FoodItem> messages;
    private LayoutInflater inflater = null;

    public FoodListAdaper(Context context, ArrayList<FoodItem> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserListViewHolder(inflater.inflate(R.layout.food_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UserListViewHolder) holder).tvName.setText(messages.get(position).getFoodName());
        ((UserListViewHolder) holder).tvQuantity.setText(messages.get(position).getQuantity() + " " + messages.get(position).getUnit());
        ((UserListViewHolder) holder).tvCalories.setText(messages.get(position).getCalories() + " calories");
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void updateMeals(List<Dish> lDish) {

        int breakfast = 0;
        int lunch = 0;
        int dinner = 0;

        for (Dish each : lDish) {
            float multiplier = 1f;
            if (each.getDescription().toLowerCase().startsWith("gram")) {
                try {
                    multiplier = each.getQuantity() / Float.parseFloat(each.getGram());
                } catch (Exception e) {
                }
            } else {
                try {
                    multiplier = each.getQuantity() / Float.parseFloat(each.getMeasure());
                } catch (Exception e) {
                }
            }

            try {
            } catch (Exception e) {
            }

        }
    }

    private static class UserListViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvQuantity;
        public TextView tvCalories;
        public View view;

        public UserListViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.item_name);
            tvQuantity = (TextView) itemView.findViewById(R.id.quantity);
            tvCalories = (TextView) itemView.findViewById(R.id.calories);
        }
    }

}


