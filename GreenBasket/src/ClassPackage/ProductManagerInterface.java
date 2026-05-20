package ClassPackage;
// abstraction : the details of implementation are hidden
public interface ProductManagerInterface {
    // adding new product
    boolean addProduct(String ItemID, String ItemName, String Category, 
            String Supplier, double Price, int StckQty, int AvlbQty);
    // viewing products
    String viewProducts();
    // searching product by category
    String searchCategory(String Category);
    // helper method to inventory monitoring, included here as it is relevant to product qty
    boolean reduceStock(String ItemID, int Qty);
}
