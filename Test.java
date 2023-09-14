import java.util.ArrayList;

/*
3/26/23
Final Project
Test.java
Contains unit tests of Inventory.java
*/ 

/**
 * A class which tests the {@link Inventory} and {@link File} methods
 * @author TramHammer
 * @version 1.0
 */
public class Test {
    /**
     * The total tests completed
     */
    static int totalTests = 0;
    /**
     * The total tests that failed
     */
    static int totalFailedTests = 0;
    /**
     * This method tests the {@link Inventory} class methods
     * @see Inventory#checkReorderCount(int, int)
     * @see Inventory#editCount(int, int)
     * @see Inventory#Inventory(String, String, int, int)
     * @see Inventory#Inventory()
     * @see Inventory#getName()
     * @see Inventory#getCount()
     * @see Inventory#getDesc()
     * @see Inventory#getLowestCount()
     * @see Inventory#setCount(int)
     * @see Inventory#setDesc(String)
     * @see Inventory#setLowestCount(int)
     * @see Inventory#setName(String)
     */
    static void testInventory() {
        int reOrder = Inventory.checkReorderCount(10, 5);
        int editCount = Inventory.editCount(10, 0);
        Inventory blankItem = new Inventory();
        Inventory checkBlankItem = new Inventory("Unknown", "n/a", 0, -1);
        totalTests++;
        if ((!blankItem.getName().equals(checkBlankItem.getName())) || (!blankItem.getDesc().equals(checkBlankItem.getDesc())) || (blankItem.getCount() != checkBlankItem.getCount()) || (blankItem.getLowestCount() != checkBlankItem.getLowestCount())) {
            totalFailedTests++;
            System.out.println("Inventory construct failed to create default values with the values: Unknown, n/a, 0, -1");
        }
        blankItem = new Inventory("CRAYOLA 24PK CRAYONS ASSRT", "Crayons 24 pack basic color", 20, 2);
        totalTests++;
        if (!blankItem.getName().equals("CRAYOLA 24PK CRAYONS ASSRT")) {
            totalFailedTests++;
            System.out.println("Inventory.getName() failed, expected CRAYOLA 24PK CRAYONS ASSRT, did not return, instead: " + blankItem.getName());   
        }
        totalTests++;
        if (!blankItem.getDesc().equals("Crayons 24 pack basic color")) {
            totalFailedTests++;
            System.out.println("Inventory.getDesc() failed, expected Crayons 24 pack basic color, did not return, instead: " + blankItem.getDesc());   
        }
        totalTests++;
        if (blankItem.getCount() != 20) {
            totalFailedTests++;
            System.out.println("Inventory.getCount() failed, expected 20, did not return, instead: " + blankItem.getCount());   
        }
        totalTests++;
        if (blankItem.getLowestCount() != 2) {
            totalFailedTests++;
            System.out.println("Inventory.getLowestCount() failed, expected 2, did not return, instead: " + blankItem.getCount());   
        }


        totalTests++;
        if (editCount != 10) {
            totalFailedTests++;
            System.out.println("Inventory.editCount failed, set current count 10 and added 0, did not return 10, instead: " + editCount);
        }
        totalTests++;
        editCount = Inventory.editCount(10, -12);
        if (editCount != 10) {
            totalFailedTests++;
            System.out.println("Inventory.editCount failed, set current count 10 and subtracted 12, did not return 10, instead: " + editCount);
        }
        totalTests++;
        editCount = Inventory.editCount(0, 12);
        if (editCount != 12) {
            totalFailedTests++;
            System.out.println("Inventory.editCount failed, set current count 0 and added 12, did not return 12, instead: " + editCount);
        }
        totalTests++;
        if (reOrder != 0) {
            totalFailedTests++;
            System.out.println("Inventory.checkReorderCount failed, set current count 10 and set lowest count to 5, did not return 0, instead: " + editCount);
        }
        reOrder = Inventory.checkReorderCount(10, 15);
        totalTests++;
        if (reOrder != 5) {
            totalFailedTests++;
            System.out.println("Inventory.checkReorderCount failed, set current count 10 and set lowest count to 15, did not return 5, instead: " + editCount);
        }
    }
    /**
     * This method tests the {@link File} class methods
     * @see File#items
     * @see File#File()
     * @see File#read()
     * @see File#read(String)
     * @see File#read(int)
     * @see File#write(ArrayList)
     */
    static void testFile() {
        File file = new File();
        file.write(file.items); 
        ArrayList<Inventory> initalRead = file.read(); 
        totalTests++;
        if (initalRead.size() != 5) {
            totalFailedTests++;
            System.out.println("File.read did not return the ArrayList<Inventory> items, current length: " + initalRead.size());
        }
        ArrayList<Inventory> newRecords = new ArrayList<Inventory>();
        int emptyWrite = file.write(newRecords);
        totalTests++;
        if (emptyWrite != -1) {
            totalFailedTests++;
            System.out.println("File.write failed to write empty array, total writes was: " + emptyWrite);
        }

        Inventory item1 = new Inventory("KINGSTON 256 GB NVME SSD", "3D NAND FLASH SSD", 2, 3);
        Inventory item2 = new Inventory("CRUCIAL 8GB DDR4 3200 MHZ RAM", "RAM", 10, 2);
        newRecords.add(item1);
        newRecords.add(item2);
        
        totalTests++;
        int twoItemWrite = file.write(newRecords);
        if (twoItemWrite != 2) {
            totalFailedTests++;
            System.out.println("File.write failed to write two items, total writes was: " + twoItemWrite);
        }
        ArrayList<Inventory> readRecords = file.read();

        
        totalTests++;
        if (readRecords.size() != 2){
            totalFailedTests++;
            System.out.println("File.read failed, expected size 2, actual size: " + readRecords.size());
        }
        totalTests++;
        Inventory readItem1 = file.read(0);
        if ((!readItem1.getName().equals(item1.getName())) || (!readItem1.getDesc().equals(item1.getDesc())) || (readItem1.getCount() != item1.getCount()) || (readItem1.getLowestCount() != item1.getLowestCount())) {
            totalFailedTests++;
            System.out.println("File.read failed, expected Object Inventory item1");
        }
        Inventory readItem2 = file.read(1);
        totalTests++;
        if ((!readItem2.getName().equals(item2.getName())) || (!readItem2.getDesc().equals(item2.getDesc())) || (readItem2.getCount() != item2.getCount()) || (readItem2.getLowestCount() != item2.getLowestCount())) {
            totalFailedTests++;
            System.out.println("File.read failed, expected Object Inventory item2");
        }
        totalTests++;
        Inventory readItemName1 = file.read("KINGSTON 256 GB NVME SSD");
        if ((!readItemName1.getName().equals(item1.getName())) || (!readItemName1.getDesc().equals(item1.getDesc())) || (readItemName1.getCount() != item1.getCount()) || (readItemName1.getLowestCount() != item1.getLowestCount())) {
            totalFailedTests++;
            System.out.println("File.read failed, expected return Object Inventory item1 with name KINGSTON 256 GB NVME SSD");
        }
        totalTests++;
        Inventory readItemName2 = file.read("CRUCIAL 8GB DDR4 3200 MHZ RAM");
        if((!readItemName2.getName().equals(item2.getName())) || (!readItemName2.getDesc().equals(item2.getDesc())) || (readItemName2.getCount() != item2.getCount()) || (readItemName2.getLowestCount() != item2.getLowestCount())) {
            totalFailedTests++;
            System.out.println("File.read failed, expected return Object Inventory item1 with name CRUCIAL 8GB DDR4 3200 MHZ RAM");
        }

    }
    /**
     * Calls {@link Test#testFile()} and {@link Test#testInventory()} and prints the tests passed and failed.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        testInventory();
        testFile();
        System.out.println("Out of " + totalTests + " tests, " + totalFailedTests + " tests failed.");
    }

}
