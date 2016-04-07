package fettle.iiitd.com.fettle.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Manan on 31-03-2016.
 */
public class Utils {

    public static final String DAILY_CALORIE_KEY = "dailyCalorie";
    public static final String WALK_10_CALOIRES_KEY = "walkTen";
    public static final String RUN_10_CALOIRES_KEY = "runTen";
    public static final String PREF_NAME = "Pref";

    public static float getBmi(int heightInCm, int weight) {
        float height = heightInCm / 100f;
        float bmi = 0f;
        if (weight == 0)
            return 0;
        bmi = (weight * 1f) / (height * height * 1f);
        return bmi;
    }

    /**
     * Target Calories to target weight mapping
     *
     * @param age    age in years
     * @param weight weight in kg
     * @param height height in cm
     * @param sex    "m" or "f"
     * @return HashMap of target to calories required
     */
    public static HashMap<String, String> CalTarget(int age, int weight, int height, String sex) {
        String url = "http://www.calculator.net/calorie-calculator.html?ctype=metric&cage="
                + age + "&csex=" + sex + "&cheightm=" + height + "&ckg=" + weight;
        HashMap<String, String> CalTarget = new HashMap<String, String>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select("table").get(6);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                if (tds.text().contains("maintain your weight"))
                    CalTarget.put("maintain_weight", tds.get(1).text());
                else if (tds.text().contains("lose 0.5 kg"))
                    CalTarget.put("lose_0.5kg", tds.get(1).text());
                else if (tds.text().contains("lose 1 kg"))
                    CalTarget.put("lose_1kg", tds.get(1).text());
                else if (tds.text().contains("gain 0.5"))
                    CalTarget.put("gain_0.5kg", tds.get(1).text());
                else if (tds.text().contains("gain 1"))
                    CalTarget.put("gain_1kg", tds.get(1).text());
            }

            System.out.println(CalTarget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CalTarget;
    }

    /**
     * Convert Calories into Activities:<br>
     * walk: Walking, 3.0 mph, mod. pace, walking dog<br>
     * run: Running,general<br>
     * stairs: Walking, upstairs<br>
     * cycling: Bicycling, <10mph, leisure<br>
     *
     * @param weight weight in kg
     * @param energy energy in calories
     * @return HashMap with keys Walk, Stairs, Bicycle, Run and corresponding time
     */
    public static HashMap<String, String> CaloriesToActivity(int weight, int energy) {
        String url = "http://www.healthassist.net/calories/act-list.php";
        url = url + "?" + weight + "," + energy + "," + "false";
        HashMap<String, String> ActivityToTime = new HashMap<String, String>();
        String table_id = "sport";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements table = doc.select("table#" + table_id);

            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                if (tds.size() > 0 && tds.get(0).text().equals("Walking, 3.0 mph, mod. pace, walking dog"))
                    ActivityToTime.put("Walk", tds.get(1).text());
                else if (tds.size() > 0 && tds.get(0).text().equals("Running, general"))
                    ActivityToTime.put("Run", tds.get(1).text());
                else if (tds.size() > 0 && tds.get(0).text().equals("Walking, upstairs"))
                    ActivityToTime.put("Stairs", tds.get(1).text());
                else if (tds.size() > 0 && tds.get(0).text().equals("Bicycling, <10mph, leisure"))
                    ActivityToTime.put("Bicycle", tds.get(1).text());
            }
            System.out.println(ActivityToTime);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ActivityToTime;
    }

    public static void storePref(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void storePref(Activity activity, String key, int value) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getPref(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public static class BmiDate {
        public Date date;
        public float bmi;

        public BmiDate(Date date, float bmi) {
            this.date = date;
            this.bmi = bmi;
        }

        public BmiDate() {
        }

        @Override
        public String toString() {
            return "BmiDate{" +
                    "date=" + date +
                    ", bmi=" + bmi +
                    '}';
        }
    }

    public static class FoodCategory {
        public String name;

        public FoodCategory(String name) {
            this.name = name;
        }

        public FoodCategory() {
        }

        @Override
        public String toString() {
            return "FoodCategory{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
