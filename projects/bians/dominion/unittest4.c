/******************************************
 * Author: Sheng Bian
 * Date: July 22, 2018
 * Description: This is unit test suite for function getCost().
 * The rule of function getCost() is Getter for the cost of
 * cards (hard coded into this function).
 * *****************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "getCost()"

int count = 0;

int assertEquals(char* cardName, int expectVal, int actualVal)
{
    printf("The cost of %s is %d\n", cardName, expectVal);
    if (expectVal == actualVal) {
        printf("PASSED\n\n");
        return 1;
    }
    else {
        printf("FAILED. The expected cost is %d, the getCost function returns %d\n\n", expectVal, actualVal);
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

    assertEquals("curse", 0, getCost(curse));
    assertEquals("estate", 2, getCost(estate));
    assertEquals("duchy", 5, getCost(duchy));
    assertEquals("province", 8, getCost(province));
    assertEquals("copper", 0, getCost(copper));
    assertEquals("silver", 3, getCost(silver));
    assertEquals("gold", 6, getCost(gold));
    assertEquals("adventurer", 6, getCost(adventurer));
    assertEquals("council_room", 5, getCost(council_room));
    assertEquals("feast", 4, getCost(feast));
    assertEquals("gardens", 4, getCost(gardens));
    assertEquals("mine", 5, getCost(mine));
    assertEquals("remodel", 4, getCost(remodel));
    assertEquals("smithy", 4, getCost(smithy));
    assertEquals("village", 3, getCost(village));
    assertEquals("baron", 4, getCost(baron));
    assertEquals("great_hall", 3, getCost(great_hall));
    assertEquals("minion", 5, getCost(minion));
    assertEquals("steward", 3, getCost(steward));
    assertEquals("tribute", 5, getCost(tribute));
    assertEquals("ambassador", 3, getCost(ambassador));
    assertEquals("cutpurse", 4, getCost(cutpurse));
    assertEquals("embargo", 2, getCost(embargo));
    assertEquals("outpost", 5, getCost(outpost));
    assertEquals("salvager", 4, getCost(salvager));
    assertEquals("sea_hag", 4, getCost(sea_hag));
    assertEquals("treasure_map", 4, getCost(treasure_map));


    // print out results
    if (count) {
        printf("\n >>>>> TEST FAILED %s, Failed Cases: %d <<<<<\n\n", TESTCARD, count);
    }
    else {
        printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);
    }

    return 0;
}
