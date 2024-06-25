package com.infinityicon.gretest;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class QuestionTitles extends Fragment {
	private OnItemSelectedListener listener;
	int quest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		quest = 0;
		View view = inflater.inflate( R.layout.fragment_q_titles, container, false);
		
		Button btnQNext = ( Button ) view.findViewById( R.id.btnQNext );
		btnQNext.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NextQuestion ( );
			}
		});
		
		Button btnQPrv = ( Button ) view.findViewById( R.id.btnQPrev );
		btnQPrv.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PrevQuestion ( );				
			}
		});
		
		Button btnEndTest = ( Button ) view.findViewById( R.id.btnEndTest );
		btnEndTest.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EndTest ( );			
			}
		});
		
		return view;
	}
	
	public interface OnItemSelectedListener {
		public void OnNextPreviousPressed ( int q );
		void OnEndTest();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if ( activity instanceof OnItemSelectedListener ) {
			listener = ( OnItemSelectedListener ) activity;
		}
	}
	
	public void NextQuestion ( ) {
		if ( quest < 10 )
			quest++;
		listener.OnNextPreviousPressed( quest );
	}
	
	public void PrevQuestion ( ) {
		if ( quest > 1 )
			quest--;
		listener.OnNextPreviousPressed( quest );
	}

	public void EndTest ( ) {
		listener.OnEndTest();
	}
}
