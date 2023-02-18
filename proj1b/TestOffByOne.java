import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testTestOffByOne() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('d','c'));
        assertFalse(offByOne.equalChars('a','c'));
        assertTrue(offByOne.equalChars('&','%'));
    }
    // Your tests go here.
}
