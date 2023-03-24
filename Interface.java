import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/** 
 *  Select week and time to book trainer
 */
 public class Interface extends JFrame implements ActionListener{

     /** Base on fileName generate different interface page */ 
     public String fileName;

     /** Whole information of course read from file */ 
     public String courseInfo;

     public Interface(){

     }

     /**
	 * When the button is clicked, play the course
     * The method could be implement in subclass
     * @param ActionEvent, when button is clicked
	 */
     public void actionPerformed(ActionEvent e){
        
     }

     /**
      * Get the course information from file.txt
      * @param filename, while file to read
      * @return file contents by format of String[][]
      */
     public String[][] readFromFile(String filename){
        // Create base enviroment when file not exist
        // You can use new AllCourse(); to generate base environment
        try {
            File file = new File(filename);
            if(!file.exists()){
                new AllCourse();
            }
        } catch (Exception e) {
            System.out.println("Create Base Enviroment Error!");
        }

        String contents = "";
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine != null){
                contents += oneLine + ",";
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println("Errors occured: IOException!");
            System.exit(1);
        }     

        // Store file contents into array
        int rows = readLine(filename);
        String[] courseContents = contents.split(",");
        int columns = courseContents.length/rows;
        String[][] courseArray = new String[rows][columns];
        int k=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                courseArray[i][j] = courseContents[k];
                k++;
            }
        }

        // Read information to an array and storage it, so that it needn't read twice.
        return courseArray;
     }
     
     /**
      * Read Line, i.e number of course
      * @param filename, file to be read
      * @return file's line
      */
     public int readLine(String filename){
         int lines = 0;
         try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine = bufferedReader.readLine();
            while(oneLine != null){
                lines++;
                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
         } catch (Exception e) {
            System.out.println("readLine function error!");
         }
        return lines;
     }

     /**
      * Search all course name, when course name contains part or all courseName
      * @param filename, file to be searched
      * @param courseName, course to be matched
      * @return searched information
      */
     public String[][] searchCourse(String filename, String courseName){
        
        int searchNum = 0;
        String[][] courseArray = readFromFile(filename);
        for(int i=0; i<courseArray.length; i++){
            if(courseArray[i][0].contains(courseName)){
                searchNum++;
            }
        }

        String[][] searchResult = new String[searchNum][courseArray[0].length];
        int k=0;
        for(int i=0; i<courseArray.length; i++){
            if(courseArray[i][0].contains(courseName)){
                for(int j=0; j<courseArray[0].length; j++){
                    searchResult[k][j] = courseArray[i][j];
                }
                k++;
            }
        }

        return searchResult;

     }

     /**
      * Add Video by Interface
      * @param videoName
      * @param videoTime
      * @param filePath
      * @param tag
      * @param vip
      */
     public void addVideo(String videoName, int videoTime, String filePath, String tag, int vip){
        Video video = new Video(videoName, videoTime, filePath, filePath, tag, vip);
        new AllCourse().writeVideoToFile(video);
        System.out.println("You have added video: " + videoName);
      }
  
      /**
       * Add Trainer by Interface
       * @param trainerName
       * @param trainerType
       * @param figPath
       * @param intro
       */
      public void addTrainer(String trainerName, String trainerType, String figPath, String intro){
        Trainer trainer = new Trainer(trainerName, trainerType, figPath, intro);
        new AllCourse().writeTrainerToFile(trainer);
        System.out.println("You have added Trainer: " + trainerName);
      }

      /**
       * Play video function
       * @param filePath video path to be played
       */
      public void playVideo(String filePath){
        Runtime runtime=Runtime.getRuntime();
        try{
            runtime.exec("cmd /c start " + filePath);
        }catch (IOException e)
        {
            System.out.println(e);
        }

    }

     /**
      * Remove course, first search then remove
      * @param filename course in which file
      * @param courseName which course to be removed
      */
     public void removeCourse(String filename, String courseName){
        // courseName must be exact that can be removed
        try {
            String[][] orignalData = readFromFile(filename);
            FileWriter fileWriter = new FileWriter(filename, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(int i=0; i<orignalData.length; i++){
                if(orignalData[i][0].equals(courseName)){
                    continue;
                }
                for(int j=0; j<orignalData[0].length; j++){
                    if(j!=orignalData[0].length-1){
                        writer.write(orignalData[i][j] + ",");
                    }
                    else{
                        writer.write(orignalData[i][j] + "\n");
                    }   
                }
                
            }
            writer.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("removeCourse() File Writer error!");
        }
        System.out.println("You have remove the course successfully!");
     }
     
     /**
      * Classify by Tag in dictionary order
      * @param filename, which file to be classify
      * @return
      */
     public String[][] classifyByTag(String filename){
        String[][] courseArray = readFromFile(filename);
        for(int i=0; i<courseArray.length; i++){
            for(int j=i+1; j<courseArray.length; j++){
                // Copy the value of String[] not pointer
                // courseArray[][2] is tag value
                if(courseArray[i][2].compareTo(courseArray[j][2])>0){
                String[] tmp = Arrays.copyOf(courseArray[i],courseArray[0].length);
                    for(int k=0; k<courseArray[0].length; k++){
                        courseArray[i][k] = courseArray[j][k];
                        courseArray[j][k] = tmp[k];
                    }
                }
            }
        }
        return courseArray;
     }

     /**
       * function used to classify in the VideoPanel
       * It can filter the SVIP and VIP out of normal video
       * @return classified information where the video is only vip or svip
       */
     public String[][] classifyByVip(){
         String[][] courses =readFromFile("texts/allVideo.txt");
         int lines =readLine("texts/allVideo.txt");
         
         int num=0;
         for(int i=0;i<=lines-1;i++){
            if(courses[i][5].equals("0"))
            num++;
        }
        int vipLines=lines-num;
        String[][] coursesVip =new String[vipLines][6];

        int j=0;
         for(int i=0;i<=lines-1;i++){
             if(courses[i][5].equals("1")||courses[i][5].equals("2"))
               {        
                   
                   
                     for(int l=0;l<=5;l++)
                       {coursesVip[j][l]=courses[i][l];}
                       j++;
                    

                }
        }

       


               return coursesVip;
         }

         

 }
