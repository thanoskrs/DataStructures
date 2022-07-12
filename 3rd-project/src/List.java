public class List implements ListInterface {

    public class Node {

        private Suspect item;
        private Node next = null;

        Node(Suspect item) {
            this.item = item;
        }
    }

    private Node head = null;
    private int totalSuspects = 0;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void addFirst(Suspect item) {
        Node n = new Node(item);
        if (isEmpty())
            head = n;
        else {
            n.next = head;
            head = n;
        }
        totalSuspects++;
    }

    @Override
    public Suspect removeFirst() {
        if (isEmpty())
            return null;
        else {
            Suspect item = head.item;
            head = head.next;
            totalSuspects--;
            return item;
        }
    }

    @Override
    public Suspect getFirst() {
        if (isEmpty())
            return null;
        else
            return head.item;
    }

    @Override
    public void printQueue() {
        if (isEmpty())
            return;
        else {
            for (Node n = head; n != null; n = n.next) {
                System.out.println(n.item);
            }
        }
    }

    @Override
    public int size() {
        return totalSuspects;
    }
}
