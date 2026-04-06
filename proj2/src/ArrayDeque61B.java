import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    public T[] contents;
    public int size;
    public int nextFirst;
    public int nextLast;
    public int length;
    public int upSizeFactor;
    public int downSizeFactor;
    public ArrayDeque61B() {
        contents = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
        length = 8;
        upSizeFactor = 2;
        downSizeFactor = 2;
    }

    //Implement Iterator
    @Override
    public Iterator<T> iterator(){
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T>{
        private int count;

        public ArrayDeque61BIterator(){
            count = 0;
        }

        public boolean hasNext(){
            return count < size;
        }

        public T next(){
            T returnItem = get(count);
            ++count;
            return returnItem;
        }
    }

    //help change nextFirst
    private void nextFirstHelper(){
        nextFirst = (nextFirst - 1 + length) % length;
    }

    private void nextLastHelper(){
        nextLast = (++nextLast) % length;
    }

    private void upSizeHelper(int refactor){
        T[] resizedContents = (T []) new Object[length * refactor];
        for (int i = 0; i < size; ++i){
            resizedContents[i] = get(i);
        }
        nextLast = size;
        length = length * refactor;
        nextFirst = length - 1;
        contents = resizedContents;
    }
    
    private void downSizeHelper(int refactor){
        T[] resizedContents = (T []) new Object[length / refactor];
        for (int i = 0; i < size; ++i){
            resizedContents[i] = get(i);
        }
        nextLast = size;
        length = length / refactor;
        nextFirst = length - 1;
        contents = resizedContents;
    }

    public int getLength(){
        return length;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }

        if (other instanceof ArrayDeque61B<?> otherAD61B){
            if (this.size() != otherAD61B.size()){
                return false;
            }
            for (int i = 0; i < size; ++i){
                if (!this.get(i).equals(otherAD61B.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder returnSB = new StringBuilder("[");
        for (int i = 0; i < size - 1; ++i){
            returnSB.append(get(i));
            returnSB.append(", ");
        }
        returnSB.append(get(size - 1));
        returnSB.append("]");
        return returnSB.toString();
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if(size == length){
            upSizeHelper(upSizeFactor);
        }
        contents[nextFirst] = x;
        nextFirstHelper();
        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if(size == length){
            upSizeHelper(upSizeFactor);
        }
        contents[nextLast] = x;
        nextLastHelper();
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; ++i){
            returnList.add(get(i));
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
        return size == 0;
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
        return contents[(nextFirst+1)%length];
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return contents[(nextLast - 1 + length)%length];
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }

        T beRemovedFirstValue = get(0);
        contents[0] = null;
        nextFirst = (nextFirst + 1) % length;
        --size;

        if (length >= 16 && size * 4 < length){
            downSizeHelper(downSizeFactor);
        }

        return beRemovedFirstValue;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }

        T beRemovedLastValue = get(size-1);
        contents[size-1] = null;
        nextLast = (nextLast - 1 + length) % length;
        --size;

        if (length >= 16 && size * 4 < length){
            downSizeHelper(downSizeFactor);
        }

        return beRemovedLastValue;
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
        if (index < 0 || index >= size){
            return null;
        }
        return contents[(nextFirst+index+1)%length];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

}
