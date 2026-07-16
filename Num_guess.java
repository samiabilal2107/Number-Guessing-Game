
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class number {
    int num1;

    number() {
        Random rand = new Random();
        num1 = rand.nextInt(1001); // Generates number from 0 to 1000
    }
}

class Work {
    Work() {
    }

    public void startGame(int maxGuesses, ArrayList<Integer> warned, ArrayList<Integer> list, Scanner sc, int num1) {
        System.out.println("--YOU HAVE " + maxGuesses + " GUESSES FOR THIS LEVEL--");
        int guess = 0;
        boolean hintUsed = false;

        while(guess < maxGuesses) {
            System.out.print("      ");

            for(int i = 0; i < maxGuesses - guess; ++i) {
                System.out.print("* ");
            }

            System.out.println();
            System.out.println("________________________________________");
            System.out.println("ENTER THE NUMBER");
            if (!sc.hasNextInt()) {
                System.out.println("INVALID INPUT! ENTER A NUMBER");
                sc.next();
            } else {
                int num = sc.nextInt();
                if (num >= 0 && num <= 1000) {
                    if (!list.contains(num)) {
                        list.add(num);
                        ++guess;
                    } else if (!warned.contains(num)) {
                        System.out.println("---YOU GUESSED THE SAME NUMBER AGAIN---");
                        System.out.println("-----NEXT TIME IT WILL COUNT-----");
                        warned.add(num);
                    } else {
                        System.out.println("---REPEATED AGAIN! NOW COUNTED---");
                        ++guess;
                    }

                    if (num == num1) {
                        System.out.println("--- CONGRATULATIONS! YOU GUESSED THE CORRECT NUMBER! ---");
                        break;
                    }

                    if (maxGuesses - guess == 0) {
                        System.out.println("YOU FAILED TO GUESS THE NUMBER!");
                        System.out.println("THE NUMBER WAS: " + num1);
                        break;
                    }

                    if (num > num1) {
                        System.out.println(num + " IS GREATER THAN THE NUMBER");
                        if (num - num1 <= 10) {
                            System.out.println("HINT: YOU ARE VERY CLOSE");
                        } else if (num - num1 <= 50) {
                            System.out.println("HINT: YOU ARE CLOSE");
                        }
                    } else {
                        System.out.println(num + " IS SMALLER THAN THE NUMBER");
                        if (num1 - num <= 10) {
                            System.out.println("HINT: YOU ARE VERY CLOSE");
                        } else if (num1 - num <= 50) {
                            System.out.println("HINT: YOU ARE CLOSE");
                        }
                    }

                    if (maxGuesses - guess == 3 && !hintUsed) {
                        hintUsed = true;
                        System.out.print("YOU WANT A HINT (YES/NO): ");
                        String hint = sc.next().toLowerCase();
                        if (hint.equals("yes")) {
                            if (num1 <= 250) {
                                System.out.println("HINT: NUMBER IS IN FIRST QUARTER 0-250");
                            } else if (num1 <= 500) {
                                System.out.println("HINT: NUMBER IS IN SECOND QUARTER 251-500");
                            } else if (num1 <= 750) {
                                System.out.println("HINT: NUMBER IS IN THIRD QUARTER 501-750");
                            } else {
                                System.out.println("HINT: NUMBER IS IN LAST QUARTER 751-1000");
                            }
                        } else if (hint.equals("no")) {
                            System.out.println("OK THEN CARRY ON THE GAME");
                        } else {
                            System.out.println("INVALID ENTRY");
                        }
                    }

                    System.out.println("REMAINING GUESSES: " + (maxGuesses - guess));
                } else {
                    System.out.println("-----YOU ARE EXCEEDING THE LIMIT-----");
                }
            }
        }

        System.out.println("YOUR GUESSES: " + String.valueOf(list));
    }

    public boolean askContinue(Scanner sc) {
        System.out.print("Do you want to continue? (yes/no): ");
        return sc.next().equalsIgnoreCase("yes");
    }
}

public class Num_guess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> warned = new ArrayList<>();
            Work w = new Work();
            number n = new number();
            System.out.println("------WELCOME TO NUMBER GUESSING GAME------");
            System.out.println("--YOU HAVE TO GUESS NUMBERS BETWEEN 0 TO 1000--");
            System.out.println("------YOU HAVE THREE LEVELS-----");
            System.out.println("\t.EASY\t.HARD\t.COMPLEX");
            System.out.println("----WHICH LEVEL YOU WANT TO PLAY----");
            String Level = sc.next();
            String level = Level.toLowerCase();
            switch (level) {
                case "easy":
                    w.startGame(10, warned, list , sc, n.num1);
                    break;

                case "hard":
                    w.startGame(7, warned,list,sc, n.num1);
                    break;

                case "complex":
                    w.startGame(5, warned, list , sc, n.num1);
                    break;

                default:
                    System.out.println("INVALID LEVEL");
                    continue;
            }

            running = w.askContinue(sc);
        }

        System.out.println("Thanks for playing!");
    }

}