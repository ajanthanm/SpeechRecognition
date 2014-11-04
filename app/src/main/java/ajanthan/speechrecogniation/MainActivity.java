package ajanthan.speechrecogniation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends Activity {

    protected static final int REQUEST_OK = 1;
    final  String  speak  = "speak";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.speak);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setClickable(false);
                final TextView textViewToChange = (TextView) findViewById(R.id.textvi);
                final Context context = getApplicationContext();
                final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en_US");

                try{
                    startActivityForResult(intent, REQUEST_OK);

                }
                catch (Exception e)
                {

                    Toast.makeText(context, "Error initializing Speech Engine", Toast.LENGTH_SHORT).show();

                }
                //textViewToChange.setText("Speech Recognizer Available? :  \t"+SpeechRecognizer.isRecognitionAvailable(context));
                //SpeechRecognizer.createSpeechRecognizer(context).startListening();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Toast.makeText(getApplicationContext(), ""+requestCode+" and"+resultCode, Toast.LENGTH_SHORT).show();
        //if(requestCode == REQUEST_OK && resultCode == REQUEST_OK)
        {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            final TextView textViewToChange = (TextView) findViewById(R.id.textvi);
            textViewToChange.setText(thingsYouSaid.get(1));
            final Button button = (Button) findViewById(R.id.speak);
            button.setClickable(true);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
