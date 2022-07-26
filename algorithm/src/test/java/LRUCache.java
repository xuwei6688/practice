import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2021/1/5
 * @Version V1.0
 **/
public class LRUCache {
    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        Node(){}

        Node(int key, int value){
            this.key  = key;
            this.value = value;
        }

        Node(int key, int value, Node pre, Node next){
            this.key = key;
            this.value =value;
            this.pre = pre;
            this.next = next;
        }
    }

    private Map<Integer, Node> cache;
    private int size;
    private int capacity;
    private Node head;
    private Node tail;



    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;

        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.pre = head;
    }

    private void addHead(Node node){
        head.next.pre = node;
        node.next = head.next;

        head.next = node;
        node.pre = head;

        size++;
    }

    private void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;

        size--;
    }

    private void moveToHead(Node node){
        removeNode(node);
        addHead(node);
    }

    public int get(int key) {
        Node cachedNode = cache.get(key);
        if(cachedNode == null){
            return -1;
        }

        moveToHead(cachedNode);

        return cachedNode.value;
    }

    public void put(int key, int value) {
        Node cachedNode = cache.get(key);
        if(cachedNode == null){
            Node node = new Node(key, value);

            if(size == capacity){
                removeNode(tail.pre);
            }

            addHead(node);
            cache.put(key, node);
        }else{
            cachedNode.value = value;
            moveToHead(cachedNode);
        }
    }

    public static void main(String[] args) {
        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}
