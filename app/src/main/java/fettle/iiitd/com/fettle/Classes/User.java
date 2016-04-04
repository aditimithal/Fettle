package fettle.iiitd.com.fettle.Classes;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manan on 05-04-2016.
 */
public class User {

    private static final String TAG = "UserClass";

    public static float getBmi() {
        return Utils.getBmi(getHeight(), getWeight());
    }

    public static int getHeight() {
        return ParseUser.getCurrentUser().getInt("height");
    }

    public static int getWeight() {
        return ParseUser.getCurrentUser().getInt("weight");
    }

    public static String getName() {
        return ParseUser.getCurrentUser().getString("name");
    }

    public static String getEmail() {
        return ParseUser.getCurrentUser().getUsername();
    }

    public static List<Utils.BmiDate> getPastBmis() {
        List<Utils.BmiDate> bmis = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Track");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.orderByAscending("createdAt");
        List<ParseObject> lPo = new ArrayList<>();
        try {
            lPo = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ParseObject po : lPo) {
            try {
                bmis.add(new Utils.BmiDate(po.getCreatedAt(), Utils.getBmi(po.getInt("height"), po.getInt("weight"))));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return bmis;
    }


}
