package com.finalapp.Appkeep;



import com.finalapp.Appkeep.*;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Database_Home123 extends ActionBarActivity {
	
	//Defining buttons for the DATABASE HOMEPAGE
	
		Button create, view, query, edit, delete;
		
		EditText sqlSerialNum, sqlName, sqlLocation, sqlLastDate, sqlDueDate, sqlItemQuery;
		//Toast t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_database__home123);
		
		//setting up the connection between xml and java files for editText view and button views
		 //sqlSerialNum = (EditText) findViewById(R.id.et_db_serial_num);
		 //sqlName = (EditText) findViewById(R.id.et_db_equip_name);
		 //sqlLocation = (EditText) findViewById(R.id.et_db_eqip_location);
		 //sqlLastDate = (EditText) findViewById(R.id.et_db_set_last_date);
		 //sqlDueDate = (EditText) findViewById(R.id.et_db_set_due_date);
		 //sqlItemQuery = (EditText) findViewById(R.id.et_query_by_row);
		
		 //create = (Button) findViewById(R.id.bn_db_input);
		 //view = (Button) findViewById(R.id.bn_db_view);		
		 //query = (Button) findViewById(R.id.bn_db_set_query);
		 //edit = (Button) findViewById(R.id.bn_db_edit);
		 //delete = (Button) findViewById(R.id.bn_db_delete);
		 
		
		 
	
	     //setting up an onClickListener for the create button
		 /*When you hit update, it gets the text and assigns the text to a string variable
		  * We create an instance of the Equipment123 class, pass in the context of 'this'
		  * This gets passed into the constructor for the Equipment123 class in Equipment123.java file
		  * The context that gets created in this constructor class gets used in the database open() method where
		  * the database helper class (dbHelper) gets instantiated.
		  * 
		  * From there, the dbHelper will create the database using the super class SQLiteOpenHelper and the onCreate() method
		  * where an SQLiteDatabase object is passed in and the attributes or 'columns' of the database are set up with SQL code
		  * 
		  * If a database has already been created, the program goes to the onUpgrade() method in the same class, drops the existing
		  * database and re-creates it using the createEntry() method in the same, Equipment123 class. Here, the strings from the 
		  * Database_Home123.java file are passed in.
		  * 
		  * Database is created using more SQL code and then the database is closed with the .close() method
		  * 
		  */
	        create.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					
					//catch exception to make sure that the createEntry into the database works
					boolean entryCheck = true;
					
					try{
					//strings to take the input from our user in the database homescreen
					String serialNum = sqlSerialNum.getText().toString();
					String name = sqlName.getText().toString();
					String location = sqlLocation.getText().toString();
					String lDate = sqlLastDate.getText().toString();
					String dDate = sqlDueDate.getText().toString();
					
					//new instance of the Equipment123 class to deal with our entry
					Equipment123 input = new Equipment123(Database_Home123.this);
					
					//open database
					input.open();
					
					//create the entry
					input.createEntry(serialNum, name, location, lDate, dDate);
					
					//close database
					input.close();
					}catch (Exception e){
						//if the thing doesn't work, we want to show a dialogue
						entryCheck = false;
						//set up a dialog to show the user the entry was unsuccessful
						Dialog d = new Dialog(Database_Home123.this);
						
						d.setTitle("Update");
						TextView tv = new TextView(Database_Home123.this);
						tv.setText("Unsuccessful");
						d.setContentView(tv);
						d.show();
					}finally{
						//print this if it works ok
						if(entryCheck){
							//set up a dialog to show the user the entry was successful
							Dialog d = new Dialog(Database_Home123.this);
							d.setTitle("Update");
							TextView tv = new TextView(Database_Home123.this);
							tv.setText("Successfully added");
							d.setContentView(tv);
							d.show();
						}
						
					}
					
					
					//add a toast to say updated
					//Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
					
				}
			});
	     
	      //setting up an onClickListener for the view button
	        view.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//intention to open up the SQL activity where we can view the full database
					Intent i = new Intent("ie.nuim.cs.appkeep.DATABASE_VIEW123");
					startActivity(i);
					
				}
			});
	        
	      //setting up an onClickListener for the date_of_last-service editText
	        sqlLastDate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//intention to open up the datePicker screen
					Intent i = new Intent("ie.nuim.cs.appkeep.DATE_PICKER_DIALOG123");
					startActivity(i);
					
					
					
				}
			}); 
	      
	        //setting up an onClickListener for the due-date-for-service editText
	       sqlDueDate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			}); 
	        

	        //setting up an onClickListener for the query button
	        query.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				try{
					//refer to the SQL serial number in the editText for the query, 
					//this string will capture and return whatever is in our editText
					String s = sqlItemQuery.getText().toString();
					//converting the String to a long
					long l = Long.parseLong(s);
					//as with the onClickListener method above for the create button, we create an instance of our Equipment123 class
					//open, get info from and close the database
					Equipment123 eq = new Equipment123(Database_Home123.this);
					
					eq.open();
					//setting up strings to hold the info we get back from each column of the database
					String returnedSerialNum = eq.getNum(l);
					String returnedEquipName = eq.getEquipName(l);
					String returnedLocation = eq.getLocation(l);
					String returnedLastDateService = eq.getLast(l);
					String returnedDueDateService = eq.getDue(l);
					
					eq.close();
					
					//get the editTexts that we used to create an entry to equal the returned value from our query
					sqlSerialNum.setText(returnedSerialNum);
					sqlName.setText(returnedEquipName);
					sqlLocation.setText(returnedLocation);
					sqlLastDate.setText(returnedLastDateService);
					sqlDueDate.setText(returnedDueDateService);
					
				}catch (Exception e){
					//if the thing doesn't work, we want to show a dialogue
					
					//set up a dialog to show the user the entry was unsuccessful
					Dialog d = new Dialog(Database_Home123.this);
					
					d.setTitle("Query");
					TextView tv = new TextView(Database_Home123.this);
					tv.setText("Unsuccessful");
					d.setContentView(tv);
					d.show();
				}
					
					
				}
			}); 
	        
	        
	       
	        
	        
	        //setting up an onClickListener for the edit button
	       edit.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				try{	
					//strings to take the input from our user in the database homescreen
					String editSerialNum = sqlSerialNum.getText().toString();
					String editName = sqlName.getText().toString();
					String editLocation = sqlLocation.getText().toString();
					String editLDate = sqlLastDate.getText().toString();
					String editDDate = sqlDueDate.getText().toString();
					
					String sRow = sqlItemQuery.getText().toString();
					//converting the String to a long
					long lRow = Long.parseLong(sRow);
					//as with the onClickListener method above for the create button, we create an instance of our Equipment123 class
					//open, get info from and close the database
					Equipment123 eqEdit = new Equipment123(Database_Home123.this);
					
					eqEdit.open();
					//calling a method that we have created to update the entry
					//we pass in the long of the row we are working with 
					eqEdit.updateEntry(lRow, editSerialNum, editName, editLocation, editLDate, editDDate);
					
					eqEdit.close();
				}catch (Exception e){
					//if the thing doesn't work, we want to show a dialogue
					
					//set up a dialog to show the user the entry was unsuccessful
					Dialog d = new Dialog(Database_Home123.this);
					
					d.setTitle("Edit/Update");
					TextView tv = new TextView(Database_Home123.this);
					tv.setText("Unsuccessful");
					d.setContentView(tv);
					d.show();
				}
					
				}
			});
	        
	        
	        //setting up an onClickListener for the delete button
	       delete.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				try{
					String sRowDelete = sqlItemQuery.getText().toString();
					//converting the String to a long
					long lRowDelete = Long.parseLong(sRowDelete);
					
					Equipment123 eqDelete = new Equipment123(Database_Home123.this);
					
					eqDelete.open();
					
					eqDelete.deleteEntry(lRowDelete);
					
					eqDelete.close();
					
				}catch (Exception e){
					//if the thing doesn't work, we want to show a dialogue
					
					//set up a dialog to show the user the entry was unsuccessful
					Dialog d = new Dialog(Database_Home123.this);
					
					d.setTitle("Delete");
					TextView tv = new TextView(Database_Home123.this);
					tv.setText("Unsuccessful");
					d.setContentView(tv);
					d.show();
				}
					
				}
			});
		 
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.database__home123, menu);
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
}
