import java.util.*;
// Ranking == Diamonds > Clubs > Hearts > Spades

/**
 * The ChooseRandom class generates a random rank and suit for a playing card.
 */
class ChooseRandom extends assignment {
    Random rand = new Random();
    int rank = rand.nextInt(13) + 1;
    int suit = rand.nextInt(4) + 1;
}

/**
 * The class "AllCards" is a subclass of "assignment" and contains a method
 * "printAll()" that prints
 * all the cards in a deck.
 */
class AllCards extends assignment {
    void printAll() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                printCard(j + 1, i);
            }
        }
    }
}

/**
 * The "assignment" class in Java defines a method to print the name of a
 * playing card given its rank
 * and suit.
 */
public class assignment {
    static void printCard(int rank, int suit) {
        switch (suit) {
            case 0:
                System.out.println("Diamond " + returnCard(rank));
                break;
            case 1:
                System.out.println("Clubs " + returnCard(rank));
                break;
            case 2:
                System.out.println("Hearts " + returnCard(rank));
                break;
            case 3:
                System.out.println("Spades " + returnCard(rank));
                break;
        }
    }

    /**
     * The function "returnCard" takes an integer as input and returns the
     * corresponding card name
     * (Ace, Jack, Queen, King) or the integer itself if it does not match any of
     * the special card
     * values.
     */
    static String returnCard(int i) {
        switch (i) {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return Integer.toString(i);
        }
    }

    public static void main(String[] args) {
        // print all the card in a deck

        AllCards superviser = new AllCards();
        superviser.printAll();

        // The code is choosing a random card for player 1 and printing it.
        System.out.println("Here, we choose a random card for player 1 is");
        ChooseRandom player2 = new ChooseRandom();
        printCard(player2.rank, player2.suit);

        // This code is choosing a random card for player 2. It creates a new instance
        // of the
        // ChooseRandom class and assigns it to the variable player1. Then, it enters a
        // while loop that
        // checks if the rank and suit of player1 are the same as the rank and suit of
        // player 1's card
        // . If they are the same, it creates a new instance of ChooseRandom and assigns
        // it
        // to player1 again. This process continues until player1 has a different rank
        // and suit than
        // player2.
        System.out.println("Here, we choose a random card for player 2 is");
        ChooseRandom player1 = new ChooseRandom();
        while (player1.rank == player2.rank && player1.suit == player2.suit) {
            player1 = new ChooseRandom();
        }
        printCard(player1.rank, player1.suit);

        // This code block is determining the winner of a card game between two players.
        // If the both player have same suit then winner is dicided on the basis of the
        // rank of the suit.
        if (player1.suit < player2.suit) {
            System.out.println("Player 2 is winner...");
        } else if (player1.suit > player2.suit) {
            System.out.println("Player 1 is winner...");
        } else if (player1.rank > player2.rank) {
            System.out.println("Player 1 is winner...");
        } else {
            System.out.println("Player 2 is winner...");
        }
    }
}