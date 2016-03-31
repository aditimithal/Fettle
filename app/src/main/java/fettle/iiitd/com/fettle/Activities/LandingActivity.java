package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fettle.iiitd.com.fettle.Classes.Dish;
import fettle.iiitd.com.fettle.Fragments.CardIntakeFragment;
import fettle.iiitd.com.fettle.Fragments.CardIntakeNutrientFragment;
import fettle.iiitd.com.fettle.Fragments.CardTrackerFragment;
import fettle.iiitd.com.fettle.Fragments.CardTrackerWeekFragment;
import fettle.iiitd.com.fettle.R;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int NUM_PAGES1 = 2;
    private static final int NUM_PAGES2 = 2;
    public static boolean updateData = false;
    private ViewPager mPager1;
    private PagerAdapter mPagerAdapter1;
    private ViewPager mPager2;
    private PagerAdapter mPagerAdapter2;
    private Fragment fragment1, fragment2;
    private CardIntakeFragment fragment3;
    private CardIntakeNutrientFragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        FloatingActionButton order = (FloatingActionButton) findViewById(R.id.fab);
//        order.setOnClickListener(this);

        mPager1 = (ViewPager) findViewById(R.id.pager1);
        mPagerAdapter1 = new ScreenSlidePagerAdapter1(getSupportFragmentManager());
        mPager1.setAdapter(mPagerAdapter1);

        CirclePageIndicator indicator1 = (CirclePageIndicator) findViewById(R.id.indicator1);
        indicator1.setViewPager(mPager1);

        mPager2 = (ViewPager) findViewById(R.id.pager2);
        mPagerAdapter2 = new ScreenSlidePagerAdapter2(getSupportFragmentManager());
        mPager2.setAdapter(mPagerAdapter2);

        CirclePageIndicator indicator2 = (CirclePageIndicator) findViewById(R.id.indicator2);
        indicator2.setViewPager(mPager2);

        setNavigationDrawer(toolbar);

        ((TextView) findViewById(R.id.tvHeight)).setText(ParseUser.getCurrentUser().getInt("height") + "cm");
        ((TextView) findViewById(R.id.tvWeight)).setText(ParseUser.getCurrentUser().getInt("weight") + "kg");

        //for testing new screens
        /*Intent myIntent = new Intent(LandingActivity.this, HomeScreen.class);
        startActivity(myIntent);*/

        new BGload().execute();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fab) {
            Intent myIntent = new Intent(LandingActivity.this, FoodTypeSelection.class);
            startActivity(myIntent);
        }
        if (v.getId() == R.id.fab1) {
            Intent myIntent = new Intent(LandingActivity.this, RestrauntList.class);
            startActivity(myIntent);

        }
        if (v.getId() == R.id.fab2) {
            Intent myIntent = new Intent(LandingActivity.this, AddFoodActivity.class);
            startActivity(myIntent);

        }

    }


    public void setNavigationDrawer(Toolbar toolbar) {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_bg)
                .addProfiles(
                        new ProfileDrawerItem().withName("Fettle Panda").withEmail("fettlepanda@gmail.com").withIcon(getResources().getDrawable(R.drawable.female_avatar))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(1).withSelectable(false),
                        new DividerDrawerItem(),
                        new ExpandableDrawerItem().withName("My Profile").withIcon(GoogleMaterial.Icon.gmd_account).withIdentifier(19).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("My Orders").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_shopping_cart).withIdentifier(2000),
                                new SecondaryDrawerItem().withName("My Statistics").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_chart).withIdentifier(2001)
                        ),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Rate Us").withIcon(GoogleMaterial.Icon.gmd_star).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Report").withIcon(GoogleMaterial.Icon.gmd_block).withIdentifier(1).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (position == 4) {
                            Intent myIntent = new Intent(LandingActivity.this, CustomCalendar.class);
                            startActivity(myIntent);
                        } else if (position == 5) {
                            Intent myIntent = new Intent(LandingActivity.this, ProfileInfo.class);
                            startActivity(myIntent);
                        }
//                        Toast.makeText(LandingActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withIcon(GoogleMaterial.Icon.gmd_settings).withName("Settings"));
    }

    public void updateMeals(List<Dish> lDish) {

        int breakfast = 0;
        int lunch = 0;
        int dinner = 0;
        int fiber = 0;
        int fats = 0;
        int carbs = 0;
        int proteins = 0;

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

            if (each.getMeal().equals("Breakfast")) {
                try {
                    breakfast += Float.parseFloat(each.getCalories()) * multiplier;
                } catch (Exception e) {
                }
            } else if (each.getMeal().equals("Lunch")) {
                try {
                    lunch += Float.parseFloat(each.getCalories()) * multiplier;
                } catch (Exception e) {
                }
            } else if (each.getMeal().equals("Dinner")) {
                try {
                    dinner += Float.parseFloat(each.getCalories()) * multiplier;
                } catch (Exception e) {
                }
            }

            try {
                fiber += Float.parseFloat(each.getFiber()) * multiplier;
            } catch (Exception e) {
            }
            try {
                fats += Float.parseFloat(each.getFat()) * multiplier;
            } catch (Exception e) {
            }
            try {
                carbs += Float.parseFloat(each.getCarbs()) * multiplier;
            } catch (Exception e) {
            }
            try {
                proteins += Float.parseFloat(each.getProtein()) * multiplier;
            } catch (Exception e) {
            }

        }

        fragment3.updateCalories(breakfast, lunch, dinner, 2000);
        fragment4.updateNutrients(fiber, fats, carbs, proteins);
    }

    public void downloadData(boolean update) {
        downloadData(update, new ArrayList<Dish>());
    }

    public void downloadData(boolean update, List<Dish> dishes) {
        List<ParseObject> lPo = new ArrayList<>();
        List<Dish> lDish = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date startDate = calendar.getTime();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("FoodIntake");
        parseQuery.whereEqualTo("user", ParseUser.getCurrentUser());
        parseQuery.whereGreaterThan("createdAt", startDate);
        try {
            lPo = parseQuery.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ParseObject each : lPo) {
            lDish.add(new Dish(each));
        }
        if (update)
            updateMeals(lDish);

        dishes = lDish;
    }

    @Override
    protected void onResume() {
        if (updateData) {
            updateData = false;
            downloadData(true);
        }
        super.onResume();
    }

    private class ScreenSlidePagerAdapter1 extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                fragment1 = new CardTrackerFragment();
                return fragment1;
            } else {
                fragment2 = new CardTrackerWeekFragment();
                return fragment2;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES1;
        }
    }

    private class ScreenSlidePagerAdapter2 extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                fragment3 = new CardIntakeFragment();
                return fragment3;
            } else {
                fragment4 = new CardIntakeNutrientFragment();
                return fragment4;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES2;
        }
    }

    public class BGload extends AsyncTask<Void, Void, Void> {

        private List<Dish> dishes;

        @Override
        protected Void doInBackground(Void... params) {
            downloadData(false, dishes);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateMeals(dishes);
            super.onPostExecute(aVoid);
        }
    }

}
