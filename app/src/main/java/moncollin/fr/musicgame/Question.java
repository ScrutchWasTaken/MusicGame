package moncollin.fr.musicgame;

/**
 * Created by scrutch on 15/12/15.
 */
public class Question {
    String music;
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String rightAnswer;

    public Question(String music, String question, String answer1, String answer2, String answer3, String answer4, String rightAnswer){
        this.music=music;
        this.question=question;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer=rightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getMusic() {
        return music;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "music: "+music+"quesion: "+question+"answer1: "+answer1+"answer2: "+answer2+"answer3: "+answer3+"answer4: "+answer4+"right: "+rightAnswer;
    }
}
