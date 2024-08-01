import java.util.UUID;

public class EmailAdministrator {
    public String generateEmail(User user) {
        return user.getName().toLowerCase() + "." + user.getDepartment().toLowerCase() + "@" + user.getLevel().toLowerCase() + ".com";
    }

    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
