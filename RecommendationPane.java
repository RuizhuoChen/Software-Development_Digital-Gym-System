import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/** 
 *  Created a JPanel that contains the Recommendation window
 *  Created on 2021/5/18:The JPane contain no.3 part of Recommendation activity
 *  Updated on 2021/5/21:Change some of the font
 * 
 */
public class RecommendationPane {
    /**The JPanel that provided the recommendation interface and functinality*/
    public JPanel recommendationPane;
    /**The account name of the user*/
    public String currentaccount;


    /**
	 * Created a JPanel that contains the Recommendation window
	 * @param  JTabbedPane jtb, jtb is the JTabbedPane that will contain this JPanel
	 * @return JPanel, the created JPanel
	 */
    public JPanel makeRecomPane(JTabbedPane jtb){
        //initialize
        recommendationPane = new JPanel();
        recommendationPane.setLayout(null);
        recommendationPane.setBounds(0, 0, 1200, 800);
        recommendationPane.setBackground(java.awt.Color.white);

        
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

        JLabel title = new JLabel("Recommendation");
        title.setFont(new Font("Dialog", Font.PLAIN, 45));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(400, 10, 400, 120);
        recommendationPane.add(title);

        //adding components
        JLabel aNewLabel1 = new JLabel("Height(cm):");
	        aNewLabel1.setBounds(394, 154, 138, 54);
            aNewLabel1.setFont(new Font(null, Font.PLAIN, 18));
            recommendationPane.add(aNewLabel1);

	        JLabel aNewLabel2 = new JLabel("Weight(kg):");
	        aNewLabel2.setBounds(394, 234, 138, 54);
            aNewLabel2.setFont(new Font(null, Font.PLAIN, 18));
            recommendationPane.add(aNewLabel2);

	        JLabel aNewLabel3 = new JLabel("Target:");
	        aNewLabel3.setBounds(394, 314, 138, 54);
            aNewLabel3.setFont(new Font(null, Font.PLAIN, 18));
            recommendationPane.add(aNewLabel3);

            JTextField textField1 = new JTextField();
	        textField1.setBounds(542, 163, 218, 37);
	        recommendationPane.add(textField1);
	        textField1.setColumns(10);

	        JTextField textField2 = new JTextField();
	        textField2.setBounds(542, 243, 218, 37);
	        textField2.setColumns(10);
	        recommendationPane.add(textField2);

            JComboBox<String> comboBox1 = new JComboBox<String>();
	        comboBox1.setBounds(542, 330, 221, 38);
	        recommendationPane.add(comboBox1);
	        comboBox1.addItem("Gain-muscle");
	        comboBox1.addItem("Fat-Loss");

            JButton recommend = new JButton("Recommed");
	        recommend.setBounds(509, 459, 118, 53);
	        recommendationPane.add(recommend);

            //getting trainer information from AllTrainer.txt
            String[][] trainersInfo = Util.readFromFile("texts/AllTrainer.txt");

            recommend.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //check whether all field have value
                    if(textField1.getText().equals("")||textField2.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "The height and weight can not be empty");
                    }
                   //recommend directly based on Target
                else{
                    // Get height and weight, then calculate BMI
                    double height = Double.parseDouble(textField1.getText());
                    double weight = Double.parseDouble(textField2.getText());
                    double BMI = calBMI(height, weight);
                    DecimalFormat df = new DecimalFormat("#.00");

                    String target = (String) comboBox1.getSelectedItem();
                    if(target.equals("Gain-muscle")){
                        //randomly recommend a Trainer of catagory Gain-muscle
                        int trainer = (int)(Math.random()*4)+1;
                        int i = 0;
                        int j = 0;
                        while(j<trainer){
                            if(trainersInfo[i][1].equals("Strength")){
                                j++;
                            }
                            if(j==trainer){
                                String trainerName = trainersInfo[i][0];
                                String trainerType = trainersInfo[i][1];
                                String imagesPath = trainersInfo[i][2];
                                String intro = trainersInfo[i][3];
                                new BookInfo(trainerName, trainerType, imagesPath,intro);
                            }
                            else{
                                i++;
                            }
                        }
                        if(BMI>24.9){
                            JOptionPane.showMessageDialog(null, "You BMI is " + df.format(BMI) + " and in "  + BMIFeedback(BMI) + ", we recommend to choose Fat-Loss.", "Suggestion", JOptionPane.PLAIN_MESSAGE);
                        }
                        
                    }
                    else if(target.equals("Fat-Loss")){
                        //recommend a Trainer of catagory Fat-Loss
                        int trainer = (int)(Math.random()*4+1);
                        int i = 0;
                        int j = 0;
                        while(j<trainer){
                            if(trainersInfo[i][1].equals("Slim")){
                                    j++;
                                }
                            if(j == trainer){
                                String trainerName = trainersInfo[i][0];
                                String trainerType = trainersInfo[i][1];
                                String imagesPath = trainersInfo[i][2];
                                String intro = trainersInfo[i][3];
                                new BookInfo(trainerName, trainerType, imagesPath,intro);

                            }
                            else{
                                i++;
                            }
                        }
                        if(BMI<=18.4){
                            JOptionPane.showMessageDialog(null, "You BMI is " + df.format(BMI) + " and in "  + BMIFeedback(BMI) + ", we recommend to choose Gain-muscle.", "Suggestion", JOptionPane.PLAIN_MESSAGE);
                        }

                    }

                }
            }
            });

        
        return recommendationPane;
    }

        // Calculate BMI value based on heigt(cm) and weight(kg)
        public double calBMI(Double height, Double weight){
            return 100*100*weight/(height*height);
        }
    
        // Get BMI range feedback
        public String BMIFeedback(double BMI){
            if(BMI<=0){
                return "BMI Error.";
            }
            else if(BMI<=18.4){
                return "Underweight";
            }
            else if(BMI<=24.9){
                return "Normal Weight";
            }
            else if(BMI<=29.9){
                return  "Overweight";
            }
            else{
                return "Obesity";
            }
        }

   
}
