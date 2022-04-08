public class CustomerRequest {
    private int id;
    private String request;
    private int documentId;
    private int userId;

    public CustomerRequest(int id, String request, int documentId, int userId) {
        this.id = id;
        this.request = request;
        this.documentId = documentId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
