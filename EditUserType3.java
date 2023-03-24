import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

   /**
     * The window indicating a successful VIP upgrading
	 * Created on 2021/05/18
	 * updated on 2021/05/21: modify the page, now have two type of user 
	 */


public class EditUserType3 extends JFrame{

     
              
    
    
    /**The JTabbedPane where the change of user type will be updated to*/
    JTabbedPane jt1;


    public EditUserType3(String currentaccount,JTabbedPane jtb) throws HeadlessException{
        JButton confirm;
        JLabel display; 
        jt1 = jtb;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 300, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        confirm = new JButton("OK");
        confirm.setBounds(80, 160, 100, 30);               // adding the Confirm Button
        contentPane.add(confirm);


        display = new JLabel("You are a VIP member now!");
        display.setBounds(50, 40, 220,120);            
        contentPane.add(display);


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditUserType3.super.dispose();

            }
        });

   
    


        

    }

  
    
}
