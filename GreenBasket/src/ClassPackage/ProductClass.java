package ClassPackage;
import java.io.*;

// encapsulation
public class ProductClass {
    private String ItemID;
    private String ItemName;
    private String Category;
    private String Supplier;
    private double Price;
    private int StckQty; // optimal quantity that should be stocked each time
    private int AvlbQty; // quantity available during sales

    public ProductClass() {} // default constructor
    
    // parameterized constructor that initializes object attributes
    public ProductClass(String ItemID, String ItemName, String Category, String Supplier, double Price, int StckQty, int AvlbQty) {
        this.ItemID = ItemID;
        this.ItemName = ItemName;
        this.Category = Category;
        this.Supplier = Supplier;
        this.Price = Price;
        this.StckQty = StckQty;
        this.AvlbQty = StckQty; // initally availableqty = stockqty, sets automatically
    }

    // encapsulation - getters & setters provide controlled access to private variables
    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String Supplier) {
        this.Supplier = Supplier;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStckQty() {
        return StckQty;
    }

    public void setStckQty(int StckQty) {
        this.StckQty = StckQty;
    }

    public int getAvlbQty() {
        return AvlbQty;
    }

    public void setAvlbQty(int AvlbQty) {
        this.AvlbQty = AvlbQty;
    }
}
    
