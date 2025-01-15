package pij.main;

import java.util.List;
import java.util.Scanner;

public class GamePlay {
    private Player player1;
    private Player player2;
    // private TileBag tileBag;
    private Board board;
    private String myGameChoice;
    private Dictionary dictionary;
    private Player currentPlayer;
    public List<String> tilesWithScore;
    private Move move;
    private Score score;
    private Tile tile;

    public GamePlay() {

        board = new Board();
        dictionary = new Dictionary();
        //tileBag = new TileBag();
        player1 = new Player("Human Player");
        player2 = new Player("Computer Player");
        currentPlayer = player1;
        tilesWithScore = currentPlayer.drawTilesWithScore();


        board.setYourChoice();

        Tile tile = new Tile();
        this.score = new Score(tile);

        this.move = new Move(board);

        System.out.println();
        System.out.println("Would you like to play an _o_pen or a_c_losed game?");
        System.out.print("Please enter your choice (o/c): ");
        Scanner myChoice = new Scanner(System.in);
        myGameChoice = myChoice.nextLine().toLowerCase();

        while (!myGameChoice.equals("o") && !myGameChoice.equals("c")) {
            System.out.print("Please enter your choice (o/c): ");
            myGameChoice = myChoice.nextLine().toLowerCase();
        }

        if (myGameChoice.equals("o")) {
            this.startGame();
        } else {
            this.startGame();
        }
    }

    public void startGame() {

        player1.drawTiles();
        player2.drawTiles();
        //player2.drawTilesWithScore();
        currentPlayer = player2;


        while(!isGameOver()) {
            takeTurn();
            switchPlayer();
        }
    }

    private void takeTurn() {

        if (myGameChoice.equals("o")) {
            System.out.println("OPEN GAME: The computer's tiles:");
            System.out.print("OPEN GAME: ");
            player2.drawTiles();
            player2.drawTilesWithScore();
            System.out.println(player2.formatTilesWithScore());



        } else {
            System.out.println("CLOSE GAME: The computer's tiles:");
            System.out.print("CLOSE GAME: ");
            player2.drawTiles();
            player2.drawTilesWithScore();
            System.out.println(player2.formatTilesWithScore());


        }
    }

    private void switchPlayer() {

        if (currentPlayer == player1) {
            currentPlayer = player2;
            System.out.println("It's computer's turn!");


            //System.out.println(currentPlayer.getName() + " tiles: " + currentPlayer.playerTiles);

        } else {
            currentPlayer = player1;
            System.out.println("It's your turn! Your tiles:");
            currentPlayer.drawTiles();
            currentPlayer.drawTilesWithScore();
            System.out.println(currentPlayer.formatTilesWithScore());
            System.out.println("Please enter your move in the format: \"word, square\" (without the quotes)");
            System.out.println("For example, for suitable tile rack and board configuration, a downward move");
            System.out.println("could be \"HI, f4\" and a rightward move could be \"HI, 4f\".");
            System.out.println();
            System.out.println("In the word, upper-case letters are standard tiles");
            System.out.println("and lower-case letters are wildcards.");
            System.out.println("Entering \",\" passes the turn.");



        }

        Scanner words = new Scanner(System.in);
        String inputOfWords;
        String[] partOfWord;
        String word;
        String position;

        do {
            inputOfWords = words.nextLine();
            partOfWord = inputOfWords.split(",", 2);
            word = partOfWord[0];
            position = partOfWord[1];

            if (inputOfWords.equals(",")) {
                System.out.println("You passed your turn.");
                return;
            } else if (!isMoveValid(position)) {
                System.out.println("Illegal move format");
                System.out.println("Please enter your move in the format: \"word, square\" (without the quotes)");
                System.out.println("For example, for suitable tile rack and board configuration, a downward move");
                System.out.println("could be \"HI, f4\" and a rightward move could be \"HI, 4f\".");
                System.out.println();
                System.out.println("In the word, upper-case letters are standard tiles");
                System.out.println("and lower-case letters are wildcards.");
                System.out.println("Entering \",\" passes the turn.");
            } else if (!isWordValid(word)){
                System.out.println("The board does not permit word " + word + " at position " + position + ". Please try again.");
                System.out.println("Please enter your move in the format: \"word, square\" (without the quotes)");
                System.out.println("For example, for suitable tile rack and board configuration, a downward move");
                System.out.println("could be \"HI, f4\" and a rightward move could be \"HI, 4f\".");
                System.out.println();
                System.out.println("In the word, upper-case letters are standard tiles");
                System.out.println("and lower-case letters are wildcards.");
                System.out.println("Entering \",\" passes the turn.");
            }

        } while (!isMoveValid(position) || !isWordValid(word));


        //System.out.println("The move is:    Word: " + word + " position: " + position);

        if (isVaildWildcardandWord(word)) {


            String[] moveParts = position.split("(?<=\\D)|(?=\\D)", 2);

            int row, col;


            if (Character.isDigit(moveParts[0].charAt(0))) {
                row = Integer.parseInt(moveParts[0]) - 1; //make row 0 based

                col = (int) moveParts[1].charAt(0) - 96 - 1; //make col 0 based


            } else {
                col = (int) moveParts[0].charAt(0) - 96 - 1;

                row = Integer.parseInt(moveParts[1]) - 1;
                //System.out.println("***row " + row);
            }

            char direction = moveParts[0].charAt(0);


            int wordScore = score.getWordScore(word);



            score.addScore(currentPlayer, wordScore);


            System.out.println("The move is:    Word: " + word + " position: " + position);
            System.out.println("Human player score:    " + score.getPlayerScore(player1));
            System.out.println("Computer player score: " + score.getPlayerScore(player2));


            currentPlayer.removeTiles(word);
            currentPlayer.drawTiles();
            move.modifyFileLinkedList(row, col, direction, word);
            //currentPlayer.removeTilefromTileBag(word);
        }

        System.out.println();
    }


    public boolean isVaildWildcardandWord (String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)){
                String tempWord = word.replaceFirst(String.valueOf(c), "_");
                if (isWordValid(tempWord)) {
                    return true;
                }

            }
        }
        return isWordValid(word);
    }



    public boolean isWordValid(String word) {
        if (word.contains("_")) {
            for (char c = 'a'; c <= 'z'; c++) {
                String replacedWord = word.replace('_', c);
                if (dictionary.isValidWords(replacedWord.toLowerCase()) && currentPlayer.isWordInTileRack(replacedWord.toUpperCase())) {
                    return true;
                }
            }
            return false;
        } else {
            return dictionary.isValidWords(word.toLowerCase()) && currentPlayer.isWordInTileRack(word.toUpperCase());
        }
    }

    public boolean isMoveValid(String move) {
        String[] parts = move.split("(?<=\\D)|(?=\\D)", 2);
        //String moveFirstPart = parts[0];
        //String moveSecondPart = parts[1];
        if (parts.length < 2) {
            return false;
        }

        String moveFirstPart = parts[0];
        String moveSecondPart = parts[1];


        boolean isFirstPartChar = moveFirstPart.matches("[a-z]");
        boolean isSecondPartNum = moveSecondPart.matches("[0-9]+");

        boolean isFirstPartNum = moveFirstPart.matches("[0-9]+");
        boolean isSecondPartChar = moveSecondPart.matches("[a-z]");

        return (isFirstPartChar && isSecondPartNum) || (isFirstPartNum && isSecondPartChar);
    }

    private boolean isGameOver() {
        if (player1.playerTiles.isEmpty() || player2.playerTiles.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}