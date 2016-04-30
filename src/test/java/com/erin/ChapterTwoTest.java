package com.erin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.erin.ChapterTwo.*;
/**
 * Created by Erin on 4/28/16.
 */
public class ChapterTwoTest {

    private ChapterTwo chapterTwo;

    public Node generateSinglyLinkedList(){

        Node n1 = new Node(3);
        Node n2 = new Node(4);
        Node n3 = new Node(8);
        Node n4 = new Node(9);
        Node n5 = new Node(2);
        Node n6 = new Node(1);
        Node n7 = new Node(5);
        Node n8 = new Node(8);
        Node n9 = new Node(10);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;

        return n1;
    }

    public Node generateLL(char ch1, char ch2, char ch3, char ch4, char ch5, char ch6){

        Node n1 = new Node(ch1);
        Node n2 = new Node(ch2);
        Node n3 = new Node(ch3);
        Node n4 = new Node(ch4);
        Node n5 = new Node(ch5);
        Node n6 = new Node(ch6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        return n1;
    }

    @Before
    public void setUp() {
        chapterTwo = new ChapterTwo();
    }

    @Test
    public void deleteDups1Test() {

        Node head = generateSinglyLinkedList();

        chapterTwo.deleteDups1(head);

        StringBuilder sb = new StringBuilder();

        Node n = head;
        while (n != null){

            sb.append(n.data);
            n = n.next;
        }

        Assert.assertEquals(sb.toString(), "12356");

    }

    @Test
    public void deleteDups2Test() {


        Node head = generateSinglyLinkedList();
        chapterTwo.deleteDups2(head);

        StringBuilder sb = new StringBuilder();

        Node n = head;
        while (n != null){

            sb.append(n.data);
            n = n.next;
        }

        Assert.assertEquals(sb.toString(), "12356");

    }

    @Test
    public void printKthToLast1Test(){

        Node head = generateSinglyLinkedList();
        chapterTwo.printKthToLast1(head, 3);
    }

    @Test
    public void KthToLast2Test(){

        Node head = generateSinglyLinkedList();
        Node expectedNode = new Node(3);
        Assert.assertEquals(chapterTwo.KthToLast2(head, 3).data, expectedNode.data);
    }

    @Test
    public void KthToLast3Test(){

        Node head = generateSinglyLinkedList();
        Node expectedNode = new Node(3);
        Assert.assertEquals(chapterTwo.KthToLast3(head, 3).data, expectedNode.data);
    }

    @Test
    public void partition1Test(){

        Node head = generateSinglyLinkedList();


        Node node = chapterTwo.partition1(head, 3);
        while(node != null){

            System.out.print("*****"+ node.data + "*****\n");
            node = node.next;
        }
    }

    @Test
    public void partition2Test(){

        Node head = generateSinglyLinkedList();

        Node node = chapterTwo.partition2(head, 3);
        while(node != null){

            System.out.print("*****"+ node.data + "*****\n");
            node = node.next;
        }
    }


    @Test
    public void isPalindromeTest(){

        Assert.assertTrue(chapterTwo.isPalindrome(generateLL('a', 'b', 'c', 'c', 'b', 'a')));
    }




}
