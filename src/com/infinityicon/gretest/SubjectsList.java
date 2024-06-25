package com.infinityicon.gretest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectsList extends Activity implements OnItemClickListener {

	public final String[] strSubjects = { "Analogies", "Sentence Complition",
			"Antonyms", "Synonyms" };
	public final int   [] iSubjects   = { 1, 2, 4, 7 };
	private final String TAG = "Subjects";
	ListView lstSubjects;
	SubjectsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjects);

		lstSubjects = (ListView) findViewById(R.id.lstSubjects);
		adapter = new SubjectsAdapter(SubjectsList.this,
				R.layout.layout_subject_list, strSubjects);
		lstSubjects.setAdapter(adapter);
		lstSubjects.setOnItemClickListener(this);
		
		Button btnResult = ( Button ) findViewById(R.id.btnResult);
		btnResult.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentR = new Intent ( SubjectsList.this, TestResult.class);
				startActivity ( intentR );
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		Intent intent = new Intent(SubjectsList.this, QuestionPaper.class);
		TextView tv = (TextView) view.findViewById(R.id.txtSID);
		intent.putExtra("Test", tv.getText().toString());
		Log.d(TAG, tv.getText().toString());
		startActivity(intent);
	}
	
	private class SubjectsAdapter extends ArrayAdapter<String> {


		public SubjectsAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if ( row == null ) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.layout_subject_list, null);
			}
			((TextView)row.findViewById(R.id.txtSubject)).setText(strSubjects[position]);
			TextView tSID = ( TextView ) row.findViewById(R.id.txtSID);
			tSID.setTextColor(Color.WHITE);
			tSID.setText(String.valueOf(iSubjects[position]));
			
			return row;
		}
	}
}
