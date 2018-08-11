/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for function gainCard().
 * The rule for function gainCard() is Adds a specific card to
 * a players hand, deck, or trash. Checks for enough supply of
 * the card then puts the card in the directed location.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "gainCard()"

int count = 0;

// I created assert function to print out test result
int assert(int a, int b) {
    if (a == b) {
        printf("PASSED\n");
        return 1;
    }
    else {
        printf("FAILED\n");
        count++;
        return 0;
    }
}


int main () {
    //set up all variables, reference from Assignment 3 Assistance (2)
    int seed = 2500;
    int numPlayer = 2;
    int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
    int player = 0;

    //I created two state before cardEffect and after cardEffect
    struct gameState G;
    memset(&G,23,sizeof(struct gameState));

    //initialize a game state and player cards
    initializeGame(numPlayer, k, seed, &G);
    printf("----------------- Testing function %s ----------------\n", TESTCARD);

    int discard = 0, deck = 1, hand = 2;

    // ----------- TEST 1: Supply pile is empty, shuffle() returns -1 --------------
    printf("\nTEST 1: Supply pile is empty, shuffle() returns -1\n");
    G.supplyCount[gold] = 0;
    assert(gainCard(gold,&G, deck, player), -1);

    // ----------- TEST 2: Add card to deck for player --------------
    G.supplyCount[gold] = 40;
    printf("\nTEST 2: Add card to deck for player\n");
    int prevDeck = G.deckCount[player];
    gainCard(gold, &G, deck, player);
    assert(prevDeck+1, G.deckCount[player]);


    // ----------- TEST 3: Add card to discard for player --------------
    G.supplyCount[gold] = 40;
    printf("\nTEST 3: Add card to discard for player\n");
    int prevDiscard = G.discardCount[player];
    gainCard(gold, &G, discard, player);
    assert(prevDiscard+1, G.discardCount[player]);

    // ----------- TEST 4: Add card to hand for player --------------
    G.supplyCount[gold] = 40;
    printf("\nTEST 4: Add card to hand for player\n");
    int prevHand = G.handCount[player];
    gainCard(gold, &G, hand, player);
    assert(prevHand+1, G.handCount[player]);


    // ----------- TEST 5: Decrease number in supply pile --------------
    G.supplyCount[gold] = 40;
    printf("\nTEST 5: Decrease number in supply pile\n");
    int prevGold = G.supplyCount[gold];
    gainCard(gold, &G, hand, player);
    assert(prevGold-1, G.supplyCount[gold]);

    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}
