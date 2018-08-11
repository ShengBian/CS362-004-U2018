/**********************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for function isGameOver().
 * The rule for function gainCard() is Checks the number of provinces
 * or if three supply piles are empty to determine if the game is over.
 ***********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "isGameOver()"

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

    //I created two state before cardEffect and after cardEffect
    struct gameState G;
    memset(&G,23,sizeof(struct gameState));

    //initialize a game state and player cards
    initializeGame(numPlayer, k, seed, &G);
    printf("----------------- Testing function %s ----------------\n", TESTCARD);

    // ----------- TEST 1: Three supply pile are at 0, the game ends --------------
    printf("\nTEST 1: Three supply pile are at 0, the game ends\n");
    G.supplyCount[0] = 0;
    G.supplyCount[1] = 0;
    G.supplyCount[2] = 0;
    assert(isGameOver(&G),1);

    // ----------- TEST 2: stack of Province cards is empty, the game ends--------------
    printf("\nTEST 2: stack of Province cards is empty, the game ends\n");
    G.supplyCount[0] = 2;
    G.supplyCount[1] = 2;
    G.supplyCount[2] = 2;
    G.supplyCount[province] = 0;
    assert(isGameOver(&G),1);

    // ----------- TEST 3: stack of Province cards is NOT empty and three supply pile are NOT at 0, the game continues--------------
    printf("\nTEST 3: stack of Province cards is NOT empty and three supply pile are NOT at 0, the game continues\n");
    G.supplyCount[0] = 2;
    G.supplyCount[1] = 2;
    G.supplyCount[2] = 2;
    G.supplyCount[province] = 1;
    assert(isGameOver(&G),0);


    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}
