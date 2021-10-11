package edu.cnm.deepdive;

import edu.cnm.deepdive.model.Card;
import edu.cnm.deepdive.model.Rank;
import edu.cnm.deepdive.model.Suit;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    List<Card> deck = new ArrayList<>();
    for (Suit suit: Suit.values()) {
      for (Rank rank: Rank.values()) {
        Card card = new Card(rank, suit);
        deck.add(card);
      }
    }
    //print out the natural order of the cards
    System.out.println(deck);
    //shuffle the deck
    Collections.shuffle(deck, new SecureRandom());
    System.out.println(deck);
    //sort the deck
    Collections.sort(deck); // or deck.sort(deck, null)
    System.out.println(deck);
    //sort in war order, use WarComparator as referee
    //now use an anonymous class, create and implement the interface.
    //if interface has exactly 1 unimplemented method, can write as a lambda, but can't have a field
    int numRanks = Rank.values().length;
    //could do some math: add 12 to ordinal enum, then mod 13. always sort smaller member before larger one
    deck.sort((card1, card2) ->
        (card1.getRank().ordinal() + numRanks-1) % numRanks - (card2.getRank().ordinal() + numRanks-1) % numRanks);
    System.out.println(deck);
  }

}
