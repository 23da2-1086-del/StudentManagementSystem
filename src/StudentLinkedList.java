import java.util.ArrayList;
import java.util.List;

/**
 * A custom singly linked list used as the primary storage for Student records.
 * Owned by: Member 1 (Student Records + Linked List)
 *
 * Each Node holds one Student and a reference to the next Node.
 * The list supports add, search, update, delete and display, which are the
 * core CRUD operations required for student record management.
 */
public class StudentLinkedList {

    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    /**
     * Adds a new student to the end of the list.
     * Returns false if a student with the same ID already exists (duplicate ID rule).
     */
    public boolean add(Student student) {
        if (contains(student.getStudentId())) {
            return false;
        }
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    public boolean contains(String studentId) {
        return search(studentId) != null;
    }

    /** Traverses the list looking for a matching Student ID (case-insensitive). */
    public Student search(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Updates the fields of an existing student in place. */
    public boolean update(String studentId, String name, String programme, double marks) {
        Student existing = search(studentId);
        if (existing == null) {
            return false;
        }
        existing.setName(name);
        existing.setProgramme(programme);
        existing.setMarks(marks);
        return true;
    }

    /** Removes the node matching the given Student ID, relinking neighbours. */
    public boolean delete(String studentId) {
        if (head == null) {
            return false;
        }
        if (head.data.getStudentId().equalsIgnoreCase(studentId)) {
            head = head.next;
            size--;
            return true;
        }
        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("---- All Student Records (Linked List) ----");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /** Returns a snapshot of all students, useful for integration/testing. */
    public List<Student> toList() {
        List<Student> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}