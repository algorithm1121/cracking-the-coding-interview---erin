package com.erin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erin on 4/23/16.
 */
public class ChapterOneTest {

    private ChapterOne chapterOne;

    @Before
    public void setUp() {
        chapterOne = new ChapterOne();
    }

    @Test
    public void isUniqueCharacter1Test() {

        String str1 = "a";
        //没有重复字母,返回结果应该为true
        Assert.assertTrue(chapterOne.isUniqueCharacter1(str1));

        String str2 = "hello";
        //l 是重复的,返回结果应该为false
        Assert.assertTrue(!chapterOne.isUniqueCharacter1(str2));
    }

    @Test
    public void isUniqueCharacter2Test(){

        String str1 = "a";
        //没有重复字母,返回结果应该为true
        Assert.assertTrue(chapterOne.isUniqueCharacter1(str1));

        String str2 = "hello";
        //l 是重复的,返回结果应该为false
        Assert.assertTrue(!chapterOne.isUniqueCharacter1(str2));
    }

    @Test
    public void reverseStringTest(){

        String str = "reverse";
        // The return value is supposed to be "esrever"
        Assert.assertEquals(chapterOne.reverseString(str), "esrever");  //???
    }

    @Test
    public void permutation1Test(){

        String str1 = "blueberry";
        String str2 = "luebrryeb";
        String str3 = "luebrryec";

        //str2 is a permutation of str1, the return value should be true;
        Assert.assertTrue(chapterOne.permutation1(str1, str2));

        //str3 is not a permutaiton of str1, the return value should be false;
        Assert.assertFalse(chapterOne.permutation1(str1, str3));
    }

    @Test
    public void permutation2Test(){

        String str1 = "blueberry";
        String str2 = "luebrryeb";
        String str3 = "luebrryec";

        //str2 is a permutation of str1, the return value should be true;
        Assert.assertTrue(chapterOne.permutation2(str1, str2));

        //str3 is not a permutaiton of str1, the return value should be false;
        Assert.assertFalse(chapterOne.permutation2(str1, str3));
    }

    @Test
    public void replacePlacesTest(){

        String str = "Mr Smith  ";

        Assert.assertEquals("Mr%20Smith", chapterOne.replacePlaces(str, 8));
    }

    @Test
    public void isPermutationOfPalindrome1Test(){

        String str1 = "attc coa";
        String str2 = "atttac";

        Assert.assertTrue(chapterOne.isPermutationOfPalindrome1(str1));

        Assert.assertFalse(chapterOne.isPermutationOfPalindrome1(str2));
    }

    @Test
    public void isPermutationOfPalindrome2Test(){

        String str1 = "attc coa";
        String str2 = "atttac";

        Assert.assertTrue(chapterOne.isPermutationOfPalindrome2(str1));

        Assert.assertFalse(chapterOne.isPermutationOfPalindrome2(str2));
    }

    @Test
    public void isPermutationOfPalindrome3Test(){

        String str1 = "attc coa";
        String str2 = "atttac";

        Assert.assertTrue(chapterOne.isPermutationOfPalindrome3(str1));

        Assert.assertFalse(chapterOne.isPermutationOfPalindrome3(str2));
    }

    @Test
    public void checkEdit1Test(){

        String str = "pale";
        String str1 = "ppale";
        String str2 = "pal";
        String str3 = "bale";
        String str4 = "bales";

        Assert.assertTrue(chapterOne.checkEdit1(str, str1));    //insert one character;
        Assert.assertTrue(chapterOne.checkEdit1(str, str2));    //remove one character;
        Assert.assertTrue(chapterOne.checkEdit1(str, str3));    //change one character;
        Assert.assertFalse(chapterOne.checkEdit1(str, str4));   //change one character and insert a character. Return false.
    }

    @Test
    public void checkEdit2Test(){

        String str = "pale";
        String str1 = "ppale";
        String str2 = "pal";
        String str3 = "bale";
        String str4 = "bales";

        Assert.assertTrue(chapterOne.checkEdit1(str, str1));    //insert one character;
        Assert.assertTrue(chapterOne.checkEdit1(str, str2));    //remove one character;
        Assert.assertTrue(chapterOne.checkEdit1(str, str3));    //change one character;
        Assert.assertFalse(chapterOne.checkEdit1(str, str4));   //change one character and insert a character. Return false.
    }

    @Test
    public void compressString1Test(){

        String str1 = "aabccccccaaa";
        String str2 = "aabcdeef";

        //Return value should be a2b1c6a3
        Assert.assertEquals("a2b1c6a3", chapterOne.compressString1(str1));

        //Return value should be aabcdeef
        Assert.assertEquals("aabcdeef", chapterOne.compressString1(str2));
    }

    @Test
    public void compressString2Test(){

        String str1 = "aabccccccaaa";
        String str2 = "aabcdeef";

        //Return value should be a2b1c6a3
        Assert.assertEquals("a2b1c6a3", chapterOne.compressString2(str1));

        //Return value should be aabcdeef
        Assert.assertEquals("aabcdeef", chapterOne.compressString2(str2));
    }

    @Test
    public void rotateMatrixTest(){

        int[][] matrix1 = new int[][]{

                { 0, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 0, 4, 4, 4, 0 },
        };

        int[][] matrix2 = new int[][]{

                { 0, 2, 2, 2 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 0, 4, 4, 4, 0 },
        };

        //If the matrix is a square, the function should rotate the matrix by 90 degrees.
        Assert.assertTrue(chapterOne.rotateMatrix(matrix1));

        //If the matrix is not a square, the function should return false.
        Assert.assertFalse(chapterOne.rotateMatrix(matrix2));
    }

    @Test
    public void setZeros1Test(){

        int[][] matrix1 = new int[][]{

                { 0, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 0, 4, 4, 4, 0 },
        };

        int[][] matrixAfter = new int[][]{

                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0 },
        };

        //Assert.assertEquals(matrixAfter, chapterOne.setZeros1(matrix1));
        chapterOne.setZeros1(matrix1);
        for(int i = 0; i < matrix1.length; i++){

            for(int j = 0; j < matrix1.length; j++){

                System.out.print(matrix1[i][j]);
            }
            System.out.print("\n");
        }

    }

    @Test
    public void setZeros2Test(){

        int[][] matrix1 = new int[][]{

                { 0, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 1, 1, 1, 1, 3 },
                { 0, 4, 4, 4, 0 },
        };

        int[][] matrixAfter = new int[][]{

                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0 },
        };

        //Assert.assertEquals(matrixAfter, chapterOne.setZeros1(matrix1));

        chapterOne.setZeros2(matrix1);
        for(int i = 0; i < matrix1.length; i++){

            for(int j = 0; j < matrix1.length; j++){

                System.out.print(matrix1[i][j]);
            }
            System.out.print("\n");
        }

    }

}
