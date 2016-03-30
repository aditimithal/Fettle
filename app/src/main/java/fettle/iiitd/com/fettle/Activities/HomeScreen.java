package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        Button overview, profile, restraunt, graph, calendar, menu, signup, profileinfo;
        overview = (Button) findViewById(R.id.overviewbutton);
        profile = (Button) findViewById(R.id.profilebutton);
        restraunt = (Button) findViewById(R.id.restrauntbutton);
        graph = (Button) findViewById(R.id.graphbutton);
        calendar = (Button) findViewById(R.id.calendarbutton);
        menu = (Button) findViewById(R.id.menubutton);
        signup = (Button) findViewById(R.id.signup);
        profileinfo = (Button) findViewById(R.id.profileInfo);

        overview.setOnClickListener(this);
        profile.setOnClickListener(this);
        restraunt.setOnClickListener(this);
        graph.setOnClickListener(this);
        calendar.setOnClickListener(this);
        menu.setOnClickListener(this);
        signup.setOnClickListener(this);
        profileinfo.setOnClickListener(this);
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
                .build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withIcon(GoogleMaterial.Icon.gmd_settings).withName("Settings"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.overviewbutton) {
            Intent myIntent = new Intent(HomeScreen.this, DayOverview.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.profilebutton) {
            Intent myIntent = new Intent(HomeScreen.this, ProfileInput.class);
            startActivity(myIntent);

        } else if (v.getId() == R.id.restrauntbutton) {
            Intent myIntent = new Intent(HomeScreen.this, RestrauntList.class);
            startActivity(myIntent);

        } else if (v.getId() == R.id.graphbutton) {
            Intent myIntent = new Intent(HomeScreen.this, CaloriesGraph.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.calendarbutton) {
            Intent myIntent = new Intent(HomeScreen.this, CalendarActivity.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.menubutton) {
            Intent myIntent = new Intent(HomeScreen.this, RestrauntMenuList.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.signup) {
            Intent myIntent = new Intent(HomeScreen.this, SignUp.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.landing) {
            Intent myIntent = new Intent(HomeScreen.this, LandingActivity.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.profileInfo) {
            Intent myIntent = new Intent(HomeScreen.this, ProfileInfo.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.customCalendar) {
            Intent myIntent = new Intent(HomeScreen.this, CustomCalendar.class);
            startActivity(myIntent);
        }
    }

}
