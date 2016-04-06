package fettle.iiitd.com.fettle.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fettle.iiitd.com.fettle.Activities.RestrauntList;
import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;


public class FoodCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static LayoutInflater inflater = null;
    int i = 0;
    private Activity activity;
    private List<Utils.FoodCategory> listCategories;

    public FoodCategoryAdapter(Activity a, List<Utils.FoodCategory> list) {
        activity = a;
        inflater = LayoutInflater.from(a.getApplicationContext());
        this.listCategories = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_course_stats_overview, parent, false));

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        ((ViewHolder) viewHolder).course.setText(listCategories.get(position).name);
        ((ViewHolder) viewHolder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RestrauntList.class);
                intent.putExtra("restaurant", listCategories.get(position).name);
                activity.startActivity(intent);
            }
        });
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView course;
        public LinearLayout back;
        public ImageView backgroundImage;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            course = (TextView) itemView.findViewById(R.id.firstLine);
            back = (LinearLayout) itemView.findViewById(R.id.mainback);
            backgroundImage = (ImageView) itemView.findViewById(R.id.backgroundImage);
        }
    }
}