package pij.main;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class Board {
    private String yourChoice;
    private String customBoardName;
    private StringBuilder fileStrings;
    private LinkedList<LinkedList<String>> fileLinkedlist;
    private int fileBoardSize;

    public Board() {
        fileLinkedlist = new LinkedList<>();
        fileStrings = new StringBuilder();
        //yourChoice = new String();
        customBoardName = new String();
    }

    public void setYourChoice() {
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.print("Please enter your choice (l/d)? ");
        Scanner yourChoiceBoard = new Scanner(System.in);
        yourChoice = yourChoiceBoard.nextLine().toLowerCase();


        while (!yourChoice.equals("d") && !yourChoice.equals("l")) {
            System.out.print("Please enter your choice (l/d)? ");
            yourChoice = yourChoiceBoard.nextLine().toLowerCase();
        }

        if (yourChoice.equals(("d"))) {
            this.readFile(new File("resources/defaultBoard.txt"));
            this.convertStringToLinkedList();
            //this.testPrintString();
            this.printInitialBoard();

        } else {
            System.out.print("Please enter the name of the board: ");
            customBoardName = yourChoiceBoard.nextLine();
            File customBoarFile = new File(customBoardName);
            while (!customBoarFile.exists() || !customBoarFile.isFile()) {
                System.out.println("This is not a valid file. Please enter the file name of the board:");
                customBoardName = yourChoiceBoard.nextLine();
            }
            this.readFile(new File("/Users/eric/Desktop/"+customBoardName+".txt"));
        }
    }

    public void readFile(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            if ((line = in.readLine()) != null) {
                fileBoardSize = Integer.parseInt(line);
            }

            while ((line = in.readLine()) != null) {
                String[] chars = line.split("");
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i].equals(".")) {
                        fileStrings.append(chars[i]).append(",");
                    } else if (chars[i].equals(")") || chars[i].equals("}")) {
                        fileStrings.append(chars[i]).append(",");
                    } else if (chars[i].equals("-")) {
                        fileStrings.append(chars[i]);
                    } else {
                        fileStrings.append(chars[i]);
                    }
                }
                fileStrings.append(";");
            }

            String result = fileStrings.toString().replaceAll("(\\{-\\w+|\\{\\d{2})\\}", "$1");
            fileStrings = new StringBuilder(result);

            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertStringToLinkedList() {

        String[] rows = fileStrings.toString().split(";");
        for (String row : rows) {
            String[] chars = row.split(",");
            LinkedList<String> rowList = new LinkedList<>();
            for (String c : chars) {
                rowList.add(c);
            }
            fileLinkedlist.add(rowList);
        }
    }

    public LinkedList<LinkedList<String>> getFileLinkedlist() {
        return fileLinkedlist;
    }

    public int getFileBoardSize() {
        return fileBoardSize;
    }

    public void printInitialBoard() {
        System.out.println();
        System.out.print(" ");
        if (!fileLinkedlist.isEmpty()) {
            for (int i = 0; i < fileLinkedlist.get(0).size(); i++) {
                System.out.printf("%4s", (char) ('a' + i));
            }
            System.out.println();
        }

        int rowNum = 1;
        for (LinkedList<String> row : fileLinkedlist) {
            System.out.printf("%2s", rowNum);
            System.out.print("  ");
            for (String c : row) {
                System.out.printf("%-4s", c);
            }
            System.out.printf("%4s", rowNum);
            System.out.println();
            rowNum++;
        }
        System.out.print(" ");
        if (!fileLinkedlist.isEmpty()) {
            for (int i = 0; i < fileLinkedlist.get(0).size(); i++) {
                System.out.printf("%4s", (char) ('a' + i));
            }
        }
        System.out.println();
    }

    public void updateBoard() {
        System.out.print(" ");
        if (!this.fileLinkedlist.isEmpty()) {
            for (int i = 0; i < this.getFileLinkedlist().get(0).size(); i++) {
                System.out.printf("%4s", (char) ('a' + i));
            }
            System.out.println();
        }
        LinkedList<LinkedList<String>> fileLinkedlist = this.getFileLinkedlist();

        int rowNum = 1;
        for (LinkedList<String> row : fileLinkedlist) {
            System.out.printf("%2s", rowNum);
            System.out.print("  ");
            for (String c : row) {
                System.out.printf("%-4s", c);
            }
            System.out.printf("%4s", rowNum);
            System.out.println();
            rowNum++;
        }
        System.out.print(" ");
        if (!this.fileLinkedlist.isEmpty()) {
            for (int i = 0; i < this.getFileLinkedlist().get(0).size(); i++) {
                System.out.printf("%4s", (char) ('a' + i));
            }
        }
        System.out.println();
    }
}