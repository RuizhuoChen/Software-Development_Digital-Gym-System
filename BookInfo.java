import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** 
 *  Trainer detail information includes Name, Type, Introduction
 *  User can book the trainer by clicking the book button
 */
public class BookInfo extends Interface {

    /** Frame to show setVisible() by all methods */
    JFrame frame = new JFrame();

    public BookInfo(String trainerName, String trainerType, String imagePath, String intro){
        frame.setTitle("Trainer Detail Infomation");
        frame.pack();
        frame.setSize(1280, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(trainerDetailInfo(trainerName, trainerType, imagePath, intro));
    }

    /**
     * Trainer detail information includes Name, Type, Introduction
     * Also including book button and cancel button
     * @param trainerName
     * @param trainerType
     * @param imagePath
     * @param intro
     * @return JPanel, contains trainer detail information and buttons
     */
    public JPanel trainerDetailInfo(String trainerName, String trainerType, String imagePath, String intro){
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel imagesPanel = new JPanel();
        JPanel textPanel = new JPanel(new BorderLayout());
        JPanel blankPanel = new JPanel();

        JButton bookBtn = new JButton("Click to Book Trainer");
        JButton returnBtn = new JButton("Return");

        JPanel namePanel = trainerName(trainerName);
        JPanel typePanel = trainerType(trainerType);
        namePanel.add(typePanel);
        
        blankPanel.setPreferredSize(new Dimension(10, 20));
        blankPanel.setBackground(Color.WHITE);

        // Add TextArea to show trainer introduction
        JTextArea textArea = new JTextArea("    " + intro);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Microsoft Serif Pro", Font.PLAIN, 30));
        textArea.setBackground(Color.WHITE);

        typePanel.add(textArea);
        textPanel.setBackground(Color.WHITE);
        textPanel.add(namePanel, BorderLayout.NORTH);

        bookBtn.setSize(50,50);
        bookBtn.setBackground(Color.YELLOW);
        bookBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Check VIP
                String userType = readCurrentUser().split(",")[3];
                if(userType.equals("normal")){
                    JOptionPane.showMessageDialog(null, "You are not a VIP user, please upgrade to VIP", "Can't Book Trainer",JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    new BookConfirm(trainerName, trainerType); // New a frame to select time to book
                }
            }
        });

        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.add(bookBtn, BorderLayout.CENTER);

        returnBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false); // Close current window
            }
        });
        returnBtn.setContentAreaFilled(false);

        btnPanel.add(returnBtn, BorderLayout.EAST);
        btnPanel.add(blankPanel, BorderLayout.SOUTH);
        
        textPanel.add(btnPanel, BorderLayout.SOUTH);

        imagesPanel.add(trainerPicture(imagePath));
        imagesPanel.setBackground(Color.WHITE);

        infoPanel.add(imagesPanel, BorderLayout.WEST);
        infoPanel.add(textPanel, BorderLayout.CENTER);

        return infoPanel;
    }

    /**
	 * Set picture in button
     * @param imagePath
	 * @return JButton, it's used as a picture
	 */
    public JButton trainerPicture(String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        JButton trainerBtn = new JButton(icon);
        trainerBtn.setMaximumSize(new Dimension(600,828));
        trainerBtn.setIcon(icon);
        trainerBtn.setHideActionText(true);
        trainerBtn.setToolTipText("Click to Show Detail Information");
        trainerBtn.setBorderPainted(false);
        trainerBtn.setContentAreaFilled(false);
        trainerBtn.setFocusPainted(false);
        trainerBtn.setVerticalTextPosition(JButton.BOTTOM);
        trainerBtn.setHorizontalTextPosition(JButton.CENTER);
        return trainerBtn;
    }

    /**
	 * Set trainer name panel format
     * @param trainerName
	 * @return JPanel
	 */
    public JPanel trainerName(String trainerName){
        JPanel trainerNamePanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(trainerName);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(null, Font.BOLD, 150));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        trainerNamePanel.add(label, BorderLayout.NORTH);
        trainerNamePanel.setBackground(Color.DARK_GRAY);
        return trainerNamePanel;
    }

    /**
	 * Set trainer type panel format
     * @param trainerType
	 * @return JPanel
	 */
    public JPanel trainerType(String trainerType){
        JPanel trainerTypePanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(trainerType + "   ");
        label.setForeground(Color.BLACK);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        trainerTypePanel.add(label, BorderLayout.NORTH);
        trainerTypePanel.setBackground(Color.LIGHT_GRAY);
        return trainerTypePanel;
    }

    /**
	 * Read current user
     * @return current user name
	 */
    public String readCurrentUser(){
        String userInfo = "";
        try{
            FileReader fileReader = new FileReader("texts/currentuser.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
                userInfo += oneLine;
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println("Errors occured: IOException!");
            System.exit(1);
        }     
        return userInfo;
    }
}

