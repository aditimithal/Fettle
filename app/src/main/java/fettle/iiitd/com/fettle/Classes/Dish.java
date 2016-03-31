package fettle.iiitd.com.fettle.Classes;

import com.parse.ParseObject;

/**
 * Created by Manan on 31-03-2016.
 */
public class Dish {

    ParseObject po;

    public Dish(ParseObject po) {
        this.po = po;
    }

    public String getCalories() {
        try {
            if (po == null)
                return "";
            return po.getString("cal");
        } catch (Exception e) {
            return "";
        }
    }

    public String getCarbs() {
        try {
            if (po == null)
                return "";
            return po.getString("carb");
        } catch (Exception e) {
            return "";
        }
    }

    public String getDescription() {
        try {

            if (po == null)
                return "";
            return po.getString("description");
        } catch (Exception e) {
            return "";
        }
    }

    public String getFat() {
        try {

            if (po == null)
                return "";
            return po.getString("fat");
        } catch (Exception e) {
            return "";
        }
    }

    public String getFiber() {
        try {

            if (po == null)
                return "";
            return po.getString("fiber");
        } catch (Exception e) {
            return "";
        }
    }

    public String getGram() {
        try {

            if (po == null)
                return "";
            return po.getString("gram");
        } catch (Exception e) {
            return "";
        }
    }

    public String getImage() {
        try {

            if (po == null)
                return "";
            return po.getString("image");
        } catch (Exception e) {
            return "";
        }
    }

    public String getMeasure() {
        try {

            if (po == null)
                return "";
            return po.getString("measure");
        } catch (Exception e) {
            return "";
        }
    }

    public String getProtein() {
        try {

            if (po == null)
                return "";
            return po.getString("protein");
        } catch (Exception e) {
            return "";
        }
    }

    public String getName() {
        try {

            if (po == null)
                return "";
            return po.getString("name");
        } catch (Exception e) {
            return "";
        }
    }

    public String getMeal() {
        try {

            if (po == null)
                return "";
            return po.getString("meal");
        } catch (Exception e) {
            return "";
        }
    }

    public int getQuantity() {
        try {

            if (po == null)
                return 0;
            return po.getInt("quantity");
        } catch (Exception e) {
            return 0;
        }
    }

}
