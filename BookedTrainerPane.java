import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.awt.*;
import java.io.IOException;
import java.io.*;

/** 
 *  Created a JPanel to contain  information about all the trainer that was booked by a given user
 *  Created on 2021/5/18:The JPane contain no.2 part of booked Trainer
 * 
 */

public class BookedTrainerPane extends Interface{

    /**The JPanel that displayed the booked trainer*/
    public JPanel bookedTrainerPane;
    /**The account name of the user*/
    public String currentaccount = null;
    /**The string array that contains all the booked trainer information*/
    public String[][] lessonInfo;

    /**
	 * Created a JPanel to contain  information about all the trainer that was booked by a given user
	 * @param  JTabbedPane jtb, jtb is the JTabbedPane that will contain this JPanel
	 * @return JPanel, the created JPanel
	 */
    public JPanel makeBookedTrainerPane(JTabbedPane jtb){
        
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
		    br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

        //gettring booked trainer information for all users
        lessonInfo = readFromFile("texts/BookInfo.txt");

        //initialize the JPanel 
        int rows = 0;
        for(int i=0; i<lessonInfo.length; i++){
            String userAccount = lessonInfo[i][0];
            if(userAccount.equals(currentaccount)){
                rows++;
            }
        }

        bookedTrainerPane = new JPanel(new GridLayout(rows+1, 5));
        bookedTrainerPane.setBounds(0, 0, 1200, 720);

        JLabel title1 = new JLabel("User");
        title1.setFont(new Font(null, Font.PLAIN, 19));
        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title1.setBorder(BorderFactory.createLineBorder(Color.black));
        bookedTrainerPane.add(title1);

        JLabel title2 = new JLabel("Trainer");
        title2.setFont(new Font(null, Font.PLAIN, 19));
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setBorder(BorderFactory.createLineBorder(Color.black));
        bookedTrainerPane.add(title2);
    
        JLabel title3 = new JLabel("Lesson Type");
        title3.setFont(new Font(null, Font.PLAIN, 19));
        title3.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setBorder(BorderFactory.createLineBorder(Color.black));
        bookedTrainerPane.add(title3);

        JLabel title4 = new JLabel("Booked Date");
        title4.setFont(new Font(null, Font.PLAIN, 19));
        title4.setHorizontalAlignment(SwingConstants.CENTER);
        title4.setBorder(BorderFactory.createLineBorder(Color.black));
        bookedTrainerPane.add(title4);

        JLabel title5 = new JLabel("Booked Hours");
        title5.setFont(new Font(null, Font.PLAIN, 19));
        title5.setHorizontalAlignment(SwingConstants.CENTER);
        title5.setBorder(BorderFactory.createLineBorder(Color.black));
        bookedTrainerPane.add(title5);


        //display the lesson booked by current user
        for(int i=0; i<lessonInfo.length; i++){
            String userAccount = lessonInfo[i][0];
            //display the lesson booked by current user
            if(userAccount.equals(currentaccount)){
                //user account
                JLabel account1 = new JLabel(lessonInfo[i][0]);
                account1.setFont(new Font(null, Font.PLAIN, 15));
                account1.setHorizontalAlignment(SwingConstants.CENTER);
                bookedTrainerPane.add(account1);
                //triner Name
                JLabel trainer1 = new JLabel(lessonInfo[i][1]);
                trainer1.setFont(new Font(null, Font.PLAIN, 15));
                trainer1.setHorizontalAlignment(SwingConstants.CENTER);
                bookedTrainerPane.add(trainer1);
                //trainer Type
                JLabel type1 = new JLabel(lessonInfo[i][2]);
                type1.setFont(new Font(null, Font.PLAIN, 15));
                type1.setHorizontalAlignment(SwingConstants.CENTER);
                bookedTrainerPane.add(type1);
                //booked day
                JLabel day1 = new JLabel(lessonInfo[i][3]);
                day1.setFont(new Font(null, Font.PLAIN, 15));
                day1.setHorizontalAlignment(SwingConstants.CENTER);
                bookedTrainerPane.add(day1);
                //booked hours
                JLabel hours1 = new JLabel(lessonInfo[i][4]);
                hours1.setFont(new Font(null, Font.PLAIN, 15));
                hours1.setHorizontalAlignment(SwingConstants.CENTER);
                bookedTrainerPane.add(hours1);
            }
        }
        
       return bookedTrainerPane;

    }


    
}
