import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** 
* Created the JTabbedPane that contain 3 user parts of user information
* @since  2021/5/18
*  
*/


public class UserTabbedPane extends Interface{
    /**the JTabbedPane called UserTabbedPane that will be created */
    public JTabbedPane userTabbedPane;


  /**
	 * Created a JTabedPane to contain 3 parts of user information
	 * @param  JTabbedPane, the JTabbedPane contain this JTabbedPane
	 * @return JTabbedPane, the created JTabbedPane
	 */
    public JTabbedPane userTabbedPane(JTabbedPane main){
        //initialize the JTabbedpanel
        userTabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
        
        //Adding 3 Pane

        //No.1 User information
        ImageIcon icon = new ImageIcon("images/middle.gif");
        UserInfoPane uip = new UserInfoPane();
        userTabbedPane.addTab("User Info", icon,uip.makeUserInfoPane(userTabbedPane));

        //No.2 Booked Trainer Information
        BookedTrainerPane btp = new BookedTrainerPane();
        userTabbedPane.addTab("BookedTrainer", icon,btp.makeBookedTrainerPane(userTabbedPane));

        //No.3 Recommendation Page
        RecommendationPane rdp = new RecommendationPane();
        userTabbedPane.addTab("Recommendation", icon,rdp.makeRecomPane(userTabbedPane));

        //refresh the user info page every time the user enter it
        userTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                UserInfoPane uip = new UserInfoPane();
                userTabbedPane.setComponentAt(0, uip.makeUserInfoPane(userTabbedPane));
                BookedTrainerPane btp = new BookedTrainerPane();
                userTabbedPane.setComponentAt(1, btp.makeBookedTrainerPane(userTabbedPane));
            }
        });

        main.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                UserInfoPane uip = new UserInfoPane();
                userTabbedPane.setComponentAt(0, uip.makeUserInfoPane(userTabbedPane));
                BookedTrainerPane btp = new BookedTrainerPane();
                userTabbedPane.setComponentAt(1, btp.makeBookedTrainerPane(userTabbedPane));
            }
        });

        

        return userTabbedPane;
        
    }
    



}
