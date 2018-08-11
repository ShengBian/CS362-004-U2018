/**********************************
 * Author: Sheng Bian
 * Date: August 5, 2018
 * Description: This is random test suite for card adventurer.
 * The rule for card adventurer is Reveal cards from your deck
 * until you reveal 2 Treasure cards. Put those Treasure cards
 * into your hand and discard the other revealed cards.
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

#define TESTCARD "adventurer"

// global variable to store failed cases
int drawTwoCardFail = 0;
int drawTreasureCardFail = 0;

// This function is to run test cases for each random cases, if failed, increase the failed number
void testAdventurer(int player, struct gameState *postState) {
    // create a previous game state to compare with game state after running cardEffect()
    struct gameState prevState;
    memcpy(&prevState,postState,sizeof(struct gameState));

    // variables to store different data
    int tempCard1, tempCard2;
    int j = 0;
    int PostStateTreasureCardCount = 0;
    int PreStateTreasureCardCount = 0;
    int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, maxBonus = 0;
    cardEffect(adventurer, choice1, choice2, choice3, postState, handpos, &maxBonus);

    int temp[MAX_HAND];
    int drawTreasureCount = 0;

    // manually draw two treasure cards from deck
    while(drawTreasureCount < 2) {
        if (prevState.deckCount[player] < 1) {
            shuffle(player, &prevState);
        }
        drawCard(player, &prevState);
        tempCard1 = prevState.hand[player][prevState.handCount[player] - 1];
        if (tempCard1 == copper || tempCard1 == silver || tempCard1 == gold)
            drawTreasureCount++;
        else{
            temp[j] = tempCard1;
            prevState.handCount[player]--;
            j++;
        }
    }

    while(j - 1 >= 0) {
        prevState.discard[player][prevState.discardCount[player]++] = temp[j - 1];
        j = j - 1;
    }
    // check treasure card in prev state and post state
    int i;
    for (i = 0; i < postState->handCount[player]; i++) {
        tempCard2 = postState->hand[player][i];
        if (tempCard2 == copper || tempCard2 == silver || tempCard2 == gold) {
            PostStateTreasureCardCount++;
        }
    }
    for (i = 0; i < prevState.handCount[player]; i++) {
        tempCard2 = prevState.hand[player][i];
        if (tempCard2 == copper || tempCard2 == silver || tempCard2 == gold) {
            PreStateTreasureCardCount++;
        }
    }

    // if treasure cards are not equal, failed!
    if (PostStateTreasureCardCount != PreStateTreasureCardCount) {
        drawTreasureCardFail++;
    }

    // check if selected player draw two cards successfully
    if(postState->handCount[player] != prevState.handCount[player]
            || postState->deckCount[player] != prevState.deckCount[player]
            || postState->discardCount[player] != prevState.discardCount[player])
    {
        drawTwoCardFail++;
    }
}

int main () {
    int randomCaseNum = 5500;
    int player;
    int numberOfPlayers[] = {2,3,4}; // max player is 4
    int treasures[] = {copper,silver,gold};
    int treasureCardCount;
    struct gameState state;
    srand(time(NULL));
    int minCard = 3; // at least 3 card in deck
            
    // randomize the game state
    int i, j;
    for (j = 0; j < randomCaseNum; j++) {
        for (i = 0; i < sizeof(struct gameState); i++) {
            ((char*)&state)[i] = floor(Random() * 256);
        }
        state.numPlayers = numberOfPlayers[rand() % 3]; // random number of players
        state.numBuys = 1;
        state.playedCardCount = floor(Random() * (MAX_DECK-1));
        player = state.numPlayers - 2; // random player
        state.deckCount[player] = floor(Random() * ((MAX_DECK - minCard) + 1) + minCard);
        treasureCardCount = floor(Random() * ((state.deckCount[player] - minCard) + 1) + minCard);

        // set random data
        for (i = 0; i < treasureCardCount; i++) {
            state.deck[player][i] = treasures[rand() % 3];
        }
        state.discardCount[player] = 0;
        state.handCount[player] = floor(Random() * ((MAX_HAND - minCard) + 1) + minCard);
        state.whoseTurn = player;

        testAdventurer(player, &state);
    }
    int count = drawTreasureCardFail + drawTwoCardFail;

    printf("----------------- Random Testing Card: %s ----------------\n", TESTCARD);

    // if no failed cases, successfully
    if (count == 0) {
        printf("\n >>>>> SUCCESS: Testing complete %s, running %d cases <<<<<\n\n", TESTCARD, randomCaseNum);
    }
    // if failed, print out detailed report
    else {
        printf("\n >>>>> TEST FAILED %s, Running %d Cases, Total Failed Cases: %d <<<<<\n\n", TESTCARD, randomCaseNum, count);
        printf("\nTEST 1: Selected player has 2 more cards in hand failed, the number of failed cases: %d\n", drawTwoCardFail);
        printf("\nTEST 2: Selected player has 2 more Treasure cards in hand failed, the number of failed cases: %d\n", drawTreasureCardFail);
    }

    return 0;
}