package com.infinityicon.gretest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class QuestionPaper extends Activity implements
		QuestionTitles.OnItemSelectedListener {
	public static final String TAG = "QuestionPaper";
	List<Question> questionList;
	List<Test> testQuestionList;
	Cursor c;
	DataAccess da;
	QuestionDesc fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_paper);

		questionList = new ArrayList<Question>(); //list of Question Class Objects
		testQuestionList = new ArrayList<Test>(); //List of Test Class Objects

		da = new DataAccess(QuestionPaper.this);
		//Creating New Test from Random Questions and Getting MAX IDs
		c = da.createTest(Integer.parseInt(getIntent( ).getExtras().getString("Test")));
		c.moveToFirst();
		int iMaxTestID = da.getMaxTestID() + 1; // Autoincrement T_TID (UnqID of Test)
		int iMaxAnsID = da.getMaxAnswerID(); // AutoIncrement T_AID (UID of User ANS)
		Log.d(TAG, "MaxAns:" + iMaxAnsID);

		while (c.isAfterLast() == false) {
			Log.d("QP", "Q: " + c.getString(c.getColumnIndex(da.T_QTEXT)));
			//Adding Cursor data of Questions to Question Object List
			questionList.add(new Question(c.getInt(c.getColumnIndex(da.T_QID)),
					c.getInt(c.getColumnIndex(da.T_SID)), c.getString(c
							.getColumnIndex(da.T_QTEXT)), c.getString(c
							.getColumnIndex(da.T_QA1)), c.getString(c
							.getColumnIndex(da.T_QA2)), c.getString(c
							.getColumnIndex(da.T_QA3)), c.getString(c
							.getColumnIndex(da.T_QA4)), c.getString(c
							.getColumnIndex(da.T_QA5)), c.getString(c
							.getColumnIndex(da.T_QA6)), c.getString(c
							.getColumnIndex(da.T_QA))));

			iMaxAnsID = iMaxAnsID + 1;
			//Adding Cursor data of Test/Question/Ans to Test Object List
			testQuestionList.add(new Test(iMaxTestID, iMaxAnsID, c.getInt(c
					.getColumnIndex(da.T_QID)), "", 0, System
					.currentTimeMillis(), c.getInt(c.getColumnIndex(da.T_SID))));
			Log.d(TAG, "T_ID:" + iMaxTestID + " A_ID:" + iMaxAnsID + "Q_ID:"
					+ c.getInt(c.getColumnIndex(da.T_QID)) + " ANSWER Wrong:"
					+ 0 + " Date:" + System.currentTimeMillis());

			c.moveToNext();
		}
		for (Test tst : testQuestionList) {
			da.AddUserTest(tst); //Adding each user test q of UserTest to Test DBTable
		}

		da.CloseDB();
		fragment = (QuestionDesc) getFragmentManager().findFragmentById(
				R.id.frgQDesc);
		fragment.setQuestionNo("Instructions:\n-Test will be of 10 Questions\n-There is no Time Limit\n-You will have to press End Test button to end the test");
	}

	@Override
	public void OnNextPreviousPressed(int q) {
		if (fragment != null && fragment.isInLayout()) {
			if (q <= questionList.size()) {
				fragment.setQuestionNo("Question No: " + q);
				q = q - 1;
				fragment.setQuestion(questionList.get(q),
						testQuestionList.get(q));
			}
		}
	}
	
	public void OnEndTest ( ) {
		int iTID = testQuestionList.get(1).getTestID(); //Current onGoing USER TEST ID
		Log.d ( "TAG", "TestID: " + iTID);
		Toast.makeText(this, "Ending Test, Please Wait!", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent ( QuestionPaper.this, TestResult.class);
		intent.putExtra("TID", iTID);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity ( intent );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater( ).inflate( R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch ( item.getItemId() ) {

		}
		return super.onOptionsItemSelected(item);
	}
}
