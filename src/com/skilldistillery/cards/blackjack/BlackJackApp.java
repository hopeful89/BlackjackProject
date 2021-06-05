package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

public class BlackJackApp {
	public static final int BLACKJACKNUMBER = 21;

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		BlackJackApp app = new BlackJackApp();
		app.launch(kb);
		kb.close();
	}

	private void launch(Scanner kb) {
		Player player = new Player();
		Dealer dealer = new Dealer();
		boolean playerTurn = true;
		boolean dealerTurn = true;
		boolean playerIsPlaying = true;

		dealCardsStart(player, dealer);
		// check for blackjack on draw for player

		if (!player.declareBlackJack()) {
			while (playerTurn && !player.isBust() && !player.scoreOfTwentyOne()) {
				currentValue(player);
				playerTurn = playerTurn(kb, player, dealer);
			}
			if (player.isBust()) {
				currentValue(player);
				System.out.println("Player is BUST");
			}
		}

		if (!dealer.declareBlackJack() && !player.declareBlackJack() && !player.isBust()) {
			while (dealerTurn && !dealer.isBust()) {
				dealerTurn = dealerTurn(dealer);
			}
			if (dealer.isBust()) {
				dealer.lookAtHand();
				System.out.println("Dealer is BUST");
			}
		}

		winnerAnnouncement(player, dealer);
	}

	public static void winnerAnnouncement(Player player, Dealer dealer) {
		boolean pushedHand = player.declareBlackJack() && dealer.declareBlackJack()
				|| dealer.handValue() == player.handValue();
		
		boolean playerBlackJack = player.declareBlackJack() && !dealer.declareBlackJack();
		
		boolean playerWins = dealer.isBust() && !player.isBust()
				|| !dealer.declareBlackJack() && (player.handValue() > dealer.handValue() && !player.isBust());
		
		if (playerBlackJack) {
			System.out.println("BLACKJACK!!!!");

		} else if (playerWins) {
			System.out.println("You beat the dealer. Get your chips.");
		} else if (pushedHand) {
			System.out.println("Its a push");
		} else if (dealer.declareBlackJack()) {
			System.out.println("Dealer hit blackjack. You lose.");
		} else {
			System.out.println("Dealer wins.");
		}
	}

	private boolean dealerTurn(Dealer dealer) {
		if (dealer.handValue() < 17) {
			dealer.addCardToHand(dealer.dealCard());
			dealer.lookAtHand();
			currentValue(dealer);
			return true;
		} else {
			dealer.lookAtHand();
			currentValue(dealer);
			return false;
		}
	}

	public void currentValue(Player player) {
		System.out.println();
		System.out.println("Current value: " + player.handValue());
	}

	public boolean playerTurn(Scanner kb, Player player, Dealer dealer) {
		String userChoice = "";

		System.out.println("********************************************");
		System.out.println();
		System.out.print("Would you like to hit or stay? ");
		userChoice = kb.nextLine();

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
