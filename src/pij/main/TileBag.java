package pij.main;

import java.util.*;

public class TileBag {

    private Random random;
    private List<Character> bag;
    private Tile tile;
    public static final int MAX_TILES_RACK = 7;
    private List<Character> lastDrawnTiles;
    private List<String> lastDrawnTilesWithScore;

    public TileBag() {
        random = new Random();
        this.tile = new Tile();
        this.bag = tile.getTile();
        this.lastDrawnTiles = new ArrayList<>();
        this.lastDrawnTilesWithScore = new ArrayList<>();
    }

    public Character addTile() {
        int index = random.nextInt(bag.size());
        return bag.remove(index);
    }

    public List<Character> drawTiles() {
        lastDrawnTiles.clear();
        List<Character> drawTiles = new ArrayList<>();
        for (int i = 0; i < MAX_TILES_RACK && !bag.isEmpty(); i++) {
            if (drawTiles.size() < MAX_TILES_RACK) {
                Character tile = addTile();
                drawTiles.add(tile);
                lastDrawnTiles.add(tile);
            }
        }
        return drawTiles;
    }

    public List<String> drawTilesWithScore() {
        lastDrawnTiles.clear();
        List<String> drawTilesWithScore = new ArrayList<>();
        int count = 0;
        for (char c : lastDrawnTiles) {
            if (count >= MAX_TILES_RACK) {
                break;
            }
            Integer score = tile.getScore(c);
            drawTilesWithScore.add(c + "" + score);
            count++;
        }
        lastDrawnTilesWithScore = drawTilesWithScore;
        return new ArrayList<>(lastDrawnTilesWithScore );
    }


    public boolean removeTileFromBag (Character  tile) {
        if(bag.contains(tile)){
            bag.remove(tile);
            return true;
        }
        return false;
    }

    public int getBagSize(){
        return bag.size();
    }

    public String tilesToString() {
        StringBuilder tileStrings = new StringBuilder();
        for (String c : lastDrawnTilesWithScore) {
            tileStrings.append("[").append(c).append("]"). append(", ");
        }
        if (tileStrings.length() > 0) {
            tileStrings.deleteCharAt(tileStrings.length() - 2);
        }
        return tileStrings.toString();
    }
}