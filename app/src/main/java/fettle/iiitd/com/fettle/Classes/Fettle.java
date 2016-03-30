package fettle.iiitd.com.fettle.Classes;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Manan on 31-03-2016.
 */
public class Fettle extends Application {

    private static final String TAG = "Fettle app";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "519Tnm96h6t5TDGSzvNpu5MxD7AseqqEQBzk7thp", "Qd0v0YdJEIJEP9rh4QIcaI6CQSqvjbvxBs8brfMH");

    }
}
