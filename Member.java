public class Member {
    /*user's account name*/
    private String account;
    /*user's password*/
    private String password;
    /*user's e-mail*/
    private String email;
    /*user's user type*/
    private String userType;//userType is "normal" in default      can be "VIP" after updating
    /*how many times user can view videos*/
    private int videotimes;
/**
 * Description	: This class the entity class that contain a user's information
 */
    public Member() {
    }

    public Member(String account, String password, String email,String userType) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.videotimes = 0;
    }
    /**
	 * Getter for videotimes
	 * @param  
	 * @return int, how many times user can view videos
	 */
    public int getVideoTimes(){
        return this.videotimes;
    }
     /**
	 * Setter for videotimes
	 * @param  int, how many times user can view videos
	 * @return 
	 */
    public void setVideoTimes(int videotimes){
        this.videotimes = videotimes;

    }

   /**
	 * Getter for user's account name
	 * @param  
	 * @return String, user's account name
	 */
    public String getAccount() {
        return account;
    }
    /**
	 * Setter user's account name
	 * @param  String, user's account name
	 * @return 
	 */
    public void setAccount(String account) {
        this.account = account;
    }
    /**
	 * Getter for user's password
	 * @param  
	 * @return String, user's password
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * Setter for user's password
	 * @param  String, user's password
	 * @return 
	 */
    public void setPassword(String password) {
        this.password = password;
    }
   /**
	 * Getter for user's e-mail
	 * @param  
	 * @return String, user's e-mail
	 */
    public String getEmail() {
        return email;
    }
    /**
	 * Setter for user's e-mail
	 * @param  String, user's e-mail
	 * @return 
	 */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
	 * Getter for user's user type
	 * @param  
	 * @return String, user's user type
	 */
    public String getUserType(){
        return userType;
    }
    /**
	 * Getter for user's user type
	 * @param  String, user's user type
	 * @return 
	 */
    public void setUserType(String userType){
        this.userType = userType;
    }

  /**
	 * Return all information of a user
	 * @param  
	 * @return String, all information of a user
	 */
    public String toString() {
        return "Member{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", videotimes='" + videotimes + '\'' +
                '}';
    }
}

