package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class BlackJackHand extends Hand{
	private int addedCards = 0;
	
	public BlackJackHand() {
		super();
	}

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : super.cards) {
			value += card.getValue();
		}
		// change here or in isSoft to see push from Blackjack on both sides
		return isSoft(value);
		
	}
	
	//if no cards dealt beyond initial draw and 21 in hand
	public boolean isBlackJack() {
		return getHandValue() == BlackJackApp.BLACKJACKNUMBER && addedCards < 3;
	}
	//hand value greater than 21
	public boolean isBust() {
		return getHandValue() > BlackJackApp.BLACKJACKNUMBER;
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
	
	public int isSoft(int value) {
		int aceValue = 11;
		boolean aceInHand = false;
		
		for (Card card : super.cards) {
			if(card.getValue() == aceValue) {
				aceInHand = true;
			}
		}
		if(value > BlackJackApp.BLACKJACKNUMBER && aceInHand) {
			System.out.println("Soft Ace Activated");
			return value - 10;
		}
		return value;
	}
	//TODO Options  isSoft()  isHard()
}
