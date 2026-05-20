package ClassPackage;
import java.io.*;
// inheritance
public class SalesAstClass extends UserClass { 
// inherits all attributes and methods from UserClass
    public SalesAstClass() {} // default constructor

    // parameterized constructors
    public SalesAstClass(String UserID, String Password, String UserName, String Role) {
        super(UserID, Password, UserName, Role);
    }

    public SalesAstClass(String UserID, String Password) {
        super(UserID, Password);
    }
    
    // polymorphism : sales assistants are not allowed to create accounts
    @Override
    public boolean createAccount()
    {
        return false; // prevents account creation
    }
}
