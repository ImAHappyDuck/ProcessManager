import java.util.NoSuchElementException;

public class ReadyQueue {

private Node <Process> header;
private Node <Process> trailer;
private int size;


//    // example method using private inner node class, notice that I do not need to call setters and getters
//    public void method() {
//        Node<String> snode = new Node<>("hi");
//        Node<String> nnode = new Node<>("bye");
//        snode.next = nnode;
//        nnode.prev = snode;
//        snode.value = "HI";
//        System.out.println(snode.next.prev);  // prints: HI created 0 millis ago
//        System.out.println(snode.next.prev.value); // prints: HI
  //  }
public ReadyQueue()
{
    header = new Node<>(null);
    trailer = new Node<>(null);
    header.next = trailer;
    trailer.prev = header;
    trailer.next = null;
    header.prev = null;
    size = 0;
}
public ReadyQueue (Process inital)
{
    header = new Node<>(null);
    trailer = new Node<>(null);
    Node init = new Node<>(inital);
    header.prev = null;
    trailer.next = null;
    header.next = init;
    trailer.prev = init;
    size = 1;
}
public int getNumProcesses()
{
    return size;
}
public void addProcess (Process p)
{
    Node newest = new Node(p);
    newest.prev = trailer.prev;
    trailer.prev = newest;
    newest.next = trailer;
    newest.prev.next = newest;
    size++;
}
public Process getFirst()
{
    return header.next.getElement();
}
//TODO: find if method type is acceptable and if should throw expt
public Process.Status removeProcess() throws Exception {
    if (size == 0)
    {
        throw new NoSuchElementException("Empty Queue");
    }
    Node<Process> remove = header.next;
    header.next = remove.next;
    remove.next.prev = header;
    size--;
    return remove.value.getState();

}
public void contextSwitch()
{
    Node front = header.next;
    header.next = front.next;
    front.next.prev = header;
    front.prev = trailer.prev;
    trailer.prev.next = front;
    trailer.prev = front;
    front.next = trailer;

}
@Override public String toString()
{
    Node<Process> curr = header.next;
    String gb ="\nPrinting ready queue(" + size + " processes):" + "\n" +  curr.getElement().toString();

    for (int i = 1; i < size;i++)//maybe change to while .next != trailer
    {
        curr = curr.next;
        gb = gb + "\n" + curr.getElement().toString();
    }
    return gb = gb + "\n";
}
public int getSize()
{return size;}



    // this is just for testing that the list is set up correctly
    // please do not remove
    public Process thirdFromBack() {
        return trailer.prev.prev.prev.getElement();
    }


    private class Node<T> {
        private T value;
        private long creationTime;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
            creationTime = System.currentTimeMillis();
        }
        public T getElement() {
            return value;
        }
    }

}
