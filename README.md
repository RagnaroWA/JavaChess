# Manual Test Plan

* GUI testing is relatively difficult, so to test the GUI I wrote a manual test plan for the GUI testing.
* Many GUI testing actually tested in the JUnit tests, but this test plan can give an overview about the interface.

## Start the game

* At first, each player need to input their own names for the game.
* Then to click the start to start the chess game.

![start1](image/start1.png)
![start2](image/start2.png)
![start3](image/start3.png)
![start4](image/start4.png)

## Chess Move Test

* Test whether the move for chess piece is correct.

![move1](image/move1.png)
![move2](image/move2.png)

## Chess Capture Test

* Test whether the capture for chess piece is correct.

![capture1](image/capture1.png)
![capture2](image/capture2.png)

## Chess Undo Test

* Test the undo functionality.

![undo1](image/undo1.png)
![undo2](image/undo2.png)

## Chess Restart Test

* Test the restart functionality.

![restart1](image/restart1.png)
![restart2](image/restart2.png)
![restart3](image/restart3.png)

## Chess Forfeit Test

* Test the forfeit functionality.
* After "Zecheng" forfeit, the score of "Bot" is increased by 1.

![forfeit1](image/forfeit1.png)
![forfeit2](image/forfeit2.png)

## Invalid Actions Test

* Invalid moves.
* Selecting the other's chess.
* Want to move the empty.

![action1](image/action1.png)
![action2](image/action2.png)
![action3](image/action3.png)

## Stalemate And Check Test

* Test for stalemate condition.
* Test for check condition.

![sc1](image/sc1.png)
![sc2](image/sc2.png)

## Exit the Game

* Exit Button to exit the game.

![exit1](image/exit1.png)
