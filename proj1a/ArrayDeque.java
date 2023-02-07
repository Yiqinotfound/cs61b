
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        firstIndex = 4;
        size = 0;
        lastIndex = 5;
    }
    /**要resize() 的时候肯定是first在last右边*/
    private void resize() {
        T[] newItems = (T[]) new Object[size * 2];
        for (int i = 0; i < lastIndex; i += 1) {
            newItems[i] = items[i];
        }

        for (int i = firstIndex + 1; i < items.length; i += 1) {
            newItems[i + items.length] = items[i];
        }
        items = newItems;
        firstIndex += size;

    }


    private int changeIndex(int index, int change) {
        int t = index + change;
        if (t >= 0 && t < items.length) {
            return t;
        } else if (t < 0) {
            return t + items.length;
        } else {
            return t - items.length;
        }
    }
    public void addFirst(T x) {
        if (items[firstIndex] != null) {
            resize();
            items[firstIndex + size] = x;
            firstIndex = changeIndex(firstIndex, -1);
            size += 1;
        } else {
            items[firstIndex] = x;
            size += 1;
            firstIndex = changeIndex(firstIndex, -1);
        }
    }

    public void addLast(T x) {
        if (items[lastIndex] != null) {
            resize();
            items[lastIndex] = x;
            lastIndex = changeIndex(lastIndex, 1);
            size += 1;
        } else {
            items[lastIndex] = x;
            size += 1;
            lastIndex = changeIndex(lastIndex, 1);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (firstIndex < lastIndex) {
            for (int i = firstIndex + 1; i < lastIndex; i += 1) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = firstIndex + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < lastIndex; i += 1) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public T removeFirst() {
        int returnIndex = changeIndex(firstIndex, 1);
        T returnValue = items[returnIndex];
        firstIndex = changeIndex(firstIndex, 1);
        return returnValue;
    }

    public T removeLast() {
        int returnIndex = changeIndex(lastIndex, -1);
        T returnValue = items[returnIndex];
        lastIndex = changeIndex(lastIndex, -1);
        return returnValue;
    }

    public T get(int index) {
        return items[firstIndex + index + 1];
    }

}



