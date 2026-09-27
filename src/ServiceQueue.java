/**
 * A FIFO queue that manages student service requests in order of arrival.
 * Owned by: Member 2 (Stack + Queue)
 *
 * Implemented as a custom singly linked queue with front and rear pointers,
 * giving O(1) enqueue and dequeue without relying on java.util.Queue.
 */
public class ServiceQueue {

    private class Node {
        ServiceRequest data;
        Node next;

        Node(ServiceRequest data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    /** Adds a request to the rear of the queue. */
    public void enqueue(ServiceRequest request) {
        Node newNode = new Node(request);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Removes and returns the request at the front of the queue. */
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    /** Looks at the front request without removing it. */
    public ServiceRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("---- Pending Service Requests (Front to Rear) ----");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
    }
}