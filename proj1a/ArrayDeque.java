public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        firstIndex = 4;
        size = 0;
        lastIndex = 4;
    }
    /**瑕乺esize() 鐨勬椂鍊欒偗瀹氭槸first鍦╨ast鍙宠竟*/
    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < lastIndex; i += 1) {
            newItems[i] = items[i];
        }

        for (int i = firstIndex + 1; i < items.length; i += 1) {
            newItems[i + newSize - items.length] = items[i];
        }
        firstIndex += newSize - items.length;
        items = newItems;

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
        if (size == items.length) {
            resize(2 * size);
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
        if (size == items.length) {
            resize(2 * size);
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
        if (size == 0) {
            return null;
        }
        int returnIndex = changeIndex(firstIndex, 1);
        T returnValue = items[returnIndex];
        firstIndex = changeIndex(firstIndex, 1);
        size -= 1;
        if ((size  < items.length / 4) && (items.length > 16)) {
            resize((int)(0.5 * items.length + 1));
        }
        return returnValue;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int returnIndex = changeIndex(lastIndex, -1);
        T returnValue = items[returnIndex];
        lastIndex = changeIndex(lastIndex, -1);
        size -= 1;
        if ((size < (items.length / 4) ) && (items.length > 16)) {
            resize((int)(0.5 * items.length + 1) );
        }
        return returnValue;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = changeIndex(firstIndex, index + 1);
        return items[realIndex];
    }


}



