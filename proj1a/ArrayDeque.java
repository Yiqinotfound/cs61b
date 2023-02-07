
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

    public void moveFirstIndex(int d) {
        if (firstIndex + d >= 0 && firstIndex + d < items.length) {
            firstIndex = firstIndex + d;
        } else if (firstIndex + d < 0) {
            firstIndex = items.length + (firstIndex + d);
        } else if (firstIndex + d >= items.length) {
            firstIndex = firstIndex + d - items.length;
        }
    }

    public void moveLastIndex(int d) {
        if (lastIndex + d >= 0 && lastIndex + d < items.length) {
            lastIndex = lastIndex + d;
        } else if (lastIndex + d < 0) {
            lastIndex = items.length + (lastIndex + d);
        } else if (lastIndex + d >= items.length) {
            lastIndex = lastIndex + d - items.length;
        }

    }
    public void addFirst(T x) {
        if (items[firstIndex] != null) {
            resize();
            items[firstIndex + size] = x;
            moveFirstIndex(-1);
            size += 1;
        } else {
            items[firstIndex] = x;
            size += 1;
            moveFirstIndex(-1);
        }
    }

    public void addLast(T x) {
        if (items[lastIndex] != null) {
            resize();
            items[lastIndex] = x;
            moveLastIndex(1);
            size += 1;
        } else {
            items[lastIndex] = x;
            size += 1;
            moveLastIndex(1);
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

    public void removeFirst() {
        moveFirstIndex(1);
    }

    public void removeLast() {
        moveLastIndex(-1);
    }

    public T get(int index) {
        return items[firstIndex + index + 1];
    }

    public static void main(String[] args) {

        ArrayDeque<String> a = new ArrayDeque();
        a.addLast("a");
        a.addLast("b");
        a.addFirst("c");
        a.addLast("d");
        a.addLast("e");
        a.addFirst("f");
        a.addLast("g");
        a.addLast("h");
        a.addLast("Z");
        a.addLast("I");
        a.addLast("J");
        a.addLast("k");
        a.addLast("L");
        a.addLast("a");
        a.addLast("a");
        a.addLast("a");
        a.addLast("a");
        a.addLast("a");
        a.addLast("a");
        a.addLast("a");

        a.printDeque();



    }


}

