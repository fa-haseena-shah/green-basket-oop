package ClassPackage;
import java.io.*;

public class FileSystem {
    File file;
    private String fileName;
    private static String FILE_PATH = "C:\\Users\\hasee\\Downloads\\tgb\\"; 
    
    // constructor initializes fileName and ensures file exists
    public FileSystem(String fileName) 
    {
        this.fileName = fileName;
        create_ANewFile();
    }
    
    public boolean create_ANewFile() // create new file if it does not exist
    {
        try {
            file = new File(FILE_PATH + fileName); 
            if (file.createNewFile())
            {
                System.out.println("File created: " + file.getName());
                return true;
            }
            else
            {
            System.out.println("That file already exists, check it out: "+file.getName());
            return false;
            }
        } 
        catch(IOException exc)
        {
            System.out.println("Something went wrong when creating the file: " + exc);
            return false;
        }
    }
        
    public boolean writeDataToFile(String record) {
        try
        {
            file.createNewFile(); // check file exist
            FileWriter fileWriter = new FileWriter(file,true);  // true keeps all previous records
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); 

            bufferedWriter.write(record);
            bufferedWriter.newLine(); 
            bufferedWriter.close();
            fileWriter.close();
            
            return true;
        } 
        
        catch (IOException exc)
        {
            System.out.println("Something went wrong when writing to file:" + exc);
            return false;
        }
    }
        
    public BufferedReader readAFile()
    {
        if(!create_ANewFile()) // check file exists before reading and create it it does not
        {
            try
            {
                // open file for reading
                FileReader fileReader = new FileReader(file); 
                BufferedReader bufferedReader =  new BufferedReader(fileReader);
                return bufferedReader;
            }
            
            catch(FileNotFoundException exc)
            {
                System.out.println("Something went wrong when reading the file: " +exc);
            }
        }
        
        return null;
    }
        
    public boolean overwriteFile(String content) // overwrites file if content gets updated
    {
        try {
            FileWriter fileWriter = new FileWriter(file, false); // false = overwrite
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);  // write updated content into file
            bufferedWriter.close();
            fileWriter.close();
            return true;
            }
        catch (IOException exc) {
            System.err.println("Error overwriting file: " + exc);
            return false;
        }
    }
}
