package org.uob.a1;

public class Map {
    
    private int width;
    private int height;
    private char[][] map;
    final private char EMPTY = '.';

    public Map(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new char[height][width];

        for (int i = 0; i<height; i++){
            for (int n = 0; n<width; n++){
                map[i][n] = EMPTY;
            }
        }
    }

    public void placeRoom(Position pos, char symbol){
        map[pos.y][pos.x] = symbol;
        

    }

    public String display(){
        String output = "";
        for (int i=0;i<height;i++){
            String row = "";
            for(int x=0;x<width;x++){
                row = row + map[i][x];
            }
            output = output + row + "\n";
        }    
        return output;
    }
               
}