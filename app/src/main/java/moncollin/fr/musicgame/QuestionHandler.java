package moncollin.fr.musicgame;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by scrutch on 15/12/15.
 */
public class QuestionHandler extends DefaultHandler {

    boolean currentElement = false;
    String currentValue = "";

    Question question;
    ArrayList<Question> questions;



    /////////////////////
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

      currentElement = true;

      if (qName.equals("questionGroup")){
    //   question = new ;
      }

    }
    /////////////////////

    public void endElement(String uri, String localName, String qName) throws SAXException {

      currentElement = false;

      if (qName.equalsIgnoreCase("answer1"))
       question.answer1 = currentValue.trim();
      else if (qName.equalsIgnoreCase("answer2"))
       question.answer2 = currentValue.trim();
      else if (qName.equalsIgnoreCase("answer3"))
       question.answer3 = currentValue.trim();
      else if (qName.equalsIgnoreCase("answer4"))
       question.answer4 = currentValue.trim();
      else if (qName.equalsIgnoreCase("right"))
       question.rightAnswer = currentValue.trim();
      else if (qName.equalsIgnoreCase("question"))
       question.question = currentValue.trim();
      else if (qName.equalsIgnoreCase("music"))
          question.question = currentValue.trim();

      currentValue = "";
     }

}
