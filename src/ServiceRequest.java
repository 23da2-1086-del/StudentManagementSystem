/**
 * Represents a single student service request (e.g. transcript, ID card).
 * Owned by: Member 2 (Stack + Queue)
 */
public class ServiceRequest {

    private final String studentId;
    private final String requestType;

    public ServiceRequest(String studentId, String requestType) {
        this.studentId = studentId;
        this.requestType = requestType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return studentId + " - " + requestType;
    }
}