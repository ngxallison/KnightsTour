/* THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Allison_Ng */

import java.util.Stack;

// Do you know the chess piece that looks like a horse? It's called a "Knight",
// and the "Knight's Tour" is a famous problem where you want the Knight to move
// around a chess board such that it visits every position on the board exactly once.
// Recall that the Knight can move in the shape of the letter "L" in any direction
// IMPORTANT: See our A3 handout for examples and more detailed instructions/hints!

public class KnightTour {
	
    // Implement and print a Knight's Tour solution, using
    // the chess board defined in class KnightBoard. Your solution must
    // use a Stack to keep track of different possible sequences of moves
    // for the Knight, in order to discover and return a valid tour. 

    // NOTE: There can be several distinct valid tours; your job is to find
    // and print only one valid tour (the first valid one you discover).
    // Please do NOT attempt to discover all possible valid tours! To get
    // a sense of how many valid board configurations exist for different
    // board sizes, see "The Knight's Paths" table here:
    // http://www.behnel.de/knight.html

    /** tour method is where you add your code for implementing
    * a Knight Tour's solution for an n*n chess board
    * @param n size of the board
    * @return KnightBoard object with a valid Knight Tour
    */
    public static KnightBoard tour(int n){
      // Your solution must utilize the stack "candidates" below,
      // to keep track of different possible sequences of Knight moves
      Stack<KnightBoard> candidates = new Stack<KnightBoard>();
      KnightBoard kb = new KnightBoard(n); // create initial board of size n*n
      candidates.push(kb); // push the initial board onto the stack. The "Search List"

      /*Arrays in order to hold the possible x "coordinate" moves. Essentially moves x & y by 1, -1, 2, or -2 in their respective pairs so that the knight moves in an L shape.
       *index 0 of both x & y are a pair, index 1, and so on. 
       */
      int x[] = new int[] {1, 2, 2, 1, -1, -2, -2, -1}; //x coordinate array
  	  int y[] = new int[] {2, 1, -1, -2, -2, -1, 1, 2}; //y coordinate array
  	  
  	  //while loop that checks if the candidates board (the "search list" of possible answers) is empty
      while (!candidates.empty()) {
    	  //pops a board (which is a possible move) off the stack and stores it into KnightBoard kb. Gets rid of the potential move from the "Search List" stack
    	  kb = candidates.pop();
    		  
    	  	//if statement that checks if all possible positions on the board have been visited (a solution has been found)
    		 if (kb.getMoveCount() == n * n) {
    			 return kb; //returns the current board (with the solution) if all possible positions have been visited
    		 } else { //if the solution has NOT been found, then this code block runs to search for any possible moves
    			/*for loop that runs to 8 in order to check all possible moves of the knight. 
    			 * There are 8 possible moves, and each value corresponds with an index of the x & y arrays. The for loop runs through theses indexes and 
    			 * uses the move() method to verify whether or not a movement in that direction (coordinate combination) is possible (verifies its on the board and hasn't
    			 * been visited).
    			 */
    			for (int i = 0; i < 8; i++) {
    				/*Creates a temporary KnightBoard which is a copy of kb. This prevents the position tracking numbers from being mixed up 
    				if backtracking occurs.
    				*/
    				KnightBoard tempBoard = kb.copyBoard(); 
    				if (tempBoard.move(tempBoard.getCurrentX() + x[i], tempBoard.getCurrentY() + y[i])) {
    					candidates.push(tempBoard); //if the move is found to be possible in that direction, then it will be pushed on to the "Search List" stack as a possible move
    				}
    		 }
    	  }
      }
      //if statement that checks if the "Search List" is empty. If it is found to be empty, then it will return the current KnightBoard (kb)
      if (candidates.empty()) {
    	  return kb; //returns current KnightBoard
      }
      
      return kb;//returns current KnightBoard
    }


    // Do NOT modify this main method. If you need to add code for
    // testing your solution, just make sure to comment it out before submission
    public static void main(String[] args) {
      int n = 3; // default board size if user didn't specify
      // pass in parameter n from command line
      if (args.length == 1) {
          n = Integer.parseInt(args[0].trim());
          if (n < 3 || n > 8) {
              System.out.println("Incorrect parameter (n must be >= 3 and <= 8)");
              System.exit(-1);
          }
      }
      long startTime = System.nanoTime();
      KnightBoard winner = KnightTour.tour(n);
      long endTime = System.nanoTime();
      double delta = (endTime - startTime) / 1e6;
      // Display the solution you discovered:
      System.out.println("\nPossible Knight Tour with max #squares visited in this ("+n+"x" +n+") board:");
      winner.printChessBoard();
      System.out.println("\n(Time to find this solution = " + delta + " milliseconds)");
    }
}
