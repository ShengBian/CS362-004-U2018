/**********************************
 * Author: Sheng Bian
 * Date: August 5, 2018
 * Description: This is random test suite for card smithy.
 * The rule for card smithy is draw 3 cards.
 ***********************************/

#include <time.h>
#include <math.h>
#include "dominion.h"
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include "rngs.h"
#include "dominion_helpers.h"

#define TESTCARD "smithy"

// global variable to store failed cases
int drawThreeCardFailNum = 0;

// This function is to run test cases for each random cases, if failed, increase the failed number
void testSmithy(int player, struct gameState *postState) {
    // create a previous game state to compare with game state after running cardEffect()
    struct gameState prevState;
    memcpy(&prevState,postState,sizeof(struct gameState));

    // data necessary for running running cardEffect()
    int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, maxBonus = 0;
    cardEffect(smithy, choice1, choice2, choice3, postState, handpos, &maxBonus);

    // using drawCard() function on prevState for comparsion
    drawCard(player,&prevState);
    drawCard(player,&prevState);
    drawCard(player,&prevState);
    discardCard(0, player, &prevState, 0);
    
    // compare cards in hand
    if (postState->handCount[player] != prevState.handCount[player] || postState->deckCount[player] != prevState.deckCount[player]){
        drawThreeCardFailNum++;
    }
    
}

int main () {
    int randomCaseNum = 5500;
    int player, deckCount, handCount, discardCount;
    // max player is 4
    int numberOfPlayers[] = {2,3,4};
    struct gameState state;
    srand(time(NULL));

    // randomize the game state
    int i, j;
    for (j = 0; j < randomCaseNum; j++) {
        for (i = 0; i < sizeof(struct gameState); i++) {
            ((char*)&state)[i] = floor(Random() * 256);
        }
        state.numPlayers = numberOfPlayers[rand() % 3];  // random player number
        state.numBuys = 1;
        state.playedCardCount = floor(Random() * (MAX_DECK-1)); // random card
        player = state.numPlayers - 2; // random player
        deckCount = floor(Random() * MAX_DECK); // random deck count
        handCount = floor(Random() * MAX_HAND); // random hand count
        discardCount = floor(Random() * MAX_DECK); //random discard count
        state.whoseTurn = player;
        // assign the data for different players
        for (i = 0; i < state.numPlayers; i++) {
            state.deckCount[i] = deckCount;
            state.handCount[i] = handCount;
            state.discardCount[i] = discardCount;
        }
        // test council room card
        testSmithy(player, &state);
    }
    int count = drawThreeCardFailNum;

    printf("----------------- Random Testing Card: %s ----------------\n", TESTCARD);

    // if no failed cases, successfully
    if (count == 0) {
        printf("\n >>>>> SUCCESS: Testing complete %s, running %d cases <<<<<\n\n", TESTCARD, randomCaseNum);
    }
    // if failed, print out detailed report
    else {
        printf("\n >>>>> TEST FAILED %s, Running %d Cases, Total Failed Cases: %d <<<<<\n\n", TESTCARD, randomCaseNum, count);
        printf("\nTEST 1: Selected player draws 3 cards from deck failed, the number of failed cases: %d\n",drawThreeCardFailNum);
    }

    return 0;
}