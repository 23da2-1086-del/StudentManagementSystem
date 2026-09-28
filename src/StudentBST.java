/**
 * A Binary Search Tree that organizes Student records by Student ID,
 * enabling ordered display and O(log n) average-case search.
 * Owned by: Member 3 (BST + Hashing)
 *
 * Ordering rule: for any node, every ID in the left subtree is
 * alphabetically/numerically smaller than the node's ID, and every ID in
 * the right subtree is larger (case-insensitive comparison).
 */
public class StudentBST {

    private class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    /** Inserts a student, rejecting duplicate IDs. */
    public boolean insert(Student student) {
        if (search(student.getStudentId()) != null) {
            return false;
        }
        root = insertRec(root, student);
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        int cmp = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    /** Searches for a student by ID, moving left or right based on comparison. */
    public Student search(String studentId) {
        Node current = root;
        while (current != null) {
            int cmp = studentId.compareToIgnoreCase(current.data.getStudentId());
            if (cmp == 0) {
                return current.data;
            }
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = deleteRec(root, studentId);
        return true;
    }

    private Node deleteRec(Node node, String studentId) {
        if (node == null) {
            return null;
        }
        int cmp = studentId.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** In-order traversal prints students sorted by Student ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("---- Students (BST In-Order by Student ID) ----");
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}
