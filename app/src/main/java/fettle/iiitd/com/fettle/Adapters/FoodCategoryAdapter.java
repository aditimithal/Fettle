package fettle.iiitd.com.fettle.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fettle.iiitd.com.fettle.R;


public class FoodCategoryAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    int i = 0;
    private Activity activity;

    public FoodCategoryAdapter(Activity a, ArrayList<?> d) {
        activity = a;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return 2;
    }

//    public ArrayList<?> getData(){
//        return d;
//    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.list_item_course_stats_overview, null);
            holder = new ViewHolder();
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
        return vi;
    }

    public static class ViewHolder {
        public TextView course;
        public TextView code;
        public TextView attendence;
        public TextView average;
        public TextView highest;
        public LinearLayout back;
    }
}