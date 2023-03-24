import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.lang.Math;
/**
 * Title	: HomePanel.java
 * Description	: This class is primarily used to implement the creation of a Homepanel and includes methods to assist in the creation of its components.
 * @author	: Tongxin Ma
 * @version	: 3.0
 */

public class HomePanel extends Interface{
    
    public JPanel homePanel1 = new JPanel(null);
    /** This method is to split and return needed string.
   *  @param tabbedPane is used to let the button to call to switch the Tab
   *  @param LOGO a button use to contain the image of our logo.gif
   *  @param TR a button use to switch the Tab to Trainer
   *  @param VR a button use to switch the Tab to Videos
   *  @return Home panel 
   */
    public JPanel homePanel(JTabbedPane tabbedPane){
        //logo on the top
        JButton  LOGO = new JButton();
        ImageIcon icon1 = new ImageIcon("images/LOGO.gif");
        LOGO.setBounds(0, 0, 1280, 240);
        Image temp1 = icon1.getImage().getScaledInstance(LOGO.getWidth(), LOGO.getHeight(), icon1.getImage().SCALE_DEFAULT);  
        icon1 = new ImageIcon(temp1); 
        LOGO.setIcon(icon1);
        LOGO.setBorderPainted(false);
        LOGO.setFocusPainted(false);
        homePanel1.add(LOGO);
        //Trainer recoomendation navigation
        JButton TR = new JButton("Trainer Recommendation: (Click to show all trainers)");
        TR.setHorizontalAlignment(SwingConstants.LEFT);
        TR.setForeground(Color.BLACK);
        TR.setBounds(0, 460, 1280, 20);
        TR.setBorderPainted(false);
        TR.setFocusPainted(false);
        TR.setBackground(Color.ORANGE);
        TR.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tabbedPane.setSelectedIndex(2);
            }
        });
        homePanel1.add(TR);
        //Randomly recommend three trainers
        //Generate three different random numbers,use the method of getRandom()
        int end = readLine("texts/AllTrainer.txt");
        //catch an Exception if the line in the file is less than we need;
        try{
            fileLessline(end, 5);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //randomInt use to store the random numbers
        int[] randomInt = new int[5];
        randomInt[0]=getRandom(0, end-1);
        do{
            randomInt[1]= getRandom(0, end-1);
        }while(randomInt[1]==randomInt[0]);

        do{
            randomInt[2]= getRandom(0, end-1);
        }while(randomInt[2]==randomInt[1]||randomInt[2]==randomInt[0]); 
        do{
            randomInt[3]= getRandom(0, end-1);
        }while(randomInt[3]==randomInt[0]||randomInt[3]==randomInt[1]||randomInt[3]==randomInt[2]);
        do{
            randomInt[4]= getRandom(0, end-1);
        }while(randomInt[4]==randomInt[3]||randomInt[4]==randomInt[2]||randomInt[4]==randomInt[1]||randomInt[4]==randomInt[0]);

        //Extract the information from the file, use the loop to make a button
        String[][] trainersInfo = readFromFile("texts/AllTrainer.txt");
        //GUILayout , BoxX is the The abscissa of the button
        int BoxX = 53 ; 
        for(int i=0; i<trainersInfo.length; i++){       
            String trainerName = trainersInfo[i][0];
            String trainerType = trainersInfo[i][1];
            String imagesPath = trainersInfo[i][2];
            String intro = trainersInfo[i][3];
            // Problem intro String can't be too long
            if(i==randomInt[0]||i==randomInt[1]||i==randomInt[2]||i==randomInt[3]||i==randomInt[4]){
            ImageIcon icon = new ImageIcon(trainersInfo[i][2]);
            JButton trainerBtn = new JButton(trainerName + ": " + trainerType, icon); 
            // trainerBtn.setMaximumSize(new Dimension(30,20)); 
            trainerBtn.setSize(150, 180);
            Image temp = icon.getImage().getScaledInstance(trainerBtn.getWidth(), trainerBtn.getHeight(), icon.getImage().SCALE_DEFAULT);  
            icon = new ImageIcon(temp); 
            trainerBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new BookInfo(trainerName, trainerType, imagesPath, intro);
                }
            });
            
            trainerBtn.setIcon(icon);
            trainerBtn.setBackground(Color.WHITE);
            trainerBtn.setToolTipText("Click to Show Detail Information");
            trainerBtn.setBorderPainted(false);
            trainerBtn.setFocusPainted(false);
            trainerBtn.setVerticalTextPosition(JButton.BOTTOM);
            trainerBtn.setHorizontalTextPosition(JButton.CENTER);
            trainerBtn.setBounds(BoxX,480,150,200);
            //the gap between the two buttons is 420
            BoxX+=256;
            homePanel1.add(trainerBtn);
            }  
        }
        //Videos recommendation naviagtion
        JButton VR = new JButton("Videos Recommendation: (Click to show all videos)");
        VR.setHorizontalAlignment(SwingConstants.LEFT);
        VR.setForeground(Color.BLACK);
        VR.setBounds(0, 240, 1280, 20);
        VR.setBorderPainted(false);
        VR.setFocusPainted(false);
        VR.setBackground(Color.ORANGE);;
        VR.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tabbedPane.setSelectedIndex(1);
            }
        });
        homePanel1.add(VR);
        //Randomly recommend three Videos
        //Generate three different random numbers,use the method of getRandom()
        int end1 = readLine("texts/AllVideo.txt");
        //catch an Exception if the line in the file is less than we need;
        try{
            fileLessline(end1, 3);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        int[] randomInt1 = new int[3];
        randomInt1[0]=getRandom(0, end1-1);
        do{
            randomInt1[1]= getRandom(0, end1-1);
        }while(randomInt1[1]==randomInt1[0]);

        do{
            randomInt1[2]= getRandom(0, end1-1);
        }while(randomInt1[2]==randomInt1[1]||randomInt1[2]==randomInt1[0]);
        //Extract the information from the file
        String[][] allCourse = readFromFile("texts/AllVideo.txt");
        int rowLength= allCourse.length;
        //BoxX1 is is the abscissa of the buttons
        int BoxX1 = 75;
        //use loop to make videos button
        for(int i=0; i<rowLength; i++){
            JButton btn = new JButton(allCourse[i][0]  + ": "+ allCourse[i][1] + " mins");
            Button_Back(btn,allCourse[i][4],allCourse[i][5]);
            String videoName = allCourse[i][0];
            String videoPath = allCourse[i][3];
            String videoVip = allCourse[i][5];
                
            if(i==randomInt1[0]||i==randomInt1[1]||i==randomInt1[2]){ 
                btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                        String[][] userInfor=new String[1][5];
                        userInfor=readFromFile("texts/currentuser.txt");
                        int leftNum = Integer.parseInt(userInfor[0][4]);
    
                        //video is "2"
                        if(videoVip.equals("2")){
                            if(userInfor[0][3].equals("normal")||userInfor[0][3].equals("VIP")){
                                JOptionPane.showMessageDialog(HomePanel.super.rootPane, "You are not a SVIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
                            }else
                               {playVideo(videoPath);}
                        }
    
                        //video is "1"
                        if(videoVip.equals("1")){
                        if(userInfor[0][3].equals("normal")){
                            JOptionPane.showMessageDialog(HomePanel.super.rootPane, "You are not a VIP customer!","Warning!",JOptionPane.WARNING_MESSAGE);
                        }
                        if(userInfor[0][3].equals("VIP")){
                            // left num --
                            if(leftNum>0)
                                    {   
                                        playVideo(videoPath);
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
                                        JOptionPane.showMessageDialog(HomePanel.super.rootPane, "You have no try left !","Warning!",JOptionPane.WARNING_MESSAGE);
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
                btn.setBounds(BoxX1, 260, 270, 200);
                homePanel1.add(btn);
                BoxX1+= 420;
            }   
        }
        // homePanel1.setBackground(Color.WHITE);
        return homePanel1; 
    }
    /** This method is to generate random number , return an integer.
    *  @param start is the interval of random numbers begins
    *  @param end is the end of interval of random number
    *  @return a random number between start and end  
    */
    
    public static int getRandom(int start, int end){
        return (int)(Math.random() * (end-start+1) + start);
    }

    /** This method is to defines the properties of the button.
    */
    public static void Button_Back(JButton Button,String ImagePath,String videoVip){
        Button.setBounds(0, 0, 270, 180);
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
    /** This method is to throw the exception if the line in the file is less than we need.
    *  @param fileLine is the line of the file
    *  @param needLine is the line we need 
    *  @return a random number between start and end  
    */
    public void fileLessline(int fileLine , int needLine) throws Exception{
        if (fileLine<needLine)
            throw new Exception("the message in file is less than your need!");
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomePanel ui = new HomePanel();
        //frame.add(ui.homePanel());
        frame.setVisible(true);
    }
}