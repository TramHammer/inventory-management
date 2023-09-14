 /*
2/28/23
Final Project
Main.java
Contains all the methods and interactions for managing inventory.
*/ 
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class which contains all the methods the user interacts with
 * @author TramHammer
 * @version 1.0
 */
public class Main {
    static File file = new File();
    /**
     * This method edits the count of a Inventory Object. Initally reads the {@link File#read()} and also reads for <code>item</code> using {@link File#read(int)} to make sure that the item is a valid item in the {@link File#items} array. 
     * Given the parameters <code>item</code> and <code>count</code> it will set a temporary Inventory item with the values of the Object it found and search for the object in the ArrayList. 
     * If there is a match it will set that index with the temporary Inventory Object.
     * This method prints out the New Count and the Item name.
     * If there is no item that is found it will print "No item found".
     * @param item the index of the item based on the indexes provided by {@link Main#invCountInquiry()}
     * @param count the count to add to the item. Negative to remove, positive to add
     * @see File#read(int)
     * @see File#read()
     * @see Inventory#getName()
     * @see Inventory#setCount(int)
     * @see Inventory#editCount(int, int)
     * @see Inventory#getCount()
     * @see Inventory#getName()
     */
    public static void editCounts(int item, int count) {
        ArrayList<Inventory> Read = file.read();
        Inventory j = file.read(item);
        if (!j.getName().equals("Unknown")) { 
            j.setCount(Inventory.editCount(file.read(item).getCount(), count));
            for (int i = 0; i < Read.size(); i++) {
                if (Read.get(i).getName().equals(j.getName())) {
                    Read.set(i, j);
                }
            }
            file.write(Read);
            System.out.println("New Count\nItem Name - Counts");
            System.out.printf("%s - %d\n", file.read(item).getName(), file.read(item).getCount());
        } else {
            System.out.println("No item found");
        }
    }
    /**
     * This method prints out the Inventory attributes of the items that need to be re-ordered. 
     * The method calls {@link File#read()} and loops through each item in the ArrayList it returns calling {@link Inventory#checkReorderCount(int, int)} and checking if the value is greater than 0.
     * If it is greater than 0, it will print out the item attributes and the count required to get it back above or equal to {@link Inventory.lowestCount}
     * @see Inventory#checkReorderCount(int, int)
     * @see File#read()
     * @see Inventory#getCount()
     * @see Inventory#getDesc()
     * @see Inventory#getLowestCount()
     * @see Inventory#getName()
     */
    public static void orderInquiry() {

        ArrayList<Inventory> read = file.read();

        System.out.println("Re-order List\nItem Name - Counts - Lowest Order Count - Description - Current Pending Order Quantity");
        for (int i = 0; i < read.size();i++) {
            if (Inventory.checkReorderCount(read.get(i).getCount(), read.get(i).getLowestCount()) > 0) {
                System.out.printf("%s - %d", read.get(i).getName(), read.get(i).getCount());
                System.out.printf(" - %d - %s - ", read.get(i).getLowestCount(), read.get(i).getDesc());
                System.out.println(Inventory.checkReorderCount(read.get(i).getCount(), read.get(i).getLowestCount()));
            }
        }
    }
    /**
     * This method prints out the Inventory attributes of the item specified at the <code>index</code>
     * @param item the index of the item based on the indexes provided by {@link Main#invCountInquiry()}
     * @see File#read(int)
     * @see Inventory#getCount()
     * @see Inventory#getDesc()
     * @see Inventory#getLowestCount()
     * @see Inventory#getName() 
     */
    public static void itemInquiry(int item) {

        System.out.println("Item information\nItem Name - Counts - Order Count - Description");
        System.out.printf("%s - %d", file.read(item).getName(), file.read(item).getCount());
        System.out.printf(" - %d - %s\n", file.read(item).getLowestCount(), file.read(item).getDesc());
    }
    /**
     * This method prints out the Inventory attributes of every item in the ArrayList {@link File#fileName}
     * @see File#read()
     * @see Inventory#getCount()
     * @see Inventory#getDesc()
     * @see Inventory#getLowestCount()
     * @see Inventory#getName() 
     */
    public static void invCountInquiry() {

        ArrayList<Inventory> read = file.read();
        System.out.println("Inventory list\nIndex - Item Name - Counts - Order Count - Description");
        for (int i = 0; i < read.size();i++) {
            System.out.printf("%d - ", i);
            System.out.printf("%s - %d", read.get(i).getName(), read.get(i).getCount());
            System.out.printf(" - %d - %s\n", read.get(i).getLowestCount(), read.get(i).getDesc());
        }
    }
    /**
     * This method removes, adds, and edits the attributes of a Inventory Object
     * When the </code>content</code> Array's length is 1, it removes the item specified at the index of 0 in the Array. 
     * <ul>
     * <li>Parses the 0th index of the <code>content</code> Array as a Integer and {@link File#read(int)} to check if the index exists
     * <li>Reads the {@link File#fileName} with the {@link File#read()} into a ArrayList with Inventory Objects
     * <li>If the Inventory item does not match the Inventory Object that we are to remove we add it to a new ArrayList <code>toWrite</code>
     * <li>If it does match, then it removes it at index in {@link File#items}
     * <li>Calls the {@link File#write(ArrayList)} on <code>toWrite</code>
     * </ul>
     * When the <code>content</code> Array's length is greater than 1 and <code>func</code> is <code>false</code>
     * <ul>
     * <li>Adds the values of the Array <code>content</code> into a Inventory Object which is added to {@link File#items}
     * <li>Writes the ArrayList {@link File#items} while setting it to the int <code>writes</code>
     * <li>If <code>writes</code> is gretaer than 0, it prints out it is successful, else it prints out the process failed.
     * </ul>
     * When <code>content</code> Array's length is greater than 1 and <code>func</code> is <code>true</code>
     * <ul>
     * <li>Parses the 0th index of the <code>content</code> Array as a Integer and {@link File#read(int)} to check if the index exists
     * <li>Reads the {@link File#fileName} with the {@link File#read()} into a ArrayList with Inventory Objects
     * <li>If the Inventory item matches the Inventory Object we set it as the temporary Inventory Object <code>j</code>
     * 
     * @param content is a Array structured as (String, String, int, int) as (name, desc, count, lowestCount)
     * @param func to write or to edit
     * @see File#read(int)
     * @see File#read()
     * @see Inventory#getCount()
     * @see Inventory#getDesc()
     * @see Inventory#getLowestCount()
     * @see Inventory#getName()
     */
    public static void itemModify(String[] content, boolean func)  {//if (modifer.equals(...) == 1)statement add, edit
        if (content.length == 1) { //Removes item
            int item = Integer.parseInt(content[0]);
            ArrayList<Inventory> Read = file.read();
            Inventory j = file.read(item);
            ArrayList<Inventory> toWrite = new ArrayList<Inventory>();
            if (!j.getName().equals("Unknown")) {
                for (int i = 0; i < Read.size(); i++) {
                    if (!Read.get(i).getName().equals(j.getName())) {
                        toWrite.add(Read.get(i));
                    } else {
                        file.items.remove(i);
                    }

                }
                file.write(toWrite);
            }
            
        } else if (content.length > 1) { //Writes item
            if (func == false) {
                int writes; 
                    file.items.add(new Inventory(content[0].toString(), content[1].toString(), 0, Integer.valueOf(content[2])));
                    writes = file.write(file.items);

                if (writes > 0) {
                    System.out.println("Wrote to " + file.fileName + " successfully with writes: " + writes);
                } else {
                    System.out.println("Failed to write to " + file.fileName);
                }
            } else {//Edit item
                int item = Integer.parseInt(content[0]);
                ArrayList<Inventory> Read = file.read();
                Inventory j = file.read(item);
                if (!j.getName().equals("Unknown")) {
                    j.setLowestCount(Integer.parseInt(content[2]));
                    j.setDesc(content[1].toString());
                    for (int i = 0; i < Read.size(); i++) {
                        if (Read.get(i).getName().equals(j.getName())) {
                            Read.set(i, j);
                        }
                    }
                    file.write(Read);

                }
                System.out.println("\nNew Item information\nItem Name - Counts - Order Count - Description");
                System.out.printf("%s - %d", file.read(item).getName(), file.read(item).getCount());
                System.out.printf(" - %d - %s\n", file.read(item).getLowestCount(), file.read(item).getDesc());
            }
        }
    }
    /**
     * Prints out the prompt for the user to understand the options
     * @see Main#main(String[])
     */
    public static void printOptions() {
        System.out.println("\nInventory Management\n----------------\n  1) Add item\n  2) Remove item\n  3) Edit item\n  4) Edit Counts\n  5) Print items with low counts\n  6) Request item information\n  7) Print report of inventory\n  8) Quit");
    }

    /**
     * The main function where all functions of {@link Main} are called.
     * @param args command line arguments
     * @exception IOException when an interruption or failure occurs when input or ouput
     * @exception InputMismatchException when a user input value does not match the desired input
     * @exception NoSuchElementException when a user input value does not match any input
     * @see Main#itemInquiry(int)
     * @see Main#printOptions()
     * @see Main#itemModify(String[], boolean)
     * @see Main#invCountInquiry()
     * @see Main#orderInquiry()
     * @see Main#editCounts(int, int)
     */
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        boolean quit = false;
        int c;

        while(!quit) {
            printOptions();
            System.out.print("Action: ");
            try {
                c = scnr.nextInt();
                String q = ""; //consumes the \n 
                if (c == 1) {
                    System.out.println("Enter the name of the item ");
                    q = scnr.nextLine(); //consumes the \n
                    String name = scnr.nextLine();
                    System.out.println("Enter description of item: ");
                    String desc = scnr.nextLine();
                    System.out.println("Enter lowest count before order: ");
                    int lowCount = scnr.nextInt();
                    String[] content = {name, desc, Integer.toString(lowCount)};
                    itemModify(content, false);
                } else if (c == 2) {
                    System.out.println("Enter the index of the item: ");
                    q = scnr.nextLine(); //consumes the \n
                    String name = scnr.nextLine();//issue with next line being funky and adding a extra line and doesn't do it for option 6
                    String content[] = {name};
                    itemModify(content, false);
                } else if (c == 3) {
                    System.out.println("Enter the index of the item: ");
                    q = scnr.nextLine(); //consumes the \n
                    int name = scnr.nextInt(); 
                    itemInquiry(name);
                    System.out.println("Enter new description of item: ");
                    q = scnr.nextLine(); //consumes the \n

                    String desc = scnr.nextLine();
                    System.out.println("Enter new lowest count before re-order: ");
                    int lowCount = scnr.nextInt();
                    String[] content = {Integer.toString(name), desc, Integer.toString(lowCount)};
                    itemModify(content, true);
                } else if (c == 4) {
                    System.out.println("Enter index of item: ");
                    q = scnr.nextLine(); //consumes the \n
                    int name = scnr.nextInt(); 
                    itemInquiry(name);
                    System.out.println("New count of item: ");
                    int count = scnr.nextInt();
                    editCounts(name, count);
                } else if (c == 5) {
                    orderInquiry();
                } else if (c == 6) {
                    System.out.println("Enter the index of the item: ");
                    q = scnr.nextLine(); //consumes the \n
                    int index = scnr.nextInt(); 
                    System.out.println(q);
                    itemInquiry(index);
                } else if (c == 7) {
                    invCountInquiry();
                } else {
                    quit = true;
                    scnr.close();
                }
            } catch(InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch(NoSuchElementException e) {
                System.out.println(e.getMessage());
            } finally {
                String q = scnr.nextLine(); //catch the next line so we don't have inf loop =)
            }
        }
    }
}