package org.uob.a1;

public class Inventory {
    final int MAX_ITEMS = 10;
    private String[] items;
    private int itemcounter;

    public Inventory(){
        this.items = new String[MAX_ITEMS];
        for (int i = 0; i <MAX_ITEMS; i++){
            items[i] = "";
        }
        this.itemcounter = 0;
    }

    public void addItem(String item){
        if (this.itemcounter < MAX_ITEMS) {
            items[itemcounter] = item;
            itemcounter++;
        }
    }

    public int hasItem(String item){
        for (int i = 0; i < itemcounter; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    
    public void removeItem(String item){
        int i = hasItem(item);
        if (i>=0){
            while(i<itemcounter-1){
                items[i] = items[i+1];
                i++;
            }
            items[itemcounter-1] = "";
            itemcounter--;
        }
    }
                

    public String displayInventory(){
        int i =0;
        String stringS = "";
        while(i <itemcounter){
            stringS = stringS + items[i] + " ";
            i++;
        }
        return stringS;
    }
}