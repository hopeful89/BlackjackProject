package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;


import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class BlackJackHand extends Hand{
	private int addedCards = 0;
	private final int blackJackNumber = 21;
	
	public BlackJackHand() {
		super();
	}

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : super.cards) {
			value += card.getValue();
		}
		return value;
	}
	
	public boolean isBlackJack() {
		return getHandValue() == blackJackNumber && addedCards < 3;

	}
	
	@Override
	public void addCard(Card card) {
		super.cards.add(card);
		addedCards++;
	}
	
	public boolean isBust() {
		return getHandValue() > blackJackNumber;
	}
	
	public List<Card> getCards(){
		List<Card> clonedCards = new ArrayList<>();
		clonedCards.addAll(super.cards);
		return clonedCards;
		
	}
	
	//TODO Options  isSoft()  isHard()
}
