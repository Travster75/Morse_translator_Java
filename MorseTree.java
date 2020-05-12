//Travis Bender

package com.company;

import java.io.File;
import java.util.Scanner;

//Class for the Tree
public class MorseTree {
    private NODE root;

    // Default constructor
    public MorseTree() throws Exception
    {
        root = new NODE();
        root = createTree();
    }

    // Creates the tree
    public NODE createTree() throws Exception
    {

        String morseSymbols = "";
        char morseLetter = ' ';

        //Open the morse file
        File inputFile = new File("//Users/slbender/Desktop/Travis/JavaRefresh/src/com/company/morse.txt");
        Scanner scnr = new Scanner(inputFile);

        String temp;

        //Gather the data from the file
        while(scnr.hasNextLine())
        {
            temp = scnr.next();
            morseLetter = temp.charAt(0);
            morseSymbols = scnr.nextLine();
            morseSymbols = morseSymbols.trim();

            //Insert into the tree
            root = insertNode(root, morseLetter, morseSymbols);
        }
        return root;

    }

    //Inserts data into the tree
    public NODE insertNode(NODE temp, char morseLetter, String symbols)
    {
        // If reached end of morse sequence
        if (symbols.isEmpty())
        {
            temp.letter = morseLetter;
            return temp;
        }
        // If dot, go left
        else if (symbols.charAt(0) == '.')
        {
            if (temp.left == null) {
                temp.left = new NODE();
            }
            temp.left = insertNode(temp.left, morseLetter, symbols.substring(1));
            return temp;
        }
        //If dash, go right
        else if (symbols.charAt(0) == '-')
        {
            if (temp.right == null)
            {
                temp.right = new NODE();
            }
            temp.right = insertNode(temp.right, morseLetter, symbols.substring(1));
            return temp;
        }
        //If an error is encountered
        else
        {
            System.out.println("Error");
            return temp;
        }

    }

    //Translates the morse sequence
    public String translate(NODE temp, String morseSymbols, String result)
    {
        //If reached end of morse sequence
        if (morseSymbols.isEmpty())
        {
            if(temp != null)
            {
                //Add translated string to result
                result = result.concat(Character.toString(temp.letter));
            }
            return result;
        }
        //If dot, go left
        else if (morseSymbols.charAt(0) == '.')
        {
            return translate(temp.left, morseSymbols.substring(1), result);
        }
        //If dash, go right
        else if (morseSymbols.charAt(0) == '-')
        {
            return translate(temp.right, morseSymbols.substring(1), result);
        }
        //X represents end of morse letter
        else if (morseSymbols.charAt(0) == 'X')
        {
            if (temp != null && temp.letter != ' ')
            {
                result = result.concat(Character.toString(temp.letter));
            }
            //Two X's represents a space
            if (morseSymbols.length() > 1)
            {
                if (morseSymbols.charAt(1) == 'X')
                {
                    result = result.concat(" ");
                }
            }
            //Begin at top of tree
            return translate(root, morseSymbols.substring(1), result);
        }
        //Insert unknown characters
        else
        {
            if (temp != null && temp.letter != ' ')
            {
                result = result.concat(Character.toString(temp.letter));
            }
            result = result.concat("?");
            //Begin at top of tree
            return translate(root, morseSymbols.substring(1), result);
        }
    }
    //Function to handle recursive translate function
    public String interpretMorse(String morseSymbols)
    {
        String result = "";
        result = translate(root, morseSymbols, result);
        return result;
    }
}
