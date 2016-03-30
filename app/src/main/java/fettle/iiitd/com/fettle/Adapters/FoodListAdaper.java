package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

//        ((RestrauntViewHolder) holder).tvName.setText(messages.get(position).getString("name"));
//        ((RestrauntViewHolder) holder).tvRole.setVisibility(View.INVISIBLE);
//        ((RestrauntViewHolder) holder).tvLocation.setVisibility(View.INVISIBLE);
//        if (messages.get(position).getString("position") != null)
//            ((RestrauntViewHolder) holder).tvRole.setText(messages.get(position).getString("position"));
//        if (messages.get(position).getParseObject("constituency") != null)
//            ((RestrauntViewHolder) holder).tvLocation.setText(messages.get(position).getParseObject("constituency").getString("name"));
//        if (!usersInstalled.contains(messages.get(position).getObjectId())) {
//            ((RestrauntViewHolder) holder).tvName.setAlpha((float) .4);
//        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private static class UserListViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvRole;
        public TextView tvLocation;
        public ImageView ivAskerPicture;
        public View view;

        public UserListViewHolder(View itemView) {
            super(itemView);
            view = itemView;
//            tvName = (TextView) itemView.findViewById(R.id.tvName);
//            tvRole = (TextView) itemView.findViewById(R.id.tvRole);
//            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
//            ivAskerPicture = (ImageView) itemView.findViewById(R.id.askerPicture);
        }
    }
}


