import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 *  Created a JPanel to contain  user information
 *  Created on 2021/5/18:The JPane contain 1 part of user information
 *  updated on 2021/05/21: modify the appearance of this page
 * 
 */

public class UserInfoPane {
    /**the JPanel called userInfoPane and contained user information that will be created */
    public JPanel userInfoPane;
    /**the  account name of the user*/
    public String currentaccount = null;
    /**the  the password of the user*/
	public String currentpassword = null;
    /**the  e-mail of the user*/
	public String currentemail = null;
    /**the  user type of the user*/
    public String currentusertype = null;
    /**how many times this user can see videos*/
    public String currentvideotimes = null;


    /**
	 * Created a JPanel to contain  user information
	 * @param  JTabbedPane jtb, jtb is the JTabbedPane that will contain this JPanel
	 * @return JPanel, the created JPanel
	 */
    public JPanel makeUserInfoPane(JTabbedPane jtb){
        //initial the JPanel, set layout to be null
        userInfoPane = new JPanel();
        userInfoPane.setLayout(null);
        userInfoPane.setBounds(0, 0, 1200, 800);
        userInfoPane.setBackground(java.awt.Color.white);

        //accquire information of current user
		String allinfo; 
		String[] info;
		BufferedReader br = null;
		InputStreamReader reader = null;
		try{
		    reader = new InputStreamReader(new FileInputStream(new File(Util.currentuser)), StandardCharsets.UTF_8);
            br = new BufferedReader(reader);
		    allinfo = br.readLine(); //obtain all information of current user
		    info = allinfo.split(",");
	        currentaccount = info[0];
		    currentpassword = info[1];
		    currentemail = info[2];
            currentusertype = info[3];
            currentvideotimes = info[4];

		    br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

        //adding Component to display information
        JLabel aNewLabel = new JLabel("Profile");
        aNewLabel.setBounds(400, 10, 400, 120);
        aNewLabel.setFont(new Font(null, Font.PLAIN, 45));
        aNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel);

        JLabel aNewLabel1 = new JLabel("Account:");
        aNewLabel1.setBounds(220, 130, 200, 54);
        aNewLabel1.setFont(new Font(null, Font.PLAIN, 25));
        //aNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel1);

        JLabel aNewLabel2 = new JLabel("Password:");
        aNewLabel2.setBounds(220, 230, 200, 80);
        aNewLabel2.setFont(new Font(null, Font.PLAIN, 25));
        //aNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel2);

        JLabel aNewLabel3 = new JLabel("Email:");
        aNewLabel3.setBounds(220, 330, 200, 80);
        aNewLabel3.setFont(new Font(null, Font.PLAIN, 25));
        //aNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel3);

        JLabel aNewLabel7 = new JLabel("User Type:");
        aNewLabel7.setBounds(220, 430, 200, 80);
        aNewLabel7.setFont(new Font(null, Font.PLAIN, 25));
        //aNewLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel7);

        JLabel aNewLabel9 = new JLabel("Video Times:");
        aNewLabel9.setBounds(220, 530, 200, 80);
        aNewLabel9.setFont(new Font(null, Font.PLAIN, 25));
        //aNewLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel9);
        
        

        JLabel aNewLabel4 = new JLabel(currentaccount);
        aNewLabel4.setBounds(490, 130, 200, 80);
        aNewLabel4.setFont(new Font(null, Font.PLAIN, 25));
        aNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel4);

        JLabel aNewLabel5 = new JLabel(currentpassword);
        aNewLabel5.setBounds(490, 230, 200, 80);
        aNewLabel5.setFont(new Font(null, Font.PLAIN, 25));
        aNewLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel5);

        JLabel aNewLabel6 = new JLabel(currentemail);
        aNewLabel6.setBounds(490, 330, 200, 80);
        //aNewLabel6.setFont(new Font(null, Font.PLAIN, 20));
        aNewLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel6);

        JLabel aNewLabel8 = new JLabel(currentusertype);
        aNewLabel8.setBounds(490, 430, 200, 80);
        aNewLabel8.setFont(new Font(null, Font.PLAIN, 25));
        aNewLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel8);

        if(currentusertype.equals("SVIP")){
            currentvideotimes = "Unlimited";
        }
        JLabel aNewLabel10 = new JLabel(currentvideotimes);
        aNewLabel10.setBounds(490, 530, 200, 80);
        aNewLabel10.setFont(new Font(null, Font.PLAIN, 25));
        aNewLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPane.add(aNewLabel10);

    

        JButton editPassword = new JButton("Edit Password");
        editPassword.setBounds(900, 240, 200, 40);
        userInfoPane.add(editPassword);
        editPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditPassword ep = new EditPassword(currentaccount,jtb);
                ep.setTitle("Edit Password");
                ep.setLocation(800,300);
                ep.setVisible(true);

            }
        });

        
        JButton editEmail = new JButton("Edit E-mail");
        editEmail.setBounds(900, 340, 200, 40);
        userInfoPane.add(editEmail);
        editEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditEmail edt = new EditEmail(currentaccount,jtb);
                edt.setTitle("Edit E-mail");
                edt.setLocation(800,300);
                edt.setVisible(true);
                
            }
        });

        JButton editUserType = new JButton("Upgrade to VIP");
        editUserType.setBounds(900, 440, 200, 40);
        userInfoPane.add(editUserType);
        editUserType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(currentusertype.equals("SVIP")){
                JOptionPane.showMessageDialog(null, "You are already a SVIP user"); 
                }
                else{
                    EditUserType1 etu = new EditUserType1(currentaccount, jtb);
                    etu.setTitle("Upgrade to VIP");
                    etu.setLocation(400,100);
                    etu.setVisible(true);
                }
            }
        });






        return userInfoPane;
    }




    
}
