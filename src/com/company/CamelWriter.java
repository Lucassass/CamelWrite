package com.company;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;


public class CamelWriter {

    // converts Strings to lowerCamel case
    private static String toLowerCamelCase(String line) {
        String result = "";
        if (line.length() == 0) {
            return result;
        }
        char firstChar = line.charAt(0);
        char firstCharToLowerCase = Character.toLowerCase(firstChar);
        result = result + firstCharToLowerCase;
        for (int i = 1; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            char previousChar = line.charAt(i - 1);
            if (previousChar == ' ') {
                char currentCharToUpperCase = Character.toUpperCase(currentChar);
                result = result + currentCharToUpperCase;
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar);
                result = result + currentCharToLowerCase;
                result = result.replaceAll("\\s", "");
            }
        }
        return result;
    }

    // Read the file lines by lines and calls the methode convert2camel
    private void readLines() {
        BufferedReader reader;
        try {
            //insert the file that need to be converted here
            reader = new BufferedReader(new FileReader("MaryAnn.txt"));
            String line = reader.readLine();
            while( line!= null ){
                convert2camel(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // convert2camel tells what to do with the each line
    private void convert2camel(String line) {
        // if the line is empty print the line because it means that it is an separation
        if (line == null || line.isEmpty()) {
            System.out.println(line);
        }
        // if the line contains text we tell it to preform the conversion to lowerCamelCase
        else {
            System.out.println(toLowerCamelCase(line));
        }
    }
    public static void main(String args[]) throws FileNotFoundException {
        // creates new file camelWriter.txt and inputs the conversions into the new file
        PrintStream fileOut = new PrintStream("./camelWriter.txt");
        System.setOut(fileOut);
        CamelWriter cw = new CamelWriter();
        cw.readLines();
    }
}