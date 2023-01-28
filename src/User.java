public class User {
    private String username;
    private String IIN;
    private String password;

    public User(String username, String ID, String password) {
        this.username = username;
        this.IIN = ID;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getIIN() {
        return IIN;
    }

    public String getPassword() {
        return password;
    }
}
