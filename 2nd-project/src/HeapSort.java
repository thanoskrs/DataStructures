
public class HeapSort{

    public void heapsort(City[] cities) {

        int N = cities.length - 1;
        for (int parent = N/2; parent >= 1; parent--) {
            sink(cities, parent, N);
        }
        while (N > 1) {
            swap(cities,1, N);
            sink(cities, 1, --N);
        }

    }

    private void sink(City[] cities, int parent, int len) {

        int left_child = parent * 2;
        int right_child = left_child + 1;

        if (left_child >  len) {
            return;
        }

        while (left_child <= len) {
            int max = left_child;

            if (right_child <= len) {
                if(cities[left_child].compareTo(cities[right_child])) {
                    max = right_child;
                }
            }

            if (cities[max].compareTo(cities[parent])) {
                return;
            } else {
                swap(cities, parent, max);
                parent = max;
                left_child = parent * 2;
                right_child = left_child + 1;
            }
        }

    }

    private void swap (City[] cities, int i, int j) {

        City temp = cities[i];
        cities[i] = cities[j];
        cities[j] = temp;
    }
}
