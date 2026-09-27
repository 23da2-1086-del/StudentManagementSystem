import java.util.Scanner;

/**
 * Entry point and menu-driven console interface for the University Student
 * Record and Campus Route Management System.
 *
 * Integration design:
 * A single Student object is shared by StudentLinkedList, StudentBST and
 * HashTable — the same reference is inserted into all three, so updating a
 * field on that object (e.g. via studentList.update) is automatically
 * visible everywhere, and no Student data is ever duplicated. Every
 * successful operation is also logged onto the ActionStack so the "Recent
 * Actions" screen reflects real system history rather than a separate log.
 */
public class Main {

    private static final StudentLinkedList studentList = new StudentLinkedList();
    private static final ActionStack actionStack = new ActionStack();
    private static final ServiceQueue serviceQueue = new ServiceQueue();
    private static final StudentBST studentBST = new StudentBST();
    private static final HashTable hashTable = new HashTable();
    private static final CampusGraph campusGraph = new CampusGraph();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    studentList.displayAll();
                    break;
                case 5:
                    addServiceRequest();
                    break;
                case 6:
                    processServiceRequest();
                    break;
                case 7:
                    actionStack.displayRecentActions();
                    break;
                case 8:
                    studentBST.displayInOrder();
                    break;
                case 9:
                    searchStudentHashing();
                    break;
                case 10:
                    addCampusLocation();
                    break;
                case 11:
                    removeCampusLocation();
                    break;
                case 12:
                    addCampusConnection();
                    break;
                case 13:
                    removeCampusConnection();
                    break;
                case 14:
                    campusGraph.displayConnections();
                    break;
                case 15:
                    traverseCampus();
                    break;
                case 16:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("==================================================");
        System.out.println(" University Student Record and Campus Route Management System");
        System.out.println("==================================================");
        System.out.println(" 1.  Add Student Record");
        System.out.println(" 2.  Update Student Record");
        System.out.println(" 3.  Delete Student Record");
        System.out.println(" 4.  Display All Records using Linked List");
        System.out.println(" 5.  Add Service Request to Queue");
        System.out.println(" 6.  Process Next Service Request");
        System.out.println(" 7.  Display Recent Actions using Stack");
        System.out.println(" 8.  Display Students using BST");
        System.out.println(" 9.  Search Student using Hashing");
        System.out.println("10.  Add Campus Location");
        System.out.println("11.  Remove Campus Location");
        System.out.println("12.  Add Campus Connection/Road");
        System.out.println("13.  Remove Campus Connection/Road");
        System.out.println("14.  Display Campus Connections");
        System.out.println("15.  Traverse Campus Locations using BFS");
        System.out.println("16.  Exit");
    }

    // ---------------------------------------------------------------
    // Student record operations (Member 1's structures, integrated)
    // ---------------------------------------------------------------

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }
        if (studentList.contains(id)) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        System.out.print("Enter Programme: ");
        String programme = scanner.nextLine().trim();

        double marks = readMarks();
        if (marks < 0) {
            return; // readMarks already printed the validation message
        }

        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        studentBST.insert(student);
        hashTable.insert(student);
        actionStack.push("Added student " + id);

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine().trim();
        Student existing = studentList.search(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        System.out.print("Enter new Programme: ");
        String programme = scanner.nextLine().trim();

        double marks = readMarks();
        if (marks < 0) {
            return;
        }

        studentList.update(id, name, programme, marks);
        // studentBST and hashTable hold a reference to the SAME Student object,
        // so no further action is needed to keep them consistent.
        actionStack.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine().trim();
        if (studentList.search(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        studentList.delete(id);
        studentBST.delete(id);
        hashTable.delete(id);
        actionStack.push("Deleted student " + id);
        System.out.println("Student deleted successfully.");
    }

    /** Reads and validates marks; returns -1 (invalid sentinel) on any problem. */
    private static double readMarks() {
        System.out.print("Enter Marks (0-100): ");
        String input = scanner.nextLine().trim();
        try {
            double marks = Double.parseDouble(input);
            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return -1;
            }
            return marks;
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks entered. Please enter a number.");
            return -1;
        }
    }

    // ---------------------------------------------------------------
    // Stack + Queue operations (Member 2)
    // ---------------------------------------------------------------

    private static void addServiceRequest() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Request Type (e.g. Transcript request): ");
        String type = scanner.nextLine().trim();
        if (id.isEmpty() || type.isEmpty()) {
            System.out.println("Student ID and request type cannot be empty.");
            return;
        }
        serviceQueue.enqueue(new ServiceRequest(id, type));
        actionStack.push("Added service request for " + id);
        System.out.println("Service request added to the queue.");
    }

    private static void processServiceRequest() {
        if (serviceQueue.isEmpty()) {
            System.out.println("No service requests to process.");
            return;
        }
        ServiceRequest request = serviceQueue.dequeue();
        actionStack.push("Processed service request for " + request.getStudentId());
        System.out.println("Processed request: " + request);
    }

    // ---------------------------------------------------------------
    // Hashing search (Member 3)
    // ---------------------------------------------------------------

    private static void searchStudentHashing() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine().trim();
        Student result = hashTable.search(id);
        if (result == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Found: " + result);
        }
    }

    // ---------------------------------------------------------------
    // Campus graph operations (Member 4)
    // ---------------------------------------------------------------

    private static void addCampusLocation() {
        System.out.print("Enter campus location name: ");
        String location = scanner.nextLine().trim();
        if (location.isEmpty()) {
            System.out.println("Location name cannot be empty.");
            return;
        }
        if (campusGraph.addLocation(location)) {
            actionStack.push("Added campus location " + location);
            System.out.println("Location added.");
        } else {
            System.out.println("This location already exists.");
        }
    }

    private static void removeCampusLocation() {
        System.out.print("Enter campus location to remove: ");
        String location = scanner.nextLine().trim();
        if (campusGraph.removeLocation(location)) {
            actionStack.push("Removed campus location " + location);
            System.out.println("Location removed.");
        } else {
            System.out.println("Location not found.");
        }
    }

    private static void addCampusConnection() {
        System.out.print("Enter starting location: ");
        String from = scanner.nextLine().trim();
        System.out.print("Enter destination location: ");
        String to = scanner.nextLine().trim();
        if (!campusGraph.hasLocation(from) || !campusGraph.hasLocation(to)) {
            System.out.println("One or both locations do not exist.");
            return;
        }
        if (campusGraph.addConnection(from, to)) {
            actionStack.push("Added road " + from + " - " + to);
            System.out.println("Connection added.");
        } else {
            System.out.println("This connection already exists.");
        }
    }

    private static void removeCampusConnection() {
        System.out.print("Enter starting location: ");
        String from = scanner.nextLine().trim();
        System.out.print("Enter destination location: ");
        String to = scanner.nextLine().trim();
        if (campusGraph.removeConnection(from, to)) {
            actionStack.push("Removed road " + from + " - " + to);
            System.out.println("Connection removed.");
        } else {
            System.out.println("Connection not found.");
        }
    }

    private static void traverseCampus() {
        if (campusGraph.isEmpty()) {
            System.out.println("The campus graph is empty.");
            return;
        }
        System.out.print("Enter starting location for BFS traversal: ");
        String start = scanner.nextLine().trim();
        campusGraph.bfsTraversal(start);
    }

    // ---------------------------------------------------------------
    // Utility
    // ---------------------------------------------------------------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // falls through to "Invalid menu choice"
        }
    }
}