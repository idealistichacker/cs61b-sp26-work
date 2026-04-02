public class IntList {
    int first;
    IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrRecursiveDestructive(IntList L, int x) {
        if (L == null) {
            return null;
        }
        L.first += x;
        // 终极奥义：让当前的 rest，重新指向后面处理完的链表！
        L.rest = incrRecursiveDestructive(L.rest, x);
        return L;
    }

    /*
     * =================================================================
     * OPTIONAL METHODS
     * =================================================================
     */

    /**
     * Returns the sum of all elements in the IntList.
     */
    public int sum() {
        // Optional: Fill in this code
        int sum = 0;
        IntList begin = this;
        while (begin != null){
            sum += begin.first;
            begin = begin.rest;
        }
        return sum;
    }

    /**
     * Destructively adds x to the end of the list.
     */
    public void addLast(int x) {
        // Optional: Fill in this code
        IntList begin = this;
        while (begin.rest != null){
            begin = begin.rest;
        }
        begin.rest = new IntList(x, null);
    }

    /**
     * Destructively adds x to the front of this IntList.
     * This is a bit tricky to implement. The standard way to do this would be
     * to return a new IntList, but for practice, this implementation should
     * be destructive.
     */
    public void addFirst(int x) {
        // 第一步：在后面造个新房子，把现在的 first 存进去，并接上原本的 rest
        this.rest = new IntList(this.first, this.rest);

        // 第二步：鸠占鹊巢！把当前的 first 改成新来的 x
        this.first = x;
    }
}
