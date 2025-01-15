package pij.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    private HashMap<Character, Integer> tiles;
    private HashMap<Character, Integer> tileScore;

    public Tile() {
        this.tiles = new HashMap<>();
        this.tileScore = new HashMap<>();
        this.fillTileChars();
        this.fillTileScores();
    }

    public void fillTileChars(){

        tiles.put('E', 10);
        tiles.put('A', 8);
        tiles.put('I', 8);
        tiles.put('N', 7);
        tiles.put('O', 7);
        tiles.put('R', 6);
        tiles.put('T', 6);
        tiles.put('U', 5);
        tiles.put('D', 4);
        tiles.put('G', 4);
        tiles.put('L', 4);
        tiles.put('S', 4);
        tiles.put('F', 3);
        tiles.put('H', 3);
        tiles.put('B', 2);
        tiles.put('C', 2);
        tiles.put('M', 2);
        tiles.put('P', 2);
        tiles.put('V', 2);
        tiles.put('Y', 2);
        tiles.put('_', 2); // wildcard
        tiles.put('J', 1);
        tiles.put('K', 1);
        tiles.put('Q', 1);
        tiles.put('W', 1);
        tiles.put('X', 1);
        tiles.put('Z', 1);

    }


    public void fillTileScores(){

        tileScore.put('E', 1);
        tileScore.put('A', 1);
        tileScore.put('I', 1);
        tileScore.put('N', 1);
        tileScore.put('O', 1);
        tileScore.put('R', 1);
        tileScore.put('T', 1);
        tileScore.put('U', 1);
        tileScore.put('D', 2);
        tileScore.put('G', 2);
        tileScore.put('L', 1);
        tileScore.put('S', 1);
        tileScore.put('F', 4);
        tileScore.put('H', 4);
        tileScore.put('B', 3);
        tileScore.put('C', 3);
        tileScore.put('M', 3);
        tileScore.put('P', 3);
        tileScore.put('V', 4);
        tileScore.put('Y', 5);
        tileScore.put('_',5); // wildcard
        tileScore.put('J', 9);
        tileScore.put('K', 6);
        tileScore.put('Q', 12);
        tileScore.put('W', 4);
        tileScore.put('X', 9);
        tileScore.put('Z', 11);

    }

    public ArrayList<Character> getTile(){
        ArrayList<Character> tileList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : tiles.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                tileList.add(entry.getKey());
            }
        }
        return tileList;
    }

    public void getTileInfo(){
        for (Map.Entry<Character, Integer> entry : tileScore.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Key: " + key + ", " + "Value: " + value);
        }
    }

    public Integer getScore(Character tile){
        return tileScore.getOrDefault(tile,0);
    }
}
