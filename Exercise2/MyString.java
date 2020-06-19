package Exercise2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class MyString
{
    public static void main(String args[])
    {
        File file = new File("/Users/sharjeel/OneDrive/Computer Science/IntelliJ/Java Workspace/UofC CPSC 331 Spring 2020/src/Exercise2/words.txt");
        LinkedList<String> fileData = new LinkedList<String>();

        try
        {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) fileData.add(fileReader.nextLine());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int inputSize = fileData.size();
        int hashTableSize = (int) (fileData.size() / 0.65);
        while (!isPrime(hashTableSize)) hashTableSize++;

        int hashTable1[] = new int[hashTableSize], hashTable2[] = new int[hashTableSize];

        while (fileData.size() > 0)
        {
            int hashCode1 = fileData.getFirst().hashCode() % hashTableSize;
            int hashCode2 = hash(fileData.getFirst()) % hashTableSize;

            if (hashCode1 < 0) hashCode1 += hashTableSize;
            if (hashCode2 < 0) hashCode2 += hashTableSize;

            hashTable1[hashCode1]++;
            hashTable2[hashCode2]++;
            fileData.removeFirst();
        }

        int t1MaxCollisions = 0, t2MaxCollisions = 0;
        double t1AverageCollisions = 0, t2AverageCollisions = 0;
        double t1EmptySlotsPercentage = 0, t2EmptySlotsPercentage = 0;

        for (int counter = 0; counter < hashTableSize; counter++)
        {
            if ((hashTable1[counter] - 1) > t1MaxCollisions) t1MaxCollisions = hashTable1[counter] - 1;
            if ((hashTable2[counter] - 1) > t2MaxCollisions) t2MaxCollisions = hashTable2[counter] - 1;

            if (hashTable1[counter] == 0) t1EmptySlotsPercentage++;
            else if (hashTable1[counter] > 1) t1AverageCollisions += hashTable1[counter] - 1;

            if (hashTable2[counter] == 0) t2EmptySlotsPercentage++;
            else if (hashTable2[counter] > 1) t2AverageCollisions += hashTable2[counter] - 1;
        }
        t1AverageCollisions /= (hashTableSize - t1EmptySlotsPercentage);
        t2AverageCollisions /= (hashTableSize - t2EmptySlotsPercentage);

        t1EmptySlotsPercentage = (t1EmptySlotsPercentage / hashTableSize) * 100;
        t2EmptySlotsPercentage = (t2EmptySlotsPercentage / hashTableSize) * 100;

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Input Size: " + inputSize);
        System.out.println("Hash Table Size: " + hashTableSize);
        System.out.println("Hash Table Size Prime: " + isPrime(hashTableSize));
        System.out.println("Hash Table 1 Empty Slots: " + t1EmptySlotsPercentage);
        System.out.println("Hash Table 2 Empty Slots: " + t2EmptySlotsPercentage);
        System.out.println("Hash Table 1 Maximum Collisions: " + t1MaxCollisions);
        System.out.println("Hash Table 2 Maximum Collisions: " + t2MaxCollisions);
        System.out.println("Hash Table 1 Average Collisions: " + t1AverageCollisions);
        System.out.println("Hash Table 2 Average Collisions: " + t2AverageCollisions);
        System.out.println("------------------------------------");
        System.out.println();
    }

    public static int hash(String key)
    {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++)
            hashVal += key.charAt(i);
        return hashVal;
    }

    static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;

        return true;
    }
}
