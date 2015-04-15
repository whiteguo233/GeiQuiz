package com.example.geiquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	public static final String ANSWER_IS_SHOWN="ANSWER_IS_SHOWN";
	private static final String TAG="CheatActivity";
	private boolean mAnswerIsTrue;
	private boolean mIsShown;
	private TextView mAnserTextView;
	private Button  mShowAnswerButton;
@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_cheat);
	Log.d(TAG, "onCreate");
	mIsShown=false;
	if (savedInstanceState!=null) {
		mIsShown=savedInstanceState.getBoolean(ANSWER_IS_SHOWN);
	}
	mAnserTextView=(TextView)findViewById(R.id.answerTextView);
	mShowAnswerButton=(Button)findViewById(R.id.showAnswerButton);
	mAnswerIsTrue=getIntent().getBooleanExtra(QuizActivity.ANSWER_IS_TRUE, false);
	mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mAnswerIsTrue) {
				mAnserTextView.setText(R.string.true_button);
			}else {
				mAnserTextView.setText(R.string.false_button);
			}
			setAnswerShownResult(true);
		}
	});
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
private void setAnswerShownResult(boolean isShown)
{
	Intent intent=new Intent();
	mIsShown=isShown;
	intent.putExtra(ANSWER_IS_SHOWN, isShown);
	setResult(RESULT_OK, intent);
}

@Override
protected void onSaveInstanceState(Bundle outState) {
	// TODO Auto-generated method stub
	super.onSaveInstanceState(outState);
	Log.d("CheatActivity", "onSaveInstanceState");
	outState.putBoolean(ANSWER_IS_SHOWN, mIsShown);
}
}
