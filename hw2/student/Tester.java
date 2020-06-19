package hw2.student;

import hw2.student.SLLBubbleSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Tester
{
    public static void main(String args[])
    {
        SLLBubbleSort sortingBackend = new SLLBubbleSort();

        SLL linkedList = new SLL();
        linkedList.addToHead(34);
        linkedList.addToTail(8);
        linkedList.addToTail(64);
        linkedList.addToTail(51);
        linkedList.addToTail(32);
        linkedList.addToTail(21);
        linkedList.printAll();
        System.out.println();
        sortingBackend.BubbleSort(linkedList);
        linkedList.printAll();
        System.out.println();
    }
}
