package edu.cnm.deepdive;

import edu.cnm.deepdive.model.Card;
import edu.cnm.deepdive.model.Rank;
import edu.cnm.deepdive.model.Suit;
import edu.cnm.deepdive.model.Suit.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Trick {

  public static void main(String[] args) {
    Random rng = new SecureRandom(); //good for gaming.
    // Create a shuffled deck.
    List<Card> deck = getDeck(rng);
    List<Card> redPile = new ArrayList<>();
    List<Card> blackPile = new ArrayList<>();
    // Sort the deck into 2 piles, according to the rules of the trick.
    sortCards(deck, redPile, blackPile);
    // Swap a random number of cards between the 2 piles, take from the smaller of the two piles.
    swapCards(redPile,blackPile,rng);
    // Sort the piles.
    Collections.sort(redPile);
    Collections.sort(blackPile);
    // Count the number of pile contents.
    printContents(redPile, Color.RED);
    printContents(blackPile, Color.BLACK);
  }

  private static List<Card> getDeck(Random rng) {
    List<Card> deck = new ArrayList<>();//want to be able to add at index poins very easily
    for (Suit suit : Suit.values()) {
      for (Rank rank: Rank.values()) {
        deck.add(new Card(rank, suit));
      }
    }
    //every rank is in a suit, but not vice versa.
    Collections.shuffle(deck, rng);//using secure random
    return deck;
  }

  private static void sortCards(List<Card> deck, List<Card> redPile, List<Card> blackPile) {
    for (Iterator<Card> iter = deck.iterator(); iter.hasNext();) {
      Card selector = iter.next();
      Card card = iter.next();
      if (selector.getSuit().getColor() == Color.RED) {
        redPile.add(card);
      } else { // else the color is BLACK
        blackPile.add(card);
      }
    }
  }

  private static void swapCards(List<Card> redPile, List<Card> blackPile, Random rng) {
    int numToSwap = rng.nextInt(Math.min(redPile.size(), blackPile.size()) + 1);
    for (int i = 0; i < numToSwap; i++) {
      blackPile.add(redPile.remove(0)); //adds to end/bottom of the pile
      redPile.add(blackPile.remove(0));
    }
  }

  private static void printContents(List<Card> pile, Color color) {
    int colorCount = 0;
    for (Card card : pile) {
      if (card.getSuit().getColor() == color) {
        colorCount++;
      }
    }
    System.out.printf("%1$s pile contains %2$d %1$s cards: %3$s%n", color, colorCount, pile);
  }

}
