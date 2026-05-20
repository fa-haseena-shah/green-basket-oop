package ClassPackage;
import java.io.*;
// abstraction through interface which specified the methods & signuatres
public class InventoryClass implements InventoryInterface
{
    FileSystem fileSystem = new FileSystem("ProductInfo.txt"); // fileSystem that handles reading product and inventory data
    FileSystem alertFileSystem = new FileSystem("RestockAlerts.txt"); // fileSystem to handle storing restock alert item IDs separately
    private final int restockThreshold = 50; // threshold below which restocking is required
    
    @Override
    public String viewInventoryInfo()
    {
        String item, allItems = ""; // stores each line read from ProductInfo.txt | accumulates formatted inventory data
        String[] info = null; // array is declared null since there are no products initially
        BufferedReader bufferedReader = fileSystem.readAFile();
        
        try 
        {
            // read each line until null reached
            while((item=bufferedReader.readLine()) !=null) 
            {
                // split line using / delimiter
                info = item.split("/"); 
                // getting avlb qty from array, converting string to int and storing it in avlbQty variable
                int avlbQty = Integer.parseInt(info[6]); 
                // using ternary operator to check conditions and mark item status (out/low/available)
                String status = (avlbQty == 0) ? "Out" : (avlbQty <= restockThreshold) ? "Low" :"Available";
                allItems = allItems + info[0] + "\t" + info[1] + "\t" + info[5] + "\t" + info[6] + "\t" + status + "\n";
                // excluding unnecessary fields in table i.e. supplier, category, etc. since focus is on inventory level monitoring 
            }
        }
        
        catch(IOException ex)
        {
            System.err.println("An error occurred when showing inventory..."+ex);
        }
        return allItems;
    }
    
    // helper method to generateRestockAlert that prevents duplicate restock alert entries till previous ones are resolved
    private boolean check(String itemID)
    {
        BufferedReader alertReader = alertFileSystem.readAFile(); // reads RestockAlerts.txt
        boolean found=false;
        try 
        {
            String line;
            // read each line till null reached
            while ((line = alertReader.readLine()) != null)
            {
                // if item ID exists, mark found and break from loop
                if (line.equals(itemID))
                {
                    found=true;
                    break;
                }
            }
            alertReader.close();
        }
        
        catch (IOException ex)
        {
            System.err.println("Error checking alert file: " + ex);
        }
        // returning true/false indicates whether the item ID was found or not
        return found;
    }
    
    @Override
    public String generateRestockAlert(String itemID)
    {
        try {
            BufferedReader bufferedReader = fileSystem.readAFile(); // reading ProductInfo.txt
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split("/");
                if (itemID.equals(data[0]))
                {
                    int avlbQty = Integer.parseInt(data[6]); // get avlb qty from array and convert string to int
                    if (avlbQty <= restockThreshold) // compare with restockThreshold to prevent accidental alert gens
                    {
                        // prevent duplicate alerts using helper check() method
                        if(check(itemID))
                        {
                            return "Item has pending restock alert, please resolve that first!";
                        }
                        else
                        {
                            alertFileSystem.writeDataToFile(itemID); // write itemID into RestockAlerts.txt
                            return "Restock alert generated";
                        }
                            
                    }
                    else { 
                       return "Stock level sufficient, alert not generated"; // prevent alert generation and show a message instead
                    }
                }
            }
            bufferedReader.close();
        }
        
        catch (IOException ex)
        {
            return "Error reading inventory file";
        }
        return "Item ID not found";
    }
    
    @Override
    public String getAlert()
    {
        String result = ""; // accumulates alert display output
        try {
            BufferedReader alertReader = alertFileSystem.readAFile(); // reading RestockAlerts.txt to get item IDs
            String alertItemID;
            
            // loop through each line of itemID
            while ((alertItemID = alertReader.readLine()) != null) 
            {
                BufferedReader inventoryReader = fileSystem.readAFile(); // reading ProductInfo.txt (to get product details i.e. name, ID)
                String line;
                
                while ((line = inventoryReader.readLine()) != null)
                {
                    String[] info = line.split("/");
                    if (alertItemID.equals(info[0])) // match alert item ID with product item ID
                    {
                       // append itemID and itemName to result
                        result = result + info[0] + "\t" + info[1] + "\n";
                        break;
                    }
                }
                inventoryReader.close();
            }
            alertReader.close();
        }
        catch (IOException ex) {
            System.err.println("Error retrieving alerts: " + ex);
        }
        // return formatted result list of items requiring restock
        return result;
    }
}
