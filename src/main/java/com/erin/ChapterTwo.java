package com.erin;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Erin on 4/28/16.
 */
public class ChapterTwo {

    public static class  Node{

        Node next = null;
        int data;

        public Node(){}
        public Node(int d){

            data = d;
        }

        void appendToTail(int d){

            Node end = new Node();
            end.data = d;
            Node n = this;
            while(n.next != null){

                n = n.next;
            }
            n.next = end;
        }

        void setNext(Node nextNode){

            next = nextNode;
        }
    }

    Node deleteNode(Node head, int d){

        Node n = head;
        if(n.data == d){

            return head.next;
        }

        while (n.next != null){

            if(n.next.data == d){

                n.next = n.next.next;
                return head;
            }
            n = n.next;
        }

        return head;
    }

    /* 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list*/

    //solution1   O(n)
    public void deleteDups1(Node n){

        HashSet<Integer> set = new HashSet<Integer>();
        Node previous = null;

        while(n != null){

            if(set.contains(n.data)){

                previous.next = n.next;
            }else{

                set.add(n.data);
                previous = n;
            }

            n = n.next;
        }
    }

    //solution2  O(n2)
    public void deleteDups2(Node head){

        Node current = head;
        while (current != null){

            Node runner = current;
            while (runner.next != null){

                if(current.data == runner.next.data){

                    runner.next = runner.next.next;
                }else{

                    runner = runner.next;
                }
            }

            current = current.next;
        }
    }

    /* 2.2 Return Kth to last: Implement an algorithm to find the kth to last element of a singly linked list*/

    //solution1 -- just index not the specific node
    public int printKthToLast1(Node head, int k){

        if(head == null){

            return 0;
        }
        int index = printKthToLast1(head.next, k) + 1;
            if(index == k){

            System.out.print(k + "th to last element of the single linked list is " + head.data);
        }
        return index;
    }

    //solution2
    class Index{

        public int value = 0;
    }

    public Node KthToLast2(Node head, int k){

        Index idx = new Index();
        return KthToLast2(head, k, idx);

    }


    public Node KthToLast2(Node head, int k, Index idx){

        if(head == null){
            return null;
        }

        Node node = KthToLast2(head.next, k, idx);
        idx.value = idx.value + 1;
        if(idx.value == k){

            return head;
        }

        return node;
    }

    //solution3
    public Node KthToLast3(Node head, int k){

        Node p1 = head;
        Node p2 = head;

        for(int i = 0; i < k; i++){

            if(p1 == null)  return null;
            p1 = p1.next;
        }

        while(p1 != null){

            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

     /* 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle. (i.e. any node but the first and last
     node, not necessarily the exact middle) of a singly linked list, ****given only access to that node***.

     EXAMPLE:
     Input: the node c from the linked list a -> b -> c -> d -> e ->f
     Result: nothing is returned, but the new linked list looks like a -> b -> d -> e ->f
     */

     boolean deleteNode(Node n){

         if(n == null || n.next == null){

             return false;
         }

         Node next = n.next;
         n.data = next.data;
         n.next = next.next;
         return true;

     }

     /*2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
     before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be
     after the elements less than x(see below). The partition element x can appear anywhere in the "right partition"; it
     does not need to appear between the left and right partitions

     EXAMPLE
     Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1[partition = 5]

     Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
     */
     //solution1
    public Node partition1(Node node, int x){

        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        while(node != null){

            if(node.data < x){

                if(beforeStart == null){

                    beforeStart = node;
                    beforeEnd = beforeStart;
                }else {

                    beforeEnd.next = node;  //link the node
                    beforeEnd = node; //set the node as the end of linked list
                }
            }else{

                if(afterStart == null){

                    afterStart = node;
                    afterEnd = afterStart;
                }else{

                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = node.next;
        }

        if(beforeStart == null){


            return afterStart;
        }

        beforeEnd.next = afterStart;
        return beforeStart;

    }

    //solution2
    public Node partition2(Node node, int x){

        Node head = node;
        Node end = node;


        while(node != null){

            Node next = node.next;

            if(node.data < 1){

                node.next = head;
                head = node;
            }else{

                end.next = node;
                end = node;
            }
            node = next;
        }
        end.next = null;

        return head;
    }

    /*Sum List: You have two numbers represented by a linked list, where each node contains a single digit. The digits
    are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two
    numbers and returns the sum as a linked list
    EXAMPLE
    Input: (7 -> 1 -> 6) + (5 -> 9 ->2) That is, 617 + 295
    Output: 2 -> 1 -> 9
    FOLLOW UP
    Suppose the digits are stored in forward order. Repeat the above problem.
    Input: (6 -> 1 -> 7) + (2 -> 9 ->5) That is, 617 + 295
    Output: 9 -> 1 -> 2
    */

    public Node addLists(Node l1, Node l2, int carry){

        if(l1 == null && l2 == null && carry == 0){

            return null;
        }

        Node result = new Node();
        int value = carry;

        if(l1 != null){

            value += l1.data;
        }

        if(l2 != null){

            value += l2.data;
        }

        result.data = value % 10;

        if(l1 != null || l2 != null){

            Node more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value > 10? 1 : 0);
            result.setNext(more);
        }

        return result;
    }

    /*2.6 Palindrome: Implement a function to check if a linked list is a palindrome*/

    //solution1
    public boolean isPalindrome1(Node head){

        Node reversed = reverseAndClone(head);
        return isEqual(head, reversed);

    }

    Node reverseAndClone(Node node){

        Node head = null;
        while (node != null){

            Node n = new Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    boolean isEqual(Node original, Node reversed){

        while(original != null && reversed != null){

            if(original.data != reversed.data){
                return false;
            }
            original = original.next;
            reversed = reversed.next;
        }

        return original == null && reversed == null;
    }



}
