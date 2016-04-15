package fettle.iiitd.com.fettle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import fettle.iiitd.com.fettle.Activities.RestrauntMenuList;
import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 29/02/16.
 */
public class AddedFoodOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ParseObject> messages;
    private LayoutInflater inflater = null;

    private Context context;


    public AddedFoodOrderAdapter(Context context, List<ParseObject> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new MenuItemViewHolder(inflater.inflate(R.layout.food_added, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        ((MenuItemViewHolder) holder).tvDishName.setText(messages.get(position).getString("name"));
        ((MenuItemViewHolder) holder).check.setChecked(true);
        ((MenuItemViewHolder) holder).check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox c = (CheckBox) v;
                if (c.isChecked()) {
                    RestrauntMenuList.addedFood.add(messages.get(position));
                } else {
                    RestrauntMenuList.addedFood.remove(messages.get(position));
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        Log.d("addedfoodorder size", messages.size() + "");
        return messages.size();
    }


    private static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDishName;
        public CheckBox check;

        public View view;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvDishName = (TextView) itemView.findViewById(R.id.food_item);
            check = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }
}
