package hw2.student;

public class SLLBubbleSort implements BubbleSortInterface {

  /**
   * A method that will sort a singly linked list that holds Comparable
   * elements. Bubble sort is used to sort the elements. In this version of
   * bubble sort, the largest element will bubble to the right in each pass.
   * Note that the linked list is sorted in place, i.e. the specified list is
   * modified; a copy is not made.
   *
   * @param list The SLL list to sort
   */

  public <T extends Comparable<? super T>> void BubbleSort( SLL<T> list )
  {
    // Outer loop that runs n-1 iterations where n = length of the passed in list
    for (int outerCounter = list.getLength()-1; outerCounter > 0; outerCounter--)
    {
      // Stores the previous, current and next node currently being evaluated
      SLLNode<T> previousNode = null, currentNode = list.head, nextNode = list.head.next;

      // Inner loop that runs outerCounter iterations to sort all but the right most nodes that have already been sorted on previous outer loop iterations
      for (int innerCounter = 0; innerCounter < outerCounter; innerCounter++)
      {
        // Checks to see if the current node (left node) is greater than the next node (right node) and swaps them if true
        if (currentNode.info.compareTo(nextNode.info) > 0)
        {
          // Creates a temporary node that will store the current node (original left node)
          SLLNode<T> tempNode;

          // Checks to see if the nodes to swap are the first two nodes in the list (special case)
          if (list.head.equals(currentNode) && list.head.next.equals(nextNode))
          {
            // Stores the current node (original left node) in a temporary node
            tempNode = list.head;

            // Pops the original left node
            list.head = list.head.next;

            // Updates the popped original left node to now be on the right side of the next node (original right node)
            tempNode.next = list.head.next;

            // Inserts the popped original left node as the new right node of the new left node (original right node)
            list.head.next = tempNode;
          }
          // Ran if the nodes to swap are not the first two nodes in the list (special case)
          else
          {
            // Stores the current node (original left node) in a temporary node
            tempNode = previousNode.next;

            // Pops the original left node
            previousNode.next = currentNode.next;

            // Updates the popped original left node to now be on the right side of the next node (original right node)
            tempNode.next = nextNode.next;

            // Inserts the popped original left node as the new right node of the new left node (original right node)
            nextNode.next = tempNode;
          }
          // Updates the current node's previous and next node references while keeping the same current node (original left node) due to it shifting to the right (became the new right node)
          previousNode = nextNode;
          nextNode = currentNode.next;
        }
        // Code ran if the current node (left node) does not need to be swapped with the next node (right node)
        else
        {
          // Updates the previous, current and next node that will be evaluated in the next iteration
          previousNode = currentNode;
          currentNode = currentNode.next;
          nextNode = currentNode.next;
        }
      }
    }
    return;
  }
}
