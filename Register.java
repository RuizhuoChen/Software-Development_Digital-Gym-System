import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;

 /**
	 * The Register window of the online gym
	 * updated on 2021/4/21: added the verification of password and e-mail
     * updated on 2021/5/27: modified the verification of password and e-mail
	 */

public class Register extends JFrame {
    JTextField emailField;
    JTextField accountField;
    JPasswordField passwordField;
    JPasswordField confirmField;

    public Register() throws HeadlessException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(0, 0, 600, 800); 
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 35));
        titleLabel.setBounds(245, 50, 200, 80);
        contentPane.add(titleLabel);

        JLabel accountLabel = new JLabel("Account:");
        accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        accountLabel.setBounds(50, 150, 100, 30);
        contentPane.add(accountLabel);

        accountField = new JTextField();
        accountField.setBounds(170, 150, 145, 25);
        contentPane.add(accountField);
        accountField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 225, 100, 30);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(170, 225, 145, 25);
        contentPane.add(passwordField);

        JLabel confirmLabel = new JLabel("Confirm:");
        confirmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        confirmLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        confirmLabel.setBounds(50, 300, 100, 30);
        contentPane.add(confirmLabel);

        confirmField = new JPasswordField();
        confirmField.setColumns(10);
        confirmField.setBounds(170, 300, 145, 25);
        contentPane.add(confirmField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setBounds(50, 375, 100, 30);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(170, 375, 145, 25);
        contentPane.add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 450, 100, 30);
        contentPane.add(registerButton);

        JButton loginButton = new JButton("Login in");
        loginButton.setBounds(300, 450, 100, 30);
        contentPane.add(loginButton);




        JLabel backGroundLabel = new JLabel("");
        backGroundLabel.setIcon(new ImageIcon(Register.class.getResource("images/background.jpg")));
        backGroundLabel.setBounds(0, 0, 600, 800);
        contentPane.add(backGroundLabel);
       

       

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register.super.dispose();
                Login login = new Login();
                login.setLocation(650,100);
                login.setVisible(true);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Member> members = Util.readFile();

                String account = accountField.getText();
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String confirm = String.valueOf(confirmField.getPassword());

                boolean register = true;
                String mess = "";
                for (Member member: Objects.requireNonNull(members)) {
                    if (member.getAccount().equals(account)){
                        register = false;
                        mess = "The account already exists.";              //1. check for duplicate account,the register flag is false while registration failed
                        break;
                    }
                }
                if (account.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                    register = false;
                    mess = "The message cannot be empty";                  //2. check for any empty line in account/e-mail/password/password confirmation
                }

                if(Util.emailFormat(email)==0){
                    register = false;
                    mess = "The e-mail format is uncorrect,need to include @ "; //3. check for e-mail format
                }
                if(Util.passwordFormat(password)==-1){
                    register = false;
                    mess = "The password should be longer than 6 digit "; //4.check for password length
                }
                if(Util.passwordFormat(password)==0){
                    register = false;
                    mess = "The password should include both character and number "; //5.check for password have both character and number
                }

                if (register){                                             //4. check if previous tests have passed 
                    if (!password.equals(confirm)){
                        mess = "Entered passwords differ!";                //5. check for whether password meets confirmation
                    }else {
                        Member member = new Member(account,password,email,"normal");//if all test passed, create new Member object
                        members.add(member);
                        if (Util.writeFile(members)){                      //write account information into file
                            mess = "Register Success!";
                        }else{
                            mess = "Register Error!";
                        }
                    }
                }
                JOptionPane.showMessageDialog(Register.super.rootPane, mess);
                if (mess.equals("Register Success!")){
                    Register.super.dispose();
                    Login login = new Login();
                    login.setLocation(650, 100);
                    login.setVisible(true);
                }
            }
        });


        //exitEvent
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Login login = new Login();
                login.setLocation(650, 100);
                login.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        new Register().setVisible(true);
    }
}


 