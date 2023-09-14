 /*
3/18/23
Final Project
Inventory.java
Contains the methods for the record class.
*/ 

/**
 * A class which represents a item using 
 * @author TramHammer
 * @version 1.0
 */
public class Inventory {
    /**
     * The <code>name</code> attribute of the Inventory Object stores the name of the item as a String
     */
    private String name;
    /**
     * The <code>desc</code> attribute of the Inventory Object stores the description of the item as a String
     */
    private String desc;
    /**
     * The <code>count</code> attribute of the Inventory Object stores the current count of the item as a int
     */
    private int count;
    /**
     * The <code>lowestCount</code> attribute of the Inventory Object stores the lowest count of the object before it considered for re-order. It is stored as a int
     */
    private int lowestCount;

    /**
     * Inventory Constructor, sets the object with the name, "Unknown", description, "n/a", count of 0 and a restock count of -1
     * This method is called when you create a Inventory Object with no parameters.
     */
    public Inventory() {
        name = "Unknown";
        desc = "n/a";
        count = 0;
        lowestCount = -1;
    }
    /** 
     * Inventory constructor, specifying the <code>name</code>, <code>description</code>, <code>count</code>, and <code>lowestCount</code> of the item.
     * This method is called when you create a Inventory Object with a String, String, int, int paramater.
     * @param name the name of the item
     * @param desc the description or identifier for the item
     * @param count the current count of the item
     * @param lowestCount the lowest count before it is considered for re-ordering
     * @see Inventory#name
     * @see Inventory#desc
     * @see Inventory#count
     * @see Inventory#lowestCount
     */
    public Inventory(String name, String desc, int count, int lowestCount) {
        this.name = name;
        this.desc = desc;
        this.count = count;
        this.lowestCount = lowestCount;
    }
    /**
     * Sets the <code>name</code> of the Inventory Object
     * @param name the name of the item
     * @see Inventory#name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the <code>desc</code> attribute of the Inventory Object
     * @param desc the description of the item
     * @see Inventory#desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Sets the <code>count</code> attribute of the Inventory Object
     * Does not change the <code>count</code> if the value is negative
     * @param count the amount of the item currently available
     * @see Inventory#count
     */
    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }
    /**
     * Sets the <code>lowestCount</code> attribute of the Inventory Object
     * Does not change the <code>lowestCount</code> if the value is negative 
     * @param lowestCount the lowest value of the item before it is considered for re-order
     * @see Inventory#lowestCount
     */
    public void setLowestCount(int lowestCount) {
        if (count >= 0) {
            this.lowestCount = lowestCount;
        }
    }
    /**
     * Gets the <code>name</code> attribute of the Inventory Object and returns it
     * @return the name of the item
     * @see Inventory#name
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the <code>desc</code> attribute of the Inventory Object and returns it
     * @return the amount of the item currently available
     * @see Inventory#desc
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Gets the <code>count</code> attribute of the Inventory Object and returns it
     * @return the current count of the item
     * @see Inventory#count
     */
    public int getCount() {
        return count;
    }
    /**
     * Gets the <code>lowestCount</code> attribute of the Inventory Object and returns it
     * @return the lowest value of the item before it is considered for re-order
     * @see Inventory#lowestCount
     */
    public int getLowestCount() {
        return lowestCount;
    }
    /**
     * Returns the new count given the current amount of a item and the amount to remove/add
     * 
     * If <code>edit</code> parameter is negative it will subtract that value from <code>count</code>
     * If <code>edit</code> paramter is positive it will add that value to <code>count</code>
     * @param count the amount of the item currently available
     * @param edit the value to remove or subtract
     * @return the new amount of the item currently available
     */
    static int editCount(int count, int edit) {
        if (((edit - count) >= 0) || ((edit + count) >= 0)) { 
            count = count + edit;
        }
        return count;
    }
    /**
     * Returns the amount that needs to be re-ordered for the item to be above the lowestCount
     * @param count the amount of the item currently available
     * @param lowestCount the amount of the item ot be considered for re-order
     * @return the amount needed to reorder
     */
    static int checkReorderCount(int count, int lowestCount) {
        int reOrderC = 0;
        if (count < lowestCount) {
            reOrderC = lowestCount - count; //updated this because the for loop had no purpose and did not show correct counts
        }
        return reOrderC;
    }

}
