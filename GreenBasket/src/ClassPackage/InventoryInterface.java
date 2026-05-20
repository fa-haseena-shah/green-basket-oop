package ClassPackage;
// abstraction as implementation complexity is hidden here
public interface InventoryInterface {
    String viewInventoryInfo();
    String generateRestockAlert(String itemID);
    String getAlert();
}
