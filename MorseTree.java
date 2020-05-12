package com.company;

import java.io.File;
import java.util.Scanner;

public class MorseTree {
    private NODE root;

    public MorseTree() throws Exception
    {
        root = new NODE();
        root = createTree();
    }
    public NODE createTree() throws Exception
    {

        String morseSymbols = "";
        char morseLetter = ' ';

        File inputFile = new File("morse.txt");
        Scanner scnr = new Scanner(inputFile);

        String temp;

        while(scnr.hasNextLine())
        {
            temp = scnr.next();
            morseLetter = temp.charAt(0);
            morseSymbols = scnr.nextLine();
            morseSymbols = morseSymbols.trim();

            root = insertNode(root, morseLetter, morseSymbols);
        }
        return root;

    }
    public NODE insertNode(NODE temp, char morseLetter, String symbols)
    {
        if (symbols.isEmpty())
        {
            temp.letter = morseLetter;
            return temp;
        }
        else if (symbols.charAt(0) == '.')
        {
            if (temp.left == null)
            {
                temp.left = new NODE();
            }
            temp.left = insertNode(temp.left, morseLetter, symbols.substring(1));
            return temp;
        }
        else if (symbols.charAt(0) == '-')
        {
            if (temp.right == null)
            {
                temp.right = new NODE();
            }
            temp.right = insertNode(temp.right, morseLetter, symbols.substring(1));
            return temp;
        }
        else
        {
            System.out.println("Error");
            return temp;
        }

    }
    public String translate(NODE temp, String morseSymbols, String result)
    {
        if (morseSymbols.isEmpty())
        {
            if(temp != null)
            {
                result = result.concat(Character.toString(temp.letter));
            }
            return result;
        }
        else if (morseSymbols.charAt(0) == '.')
        {
            return translate(temp.left, morseSymbols.substring(1), result);
        }
        else if (morseSymbols.charAt(0) == '-')
        {
            return translate(temp.right, morseSymbols.substring(1), result);
        }
        else if (morseSymbols.charAt(0) == 'X')
        {
            if (temp != null && temp.letter != ' ')
            {
                result = result.concat(Character.toString(temp.letter));
            }
            if (morseSymbols.length() > 1)
            {
                if (morseSymbols.charAt(1) == 'X')
                {
                    result = result.concat(" ");
                }
            }
            return translate(root, morseSymbols.substring(1), result);
        }
        else
        {
            if (temp != null && temp.letter != ' ')
            {
                result = result.concat(Character.toString(temp.letter));
            }
            result = result.concat("?");
            return translate(root, morseSymbols.substring(1), result);
        }
    }
    public String interpretMorse(String morseSymbols)
    {
        String result = "";
        result = translate(root, morseSymbols, result);
        return result;
    }
}
