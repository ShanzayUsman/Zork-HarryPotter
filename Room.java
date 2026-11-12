package org.uob.a1;

public class Room {
    private String name;
    private String description;
    private char symbol;
    private Position position;
    
    private Room NorthRoom;
    private Room SouthRoom;
    private Room EastRoom;
    private Room WestRoom;

    public Room(String name,String description, char symbol, Position position){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setNorth(Room northroom){
        this.NorthRoom = northroom;
    }
     public void setSouth(Room southroom){
        this.SouthRoom = southroom;
    }
    public void setEast(Room eastroom){
        this.EastRoom = eastroom;
    }
     public void setWest(Room westroom){
        this.WestRoom = westroom;
    }
     public Room getNorth(){
        return this.NorthRoom;
    }
     public Room getSouth(){
        return this.SouthRoom;
    }
    public Room getEast(){
        return this.EastRoom;
    }
    public Room getWest(){
        return this.WestRoom;
    }
}
