package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

public class BlackJackApp {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		BlackJackApp app = new BlackJackApp();
		app.launch(kb);
		kb.close();
	}

	private void launch(Scanner kb) {
		// Game loop
		boolean playerIsPlaying = true;
		Player player = new Player();
		while (playerIsPlaying) {
			
			// Instantiate new player/dealer for each game
			
			Dealer dealer = new Dealer();

			// Stop looping each sides turn when false
			boolean playerTurn = true;
			boolean dealerTurn = true;

			dealCardsStart(player, dealer);

			// if player hand is not blackjack
			if (!player.declareBlackJack()) {
				while (playerTurn && !player.isBust() && !player.scoreOfTwentyOne()) {
					playerTurn = playerTurn(kb, player, dealer);
				}
				if (player.isBust()) {
					currentValue(player);
					System.out.println("Player is BUST");
				}
				if (player.scoreOfTwentyOne()) {
					currentValue(player);
					System.out.println();
				}
			}
			// if the dealer nor the player has declared blackjack and player hand is not
			// bust
			if (!dealer.declareBlackJack() && !player.declareBlackJack() && !player.isBust()) {
				while (dealerTurn && !dealer.isBust()) {
					dealerTurn = dealerTurn(dealer);
				}
				if (dealer.isBust()) {
					System.out.println("Dealer is BUST");
				}
			}
			// declare winner of hand
			winnerAnnouncement(player, dealer);

			// check if player wishes to continue playing
			playerIsPlaying = playAgain(kb);
			player.foldHand();
		}
	}
	// does player want to continue

	private boolean playAgain(Scanner kb) {
		String userChoice = "";
		System.out.println();
		System.out.println("*****************");
		System.out.println("*  Play Again?  *");
		System.out.println("*****************");
		System.out.println("  Y|YES    N|NO  ");
		userChoice = kb.nextLine();

		if (userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("Yes")) {
			return true;
		} else {
			return false;
		}
	}

	// who won this hand
	public static void winnerAnnouncement(Player player, Dealer dealer) {
		// both dealer and player have BJ or hands are equal in value
		boolean pushedHand = player.declareBlackJack() && dealer.declareBlackJack()
				|| dealer.handValue() == player.handValue();

		// player has BJ and dealer does not
		boolean playerBlackJack = player.declareBlackJack() && !dealer.declareBlackJack();

		// dealer is bust and player is not bust or dealer doesn't have BJ
		// and player hand value is higher without being bust
		boolean playerWins = dealer.isBust() && !player.isBust()
				|| !dealer.declareBlackJack() && (player.handValue() > dealer.handValue() && !player.isBust());

		if (playerBlackJack) {
			System.out.println();
			System.out.println("*******YOU*******");
			System.out.println("*******HIT*******");
			System.out.println("****BLACKJACK****");
			System.out.println();
			System.out.println("The dealer hand was");
			dealer.lookAtHand();

		} else if (playerWins) {
			System.out.println();
			System.out.println("You beat the dealer. Get your chips.");
		} else if (pushedHand) {
			if (player.declareBlackJack() && dealer.declareBlackJack()) {
				System.out.println();
				System.out.println("DOUBLE BLACKJACK");
			}
			System.out.println("Its a push");
		} else if (dealer.declareBlackJack() && !player.isBust()) {
			System.out.println("Dealer hit blackjack. You lose.");
		} else {
			System.out.println("DEALER WINS");
		}
	}

	private boolean dealerTurn(Dealer dealer) {
		if (dealer.handValue() < 17) {
			dealer.addCardToHand(dealer.dealCard());
			dealer.lookAtHand();
			currentValue(dealer);

			if (dealer.handValue() >= 17) {
				return false;
			}
			return true;
		} else {
			System.out.println();
			dealer.lookAtHand();
			currentValue(dealer);
			return false;
		}
	}

	public void currentValue(Player player) {
		System.out.println("Current value: " + player.handValue());
		System.out.println();
	}

	public boolean playerTurn(Scanner kb, Player player, Dealer dealer) {
		String userChoice = "";
		System.out.println("********************************************");
		currentValue(player);
		System.out.print("Would you like to hit or stay? ");
		userChoice = kb.nextLine();
		System.out.println();

		if (userChoice.equalsIgnoreCase("hit")) {
			player.addCardToHand(dealer.dealCard());
			player.lookAtHand();
			return true;
		} else if (userChoice.equalsIgnoreCase("Stay")) {
			return false;
		} else {
			System.out.println("Invalid response");
			return true;
		}
	}

	public void dealCardsStart(Player player, Dealer dealer) {
		System.out.println("Welcome to BlackJack");
		System.out.println("Dealer approches and shuffles the deck");
		System.out.println();

		int startCount = 2;
		// comment out the shuffle to see the soft ace activate if not seen during
		// gameplay keep hitting to see the second as well
		dealer.shuffleDeck();

		while (startCount != 0) {
			player.addCardToHand(dealer.dealCard());
			player.lookAtHand();
			dealer.addCardToHand(dealer.dealCard());
			dealer.handAtDeal();
			--startCount;
		}

	}

}
