package archive.Dec2025.leetcode.hard.cache;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    private Map<Integer,Node> map;
    private Node head;
    private Node tail;

    private int capacity;

    public static void main(String[] args) {

        LRU cache = new LRU(3);

        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.print();
        cache.put(4,4);
        cache.print();
        cache.put(2,20);
        cache.print();
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
        cache.print();

    }

    LRU(int n){
        capacity = n;
        map = new HashMap<>(capacity);
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public void put(int k, int v){
        if(map.containsKey(k)){
            Node node = map.get(k);
            node.v = v;
            remove(node);
            addToFront(node);
        }else{
            if(map.size() == capacity){
                Node lru = tail.prev;
                map.remove(lru.k);
                remove(lru);
            }
            Node node = new Node(k,v);
            map.put(k,node);
            addToFront(node);
        }
    }

    public int get(int k){
        if(map.containsKey(k)){
            Node node = map.get(k);
            remove(node);
            addToFront(node);
            return node.v;
        }
        return -1;
    }

    public void addToFront(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void print(){
//        map.entrySet()
//                .stream()
//                .map(e-> e.getValue())
//                .forEach(e-> System.out.print("["+e.k+","+e.v+"]"));

        Node temp = head.next;
        while(temp!=tail){
            System.out.print("["+temp.k+","+temp.v+"]");
            temp = temp.next;
        }
        System.out.println();
    }


}
