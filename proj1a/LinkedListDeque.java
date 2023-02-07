public class LinkedListDeque<T> {
    private TNode sentinel;
    int size;
    private class TNode {
        private T item;
        private TNode next;
        private TNode prev;

        TNode(T i, TNode p, TNode n) {
            item = i;
            next = n;
            prev = p;
        }
        public  T getRecursiveHelper(int index) {
            if (index == 0) {
                return item;
            } else {
                return next.getRecursiveHelper(index - 1);
            }
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T x) {
        TNode newNode = new TNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T x) {
        TNode newNode = new TNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnValue = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return returnValue;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnvalue = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return returnvalue;
    }

    public T get(int index) {
        int i = 0;
        TNode cur = sentinel.next;
        while (i < index) {
            cur = cur.next;
            i += 1;
        }
        return cur.item;
    }

    public T getRecursive(int index) {
        return sentinel.getRecursiveHelper(index + 1);
    }
    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque();
        L.addLast("a");
        L.addLast("b");
        L.addLast("c");
        L.addLast("d");
        L.addFirst("e");
        L.printDeque();
        System.out.println();
        L.removeFirst();
        L.printDeque();
        System.out.println();
        L.removeLast();
        L.printDeque();
        System.out.println(L.getRecursive(2));

    }
}
