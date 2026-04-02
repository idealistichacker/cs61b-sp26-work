import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

// 开启自定义执行顺序的魔法
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntListOptionalTests {

    // 【辅助魔法】快速造一列火车 (从 RequiredTests 里学来的)
    public static IntList of(int... nums) {
        if (nums.length == 0) return null;
        IntList L = new IntList(nums[0], null);
        IntList current = L;
        for (int i = 1; i < nums.length; i++) {
            current.rest = new IntList(nums[i], null);
            current = current.rest;
        }
        return L;
    }

    // 【辅助魔法】严谨比对两条链表是否长得一模一样
    public static boolean checkEquals(IntList L1, IntList L2) {
        if (L1 == null && L2 == null) return true;
        if (L1 == null || L2 == null) return false;
        if (L1.first != L2.first) return false;
        return checkEquals(L1.rest, L2.rest);
    }

    /* * =================================================================
     * 下面是你的 Optional 方法的专属测试主场！
     * =================================================================
     */

    @Test
    @Order(1)
    @DisplayName("Test sum() - Should return the sum of all elements")
    public void testSum() {
        // Arrange (摆阵型)
        IntList L = of(1, 2, 3, 4);

        // Act & Assert (因为返回的是个整数，直接用优雅的 Truth 断言！)
        assertThat(L.sum()).isEqualTo(10);

        // 测一个极端的边界情况：只有一节车厢
        IntList singleNode = of(42);
        assertThat(singleNode.sum()).isEqualTo(42);
    }

    @Test
    @Order(2)
    @DisplayName("Test addLast() - Should append element to the end destructively")
    public void testAddLast() {
        // Arrange
        IntList L = of(1, 2);

        // Act: 搞破坏，强行在后面加个 3
        L.addLast(3);

        // Assert: 用我们的 checkEquals 来核对全车厢
        IntList expected = of(1, 2, 3);
        if (!checkEquals(L, expected)) {
            // 如果不对，像官方一样抛出极其专业的报错信息
            fail("addLast failed! Expected list to look like (1, 2, 3) but it got corrupted.");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Test addFirst() - Should prepend element using the tricky clone method")
    public void testAddFirst() {
        // Arrange
        IntList L = of(2, 3);

        // Act: 施展你的“鸠占鹊巢”魔法
        L.addFirst(1);

        // Assert
        IntList expected = of(1, 2, 3);
        if (!checkEquals(L, expected)) {
            fail("addFirst failed! The tricky implementation didn't produce the expected (1, 2, 3) structure.");
        }
    }
}