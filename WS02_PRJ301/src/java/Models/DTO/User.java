package Models.DTO;

public class User {
    private String userId;
    private int password;
    private String fullName;
    private int role; // 0: user, 1: staff

    public User() {}

    public User(String userId, int password, String fullName, int role) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public User(String userId, String fullName, int role) {
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
    }

  

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getPassword() { return password; }
    public void setPassword(int password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }
}