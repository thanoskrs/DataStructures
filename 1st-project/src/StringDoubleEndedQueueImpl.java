import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue <T> {


    public class Node<T> {

        private T item;
        private Node<T> next = null;
        private Node<T> prev = null;

        Node(T item) {
            this.item = item;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    /* we increase totalItems by 1 every time
       we insert a new node at the queue, and decrease by 1 when we
       remove a node. So, size has time O(1)
     */
    private int totalItems = 0;

    @Override
    public boolean isEmpty() {
        return head == null;
      
    }

    @Override
    public void addFirst(T item) {
        Node<T> i = new Node<T>(item);
        if(isEmpty()) {
            head = tail = i;
        } else {
            i.next = head;
            head.prev = i;
            head = i;
        }
        totalItems += 1;

    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        // no item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = head.item; //we need the first item
        // 1 or more items
        if (size() == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        totalItems -=1;
        return item;
    }

    @Override
    public void addLast(T item) {
        Node<T> i = new Node<T>(item);
        if(isEmpty()) {
            head = tail = i;
        } else {
            i.prev = tail;
            tail.next = i;
            tail = i;
        }
        totalItems += 1;

    }

    @Override
    public T removeLast() throws NoSuchElementException {
        // no item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = tail.item; //we need the last item
        // 1 or more items
        if (size() == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        totalItems -=1;
        return item;
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.item;
    }

    @Override
    public T getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.item;
    }

    @Override
    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.print("List is empty.");
        }
		stream.print("HEAD--> ");
        for (Node t=head; t!=null;t =t.next) {
            if(t.next != null) {
                stream.print(t.item + " <--> ");
            } else {
                stream.print(t.item);
            }
        }
		stream.print(" <--TAIL");

    }

    @Override
    public int size() {
        return totalItems;
    }
}