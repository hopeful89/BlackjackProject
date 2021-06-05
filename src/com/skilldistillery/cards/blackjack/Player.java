package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;

public class Player {
	protected BlackJackHand hand = new BlackJackHand();
	private int betMoney = 50;
	
	public Player(){};
	
	public void addCardToHand(Card card) {
		hand.addCard(card);
	}
	
	public void lookAtHand() {
		System.out.println("Player: " + hand.toString());
	}
	
	public void foldHand() {
		hand.clearHand();
	}
	
	public boolean declareBlackJack() {
		return hand.isBlackJack();
	}
	
	public int handValue() {
		return hand.getHandValue(); 
	}
	
	public boolean isBust() {
		return hand.isBust();
	}
	
	public boolean scoreOfTwentyOne() {
		if(hand.getHandValue() == BlackJackHand.BLACKJACKNUMBER) {
			return true;
		}else {
			return false;
		}
	}

	public int getBetMoney() {
		return betMoney;
	}

	public void setBetMoney(int betMoney) {
		this.betMoney += betMoney;
	}
	

}
