package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);


        Button overview, profile, restraunt, graph, calendar, menu,signup;
        overview = (Button) findViewById(R.id.overviewbutton);
        profile = (Button) findViewById(R.id.profilebutton);
        restraunt = (Button) findViewById(R.id.restrauntbutton);
        graph = (Button) findViewById(R.id.graphbutton);
        calendar = (Button) findViewById(R.id.calendarbutton);
        menu = (Button) findViewById(R.id.menubutton);
        signup = (Button) findViewById(R.id.signup);

        overview.setOnClickListener(this);
        profile.setOnClickListener(this);
        restraunt.setOnClickListener(this);
        graph.setOnClickListener(this);
        calendar.setOnClickListener(this);
        menu.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.overviewbutton) {
            Intent myIntent = new Intent(HomeScreen.this, DayOverview.class);
            startActivity(myIntent);
        } else if (v.getId() == R.id.profilebutton) {
            Intent myIntent = new Intent(HomeScreen.this, ProfileInformation.class);
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
        }
        else if (v.getId() == R.id.signup) {
            Intent myIntent = new Intent(HomeScreen.this, SignUp.class);
            startActivity(myIntent);
        }
    }
}
