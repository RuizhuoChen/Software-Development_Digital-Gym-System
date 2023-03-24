/*
 * @Author: your name
 * @Date: 2021-03-28 20:02:49
 * @LastEditTime: 2021-04-15 18:41:38
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \Video-Live\AllCourse.java
 */
import java.io.*;
import java.nio.file.*;

/**
 * Generate base environment of video and trainer
 */
public class AllCourse{

    public AllCourse(){
      // Generate base enviroment
      File videoFile = new File("texts/AllVideo.txt");
      if(!videoFile.exists()){
        addVideo("Functional Training", 30, "videos/FunctionalTraining.MP4", "images/button_back1.jpg", "HIIT",0);
        addVideo("Yoga Training", 35, "videos/YogaTraining.MP4","images/button_back2.jpg", "Yoga",0);
        addVideo("Training Muscles", 45, "videos/TrainingMuscles.MP4","images/button_back3.jpg", "Strength",0);
        addVideo("Functional Training", 38, "videos/FunctionalTraining2.MP4", "images/button_back8.jpg", "HIIT",1);
        addVideo("Yoga Training", 33, "videos/YogaTraining2.MP4","images/button_back4.jpg", "Yoga",1);
        addVideo("Training Muscles", 50, "videos/TrainingMuscles2.MP4","images/button_back7.jpg", "Strength",1);
        addVideo("Functional Training", 67, "videos/FunctionalTraining3.MP4", "images/button_back9.jpg", "HIIT",2);
        addVideo("Yoga Training", 45, "videos/YogaTraining3.MP4","images/button_back5.jpg", "Yoga",2);
        addVideo("Training Muscles", 40, "videos/TrainingMuscles3.MP4","images/button_back6.jpg", "Strength",2);
      }

      File trainerFile = new File("texts/AllTrainer.txt");
      if(!trainerFile.exists()){
        addTrainer("Tom", "Strength", "images/Trainer1.jpg","More than 20 years of experience in martial arts training. Disciplinary sports club fitness instructor Boundlss online coach. Watahhh fitness personal trainer IPTA Certified Senior Fitness Trainer.");
        addTrainer("Tina", "Strength", "images/Trainer2.jpg", "Students can strengthen their core muscles and improve poor posture and pain. And make their body shape more beautiful. I teach in small classes and hoping that students can get professional guidance and provide teaching according to different levels of people.");
        addTrainer("Ann", "Slim", "images/Trainer3.jpg","I have worked in fitness industry for ten years. I have worked at London Fitness as a Group Fitness Trainer for three years. I am also certified in Personal Training ans Spinning. I can help you decrease body fat and build muscle strength.");
        addTrainer("Vee", "Slim", "images/Trainer4.jpg","Our mission is to help people enjoy life to its fullest by supervising and creating expertly designed fitness programs. Whether it's improving lean muscle we value and individual's need to improve their quality of life.");
        addTrainer("Jane", "Slim", "images/Trainer5.jpg","You might have tries other programs or methods in hte past with nothing to show for it. Dan has designed and reshaped his system to make sure it provides all the knowledge and support you need to achieve the desired results.");
        addTrainer("Ken", "Strength", "images/Trainer6.jpg","After eight years in the finace industry then Ken left his desk job behind for a career in fitness. Ken quickly developed a fiercely loyal following through teaching at various gyms in the city. ");
        addTrainer("Andy", "Strength", "images/Trainer7.jpg","I first gained an interest in fitness when I was 8 years old. The interest quickly developed into a passion and since then I have been dedicated to understanding the most effective and efficient way to increase muscle mass and decrease body fat.");
        addTrainer("Lucia", "Slim", "images/Trainer8.jpg","Being active has always been very important to me growing up in an athletic household. I specialize in athletic and sports performace as well as MMA conditioning. Fitness has instilled so much disciplince and confidence in me.");
        addTrainer("Bert", "Strength", "images/Trainer9.jpg","Bert specialized in training clients all ages from beginners to professional athletes. Bert's main passion is to develop healthy training & nutrition lifestyles. Along with an absolute mindset to be healthy & get younger for life.");
        addTrainer("Rita", "Slim", "images/Trainer10.jpg","Define Motivational Interviewing(MI). Explore ambivalence as a barrier to change. Examine and practive the Spirit of MI. Practice core skills of MI.");
        addTrainer("Shelley", "Slim", "images/Trainer11.jpg","I was once overweight and now I've found what makes me happy. All I want to do is help others achieve that health journey. I can relate to people that don't like to work out because I used to be one of those people.");
      }
    }
   
    /**
     * Add Video
     * @param videoName
     * @param videoTime
     * @param filePath
     * @param figPath
     * @param tag
     * @param vip
     */
    public void addVideo(String videoName, int videoTime, String filePath, String figPath, String tag, int vip){
      // Video Name, Video Time, Video URL, Background Button URL
      Video video = new Video(videoName, videoTime, filePath, figPath, tag, vip);
      writeVideoToFile(video);
    }

    /**
     * Add Trainer
     * @param trainerName
     * @param trainerType
     * @param figPath
     * @param intro
     */
    public void addTrainer(String trainerName, String trainerType, String figPath, String intro){
      // Trainer Name, Trainer Type, Background Button URL
      Trainer trainer = new Trainer(trainerName, trainerType, figPath, intro);
      writeTrainerToFile(trainer);
    }

    /**
     * Write Video Information to file
     * @param video
     */
    public void writeVideoToFile(Video video){
      String fileDirectory = "texts";
      fileExist(fileDirectory);

      String filename = "texts/AllVideo.txt";
      try {
        FileWriter fileWriter = new FileWriter(filename, true); // It can write at the end of file.
        BufferedWriter writer = new BufferedWriter(fileWriter);
        // Write to file order: Name, Time, Tag, videoPath, videoFig
        writer.write(video.getVideoName() + "," + video.getVideoTime() + "," + video.getVideoTag()+ "," + video.getVideoPath() + "," + video.getVideoFig() +  "," + video.getVideoVip() + "\n");
        writer.close();
        fileWriter.close();
      } catch (Exception e) {
        System.out.println("File Writer error!");
      }
    }
    
    /**
     * Write Trainer information to file
     * @param trainer
     */
    public void writeTrainerToFile(Trainer trainer){
      String fileDirectory = "texts";
      fileExist(fileDirectory);

      String filename = "texts/AllTrainer.txt";
      try {
        FileWriter fileWriter = new FileWriter(filename, true); // It can write at the end of file.
        BufferedWriter writer = new BufferedWriter(fileWriter);  
        writer.write(trainer.getTrainerName() + "," + trainer.getTrainerType() + "," + trainer.getTrainerFig() + "," + trainer.getTrainerIntro() + "\n");
        writer.close();
        fileWriter.close();
      } catch (Exception e) {
        System.out.println("File Writer error!");
      }
    }

    /**
     * Check directory exist or generate it
     * @param pathName
     */
    public void fileExist(String pathName){
      File file = new File(pathName);
      if(!file.exists()){
        Path path = Paths.get(pathName);
        try {
          Files.createDirectory(path);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          System.out.println(pathName + "is not exist.");
        }
      }
    }

    
}
