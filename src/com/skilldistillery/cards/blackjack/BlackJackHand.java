package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;
import com.skilldistillery.cards.common.Rank;

public class BlackJackHand extends Hand{
	public static final int BLACKJACKNUMBER = 21;
	private final int removeSoftAce = 10;
	private int addedCards = 0;
	
	public BlackJackHand() {
		super();
	}

	@Override
	public int getHandValue() {
		boolean isSoft = false;
		int numOfAces = 0;
		int value = 0;
		//get value and establish if there are aces in hand
		for (Card card : super.cards) {
			value += card.getValue();
			if(card.getValue() == Rank.ACE.getValue()) {
				isSoft = true;
				numOfAces++;
			}
		}
		if(value > BLACKJACKNUMBER && isSoft) {
			return isSoft(value, numOfAces);
		}
		return value;
	}
	
	//determine is value exceeds 21 and ace in hand - 10 or return supplied value
	public int isSoft(int value, int numOfAces) {
		while(value > BLACKJACKNUMBER && numOfAces > 0) {
			value -= removeSoftAce;
			numOfAces--;
		}
		return value;
	}

	
	//if no cards dealt beyond initial draw and 21 in hand
	public boolean isBlackJack() {
		return getHandValue() == BLACKJACKNUMBER && addedCards < 3;
	}
	//hand value greater than 21
	public boolean isBust() {
		return getHandValue() > BLACKJACKNUMBER;
	}
	
	@Override
	public void addCard(Card card) {
		super.cards.add(card);
		addedCards++;
	}
		
	public List<Card> getCards(){
		List<Card> clonedCards = new ArrayList<>();
		clonedCards.addAll(super.cards);
		return clonedCards;
	}
	
	public void resetAddedCardsForNewGame() {
		addedCards = 0;
	}
	
}
