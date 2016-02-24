package moncollin.fr.musicgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLOutput;

/**
 * Created by scrutch on 09/02/16.
 */
public class QuestionAnswer  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        Intent intent = getIntent();
        String answer = intent.getStringExtra("answer");
        System.out.println("answer :"+answer);

        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(answer);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionAnswer.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
