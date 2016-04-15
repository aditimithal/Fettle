package fettle.iiitd.com.fettle.Classes;

/**
 * Created by danishgoel on 26/03/16.
 */
public class FoodItem {

    String foodName;
    int quantity;
    int calories;
    String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFoodName() {
        return Utils.toTitleCase(foodName);
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
