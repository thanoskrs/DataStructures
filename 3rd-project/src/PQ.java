import javax.swing.*;

public class PQ implements PQInterface{

    private Suspect heap[];
    private int size;

    private static final int DEFAULT_CAPACITY = 5;
    private static final int AUTOGROW_SIZE = 5;

    public PQ() {
        heap = new Suspect[DEFAULT_CAPACITY + 1];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(Suspect item) {
        if (size == heap.length - 1)
            resize();
        heap[++size] = item;
        swim(size);
    }

    @Override
    public Suspect max() {
        if (isEmpty())
            return null;
        return heap[1];
    }

    @Override
    public Suspect getmax() {
        if (isEmpty()) {
            return null;
        }

        Suspect root = heap[1];
        heap[1] = heap[size];
        size--;

        sink(1);
        return root;
    }

    private void swim(int i) {
        if (i == 1)
            return;

        int parent = i / 2;

        while (i > 1 && heap[i].compareTo(heap[parent])) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }

    private void sink(int i){
        int left_child = 2 * i;
        int right_child = left_child + 1;

        if (left_child > size)
            return;

        while (left_child <= size) {

            int max = left_child;
            if (right_child <= size) {
                if (heap[right_child].compareTo(heap[left_child]))
                    max = right_child;
            }
            if (heap[i].compareTo(heap[max])) {
                return;
            } else {
                swap(i, max);
                i = max;
                left_child = 2 * i;
                right_child = left_child + 1;
            }
        }
    }

    private void swap(int i, int j) {
        Suspect temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    private void resize() {
        Suspect[] newHeap = new Suspect[heap.length + AUTOGROW_SIZE];

        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
}
