public class PQ implements PQinterface {

    private City heap[];
    private int IDposition[];
    private int size;
    private Comparator comparator;

    private static final int DEFAULT_CAPACITY = 5;
    private static final int AUTOGROW_SIZE = 5;

    public PQ() {
        heap = new City[DEFAULT_CAPACITY + 1];
        size = 0;
        IDposition = new int[1000];
        comparator = new Comparator();
    }

    @Override
    public boolean isEmpty() {
       return size == 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void insert(City x) {
        if ( (double) size / (heap.length - 1) >= 0.75)
            resize();

        heap[++size] = x;
        IDposition[x.getID()] = size;
        swim(size);
    }

    @Override
    public City max() {
        if (isEmpty())
            return null;

        return heap[1];
    }

    @Override
    public City getmax() {
        if (isEmpty()) {
            return null;
        }

        City root = heap[1];
        heap[1] = heap[size];
        IDposition[heap[size].getID()] = 1;
        size--;

        sink(1);
        IDposition[root.getID()] = 0;
        return root;
    }

    @Override
    public City remove(int id)
    {
        if (IDposition[id] == 0)
             return null;

        City city = heap[IDposition[id]];
        heap[IDposition[id]] = heap[size];
        IDposition[heap[size].getID()] = IDposition[id];
        size--;

        sink(IDposition[id]);
        IDposition[city.getID()] = 0;
        return city;
    }


    private void swim(int i) {
        if (i == 1)
            return;

        int parent = i / 2;

        while (i > 1 && comparator.compareTo(heap[i], (heap[parent]))) {
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
                if (comparator.compareTo(heap[right_child], (heap[left_child])))
                    max = right_child;
            }
            if (comparator.compareTo(heap[i], (heap[max]))) {
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
        City temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        IDposition[heap[i].getID()] = i;
        IDposition[heap[j].getID()] = j;
    }


    private void resize() {
        City[] newHeap = new City[heap.length + AUTOGROW_SIZE];

        for(int i = 0; i<= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }


    public void minToMax() {
        comparator.priority = Comparator.choosePriority.MAX;
        for (int parent = size/2; parent >= 1; parent--) {
            sink(parent);
        }
    }
}
