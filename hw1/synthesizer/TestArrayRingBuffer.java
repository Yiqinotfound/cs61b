package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }
    @Test
    public void testEnqueueAndDequeue() {
        ArrayRingBuffer<Integer> array = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i += 1) {
            array.enqueue(i);
        }

        int expected = 0;
        assertEquals(expected, (int)array.peek());
        assertEquals(0,(int)array.dequeue());
        expected = 1;
        assertEquals(expected,(int)array.peek());
    }

    @Test
    public void testIteration() {
        ArrayRingBuffer<Integer> array = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i += 1) {
            array.enqueue(i);
        }

        Iterator<Integer> iterator = array.iterator();
        for (int i = 0; i < 10; i += 1) {
            assertEquals((int)iterator.next(), i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
