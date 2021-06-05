# BlackJack Project

### Overview

This is a console version of your favorite card game Blackjack!

Wagers are implement and you will be prompted for one.

You will be presented with an option to Hit, Stay, or Double Down.

Should you choose to hit and exceed 21 and not have an ace in your hand
the game is over. If you have more than one ace only one will be considered
a soft ace at a time. Once you exceed 21 a second time the next ace will 
become a soft ace.

If you choose to stay the dealer turn will begin.

Dealer will hit add cards to his hand until he reaches 17 or higher.

Once the dealer is complete, the game will decide and output who the winner is.

If you run out of money or choose to not play anymore the game ends.


### Technologies Used
* Java
* Enumerals
* Loops
* OOP - Abstraction, Encapsulation, Inheritance, Polymorphism
* Collections
* Throwable
* Exception handling

### How to Run

This is a console project. To run in eclipse please use the run button.


### Lessons Learned
This was an excellent project that forced me to consider visibility with access modifiers. Specifically the use of protected and sub class inheritance. I am starting to see a use of throwing my own exceptions when the user fails to provide valid input.  During some code re-factor, I was able to see the power of having ENUMs present.  This allowed me to use conditionals on the ENUM values without the need for another variable initialization for those primitive types.  
