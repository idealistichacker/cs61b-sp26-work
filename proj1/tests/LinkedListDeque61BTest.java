import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    /**
     * Flags for toList tests
     * “to_list_empty”: Check that toList works with empty LinkedListDeque61B.
     * “to_list_nonempty”: Check that toList works with non-empty LinkedListDeque61B.
     */
    public void toListTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("to_list_empty: ").that(lld1.toList()).containsExactly();
        lld1.addLast(64);
        assertWithMessage("tto_list_nonempty: ").that(lld1.toList()).containsExactly(64);
    }

    @Test
    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    public void isEmptyTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 is empty: ").that(lld1.isEmpty()).isTrue();
        lld1.addFirst(64);
        assertWithMessage("lld1 is not empty: ")
                .that(lld1.isEmpty())
                .isFalse();
    }

    @Test
    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    public void sizeTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("lld1 has size 0: ")
                .that(lld1.size())
                .isEqualTo(0);

        lld1.addFirst(64);
        assertWithMessage("lld1 has size 1: ")
                .that(lld1.size())
                .isEqualTo(1);

        lld1.addFirst(64);
        assertWithMessage("lld1 has size 2: ")
                .that(lld1.size())
                .isEqualTo(2);

        //Add some elements to a deque and remove them all, then check that size still works.
        lld1.removeFirst();
        lld1.removeFirst();
        assertWithMessage("size_after_remove_to_empty: ")
                .that(lld1.size())
                .isEqualTo(0);

        //Remove from an empty deque, then check that size still works.
        lld1.removeFirst();
        assertWithMessage("size_after_remove_from_empty: ")
                .that(lld1.size())
                .isEqualTo(0);

    }

    @Test
    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     *
     * constant time
     */
    public void getFirstTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("empty LLD61B: ")
                .that(lld1.getFirst())
                .isNull();

        lld1.addFirst(64);
        assertWithMessage("LLD61B: [64]")
                .that(lld1.getFirst())
                .isEqualTo(64);

        lld1.addFirst(32);
        assertWithMessage("LLD61B: [32, 64]")
                .that(lld1.getFirst())
                .isEqualTo(32);

        lld1.addLast(128);
        assertWithMessage("LLD61B: [32, 64, 128]")
                .that(lld1.getFirst())
                .isEqualTo(32);
    }

    @Test
    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     *
     * constant time
     */
    public void getLastTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("empty LLD61B: ")
                .that(lld1.getLast())
                .isNull();

        lld1.addFirst(64);
        assertWithMessage("LLD61B: [64]")
                .that(lld1.getLast())
                .isEqualTo(64);

        lld1.addFirst(32);
        assertWithMessage("LLD61B: [32, 64]")
                .that(lld1.getLast())
                .isEqualTo(64);

        lld1.addLast(128);
        assertWithMessage("LLD61B: [32, 64, 128]")
                .that(lld1.getLast())
                .isEqualTo(128);
    }

    @Test
    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    public void getTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("corner case: .get(0) but LLD61B is: []")
                .that(lld1.get(0))
                .isNull();

        assertWithMessage("corner case: .get(64) but LLD61B is: []")
                .that(lld1.get(64))
                .isNull();

        assertWithMessage("corner case: .get(-64) but LLD61B is: []")
                .that(lld1.get(-64))
                .isNull();

        lld1.addFirst(1);
        assertWithMessage("corner case: .get(64) but LLD61B is: [1]")
                .that(lld1.get(64))
                .isNull();
        assertWithMessage(".get(1) but LLD61B is: [1]")
                .that(lld1.get(0))
                .isEqualTo(1);

        lld1.addFirst(2);
        lld1.addFirst(3);
        assertWithMessage(".get(1) but LLD61B is: [3, 2, 1]")
                .that(lld1.get(1))
                .isEqualTo(2);
        assertWithMessage("LLD61B is: [3, 2, 1]")
                .that(lld1.toList())
                .containsExactly(3,2,1)
                .inOrder();
    }

    @Test
    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    public void getRecursiveTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("corner case: .getRecursive(0) but LLD61B is: []")
                .that(lld1.getRecursive(0))
                .isNull();

        assertWithMessage("corner case: .getRecursive(64) but LLD61B is: []")
                .that(lld1.getRecursive(64))
                .isNull();

        assertWithMessage("corner case: .getRecursive(-64) but LLD61B is: []")
                .that(lld1.getRecursive(-64))
                .isNull();

        lld1.addFirst(1);
        assertWithMessage("corner case: .getRecursive(64) but LLD61B is: [1]")
                .that(lld1.getRecursive(64))
                .isNull();
        assertWithMessage(".getRecursive(1) but LLD61B is: [1]")
                .that(lld1.getRecursive(0))
                .isEqualTo(1);

        lld1.addFirst(2);
        lld1.addFirst(3);
        assertWithMessage(".getRecursive(1) but LLD61B is: [3, 2, 1]")
                .that(lld1.getRecursive(1))
                .isEqualTo(2);
        assertWithMessage("LLD61B is: [3, 2, 1]")
                .that(lld1.toList())
                .containsExactly(3,2,1)
                .inOrder();
    }

    @Test
    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    public void removeFirst(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("LLD61B: []; empty LLD61B .removeFirst() should return null. ")
                .that(lld1.removeFirst())
                .isNull();

        for (int i = 0; i < 7; i++){
            lld1.addLast(i);
        }

        assertWithMessage("LLD61B: [0, 1, 2, 3, 4, 5, 6]; LLD61B .removeFirst() should return 0. ")
                .that(lld1.removeFirst())
                .isEqualTo(0);
        assertThat(lld1.toList()).containsExactly(1, 2, 3, 4, 5, 6).inOrder();

        for (int i = 0; i < 6; i++){
            lld1.removeFirst();
        }
        assertThat(lld1.toList()).containsExactly().inOrder();

        lld1.addFirst(999);
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(999).inOrder();
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Test
    public void removeLast(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("LLD61B: []; empty LLD61B .removeLast() should return null. ")
                .that(lld1.removeLast())
                .isNull();

        for (int i = 0; i < 7; i++){
            lld1.addLast(i);
        }

        assertWithMessage("LLD61B: [0, 1, 2, 3, 4, 5, 6]; LLD61B .removeLast() should return 6. ")
                .that(lld1.removeLast())
                .isEqualTo(6);
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5).inOrder();

        for (int i = 0; i < 6; i++){
            lld1.removeLast();
        }
        assertThat(lld1.toList()).containsExactly().inOrder();
    }


}