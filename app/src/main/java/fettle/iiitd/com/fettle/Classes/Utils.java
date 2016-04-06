package fettle.iiitd.com.fettle.Classes;

import java.util.Date;

/**
 * Created by Manan on 31-03-2016.
 */
public class Utils {

    public static float getBmi(int heightInCm, int weight) {
        float height = heightInCm / 100f;
        float bmi = 0f;
        if (weight == 0)
            return 0;
        bmi = (weight * 1f) / (height * height * 1f);
        return bmi;
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
