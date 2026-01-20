package archive.Dec2025.leetcode.hard.cache;

import java.util.HashMap;
import java.util.Map;

public class LFU {

    private int CAPACITY;
    private Map<Integer, LFUNode> nodeMap;
    private Map<Integer,DoublyLinkedList> freqMap;
    private int minFreq;

    public static void main(String[] args) {

        LFU cache = new LFU(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(2,20);
        cache.put(2,200);
        cache.put(1,1);
        cache.put(4,40);
        System.out.println(cache.get(1));
        cache.put(0,0);
        cache.put(3,3);
        cache.get(3);
        cache.get(3);
        cache.print();

    }

    LFU(int n){
        this.CAPACITY = n;
        nodeMap = new HashMap<>(CAPACITY);
        freqMap = new HashMap<>();
        minFreq = 0;
    }

    public void put(int k, int v){
        if(nodeMap.containsKey(k)){

            LFUNode node = nodeMap.get(k);
            node.setV(v);
            updateFrequency(node);

        }else{
            if(nodeMap.size() == CAPACITY){
                DoublyLinkedList minList  = freqMap.get(minFreq);
                LFUNode lru = minList.removeLast();
                nodeMap.remove(lru.getK());
            }
            LFUNode node = new LFUNode(k,v);
            nodeMap.put(k,node);

            freqMap.computeIfAbsent(1,e->new DoublyLinkedList()).addToFront(node);
            minFreq = 1;
        }
    }

    public int get(int k){
        if(nodeMap.containsKey(k)){
            LFUNode node = nodeMap.get(k);
            int v = node.getV();
            updateFrequency(node);
            return v;
        }
        return -1;
    }

    public void updateFrequency(LFUNode node){

        int freq = node.getF();

        DoublyLinkedList freqList = freqMap.get(freq);
        freqList.remove(node);

        if(minFreq == freq && freqList.getSize() == 0){
            minFreq++;
        }

        node.setF(freq+1);
        freqMap.computeIfAbsent(freq+1,e->new DoublyLinkedList()).addToFront(node);
    }

    public void print(){

        freqMap.entrySet()
                .forEach(e-> {
                    System.out.print("["+e.getKey()+" -> ");
                    e.getValue().print();
                    System.out.println("]");
                });
    }
}
