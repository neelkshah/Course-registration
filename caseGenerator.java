import java.util.*;

public class caseGenerator {
    static void generateAllBinaryStringsHelper(int n, int arr[], int i){
        arr[i] = 0;
        generateAllBinaryStringsHelper(n, arr, i + 1);
        arr[i] = 1;
        generateAllBinaryStringsHelper(n, arr, i + 1);
    }

    // Driver Code
    public int[] generateAllBinaryStrings(int bits)
    {
        int n = bits;
        int[] arr = new int[n];
        generateAllBinaryStringsHelper(n, arr, 0);
        return arr;
    }
}