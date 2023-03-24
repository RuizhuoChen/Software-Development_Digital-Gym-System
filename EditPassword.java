import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;
   /**
     * The window where user can edit their password
	 * Created on 2021/05/06
     * updated on 2021/5/18: modified to accomadate change in JTabbedPane
     * updated on 2021/5/21
	 * 
	 */

public class EditPassword extends JFrame{


     /**The JTabbedPane where the change of password will be updated to*/
    JTabbedPane jt1;


    

    public EditPassword(String currentaccount,JTabbedPane jtb) throws HeadlessException{
        JButton confirm;
        JButton back;
        JLabel newpassword1;                    
        JLabel confirmpassword1;
        JTextField newpassword2;
        JTextField confirmpassword2;
        jt1 = jtb;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 300, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        confirm = new JButton("Confirm");
        confirm.setBounds(160, 200, 100, 30);               // adding the Confirm Button
        contentPane.add(confirm);

        back = new JButton("Back");
        back.setBounds(40, 200, 100, 30);                   // adding the Back Button
        contentPane.add(back);

        newpassword1 = new JLabel("New Password");
        newpassword1.setBounds(20, 60, 125, 25);            
        contentPane.add(newpassword1);


        newpassword2 = new JTextField();
        newpassword2.setBounds(130, 60, 145, 25);           //enter new password here
        newpassword2.setColumns(10);
        contentPane.add(newpassword2);


        
        confirmpassword1 = new JLabel("Confirm Password");
        confirmpassword1.setBounds(20, 120, 125, 25);
        contentPane.add(confirmpassword1);


        confirmpassword2 = new JTextField();
        confirmpassword2.setBounds(130, 120, 145, 25);  //reenter new password here to confirm
        confirmpassword2.setColumns(10);
        contentPane.add(confirmpassword2);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                EditPassword.super.dispose();
            }
        });


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newpassword = newpassword2.getText();
                String confirmpassword = confirmpassword2.getText();
                String account = currentaccount;
                if(!newpassword.equals(confirmpassword)){
                    JOptionPane.showMessageDialog(EditPassword.super.rootPane, "The two passwords are inconsistent, please enter it again"); //check whether this two passwords are indentical
                }
                else if(Util.passwordFormat(newpassword)==0){
                    JOptionPane.showMessageDialog(EditPassword.super.rootPane, "The password should contain both number and character"); //check for correct password format
                }
                else if(Util.passwordFormat(newpassword)==-1){
                    JOptionPane.showMessageDialog(EditPassword.super.rootPane, "The password should be over 6 digit");//check for correct password format
                }
                else{
                    List<Member> members = Util.readFile();
                    for (Member member: Objects.requireNonNull(members)) {
                        if (member.getAccount().equals(account)){
                            member.setPassword(newpassword); 
                            Util.writeFile(members);         //change the password in member.txt
                            Util.recordCurrentUser(member); //change the password in currentuser.txt after modity password
                            JOptionPane.showMessageDialog(EditPassword.super.rootPane, "Password successfully changed");
                            EditPassword.super.dispose();
                            
                           //update the change to the UserInfoPane

                           UserInfoPane uip = new UserInfoPane();
                           jt1.setComponentAt(0, uip.makeUserInfoPane(jt1));
                            
                        }
                     }
                }
                
            }
        });
        

    }
     
}


