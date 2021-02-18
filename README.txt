=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. You may copy and paste from your proposal
  document if you did not change the features you are implementing.

  1. Concept 1: 2D Array

- What specific feature of your game will be implemented using this concept?
    The state of the board will be implemented using this feature, i.e. which spaces of the board are empty, or belong to one of the players.


- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.
    It makes sense to use a 2D array because the game board is basically a set of cells each containing a piece of data. A 2D array makes the most sense because it will be useful to check the cells around a specific cell when looking for a winner.

    This will be non trivial because I will have to determine if a player can put their coin in a certain column and make sure the coin goes to the lowest empty cell in order to correctly implement the game.
  

  2. Concept 2: Complex search of the game state
- What specific feature of your game will be implemented using this concept?
    I will use this to determine if the current board contains a winner.


- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.
    This makes sense because the program must be able to find any 4 adjacent coins either horizontally, vertically or diagonally to determine if a player has won. I think the most complex part of this search will be detecting diagonal wins.
    

  3. Concept 3: JUnit Testing

- What specific feature of your game will be implemented using this concept?
    I tested both my File IO and calculating my win state.


- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.
    It makes sense to test these areas because they could both contain edge cases that could cause bugs if they are not exhaustively tested. This is nontrivial because there are many corner cases to test that are central to the game’s playability. 
  

  4. Concept 4: File I/O

- What specific feature of your game will be implemented using this concept?
    I will use File I/O to save high scores. I will define high scores as the least number of moves it took a player to win. For example, if Jim beat Tom in 3 moves, then Tom beat Jim in 8 moves and those were the only two games ever played, the high scores would be displayed as follows:
1. Jim beat Tom in 4 moves
2. Tom beat Jim in 8 moves


- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.
    This will be non-trivial because each entry into my txt file will contain 3 vital pieces of information, two strings and an integer that will need to be parsed and displayed correctly.

  


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game.java: This is the main class of my game and serves to assemble the GUI and creates all pop up windows and dialogs.
  It also handles the layout of the top menu bar.
  GameBoard.java: This class keeps track of the boardstate, player names, who's turn it is, checks for winners, and creates
  a few of the pop-ups in its methods to give game.java for data that game.java cannot access easily. It also handles drawing the board to the screen.
  HighScores.java: This class handles all of the file IO including reading a file, storing that data in a TreeMap,
  updating this TreeMap with new entries and writing this data back to the file.
  ScoreEntry: A small class that simply stores the winner and loser of a match. Used in HighScores to keep track of 
  game data.
  MyTests.java: Holds my JUnit Tests.

- Revisit your proposal document. What components of your plan did you end up
  keeping? What did you have to change? Why?
  I kept every part of my plan. I had to add a ScoreEntry class because I could not think of an easier way to attach the two player names
  to the score in a clean way.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  The hardest part for me was changing certain parts of the GUI after events happened. For instance, updating 
  the highscores list with a new match after it finishes.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  Over all, I think my game is fairly well designed. I think my private state is very well encapsulated. If I could refactor
  I would not make the GameBoard constructor take in a frame and a HighScores. I only did this because I could not figure out how to
  update certain parts of the GUI outside of the GameBoard.java class. Otherwise I think my program is well 
  seperated.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.


