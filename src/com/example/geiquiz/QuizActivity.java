package com.example.geiquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
	public static final String ANSWER_IS_TRUE="ANSWER_IS_TRUE";
	public static final String CHEAT_BUNDLE="CHEAT_BUNDLE";
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mQuestionTextView;
	private Button mCheatButton;
	private static final String TAG="QuizActivity";
	private static final String KEY_INDEX="index";
	private int mIndex=0;
	private Bundle mCheatBundle;
	private TrueFalse[] mQuestionBank=new TrueFalse[] {
		new TrueFalse(R.string.question_text_a, true),
		new TrueFalse(R.string.question_text_b, false)
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mCheatBundle=new Bundle();
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState!=null) {
			mIndex=savedInstanceState.getInt(KEY_INDEX,0);
			mCheatBundle=savedInstanceState.getBundle(CHEAT_BUNDLE);
		}
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text);
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(QuizActivity.this,CheatActivity.class);
				boolean answerIsTrue=mQuestionBank[mIndex].isTrueQuestion();
				i.putExtra(ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);
				}
		});
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIndex=(mIndex+1)%mQuestionBank.length;
				updateQuestion();
			}
		});
        mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIndex=(mIndex+1)%mQuestionBank.length;
				updateQuestion();
			}
		});
        mPrevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mIndex-1<0) {
					mIndex=mQuestionBank.length-1;
				}else {
					mIndex=(mIndex-1)%mQuestionBank.length;
				}
				updateQuestion();
			}
		});
        mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(true);
			}
		});
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(false);
			}
		});
        updateQuestion();
    }
    @Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");	
	}
	@Override
    protected void onRestart()
    {
    	super.onRestart();
    	Log.d(TAG, "onRestart");
    }
    @Override
	protected void onResume()
	{
		super.onResume();
		Log.d(TAG, "onResume");
	}
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d(TAG, "onPause");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop");
	}
	@Override
    protected void onDestroy()
    {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy");

    }
	private void checkAnswer(boolean press)
    {
    	int messageId=0;
    	boolean result=mQuestionBank[mIndex].isTrueQuestion();
    	if (mCheatBundle.getBoolean(String.valueOf(mIndex))) {
			messageId=R.string.judgment_toast;
		}else {
    	if (result==press) {
			messageId=R.string.correct_toast;
		}else {
			messageId=R.string.incorrect_toast;
		}
		}
    	Toast.makeText(QuizActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }
    private void updateQuestion()
    {
        int question=mQuestionBank[mIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
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
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data==null) {
			return;
		}
		mCheatBundle.putBoolean(String.valueOf(mIndex),data.getBooleanExtra(CheatActivity.ANSWER_IS_SHOWN, false));
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d("QuizActivity", "onSaveInstanceState");
		outState.putInt(KEY_INDEX, mIndex);
		outState.putBundle(CHEAT_BUNDLE, mCheatBundle);
	}

}
