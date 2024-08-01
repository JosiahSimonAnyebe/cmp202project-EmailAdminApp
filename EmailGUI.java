import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class EmailGUI extends JFrame {
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField levelField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JButton submitButton;
    private JButton clearButton;
    private EmailAdministrator emailAdministrator;

    public EmailGUI() {
        emailAdministrator = new EmailAdministrator();

        nameField = new JTextField(20);
        departmentField = new JTextField(20);
        levelField = new JTextField(20);
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear Entries");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(nameField.getText(), departmentField.getText(), levelField.getText());
                String email = emailAdministrator.generateEmail(user);
                String password = emailAdministrator.generatePassword();
                user.setEmail(email);
                user.setPassword(password);
                emailLabel.setText("Email: " + email);
                passwordLabel.setText("Password: " + password);
                try {
                    FileWriter writer = new FileWriter("user_data.txt", true);
                    writer.write(user.getName() + "," + user.getDepartment() + "," + user.getLevel() + "," + user.getEmail() + "," + user.getPassword() + "\n");
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving user data");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                departmentField.setText("");
                levelField.setText("");
                emailLabel.setText("");
                passwordLabel.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);
        panel.add(new JLabel("Level:"));
        panel.add(levelField);
        panel.add(submitButton);
        panel.add(clearButton);
        panel.add(emailLabel);
        panel.add(passwordLabel);

        add(panel);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EmailGUI();
    }
}
