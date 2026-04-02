import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;
//    private Node lastNode;

    // 升级 1：私有化内部类，防止外部偷窥！
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        // 升级 2：给 Node 加一个极度方便的构造函数
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque61B() {
        // 见证奇迹的时刻：由于是环形哨兵，一开始它的 prev 和 next 都是它自己！
        // 因为在实例化 sentinel 时它自己还没完全建好，所以我们先给 prev 和 next 传 null，下一行立刻把环接上！
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
//        lastNode = sentinel;
    }

    // ... 下面的代码保持不变 ...


    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
    }


    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node newFirstNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = newFirstNode;
        sentinel.next = newFirstNode;
        size += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node newLastNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newLastNode;
        sentinel.prev = newLastNode;
        size += 1;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node firstNode = sentinel.next;
        while (firstNode != sentinel){
            returnList.add(firstNode.item);
            firstNode = firstNode.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        // true,if empty
        return sentinel.next == sentinel;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        return sentinel.next.item;
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return sentinel.prev.item;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        Node beRemoved = sentinel.next;
        beRemoved.next.prev = sentinel;
        sentinel.next = beRemoved.next;
        size -= 1;
        return beRemoved.item;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        Node beRemoved = sentinel.prev;
        beRemoved.prev.next = sentinel;
        sentinel.prev = beRemoved.prev;
        size -= 1;
        return beRemoved.item;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        int count = 0;
        Node firstNode = sentinel.next;
        // if LLD61B is empty then return null directly
//        if (firstNode == sentinel){
//            return null;
//        } else
        if ((index <= size-1) && (0 <= index)) {
            while (count != index){
                firstNode = firstNode.next;
                count += 1;
            }
            return firstNode.item;
        } else {
            return null;
        }
    }


    private T getRecursiveHelper(Node current, int index){
        if (index == 0){
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index-1);
        }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * 1. 偷偷摸了哨兵的口袋 (逻辑隐患)
     * 试想一下，如果当前链表是空的 (size == 0)，此时用户调用了 getRecursive(0)，会发生什么？
     *
     * 进 getRecursive(0)，调用 getRecursiveHelper(sentinel.next, 0)。注意，空链表的 sentinel.next 就是 sentinel 自己。
     *
     * 进 helper 方法，第一行遇到 if (index == 0)，条件成立！
     *
     * 执行 return current.item;。此时 current 是哨兵，返回了 sentinel.item。
     *
     * 为什么说这是个隐患？
     * 因为你极其幸运！你在构造函数里恰好写了 sentinel.item = null;，所以这里恰好返回了 null。如果在其他的开发场景下，哨兵的 item 里装了些奇怪的默认值，这个 bug 就会当场爆炸💥！这也是为什么我们不建议在还没确认数据合法性的时候，就直接用 index == 0 去取数据。
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        // 1. 终极防御：越界或空链表，直接拦在门外！
        if (index < 0 || index > size-1){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

/**
 *优点 (Pros)：极其纯粹的递归！不需要任何辅助方法，不需要任何外部状态。
 * 逻辑极其硬核，写出来绝对能惊艳到批改作业的 TA，证明你对 addFirst 和 removeFirst 的信任度达到了 100%。
 * 致命缺点 (Cons)：性能损耗：虽然每次 removeFirst 和 addFirst 都是 $O(1)$，但这种频繁的内存解绑、重新绑定，底层的开销比仅仅移动指针（Helper Method 做法）要大得多。并发灾难（线程不安全）：这个方法在执行的过程中，链表是被物理破坏过的！如果这时候有另一个程序（线程）恰好来查链表的长度，或者想拿第一个元素，它会当场崩溃，因为它拿到的是一个“正在做手术”、处于残缺状态的链表！
 */
//    @Override
//    public T getRecursive(int index) {
//        // 1. 基础边界防御
//        if (index < 0 || index >= size) {
//            return null;
//        }
//
//        // 2. Base Case (递归出口)：如果要找的就是当前的第 0 个，直接返回！
//        if (index == 0) {
//            return getFirst();
//        }
//
//        // 3. 见证奇迹的时刻：把车头“借”出来，此时链表长度 size 会自动减 1
//        T firstItem = removeFirst();
//
//        // 4. 递归降维打击：在变短的链表里，找第 index - 1 个元素
//        T result = getRecursive(index - 1);
//
//        // 5. 完璧归赵：把刚才借出来的车头，重新装回第一节！
//        addFirst(firstItem);
//
//        // 6. 深藏功与名，把结果往上层传递
//        return result;
//    }
}

