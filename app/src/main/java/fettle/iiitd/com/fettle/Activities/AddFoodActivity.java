package fettle.iiitd.com.fettle.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fettle.iiitd.com.fettle.R;

public class AddFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        ArrayList<String> stringlist = new ArrayList<>();
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");
        stringlist.add("abc");

        GridLayoutManager lLayout = new GridLayoutManager(AddFoodActivity.this, 2);

        RecyclerView rView = (RecyclerView) findViewById(R.id.myList);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        CardArrayRecyclerViewAdapter rcAdapter = new CardArrayRecyclerViewAdapter(AddFoodActivity.this, stringlist);
        rView.setAdapter(rcAdapter);
    }

    public class CardArrayRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> //adapter for recyclerview to load all listvenue
    {

        private List<String> foods;
        private LayoutInflater inflater = null;

        public CardArrayRecyclerViewAdapter(Context context, List<String> venue) {
            this.foods = venue;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CardListViewHolder(inflater.inflate(R.layout.addfood_item_cell, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ((CardListViewHolder) holder).tvName.setText(foods.get(position));


            ((CardListViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() //open venuepage of particular foods
            {
                @Override
                public void onClick(View v) {
                    //listhotposition = position;
                    //Intent i = new Intent(manangrid.this, venuepaage.class);
                    //startActivity(i);
                }
            });

        }


        @Override
        public int getItemCount() {
            return foods.size();
        }

        private class CardListViewHolder extends RecyclerView.ViewHolder {
            public TextView tvName;
            public ImageView imageView;
            public View view;

            public LinearLayout linearLayout;

            public CardListViewHolder(View convertView) {
                super(convertView);
                view = convertView;
                imageView = (ImageView) convertView.findViewById(R.id.iv); //imageview of card
                tvName = (TextView) convertView.findViewById(R.id.foodName); //name of foods of card
                linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout); //Linearlayout of whole card to open venuepage
            }
        }

    }

}
