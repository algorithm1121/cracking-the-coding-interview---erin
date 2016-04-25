package com.erin;

import java.util.*;

/**
 * Created by Erin on 4/23/16.
 */
public class ChapterOne {

      /*
    * 1.1 Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structure?
    * */

    //1.1 solution1
    public static boolean isUniqueCharacter1(String string) {

        boolean[] char_set = new boolean[256];
        for (int i = 0; i < string.length(); i++){

            int val = string.charAt(i);

            if (char_set[val] == true) 	return false;
            else char_set[val] = true;
        }
        return true;
    }

    //1.1 solution2
    public static boolean isUniqueCharacter2 (String string) {

        int checker = 0;
        for (int i = 0; i < string.length(); i++){

            int val = string.charAt(i) - 'a';
            if((checker & (1 << val)) > 0)	return false;
            checker |= (1 << val);
        }
        return true;
    }

    /*
    * 1.2' Write code to reverse a C-Style String
    * */
    //1.2' solution
    public static String reverseString(String string) {

        char[] charArray = string.toCharArray();
        char temp;
        for (int i = 0, j = charArray.length - 1; i < j; i++, j--){

            temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }

        String returnString = new String(charArray);
        return returnString;
    }

    /*
    * 1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the other
    * */
    //1.2 solution one

    public static boolean permutation1(String s1, String s2){

        if (s1.length() != s2.length()) {

            return false;
        }

        return sort(s1).equals(sort(s2));
    }

    public static String sort(String s){

        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }

    //1.2 solution two
    public static boolean permutation2(String s, String t){

        if (s.length() != t.length()) {

            return false;
        }

        int[] letter = new int[128];

        for(int i = 0; i < s.length(); i++){

            letter[s.charAt(i)]++;
        }

        for(int i = 0; i < t.length(); i++){

            int c = letter[t.charAt(i)];
            c--;
            if (c < 0){

                return false;
            }
        }

        return true;

    }

    /*
    *1.3 Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to
    * hold the additional characters, and that you are given the "true" length of the string. (Note: if implementing in Java, please use
    * a character array so that you can perform this operation in place)
    * */

    //1.3 solution
    public static String replacePlaces(String string, int trueLength){

        int spaceCount = 0;
        char[] charArray = string.toCharArray();

        for(int i = 0; i < trueLength; i++){

            if(charArray[i] == ' '){

                spaceCount++;
            }
        }

        int length = trueLength + spaceCount * 2;
        int index = length - 1;

        if (trueLength < string.length())    charArray[trueLength] = '\0';//?????

        for(int i = trueLength - 1; i >= 0; i--){

            if(charArray[i] != ' ') {

                charArray[index] = charArray[i];
                index--;
            }else {

                charArray[index] = '0';
                charArray[index - 1] = '2';
                charArray[index - 2] = '%';
                index = index - 3;
            }
        }

        return new String(charArray);
    }

    /*
    * 1.4 Give a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or
    * phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does
    * not need to be limited to just dictionary words
    * EXAMPLE
    * Input: Tact Coa
    * Output: True(permutations: "taco cat", "atco cta", etc.)
    * */

    //1.4 solution one (own)
    public  static boolean isPermutationOfPalindrome1(String string){

        int[] letter = new int[26];

        for (int i = 0; i < string.length(); i++){

            if (string.charAt(i) >= 97 && string.charAt(i) <= 122){

                letter[string.charAt(i) - 97]++;
            }
        }

        int centerLetterIndex = 0;
        for (int i = 0; i < 26; i++){

            if(letter[i] % 2 != 0 )      centerLetterIndex++;
            if(centerLetterIndex > 1)     return false;

        }
        return true;

    }

    //1.4 solution two

    public static boolean isPermutationOfPalindrome2(String string){

        int[] table = buildCharacterFrequencyTable(string);
        return checkOdd(table);
    }

    public static int getCharNumber(Character c){

        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);

        if(a <= val && val <= z){

            return val - a;
        }
        return -1;
    }
    public static int[] buildCharacterFrequencyTable(String string){

        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int[] table = new int[z - a + 1];

        for( char c : string.toCharArray()){

            int x = getCharNumber(c);
            if(x != -1) {

                table[x]++;
            }
        }
        return table;
    }

    public static boolean checkOdd(int[] table){

        boolean foundOdd = false;

        for(int count : table){

            if(count % 2 == 1){

                if(foundOdd){

                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }


    //1.4 solution3(most elegant)
    public static boolean isPermutationOfPalindrome3(String string){

        int bitVector = createBitVector(string);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    public static int toggle(int bitVector, int index){

        if(index < 0)   return bitVector;

        int mask = 1 << index;
        if((bitVector & mask) == 0) {

            bitVector |= mask;

        }else {

            bitVector &= ~mask;

        }
        return bitVector;
    }

    public static int createBitVector(String phrase){

        int bitVector = 0;

        for (char c : phrase.toCharArray()){

            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);

        }
        return bitVector;
    }

    public static boolean checkExactlyOneBitSet(int bitVector) {

        return (bitVector & (bitVector - 1)) == 0;
    }

    /**
     * One away: There are three types of edits that can be performed on strings. Insert a character, remove a character,
     * or replace a character. Given two strings, write a function to check if they are one edit(or zero edit) away.
     */

    //1.5 solution one
    public static boolean checkEdit1(String first, String second){

        // Insert a character.
        if(second.length() == first.length())    return oneEditReplace(first, second);
        else if(second.length() - first.length() == 1)    return insertOne(first, second);
        else if(first.length() - second.length() == -1)    return insertOne(second, first);

        return false;
    }

    public static boolean oneEditReplace(String first, String second){

        System.out.print("aa");
        boolean foundReplace = false;

        for(int i = 0; i < first.length(); i++){

            if(first.charAt(i) != second.charAt(i)){

                if(foundReplace){

                    return false;
                }
                foundReplace = true;
            }
        }
        return true;
    }

    public static boolean insertOne(String first, String second){

        int index1 = 0;
        int index2 = 0;

        while(index1 < first.length() && index2 < second.length()){

            if(first.charAt(index1) != second.charAt(index2)){

                if(index1 != index2){

                    return false;
                }
                index2++;
            }else{

                index1++;
                index2++;
            }
        }
        return true;
    }

    //1.5 solution two
    public static boolean checkEdit2(String first, String second){

        if(Math.abs(first.length() - second.length()) > 1){

            return false;
        }

        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while(index1< s1.length() && index2 < s2.length()){

            if(s1.charAt(index1) != s2.charAt(index2)){

                if(foundDifference){

                    return false;
                }
                foundDifference = true;

                if(s1.length() == s2.length()){

                    index1++;

                }
                index2++;

            }else {

                index1++;
                index2++;
            }

        }
        return true;
    }

     /*
     * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
     * For example, the string aabccccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than
     * the original string, your method should return the original string. You can assume the string has only uppercase
     * and lower case letters
     * */

    //1.6 solution one (own)
    public static String compressString1(String string){

        StringBuilder compressed = new StringBuilder();

        int i = 0, j = 1, count = 1;
        while(i < string.length() - 1 && j < string.length()){

            if(string.charAt(i) == string.charAt(j)){

                count++;
            }else{

                compressed.append(string.charAt(i));
                compressed.append((count));
                count = 1;
                i = j;
            }
            if(j == string.length() - 1){

                compressed.append(string.charAt(i));
                compressed.append((count));
            }
            j++;
        }

        if(string.length() <= compressed.length()){

            return string;
        }

        return compressed.toString();

    }

    //1.6 solution two

    public static String compressString2(String string){

        int compressedLength = countCompression(string);
        if(compressedLength >= string.length())     return string;

        StringBuilder compressed = new StringBuilder(compressedLength);
        int countConsecutive = 0;

        for(int i = 0; i < string.length(); i++){

            countConsecutive++;
            if(i+1 >= string.length() || string.charAt(i) != string.charAt(i + 1)){  //!!! the order

                compressed.append(string.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }

        return compressed.toString();

    }

    public static int countCompression(String string){

        int compressionLength = 0, countConsecutive = 0;

        for(int i = 0; i < string.length(); i++){

            countConsecutive++;
            if(i+1 >= string.length() ||string.charAt(i) != string.charAt(i+1)){  //the order cannot be change!

                compressionLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressionLength;

    }

     /*
     * 1.7 Rotate Matrix: Given an image represented by an N*N matrix, where each pixel in the image is 4 bytes, write a
     * method to rotate the image by 90 degrees. Can you do this in place?
     * */

    //1.7 solution
    public static boolean rotateMatrix(int[][] matrix){

        if(matrix.length == 0  || matrix[0].length != matrix.length)    return false;

        int n = matrix.length;
        for(int layer = 0; layer < n/2; layer++){

            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; i ++){

                int offset = i - layer;

                int top = matrix[first][i];

                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }

        for(int i = 0; i < matrix.length; i++){

            for(int j = 0; j < matrix.length; j++){

                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        return true;
    }

     /*
     * Zero Matrix: Write an algorithm such that if an element in an M*N matrix is 0, its entire row and column are set to 0
     * */

    //1.8 solution1
    public static void setZeros1(int[][] matrix){

        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];


        for(int i = 0; i < matrix.length; i++){

            for(int j = 0; j < matrix[0].length; j++){

                if(matrix[i][j] == 0){

                    rows[i] = true;
                    columns[j] = true;

                }
            }
        }

        for(int i = 0; i< rows.length; i++){

            if(rows[i]){

                for(int x = 0; x < matrix[0].length; x++){

                    matrix[i][x] = 0;
                }
            }
        }

        for(int i = 0; i < columns.length; i++){

            if(columns[i]){

                for(int x = 0; x < matrix[0].length; x++){

                    matrix[x][i] = 0;
                }
            }
        }

    }

    //1.8 solution2
    public static void setZeros2(int[][] matrix){

        boolean rowHasZero = false;
        boolean colHasZero = false;

        for(int j = 0; j < matrix[0].length; j++){

            if(matrix[0][j] == 0){

                rowHasZero = true;
                break;
            }
        }

        for(int i = 0; i < matrix.length; i++){

            if(matrix[i][0] == 0){

                colHasZero = true;
                break;
            }
        }

        for(int i = 1; i < matrix.length; i++){

            for(int j = 1; j < matrix[0].length; j++){

                if(matrix[i][j] == 0){

                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int j = 1; j < matrix[0].length; j++){

            if(matrix[0][j] == 0){

                nullCol(matrix, j);
            }
        }

        for(int i = 1; i < matrix.length; i++){

            if(matrix[i][0] == 0){

                nullRow(matrix, i);
            }
        }

        if(rowHasZero)     nullRow(matrix, 0);
        if(colHasZero)     nullCol(matrix, 0);

    }

    public static void nullRow(int[][] matrix, int row){

        for(int x = 0; x < matrix[0].length; x++){

            matrix[row][x] = 0;
        }
    }

    public static void nullCol(int[][] matrix, int col){

        for(int x = 0; x < matrix.length; x++){

            matrix[x][col] = 0;
        }

    }

    /*
    * String Rotation: Assume you have a method isSubstring which checks if one word is a substring of another. Given two
    * strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring(e.g., "waterbottle")
    * is a rotation of "erbottlewat".
    * */
    public static boolean stringRotation(String s1, String s2){

        if(s1.length() == s2.length() && s1.length() > 0){

            String s1s1 = s1 + s1;
            isSubString(s1s1, s1);
        }

        return false;
    }

    public static boolean isSubString(String x, String y){

        //implement method

        return true;
    }
    public static void main(String[] args) {

        String s1 = "Mr Smith    ";
        String s2 = "abcdef";
        String s3 = "attc coad";
        String first = "pale";
        String second = "palee";
        String string = "abcdaaaaaac";

        //System.out.print(isPermutationOfPalindrome1(s3)); //1.4

        int[][] matrix = new int[][]{

                { 0, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 0, 4, 4, 4, 0 },
        };

        //System.out.print(compressString2("aabccccccaaa")); //1.6
        setZeros2(matrix); //1.8
        for(int i = 0; i < matrix.length; i++){

            for(int j = 0; j < matrix.length; j++){

                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        /*
        System.out.print(isUniqueCharacter1(s2)); // 1.1
        System.out.print(isUniqueCharacter1(s2)); // 1.1
        System.out.print(isUniqueCharacter2(s2));// 1.1
        System.out.print(reverseString(s2)); //1.2'
        System.out.print(permutation1(s1, s2)); // 1.2
        System.out.print(permutation2(s1, s2)); //1.2
        System.out.print(replacePlaces(s1, 8)); //1.3
        System.out.print(isPermutationOfPalindrome1(s3)); //1.4
        System.out.print(isPermutationOfPalindrome2(s3)); //1.4
        System.out.print(checkEdit1(first, second));//1.5
        System.out.print(checkEdit2(first, second));//1.5
        System.out.print(compressString1("aabcdeef")); //1.6
        System.out.print(compressString2(string));//1.6
        System.out.print(rotateMatrix(matrix)); //1.7
        setZeros1(matrix) //1.8
        setZeros2(matrix) //1.8

        */

    }

}
