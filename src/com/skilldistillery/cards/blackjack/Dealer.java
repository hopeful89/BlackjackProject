package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer extends Player {
	private Deck dealerDeck = new Deck();

	public Dealer() {
		super();
	};

	public Card dealCard() {
		return dealerDeck.dealCard();
	}

	public void shuffleDeck() {
		dealerDeck.shuffle();
	}

	@Override
	public void lookAtHand() {
		if (hand.getCards().size() == 2) {
			System.out.println("Dealer: Hidden, " + "" + hand.getCards().get(hand.getCards().size() - 1).toString());

		} else if (hand.getCards().size() == 1) {
			System.out.println("Dealer: Hidden");
		} else {
			System.out.println("Dealer: " + hand.toString());
		}

	}

}
