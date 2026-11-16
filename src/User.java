public class User {

    int userId;
    String username;
    String password;
    Role role;

        public User(int userId, String username, String password, Role role) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public Role getRole() {
            return role;
        }
    }
