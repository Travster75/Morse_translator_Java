package com.company;

public class Main {

    public static void main(String[] args) throws Exception{

        MorseTree mTree = new MorseTree();

        String morse = "....X.X.-..X.-..X---XX.--X---X.-.X.-..X-..X.-.-.-X";

        String result = mTree.interpretMorse(morse);

        System.out.println(result);

    }
}
