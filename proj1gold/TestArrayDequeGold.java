import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDequeGold() {
        StudentArrayDeque stu = new StudentArrayDeque<Integer>();
        ArrayDequeSolution sol = new ArrayDequeSolution<Integer>();
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < 1024; ++i) {
            int rand = StdRandom.uniform(4);
            if (i % 5 == 0) {
                msg.append("size()\n");
                assertEquals(msg.toString(), sol.size(), stu.size());
            }
            if (rand == 0) {
                // addFirst
                stu.addFirst(i);
                sol.addFirst(i);
                msg.append("addFirst(" + i + ")\n");
            } else if (rand == 1) {
                // addLast
                stu.addLast(i);
                sol.addLast(i);
                msg.append("addLast(" + i + ")\n");
            } else if (rand == 2) {
                // removeFirst
                if (sol.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), stu.isEmpty());
                    continue;
                }
                msg.append("removeFirst()\n");
                assertEquals(msg.toString(), sol.removeFirst(), stu.removeFirst());
            } else if (rand == 3) {
                // removeLast
                if (sol.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), stu.isEmpty());
                    continue;
                }
                msg.append("removeLast()\n");
                assertEquals(msg.toString(), sol.removeLast(), stu.removeLast());
            }
        }
    }
}
