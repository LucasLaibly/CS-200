
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AdventureStory.java
// Files: a list of all source files used by that program
// Course: CS 200 Spring 2019
//
// Author: Lucas Laibly
// Email: laibly@wisc.edu
// Lecturer's Name: Jim Williams
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

import java.io.IOException;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * This class will prepare and run the Adventure Story simulator guided by user input. There are
 * multiple methods to guid the user throughout the game.
 * 
 * @author Lucas Laibly
 */

public class AdventureStory
{

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output and the user is prompted again.
     *
     * @param sc     The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min    The minimum acceptable int value (inclusive).
     * @param max    The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max)
    {

        // the value the user enters
        int userIntegerEntered = 0;

        //boolean to run loop until code put in is valid
        boolean validCode = false;

        // continue running until user puts in a valid code (int)
        while (!validCode)
        {
            //print the pompt to ask for an integer
            System.out.print(prompt);

            // try-catch for error handling
            try {

                if (sc.hasNextInt())
                {

                    //only collect the integer entered
                    userIntegerEntered = sc.nextInt();

                    //if there is anything other than the integer, take it in and trash it
                    //sc.nextLine();

                    // make sure entered value is between max and min values set
                    if (userIntegerEntered <= max && userIntegerEntered >= min)
                    {
                        validCode = true;
                        return userIntegerEntered;
                    }

                    // error statement if bounds are not met
                    else {
                        System.out.println("Invalid value.");
                    }
                }
                // error statement if not an integer
                else {
                    System.out.println("Invalid value.");
                    sc.nextLine();
                }
            }
            // try-catch exception to be thrown for being incorrect
            catch (Exception e) {
                System.out.print("Invalid value.");
            }
        }
        //keep running until valid number (int) is entered
        return -1;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt)
    {
        System.out.println(prompt);
        String input;
        char converter;
        if(sc.hasNextLine())
        {
            input = sc.nextLine().trim();
            if(input.length() > 0)
            {
                converter = input.charAt(0);
                converter = Character.toLowerCase(converter);
                return converter;
            }

        }

        //return 'n';
        return '\0';
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input, removing any leading and 
     * trailing whitespace.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user with leading and trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt)
    {
        // user input string
        String userString;

        System.out.print(prompt);

        //get user input, then trim it
        userString = sc.nextLine();
        userString = userString.trim();

        return userString;
    }

    /**
     * Saves the current position in the story to a file.
     *
     * The format of the bookmark file is as follows:
     * Line 1: The value of Config.MAGIC_BOOKMARK
     * Line 2: The filename of the story file from storyFile
     * Line 3: The current room id from curRoom
     *
     * Note: use PrintWriter to print to the file.
     *
     * @param storyFile The filename containing the cyoa story.
     * @param curRoom The id of the current room.
     * @param bookmarkFile The filename of the bookmark file.
     * @return false on an IOException, and true otherwise.
     */
    public static boolean saveBookmark(String storyFile, String curRoom, String bookmarkFile)
    {
        try
        {
            //make a new file object
            File file = new File(bookmarkFile);

            //open via PrintWriter to make a bookmark file
            PrintWriter printWriter = new PrintWriter(file);

            //print the file as specified in the method header
            printWriter.println(Config.MAGIC_BOOKMARK);
            printWriter.println(storyFile);
            printWriter.println(curRoom);

            //close the print writer
            printWriter.close();
        }

        catch(IOException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Loads the story and current location from a file either a story file or a bookmark file. 
     * NOTE: This method is partially implemented in Milestone 2 and then finished in Milestone 3.
     * 
     * The type of the file will be determined by reading the first line of the file. The first
     * line of the file should be trimmed of whitespace.
     *
     * If the first line is Config.MAGIC_STORY, then the file is parsed using the parseStory method.
     * If the first line is Config.MAGIC_BOOKMARK, the the file is parsed using the parseBookmark
     * method.
     * Otherwise, print an error message, terminated by a new line, to System.out, displaying: 
     * "First line: trimmedLineRead does not correspond to known value.", where trimmedLineRead is 
     * the trimmed value of the first line from the file. 
     *
     * If there is an IOException, print an error message, terminated by a new line, to System.out,
     * saying "Error reading file: fName", where fName is the value of the parameter.
     *
     * If there is an error reading the first line, print an error message, terminated by a new 
     * line, to System.out, displaying: "Unable to read first line from file: fName", where fName is
     * the value of the parameter. 
     *
     * This method will be partially implemented in Milestone #2 and completed in Milestone #3 as 
     * described below.
     *
     * Milestone #2: Open the file, handling the IOExceptions as described above. Do not read the
     * the first line: Assume the file is a story file and call the parseStory method.
     *
     * Milestone #3: Complete the implementation of this method by reading the first line from the
     * file and following the rules of the method as described above.
     *
     * @param fName The name of the file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms. Since the rooms can have multiple transitions, each room 
     *              will be an ArrayList<String[]> with one String[] per transition with the 
     *              overall structure being an ArrayList of ArrayLists of String[].
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is an IOException or a parsing error. Otherwise, true. 
     */
    public static boolean parseFile(String fName, ArrayList<String[]> rooms,
                                    ArrayList<ArrayList<String[]>> trans, String[] curRoom)
    {
        //make a new File obj to read the file name
        File file = new File(fName);

        try
        {
            //pass the file to the scanner to read it
            Scanner input = new Scanner(file);

            //as long as we have another line
            if (input.hasNextLine())
            {
                //read in the line and trim it down
                String firstLine = input.nextLine().trim();

                // if our line is bookmark
                if ((firstLine.equals(Config.MAGIC_BOOKMARK) || firstLine.equals(Config.MAGIC_STORY)))
                {

                    // checks if the file is a story
                    if (firstLine.equals(Config.MAGIC_STORY))
                    {
                        //parse the story fully, filling the needed items
                        parseStory(input, rooms, trans, curRoom);

                        // see if the first room is null
                        if (curRoom[0] == null)
                        {
                            curRoom[0] = rooms.get(0)[Config.ROOM_ID];
                        }

                    }

                    // checks if the file is bookmarked
                    else if (firstLine.equals(Config.MAGIC_BOOKMARK))
                    {
                        //parse it as a bookmark
                        parseBookmark(input, rooms, trans, curRoom);
                    }
                }

                //issue statement that the first line is not know
                else {
                    System.out.println("First line: " + firstLine + " does not correspond to known value.");
                    return false;
                }
            }
            else {
                //issue statment htat first line cannot be read
                System.out.println("Unable to read first line from file: " + fName);
                return false;
            }
            //catch any exceptions that may be thrown and issue the warning as instructed
        }
        catch(Exception e)
        {
            System.out.println("Error reading file: " + fName);
            return false;
        }

        //else, just return true
        return true;
    }

    /**
     * Loads the story and the current room from a bookmark file. This method assumes that the first
     * line of the file, containing Config.MAGIC_BOOKMARK, has already been read from the Scanner.
     *
     * The format of a bookmark file is as follows:
     * Line No: Contents
     *       1: Config.MAGIC_BOOKMARK
     *       2: Story filename
     *       3: Current room id
     *
     * As an example, the following contents would load the story Goldilocks.story and set the 
     * current room to id 7.
     *
     * #!BOOKMARK
     * Goldilocks.story
     * 7
     *
     * Your method should not duplicate the code from the parseFile method. It must use the
     * parseFile method to populate the rooms and trans methods based on the contents of the story
     * filename read and trimmed from line 2 of the file. The value of for the cell at index 0 of 
     * curRoom is the trimmed value read on line 3 of the file.
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */
    public static boolean parseBookmark(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom)
    {

        // parseFile boolean value
        boolean pfSuccess = true;

        //file name
        String fileName = "";

        //line of text read in @ time from scanner
        String lineofText = "";

        try
        {
            String id = "";

            //read in the file name -- TRIM
            fileName = sc.nextLine().trim();

            //get the id on the next line -- TRIM
            id = sc.nextLine().trim();

            //set the current room to id
            curRoom[Config.ROOM_ID] = id;

            //call parse file on the given information
            pfSuccess = parseFile(fileName, rooms, trans, curRoom);

        }

        //null pointer exception, in case.
        //anticipating this is the only one thrown
        catch(NullPointerException e)
        {
            return false;
        }

        return pfSuccess;
    }

    /**
     * This method parses a story adventure file.
     *
     * The method will read the contents from the Scanner, line by line, and populate the parallel 
     * ArrayLists rooms and trans. As such the story files have a specific structure. The order of
     * the rooms in the story file correspond to the order in which they will be stored in the 
     * parallel ArrayLists.
     *
     * When reading the file line-by-line, whitespace at the beginning and end of the line should be
     * trimmed. The file format described below assumes that whitespace has been trimmed.
     *
     * Story file format:
     *
     * - Any line (outside of a room's description) that begins with a '#' is considered a comment 
     *   and should be ignored.
     * - Room details begin with a line starting with 'R' followed by the room id, terminated with 
     *   a ':'. Everything  after the first colon is the room title. The substrings of the room id 
     *   and the room title should be trimmed.
     * - The room description begins on the line immediate following the line prefixed with 'R',
     *   containing the room id, and continues until a line of ";;;" is read.
     *   - The room description may be multi-line. Every line after the first one, should be 
     *     prefixed with a newline character ('\n'), and concatenated to the previous description 
     *     lines read for the current room.
     * - The room transitions begin immediately after the line of ";;;", and continue until a line
     *   beginning with 'R' is encountered. There are 3 types of transition lines:
     *   - 1 -- Terminal Transition: A terminal transition is either Config.SUCCESS or 
     *                               Config.FAIL. This room is the end of the story. 
     *                               This value should be stored as a transition with the String at
     *                               index Config.TRAN_DESC set to the value read. The rest of the 
     *                               Strings in the transition String array should be null.
     *                               A room with a terminal transition can only have one transition 
     *                               associated with it. Any additional transitions should result in
     *                               a parse error.
     *   - 2 -- Normal Transition: The line begins with ':' followed by the transition description, 
     *                             followed by " -> " (note the spaces), followed by the room id to 
     *                             transition to. For normal transitions (those without a transition
     *                             weight), set the value at index Config.TRAN_PROB to null.
     *   - 3 -- Weighted Transition: Similar to a normal transition except that there is a 
     *                               probability weight associated with the transition. After the 
     *                               room id (as described in the normal transition) is a '?' 
     *                               followed by the probability weight. 
     *   - You can assume that room ids do not contain a '?'.
     *   - You can assume that Config.SUCCESS and Config.FAIL do not start with a ':'.
     *
     * In the parallel ArrayLists rooms and trans, the internal structures are as follows:
     *
     * The String array structure for each room has a length of Config.ROOM_DET_LEN. The entries in
     * the array are as follows:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * The String array structure for each transition. Note that each room can have multiple 
     * transitions, hence, the ArrayList of ArrayLists of String[]. The length of the String[] is
     * Config.TRAN_DET_LEN. The entries in the String[] are as follows:
     * Index               | Description
     * ------------------------------------------------------------------
     * Config.TRAN_DESC    | The transition description
     * Config.TRAN_ROOM_ID | The transition destination (id of the room) 
     * Config.TRAN_PROB    | The probability weight for the transition
     *
     * If you encounter a line that violates the story file format, the method should print out an 
     * error message, terminated by a new line, to System.out displaying: 
     * "Error parsing file on line: lineNo: lineRead", where lineNo is the number of lines read
     * by the parseStory method (i.e. ignoring the magic number if Milestone #3), and lineRead is 
     * the offending trimmed line read from the Scanner.
     *
     * After parsing the file, if rooms or trans have zero size, or they have different sizes, print
     * out an error message, terminated by a new line, to System.out displaying:
     * "Error parsing file: rooms or transitions not properly parsed."
     *
     * After parsing the file, if curRoom is not null, store the reference of the id of the room at 
     * index 0 of the rooms ArrayList into the cell at index 0 of curRoom. 
     *
     * Hint: This method only needs a single loop, reading the file line-by-line.
     * 
     * Hint: To successfully parse the file, you will need to maintain a state of where you are in 
     *       the file. I.e., are you parsing the description, parsing the transitions; is there an 
             error; etc? One suggestion would be to use an enum to enumerate the different states. 
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details.
     * @param trans The ArrayList structure that will contain the transition details.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */
    public static boolean parseStory(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom)
    {
        //current room array to be added to AL's
        String[] currentRoom = new String[Config.TRAN_DET_LEN];

        //user input
        String lineOfText = "";

        //Strings for the related room values
        String roomDescription = "";
        String roomID = "";
        String roomTitle = "";

        //Strings for the related trans values
        String transDescription = null;
        String transRoomID = null;
        String transProbab = null;

        // all characters we are to anticipate in the file
        char skipR = 'R';
        char skipQue = '?';
        char skipHash = '#';
        char skipColon = ':';
        char skipSemi = ';';
        char skipGator = '>';

        int roomNumber = 0;

        //bool value for if we have the room description
        boolean roomDescriptionBoolean = false;

        //smaller version of transitions so we can more easily move array into AL into AL
        ArrayList<String[]> curTrans = new ArrayList<String[]>();

        try
        {

            // sets up while loop to iterate through each possibility
            while (sc.hasNextLine()) {
                lineOfText = sc.nextLine();

                // Eats line of code if commented out
                if (!roomDescriptionBoolean
                        && (lineOfText.equals("")
                        || (lineOfText.charAt(0) == skipHash)))
                {
                    continue;
                }

                //<editor-fold desc = "rooms">

                // sets the roomDescription as long as it isnt anything else
                else if (lineOfText.equals("")
                        || lineOfText.charAt(0) != skipSemi
                        && lineOfText.charAt(0) != skipColon
                        && !lineOfText.contains(Config.SUCCESS)
                        && !lineOfText.contains(Config.FAIL)
                        && roomDescriptionBoolean)
                {
                    roomDescription += "\n" + lineOfText;
                }

                //if the line starts with R or has a colon (transtiions)
                else if (lineOfText.charAt(0) == skipR && lineOfText.indexOf(skipColon) > 0)
                {

                    //make a current room array, flushed and ready to use
                    currentRoom = new String[Config.ROOM_DET_LEN];

                    //set the room id as what is between index 1 and the colon
                    roomID = lineOfText.substring(1, lineOfText.indexOf(skipColon));

                    //add it to the room array @ index Config.Room_ID
                    currentRoom[Config.ROOM_ID] = roomID;

                    //next, look for the title of the room from colon to end of line
                    roomTitle = lineOfText.substring(lineOfText.indexOf(skipColon) + 1);

                    //trim title and assign it
                    roomTitle = roomTitle.trim();

                    //add it to the current room array @ index Config.Room_Title
                    currentRoom[Config.ROOM_TITLE] = roomTitle;

                    //increment for the room description
                    roomDescription = sc.nextLine();

                    //at this point we have the room number, and title, and transitions
                    //we need the room description and then we are done.
                    roomDescriptionBoolean = true;

                    //as long as we are greater than the first room
                    if (roomNumber > 0)
                    {
                        //add the trans array to arraylist<string[]>
                        trans.add(curTrans);
                        //flush the curTrans arraylist
                        curTrans = new ArrayList<String[]>();
                    }

                    //add the room just read to the arraylist
                    rooms.add(currentRoom);

                    //increment which room we are looking at
                    roomNumber++;
                }

                //triple semi colon -> end of description
                else if (lineOfText.equals(";;;"))
                {
                    //trim the description
                    roomDescription = roomDescription.trim();

                    //add it to current room array @ index Config.Room_Desc
                    currentRoom[Config.ROOM_DESC] = roomDescription;

                    //as we have read the current rooms description, what follows will only be the next room title
                    //and id, so we must go false first
                    roomDescriptionBoolean = false;
                }

                //</editor-fold>

                //<editor-fold desc = "transitions">

                // transition of normal or weighted
                else if (lineOfText.charAt(0) == skipColon)
                {
                    //make an array for transitions to add to AL
                    String[] curTransArr = new String[Config.TRAN_DET_LEN];

                    //the dexcription is from start of line till base of arrow head
                    transDescription = lineOfText.substring(1, lineOfText.indexOf(skipGator) - 2).trim();

                    //add the description to the array @ index Tran_Desc
                    curTransArr[Config.TRAN_DESC] = transDescription;

                    //for everything after the arrow of transitions
                    String textAfterArrow = lineOfText.substring(lineOfText.indexOf(skipGator) + 1);

                    if (textAfterArrow.indexOf(skipQue) > 0)
                    {
                        //set the transition roomID to substring from array till question mark (tran prob value)
                        transRoomID = textAfterArrow.substring(textAfterArrow.indexOf(skipGator) + 2,
                                textAfterArrow.indexOf(skipQue) - 1);

                        //set transition room ID, trim it
                        transRoomID = transRoomID.trim();

                        //add it to the transitions array @ index Tran_Room_ID
                        curTransArr[Config.TRAN_ROOM_ID] = transRoomID;

                        //define the tran prob value as what ever comes after the question mark
                        transProbab = textAfterArrow.substring(textAfterArrow.indexOf(skipQue) + 1).trim();

                        //set the trans prob @ index Tran_Prob
                        curTransArr[Config.TRAN_PROB] = transProbab;

                        //add it manually to the transition Array list
                        curTrans.add(curTransArr);

                    }
                    else {

                        //continue with normal transition

                        //trans Room ID goes from the arrow to the end of the line
                        transRoomID = lineOfText.substring(lineOfText.indexOf(skipGator) + 2).trim();

                        //add mantually @ index Tran_Room_ID
                        curTransArr[Config.TRAN_ROOM_ID] = transRoomID;

                        //push it into the transition ArrayList
                        curTrans.add(curTransArr);
                    }
                }

                // runs terminal transition success
                else if (lineOfText.trim().equals(Config.SUCCESS))
                {
                    //temporary array list that will hold the tran details
                    String[] terminalSuccess = new String[Config.TRAN_DET_LEN];

                    //set tran description to success because we are in the success
                    terminalSuccess[Config.TRAN_DESC] = Config.SUCCESS;

                    //add the success array to the array list
                    curTrans.add(terminalSuccess);

                }

                // terminal fail transition
                else if (lineOfText.trim().equals(Config.FAIL))
                {
                    //temporary array list that will hold the tran details
                    String[] terminalFail = new String[Config.TRAN_DET_LEN];

                    //set tran description to fail because we are a failure
                    terminalFail[Config.TRAN_DESC] = Config.FAIL;

                    //add the fail array to the array list
                    curTrans.add(terminalFail);
                }

                //</editor-fold>

            }

            //if the file is out of information, add an empty line
            if (!sc.hasNextLine()) {
                trans.add(curTrans);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error parsing file: rooms or transitions not properly parsed.");
            return false;
        }

        //base case
        return true;
    }

    /**
     * Returns the index of the given room id in an ArrayList of rooms. 
     *
     * Each entry in the ArrayList contain a String array, containing the details of a room. The 
     * String array structure, which has a length of Config.ROOM_DET_LEN, and has the following 
     * entries:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The index of the room with the given id if found in rooms. Otherwise, -1.
     */
    public static int getRoomIndex(String id, ArrayList<String[]> rooms)
    {
        // sets room index to -1 to return if false
        int roomIndex = -1;

        //where we start, the current index
        int current = 0;

        // for each array in rooms array list
        for (String[] room : rooms)
        {
            // look to see if the Room_ID in eaach array is the same as the ID we are looking for
            if (room[Config.ROOM_ID].equals(id))
            {
                //set it if we find it
                roomIndex = current;
                break;
            }
            //else go to the next index
            current++;
        }

        return roomIndex;
    }

    /**
     * Returns the room String array of the given room id in an ArrayList of rooms. 
     *
     * Remember to avoid code duplication!
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The reference to the String array in rooms with the room id of id. Otherwise, null.
     */
    public static String[] getRoomDetails(String id, ArrayList<String[]> rooms)
    {

        for(int i = 0 ; i < rooms.size() ; i++)
        {
            //assign array via array list from index for loop
            String[] roomDetails = rooms.get(i);

            //if ID is found, return
            if(id == roomDetails[Config.ROOM_ID])
            {
                return roomDetails;
            }
        }

        // return null if nothing is found
        return null;
    }

    /**
     * Prints out a line of characters to System.out. The line should be terminated by a new line.
     *
     * @param len The number of times to print out c. 
     * @param c The character to print out.
     */
    public static void printLine(int len, char c) {

        // for loop to print a line of characters
        //System.out.println();
        for(int i = 0 ; i < len ; i++)
        {
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * Prints out a String to System.out, formatting it into lines of length no more than len 
     * characters.
     * 
     * This method will need to print the string out character-by-character, counting the number of
     * characters printed per line. 
     * If the character to output is a newline, print it out and reset your counter.
     * If it reaches the maximum number of characters per line, len, and the next character is:
     *   - whitespace (as defined by the Character.isWhitespace method): print a new line 
     *     character, and move onto the next character.
     *   - NOT a letter or digit (as defined by the Character.isLetterOrDigit method): print out the
     *     character, a new line, and move onto the next character.
     *   - Otherwise:
     *       - If the previous character is whitespace, print a new line then the character.
     *       - Otherwise, print a '-', a new line, and then the character. 
     * Remember to reset the counter when starting a new line. 
     *
     * After printing out the characters in the string, a new line is output.
     *
     * @param len The maximum number of characters to print out.
     * @param val The string to print out.
     */
    public static void printString(int len, String val) {

        int count = 1;
        for (int i = 0; i < val.length(); i++)
        {

            //if the line we are looking at is a new line
            if (val.charAt(i) == '\n') {
                count = 0;
            }

            //assuming out count is the same as the length we are passed
            if (count == len) {


                //if the character is whitespace
                if (Character.isWhitespace(val.charAt(i)))
                {
                    System.out.println();

                    //everytime we print a new line, reset count;
                    count = 0;
                }

                //if the character is NOT a letter or digit
                else if (!(Character.isLetterOrDigit(val.charAt(i))))
                {
                    System.out.println(val.charAt(i));

                    //after every new line, reset count
                    count = 0;
                }

                //if the character we are looking has a previous white space
                else if (Character.isWhitespace(val.charAt(i - 1)))
                {
                    //make a new line, and then print the character
                    System.out.print("\n" + val.charAt(i));
                    count = 1;
                }

                else {
                    //mid word we want to do the dash, new line, then print the rest of the word
                    System.out.print("-\n" + val.charAt(i));
                    count = 1;
                }
            }

            else {
                //just print the character and be happy
                System.out.print(val.charAt(i));
            }

            count++;
        }

        System.out.println();
    }


    /**
     * This method prints out the room title and description to System.out. Specifically, it first
     * loads the room details, using the getRoomDetails method. If no room is found, the method
     * should return, avoiding any runtime errors.
     *
     * If the room is found, first a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output. Followed by the room's title, a new line, and the room's description. Both the title
     * and the description should be printed using the printString method with a maximum length of
     * Config.DISPLAY_WIDTH. Finally, a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output.
     *
     * @param id Room ID to display
     * @param rooms ArrayList containing the room details.
     */
    public static void displayRoom(String id, ArrayList<String[]> rooms)
    {
        //array to hold the room details that we get
        String[] roomDeets = getRoomDetails(id, rooms);

        try
        {
            if (roomDeets[Config.ROOM_ID] != null && roomDeets[Config.ROOM_TITLE] != null)
            {
                //print the top line
                printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);

                //print the string of room details that we have in the array
                printString(Config.DISPLAY_WIDTH, roomDeets[Config.ROOM_TITLE]);

                //empty line -- ZyBooks
                System.out.println();

                //as long as the room details array is not null
                if (roomDeets[Config.ROOM_DESC] != null)
                {
                    //print the string of the room details that we have
                    printString(Config.DISPLAY_WIDTH, roomDeets[Config.ROOM_DESC]);
                }

                else {
                    //push out an empty line
                    System.out.println();
                }

                //print the bottom line
                printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);
            }
        }

        catch(NullPointerException e)
        {
            return;
        }
    }

    /**
     * Prints out and returns the transitions for a given room. 
     *
     * If the room ID of id cannot be found, nothing should be output to System.out and null should
     * be returned.
     *
     * If the room is a terminal room, i.e., the transition list is consists of only a single 
     * transition with the value at index Config.TRAN_DESC being either Config.SUCCESS or 
     * Config.FAIL, nothing should be printed out.
     *
     * The transitions should be output in the same order in which they are in the ArrayList, and 
     * only if the transition probability (String at index TRAN_PROB) is null. Each transition 
     * should be output on its own line with the following format:
     * idx) transDesc
     * where idx is the index in the transition ArrayList and transDesc is the String at index 
     * Config.TRAN_DESC in the transition String array.
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param id The room id of the transitions to output and return.
     * @param rooms The ArrayList structure that contains the room details.
     * @param trans The ArrayList structure that contains the transition details.
     * @return null if the id cannot be found in rooms. Otherwise, the reference to the ArrayList of
     *         transitions for the given room.
     */
    public static ArrayList<String[]> displayTransitions(String id, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans)
    {
        //get the index of where the id is found in the room arraylist
        int indexofID = getRoomIndex(id, rooms);

        //make a smaller version of trans so it is only 2D
        ArrayList<String[]> transitions = new ArrayList<String[]>();

        //cannot be found
        if (indexofID == -1)
        {
            return null;
        }

        //terminal room
        if (trans.get(indexofID).size() == 1)
        {

            // @ index and size 1, there is either a success or fail
            if (trans.get(indexofID).get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)
                || trans.get(indexofID).get(0)[Config.TRAN_DESC].equals(Config.FAIL))
            {
                //either one of these we want to get this value and push it into transitions arraylist
                transitions = trans.get(indexofID);
                return transitions;
            }
        }

        //iterate through the transitions and print as instructed
        for (int i = 0; i < trans.get(indexofID).size(); i++)
        {
            transitions = trans.get(indexofID);

            if (trans.get(indexofID).get(i)[Config.TRAN_PROB] == null)
            {
                System.out.println(i + ") " + trans.get(indexofID).get(i)[Config.TRAN_DESC]);
            }
        }

        return transitions;
    }


    /**
     * Returns the next room id, selected randomly based on the transition probability weights.
     *
     * If curTrans is null or the total sum of all the probability weights is 0, then return null. 
     * Use Integer.parseInt to convert the Strings at index Config.TRAN_PROB of the transition
     * String array to integers. If there is a NumberFormatException, return null.
     *
     * It is important to follow the specifications of the random process exactly. Any deviation may
     * result in failed tests. The random transition work as follows:
     *   - Let totalWeight be the sum of the all the transition probability weights in curTrans.
     *   - Draw a random integer between 0 and totalWeight - 1 (inclusive) from rand.
     *   - From the beginning of the ArrayList curTrans, start summing up the transition probability 
     *     weights.
     *   - Return the String at index Config.TRAN_ROOM_ID of the first transition that causes the 
     *     running sum of probability weights to exceed the random integer.   
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param rand The Random class from which to draw random values.
     * @param curTrans The ArrayList structure that contains the transition details.
     * @return The room id that was randomly selected if the sum of probabilities is greater than 0.
     *         Otherwise, return null. Also, return null if there is a NumberFormatException. 
     */
    public static String probTrans(Random rand, ArrayList<String[]> curTrans)
    {
        //totalWeight and variable to temporarily hold a weight
        int totalWeight = 0;
        int tempWeight = 0;

        //number we psuedo generate randomly
        int randomInt = 0;

        //index at where we are in the array
        int index = 0;

        //make a new "Random" object that can generate what we need
        Random rnd = new Random();

        //base check to see if curTrans is null or our tran_prob is 0
        if(curTrans == null || Config.TRAN_PROB == 0 )
        {
            return null;
        }

        try
        {
            //sum the weights of trans_prob in each array
            for (int i = 0; i < curTrans.size(); i++)
            {
                totalWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
            }

            // psuedo-random generation of a number between zero and totalWeight - 1
            randomInt = rnd.nextInt(totalWeight);

            // for each loop that will hit every array index Tran_Prob
            for (int i = 0; i < curTrans.size(); i++)
            {
                //sum the weight leading up to the comparison
                tempWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);

                //comparrison check
                if(tempWeight > randomInt)
                {
                    // convert index found to string and return
                    return curTrans.get(i)[Config.TRAN_ROOM_ID];
                }
            }

        }

        //as specified
        catch(NumberFormatException e)
        {
            return null;
        }


        //The room id that was randomly selected if the sum of probabilities is  not greater than 0.
        return null;
    }

    /**
     * This is the main method for the Story Adventure game. It consists of the main game loop and
     * play again loop with calls to the various supporting methods. This method will evolve over
     * the 3 milestones.
     *
     * The Scanner object to read from System.in and the Random object with a seed of Config.SEED
     * will be created in the main method and used as arguments for the supporting methods as
     * required.
     *
     * Milestone #1:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #2:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the 
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - Prompt the user for a number between -1 and the number of transitions 
     *                     minus 1, using the promptInt method with a prompt of "Choose: "
     *                   - If the returned value is -1:
     *                      - read a char using promptChar with a prompt of
     *                        "Are you sure you want to quit the adventure? "
     *                      - Set the current room ID to Config.FAIL if that character returned is 'y'
     *                   - Otherwise: Set the current room ID to the room ID at index 
     *                                Config.TRAN_ROOM_ID of the selected transition.
     *                 - Otherwise, the current transition is terminal: Set the current room ID to 
     *                   the terminal state in the transition String array.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a 
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line): 
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *  - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #3:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the 
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - If the value returnd by the probTrans method is null:
     *                     - Prompt the user for a number between -2 and the number of transitions 
     *                       minus 1, using the promptInt method with a prompt of "Choose: "
     *                     - If the returned value is -1:
     *                        - read a char using promptChar with a prompt of
     *                          "Are you sure you want to quit the adventure? "
     *                        - Set the current room ID to Config.FAIL if that character returned is 
     *                          'y'
     *                     - If the returned value is -2:
     *                        - read a String using the promptString method with a prompt of:
     *                          "Bookmarking current location: curRoom. Enter bookmark filename: ", 
     *                          where curRoom is the current room ID.
     *                        - Call the saveBookmark method and output (terminated by a new line):
     *                           - if successful: "Bookmark saved in fSave"
     *                           - if unsuccessful: "Error saving bookmark in fSave"
     *                       where fSave is the String returned by promptString.
     *                     - Otherwise: Set the current room ID to the room id at index 
     *                                  Config.TRAN_ROOM_ID of the selected transition.
     *                   - Otherwise, the value returned by probTrans is not null: make this value
     *                     the current room ID.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL.
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a 
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line): 
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline
     */
    public static void main(String[] args)
    {
        String welcome = "Welcome to this choose your own adventure system!";
        String filename = "Please enter the story filename: ";
        String choose = "Choose: ";
        String promptChar = "Are you sure you want to quit the adventure? ";
        String failure = "You failed to complete the adventure. Better luck next time!";
        String congrats = "Congratulations! You successfully completed the adventure!";
        String tryAgain = "Do you want to try again? ";

        char userChar = ' ';

        //boolean values to keep the game running, and see if the player wants to go again
        boolean game = true;
        boolean play = true;

        //scanner that we need to pass
        Scanner input = new Scanner(System.in);

        //generate the random see
        Random rand = new Random(Config.SEED);

        //transition probability holder
        String tranProbab = null;

        //display welcome sentence
        System.out.println(welcome);

        //let code start
        while (play)
        {

            //make the arraylist lists, of string[] (2D) or arraylist of string[] (3D)
            ArrayList<String[]> rooms = new ArrayList<String[]>();
            ArrayList<ArrayList<String[]>> trans = new ArrayList<ArrayList<String[]>>();

            //make a string array that is the current room
            String[] curRoom = new String[1];

            //prompt the string to get the file name
            String fileName = promptString(input, filename);

            //try to parse the file, if success continue
            if (parseFile(fileName, rooms, trans, curRoom))
            {
                //begin the game -- actually
                while (game)
                {
                    //display the current room
                    displayRoom(curRoom[0], rooms);
                    ArrayList<String[]> curTrans = new ArrayList<String[]>();

                    //display the transitions and save it in the curTrans array
                    curTrans = displayTransitions(curRoom[0], rooms, trans);

                    //next room ID, selected randomly from transition probability weights
                    tranProbab = probTrans(rand, curTrans);

                    //if the current transition is NOT success or failure
                    if (!curTrans.get(0)[Config.TRAN_DESC].contains(Config.SUCCESS)
                        && !(curTrans.get(0)[Config.TRAN_DESC].contains(Config.FAIL)))
                    {
                        //make the weight irrelevant
                        if (tranProbab == null)
                        {
                            //look to get an integer value from the user -> where to go
                            int userAnswerInt = promptInt(input, choose, -2, curTrans.size() - 1);

                            //if the number is nul (-1), then..
                            if (userAnswerInt == -1)
                            {
                                //prompt for a character to see if the player want to go again, if yes, break
                                //set the current room to failure though cause they didnt finish
                                char userIntToChar = promptChar(input, promptChar);

                                //handle if the user enters 'y'
                                if (userIntToChar == 'y') {
                                    curRoom[0] = Config.FAIL;
                                    break;
                                }
                            }

                            //if the user enters a -2, look to bookmark
                            else if (userAnswerInt == -2)
                            {
                                //String to go for bookmarking
                                String bookmarkFile = promptString(input, "Bookmarking current location: "
                                        + curRoom[0] + ". Enter bookmark filename: ");

                                //boolean to see if the bookmark has been saved or not
                                boolean bookmarkBoolean = saveBookmark(fileName, curRoom[0], bookmarkFile);

                                //if we did save is successfully then we want to alert the user that it worked
                                if (bookmarkBoolean)
                                {
                                    System.out.println("Bookmark saved in " + bookmarkFile);
                                }

                                //if it did not save correctly
                                else {
                                    System.out.println("Error saving bookmark in " + bookmarkFile);
                                }

                                break;

                            }

                            //neither neg 1 or neg 2 -> fill our current room array
                            else {
                                curRoom[0] = rooms.get(getRoomIndex(curTrans.get(userAnswerInt)
                                        [Config.TRAN_ROOM_ID], rooms))[Config.ROOM_ID];
                            }
                        }

                        //add the tran probability
                        else {
                            curRoom[0] = tranProbab;
                        }

                    }

                    //the user does not want to game again
                    else {
                        game = false;
                    }
                }


                //we need to handle the failure
                if (curRoom[0].contentEquals(Config.FAIL)
                        || trans.get(getRoomIndex(curRoom[0], rooms)).get(0)[Config.TRAN_DESC].contains(Config.FAIL))
                {
                    //print the failure alert
                    System.out.println(failure);
                }

                //handle if the room is a success
                else if (trans.get(getRoomIndex(curRoom[0], rooms)).get(0)[Config.TRAN_DESC]
                    .contains(Config.SUCCESS))
                {
                    //print the congrats sign
                    System.out.println(congrats);
                }

                //prompt the user char, and evaluate what action to take
                userChar = promptChar(input, tryAgain);



            }

            //if the user enters a 'n', end everything
            else if (userChar == 'n')
            {
                System.out.println("Thank you for playing!");
                play = false;

            }

        }
    }
}
