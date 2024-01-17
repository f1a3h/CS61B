public class Palindrome {
    /**
     * Convert a word to a deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); ++i) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * Check if the given Deque<Character> is a palindrome.
     */
    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }

        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        return first == last ? isPalindromeHelper(deque) : false;
    }

    /**
     * Check if the given word is a palindrome.
     */
    public boolean isPalindrome(String word) {
        if (word.isEmpty()) {
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    /**
     * Check if the given word is a palindrome using a CharacterComparator.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }

        for (int i = 0; i < word.length() / 2; ++i) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
