package pij.main;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int MAX_TILES_RACK = 7;
    private String playerName;
    private TileBag tileBag;
    public List<Character> playerTiles;
    public ArrayList<String> playerTilesWithScore;
    private Tile tile;


    public Player(String playerName) {
        this.playerName = playerName;
        this.playerTiles = new ArrayList<>();
        this.playerTilesWithScore = new ArrayList<>();
        this.tile = new Tile();
        this.tileBag = new TileBag();
    }

    public void drawTiles() {
        if (playerTiles.size() < MAX_TILES_RACK) {
            List<Character> newTiles = tileBag.drawTiles();
            int tilesNeeded = MAX_TILES_RACK - playerTiles.size();
            if (newTiles.size() < tilesNeeded) {
                playerTiles.addAll(newTiles);
            } else {
                playerTiles.addAll(newTiles.subList(0, tilesNeeded));
            }
        }
    }

    public Integer getTileScore(Character tile) {
        return this.tile.getScore(tile);
    }

    public ArrayList<String> drawTilesWithScore() {
        playerTilesWithScore.clear();
        int count = 0;
        for (Character c : playerTiles) {
            if (count >= MAX_TILES_RACK) {
                break;
            }
            String tileWithScore = c + "" + this.tile.getScore(c);

            //playerTilesWithScore.add(c);
            playerTilesWithScore.add(tileWithScore);
            count++;
        }
        //tilesToString();
        //System.out.println(playerTilesWithScore);
        return playerTilesWithScore;
    }

    public String getName() {
        return playerName;
    }
    public boolean isWordInTileRack(String word) {
        List<Character> tempTiles = new ArrayList<>(playerTiles);
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (tempTiles.contains(c)) {
                    tempTiles.remove((Character) c);
                } else {
                    return false;
                }
            } else if (Character.isLowerCase(c)) {
                if (tempTiles.contains('_')) {
                    tempTiles.remove((Character) '_');
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeTiles(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (playerTiles.contains(c)) {
                    playerTiles.remove((Character) c);
                    //System.out.println("***removing tile: " + c);
                }
            } else if (Character.isLowerCase(c)) {
                if (playerTiles.contains('_')) {
                    playerTiles.remove((Character) '_');
                    //System.out.println("***removing wildcard: " + '_');
                }
            }
        }
    }

    public int sizeOfTileBag(){
        System.out.println(tileBag.getBagSize());
        return tileBag.getBagSize();
    }


    public String formatTilesWithScore() {
        StringBuilder tileStrings = new StringBuilder();
        //ArrayList<String> tilesWithScore = drawTilesWithScore();
        for (String c : playerTilesWithScore) {
            tileStrings.append("[").append(c).append("]"). append(", ");
        }
        if (tileStrings.length() > 0) {
            tileStrings.deleteCharAt(tileStrings.length() - 2);
        }
        return tileStrings.toString();
    }
}

