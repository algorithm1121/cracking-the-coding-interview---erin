/**
 * Created by Erin on 4/20/16.
 */
import  java.util.*;
public class chapterOne {




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
        public static String sort(String s){

            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);

            return new String(charArray);
        }

        public static boolean permutation1(String s1, String s2){

            if (s1.length() != s2.length()) {

                return false;
            }

            return sort(s1).equals(sort(s2));
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

        //1.4 solution one
        public  static boolean isPermutationOfPalindrome1(String string){

            int[] letter = new int[128];

            for (int i = 0; i < string.length(); i++){

                if (string.charAt(i) >= 97 && string.charAt(i) <= 122){

                    letter[string.charAt(i)]++;
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

        public static void main(String[] args) {

            String s1 = "Mr Smith    ";
            String s2 = "abcdef";
            String s3 = "attc coa";
            String first = "pale";
            String second = "palee";
            System.out.print(checkEdit2(first, second));

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
        */

        }

}
