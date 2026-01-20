package archive.Dec2025.leetcode.hard.heap;

public class MinHeap {

    private int capacity;
    private int heapSize;

    private int[] minHeap;

    MinHeap(int n){

        capacity = n;
        heapSize = 0;
        minHeap = new int[n];
    }

    public int getParent(int i){
        return (i-1)/2;
    }

    public int getLeft(int i){
        return 2*i+1;
    }

    public int getRight(int i){
        return 2*i+2;
    }

    public void swap(int i,int j){
        int temp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = temp;
    }

    public void getMin(){
        if(heapSize <=0){
            System.out.println("Heap is empty!!!!");
            return;
        }
        System.out.println("Minimum is - "+minHeap[0]);
    }

    public void extractMin(){

        if(heapSize <=0){
            System.out.println("Heap is empty");
            return;
        }

        if(heapSize == 1){
            System.out.println("Minimum removed is - "+minHeap[0]);
            heapSize--;
            return;
        }

        int min = minHeap[0];
        minHeap[0] = minHeap[heapSize-1];
        heapSize--;
        minHeapify(0);
        System.out.println("Minimum removed is - "+min);

    }

    public void insert(int key){

        if(heapSize >= capacity){
            System.out.println("Heap is full!!!!");
            return;
        }

        int i = heapSize;
        minHeap[i] = key;
        heapSize++;

        while(i!=0 && minHeap[i] < minHeap[getParent(i)]){
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    public void delete(int i){
        if(i>=heapSize){
            System.out.println("Invalid index provided");
            return;
        }

        int key  = minHeap[i];
        minHeap[i] = minHeap[heapSize-1];
        heapSize--;
        minHeapify(i);
        System.out.println("Removed Element - "+key);
    }

    public void minHeapify(int root){

        int l = getLeft(root);
        int r = getRight(root);
        int smallest  = root;

        if( l < heapSize && (minHeap[l] < minHeap[root])){
            smallest = l;
        }

        if(r < heapSize && (minHeap[r] < minHeap[smallest])){
            smallest = r;
        }

        if(smallest!=root){
            swap(smallest,root);
            minHeapify(smallest);
        }
    }

    public void decreaseKey(int i, int key){

        if(i>=heapSize){
            System.out.println("Invalid Index");
            return;
        }

        //Assumption is key should be less than already provided value
        if(key>minHeap[i]){
            System.out.println("Invalid Key");
            return;
        }

        if(key ==  minHeap[i]){
            return;
        }

        minHeap[i] = key;

        while(i!=0 &&(minHeap[i] < minHeap[getParent(i)])){
            swap(i,getParent(i));
            i = getParent(i);
        }

    }

    public void increaseKey(int i, int key){

        if(i>=heapSize){
            System.out.println("Invalid Index");
            return;
        }

        //Assumption is key should be greater than already provided value
        if(key<minHeap[i]){
            System.out.println("Invalid Key");
            return;
        }

        if(key ==  minHeap[i]){
            return;
        }

        minHeap[i] = key;

        minHeapify(i);

    }

    public void changeKey(int i, int key){
        if(i>=heapSize){
            System.out.println("Invalid Index");
            return;
        }

        if(key == minHeap[i]){
            return;
        }

        if(key < minHeap[i]){
            decreaseKey(i, key);
        }else{
            increaseKey(i,key);
        }
    }

    public void print(){

        for(int i=0;i<heapSize;i++){
            System.out.print(minHeap[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {

        MinHeap heap = new MinHeap(8);

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        heap.insert(50);
        heap.insert(60);
        heap.print();
        heap.increaseKey(2,100);
        heap.print();
    }
}
