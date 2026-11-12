package org.uob.a1;

public class Score {
    private int score;
    private int roomsVisited;
    private int puzzlesSolved;
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore){
        this.score = startingScore;
        this.roomsVisited = 0;
        this.puzzlesSolved = 0;
    }

    public void visitRoom(){
        this.roomsVisited++;
        
    }

    public void solvePuzzle(){
        this.puzzlesSolved++;
    }

    public double getScore(){
        return this.score - roomsVisited + (puzzlesSolved * PUZZLE_VALUE);
    }
}