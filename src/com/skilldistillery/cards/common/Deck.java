package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> deck = new ArrayList<>();

	public Deck() {
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	public int checkDeckSize() {
		if (deck != null) {
			return deck.size();
		}
		return -1;
	}

	public Card dealCard() {
		return this.deck.remove(this.deck.size() - 1);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public int getDeckSize() {
		return deck.size();
	}
}
