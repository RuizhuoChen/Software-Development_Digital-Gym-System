import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/** 
 *  Created a JPanel that contains search tab and all trainers
 *  Search Button can search trainer name and show in current panel
 *  All trainers' picture can be clicked to show detail information
 */

public class TrainerPanel extends Interface {

    /** The JPanel that displayed all trainers */
    JPanel trainerPanel = new JPanel(new BorderLayout());
    
    public TrainerPanel(){}

    /**
	 * Created a Trainer JPanel contains search panel and trainers panel
	 * @return JPanel, to be add to Scroll Panel
	 */
    public JPanel trainerPanel(){
        try {
            File file = new File("texts/AllTrainer.txt");
            if(!file.exists()){
                new AllCourse();
            }
        } catch (Exception e) {
            System.out.println("Create Base Enviroment Error!");
        }
        
        trainerPanel.add(searchPanel(), BorderLayout.NORTH);
        trainerPanel.add(trainersPanel(), BorderLayout.CENTER);
        return trainerPanel;
    }
    
    /**
	 * Created a Search JPanel can search by trainer's part or full name
	 * @return JPanel, to be add to trainer panel
	 */
    public JPanel searchPanel(){
        JPanel searchPanel = new JPanel();
        JTextField textField = new JTextField(20);

        ImageIcon icon = new ImageIcon("images/search.png");
        JButton searchBtn = new JButton(icon);

        searchBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String[][] searchTrainers = searchCourse("texts/AllTrainer.txt", textField.getText());
               trainerPanel.removeAll(); // Clear trainerPanel all components
               trainerPanel.add(searchPanel(), BorderLayout.NORTH);
               trainerPanel.add(refreshTrainerPanel(searchTrainers), BorderLayout.CENTER);
               trainerPanel.revalidate(); // Refresh Panel
            }
        });

        searchBtn.setMaximumSize(new Dimension(32,32));
        searchBtn.setIcon(icon);
        searchBtn.setHideActionText(true);
        searchBtn.setToolTipText("Click to Search");
        searchBtn.setBorderPainted(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setFocusPainted(false);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setForeground(Color.WHITE);
        searchPanel.add(searchLabel);
        searchPanel.add(textField);
        searchPanel.add(searchBtn);
        searchPanel.setBackground(Color.DARK_GRAY);
        return searchPanel;
    }

    /**
	 * Created a Trainers JPanel contains all trainers
	 * @return JPanel, to be add to trainer panel
	 */
    public JPanel trainersPanel(){
        
        int rows = readLine("texts/AllTrainer.txt"); // Trainer Number
        if(rows%3==0){
            rows /= 3;
        }
        else{
            rows = rows/3 + 1;
        }
        JPanel trainersPanel = new JPanel(new GridLayout(rows, 3, 5, 5)); // Column is 3 and gap is 5

        String[][] trainersInfo = readFromFile("texts/AllTrainer.txt");

        for(int i=0; i<trainersInfo.length; i++){
            String trainerName = trainersInfo[i][0];
            String trainerType = trainersInfo[i][1];
            String imagesPath = trainersInfo[i][2];
            String intro = trainersInfo[i][3];

            ImageIcon icon = new ImageIcon(trainersInfo[i][2]);
            JButton trainerBtn = new JButton(trainerName + ": " + trainerType, icon);

            trainerBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new BookInfo(trainerName, trainerType, imagesPath, intro);  // Generate trainers' information
                }
            });

            trainerBtn.setMaximumSize(new Dimension(600,828));
            trainerBtn.setIcon(icon);
            trainerBtn.setBackground(Color.WHITE);
            trainerBtn.setHideActionText(true);
            trainerBtn.setToolTipText("Click to Show Detail Information");
            trainerBtn.setBorderPainted(false);
            trainerBtn.setFocusPainted(false);
            trainerBtn.setVerticalTextPosition(JButton.BOTTOM);
            trainerBtn.setHorizontalTextPosition(JButton.CENTER);
            trainersPanel.add(trainerBtn);
        }

        return trainersPanel;
    }

    /**
	 * Created a new Trainers JPanel contains all trainers when clicked search button
     * @param searchTrainers contains eligible trainers' all information
	 * @return JPanel, to be refresh to add to trainer panel
	 */
    public JPanel refreshTrainerPanel(String[][] searchTrainers){
        
        int rows = searchTrainers.length; // Trainer Number
        if(rows%3==0){
            rows /= 3;
        }
        else{
            rows = rows/3 + 1;
        }
        JPanel trainersPanel = new JPanel(new GridLayout(rows, 3, 5, 5)); // Column is 3 and gap is 5

        for(int i=0; i<searchTrainers.length; i++){
            String trainerName = searchTrainers[i][0];
            String trainerType = searchTrainers[i][1];
            String imagesPath = searchTrainers[i][2];
            String intro = searchTrainers[i][3];

            ImageIcon icon = new ImageIcon(searchTrainers[i][2]);
            JButton trainerBtn = new JButton(trainerName + ": " + trainerType, icon);

            trainerBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new BookInfo(trainerName, trainerType, imagesPath, intro); // Generate trainers' information
                }
            });

            trainerBtn.setMaximumSize(new Dimension(600,828));
            trainerBtn.setIcon(icon);
            trainerBtn.setBackground(Color.WHITE);
            trainerBtn.setHideActionText(true);
            trainerBtn.setToolTipText("Click to Show Detail Information");
            trainerBtn.setBorderPainted(false);
            trainerBtn.setFocusPainted(false);
            trainerBtn.setVerticalTextPosition(JButton.BOTTOM);
            trainerBtn.setHorizontalTextPosition(JButton.CENTER);
            trainersPanel.add(trainerBtn);
        }

        
        return trainersPanel;
    }

    /**
	 * Make Trainer Panel to scrool
	 * @return JScrollPane, to be add to Main Panel
	 */
    public JScrollPane scrollPanel(){
        JScrollPane scrollPanel = new JScrollPane(
                trainerPanel(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        return scrollPanel;
    }


}
