package pij.main;

//import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class Move {
    private Board board;
    private HashSet<Point> setPoints;
    private LinkedList<LinkedList<String>> fileLinkedList;
    private Tile tile;
    private static final int WILDCARD_SCORE = 5;


    public Move(Board board) {
        this.board = board;
        this.setPoints = new HashSet<>();
        this.fileLinkedList = board.getFileLinkedlist();
        this.tile = new Tile();
    }

    public void retriveFileLinkedList() {
        LinkedList<LinkedList<String>> fileLinkedlist = board.getFileLinkedlist();
    }

    public void retriveFileBoardSize() {
        int fileBoardSize = board.getFileBoardSize();
    }

    public void modifyFileLinkedList(int row, int col, char direction, String letters) {

        Point point = new Point(row, col);
        while (setPoints.contains(point)) {
            //System.out.println("This point has been changed");
            if (Character.isDigit(direction)) {
                col++;
                if (col >= fileLinkedList.size()) {
                    //System.out.println("You have already changed all the squares in the column");
                    return;
                } else {
                    point = new Point(row, col);
                }
            } else {
                row++;
                if (row >= fileLinkedList.size()) {
                    //System.out.println("You have already changed all the squares in the row");
                    return;
                } else {
                    point = new Point(row, col);
                }
            }
        }

        for (char c : letters.toCharArray()) {
            if (row >= 0 && row < fileLinkedList.size() && col >= 0 && col < fileLinkedList.get(0).size()) {
                int score = calculateScore(c);
                String tileAndScore = String.format("%s%d", c, score);

                fileLinkedList.get(row).set(col, tileAndScore);
                setPoints.add(point);



            } else {
                System.out.println("Invalid row or column number");
            }

            if (Character.isDigit(direction)) {
                col++;
            } else {
                row++;
            }
            point = new Point(row, col);
        }

        board.updateBoard();
    }

    public int calculateScore(char c) {

        if (Character.isLowerCase(c)){
            return WILDCARD_SCORE;
        }
        return tile.getScore(c);
    }
}