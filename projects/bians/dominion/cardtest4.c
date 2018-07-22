/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for card adventurer.
 * The rule for card adventurer is Reveal cards from your deck
 * until you reveal 2 Treasure cards. Put those Treasure cards
 * into your hand and discard the other revealed cards.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "adventurer"

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
    cardEffect(adventurer, choice1, choice2, choice3, &postState, handpos, &maxBonus);

    // ----------- TEST 1: player 1 has 2 more cards in hand--------------
    printf("\nTEST 1: player 1 has 2 more cards in hand\n");
    assert(postState.handCount[player1], prevState.handCount[player1]+2);

    // ----------- TEST 2: player 1 has 2 more Treasure cards in hand--------------
    printf("\nTEST 2: player 1 has 2 more Treasure cards in hand\n");

    // calculate prev Treasure cards and post Treasure cards
    int preTreasureCount = 0, postTreasureCount = 0;
    int i, card;
    for (i = 0; i < prevState.handCount[player1]; i++)
    {
        card = prevState.hand[player1][i];
        if (card == copper || card == silver || card == gold)
        {
            preTreasureCount++;
        }
    }
    for (i = 0; i < postState.handCount[player1]; i++)
    {
        card = postState.hand[player1][i];
        if (card == copper || card == silver || card == gold)
        {
            postTreasureCount++;
        }
    }
    assert(preTreasureCount+2, postTreasureCount);

    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}






















