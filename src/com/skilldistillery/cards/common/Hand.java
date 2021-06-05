package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

	protected List<Card> cards = new ArrayList<>();
	
	public Hand(){
		
	};

	public void addCard(Card card) {
		cards.add(card);
	}

	public abstract int getHandValue();
	
	public void clearHand() {
		cards.clear();
	}

	@Override
	public String toString() {
		return "" + cards ;
	}
	
	
}
