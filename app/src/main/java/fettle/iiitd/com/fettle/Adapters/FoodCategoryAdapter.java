package fettle.iiitd.com.fettle.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.R;


public class FoodCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static LayoutInflater inflater = null;
    int i = 0;
    private Activity activity;

    public FoodCategoryAdapter(Activity a, ArrayList<?> d) {
        activity = a;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        View vi = viewHolder.itemView;
        ViewHolder holder;

        if (viewHolder.itemView == null) {
            vi = inflater.inflate(R.layout.list_item_course_stats_overview, null);
            holder = new ViewHolder(vi);
            holder.course = (TextView) vi.findViewById(R.id.firstLine);
            holder.code = (TextView) vi.findViewById(R.id.secondLine);
            holder.attendence = (TextView) vi.findViewById(R.id.attendence);
            holder.average = (TextView) vi.findViewById(R.id.average);
            holder.highest = (TextView) vi.findViewById(R.id.highest);
            holder.back = (LinearLayout) vi.findViewById(R.id.mainback);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (position == 0) {
            holder.course.setText("Bakery");
            holder.code.setText("Bakery");
            holder.average.setText("Bakery");
            holder.back.setBackgroundResource(R.drawable.bakery);
        } else if (position == 1) {
            holder.course.setText("Beverages");
            holder.code.setText("Beverages");
            holder.average.setText("Beverages");
            holder.back.setBackgroundResource(R.drawable.beverages);
        }

    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView course;
        public TextView code;
        public TextView attendence;
        public TextView average;
        public TextView highest;
        public LinearLayout back;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}