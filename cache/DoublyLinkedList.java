package archive.Dec2025.leetcode.hard.cache;

import lombok.Data;

@Data
public class DoublyLinkedList {

    private LFUNode head;
    private LFUNode tail;
    private int size;

    DoublyLinkedList(){
        head = new LFUNode(0,0);
        tail = new LFUNode(0,0);
        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    public void addToFront(LFUNode node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

        size++;
    }

    public void remove(LFUNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
    }

    public LFUNode removeLast(){
        LFUNode lastNode = tail.prev;
        remove(lastNode);
        return lastNode;
    }

    public void print(){
        LFUNode temp = head.next;
        while(temp!=tail){
            System.out.print("{"+temp.getK()+","+ temp.getV()+","+ temp.getF()+"}");
            temp = temp.next;
        }
    }


}
