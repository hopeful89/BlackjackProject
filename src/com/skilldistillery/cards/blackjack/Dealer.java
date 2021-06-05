package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer extends Player {
	private Deck dealerDeck = new Deck();
	private int minimumDeckSize = 15;

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
		System.out.println("Dealer: " + hand.toString());
	}

	public void handAtDeal() {
			if (hand.getCards().size() == 1) {
			System.out.println("Dealer: Hidden");
			}else {
				System.out.println("Dealer: Hidden, " + "" + hand.getCards().get(hand.getCards().size() - 1).toString());
			}
	}

	public void getNewDeck() {
		if(dealerDeck.getDeckSize() < minimumDeckSize) {
			dealerDeck = new Deck();
		}
	}
	
	public int getDeckCount() {
		return dealerDeck.getDeckSize();
	}

}
