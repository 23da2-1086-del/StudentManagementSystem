import java.util.ArrayList;
import java.util.List;

/**
 * A LIFO stack that records recent actions performed in the system
 * (e.g. "Added student S001", "Deleted student S003").
 * Owned by: Member 2 (Stack + Queue)
 *
 * Implemented over a resizable ArrayList so push/pop are O(1) at the end,
 * matching standard stack behaviour without relying on java.util.Stack.
 */
public class ActionStack {

    private final List<String> stack;

    public ActionStack() {
        stack = new ArrayList<>();
    }

    /** Pushes a new action description onto the top of the stack. */
    public void push(String action) {
        stack.add(action);
    }

    /** Removes and returns the most recent action, or null if the stack is empty. */
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    /** Looks at the most recent action without removing it. */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /** Displays actions with the most recent one first (LIFO order). */
    public void displayRecentActions() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (Most Recent First) ----");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println((stack.size() - i) + ". " + stack.get(i));
        }
    }
}