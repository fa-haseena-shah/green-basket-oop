package ClassPackage;
import java.io.*;
// inheritance
public class StoreMngrClass extends UserClass { 
// inherits all attributes and methods from UserClass
    public StoreMngrClass(){} // default constructor

    // super constructors to ensure User class part of object is properly 
    // initialized before its own initialization
    // has all parameters for registration
    public StoreMngrClass(String UserID, String Password, String UserName, String Role) {
        super(UserID, Password, UserName, Role);
    }

    // has userID, password parameters for login
    public StoreMngrClass(String UserID, String Password) {
        super(UserID, Password);
    }
    
    // polymorphism : store managers may create user accounts
    @Override
    public boolean createAccount()
    {
        // ensure file exists
        if(!fileSystem.create_ANewFile())
        {
            // store extracted values which are obtained using getters, delimited by /, into record variable
            String record = getUserID() + "/" + getPassword() + "/" + getUserName() + "/" + getRole(); 
            return fileSystem.writeDataToFile(record); 
            // writes that record variable into the file via relevant file system method
        }
        return false;
    }
}
