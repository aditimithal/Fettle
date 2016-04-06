package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.Activities.RestrauntMenuList;
import fettle.iiitd.com.fettle.Classes.Restraunt;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class RestrauntListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    Context context;
    private ArrayList<Restraunt> messages;
    private LayoutInflater inflater = null;

    public RestrauntListAdapter(Context context, ArrayList<Restraunt> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestrauntViewHolder(inflater.inflate(R.layout.restraunt_list_card, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((RestrauntViewHolder) holder).card.setOnClickListener(this);
//        ((RestrauntViewHolder) holder).CtvName.setText(messages.get(position).getString("name"));
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.restrauntCard) {
            Intent myIntent = new Intent(context, RestrauntMenuList.class);
            context.startActivity(myIntent);
        }

    }

    private static class RestrauntViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvRole;
        public TextView tvLocation;
        public ImageView ivAskerPicture;
        public CardView card;
        public View view;

        public RestrauntViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            card = (CardView) itemView.findViewById(R.id.restrauntCard);
//            tvName = (TextView) itemView.findViewById(R.id.tvName);
//            tvRole = (TextView) itemView.findViewById(R.id.tvRole);
//            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
//            ivAskerPicture = (ImageView) itemView.findViewById(R.id.askerPicture);
        }
    }
}

