
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
     * The window where user can edit their e-mail
	 * Created on 2021/05/06
     * Updated on 2021/5/18: change the output to correspond to the change in the JTabbedPane
	 * 
	 */


public class EditEmail extends JFrame{

 
    /**The JTabbedPane where the change of E-mail will be updated to*/
    JTabbedPane jt1;


    

    public EditEmail(String currentaccount,JTabbedPane jt) throws HeadlessException{
        JButton confirm;
        JButton back;
        JLabel newemail1;                    
        JLabel confirmemail1;
        JTextField newemail2;
        JTextField confirmemail2;
        //accquire the JTabbedPane that created this page
        jt1 = jt;

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

        newemail1 = new JLabel("New E-mail");
        newemail1.setBounds(20, 60, 125, 25);            
        contentPane.add(newemail1);


        newemail2 = new JTextField();
        newemail2.setBounds(130, 60, 145, 25);           //enter new email here
        newemail2.setColumns(10);
        contentPane.add(newemail2);


        
        confirmemail1 = new JLabel("Confirm E-mail");
        confirmemail1.setBounds(20, 120, 125, 25);
        contentPane.add(confirmemail1);


        confirmemail2 = new JTextField();
        confirmemail2.setBounds(130, 120, 145, 25);  //reenter new email here to confirm
        confirmemail2.setColumns(10);
        contentPane.add(confirmemail2);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                EditEmail.super.dispose();

            }
        });


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newemail = newemail2.getText();
                String confirmemail = confirmemail2.getText();
                String account = currentaccount;
                if(!newemail.equals(confirmemail)){
                    JOptionPane.showMessageDialog(EditEmail.super.rootPane, "The two e-mails are inconsistent, please enter it again"); //check whether this two e-mails are indentical
                }
                else if(Util.emailFormat(newemail)==0){
                    JOptionPane.showMessageDialog(EditEmail.super.rootPane, "E-mail format is invalid, needs to include @");
                }
                else{
                    List<Member> members = Util.readFile();
                    for (Member member: Objects.requireNonNull(members)) {
                        if (member.getAccount().equals(account)){
                            member.setEmail(newemail); 
                            Util.writeFile(members);         //change the email in member.txt
                            Util.recordCurrentUser(member); //change the email in currentuser.txt after modity password
                            JOptionPane.showMessageDialog(EditEmail.super.rootPane, "E-mail successfully changed");
                            EditEmail.super.dispose();
                            
                            //update the change to UserInfoPane
                            UserInfoPane uip = new UserInfoPane();
                            jt1.setComponentAt(0, uip.makeUserInfoPane(jt1));
			                
                            
                        }
                     }
                }
                
            }
        });
        

    }

    
 
}

