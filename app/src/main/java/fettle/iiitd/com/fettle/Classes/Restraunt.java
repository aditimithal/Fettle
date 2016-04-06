package fettle.iiitd.com.fettle.Classes;

/**
 * Created by danishgoel on 24/03/16.
 */
public class Restraunt {

    String name;
    int stars;
    int minOrder;
    int deliveryTime;

    public Restraunt() {
    }

    public Restraunt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Restraunt{" +
                "name='" + name + '\'' +
                '}';
    }
}
