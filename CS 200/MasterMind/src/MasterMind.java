//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: MasterMind
// Files: MasterMind.java
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

import java.io.EOFException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is the heart of the program. This is where the game exists.
 * It handles all actions and reactions that should be occuring.
 * MasterMind program.
 *
 * @author Lucas Laibly
 */
public class MasterMind
{

    /**
     * Prompts the user for a value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will consume an entire
     * line of input while reading an int. Leading whitespace is ignored.
     * If the value read is between min and max 
     * (inclusive), that value is returned. Otherwise, output 
     *    "Expected value between 0 and 10." 
     * where 0 and 10 are the values in the min and max parameters, respectively. 
     * Invalid input may be non-integer in which case the same error message is
     * displayed and the user is prompted again. 
     *
     * Note: This is a general purpose method to prompt for, read and validate an int 
     * within the min and max. This method should be tested for any min and max.
     *
     * @param input The Scanner instance to read from System.in.
     * @param prompt Output to the user.
     * @param min The minimum acceptable int value (inclusive).
     * @param min The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner input, String prompt, int min, int max)
    {
        String wrong = "Expected value between " + min + " and " + max +".";

        //Here we use a while loop with a nested try-catch block in it
        //we want to loop until the user enters a valid number so that
        // we can generate a valid seed for the game.
        while (true) {
            System.out.print(prompt);
            String test = input.nextLine();
            int intInputValue = 0;

            //the try looks to see if the user has entered an Integer using the
            //integer class, and specifically then the parseInt to
            //see if we can parse it as an integer
            try {
                intInputValue = Integer.parseInt(test);
                return intInputValue;
            }
            //if the value entered does not parse as interger we want to throw
            //a number format exception.
            //this means we will be erroring out becasue the entered value is not
            //an integer --> by erroring out, we will repeat the above.
            catch (NumberFormatException ne) {
                System.out.println(wrong);
            }
        }

        /*
        pretty sure this is the bad way to do it
            if(hold >= min && hold <= max)
            {
                flag = true;
                return hold;
            }

            else{
                System.out.println("Expected value between " + min + " and " + max +".");
            }
            */
    }

    /**
     * Returns the index within arr of the first occurrence of the specified character.
     * If arr is null or 0 length then return -1. For all arrays, don't assume a length
     * but use the array .length attribute.
     * @param arr  The array to look through.
     * @param ch   The character to look for.
     * @return The index within the array of the first occurrence of the specified
     *     character or -1 if the character is not found in the array.
     */
    public static int indexOf(char[] arr, char ch)
    {
        int index =0;


        //preliminary check to see if the array passed is either null or empty
        //we do not even want to bother continuing
        if(arr == null || arr.length == 0)
        {
            return -1;
        }

        //with a valid array we continue, getting and returning the correct index of the
        //wanted character
        for(int i =0; i < arr.length; i++)
        {
            if(arr[i] == ch)
            {
                index = i;
                return index;
            }
        }


        return -1;
    }

    /**
     * Generates the hidden code to be guessed by the user. The hidden code
     * returned is an array of characters with length numPositions.
     * The characters in the array are randomly chosen, in order starting at index 0,
     * from the symbols array.
     *    rand.nextInt( symbols.length)
     * is used to determine the index in the symbols array of each character
     * in the code. For all arrays, don't assume a length but use the array .length attribute.
     *
     * Example: 
     * if numPositions is 3 and symbols is the array {'A','B','C'}
     * the returned array will have a length of 3 and may contain any selection of 
     * the available symbols such as {'B','C','B'} or {'C','A','B'}.
     *
     * @param rand A random number generator.
     * @param numPositions  The number of symbols in the code.
     * @param symbols  The symbols to choose from.
     * @return An array of length numPositions of randomly chosen symbols.
     */
    public static char[] generateHiddenCode(Random rand, int numPositions, char[] symbols)
    {

        //we want to generate a hidden code to test
        char genHiddenCode[] = new char[numPositions];

        //for each position in our passed integer we want to randomize the next int
        //then send out hidden code as the value from out symbols index OF that new random int
        for(int i = 0; i < numPositions; i++)
        {
            int index = rand.nextInt(symbols.length);
            genHiddenCode[i] = symbols[index];
        }
        return genHiddenCode;
    }

    /**
     * Checks whether the code is the correct length and only contains valid symbols.
     * Uses the indexOf method you wrote to check whether each character in the input is in the 
     * symbols array.  If code or symbols are null then false is returned.
     * For all arrays, don't assume a length but use the array .length attribute.
     *
     * @param numPositions  The required number of symbols in the code.
     * @param symbols  The allowed symbols in the code.
     * @param code  The code that is being checked.
     * @return true if the code is the correct length and has only valid symbols otherwise
     * returns false.
     */
    public static boolean isValidCode( int numPositions, char [] symbols, char [] code)
    {
        int index =0;

        //trivial check to see if the code array and symbols array are null or not
        if(code == null || symbols == null)
        {
            return false;
        }

        //even more trivial check to make sure our number of positions equals the code length
        //so we dont error via outofbounds
        if(numPositions != code.length)
        {
            return false;
        }

        //begin nesting method calls to make sure the code is valid and apart of out collection of symbols
        for (int i = 0; i < numPositions; i++)
        {
            index = indexOf(symbols, code[i]);
            if(index == -1)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and remove
     * leading and trailing whitespace. If the line equals the single character '?'
     * then return null. If the line is a valid code (determine with isValidCode) return
     * the code, otherwise print "Invalid code." and prompt again.
     *
     * @param input The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @param numPositions The number of code positions.
     * @param symbols The valid symbols.
     * @return Returns null or a valid code.
     */
    public static char[] promptForGuess(Scanner input, String prompt, int numPositions, char[] symbols)
    {
        boolean flag = false;
        System.out.print(prompt);
        String userChoice = input.nextLine();

        while( userChoice.length() == 0 ) {
            System.out.println("Invalid code.");
            System.out.print(prompt);
            userChoice = input.nextLine().trim();
        }

        if(userChoice.charAt(0) == '?')
        {
            return null;
        }

        //put the user guess into a char array
        char[] test = userChoice.trim().toCharArray();

        //make sure the code entered is valid and apart of the symbols we have
        while(!isValidCode(numPositions, symbols, test))
        {
            System.out.println("Invalid code.");
            System.out.print(prompt);
            userChoice = input.nextLine();
            if(userChoice.charAt(0) == '?')
            {
                return null;
            }
            test = userChoice.trim().toCharArray();
        }

        while(test.length > numPositions)
        {
            System.out.println("Invalid code.");
            System.out.print(prompt);
            userChoice = input.nextLine().trim();
            test = userChoice.toCharArray();
        }

        //we want to make sure our guess IS valid. to do so we pass it our validcode method
        //which will compare character by character
        if(test.length <= numPositions)
        {
            for (int i = 0; i < numPositions; i++)
            {
                flag = isValidCode(numPositions, symbols, test);

            }
        }

        return test;
    }

    /**
     * Returns the sum of "black hits" and "white hits" between the hiddenCode 
     * and guess.  A "black hit" indicates a matching symbol in the same position in the
     * hiddenCode and guess.  A "white hit" indicates a matching symbol but different
     * position in the hiddenCode and guess that is not already accounted for with other 
     * hits.
     *
     * Algorithm to determine the total number of hits:
     *
     * Count the number of each symbol in the hiddenCode, and separately count the
     * number of each symbol in the guess. For each symbol, determine the minimum of the
     * count of that symbol in the hiddenCode and the count of that symbol found in the guess.  
     * The total number of hits, black and white, is the sum of these minimums for 
     * all the symbols.
     *
     * Algorithm based on Donald Knuth, 1976, The Computer As Master Mind, 
     *      J. Recreational Mathematics, Vol. 9(1)
     *
     * Suggestion: To do the count, create an array of int the length of the number of symbols.
     * For each symbol use the indexOf method you wrote to determine the index in the array to increment
     * the symbols count.
     *
     * @param hiddenCode The code hidden from the user.
     * @param guess  The user's guess of the code.
     * @param symbols  The possible symbols in the hiddenCode and guess.
     * @return The total number of hits.
     */
    public static int countAllHits(char[] hiddenCode, char[] guess, char[] symbols)
    {
        int pinHits = 0;
        int blackPinHits = 0;
        int whitePinHits = 0;

        //we make TWO boolean ghost arrays -- these will let us see if a space has already by marked
        //as black hit (takes precedence over white hit)
        boolean[] codeUsed = new boolean[hiddenCode.length];
        boolean[] guessUsed = new boolean[guess.length];

        //begin comparing each value to see if there is a direct copy of it in the code
        for (int i = 0; i < guess.length; i++) {
            if(guess[i] == hiddenCode[i])
            {
                //if there is a 1 to 1 copy from guess to hiddencode then we increment our black hits
                blackPinHits++;

                //but we also want to document this, so that this block does nto get double counted as a white hit
                guessUsed[i] = true;
                codeUsed[i] = guessUsed[i];
            }
        }

        //we want to look through both our guess and hidden to see if they are in each other at some point
        for (int i = 0; i < hiddenCode.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                //we need to utilize our boolean ghost arrays to make sure we do not double count the black hits as white hits
                if (!codeUsed[i] && !guessUsed[j] && hiddenCode[i] == guess[j]) {
                    whitePinHits++;
                    codeUsed[i] = guessUsed[j] = true;
                    break;
                }
            }
        }

        //trivial summation
        pinHits = blackPinHits + whitePinHits;
        return pinHits;
    }

    /**
     * Returns the number of each kind of hit the guess has with the code. 
     * The results are an array of length Config.HITS_ARRAY_LENGTH. 
     * The count of the number of symbols in the guess that correspond in position 
     * and symbol with the hidden code are recorded in the Config.BLACK_HITS_INDEX 
     * position within the result array. The number of symbols that match between
     * the guess and the hidden code but are in different positions and not otherwise
     * counted are recorded in the Config.WHITE_HITS_INDEX within the result array.
     *
     * Algorithm:
     * Count the number of black hits - the number of positions in the guess and hidden code
     * that have the same symbol.
     * Count the total number of hits using countAllHits and subtract to find the number
     * of white hits. White hits are symbols that match between guess and hiddenCode that
     * are not in the same position and not already accounted for with other hits.
     *
     * @param hiddenCode  The code the user is trying to guess.
     * @param guess  The user's guess.
     * @param symbols  The possible symbols in the hiddenCode and guess.
     * @return The array containing the number of "black hits" and "white hits".
     */
    public static int[] determineHits(char[] hiddenCode, char[] guess, char[] symbols)
    {
        int blackPinHits = 0;
        int whitePinHits = 0;
        int temp = 0;

        int[] pinHits = new int[Config.HITS_ARRAY_LENGTH];

        // begin by looking for how many black pin hits we have
        for (int i = 0; i < guess.length; i++) {
            if(guess[i] == hiddenCode[i])
            {
                blackPinHits++;
            }
        }

        // this will get us our total hits
        temp = countAllHits(hiddenCode, guess, symbols);

        // calculate our white pin hits
        whitePinHits = temp - blackPinHits;

        pinHits[Config.BLACK_HITS_INDEX] = blackPinHits;
        pinHits[Config.WHITE_HITS_INDEX] = whitePinHits;

        return pinHits;
    }

    /**
     * Prints out the game board showing the guesses and the corresponding hits.
     * See output examples. 
     * Game board example:
     *  6) [4, 5, 2, 4] BBBB
     *  5) [4, 4, 2, 5] BBWW
     *  4) [4, 4, 2, 4] BBB
     *  3) [1, 3, 3, 3] 
     *  2) [2, 3, 3, 3] W
     *  1) [1, 1, 2, 2] B
     *
     * Only rows with non-null guesses are shown. The number on the left is the guess, 
     * so the guesses are shown from last to first.
     * Looking at one line in detail:
     *  5) [4, 4, 2, 5] BBWW
     * 				      ^^  2 white hits, the 2nd 4 and 5 (we don't know which until solved)
     *                  ^^ 2 black hits, the 1st 4 and 2 (we don't know which until solved)
     *     ^^^^^^^^^^^^ the guess output using Arrays.toString()
     *  ^^ the guess number
     * The symbols B and W are the characters from Config.BLACK_HIT_SYMBOL and
     * Config.WHITE_HIT_SYMBOL. All the black hits will be shown before the white hits.
     * The length of all arrays should be determined using the array .length attribute, not
     * assumed from a constant.
     *
     * @param guesses  The array of guesses. Each row is a guess or null (meaning no guess
     * yet).
     * @param hits  The array of hits. Each row is the hits from determineHits for 
     * the corresponding guess in the parallel guesses array, or null.
     */
    public static void printBoard(char[][] guesses, int[][] hits)
    {

        //guesses
        //holds ea. lines worth of input from immeadiate source
        char[] hold = new char[Config.CODE_POSITIONS];
        int temp = 0;

        //start from bottom to top
        for (int i = guesses.length-1; i >= 0 ; i--)
        {
            String output = "";

            //begin by by copying each line into a single array
            //this is probably the worst way to do it, but other options just did not pan out
            for (int j = 0; j < Config.CODE_POSITIONS; j++)
            {
                char localworst = guesses[i][j];
                hold[j] = localworst;
            }

            //we need to document where our black and white hits are indexed --> these should be generic
            //as it is easily forseeable change to screw students over.
            int blackhold = hits[i][Config.BLACK_HITS_INDEX];
            int whitehold = hits[i][Config.WHITE_HITS_INDEX];

            //we go to each index and add them to a string where black hits come before white hits (as per request)
            for (int k = 0; k < blackhold; k++)
            {
                output = output + Config.BLACK_HITS_SYMBOL;
            }

            for (int k = 0; k < whitehold; k++)
            {
                output = output + Config.WHITE_HITS_SYMBOL;
            }

            //as our 2D array starts as all empty cells, we want to make sure we DO NOT print the empty cells
            //and only the ones that have a value in them.
            //using hte escpae char lets us succeed here.
            if(guesses[i][0] != '\u0000')
            {
                int temphold = i + 1;
                System.out.println(" " + temphold + ") " + Arrays.toString(hold) + " " + output);
            }

        }

    }

    /**
     * The MasterMind main method that contains the welcome and the main game
     * loop. Carefully examine example output to help answer questions on prompts and
     * how this program should work. 
     *
     * Algorithm:
     * Use appropriate constants from Config. For example, to create an array use Config.MAX_GUESSES,
     *     but once an array exists don't use the constants but use the array .length attribute.
     * Determine seed or not (call promptInt with Integer.MIN_VALUE, Integer.MAX_VALUE)
     * Display welcome message.
     * Generate the hidden code (call generateHiddenCode)
     * Create 2D arrays for guesses and corresponding hits. Initially every row is null
     *     until guesses are made and hits are determined for a guess.
     * (milestone 3) enumerate all the possibilities (call enumeratePossibilities)
     * Loop
     *     Prompt for guess (call promptForGuess)
     *     (milestone 3) If guess is null then call computerGuess
     *     Determine how many black and white hits (call determineHits)
     *     Output the board (call printBoard)
     *     (milestone 3) Output number of remaining possibilities
     * End loop when won or lost  
     * Output won or lost message.
     *
     *
     * @param args  unused
     */
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);

        int intchoice = 0;
        int guessing = 0;
        int hinting = 0;
        int returnUpPos = 0;

        String stringchoice = "";

        //we need two 2D arrays to be able to hold previous inputs and hits
        //that the user has entered and been given in return
        //to be passed to print board method
        char[][] userguess = new char[Config.MAX_GUESSES][Config.CODE_POSITIONS];
        int[][] userhits  = new int[Config.MAX_GUESSES][Config.HITS_ARRAY_LENGTH];
        char[][] returnEnumPos;

        //char arrays that we will need to returns
        char[] returnPFG = new char[Config.CODE_POSITIONS];
        char[] returnGHC = new char[Config.CODE_POSITIONS];
        int[] returnhits  = new int[2];

        //prompts
        String promptSeed  = "Enter seed (0 for unrepeatable): ";
        String promptguess = "Enter guess (? for help): ";

        //start
        intchoice = promptInt(userInput, promptSeed, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Random choicerand = new Random(intchoice);

        //generate the hidden code
        returnGHC = generateHiddenCode(choicerand, Config.CODE_POSITIONS, Config.CODE_SYMBOLS);

        System.out.println("Welcome to Master Mind!");

        System.out.println("I have a " + Config.CODE_POSITIONS + " symbol code using "
                + Arrays.toString(Config.CODE_SYMBOLS) +".");

        System.out.println("Can you guess my code within " + Config.MAX_GUESSES + " guesses?");

        //we can all the possibilities of the symbols we have
        returnEnumPos = enumeratePossibilities(Config.CODE_POSITIONS, Config.CODE_SYMBOLS);

        //start the guessing!
        for (int i = 0; i < Config.MAX_GUESSES; i++)
        {

            //prompt for guess
            returnPFG = promptForGuess(userInput, promptguess, Config.CODE_POSITIONS, Config.CODE_SYMBOLS);
            if(returnPFG == null)
            {
                //insert a computer guess
                returnPFG = computerGuess(returnEnumPos);
                hinting++;
            }

            //at this point a guess of some sort has been made, so we want to increment
            guessing++;

            //read the user's guess into our 2D array of user's guesses, i think?
            for (int j = 0; j < returnPFG.length; j++)
            {
                char localbad = returnPFG[j];
                userguess[i][j] = localbad;
            }

            //get the hits
            returnhits = determineHits(returnGHC, returnPFG, Config.CODE_SYMBOLS);

            //add hits to userhits 2D array for storage
            for (int j = 0; j < returnhits.length; j++)
            {
                int localworst = returnhits[j];
                userhits[i][j] = localworst;
            }

            //print the board
            printBoard(userguess, userhits);

            //we have our update possibilities
            returnUpPos = updatePossibilities(returnPFG, returnhits, returnEnumPos, Config.CODE_SYMBOLS);

            //let the user know how many poss. are left
            System.out.println("remaining possibilities: " + returnUpPos);


            //check to see if the user is done
            if(Arrays.equals(returnPFG, returnGHC))
            {
                System.out.println("Congratulations! You guessed code with only " + guessing +
                        " guesses and " + hinting + " hints!");
                break;
            }
        }

    }

    /**
     * Determine the next code in sequence given the ordered symbols and
     * a code. See MasterMindTests.testNextCode() method for various test cases.
     * Most significant positions are the left most, just like for a number 
     * such as 1234, where 1 is the most significant digit.
     *
     * Consider how you would add 1 to 199. Work out on paper.  Now try with
     * the symbols A, B, C in that order. If you added B to BC what would
     * the result be? CA?
     *
     * Algorithm:
     * Loop from least significant position to the most significant
     *     Find the index of the symbol
     *     if least significant position
     *         if last symbol then 
     *             set to first symbol and carry
     *         else set next symbol
     *     else 
     *         if carry and last symbol
     *             set to first symbol and keep carry set
     *         else if carry and not last symbol
     *             set next symbol, clear carry
     *         else 
     *             no carry, so keep symbol
     *         end if
     *     end if
     * End loop
     *
     * If carry
     *      then prepend an additional symbol.
     *
     * Since, in decimal, leading 0's
     * are assumed then we assume the same for any symbols. So, we would prepend
     * the 2nd symbol, in decimal a 1.   
     *
     * @param code   A code with the symbols.
     * @param symbols  The symbols to use for the code.
     * @return  The next code in the sequence based on the order of the symbols.
     */
    public static char[] nextCode(char[] code, char[] symbols)
    {
        char[] nextCode = new char[code.length];
        int i=0;
        boolean carry = false;

        //index i of code from right to left
        for(i = code.length-1; i >= 0 ; i--)
        {

            //find index j of symbol
            int j=0;
            while(code[i]!=symbols[j])
            {
                j++;
            }

            if(code[i]==symbols[j])
            {

                //if least significant position
                if(i == code.length-1)
                {
                    //if last symbol then: set to first symbol and carry
                    if(j==symbols.length-1)
                    {
                        nextCode[i] = symbols[0];
                        carry = true;
                    }

                    //else: set next symbol
                    else
                    {
                        nextCode[i] = symbols[j+1];
                    }

                }

                else
                {
                    //if carry and last symbol: set to first symbol and keep carry set
                    if(carry==true&&j==symbols.length-1)
                    {
                        nextCode[i] = symbols[0];
                    }

                    //else if carry and not last symbol: set next symbol, clear carry
                    else if(carry==true&&j!=symbols.length-1)
                    {
                        nextCode[i] = symbols[j+1];
                        carry = false;
                    }

                    //else: no carry, so keep symbol
                    else
                    {
                        nextCode[i] = symbols[j];
                    }
                }
            }
        }
        //prepend an additional symbol
        if (carry==true)
        {
            char[] nextCodeAndMore = new char[nextCode.length+1];
            nextCodeAndMore[0] = symbols[1];
            for(int k = 1 ; k<nextCode.length+1 ; k++)
            {
                nextCodeAndMore[k] = nextCode[k-1];
            }
            return nextCodeAndMore;
        }

        return nextCode;
    }

    /**
     * List all the possibilities (permutations) of codes for the specified number of
     * positions and the provided codes.
     *
     * Algorithm:
     * Create an array the length being the number of possibilities (permutations). 
     *     For example, 3 symbols in each of 4 positions means there are 3*3*3*3 or 3^4 
     *     which equals 81 permutations.
     * Determine the "first" code (all positions having the same first symbol).
     * For each permutation call nextCode to generate the next code from the current.
     *
     * If numPositions is less than or equal to 0 or symbols is 0 length or null
     * then return null.
     *
     * @param numPositions The number of positions in a code.
     * @param symbols The possible symbols used in a code.
     * @return An array of all the possible codes that can be generated from the 
     * symbols for the numPositions.
     */
    public static char[][] enumeratePossibilities(int numPositions, char[] symbols)
    {
        if(symbols==null||symbols.length==0||numPositions<=0)
        {
            return null;
        }

        char[]currentCode = new char[numPositions];
        int numPossibilities = 1;
        int i = 0;
        int j = 0;

        //calculates numPossibilities
        for (i = 0 ; i < numPositions ; i++)
        {
            numPossibilities = numPossibilities * symbols.length;
        }

        //generates first code
        for(i = 0 ; i < numPositions ; i++)
        {
            currentCode[i] = symbols[0];
        }

        //declares 2d array enumeratePossibilities w first code
        char[][]enumeratePossibilities = new char[numPossibilities][];
        enumeratePossibilities[0] = currentCode;

        //recursion
        for(j = 1 ; j < numPossibilities ; j++)
        {
            //begin incrementing via nextCode()
            enumeratePossibilities[j] = nextCode(currentCode, symbols);
            currentCode = enumeratePossibilities[j];
        }

        return enumeratePossibilities;
    }

    /**
     * Updates the remaining possibilities array and returns the number
     * of possibilities.
     * The hiddenCodeHits parameter contains the black and white hits when the guess is compared 
     * to the code. The possibilities parameter contains all the possible remaining candidates
     * for the hidden code. Determine the hits for each non-null guess when compared to each 
     * possibility and only keep the possibilities that match the result parameter hits.
     * Remove a possibility from the array of possibilities by setting it to null.
     *
     * @param guess  The current guess
     * @param hiddenCodeHits  The hits when guess is compared to hiddenCode.
     * @param possibilities The remaining codes that contain the hidden code.
     * @param symbols The potential symbols in the codes.
     * @return The number of remaining possibilities.
     */
    public static int updatePossibilities(char[] guess, int[] hiddenCodeHits, char[][] possibilities,
                                          char[] symbols)
    {
        int possCount = 0;
        int blackCount = hiddenCodeHits[Config.BLACK_HITS_INDEX];
        int whiteCount = hiddenCodeHits[Config.WHITE_HITS_INDEX];

        for (int i = 0; i < possibilities.length; i++)
        {
            int[] gComp = new int[hiddenCodeHits.length];
            char[] temp = new char[guess.length];

            //we set a temp based on pos[i] for the current row
            temp = possibilities[i];
            if(temp != null)
            {
                //our guess comparison has been made (we know what has been hit)
                gComp = determineHits(temp, guess,symbols);

                //if they are not equal, then we remove that row from our possibilities
                if(!Arrays.equals(gComp, hiddenCodeHits))
                {
                    possibilities[i] = null;
                }
                else{
                    possCount++;
                }
            }
        }

        return possCount;
    }

    /**
     * Select the first remaining code (lowest index) from possibilities.
     * If no codes are left then return null.
     *
     * @param possibilities The array of remaining possible codes.
     * @return A code from the array.
     */
    public static char[] computerGuess(char[][] possibilities)
    {
        for (int i = 0; i < possibilities.length; i++)
        {
            //if the row has not been marked as null, we want to return that one, otherwise, there are no
            //guesses to be made
            if(possibilities[i] != null)
            {
                return possibilities[i];
            }
        }

        return null;
    }
}