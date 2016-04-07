package fettle.iiitd.com.fettle.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

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

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setNavigationDrawer(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button overview, profile, restraunt, graph, calendar, menu, signup, profileinfo, dialog, newdata;
        overview = (Button) findViewById(R.id.overviewbutton);

        graph = (Button) findViewById(R.id.graphbutton);
        calendar = (Button) findViewById(R.id.calendarbutton);

        signup = (Button) findViewById(R.id.signup);
        dialog = (Button) findViewById(R.id.dialog);
        newdata = (Button) findViewById(R.id.newdata);


        overview.setOnClickListener(this);

        graph.setOnClickListener(this);
        calendar.setOnClickListener(this);

        signup.setOnClickListener(this);
        dialog.setOnClickListener(this);
        newdata.setOnClickListener(this);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                        new PrimaryDrawerItem().withName("Nutritional Chart").withIcon(GoogleMaterial.Icon.gmd_chart_donut).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("My Diary").withIcon(GoogleMaterial.Icon.gmd_book).withIdentifier(1).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Rate Us").withIcon(GoogleMaterial.Icon.gmd_star).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Report").withIcon(GoogleMaterial.Icon.gmd_block).withIdentifier(1).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (position == 4) {
                            Intent myIntent = new Intent(HomeScreen.this, CustomCalendar.class);
                            startActivity(myIntent);
                        } else if (position == 5) {
                            Intent myIntent = new Intent(HomeScreen.this, ProfileInfo.class);
                            startActivity(myIntent);
                        }

                        Toast.makeText(HomeScreen.this, "" + position, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withIcon(GoogleMaterial.Icon.gmd_settings).withName("Settings"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.overviewbutton) {
            Intent myIntent = new Intent(HomeScreen.this, DayOverview.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.graphbutton) {
            Intent myIntent = new Intent(HomeScreen.this, CaloriesGraph.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.calendarbutton) {
            Intent myIntent = new Intent(HomeScreen.this, CalendarActivity.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.signup) {
            Intent myIntent = new Intent(HomeScreen.this, SignUp.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.newdata) {
            Intent myIntent = new Intent(HomeScreen.this, NewItem.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.dialog) {
            // custom dialog
            final Dialog dialog = new Dialog(HomeScreen.this);
            dialog.setContentView(R.layout.quant_dialog);
            dialog.setTitle("Add new Item");

            NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
            numberPicker.setMaxValue(100);
            numberPicker.setMinValue(1);
            dialog.show();

            NumberPicker numberPicker1 = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
            numberPicker1.setMinValue(0);
            numberPicker1.setMaxValue(1);
            numberPicker1.setDisplayedValues(new String[]{"Grams", "Cups"});

            Button discard, add;

            discard = (Button) dialog.findViewById(R.id.discard);
            discard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            add = (Button) dialog.findViewById(R.id.add);
        }
    }

}
