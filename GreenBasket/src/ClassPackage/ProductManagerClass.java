package ClassPackage;
import java.io.*;

// abstraction : implements product management operations defined in interface
public class ProductManagerClass implements ProductManagerInterface { 
    FileSystem fileSystem = new FileSystem("ProductInfo.txt");
    public ProductManagerClass() {} // default constructor
    
    @Override
    public boolean addProduct(String ItemID, String ItemName, String Category, String Supplier, double Price, int StckQty, int AvlbQty)
    {
        // ensure file exists or is ready for writing
        if(!fileSystem.create_ANewFile())
        {
            String record = ItemID + "/" + ItemName + "/" + Category + "/" + Supplier + "/" + Price + "/"+ StckQty + "/" + AvlbQty; 
            // store values from product class, separated by delimiter /, into record variable
            return fileSystem.writeDataToFile(record); // writes that record variable into the file
        }
        return false;
    }
    
    @Override
    public String viewProducts()
    {
        String product, allProducts = "";
        String[] products = null; // array is declared null since there are no products initially
        
        BufferedReader bufferedReader = fileSystem.readAFile();
        
        try 
        {
            while((product=bufferedReader.readLine()) !=null)
            {
                products = product.split("/"); 
                // split the record with / delimiter (that is how we separated it during creation)
                allProducts = allProducts + products[0] + "\t" + products[1] + "\t" + products[2] + "\t" + products[3] + 
                             "\t" + products[4] + "\t" + products[5] + "\n"; 
                // add each of the array members into allProducts, continues till line read is null (ProductInfo.txt end)
                // available qty is excluded in the view(used in inventory monitoring only)
            }
        }
        
        catch(IOException ex)
        {
            System.err.println("An error occurred when searching for products..."+ex);
        }
        return allProducts;
    }
    
    @Override
    public String searchCategory(String Category)
    {
        String searchResult = ""; // empty search result first
        BufferedReader bufferedReader = fileSystem.readAFile();
        try
        {
            String ProductInfo;
            while ((ProductInfo=bufferedReader.readLine()) != null) 
            {
                String[] array= ProductInfo.split("/");
                if(Category.equals(array[2])) // check if the category selected by user exists (category is @ index 2)
                {
                    searchResult = searchResult + array[0] + "\t" + array[1] + "\t" + array[3] + 
                           "\t" + array[4] + "\t" + array[5] + "\n"; 
                   // add each of the array members into searchResult, continues till line read is null (ProductInfo.txt end)
                }
            }
        }
        catch(IOException exc)
        {
            System.err.println("Sorry an error occured when searching.."+exc);
        }
        return searchResult;
    }   
    
    @Override
    // this method is to simulate stock reductions for inventory monitoring purposes
    public boolean reduceStock(String ItemID, int redQty) {
        boolean success = false; // indicate successful reduction
        StringBuilder fileContent = new StringBuilder(); // store updated contents 
        if (redQty <= 0) 
        {
            return false; // prevent using invalid qty
        }  
        try {
            BufferedReader bufferedReader = fileSystem.readAFile();
            String ProductInfo;
            
            while ((ProductInfo = bufferedReader.readLine()) != null) {
                String[] array = ProductInfo.split("/"); // split each line using "/" as delimiter
                
                if (ItemID.equals(array[0].trim())) // check if matching itemID found
                {
                    int AvlbQty = Integer.parseInt(array[6]); // retrieve available qty of product at matching itemID, from txt file at index 6,
                                                             // it is stored as String so parse it to int and store it as AvlbQty variable
                    AvlbQty -= redQty; // reduce it by qty specified in method call
                    if (AvlbQty < 0) AvlbQty = 0; // safety check, if qty drops below 0, it is specified back to 0
                    
                    array[6] = String.valueOf(AvlbQty); // convert new qty to string and update into array
                    success = true; // successful operation
                }
                
                fileContent.append(String.join("/", array)).append("\n"); // rebuild file content with updated records
           }
            
            bufferedReader.close();
            if (success) 
            {
                fileSystem.overwriteFile(fileContent.toString()); // overwrite file if the target item was found and updated ONLY
            }
        } catch (IOException exc) {
            System.err.println("Error reducing stock: " + exc);
        }
        return success; // return if stock was successfully reduced or not
    }
}

