package com.redux.kumardivyarajat.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuizGameActivity extends ActionBarActivity {

    public static final String TAG = QuizGameActivity.class.getSimpleName();
    public String Question;
    public String OptionA;
    public String OptionB;
    public String OptionC;
    public String OptionD;
    public int Correct;

    public TextView mQuestion;
    public RadioButton mOptionA;
    public RadioButton mOptionB;
    public RadioButton mOptionC;
    public RadioButton mOptionD;
    public Button mSubmit;

    static int count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);
        Intent intent = getIntent();
        String subject = intent.getStringExtra("Subject");
        Log.d(TAG, subject);

        mQuestion = (TextView)findViewById(R.id.questions);
        mOptionA = (RadioButton)findViewById(R.id.optionA);
        mOptionB = (RadioButton)findViewById(R.id.optionB);
        mOptionC = (RadioButton)findViewById(R.id.optionC);
        mOptionD = (RadioButton)findViewById(R.id.optionD);
        mSubmit = (Button)findViewById(R.id.quiz_game_submit_button);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Questions");
        query.whereEqualTo("Subject",subject);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Integer[] arr = new Integer[15];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = i;
                    }
                    Collections.shuffle(Arrays.asList(arr));
                    /* TODO Have to change from here in order to make it fetch questions according to the random list*/
                    for(int i = 0; i <arr.length; i++) {
                        for(ParseObject object: objects) {
                            Question = object.getString("Question");
                            OptionA = object.getString("OptionA");
                            OptionB = object.getString("OptionB");
                            OptionC = object.getString("OptionC");
                            OptionD = object.getString("OptionD");
                            Correct = object.getInt("CorrectAnswer");
                            final String[] mOptions = {
                                    OptionA,OptionB,OptionC,OptionD
                            };



                            mQuestion.setText(Question);
                            mOptionA.setText(OptionA);
                            mOptionB.setText(OptionB);
                            mOptionC.setText(OptionC);
                            mOptionD.setText(OptionD);

                            mSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(/*check if radio button 1 is checked*/) {
                                        if (mSubmit.getText().toString() == mOptions[0]) {
                                            count++;
                                            Toast.makeText(QuizGameActivity.this, "Well Done! Correct Answer", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(QuizGameActivity.this, "Sorry. Wrong Answer", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else if() {
                                            if(mSubmit.getText().toString() == mOptions[1]) {
                                                count++;
                                                Toast.makeText(QuizGameActivity.this, "Well Done! Correct Answer", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(QuizGameActivity.this,"Sorry. Wrong Answer",Toast.LENGTH_SHORT).show();
                                            }
                                    } else if() {
                                        if(mSubmit.getText().toString() == mOptions[2]) {
                                            count++;
                                            Toast.makeText(QuizGameActivity.this, "Well Done! Correct Answer", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(QuizGameActivity.this,"Sorry. Wrong Answer",Toast.LENGTH_SHORT).show();
                                        }
                                    } else if(){
                                        if(mSubmit.getText().toString() == mOptions[3]) {
                                            count++;
                                            Toast.makeText(QuizGameActivity.this, "Well Done! Correct Answer", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(mSubmit.getText().toString() != mOptions[3]) {
                                            Toast.makeText(QuizGameActivity.this, "Sorry. Wrong Answer", Toast.LENGTH_SHORT).show();

                                        }
                                    }  else {
                                        Toast.makeText(QuizGameActivity.this,"Seems like you didn't enter any choice. No marks for you",Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });


                        }

                    }
                    Toast.makeText(QuizGameActivity.this, Arrays.toString(arr), Toast.LENGTH_SHORT).show();

                } else {
                  //  objectRetrievalFailed();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
