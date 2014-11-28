package com.finalapp.Appkeep;


//import ie.nuim.cs.appkeep.aboutpage;

//import ie.nuim.cs.appkeep.databasepage;
//import ie.nuim.cs.appkeep.notificationpage;
//import ie.nuim.cs.appkeep.timelinepage;  //all need to be changed when the rest are in place.
import com.finalapp.*;
import android.R;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HomePage extends Activity implements OnClickListener {
	
	
	Button databaseButton; //button that links to the database, button1 in homepage.xml
	Button aboutButton;	   //button that links to the about page, button2 in homepage.xml
	Button timelineButton; //button that links to the timeline page, button3 in homepage.xml
	Button notificationButton; //button that links to the notification page, button4 in homepage.xml
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		databaseButton = (Button) findViewById(R.id.button1);
		databaseButton.setOnClickListener(this);
		
		aboutButton = (Button) findViewById(R.id.button2);
		aboutButton.setOnClickListener(this);
		
		timelineButton = (Button) findViewById(R.id.button3);
		timelineButton.setOnClickListener(this);
		
		notificationButton = (Button) findViewById(R.id.button4);
		notificationButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		 switch(v.getId()) {
		 
		 case R.id.button1:
	           
			 Intent databaseIntent=new Intent(getApplicationContext(), Database_Home123.class); //replace databasepage with actual name of database file
		       startActivity(databaseIntent);
			 
	           break;
	           case R.id.button2:
	        	   
	        	   
	        	   Intent aboutIntent=new Intent(getApplicationContext(), aboutpage.class); //replace aboutpage with actual name of about file
	    	       startActivity(aboutIntent);
	        	   
	        	   
	           break;
	           case R.id.button3:
	        	   
	        	   Intent timelineIntent=new Intent(getApplicationContext(), MainActivity.class); //replace timelinepage with actual name of timeline file
	    	       startActivity(timelineIntent);
	    	       
	    	   break;
	           case R.id.button4:
	        	   
	        	   Intent notificationIntent=new Intent(getApplicationContext(), AlarmActivity.class); //replace notificationpage with actual name of notification file
	    	       startActivity(notificationIntent);
	    	       
	    	   break;
	           
		
	       
		 }
	}
	
	

}