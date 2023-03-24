/**
 * Trainer attributes including Trainer Name, Type, Self Picture, Introduction
 */
public class Trainer {
    private String trainerName;
    private String trainerType;
    private String figPath;
    private String intro;

    public Trainer(String trainerName, String trainerType, String figPath, String intro){
        setTrainerName(trainerName);
        setTrainerType(trainerType);
        setTrainerFig(figPath);
        setTrainerIntro(intro);
    }

    public String getTrainerName(){
        return trainerName;
    }

    public String getTrainerType(){
        return trainerType;
    }

    public void setTrainerName(String trainerName){
        this.trainerName = trainerName;
    }

    public void setTrainerType(String trainerType){
        this.trainerType = trainerType;
    }

    public void setTrainerFig(String figPath){
        this.figPath = figPath;
    }

    public String getTrainerFig(){
        return figPath;
    }

    public void setTrainerIntro(String intro){
        this.intro = intro;
    }

    public String getTrainerIntro(){
        return intro;
    }
    
}