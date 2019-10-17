//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestAdventureStory
// Files:           AdventureStory.java TestAdventureStory.java
// Course:          CS 200 Spring 2019
//
// Author:          Lucas Laibly
// Email:           laibly@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the AdventureStory
 * class as they are developed. These methods are private since they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author Lucas Laibly
 *
 */
public class TestAdventureStory {
    
    /**
     * This is the main method that runs the various tests. 
     * 
     * @param args  (unused)
     */
    public static void main(String[] args) {
        //Milestone 1 Tests
        //testPromptInt(); // --- D O N E
        //testPromptChar(); // --- D O N E
        //testPromptString(); // --- D O N E
        //testGetRoomIndex(); // --- D O N E
        //testGetRoomDetails(); // --- D O N E

        //Milestone 2 Tests
        //testParseStory(); // --- D O N E

        
        //Milestone 3 Tests
        //testParseBookmark(); // --- D O N E
        testProbTrans();
    }

    /**
     * This runs some tests on the promptInt method.
     */ 
    private static void testPromptInt() {
        boolean error = false;
        
        //tests w /n
        { 
            Scanner in = new Scanner("8\n");
            int expected = 8;
            int result = AdventureStory.promptInt(in, "Enter integer: ", 5, 15);
            if(expected != result) {
                System.out.println("1) testPromptInt expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        //tests w invalid int
        { 
            Scanner in = new Scanner("2");
            int expected = 8;
            int result = AdventureStory.promptInt(in, "Enter integer: ", 5, 15);
            if(expected == result) {
                System.out.println("2) testPromptInt expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        //tests with not an int
        { 
            Scanner in = new Scanner("a");
            int expected = 8;
            int result = AdventureStory.promptInt(in, "Enter integer: ", 5, 15);
            if(expected != result) {
                System.out.println("1) testPromptInt expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        if(error) {
            System.out.println("testPromptInt failed");
        } else {
            System.out.println("testPromptInt passed");
        }        
    }
    
    /**
     * This runs some tests on the promptString method.
     */ 
    private static void testPromptString() {
        boolean error = false;
        
        { 
            Scanner in = new Scanner("foobar\n");
            String expected = "foobar";
            String result = AdventureStory.promptString(in, "Enter: ");
            if(!expected.equals(result)) {
                System.out.println("1) testPromptString expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        if(error) {
            System.out.println("testPromptString failed");
        } else {
            System.out.println("testPromptString passed");
        }        
    }

    /**
     * This runs some tests on the promptChar method.
     */ 
    private static void testPromptChar() {
        boolean error = false;
        
        { 
            Scanner in = new Scanner("  foobar\n");
            char expected = 'f';
            char result = AdventureStory.promptChar(in, "Enter: ");
            if(expected != result) {
                System.out.println("1) testPromptChar expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        if(error) {
            System.out.println("testPromptChar failed");
        } else {
            System.out.println("testPromptChar passed");
        }        
    }
    

    /**
     * This runs some tests on the getRoomIndex method.
     */ 
    private static void testGetRoomIndex() {
        boolean error = false;
        
        {
            // Assuming normal Config.java
            ArrayList<String[]> in
                = new ArrayList<>(Arrays.asList(new String[][]{{"id1","",""},{"id2","",""}}));
            int expected = -1;
            int result = AdventureStory.getRoomIndex(new String("id3"),in);
            if(expected != result) {
                System.out.println("1) testGetRoomIndex expected: " + expected + " result: " + result);
                error = true;
            }
        }
        
        if(error) {
            System.out.println("testGetRoomIndex failed");
        } else {
            System.out.println("testGetRoomIndex passed");
        }        
    }

    /**
     * This runs some tests on the getRoomIndex method.
     */ 
    private static void testGetRoomDetails() {
        boolean error = false;
        
        {
            //Assuming normal Config.java
            ArrayList<String[]> in
                = new ArrayList<>(Arrays.asList(new String[][]{{"id1","",""},{"id2","",""}}));
            String[] expected = in.get(0);
            String[] result = AdventureStory.getRoomDetails(new String("id1"),in);
            if(!Arrays.equals(expected,result)) {
                System.out.println("1) testGetRoomDetails expected: " + Arrays.toString(expected)
                                   + " result: " + Arrays.toString(result));
                error = true;
            }
        }
        
        if(error) {
            System.out.println("testGetRoomDetails failed");
        } else {
            System.out.println("testGetRoomDetails passed");
        }        
    }

    private static void testParseStory() {
        boolean error = false;

        // <editor-fold desc= "given test">
        {
            Scanner testSc
                = new Scanner("#!STORY\n" +
                              "R1: Room 1\nRoom 1 description\n;;;\n: Transition 1 -> 1\n: Transition 2 -> 2\n" +
                              "R2: Room 2 \n Room 2 description \n;;;\n =) \n");
            ArrayList<String[]> arrRooms = new ArrayList<String[]>();
            ArrayList<ArrayList<String[]> >  arrTrans = new ArrayList<ArrayList<String[]> >();
            String[] curRoom = new String[1];
            boolean passed = true;
            if(!AdventureStory.parseStory(testSc, arrRooms, arrTrans, curRoom)) {
                System.out.println("parseStory 1: returned false instead of true.");
                passed = false;
            }
            //Assuming normal Config.java
            //Expected ArrayList of room details:
            ArrayList<String[]> expRooms =
                new ArrayList<String[]>(Arrays.asList(new String[][]{{"1",
                                                                      "Room 1",
                                                                      "Room 1 description"},
                                                                     {"2",
                                                                      "Room 2",
                                                                      "Room 2 description"}}));
            if(!compareArrayListsArrays(arrRooms, expRooms)) {
                System.out.println("parseStory 1: \nrooms ArrayList returned: \n" +
                                   Arrays.deepToString(arrRooms.toArray()) +
                                   "\nExpected: \n" +
                                   Arrays.deepToString(expRooms.toArray()) + "\n");
                passed = false;
            }
            //Expected ArrayList of ArrayList of transition details:
            ArrayList<String[]> room1Trans =
                new ArrayList<String[]>(Arrays.asList(new String[][]{{"Transition 1",
                                                                      "1",
                                                                      null},
                                                                     {"Transition 2",
                                                                      "2",
                                                                      null}}));
            ArrayList<String[]> room2Trans =
                new ArrayList<String[]>(Arrays.asList(new String[][]{{"=)", null, null}}));
            ArrayList<ArrayList<String[]> > expTrans = new ArrayList<ArrayList<String[]> >();
            expTrans.add(room1Trans);
            expTrans.add(room2Trans);
            if(!compare2dArrayLists(arrTrans, expTrans)) {
                System.out.println("parseStory 1: \ntransition ArrayList returned: \n" +
                                   toString2dArrayLists(arrTrans) +
                                   "\nExpected: \n" +
                                   toString2dArrayLists(expTrans) + "\n");
                passed = false;
            }
            if(passed) System.out.println("parseStory 1: passed"); 
            
        }

        // </editor-fold>

        //no success or fail, one transition
        {
            Scanner testSc
                    = new Scanner("#!STORY\n" +
                    "R1: Room 1\nRoom 1 description\n;;;\n: Transition 1 -> 1");
            ArrayList<String[]> arrRooms = new ArrayList<String[]>();
            ArrayList<ArrayList<String[]> >  arrTrans = new ArrayList<ArrayList<String[]> >();
            String[] curRoom = new String[1];
            boolean passed = true;
            if(!AdventureStory.parseStory(testSc, arrRooms, arrTrans, curRoom)) {
                System.out.println("parseStory 2: returned false instead of true.");
                passed = false;
            }
            //Assuming normal Config.java
            //Expected ArrayList of room details:
            ArrayList<String[]> expRooms =
                    new ArrayList<String[]>(Arrays.asList(new String[][]{{"1",
                            "Room 1",
                            "Room 1 description"}}));
            if(!compareArrayListsArrays(arrRooms, expRooms)) {
                System.out.println("parseStory 2: \nrooms ArrayList returned: \n" +
                        Arrays.deepToString(arrRooms.toArray()) +
                        "\nExpected: \n" +
                        Arrays.deepToString(expRooms.toArray()) + "\n");
                passed = false;
            }
            //Expected ArrayList of ArrayList of transition details:
            ArrayList<String[]> room1Trans =
                    new ArrayList<String[]>(Arrays.asList(new String[][]{
                            {"Transition 1",
                            "1",
                            null}}));
            ArrayList<ArrayList<String[]> > expTrans = new ArrayList<ArrayList<String[]> >();
            expTrans.add(room1Trans);
            if(!compare2dArrayLists(arrTrans, expTrans)) {
                System.out.println("parseStory 2: \ntransition ArrayList returned: \n" +
                        toString2dArrayLists(arrTrans) +
                        "\nExpected: \n" +
                        toString2dArrayLists(expTrans) + "\n");
                passed = false;
            }
            if(passed) System.out.println("parseStory 2: passed");

        }

        //look to fail --> we wnat to see the "Error parseing file: ... " line from Parse Story
        {
            Scanner testSc
                    = new Scanner("#!STORY\n" +
                    "R: \n \n;;;\n: ");
            ArrayList<String[]> arrRooms = new ArrayList<String[]>();
            ArrayList<ArrayList<String[]> >  arrTrans = new ArrayList<ArrayList<String[]> >();
            String[] curRoom = new String[1];
            boolean passed = true;

            if(AdventureStory.parseStory(testSc, arrRooms, arrTrans, curRoom))
            {
                System.out.println("parseStory 3: returned false instead of true.");
                passed = false;
            }

            //Assuming normal Config.java

            //Expected ArrayList of room details:
            ArrayList<String[]> expRooms =
                    new ArrayList<String[]>(Arrays.asList(new String[][]
                            {{"","" ,""}}));

            if(!compareArrayListsArrays(arrRooms, expRooms))
            {
                System.out.println("parseStory 3: \nrooms ArrayList returned: \n" +
                        Arrays.deepToString(arrRooms.toArray()) +
                        "\nExpected: \n" +
                        Arrays.deepToString(expRooms.toArray()) + "\n");
                passed = false;
            }
            //Expected ArrayList of ArrayList of transition details:
            ArrayList<String[]> room1Trans =
                    new ArrayList<String[]>(Arrays.asList(new String[][]{}));
            ArrayList<ArrayList<String[]> > expTrans = new ArrayList<ArrayList<String[]> >();
            expTrans.add(room1Trans);

            if(compare2dArrayLists(arrTrans, expTrans))
            {
                System.out.println("parseStory 3: \ntransition ArrayList returned: \n" +
                        toString2dArrayLists(arrTrans) +
                        "\nExpected: \n" +
                        toString2dArrayLists(expTrans) + "\n");
                passed = false;
            }
            if(passed) System.out.println("parseStory 3: passed");

        }




    }
    private static String toString2dArrayLists(ArrayList<ArrayList<String[]> > arrL1) {
        String toRet = "[\n";
        for(int i = 0; i < arrL1.size(); ++i) {
            toRet += "\t" + Arrays.deepToString(arrL1.get(i).toArray());
            if(i < arrL1.size() - 1) toRet += ",";
            toRet += "\n";
        }
        toRet += "]";
        return toRet;
    }

    private static boolean compareArrayListsArrays(ArrayList<String[]> arrL1, ArrayList<String[]> arrL2) {
        if(arrL1.size() != arrL2.size()) return false;
        for(int i = 0; i < arrL1.size(); ++i) {
            if(!Arrays.deepEquals(arrL1.get(i), arrL2.get(i))) return false;
        }
        return true;
    }

    private static boolean compare2dArrayLists(ArrayList<ArrayList<String[]> > arrL1,
                                               ArrayList<ArrayList<String[]> > arrL2) {
        if(arrL1.size() != arrL2.size()) return false;
        for(int i = 0; i < arrL1.size(); ++i) {
            if(!compareArrayListsArrays(arrL1.get(i), arrL2.get(i))) return false;
        }
        return true;
    }

    private static void testParseBookmark()
    {

        // expect success mind reading # bookmark
        boolean error = false;
        boolean success = true;

        {
            File file = new File("parseBookmarkTest.story");
            try
            {
                Scanner in = new Scanner(file);
                ArrayList<String[]> rooms = new ArrayList<String[]>();
                ArrayList<ArrayList<String[]>> trans = new ArrayList<ArrayList<String[]>>();
                String[] curRoom = new String[Config.ROOM_DET_LEN];

                boolean result = AdventureStory.parseBookmark(in, rooms, trans, curRoom);
                if(success == result) {
                    System.out.println("1) testPromptInt expected: " + success + " result: " + result);
                    error = true;
                }
            }
            catch(IOException e)
            {
                System.out.println("Unable to handle file");
            }
        }
        if(error) {
            System.out.println("testParseBookmark failed");
        } else {
            System.out.println("testParseBookmark test 1 passed");
        }

        // will pass, expect to see "Unable to handle file" -> file wont exist
        {
             error = false;
             success = true;

            {
                File file = new File("somethingThatNoExist.story");
                try
                {
                    Scanner in = new Scanner(file);
                    ArrayList<String[]> rooms = new ArrayList<String[]>();
                    ArrayList<ArrayList<String[]>> trans = new ArrayList<ArrayList<String[]>>();
                    String[] curRoom = new String[Config.ROOM_DET_LEN];

                    boolean result = AdventureStory.parseBookmark(in, rooms, trans, curRoom);
                    if(success == result) {
                        System.out.println("1) testPromptInt expected: " + success + " result: " + result);
                        error = true;
                    }

                    else{
                        System.out.println("testParseBookmark test 2 failed!");
                    }
                }
                catch(IOException e)
                {
                    System.out.println("Unable to handle file");
                }
            }

            if(error) {
                System.out.println("testParseBookmark failed");
            } else {
                System.out.println("testParseBookmark test 2 passed");
            }
        }
    }


    private static void testProbTrans()

    {
        ArrayList<String[]> curTrans = new ArrayList<String[]>();
        Random rnd = new Random(3);
        String hold = "";

        hold = AdventureStory.probTrans(rnd, curTrans);
    }
}
