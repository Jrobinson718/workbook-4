package com.pluralsight;
import java.util.ArrayList;

public class Game {
    //   === Instance Variables
    private ArrayList<Player> players;
    private Deck deck;
    private Console console;

    //   === Constructor ===
    public Game(){
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.console = new Console();
        System.out.println("New deck created! There are " + deck.cardsLeft() + " cards.");
        this.deck.shuffle();
        System.out.println("Deck has been shuffled.");
    }

    // Method to add a new player to the game and deal 2 cards
    public void addPlayerAndHand(String playerName) {
        Player newPlayer = new Player(playerName);
        Hand playerHand = newPlayer.getHand();

        System.out.println("\nDealing to " + playerName);

        Card card1 = deck.deal();
        if (card1 != null) {
            playerHand.deal(card1);
            System.out.println("Dealt: " + "\n" + card1.toString());
        }else {
            return;
        }

        Card card2 = deck.deal();
        if (card2 != null) {
            playerHand.deal(card2);
            System.out.println("\nDealt: " + "\n" + card2.toString());
        }

        this.players.add(newPlayer);
        System.out.println("\n" + playerName + " has joined the game!\n");
        newPlayer.displayPlayerHand();

        if (playerHand.getValue() == 21 && playerHand.getSize() == 2) {
            System.out.println("\n" + playerName + " has BLACKJACK!");
        }
    }

    // Manages a players turn
    // Lets the player hit or stand
    private void playerTurn(Player player) {
        System.out.println("\n-- " + player.getName() + "'s Turn --");

        if (player.getHand().getValue() == 21 && player.getHand().getSize() == 2) {
            System.out.println(player.getName() + " has Blackjack and stands.");
            return;
        }

        while (player.getHand().getValue() < 21) {
            player.displayPlayerHand();
            String choice = console.promptForString("Hot or stand? (H/S): ").toUpperCase();
            if (choice.equals("H")) {
                // Hit logic, includes busts and automatically winning from blackjack
                Card hitCard = deck.deal();
                if (hitCard == null) {
                    System.out.println("Deck is empty. Cannot hit.");
                    break;
                }
                hitCard.flip();
                System.out.println("\n" + player.getName() + " hits and gets: " + "\n" + hitCard.toString() + "\n");
                player.getHand().deal(hitCard);

                if (player.getHand().getValue() > 21) {
                    player.displayPlayerHand();
                    System.out.println("\n" + player.getName() + " BUSTS with " + player.getHand().getValue() + "!" + "\n");
                    player.setBust(true);
                    break;
                }

                if (player.getHand().getValue() == 21) {
                    player.displayPlayerHand();
                    System.out.println("\n" + player.getName() + " has 21! BLACKJACK" + "\n");
                    break;
                }
            } else if (choice.equals("S")) {
                System.out.println("\n" + player.getName() + " stands with " + player.getHand().getValue() + "." + "\n");
                break;
            }else {
                System.out.println("Invalid choice. Please enter 'H' to Hit or 'S' to Stand.");
            }
        }

        if (player.isBust() && player.getHand().getValue() < 21) {
            player.displayPlayerHand();
            System.out.println(player.getName() + " ends turn with " + player.getHand().getValue() + ".");
        }
    }

    // Determines and displays the winner of the game
    // The player(s) with the highest score less than or equal to 21 win.
    private void determineWinners() {
        System.out.println("\n=== Determining Winner(s) ===");
        int bestScore = 0;
        ArrayList<Player> winners = new ArrayList<>();
        boolean nonBustedPlayer = false;

        // Iterates through players to find the best score
        for (Player player : players) {
            if (!player.isBust()) {
                nonBustedPlayer = true;
                int playerScore = player.getHand().getValue();
                System.out.println(player.getName() + " has " + playerScore + " points.");

                if (playerScore > bestScore) {
                    bestScore = playerScore;
                    winners.clear();
                    winners.add(player);
                } else if (playerScore == bestScore) {
                    winners.add(player);
                }
            }else {
                System.out.println(player.getName() + " BUSTED with " + player.getHand().getValue() + ".");
            }
        }

        //Announces winner(s)
        if (!nonBustedPlayer && !players.isEmpty()) {
            System.out.println("\nAll players busted! No winner :(");
        }else if (!winners.isEmpty()) {
            System.out.println("\n === Winner(s)! with a score of " + bestScore  + " ===");
            for (Player winner : winners) {
                System.out.println("- " + winner.getName());
                winner.displayPlayerHand();
            }
        }else {
            System.out.println("\nNo players in the game, there can't be a winner.");
        }
    }

    // Main game loop
    // Sets up players
    // Manages turns
    // Determines winner
    public void play() {
        System.out.println("Welcome to BlackJack!");
        int numPlayers = console.promptForInt("How many players are playing? (1 or more): ");
        if (numPlayers <= 0) {
            System.out.println("No players selected. Ending game.");
        }

        for (int i = 0; i < numPlayers; i++) {
            String playerName = console.promptForString("Enter name for player " + (i + 1) + ": ");
            addPlayerAndHand(playerName);
        }

        if (!players.isEmpty()) {
            for (Player player : players) {
                if (!player.isBust()) {
                    playerTurn(player);
                }
            }
        }else {
            System.out.println("No players joined. Game over :(");
            return;
        }

        determineWinners();

        System.out.println("\nCards remaining in deck: " + deck.cardsLeft());
        System.out.println("Thanks for playing!");

    }
}
