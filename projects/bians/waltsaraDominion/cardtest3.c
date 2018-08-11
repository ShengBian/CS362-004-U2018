/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for card smithy.
 * The rule for card smithy is draw 3 cards.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "smithy"

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
    int seed = 1000;
    int numPlayer = 2;
    int maxBonus = 10;
    int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
    int player1 = 0, handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0;

    //I created two state before cardEffect and after cardEffect
    struct gameState postState, prevState;
    memset(&postState,23,sizeof(struct gameState));
    memset(&prevState,23,sizeof(struct gameState));

    //initialize a game state and player cards
    initializeGame(numPlayer, k, seed, &postState);
    printf("----------------- Testing Card: %s ----------------\n", TESTCARD);

    //copy game state to prev state
    memcpy(&prevState, &postState, sizeof(struct gameState));

    // use the card village on postState
    cardEffect(smithy, choice1, choice2, choice3, &postState, handpos, &maxBonus);

    // ----------- TEST 1: player 1 draws 3 cards from deck--------------
    printf("\nTEST 1: player 1 draws 3 cards from deck\n");
    assert(postState.deckCount[player1],prevState.deckCount[player1]-3);

    // ----------- TEST 2: player 1 has 2 more cards in hand--------------
    printf("\nTEST 2: player 1 has 2 more cards in hand\n");
    assert(postState.handCount[player1], prevState.handCount[player1]+2);

    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}
