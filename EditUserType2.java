import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;
   /**
     * The window where user confirmed and pay for VIP upgrade
	 * Created on 2021/05/18
     * updated on 2021/05/21: modify the page, now have two type of user
	 * 
	 */


public class EditUserType2 extends JFrame{

               
    
    
    /**The JTabbedPane where the change of user type will be updated to*/
    JTabbedPane jt1;


    

    public EditUserType2(String currentaccount,JTabbedPane jtb,String viptype) throws HeadlessException{
        JButton confirm;
        JButton back;
        JLabel carddisplay;  
        JLabel mess;   
        JTextField cardnum; 
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

        String message = "You are booking the "+ viptype +" package";
        mess = new JLabel(message);
        mess.setBounds(40, 0, 220,120);            
        contentPane.add(mess);


        carddisplay = new JLabel("Please enter your bank account: ");
        carddisplay.setBounds(40, 40, 220,120);            
        contentPane.add(carddisplay);

        cardnum = new JTextField();
        cardnum.setBounds(65, 120, 145, 25);           //enter new password here
        cardnum.setColumns(10);
        contentPane.add(cardnum);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Util.accountFormat(cardnum.getText())){
                    
                    List<Member> members = Util.readFile();
                    for (Member member: Objects.requireNonNull(members)) {
                        if (member.getAccount().equals(currentaccount)){
                            if(viptype.equals("vip1")){
                                member.setUserType("VIP"); //case1:vip1 pack
                                member.setVideoTimes(50 + member.getVideoTimes());
                            }
                            else if(viptype.equals("vip2")){
                                member.setUserType("VIP");
                                member.setVideoTimes(100 + member.getVideoTimes());//case2:vip2 pack
                            }
                            else{
                                member.setUserType("SVIP");
                                member.setVideoTimes(-1);//case3:svip pack
                            }                     
                        Util.writeFile(members);  //change the information in member.txt       
                        Util.recordCurrentUser(member);  //change the information in member.txt
                        }
                    }
                    EditUserType2.super.dispose();
                    EditUserType3 eut3 = new EditUserType3(currentaccount,jt1);
                    eut3.setTitle("Notify");
                    eut3.setLocation(800,300);
                    eut3.setVisible(true);
                    //update the change to UserInfoPane
                    UserInfoPane uip = new UserInfoPane();
                    jt1.setComponentAt(0, uip.makeUserInfoPane(jt1));
                }
            
                else{
                    JOptionPane.showMessageDialog(EditUserType2.super.rootPane, "This account is invalid"); 
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditUserType2.super.dispose();
                EditUserType1 etu1 = new EditUserType1(currentaccount,jt1);
                etu1.setTitle("Upgrade to VIP");
                etu1.setLocation(400,100);
                etu1.setVisible(true);
            }
        });
    }


    
}