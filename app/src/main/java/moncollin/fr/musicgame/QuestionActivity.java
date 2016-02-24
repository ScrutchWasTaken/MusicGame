package moncollin.fr.musicgame;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private InputStream input;
    private ArrayList<Question> questionsList = new ArrayList<Question>();
    private ArrayList<RadioButton> rList = new ArrayList<RadioButton>();
    private Question q;
    private int index = 0;
    private int answer;
    private String rightAnswer;
    private Intent intent;
    private AssetFileDescriptor afd;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView t = (TextView) findViewById(R.id.textView);
        RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton r4 = (RadioButton) findViewById(R.id.radioButton4);
        rList.add(r1);
        rList.add(r2);
        rList.add(r3);
        rList.add(r4);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getAssets().open("questions_fr.xml")));
            parse(br);
            index = new Random().nextInt(questionsList.size());
            t.setText(questionsList.get(index).getQuestion());
            r1.setText(questionsList.get(index).getAnswer1());
            r2.setText(questionsList.get(index).getAnswer2());
            r3.setText(questionsList.get(index).getAnswer3());
            r4.setText(questionsList.get(index).getAnswer4());
            for (RadioButton r:rList) {
                if(r.getText()==questionsList.get(index).getRightAnswer())
                    answer = r.getId();
            }


        }catch(Exception e) {
            e.printStackTrace();
        }

        intent = new Intent(QuestionActivity.this, QuestionAnswer.class);
        rightAnswer = "Try again !";

        Button playMusic = (Button)findViewById(R.id.button2);
        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    afd = getAssets().openFd(questionsList.get(index).getMusic());
                    player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button stopMusic = (Button)findViewById(R.id.button3);
        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button answer = (Button) findViewById(R.id.button4);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("answer",rightAnswer);
                startActivity(intent);
                if(player!=null){player.stop();}
                finish();
            }
        });

    }


    public void parse (BufferedReader file) throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(file);
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == XmlPullParser.START_TAG) {
                questionsList.add(new Question(xpp.getAttributeValue(null,"M"),xpp.getAttributeValue(null,"Q"),xpp.getAttributeValue(null,"A1"),xpp.getAttributeValue(null,"A2"),xpp.getAttributeValue(null,"A3"),xpp.getAttributeValue(null,"A4"),xpp.getAttributeValue(null,"R")));
//                Question q1 = new Question(xpp.getAttributeValue(null,"M"),xpp.getAttributeValue(null,"Q"),xpp.getAttributeValue(null,"A1"),xpp.getAttributeValue(null,"A2"),xpp.getAttributeValue(null,"A3"),xpp.getAttributeValue(null,"A4"),xpp.getAttributeValue(null,"R"));
            } else if(eventType == XmlPullParser.END_TAG) {
            }
            else if(eventType == XmlPullParser.COMMENT){
            }
            eventType = xpp.next();
        }
        questionsList.remove(0);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

//        intent = new Intent(QuestionActivity.this, QuestionAnswer.class);

        // Check if right answer is checked
        if(view.getId()==answer){
            if(checked){
                //right answer
                rightAnswer = "Right Answer !";
            }
            else{
                rightAnswer = "Try again !";
            }
        }
        else{
            //you noob!
            rightAnswer = "Try again !";

        }
//        intent.putExtra("answer",rightAnswer);
    }
}
