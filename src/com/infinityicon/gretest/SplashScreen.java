package com.infinityicon.gretest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView ( R.layout.activity_splash_screen );
		
		new Handler ( ).postDelayed ( new Runnable() {
			public void run() {
				Intent intent = new Intent(SplashScreen.this,
						SubjectsList.class);
				startActivity ( intent );
				SplashScreen.this.finish ( );
			}
		}, 2000 );
	}
}
