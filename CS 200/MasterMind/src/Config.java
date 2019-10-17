//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: MasterMind
// Files: Config.java
// Course: CS200 Spring 2019
//
// Author: Lucas Laibly
// Email: laibly@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Megan Winarta
// Partner Email:   mvwinarta@wisc.edu
// Lecturer's Name: Marc Renault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Config {

    /**
     * A debugging technique is to add statements like the following at key places in your program:
     * if (Config.DEBUG) { System.out.println("DEBUG: value=" + value); } Then you can turn on or
     * off all these statements simply by changing the value of DEBUG here.
     */
    static final boolean DEBUG = false;

    /**
     * The acceptable set of symbols and length for a code. Your code should
     * use CODE_SYMBOLS and CODE_POSITIONS for the symbols and number of positions.
     *
     * With 6 symbols and 4 positions there are 6*6*6*6 == 6^4 == 1,296 possible
     * codes. With 8 symbols and 6 positions there are 8*8*8*8*8*8 == 8^6 == 262,144
     * possible codes. This is referred to as exponential growth. A problem that
     * seems just a little bit bigger actually may be much more difficult to solve.
     *
     * {'1', '2', '3', '4', '5', '6'} --> 4
     * {'a', 'b', 'c', 'd', 'e'} --> 3
     */
    public static final char[] CODE_SYMBOLS = new char[] {'a', 'b', 'c', 'd', 'e'};
    public static final int CODE_POSITIONS = 3;

    /**
     * The maximum number of guesses for a user to win.
     */
    public static final int MAX_GUESSES = 10;

    /**
     *  The length of the hits array
     */
    public static final int HITS_ARRAY_LENGTH = 2;

    /**
     * A "black hit" indicates a matching symbol in the same position in the
     * hidden code and guess.
     */
    public static final int BLACK_HITS_INDEX = 0;

    /**
     * A "white hit" indicates a matching symbol but different position in the 
     * hidden code and guess that is not already accounted for with other 
     * hits.
     */
    public static final int WHITE_HITS_INDEX = 1;

    /**
     * The symbol that indicates a black hit.
     */
    public static final char BLACK_HITS_SYMBOL = 'B';

    /**
     * The symbol that indicates a white hit.
     */
    public static final char WHITE_HITS_SYMBOL = 'W';
}