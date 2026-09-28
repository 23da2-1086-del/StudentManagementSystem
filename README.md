# University Student Record and Campus Route Management System

## Project Description

A Java console application developed for CIT300 (Graded Practical Assignment 1) that manages university student records and models the connections between campus locations. The system demonstrates the practical application of linear data structures, trees, hashing, and graphs.

## Objectives

* Store and manage student records (Student ID, Name, Programme, Marks).
* Track recent system actions using a stack (LIFO).
* Manage student service requests using a queue (FIFO).
* Organize and search student records efficiently using a BST and a hash table.
* Model campus locations and roads as a graph and traverse it using BFS.
* Provide a single menu-driven console application with input validation.

## Features

* Add, update, delete, search and display student records.
* Recent action history using a stack and student service request queue.
* Student lookup by ID via both BST (ordered) and hash table (fast search).
* Add/remove campus locations and roads; display the campus network.
* BFS traversal of the campus graph from any starting location.
* Input validation for duplicate IDs, invalid marks, missing records, unknown locations, empty structures, and invalid menu choices.

## Data Structures

| Data Structure         | Used For                                  | File(s)                  |
| ---------------------- | ----------------------------------------- | ------------------------ |
| Singly Linked List     | Primary student record storage            | `StudentLinkedList.java` |
| Stack (LIFO)           | Recent action history                     | `ActionStack.java`       |
| Queue (FIFO)           | Student service requests                  | `ServiceQueue.java`      |
| Binary Search Tree     | Ordered student lookup by ID              | `StudentBST.java`        |
| Hash Table (chaining)  | Fast student ID search                    | `HashTable.java`         |
| Graph (adjacency list) | Campus locations and roads, BFS traversal | `CampusGraph.java`       |

## Technologies

* Java (standard library only, no external dependencies)
* Console-based text interface

## Project Structure

```text
UniversityStudentCampusSystem/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentLinkedList.java
│   ├── ActionStack.java
│   ├── ServiceRequest.java
│   ├── ServiceQueue.java
│   ├── StudentBST.java
│   ├── HashTable.java
│   └── CampusGraph.java
├── README.md
└── .gitignore
```

## How to Run

```text
cd UniversityStudentCampusSystem/src
javac *.java
java Main
```

## Menu

```text
 1. Add Student Record
 2. Update Student Record
 3. Delete Student Record
 4. Display All Records using Linked List
 5. Add Service Request to Queue
 6. Process Next Service Request
 7. Display Recent Actions using Stack
 8. Display Students using BST
 9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS
16. Exit
```

## Group Members

### Member 1

* **Name:** M.F.M. Amry
* **Student ID:** 23DA2-1086
* **Responsibility:** Student records and linked list implementation
* **Contribution:** Implemented the `Student` class and developed the singly linked list for adding, searching, updating, deleting, and displaying student records.


## GitHub Collaboration

Each member developed their assigned module on a dedicated feature branch:

* `feature/member1-student-records` — M.F.M. Amry
* `feature/member2-stack-queue` — M.S.M. Saneej
* `feature/member3-bst-hashing` — M.N.M. Afrath
* `feature/member4-campus-graph` — N.M. Askan

Each member is responsible for committing and pushing their own work to their assigned feature branch. The completed modules can then be reviewed and merged into the `main` branch through pull requests.

The GitHub commit history should reflect the actual work completed by each member.

## Testing

The system should be tested for the main operations of each data structure and feature.

Testing includes:

* Adding, updating, deleting, searching, and displaying student records.
* Testing duplicate student IDs and invalid student information.
* Adding and processing service requests using the queue.
* Displaying recent actions using the stack.
* Searching and displaying students using the BST.
* Searching student records using the hash table.
* Adding and removing campus locations.
* Adding and removing campus roads/connections.
* Displaying campus connections.
* Performing BFS traversal from a selected campus location.
* Testing invalid menu choices and empty data structures.

Only tests that have actually been executed should be reported as passed.
