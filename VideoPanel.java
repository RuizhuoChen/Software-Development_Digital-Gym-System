import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/** 
 *  Created a JPanel that contains search tab and all courses
 *  Search Button can search all videos and show in current panel
 * The first button is used to present all courses in the GYM.
 *  Two more buttons can be used to classify on types and on vip/svip. 
 * When clicking the video, it can play the video on your local video player.
 */

public class VideoPanel extends Interface{
    /**The file that contains all information about courses */
    String fileName = "texts/AllVideo.txt";
    /** The JPanel that displayed all courses */
    JPanel videoPanel = new JPanel(new BorderLayout());

    public VideoPanel(){}

    /**
	 * Created a Video JPanel contains search panel and videos panel
	 * @return JPanel, to be add to Scroll Panel
	 */
    public JPanel videoPanel(){

    videoPanel.add(searchPanel(), BorderLayout.NORTH);
    videoPanel.add(coursePanel(), BorderLayout.CENTER);
        
    return videoPanel;    
    }

    /**
	 * Created a Search JPanel can search by videos' part or in full-name
	 * @return JPanel, to be add to video panel
	 */
    public JPanel searchPanel(){
        JPanel searchPanel = new JPanel();
        JTextField textField = new JTextField(20);

        ImageIcon icon = new ImageIcon("images/search.png");
        JButton searchBtn = new JButton(icon);

        searchBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String input = textField.getText();
            String[][] searchResult=searchCourse("texts/AllVideo.txt",input);
            if(input.equals("")) {
                JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "Search can't be empty!","Warning!",JOptionPane.WARNING_MESSAGE);}
            else{
                videoPanel.removeAll();// Clear trainerPanel all components
                videoPanel.add(searchPanel(), BorderLayout.NORTH);
                videoPanel.add(refreshVideoPanel(searchResult), BorderLayout.CENTER);
                videoPanel.revalidate();// Refresh Panel
            }   
        }
        });
        searchBtn.setMaximumSize(new Dimension(32,32));
        searchBtn.setIcon(icon);
        searchBtn.setHideActionText(true);
        searchBtn.setToolTipText("Click to Search");
        searchBtn.setBorderPainted(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setFocusPainted(false);

        //The information about the courses after classified by type attributes
        String[][] tagVideo = classifyByTag("texts/AllVideo.txt");

        //The button that used to present all videos in the GYM
        JButton AllBtn =new JButton("All courses");
        //all information about video in the text
        String[][] allvideo = readFromFile("texts/AllVideo.txt");
        AllBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    videoPanel.removeAll();// Clear trainerPanel all components
                    videoPanel.add(searchPanel(), BorderLayout.NORTH);
                    videoPanel.add(refreshVideoPanel(allvideo), BorderLayout.CENTER);
                    videoPanel.revalidate();// Refresh Panel
            }
        });


        //The button used for classifing by types
        JButton tagBtn =new JButton("Classsified");
        tagBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    videoPanel.removeAll();// Clear trainerPanel all components
                    videoPanel.add(searchPanel(), BorderLayout.NORTH);
                    videoPanel.add(refreshVideoPanel(tagVideo), BorderLayout.CENTER);
                    videoPanel.revalidate();// Refresh Panel
            }
        });


        //The information about the courses after classified by the vip attribute
        String[][] vipVideo = classifyByVip();
        JButton vipBtn =new JButton("Vip");
        vipBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //Used for storing user's information
            String[][] userInfor=new String[1][4];
            userInfor=readFromFile("texts/currentuser.txt");
            if(userInfor[0][3].equals("normal")){
                JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You are not a VIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
            }else{
                videoPanel.removeAll();
                videoPanel.add(searchPanel(), BorderLayout.NORTH);
                videoPanel.add(refreshVideoPanel(vipVideo), BorderLayout.CENTER);
                videoPanel.revalidate();}
            }
        });
       
        searchPanel.add(textField);
        searchPanel.add(searchBtn);
        searchPanel.add(AllBtn);
        searchPanel.add(tagBtn);
        searchPanel.add(vipBtn);
        searchPanel.setBackground(Color.DARK_GRAY);
        return searchPanel;
    }
    

     /**
	 * Created a Videos JPanel contains all courses could be played
	 * @return JPanel, to be add to video panel
	 */
    public JPanel coursePanel(){

        int rows = readLine("texts/AllVideo.txt"); // Trainer Number
        if(rows%3==0){
            rows /= 3;
        }
        else{
            rows = rows/3 + 1;
        }
        
        JPanel coursePanel=new JPanel();
        coursePanel.setLayout(new FlowLayout());
        
        
        coursePanel.setPreferredSize(new Dimension(1000, rows*250));
        String[][] allCourse = readFromFile("texts/AllVideo.txt");
        int rowLength= allCourse.length;
         

        // Generate Total Button for each course
        for(int i=0; i<rowLength; i++){

            JButton btn = new JButton(allCourse[i][0]  + ": "+ allCourse[i][1] + " mins");
            Button_Back(btn,allCourse[i][4],allCourse[i][5]);
            String videoName = allCourse[i][0];
            String videoPath = allCourse[i][3];
            String videoVip=allCourse[i][5];
                
                
            btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            // System.out.println("This button is clicked.");
            String[][] userInfor=new String[1][5];
            userInfor=readFromFile("texts/currentuser.txt");
            int leftNum = Integer.parseInt(userInfor[0][4]);
            //video is "2"
            if(videoVip.equals("2")){
            if(userInfor[0][3].equals("normal")||userInfor[0][3].equals("VIP")){
                JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You are not a SVIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
            }else
            {playVideo(videoPath);}
            }

            //video is "1"
            if(videoVip.equals("1")){
            if(userInfor[0][3].equals("normal")){
                JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You are not a VIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
            }
            if(userInfor[0][3].equals("VIP")){
            // left num --
            if(leftNum>0)
            {   playVideo(videoPath);
            leftNum--;
            //Recored the change in currentuser.txt and member.txt
            List<Member> members = Util.readFile();
            for (Member member: Objects.requireNonNull(members)) {
                if (member.getAccount().equals(userInfor[0][0])){
                    member.setVideoTimes(leftNum); 
                    Util.writeFile(members);         
                    Util.recordCurrentUser(member);
                }
            } 
            }else{
                JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You have no try left !","Warning!",JOptionPane.WARNING_MESSAGE);
            }}
                if(userInfor[0][3].equals("SVIP")){
                playVideo(videoPath);
                }
            }

           //video is "0"
              if(videoVip.equals("0")){
    
                playVideo(videoPath);
                }
                }
                });
             coursePanel.add(btn);
            }
       

        return coursePanel;
    }


    /**
	 * Created a new Videos JPanel contains all courses when clicked search button
     * @param searchResult contains eligible videos' all information
	 * @return JPanel, to be refresh to add to video panel
	 */

    public JPanel refreshVideoPanel(String[][] searchResult){

        int rows = readLine("texts/AllVideo.txt"); // Trainer Number
        if(rows%3==0){
            rows /= 3;
        }
        else{
            rows = rows/3 + 1;
        }
        
        
        JPanel coursePanel=new JPanel();
        coursePanel.setLayout(new FlowLayout());
        
        
        coursePanel.setPreferredSize(new Dimension(1000, rows*250));

        for(int i=0; i<searchResult.length; i++){
            String videoName = searchResult[i][0];
            String videoTime = searchResult[i][1];
            String videoType = searchResult[i][2];
            String videoPath = searchResult[i][3];
            String videoPicture = searchResult[i][4];
            String videoVip = searchResult[i][5];

            JButton btn = new JButton(videoName+ ": "+ videoTime + " mins");
            Button_Back(btn,videoPicture,videoVip);
            
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    // System.out.println("This button is clicked.");
                    String[][] userInfor=new String[1][5];
                    userInfor=readFromFile("texts/currentuser.txt");
                    int leftNum = Integer.parseInt(userInfor[0][4]);

                    //video is "2"
                    if(videoVip.equals("2")){
                        if(userInfor[0][3].equals("normal")||userInfor[0][3].equals("VIP")){
                            JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You are not a SVIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
                        }else
                           {playVideo(videoPath);}
                    }

                    //video is "1"
                    if(videoVip.equals("1")){
                    if(userInfor[0][3].equals("normal")){
                        JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You are not a VIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
                    }
                    if(userInfor[0][3].equals("VIP")){
                        // left num --
                        if(leftNum>-0)
                                {   playVideo(videoPath);
                                    leftNum--;
                                    //Recored the change in currentuser.txt and member.txt
                                    List<Member> members = Util.readFile();
                                    for (Member member: Objects.requireNonNull(members)) {
                                        if (member.getAccount().equals(userInfor[0][0])){
                                            member.setVideoTimes(leftNum); 
                                            Util.writeFile(members);         
                                            Util.recordCurrentUser(member);
                                         }
                                    } 
                                }else{
                                    JOptionPane.showMessageDialog(VideoPanel.super.rootPane, "You have no try left !","Warning!",JOptionPane.WARNING_MESSAGE);
                            }}
                    if(userInfor[0][3].equals("SVIP")){
                        playVideo(videoPath);
                    }
                    }

                    //video is "0"
                    if(videoVip.equals("0")){
                        
                        playVideo(videoPath);
                    }
                    }
            });

            coursePanel.add(btn);
            }
            return coursePanel;
        }

        
    /**
	 * Used to generate the video frame, whcin can be used to click and play the particular course
     * @param Button the course's button
     * @param ImagePath the picture used by the button
     * @param videoVip which presents whether the video is normal, vip or svip
	 * @return
	 */

    public static void Button_Back(JButton Button,String ImagePath,String videoVip){
        Button.setBounds(0, 0, 300, 200);
        ImageIcon imageIcon = new ImageIcon(ImagePath);
        Image suitablImage = imageIcon.getImage().getScaledInstance(Button.getWidth(), Button.getHeight(), imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(suitablImage);
        Button.setIcon(imageIcon);
        if(videoVip.equals("2")){
            Button.setToolTipText("SVIP");
        }
        if(videoVip.equals("1")){
           Button.setToolTipText("VIP");
        }
        if(videoVip.equals("0")){Button.setToolTipText("Normal");}
        Button.setBackground(Color.white);
        Button.setBorderPainted(false);
        Button.setFocusPainted(false);
        Button.setVerticalTextPosition(JButton.BOTTOM);
        Button.setHorizontalTextPosition(JButton.CENTER);
    }

    /**
	 * Make Video Panel to scrool
	 * @return JScrollPane, to be add to Main Panel
	 */
    public JScrollPane scrollPanel(){
        JScrollPane scrollPanel = new JScrollPane(
                videoPanel(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        return scrollPanel;
    }
    
}
