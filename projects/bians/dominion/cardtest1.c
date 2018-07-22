/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for card council_room.
 * The rule for card council_room is draw 4 cards, increase buys
 * by 1, and each other player draws a card.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "council_room"

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
    int player1 = 0, player2 = 1, handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0;

    //I created two state before cardEffect and after cardEffect
    struct gameState postState, prevState;
    memset(&postState,23,sizeof(struct gameState));
    memset(&prevState,23,sizeof(struct gameState));

    //initialize a game state and player cards
    initializeGame(numPlayer, k, seed, &postState);
    printf("----------------- Testing Card: %s ----------------\n", TESTCARD);

    //copy game state to prev state
    memcpy(&prevState, &postState, sizeof(struct gameState));

    // use the card council_room on postState
    cardEffect(council_room, choice1, choice2, choice3, &postState, handpos, &maxBonus);

    // ----------- TEST 1: player 1 draws 4 cards from deck--------------
    printf("\nTEST 1: player 1 draws 4 cards from deck\n");
    assert(postState.deckCount[player1],prevState.deckCount[player1]-4);

    // ----------- TEST 2: player 1 has 3 more cards in hand --------------
    printf("\nTEST 2: player 1 has 3 more cards in hand\n");
    assert(postState.handCount[player1], prevState.handCount[player1]+3);

    // ----------- TEST 3: player 1 buys increase by 1 --------------
    printf("\nTEST 3: player 1 buys increase by 1\n");
    assert(postState.numBuys, prevState.numBuys+1);

    // ----------- TEST 4: player 2 draws 1 card from deck --------------
    printf("\nTEST 4: player 2 draws 1 card from deck\n");
    assert(postState.deckCount[player2], prevState.deckCount[player2]-1);

    // ----------- TEST 5: player 2 has 1 more cards in hand --------------
    printf("\nTEST 5: player 2 has 1 more cards in hand\n");
    assert(postState.handCount[player2], prevState.handCount[player2]+1);

    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}

