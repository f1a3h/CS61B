import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDequeGold() {
        StudentArrayDeque stu = new StudentArrayDeque<Integer>();
        ArrayDequeSolution sol = new ArrayDequeSolution<Integer>();
        StringBuilder msg = new StringBuilder();
        int sz = 0;

        for (int i = 0; i < 1024; ++i) {
            int rand = StdRandom.uniform(4);
            if (i % 7 == 0) {
                msg.append("size()\n");
                assertEquals(msg.toString(), sol.size(), stu.size());
            }
            if (rand == 0) {
                // addFirst
                sz++;
                stu.addFirst(i);
                sol.addFirst(i);
                msg.append("addFirst(" + i + ")\n");
                assertEquals(msg.toString(), sol.get(0), stu.get(0));
            } else if (rand == 1) {
                // addLast
                sz++;
                stu.addLast(i);
                sol.addLast(i);
                msg.append("addLast(" + i + ")\n");
                assertEquals(msg.toString(), sol.get(sz - 1), stu.get(sz - 1));
            } else if (rand == 2) {
                // removeFirst
                if (sz < 1) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), stu.isEmpty());
                    continue;
                }
                sz--;
                msg.append("removeFirst()\n");
                assertEquals(msg.toString(), sol.removeFirst(), stu.removeFirst());
            } else if (rand == 3) {
                // removeLast
                if (sz < 1) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), stu.isEmpty());
                    continue;
                }
                sz--;
                msg.append("removeLast()\n");
                assertEquals(msg.toString(), sol.removeLast(), stu.removeLast());
            }
        }
    }
}
