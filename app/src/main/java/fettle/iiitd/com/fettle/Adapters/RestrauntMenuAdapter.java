package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
            return new UserListViewHolder(inflater.inflate(R.layout.restraunt_menu_header, parent, false));
        } else {
            return new UserListViewHolder(inflater.inflate(R.layout.restraunt_menu_card, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == VIEW_HEADER) {
            ((TextView) ((UserListViewHolder) holder).view.findViewById(R.id.restraunt_name)).setText(restaurant);
        } else {
            ((TextView) ((UserListViewHolder) holder).view.findViewById(R.id.dish)).setText(messages.get(position).getName());
        }

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
