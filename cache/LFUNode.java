package archive.Dec2025.leetcode.hard.cache;

import lombok.Data;

@Data
public class LFUNode {

    private int k;
    private int v;
    private int f;

    LFUNode next;
    LFUNode prev;

    LFUNode(int k, int v){
        this.k = k;
        this.v = v;
        this.f = 1;
    }
}
