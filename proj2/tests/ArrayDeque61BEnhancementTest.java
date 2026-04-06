import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BEnhancementTest {
    @Test
    /**
     * Test ArrayDeque61B is iterable(can be iterated over) and foreach can be applied
     */
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> ad = new ArrayDeque61B<>();

        ad.addLast("front"); // after this call we expect: ["front"]
        ad.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad).containsExactly("front", "middle", "back");

        for (String s : ad) {
            System.out.println(s);
        }

        Deque61B<Integer> adt = new ArrayDeque61B<>();
        for (int i = 0; i < 64; ++i) {
            adt.addLast(i);
        }

        for (int i = 64; i < 128; ++i){
            adt.addFirst(i);
        }

        List<Integer> expectedList = new ArrayList<>();
        for (int i = 127; i >= 64; --i){
            expectedList.add(i);
        }

        for (int i = 0; i < 64; ++i) {
            expectedList.add(i);
        }

        assertWithMessage("AD61B [127, 126, ..., 64, 0, 1, .., 63]")
                .that(adt)
                .containsExactlyElementsIn(expectedList)
                .inOrder();
    }

    @Test
    public void testEqualArrayDeque61B() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad).isEqualTo(ad2);
    }

    @Test
    public void testPrintout(){
        Deque61B<String> ad = new ArrayDeque61B<>();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        System.out.println(ad);

    }


}
