import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/** 
* Contains the static methods that is reused many times
* including writing & reading files, validating data format
* Last updated on :2021/5/27
*  
*/
   /**
	 * updated on 2021/4/21: added the function of verify e-mail and password format
	 * updated on 2021/4/21: added the function of record current user
	 * updated on 2021/5/18: added the function of creating ImageIcon
     * updated on 2021/5/18: added the function of verifying bank account format
     * updated on 2021/5/18: added the function of read text file into a string array
     * updated on 2021/5/20: added the funciton of recording Video times and userType
     * 
	 */

public class Util {
    /**The file path of all user's information */
    public static String pathname = "texts/member.txt"; 
    /**The file path of the file that record the information of current user*/
    public static String currentuser = "texts/currentuser.txt";

     /**
	 * Read the information of a List<Member> array from a text file
	 * @param 
	 * @return List<Member> Member array that contain all the members
	 */
    public static List<Member> readFile() {
        //List<Member> memberList = new ArrayList<>();
        BufferedReader br = null;
        InputStreamReader reader = null;

        try {
            if (!new File(pathname).exists()) {
                new File(pathname).createNewFile();
            }
            reader = new InputStreamReader(new FileInputStream(new File(pathname)), StandardCharsets.UTF_8);
            br = new BufferedReader(reader);
            StringBuilder all = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                all.append(line);
            }
            br.close();
            if (all.length() != 0) {
                return StringToArrayList(all.toString());
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
	 * Write the information of a List<Member> array into a text file
	 * @param List<Member> Member array that contain all the members
	 * @return boolean, indicating whether the writing of file is successful.
	 */
    public static boolean writeFile(List<Member> memberList) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(new File(pathname)), StandardCharsets.UTF_8);
            writer.flush();
            String str = memberList.toString();
            writer.write(str);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
	 * Store the information of members that read from a text file into arrayList
	 * @param String the string that contain all the information of all the members
	 * @return List<Member> arrayList that contain all the members
	 */
    public static List<Member> StringToArrayList(String str) {
        List<Member> members = new ArrayList<>();
        if (str.length() == 2) {
            return new ArrayList<>();
        } else {
            String[] strings = str.replace("Member{", "").split("},");
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i].replace("{", "").replace("'", "").replace("[", "").replace("]", "").replace("}", "");
                String[] keyAndValue = string.split(",");
                Member member = new Member();
                for (int j = 0; j < keyAndValue.length; j++) {
                    keyAndValue[j] = keyAndValue[j].trim();
                    String[] world = keyAndValue[j].split("=");
                    if (world[0].equals("account")) {
                        member.setAccount(world[1]);
                    } else if (world[0].equals("email")) {
                        member.setEmail(world[1]);
                    } else if (world[0].equals("password")) {
                        member.setPassword(world[1]);
                    } else if (world[0].equals("userType")) {
                        member.setUserType(world[1]);
                    } else if (world[0].equals("videotimes")) {
                        member.setVideoTimes(Integer.parseInt(world[1]));
                    }
                      
                }
                members.add(member);
            }
            return members;
        }
    }
     /**
	 * Record the current user of the online gym
	 * @param member the member object represent that user
	 * @return 
	 */
    public static void recordCurrentUser(Member member){
        try{
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(currentuser)), StandardCharsets.UTF_8);
        writer.flush();             //flush the buffer
        writer.write(member.getAccount()+','+member.getPassword()+','+member.getEmail()+','+member.getUserType()+','+member.getVideoTimes());
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
	 * Verify the format of a new password
	 * @param pasword the password choose by user
	 * @return int value indicate the verified outcome,1=success,0=require both number and character,-1=lenth less than 6 
	 */
    public static int passwordFormat(String password){
        //chek for the length of password(bigger than 6)
        if(password.length()<6)
        {return -1;}
        //chek for the both character and number
        boolean havechar = false;
        boolean havenum = false;
        for (int i = 0; i < password.length(); i++) {
            if(password.charAt(i)<='9'&& password.charAt(i)>='0')
            {havenum = true;}
            if(password.charAt(i)<='z'&&password.charAt(i)>='a'||password.charAt(i)<='Z'&&password.charAt(i)>='A')
            {havechar = true;}
        }

        if(havenum&&havechar){
            return 1;
        }
        else{
            return 0;
        }
    }
 
    /**
	 * Verify the format of a new email(must included @)
	 * @param email the email choose by user
	 * @return int value indicate the verified outcome,1=valid,0=invalid
	 */
     public static int emailFormat(String email){
         boolean valid = false;
        for (int i = 0; i < email.length(); i++) {
            if(email.charAt(i)=='@'){
               valid = true;
            }
        }

        if(valid == true){
            return 1;
        }
        else{
            return 0;
        }

     
    }



 

     /**
	 * Verify the format of a new email
	 * @param bankaccount the bankaccount inputed by user
	 * @return boolean indicate wheter it is valid
	 */
    public static boolean accountFormat(String bankaccount){

        int i = 0;
        //consist of only number
        for(i=0;i<bankaccount.length();i++){
          if(!Character.isDigit(bankaccount.charAt(i))){
                return false;
          }
        }
        // length should be over 5
        if(bankaccount.length()<5){
            return false;
        }
        return true;

        
    }

    /**
	 * Read information from a file and store it in a String array
	 * @param String the path of the file
	 * @return String[][] String array that contain all the information 
	 */
    public static String[][] readFromFile(String filename){
        // Exit when file not exist
        try {
            File file = new File(filename);
            if(!file.exists()){
                System.out.println("No such file, then we will exit..");
                System.exit(0);
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
        String[][] infoArray = new String[rows][columns];
        int k=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                infoArray[i][j] = courseContents[k];
                k++;
            }
        }

        // Read information to an array and storage it, so that it needn't read twice.
        return infoArray;
     }

     /**
	 * Returning how many lines are there in a given file
	 * @param String the path of the file
	 * @return int, how many lines are the file.
	 */
     public static int readLine(String filename){
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




    
}

