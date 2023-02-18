public class ArrayDeque<T> implements Deque <T> {

    private T[] items;
    private int left;
    private int right;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        left = right = 0; size = 0;
    }

    public int size() {
        return size;
    }

    private int correctIndex(int index) {
        if (index < 0) {
            return index + items.length;
        } else if (index >= items.length) {
            return index % items.length;
        } else {
            return index;
        }
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i += 1) {
            newItems[i] = items[correctIndex(i + left)];
        }
        left = 0;
        right = size - 1;
        items = newItems;

    }
    public void addFirst(T x) {
        if (size == items.length) {
            resize(2 * size);
            left = correctIndex(left - 1);
            items[left] = x;
        } else if (size == 0){
            items[left] = x;
        } else {
            left = correctIndex(left - 1);
            items[left] = x;
        }
        size += 1;
    }
     public void addLast(T x) {
        if (size == items.length) {
            resize(2 * size);
            right = correctIndex(right + 1);
            items[right] = x;
        } else if (size == 0){
            items[right] = x;
        } else {
            right = correctIndex(right + 1);
            items[right] = x;
        }
        size += 1;
     }

     public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T returnValue = items[left];
            if (size > 1) {
                left = correctIndex(left + 1);
            }
            size -= 1;
            if (items.length > 16 && size < items.length / 4) {
                resize((int)(0.5 * items.length));
            }
            return returnValue;
        }
     }

     public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T returnValue = items[right];
            if (size > 1) {
                right = correctIndex(right - 1);
            }
            size -= 1;
            if (items.length > 16 && size < items.length / 4) {
                resize((int)(0.5*items.length));
            }
            return returnValue;
        }
     }

     public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[correctIndex(i + left)] + " ");
        }
     }

     public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[correctIndex(index + left)];
     }

     public boolean isEmpty() {
        return size == 0;
     }

}