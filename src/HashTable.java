import java.util.ArrayList;
import java.util.List;

/**
 * A hash table keyed by Student ID, used for fast O(1) average-case search.
 * Owned by: Member 3 (BST + Hashing)
 *
 * Collision handling strategy: separate chaining. Each bucket is a small
 * List<Student>; when two IDs hash to the same bucket, both are stored in
 * that bucket's list and distinguished by comparing IDs during search.
 */
public class HashTable {

    private static final int TABLE_SIZE = 16;
    private final List<Student>[] buckets;

    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets = new List[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    /** Simple polynomial hash function over the student ID's characters. */
    private int hash(String studentId) {
        int hashCode = 0;
        for (char c : studentId.toUpperCase().toCharArray()) {
            hashCode = (hashCode * 31 + c);
        }
        return Math.abs(hashCode) % TABLE_SIZE;
    }

    /** Inserts a student, rejecting duplicate IDs even across a collision chain. */
    public boolean insert(Student student) {
        int index = hash(student.getStudentId());
        for (Student s : buckets[index]) {
            if (s.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                return false;
            }
        }
        buckets[index].add(student);
        return true;
    }

    public Student search(String studentId) {
        int index = hash(studentId);
        for (Student s : buckets[index]) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }

    public boolean delete(String studentId) {
        int index = hash(studentId);
        return buckets[index].removeIf(s -> s.getStudentId().equalsIgnoreCase(studentId));
    }

    public void displayTable() {
        System.out.println("---- Hash Table Contents ----");
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (!buckets[i].isEmpty()) {
                System.out.print("Bucket " + i + ": ");
                for (Student s : buckets[i]) {
                    System.out.print("[" + s.getStudentId() + "] ");
                }
                System.out.println();
            }
        }
    }
}