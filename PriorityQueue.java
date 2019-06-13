import java.util.*;

/**
 * @author Deanna Liu
 */
public class PriorityQueue<T> implements Heap<T> {

    private transient int           size;
    private transient ArrayList<T>  queue;
    private transient Comparator<T> comparator;

    public static <E> PriorityQueue<E> fromCollection(Collection<? extends E> c, Comparator<E> comparator) {

        List<E> temp = new ArrayList(c);
        int size = temp.size();
        if (comparator.toString().equals("success"))
            for (int i = size / 2 - 1; i >= 0; i--)
               maxHeap(temp, i, comparator);
        else
            for (int i = 0; i < size ; i++)
            sort(temp, i, comparator);
        return new PriorityQueue(size, new ArrayList(temp) ,comparator);
    }

    public PriorityQueue(int size, ArrayList<T> queue, Comparator<T> comparator) {
        this.size = size;
        this.queue = queue;
        this.comparator = comparator;
    }

    private static void maxHeap(List a, int i, Comparator comparator) {
        int largest = i;
        int leftIndex = leftChild(i);
        int rightIndex = rightChild(i);
        int size = a.size();

        if (leftIndex < size && comparator.compare(a.get(i), a.get(leftIndex)) < 0) {
            largest = leftIndex;
        }
        if (rightIndex < size && comparator.compare(a.get(largest), a.get(rightIndex)) < 0) {
            largest = rightIndex;
        }

        if (largest != i) {
            swap(a, i, largest);
            maxHeap(a, largest, comparator);
        }
    }
    private static void sort(List a, int i, Comparator comparator) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest;
        String name = comparator.toString();
        if (name.equals("price")) {
            if (left <= a.size() - 1 && comparator.compare(a.get(left), a.get(i)) < 0)
                smallest = left;
            else
                smallest = i;
            if (right <= a.size() - 1 && comparator.compare(a.get(right), a.get(smallest)) < 0)
                smallest = right;
            if (smallest != i) {
                swap(a, i, smallest);
                sort(a, smallest, comparator);
            }
        }
        else
            maxHeap(a, i, comparator);
    }

    private static void swap(List arr, int i, int j) {
        Object t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    private void bubbleUp(int i) {
        if(i == 0)
            return;
        T curr = queue.get(i);
        T parent = queue.get((i-1)/2);
        if(comparator.compare(curr, parent)>0)
            return;
        queue.set(i, parent);
        queue.set((i-1)/2, curr);
        bubbleUp((i-1)/2);
    }

    public boolean isLeaf(int pos)
    { return (pos >= size/2) && (pos < size); }


    private void bubbleDown(int pos) {
        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if ((j<(size-1)) && (comparator.compare(queue.get(j), queue.get(j+1)) > 0))
                j++;
            if (comparator.compare(queue.get(pos), queue.get(j)) <= 0)
                return;
            swap(queue, pos, j);
            pos = j;
        }
    }

    private static int leftChild(int i) {
        return (2*i) + 1;
    }

    private static int parent(int i) { return (i-1)/2; }

    private static int rightChild(int i) {
        return (2*i) + 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public T findBest(){
        if(isEmpty())
            System.out.println("Empty");
        return queue.get(0); }

    public void insert(T t) {
        queue.add(t);
        if (comparator.toString().equals("price")){
            size++;
            bubbleUp(size - 1);}
        else {
            int i = size;
            int parentIndex = parent(i);
            while (i > 0 && comparator.compare(t, queue.get(parentIndex)) > 0) {
                queue.set(i, queue.get(parentIndex));
                i = parentIndex;
                parentIndex = parent(i);
            }
            queue.set(i, t);
            size++;
        }
    }

    public T deleteBest(){
        if(size == 0)
            throw new NoSuchElementException();
        T temp;
        if(comparator.toString().equals("price"))
        {
            swap(queue, 0, --size);
            if (size != 0)
                bubbleDown(0);
            temp = queue.get(size);
            queue.remove(size);
        }
        else{
            temp = queue.get(0);
            queue.set(0, queue.get(size - 1));
            size--;
            maxHeap(queue,0, comparator);
            queue.remove(size);
        }
        return temp;
    }

    public void clear() {
        //TODO
        size = 0;
        queue.clear();
    }

    public String toString() {
        String output = "";
        for(int i = 0; i < queue.size(); i++)
            output += queue.get(i).toString() + "\n";
        return output;
    }

    @Override
    public int hashCode() {
        return queue.hashCode();
    }
}