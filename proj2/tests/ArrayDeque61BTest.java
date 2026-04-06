import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    public void addFirst_addLast_Test(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);
        adt.addLast(67);
        adt.addLast(68);
        adt.addLast(69);
        assertWithMessage("AD61B [61, 62, 63, 64, 65, 66, 67, 68, 69] ")
                .that(adt.toList())
                .containsExactly(61, 62, 63, 64, 65, 66, 67, 68, 69)
                .inOrder();

        for (int i = 70; i < 270; ++i){
            adt.addLast(i);
        }
//        assertWithMessage("AD61B [61, 62, 63, 64, 65, 66, 67, 68, 69, ..., 269] ")
//                .that(adt.toList())
//                .containsExactly(61, 62, 63, 64, 65, 66, 67, 68, 69)
//                .inOrder();

        for (int i = 60; i > 0 ; --i){
            adt.addFirst(i);
        }

        List<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i < 270; ++i){
            expectedList.add(i);
        }

        assertWithMessage("AD61B [61, 62, 63, 64, 65, 66, 67, 68, 69, ..., 269] ")
                .that(adt.toList())
                .containsExactlyElementsIn(expectedList)
                .inOrder();

    }



    @Test
    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    public void getFirstTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage("Empty AD61B []")
                .that(adt.getFirst())
                .isNull();

        adt.addFirst(64);
        assertWithMessage("AD61B [64] ")
                .that(adt.getFirst())
                .isEqualTo(64);

        adt.addFirst((63));
        assertWithMessage("AD61B [63, 64] ")
                .that(adt.getFirst())
                .isEqualTo(63);
    }

    @Test
    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    public void getLastTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage("Empty AD61B []")
                .that(adt.getLast())
                .isNull();

        adt.addLast(64);
        assertWithMessage("AD61B [64] ")
                .that(adt.getLast())
                .isEqualTo(64);

        adt.addLast((63));
        assertWithMessage("AD61B [63, 64] ")
                .that(adt.getLast())
                .isEqualTo(63);
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
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage("Empty AD61B []")
                .that(adt.get(5))
                .isNull();

        adt.addFirst(64);
        assertWithMessage("index >= size AD61B [64]")
                .that(adt.get(1))
                .isNull();

        assertWithMessage("index in negative AD61B [64]")
                .that(adt.get(-1))
                .isNull();

        adt.addFirst(63);
        adt.addFirst(62);
        assertWithMessage(".get(2) AD61B [62, 63, 64] ")
                .that(adt.get(2))
                .isEqualTo(64);
        adt.addLast(65);
        adt.addLast(66);
        assertWithMessage(".get(2) AD61B [62, 63, 64, 65, 66] ")
                .that(adt.get(2))
                .isEqualTo(64);
        assertWithMessage(".get(4) AD61B [62, 63, 64, 65, 66] ")
                .that(adt.get(4))
                .isEqualTo(66);
        adt.addFirst(61);
        assertWithMessage(".get(5) AD61B [61, 62, 63, 64, 65, 66] ")
                .that(adt.get(5))
                .isEqualTo(66);
    }

    @Test
    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    public void sizeTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage(".size() Empty AD61B []")
                .that(adt.size())
                .isEqualTo(0);

        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);
        assertWithMessage(".size() AD61B [61, 62, 63, 64, 65, 66] ")
                .that(adt.size())
                .isEqualTo(6);

    }

    @Test
    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    public void isEmptyTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage(".isEmpty() Empty AD61B []")
                .that(adt.isEmpty())
                .isTrue();

        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);

        assertWithMessage(".isEmpty() Empty AD61B []")
                .that(adt.isEmpty())
                .isFalse();
    }

    @Test
    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    public void toListTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage("to_list_empty: ").that(adt.toList()).containsExactly();
        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);
        assertWithMessage("tto_list_nonempty: ").that(adt.toList())
                .containsExactly(61, 62, 63, 64, 65, 66)
                .inOrder();
    }

    @Test
    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    public void removeFirstTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage(".removeFirst() Empty AD61B")
                .that(adt.removeFirst())
                .isNull();

        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);
        assertWithMessage(".removeFirst() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.removeFirst())
                .isEqualTo(61);
        assertWithMessage(".removeFirst() then .toList() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.toList())
                .containsExactly(62, 63, 64, 65, 66)
                .inOrder();
        assertWithMessage(".removeFirst() then .size() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.size())
                .isEqualTo(5);

        adt.removeFirst();
        adt.removeFirst();
        adt.removeFirst();
        adt.removeFirst();
        adt.removeFirst();
        assertWithMessage(".removeFirst() then .get(3) AD61B []")
                .that(adt.get(3))
                .isEqualTo(null);

    }
    
    @Test
    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    public void removeLastTest(){
        Deque61B<Integer> adt = new ArrayDeque61B<>();
        assertWithMessage(".removeLast() Empty AD61B")
                .that(adt.removeLast())
                .isNull();

        adt.addFirst(64);
        adt.addFirst(63);
        adt.addFirst(62);
        adt.addLast(65);
        adt.addLast(66);
        adt.addFirst(61);
        assertWithMessage(".removeLast() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.removeLast())
                .isEqualTo(66);
        assertWithMessage(".removeLast() then .toList() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.toList())
                .containsExactly(61, 62, 63, 64, 65)
                .inOrder();
        assertWithMessage(".removeLast() then .size() AD61B [61, 62, 63, 64, 65, 66]")
                .that(adt.size())
                .isEqualTo(5);

        adt.removeLast();
        adt.removeLast();
        adt.removeLast();
        //remove_first_to_one
        assertThat(adt.removeFirst()).isEqualTo(61);
//        adt.removeLast();
        //Add some elements to a deque and remove almost all of them. Check that removing the last element with removeFirst works.
        assertWithMessage("remove_first_to_empty:")
                .that(adt.removeFirst())
                        .isEqualTo(62);
        assertWithMessage(".removeLast() then .get(3) AD61B []")
                .that(adt.get(3))
                .isEqualTo(null);
    }

    @Test
    public void downSizeTest(){
        ArrayDeque61B<Integer> adt = new ArrayDeque61B<>();
        for (int i = 0; i < 128; ++i){
            adt.addLast(i);
        }

        for (int i = 0; i < 96; ++i){
            adt.removeLast();
        }

        assertWithMessage("the length now should be 128")
                .that(adt.length)
                .isEqualTo(128);

        adt.removeLast();

        assertWithMessage("the length now should be 64")
                .that(adt.length)
                .isEqualTo(64);


    }

    @Test
    //先加满、再删光、再重新加
    public void addRemoveAddTest() {
        Deque61B<Integer> adt = new ArrayDeque61B<>();

        // 1. 疯狂加满，构造[127, 126, ..., 64, 0, 1, .., 63]
        for (int i = 0; i < 64; ++i) {
            adt.addLast(i);
        }

        for (int i = 64; i < 128; ++i){
            adt.addFirst(i);
        }


        // 2. 疯狂删光
        for (int i = 0; i < 128; ++i) {
            adt.removeLast();
        }
        assertWithMessage("先加满、再删光; AD61B 应该为空")
                .that(adt.size())
                .isEqualTo(0);

        // 3. 废墟上重建
        for (int i = 0; i < 64; ++i) {
            adt.addLast(i);
        }

        for (int i = 64; i < 128; ++i){
            adt.addFirst(i);
        }

        // ==========================================
        // 🌟 天才少女的极客验证法
        // ==========================================

        // 方案 A：朴素 ArrayList 构造法（最老实，最符合大三 CS 基础）
        List<Integer> expectedList = new ArrayList<>();
        for (int i = 127; i >= 64; --i){
            expectedList.add(i);
        }

        for (int i = 0; i < 64; ++i) {
            expectedList.add(i);
        }

    /* // 方案 B：Java 8 Stream 降维打击法（如果你想在 TA 面前炫技，可以用这一行代替上面的 for 循环）
    // List<Integer> expectedList = java.util.stream.IntStream.range(0, 128).boxed().collect(java.util.stream.Collectors.toList());
    */
        List<Integer> returnList = adt.toList();
        // 见证奇迹的断言：把生成好的标准答案列表传给 containsExactlyElementsIn
        assertWithMessage("先加满、再删光、再重新加; AD61B [127, 126, ..., 64, 0, 1, .., 63]")
                .that(adt.toList())
                .containsExactlyElementsIn(expectedList)
                .inOrder();
    }

}
