package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

public class BlackJackApp {
	//TODO consider a table class than has Player[] with options to allow more players or use more players as split hand
	private Player player = new Player();
	private Dealer dealer = new Dealer();
	private Scanner kb = new Scanner(System.in);
	private int winMultiplier = 2;
	private int playerBet = 0;

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.launch();
		app.kb.close();
	}

	private void launch() {
		// Game loop
		boolean playerIsPlaying = true;

		System.out.println("*******************Welcome to BlackJack*******************");

		//while player hasn't quit and hasn't lost their money
		while (playerIsPlaying && player.getBetMoney() > 0) {
			// Stop looping each sides turn when false
			boolean playerTurn = true;
			boolean dealerTurn = true;

			System.out.println("Dealer approches and shuffles the deck");
			System.out.println("Current deck count: " + dealer.getDeckCount());
			System.out.println();

			// get player bet and deal cards
			playerBet();
			dealCardsStart();

			// if player hand is not blackjack
			if (!player.declareBlackJack()) {
				while (playerTurn && !player.isBust() && !player.scoreOfTwentyOne()) {
					playerTurn = playerTurn();
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
			// if the dealer and the player has not declared blackjack and player hand is
			// not
			// bust
			if (!dealer.declareBlackJack() && !player.declareBlackJack() && !player.isBust()) {
				while (dealerTurn && !dealer.isBust()) {
					dealerTurn = dealerTurn();
				}
				if (dealer.isBust()) {
					System.out.println("Dealer is BUST");
				}
			}
			// declare winner of hand
			winnerAnnouncement();

			// check if player wishes to continue playing
			if (player.getBetMoney() <= 0) {
				System.out.println("***********************************************");
				System.out.println("The dealer took all your money and ran. GoodBye");
				System.out.println("***********************************************");
				break;
			} else {
				playerIsPlaying = playAgain();
				resetGameToStart();
			}
		}

	}

	// does player want to continue
	private void playerBet() {
		System.out.println("//////////////////////////////");
		System.out.println("\t You have $" + player.getBetMoney());
		System.out.println("//////////////////////////////");

		while (playerBet == 0) {
			try {
				System.out.print("Please enter your wager: ");
				System.out.println();
				playerBet = Integer.parseInt(kb.nextLine());
				if (playerBet > player.getBetMoney()) {
					throw new IllegalArgumentException("You tried to bet more than you have");
				}
			} catch (IllegalArgumentException e) {
				playerBet = 0;
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	private boolean playAgain() {
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
			System.out.println("GooooodBye!");
			return false;
		}
	}

	// who won this hand of BJ
	public void winnerAnnouncement() {
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
			player.winBetMoney(playerBet * (int)(Math.pow(winMultiplier, 2)));

		} else if (playerWins) {
			System.out.println();
			System.out.println("You beat the dealer. Get your chips.");
			player.winBetMoney(playerBet * winMultiplier);
		} else if (pushedHand) {
			if (player.declareBlackJack() && dealer.declareBlackJack()) {
				System.out.println();
				System.out.println("DOUBLE BLACKJACK");
			}
			System.out.println("Its a push");
		} else if (dealer.declareBlackJack()) {
			System.out.println("Dealer hit blackjack. You lose.");
			player.loseBetMoney(playerBet);
		} else {
			System.out.println("DEALER WINS");
			player.loseBetMoney(playerBet);
		}
	}

	private boolean dealerTurn() {
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
	// Print value of hand for either player
	public void currentValue(Player player) {
		System.out.println("Current value: " + player.handValue());
		System.out.println();
	}

	public boolean playerTurn() {
		String userChoice = "";
		
		System.out.println("********************************************");
		currentValue(player);
		System.out.print("Would you like to hit, stay, double down(dd)? ");
		userChoice = kb.nextLine();
		System.out.println();

		if (userChoice.equalsIgnoreCase("hit")) {
			player.addCardToHand(dealer.dealCard());
			player.lookAtHand();
			return true;
		} else if (userChoice.equalsIgnoreCase("Stay")) {
			return false;
		} else if (userChoice.equalsIgnoreCase("dd") && player.getBetMoney() > playerBet * 2) {
			playerBet *= 2;
			player.addCardToHand(dealer.dealCard());
			player.lookAtHand();
			if (!player.isBust()) {
				return true;
			} else {
				return false;
			}
		} else if (userChoice.equalsIgnoreCase("dd") && !(player.getBetMoney() > playerBet * 2)) {
			System.out.println("Not enough money to double down");
			return true;
		} else {
			System.out.println("Invalid response");
			return true;
		}
	}

	public void dealCardsStart() {
		int startCount = 2;
		// comment out the shuffle to see the soft ace activate if not seen during
		// game play keep hitting to see the second as well
		dealer.shuffleDeck();
		
		//deal out cards one each and show hand
		while (startCount != 0) {
			player.addCardToHand(dealer.dealCard());
			player.lookAtHand();
			dealer.addCardToHand(dealer.dealCard());
			dealer.handAtDeal();
			--startCount;
		}
	}

	// clears each hand, gets a new deck if below dealer threshold, resets player bet
	public void resetGameToStart() {
		dealer.getNewDeck();
		player.foldHand();
		dealer.foldHand();
		playerBet = 0;
	}
}
