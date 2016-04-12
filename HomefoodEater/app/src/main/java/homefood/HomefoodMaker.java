package homefood;
import java.util.ArrayList;
import java.util.UUID;

public class HomefoodMaker {
    private String Email;
    private String shopName;
    private ArrayList<Food> menu;
    private UUID theHomefoodMakerID;

    public HomefoodMaker(String Email, String shopName){
        this.Email = Email;
        this.shopName = shopName;
        this.theHomefoodMakerID = UUID.randomUUID();
        this.menu = new ArrayList<Food>();
    }

    public HomefoodMaker(){
        this.Email = new String();
        this.shopName = new String();
        this.theHomefoodMakerID = UUID.randomUUID();
        this.menu = new ArrayList<Food>();
    }

    public UUID getTheHomefoodMakerID() {
        return theHomefoodMakerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Food> menu) {
        this.menu = menu;
    }

    public void addFoodToMenu(Food food){
        menu.add(food);
    }

}

