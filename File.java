 /*
2/28/23
Final Project
File.java
Contains the methods under the file for writing and reading information to inventory.csv
*/ 

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class which contains all the methods which interact with the {@link File#fileName} using Inventory Objects
 * @author TramHammer
 * @version 1.0
 */
public class File {
    /**
     * The fileNmae which will be read from and written to by the {@link File#read()} and {@link File#write(ArrayList)} methods
     * @see File#read()
     * @see File#write()
     */
    final String fileName = "inventory.csv";
    boolean initalWrite = false;
    /**
     * The ArrayList that stores the Inventory Objects that are currently available
     */
    ArrayList<Inventory> items = new ArrayList<Inventory>();
    int lengthOfArray = 0;
    /**
     * File constructor, this creates 5 new Inventory Objects and adds them to the <code>items</code> ArrayList
     * @see File#items
     */
    public File() {
        items.add(new Inventory("GRAPES GRN HM 2", "Green grapes 2 pound bags", 100, 15));
        items.add(new Inventory("BLUEBERRIES 1.5 HM", "Blueberries 1.5 oz containers", 23, 3));
        items.add(new Inventory("BR 10C PAC RED PIT", "10 count red pit carpet", 12, 4));
        items.add(new Inventory("BYD 13OZ GRF CKN PEA", "Chef Boyarde 13 OZ Can of Peas", 3, 4));
        items.add(new Inventory("GV FRENCH ONION 2 OZ", "Great Values French Onion 2 ounces", 1, 4));
    }
    /** 
     * The method reads <code>fileName</code> and adds each value to a Inventory Object inside a ArrayList.
     * If there is no file present and there is a FileNotFoundException it will attempt to write the values in the ArrayList <code>items</code> and then attempt to read and return an ArrayList with those Inventory Objects. <code>initalWrite</code> is set to <code>true</code> when this is completed
     * If there is no file present after attempting ot write the values in the ArrayList <code>items</code> it will instead return the ArrayList <code>items</code> since <code>initalWrite</code> is <code>true</code>
     * @exception FileNotFoundException when attempts to read <code>fileName</code> and it is not present
     * @exception IOException when an interruption or failure occurs when reading file
     * @return an ArrayList with Inventory Objects.
     * @see File#items
     * @see File#initalWrite
     */
    public ArrayList<Inventory> read() {
        ArrayList<Inventory> tempList = new ArrayList<Inventory>();;
        FileInputStream fileIn = null;
        Scanner inFS = null;
        try {
            String[] readArray;
            fileIn = new FileInputStream(fileName);
            inFS = new Scanner(fileIn);
            while(inFS.hasNextLine()) { 
                String lineRead = inFS.nextLine();
                readArray = lineRead.split(",");
                tempList.add(new Inventory(readArray[0], readArray[1], Integer.parseInt(readArray[2]), Integer.parseInt(readArray[3])));
            }
            inFS.close();
        } catch (FileNotFoundException e) {
            if (initalWrite == false) {
                write(items);
                read();
                initalWrite = true;
            } else {
                return items;
            }
        } catch(IOException e) {
            return new ArrayList<Inventory>();
        }
        return tempList;
    }
    /**
     * This method reads using the {@link File#read()} method using the file <code>fileName</code> and returns the Inventory Object with the name that matches the <code>name</code>
     * @param name the name attribute of the Inventory Object
     * @return an Inventory Object
     * @throws IndexOutOfBoundsException when there is no name that matches the 
     * @see Inventory#getName()
     * @see File#read()
     */
    public Inventory read(String name) {
        Inventory returnValue = new Inventory();
        try {
            ArrayList<Inventory> tempList = read();
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getName().equals(name)) {
                    returnValue = tempList.get(i);
                }  
            }
        } catch(IndexOutOfBoundsException e) {
            return new Inventory();
        }
        return returnValue;
    }
    /**
     * This method reads using the {@link File#read()} method using the file <code>fileName</code> and returns the Inventory Object at the specified <code>index</code>
     * @param index the index of the Inventory Object
     * @return an Inventory Object
     * @throws IndexOutOfBoundsException when the <code>index</code> is out of the bounds of the ArrayList
     * @see File#read()
     */
    public Inventory read(int index) {
        ArrayList<Inventory> tempList = read();
        try {
            return tempList.get(index);
        } catch(IndexOutOfBoundsException e) {
            return new Inventory();
        }
    }
    /**
     * This method combines the <code>items<code> ArrayList with the ArrayList returned from the {@link File#read()} method and writes to the <code>fileName</code> file and returns the amount of writes completed.
     * @param inv the ArrayList with Inventory Objects 
     * @return the amount of writes completed
     * @throws IOException when an interruption or failure occurs when writing file
     * @see Inventory#getName()
     * @see Inventory#getDesc()
     * @see Inventory#getCount()
     * @see Inventory#getLowestCount()
     * @see File#read()
     */
    public int write (ArrayList<Inventory> inv) {
        int writes = 0;
        ArrayList<Inventory> tempList;
        PrintWriter outFS = null;
        try {
            outFS = new PrintWriter(new FileOutputStream(fileName)); 
            tempList = read();
            tempList.addAll(inv);

            if (tempList.isEmpty()) {
                outFS.close(); 
                return -1;
            }
            for (Inventory item : tempList) {
                outFS.print(item.getName() + "," + item.getDesc() + ",");
                outFS.printf("%02d,",item.getCount());
                outFS.printf("%02d\n",item.getLowestCount());
                writes++;
            }
        } catch(IOException e) {
            return -1;
        } finally {
            outFS.close();
        }
        return writes;
    }
}