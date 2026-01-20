package archive.Dec2025.leetcode.hard.heap;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {

        int[] a = {5,4,3,2,1};

        heapSort(a);
        System.out.println("Sorted Array-"+ Arrays.toString(a));
    }

    public static void heapSort(int[] a){

        int n = a.length;

        //Perform max-heapify on internal nodes.
        for(int i=(n-1)/2;i>=0;i--){
            maxHeapify(a,n,i);
        }

        for(int i=n-1;i>=0;i--){
            swap(a,0,i);
            maxHeapify(a,i,0);
        }
    }


    public static void maxHeapify(int[] a, int n,int i){

        int l = 2*i+1;
        int r = 2*i+2;

        int largest = i;

        if(l<n &&(a[l]>a[i])){
            largest = l;
        }

        if(r<n && (a[r]>a[largest])){
            largest = r;
        }

        if(largest!=i){
            swap(a,i,largest);
            maxHeapify(a,n,largest);
        }
    }

    public static void swap(int[] a, int i, int j){
        int temp =  a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
