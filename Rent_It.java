import View.*;
import Controller.*;

public class Rent_It {
    public static void main(String[] args) {
        final String customerFilepath = "./Database/Customer/CustomerList.csv";
        new Management();
        System.out.println("Customer database status:" + DatabaseTools.uniqueUsernameCheck(customerFilepath, "John"));
        System.out.println("Item database status:" + DatabaseTools.uniqueItemNamePerUser("John", "Chair (25)"));
    }
}
