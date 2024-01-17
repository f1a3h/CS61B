import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("aabbabba"));
        assertFalse(palindrome.isPalindrome("abbabbab"));
        assertFalse(palindrome.isPalindrome("Malayalam"));
        /* A non-alphabetic character should be OK. */
        // assertFalse(palindrome.isPalindrome("?"));

        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("x"));
        assertTrue(palindrome.isPalindrome("rotavator"));
    }

    @Test
    public void testIsOffByOnePalindrome() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("cat", cc));
        assertFalse(palindrome.isPalindrome("Ab", cc));
        assertFalse(palindrome.isPalindrome("xx", cc));

        assertTrue(palindrome.isPalindrome("ab", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
    }
}
