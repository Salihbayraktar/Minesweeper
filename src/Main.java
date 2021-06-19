import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static char[][] gameField;
    static char[][] printedField;
    static int tableRowSize;
    static int tableColumnSize;
    static int totalMines;

    public static void fillGameAndPrintedField(int userRow, int userColumn) {

        tableRowSize = userRow + 2;
        tableColumnSize = userColumn + 2;
        gameField = new char[tableRowSize][tableColumnSize];
        printedField = new char[tableRowSize][tableColumnSize];

        for (char[] chars : gameField) {
            Arrays.fill(chars, '.');
        }

        for (char[] chars : printedField) {
            Arrays.fill(chars, '.');
        }

        for (int r = 0; r < gameField.length; r++) {
            gameField[r][0] = '0';
            gameField[r][gameField[0].length - 1] = '0';
        }

        for (int c = 0; c < gameField[0].length; c++) {
            gameField[0][c] = '0';
            gameField[gameField.length - 1][c] = '0';
        }

    }

    public static void addMineToGameField(int totalMines) {

        int counter = 0;

        while (counter != totalMines) {
            Random random = new Random();
            int randomRowPosition = random.nextInt(tableRowSize - 2) + 1;
            int randomColumnPosition = random.nextInt(tableColumnSize - 2) + 1;
            if (gameField[randomRowPosition][randomColumnPosition] == '.') {
                gameField[randomRowPosition][randomColumnPosition] = 'X';
                counter++;
            }
        }
    }

    public static boolean controlTotalMines(int totalMines) {

        if ((tableRowSize - 2) * (tableColumnSize - 2) > totalMines) {
            return true;
        } else {
            System.out.println("Mines excess the game table\n");
            return false;
        }

    }

    public static void fillHints() {

        for (int i = 1; i < gameField.length - 1; i++) {

            for (int j = 1; j < gameField[i].length - 1; j++) {

                int minesAround = 0;

                if (gameField[i][j] == '.') {

                    for (int upMinesColumn = j - 1, upMinesRow = i - 1; upMinesColumn < j + 2; upMinesColumn++) {

                        if (gameField[upMinesRow][upMinesColumn] == 'X') minesAround++;

                    }

                    for (int downMinesColumn = j - 1, downMinesRow = i + 1; downMinesColumn < j + 2; downMinesColumn++) {

                        if (gameField[downMinesRow][downMinesColumn] == 'X') minesAround++;

                    }

                    if (gameField[i][j - 1] == 'X') minesAround++; // Left Side

                    if (gameField[i][j + 1] == 'X') minesAround++; // Right Side

                }
                if (minesAround != 0) {
                    gameField[i][j] = String.valueOf(minesAround).charAt(0);

                }
            }
        }
    }


    public static boolean controlMineCoordinates(int row, int column) {
        if (!controlInputRowAndColumn(row, column)) {
            return false;
        }
        if (Character.isDigit(printedField[row][column])) {
            System.out.println("There is a number here!");
            return false;
        }
        if (printedField[row][column] == '/') {
            System.out.println("There is a slash here!");
            return false;
        }
        return true;
    }

    public static void fillPrintedTableWithMines() {
        for (int r = 1; r < printedField.length - 1; r++) {
            for (int c = 1; c < printedField[0].length - 1; c++) {
                if (gameField[r][c] == 'X') {
                    printedField[r][c] = 'X';
                }
            }
        }
    }

    public static boolean controlInputRowAndColumn(int row, int column) {
        if (row > (tableRowSize - 2) || row < 1 || column > (tableColumnSize - 2) || column < 1) {
            System.out.println("Invalid range of coordinates...");
            return false;
        }
        return true;
    }

    public static boolean controlFreeCoordinates(int row, int column) {

        if (!controlInputRowAndColumn(row, column)) {
            return false;
        }
        if (printedField[row][column] == '*') {
            System.out.println("Marked as mines in this location");
            return false;
        }

        if (gameField[row][column] == 'X') {

            fillPrintedTableWithMines();
            printGameTable(printedField);
            System.out.println("You stepped on a mine and failed!");
            System.exit(0);

        }
        return true;
    }

    public static boolean isAllMinesMarked() {

        int countStars = 0;
        for (int r = 1; r < gameField.length - 1; r++) {

            for (int c = 1; c < gameField[r].length - 1; c++) {

                if (gameField[r][c] == 'X' && printedField[r][c] != '*') {
                    return false;
                } else if (printedField[r][c] == '*') {
                    countStars++;
                }
            }
        }
        return totalMines == countStars;
    }

    public static boolean isOnlyMinesLeft() {

        int countDotAndStar = 0;
        for (int r = 1; r < printedField.length - 1; r++) {

            for (int c = 1; c < printedField[r].length - 1; c++) {

                if (printedField[r][c] == '*' || printedField[r][c] == '.') {
                    countDotAndStar++;

                    if (countDotAndStar > totalMines) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void explore(int row, int column, char[][] gameField, char[][] printedTable) {

        if (Character.isDigit(gameField[row][column])) {
            printedTable[row][column] = gameField[row][column];
            return;
        } else if (printedTable[row][column] == '/') {
            return;
        } else {
            printedTable[row][column] = '/';
        }

        explore(row - 1, column - 1, gameField, printedTable);
        explore(row - 1, column, gameField, printedTable);
        explore(row - 1, column + 1, gameField, printedTable);

        explore(row, column - 1, gameField, printedTable);
        explore(row, column + 1, gameField, printedTable);

        explore(row + 1, column - 1, gameField, printedTable);
        explore(row + 1, column, gameField, printedTable);
        explore(row + 1, column + 1, gameField, printedTable);
    }

    public static void printGameTable(char[][] printedTable) {

        System.out.print("  |");
        for (int i = 1; i < printedTable[0].length - 1; i++) {
            System.out.print(i / 10);
        }
        System.out.println("|");
        //----------------------------------------------------
        System.out.print("  |");
        for (int i = 1; i < printedTable[0].length - 1; i++) {
            System.out.print(i % 10);
        }
        System.out.println("|");

        //-----------------------------------------------------
        System.out.print("  |");
        for (int i = 1; i < printedTable[0].length - 1; i++) {
            System.out.print("-");
        }
        System.out.println("|");

        //-----------------------------------------------------
        for (int i = 1; i < printedTable.length - 1; i++) {

            if (i < 10) {
                System.out.print(" " + i + "|");
            } else {
                System.out.print(i + "|");
            }


            for (int j = 1; j < printedTable[i].length - 1; j++) {
                System.out.print(printedTable[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public static void printAllValuesOfTable(char[][] table) {
        for (char[] c : table) {
            for (char d : c) {
                System.out.print(d);
            }
            System.out.println();

        }

    }

    public static boolean isInputInteger(String input) {
        Scanner scanner = new Scanner(input);

        try {
            scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input! \n");
            return false;
        }

        return true;
    }

    public static boolean isQueryValid(String query) {
        Scanner scanner = new Scanner(query);

        try {
            scanner.nextInt();
            scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid Query! \n");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner inputScanner;
        int userRow;
        int userColumn;

        do {
            System.out.println("Enter the number of rows of the playground");
            String userInputRow = scanner.nextLine();
            if (isInputInteger(userInputRow)) {
                inputScanner = new Scanner(userInputRow);
                userRow = inputScanner.nextInt();
                break;
            }
        } while (true);

        do {
            System.out.println("Enter the number of columns of the playground");
            String userInputColumn = scanner.nextLine();
            if (isInputInteger(userInputColumn)) {
                inputScanner = new Scanner(userInputColumn);
                userColumn = inputScanner.nextInt();
                break;
            }
        } while (true);


        fillGameAndPrintedField(userRow, userColumn);

        do {
            System.out.println("How many mines do you want on the field?");
            String userInputMines = scanner.nextLine();
            if (isInputInteger(userInputMines)) {

                inputScanner = new Scanner(userInputMines);
                totalMines = inputScanner.nextInt();

                if (controlTotalMines(totalMines)) {
                    break;
                }
            }
        } while (true);

        addMineToGameField(totalMines);

        fillHints();

        printGameTable(printedField);

        while (!isOnlyMinesLeft() && !isAllMinesMarked()) {

            System.out.println("Set/unset mines marks or claim a cell as free:"); // modes are 'free' and 'mine'
            String query = scanner.nextLine();
            if (isQueryValid(query)) {
                Scanner queryScan = new Scanner(query);
                int row = queryScan.nextInt();
                int column = queryScan.nextInt();
                String mode = queryScan.nextLine().trim();
                switch (mode) {
                    case "free":
                        if (controlFreeCoordinates(row, column)) {
                            explore(row, column, gameField, printedField);
                            printGameTable(printedField);
                        }
                        break;
                    case "mine":
                        if (controlMineCoordinates(row, column)) {
                            if (printedField[row][column] == '.') {
                                printedField[row][column] = '*';
                            } else if (printedField[row][column] == '*') {
                                printedField[row][column] = '.';
                            }

                            printGameTable(printedField);
                        }

                        break;
                    default:
                        System.out.println("Invalid Mode");
                        break;
                }
            }
        }
        System.out.println("Congratulations! You found all the mines!");
    }
}
