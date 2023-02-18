public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        ArrayDeque<Character> deque = new ArrayDeque ();
        for(int i = 0; i < word.length(); i += 1) {
            char ch = word.charAt(i);
            deque.addLast(ch);
        }

        return deque;
    }
    private boolean isPalindromeHelper(Deque word) {
        if (word.size() <= 1) {
            return true;
        } else {
            if(word.removeLast() == word.removeFirst()) {
                return isPalindromeHelper(word);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        } else {
            if(cc.equalChars((char)word.removeLast(),  (char)word.removeFirst())) {
                return isPalindromeHelper(word, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d,cc);
    }
}


