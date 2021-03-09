import Model.*;
import View.*;
import Controller.*;

public class Rent_It {
    
    /**
     * 
     * @param args User arguments when running the program
     */
    public static void main(String[] args) {
        final String customerFilepath = "./Database/Customer/CustomerList.csv";
        new Management();
        System.out.println("Customer database status:" + DatabaseTools.uniqueUsernameCheck(customerFilepath, "John"));
        System.out.println("Item database status:" + DatabaseTools.uniqueItemNamePerUser("John", "Chair (25)"));
    }
}
