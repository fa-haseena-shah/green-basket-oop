package ClassPackage;
import java.io.*;
// abstraction
public abstract class UserClass {
    FileSystem fileSystem = new FileSystem("UserData.txt");
    // encapsulation
    private String UserID;
    private String Password;
    private String ConfirmPassword;
    private String UserName;
    private String Role;
    
    public UserClass() {} // default constructor
    
    // constructor overloading
    // constructor that initializes object by setting attributes, has all parameters (for registration) 
    public UserClass(String UserID, String Password, String UserName, String Role) {
        this.UserID = UserID;
        this.Password = Password;
        this.UserName = UserName;
        this.Role = Role;
    }
    
    // constructor used when login
    public UserClass(String UserID, String Password) {
        this.UserID = UserID;
        this.Password = Password;
    }

    // encapsulation - getters & setters provide controlled access to private variables
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    // abstraction
    public abstract boolean createAccount();
    
    // validates user login by checking inptus with stored credentials
    public boolean validateLogin()
    {
        try {
            String[] array = null; // null initially since there is nothing to store
            
            BufferedReader bufferedReader = fileSystem.readAFile(); 
            String user;

            // read each user record line till file ends
            while ((user = bufferedReader.readLine()) != null) 
            {              
                array = user.split("/"); // split the record with / (that is how we separated it during creation)
                
                // check matching ID and password is found in array 
                if (array[0].equals(getUserID()) && array[1].equals(getPassword())) 
                {
                    // populate remaining user details upon successful login
                    this.setUserID(array[0]);
                    this.setPassword(array[1]);
                    this.setUserName(array[2]);
                    this.setRole(array[3]);
                    return true; // successful login
                }
            }
        } 
        catch (IOException ex) // handle errors
        {
            System.err.println("Sorry, error occured when validating login attempt:"+ex);
        }   
        // login fails if no matching record found
        return false;
    }
}
