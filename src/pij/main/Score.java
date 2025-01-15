package pij.main;

import java.util.HashMap;
import java.util.Map;
public class Score {

    private Tile tile;
    private Map<Player, Integer> playerScores;
    private static final int WILDCARD_SCORE = 5;
    public Score(Tile tile) {
        this.tile = new Tile();
        this.playerScores = new HashMap<>();
    }

    public int getTileScore(Character tile) {
        return this.tile.getScore(tile);
    }
    public int getWordScore(String word) {
        int totalScore = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                totalScore += getTileScore(c);
            } else if (Character.isLowerCase(c)) {
                //if (player.playerTiles.contains('_')) {
                totalScore += WILDCARD_SCORE;
            }
        }
        return totalScore;
    }

    public void addScore(Player player, int score) {
        int currentScore = playerScores.getOrDefault(player,0);
        playerScores.put(player, currentScore + score);
    }
    public int getPlayerScore(Player player) {
        return playerScores.getOrDefault(player, 0);
    }
}

