import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('x', 'x'));
        assertFalse(offByOne.equalChars('\'', '?'));
        assertFalse(offByOne.equalChars('1', '4'));
        assertFalse(offByOne.equalChars('a', 'g'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('a', 'B'));

        assertTrue(offByOne.equalChars('p', 'q'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('5', '6'));
        assertTrue(offByOne.equalChars('A', 'B'));
    }
}
