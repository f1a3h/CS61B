package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(1, arb.peek());
        arb.enqueue(3);
        assertEquals(10, arb.capacity());
        assertEquals(3, arb.fillCount());
        assertFalse(arb.isFull());
        assertFalse(arb.isEmpty());
        for (int i = 4; i <= 10; ++i) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
        for (int i = 1; i <= 10; ++i) {
            assertEquals(i, arb.dequeue());
        }
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
