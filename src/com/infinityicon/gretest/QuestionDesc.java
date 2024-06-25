package com.infinityicon.gretest;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionDesc extends Fragment {
	public static final String TAG = "QuestionDesc";
	TextView txtQ;
	RadioGroup rbgOptions;
	RadioButton rb_o1;
	RadioButton rb_o2;
	RadioButton rb_o3;
	RadioButton rb_o4;
	RadioButton rb_o5;
	RadioButton rb_o6;

	DataAccess da;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_q_desc, container, false);
		txtQ = (TextView) view.findViewById(R.id.lblQ);
		rbgOptions = (RadioGroup) view.findViewById(R.id.rbgOptions);
		rb_o1 = (RadioButton) view.findViewById(R.id.radio0);
		rb_o2 = (RadioButton) view.findViewById(R.id.radio1);
		rb_o3 = (RadioButton) view.findViewById(R.id.radio2);
		rb_o4 = (RadioButton) view.findViewById(R.id.radio3);
		rb_o5 = (RadioButton) view.findViewById(R.id.radio4);
		rb_o6 = (RadioButton) view.findViewById(R.id.radio5);
		
		txtQ.setTextColor(Color.WHITE);
		rb_o1.setTextColor(Color.WHITE);
		rb_o2.setTextColor(Color.WHITE);
		rb_o3.setTextColor(Color.WHITE);
		rb_o4.setTextColor(Color.WHITE);
		rb_o5.setTextColor(Color.WHITE);
		rb_o6.setTextColor(Color.WHITE);
		
		rb_o1.setVisibility(View.INVISIBLE);
		rb_o2.setVisibility(View.INVISIBLE);
		rb_o3.setVisibility(View.INVISIBLE);
		rb_o4.setVisibility(View.INVISIBLE);
		rb_o5.setVisibility(View.INVISIBLE);
		rb_o6.setVisibility(View.INVISIBLE);
		
		da = new DataAccess(getActivity());// Init DA
		return view;
	}

	public void setQuestionNo(String q) {
		TextView txtQNo = (TextView) getView().findViewById(R.id.lblQNo);
		txtQNo.setTextColor( Color.WHITE );
		txtQNo.setText( q );
	}

	public void setQuestion(Question q, Test t) {
		Log.d(TAG, "Inside setQuestion");
		final String ans = da.getUserTestAnswer(t.getAnswerID());//users selected answer
		final Test test = t;
		final Question quest = q;
		
		rbgOptions.check(-1); //clears NEXT question radio button selection
		
		OnClickListener ocrListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "ID:" + v.getId());
				if (ans.compareTo(v.getTag().toString()) != 0) { //if not same ans click
					Log.d(TAG, "UPDATING " + test.getAnswerID());
					test.setAnswer(v.getTag().toString());
					da.UpdateUserTest(test, v.getTag().toString(), quest);
				}
			}
		};

		txtQ.setText(q.getQ_question());

		if (q.getQ_o1().compareTo("") == 0) { //Check if Question Option exists
			// Log.d(TAG, "Hiding");
			rb_o1.setVisibility(View.INVISIBLE);
		} else {
			rb_o1.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o1()) == 0) // if selectedAns=thisOption than check it BUNDLE
				rbgOptions.check(rb_o1.getId());
			rb_o1.setText(q.getQ_o1());
			rb_o1.setTag(q.getQ_o1());
		}

		if (q.getQ_o2().compareTo("") == 0) {
			// Log.d(TAG, "Hiding");
			rb_o2.setVisibility(View.INVISIBLE);
		} else {
			rb_o2.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o2()) == 0)
				rbgOptions.check(rb_o2.getId());
			rb_o2.setText(q.getQ_o2());
			rb_o2.setTag(q.getQ_o2());
		}

		if (q.getQ_o3().compareTo("") == 0) {
			// Log.d(TAG, "Hiding");
			rb_o3.setVisibility(View.INVISIBLE);
		} else {
			rb_o3.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o3()) == 0)
				rbgOptions.check(rb_o3.getId());
			rb_o3.setText(q.getQ_o3());
			rb_o3.setTag(q.getQ_o3());
		}

		if (q.getQ_o4().compareTo("") == 0) {
			// Log.d(TAG, "Hiding");
			rb_o4.setVisibility(View.INVISIBLE);
		} else {
			rb_o4.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o4()) == 0)
				rbgOptions.check(rb_o4.getId());
			rb_o4.setText(q.getQ_o4());
			rb_o4.setTag(q.getQ_o4());
		}

		if (q.getQ_o5().compareTo("") == 0) {
			// Log.d(TAG, "Hiding");
			rb_o5.setVisibility(View.INVISIBLE);
		} else {
			rb_o5.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o5()) == 0)
				rbgOptions.check(rb_o5.getId());

			rb_o5.setText(q.getQ_o5());
			rb_o5.setTag(q.getQ_o5());
		}

		if (q.getQ_o6().compareTo("") == 0) {
			// Log.d(TAG, "Hiding");
			rb_o6.setVisibility(View.INVISIBLE);
		} else {
			rb_o6.setVisibility(View.VISIBLE);
			if (ans.compareTo(q.getQ_o6()) == 0)
				rbgOptions.check(rb_o6.getId());

			rb_o6.setText(q.getQ_o6());
			rb_o6.setTag(q.getQ_o6());
		}
		// OnClick on Radio Buttons handled by ocrListener
		rb_o1.setOnClickListener(ocrListener);
		rb_o2.setOnClickListener(ocrListener);
		rb_o3.setOnClickListener(ocrListener);
		rb_o4.setOnClickListener(ocrListener);
		rb_o5.setOnClickListener(ocrListener);
		rb_o6.setOnClickListener(ocrListener);
	}
}