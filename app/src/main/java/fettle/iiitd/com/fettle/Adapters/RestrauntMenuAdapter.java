package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fettle.iiitd.com.fettle.Classes.Menu;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 29/02/16.
 */
public class RestrauntMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_HEADER = 0;
    private final int VIEW_CARD = 1;
    private List<Menu> messages;
    private LayoutInflater inflater = null;
    private String restaurant;

    public RestrauntMenuAdapter(Context context, String restaurant, List<Menu> messages) {
        messages.add(0, new Menu());
        this.messages = messages;
        this.restaurant = restaurant;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_HEADER) {
            return new MenuHeaderViewHolder(inflater.inflate(R.layout.restraunt_menu_header, parent, false));
        } else {
            return new MenuItemViewHolder(inflater.inflate(R.layout.restraunt_menu_card, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == VIEW_HEADER) {

            ((MenuHeaderViewHolder) holder).tvRestrauntName.setText("");
            ((MenuHeaderViewHolder) holder).tvLocation.setText("");
            ((MenuHeaderViewHolder) holder).ratingBar.setRating(3);
        }
        if (getItemViewType(position) == VIEW_CARD) {
            ((MenuItemViewHolder) holder).tvDishName.setText("");
            ((MenuItemViewHolder) holder).tvCalories.setText("");
            ((MenuItemViewHolder) holder).tvPrice.setText("");
            ((MenuItemViewHolder) holder).tvRunningTime.setText("");
            ((MenuItemViewHolder) holder).tvWalkingTime.setText("");
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_CARD;
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
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
        public ImageView ivAskerPicture;
        public View view;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvDishName = (TextView) itemView.findViewById(R.id.dish_name);
            tvPrice = (TextView) itemView.findViewById(R.id.price);
            tvCalories = (TextView) itemView.findViewById(R.id.calories);
            tvWalkingTime = (TextView) itemView.findViewById(R.id.walking_time);
            tvRunningTime = (TextView) itemView.findViewById(R.id.running_time);
//            ivAskerPicture = (ImageView) itemView.findViewById(R.id.askerPicture);
        }
    }
}
