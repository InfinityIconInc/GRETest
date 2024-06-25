package com.infinityicon.gretest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class TestResult extends Activity {
	public static String TAG = "TestResult";
	int iTID, iWrong;
	DataAccess da;
	Cursor c;
	SimpleCursorAdapter adapter;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		da = new DataAccess(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_result);

		ListView lstResults = (ListView) findViewById(R.id.lstResults);
		TextView txtResult = (TextView) findViewById(R.id.txtResult);
		Button btnGoBack = ( Button ) findViewById( R.id.btnTestList );
		
		btnGoBack.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (TestResult.this, SubjectsList.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);				
			}
		});

		Cursor c = da.getPreviousTestResults();
		c.moveToFirst();

		adapter = new SimpleCursorAdapter(TestResult.this, R.layout.row_result,
				c, new String[] { "SUM(wrong)", DataAccess.T_TDATE,
						DataAccess.T_TSID }, new int[] { R.id.txtSub,
						R.id.txtMarks, R.id.txtDate });
		adapter.setViewBinder(VIEW_BINDER);

		lstResults.setAdapter(adapter);
		Bundle bundle = getIntent().getExtras();
		
		if ( bundle != null ) {
			if ( bundle.containsKey("TID")) {
				iTID = getIntent().getExtras().getInt("TID");
				c = da.getTestResult(iTID);
				c.moveToFirst();
				iWrong = c.getInt(c.getColumnIndex("COUNT(wrong)"));
				txtResult.setText(10 - iWrong + " Correct out of total 10 Questions.");
			}
		}
		da.CloseDB();
	}

	static final ViewBinder VIEW_BINDER = new ViewBinder() {

		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (view.getId() == R.id.txtDate) {
				long time = cursor.getLong(cursor
						.getColumnIndex(DataAccess.T_TDATE));
				CharSequence relativeTime = DateUtils
						.getRelativeTimeSpanString(time);
				((TextView) view).setText(relativeTime);
				return true;
			} else if (view.getId() == R.id.txtMarks) {
				String strWrong = cursor.getString(cursor
						.getColumnIndex("SUM(wrong)"));
				((TextView) view).setText(strWrong + " correct out of 10");
				return true;
			} else if (view.getId() == R.id.txtSub) {
				int iSubID = cursor.getInt(cursor
						.getColumnIndex(DataAccess.T_TSID));
				if (iSubID == 1)
					((TextView) view).setText("Analogies");
				else if (iSubID == 2)
					((TextView) view).setText("Sentence Complition");
				else if (iSubID == 4)
					((TextView) view).setText("Antonyms");
				else
					((TextView) view).setText("Synonyms");
				return true;
			} else
				return false;
		}

	};
}
