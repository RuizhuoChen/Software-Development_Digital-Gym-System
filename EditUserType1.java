import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

   /**
     * The window where user can choose which type of VIP they wanted
	 * Created on 2021/05/18
     * updated on 2021/05/21: modify the page, now have two type of user
	 * 
	 */


public class EditUserType1 extends JFrame{

    /**The path of the icon for vip*/  
    String vip1image = "images/vip1.jpg";   
    /**The path of the icon for svip*/  
    String svip1image = "images/svip1.jpg";            
    
    
    /**The JTabbedPane where the change of user type will be updated to*/
    JTabbedPane jt1;


    

    public EditUserType1(String currentaccount,JTabbedPane jtb) throws HeadlessException{
        JButton vip1;
        JButton vip2;
        JButton svip1; 
        jt1 = jtb;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 1000, 600);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //adding 3 components
        ImageIcon vipicon = new ImageIcon(vip1image);
        ImageIcon svipicon = new ImageIcon(svip1image);
        vip1 = new JButton("VIP1:50times of viewing video  20$", vipicon);
        vip1.setBounds(20, 30, 300, 400);
        //vip1.setIcon(vipicon);
        vip1.setHideActionText(true);
        vip1.setToolTipText("Click to upgrade to VIP user");
        vip1.setBorderPainted(false);
        vip1.setContentAreaFilled(false);
        vip1.setFocusPainted(false);
        vip1.setVerticalTextPosition(JButton.BOTTOM);
        vip1.setHorizontalTextPosition(JButton.CENTER);
        contentPane.add(vip1);

        
        vip2 = new JButton("VIP2:100 times of viewing video  30$", vipicon);
        vip2.setBounds(340, 30, 300, 400);
        vip2.setHideActionText(true);
        vip2.setToolTipText("25% Discount!!");
        vip2.setBorderPainted(false);
        vip2.setContentAreaFilled(false);
        vip2.setFocusPainted(false);
        vip2.setVerticalTextPosition(JButton.BOTTOM);
        vip2.setHorizontalTextPosition(JButton.CENTER);
        contentPane.add(vip2);

        svip1 = new JButton("SVIP:viewing video without limit  80$", svipicon);
        svip1.setBounds(660, 30, 300, 400);
        svip1.setHideActionText(true);
        svip1.setToolTipText("Viewing all video without limit");
        svip1.setBorderPainted(false);
        svip1.setContentAreaFilled(false);
        svip1.setFocusPainted(false);
        svip1.setVerticalTextPosition(JButton.BOTTOM);
        svip1.setHorizontalTextPosition(JButton.CENTER);
        contentPane.add(svip1);

        


        vip1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditUserType1.super.dispose();
                EditUserType2 eut2 = new EditUserType2(currentaccount,jt1,"vip1");
                eut2.setTitle("Payment page");
                eut2.setLocation(800,300);
                eut2.setVisible(true);

            }
        });
        

        vip2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditUserType1.super.dispose();
                EditUserType2 eut2 = new EditUserType2(currentaccount,jt1,"vip2");
                eut2.setTitle("Payment page");
                eut2.setLocation(800,300);
                eut2.setVisible(true);
            }
        });

        svip1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditUserType1.super.dispose();
                EditUserType2 eut2 = new EditUserType2(currentaccount,jt1,"svip");
                eut2.setTitle("Payment page");
                eut2.setLocation(800,300);
                eut2.setVisible(true);
            }
        });
        

    


        

    }

   
    
}

