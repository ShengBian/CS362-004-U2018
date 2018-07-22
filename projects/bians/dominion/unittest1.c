/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for function shuffle().
 * The rule for function shuffle() is Getter for gameState returning
 * the card in a specific position of the hand.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "shuffle()"

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
    int player1 = 0;

    //I created two state before cardEffect and after cardEffect
    struct gameState G;
    memset(&G,23,sizeof(struct gameState));

    //initialize a game state and player cards
    initializeGame(numPlayer, k, seed, &G);
    printf("----------------- Testing function %s ----------------\n", TESTCARD);


    // ----------- TEST 1: Cards are shuffled after shuffle() --------------
    printf("\nTEST 1: Cards are shuffled after shuffle() \n");
    int pre_shuffle = G.deck[player1][0];
    shuffle(player1,&G);
    int post_shuffle = G.deck[player1][0];
    if (pre_shuffle != post_shuffle){
        printf("PASSED\n");
    } else{
        printf("FAILED\n");
        count++;
    }

    // ----------- TEST 2: Cards Number are not changed after shuffle() --------------
    printf("\nTEST 2: Cards Number are not changed after shuffle() \n");
    G.deckCount[player1] = 5;
    shuffle(player1,&G);
    assert(G.deckCount[player1], 5);

    // ----------- TEST 3: Cards Number are not changed after shuffle() --------------
    printf("\nTEST 3: Cards Number is 0, shuffle() returns -1\n");
    G.deckCount[player1] = 0;
    assert(shuffle(player1,&G), -1);


    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}
