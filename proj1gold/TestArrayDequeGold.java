import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
public class TestArrayDequeGold {

    @Test
    public void testAddFirst() {
        StudentArrayDeque<Integer> d1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> d2 = new ArrayDequeSolution<>();

        for (int i = 0 ;i < 10; i += 1) {
            int randomNumber = StdRandom.uniform(1, 10);
            d1.addFirst(randomNumber);
            d2.addFirst(randomNumber);
        }

       for (int i = 0;i < 10;i += 1) {
           assertEquals(d1.get(i), d2.get(i));
       }

       for (int i = 0; i < 5; i += 1) {
           d1.addFirst(1);
       }

       for (int i = 0; i < 10; i += 1) {
           assertEquals(d2.get(i), d1.get(i + 5));
       }

    }
}
