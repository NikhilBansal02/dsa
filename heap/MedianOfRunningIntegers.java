package archive.Dec2025.leetcode.hard.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfRunningIntegers {

    public static PriorityQueue<Integer> q1 = new PriorityQueue<>(Comparator.reverseOrder());
    public static PriorityQueue<Integer> q2 = new PriorityQueue<>();

    public static void main(String[] args) {

        median(0);
        median(6);
        median(5);
        median(4);
        median(3);
        median(9);

    }

    public static void median(int num){

        if(q1.isEmpty() || q1.peek()>num){
            q1.add(num);
        }else{
            q2.add(num);
        }

        if(q1.size() > (q2.size()+1)){
            q2.add(q1.poll());
        }else if(q2.size() > (q1.size()+1)){
            q1.add(q2.poll());
        }

        if(q1.size() == 0 && q2.size() ==0 ){
            System.out.println("No items for median");
            return;
        }

        if(q1.size() == q2.size()){
            System.out.println((double) (q1.peek()+q2.peek())/2);
        }else if(q1.size() > q2.size()){
            System.out.println(q1.peek());
        }else{
            System.out.println(q2.peek());
        }
    }
}
