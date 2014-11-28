package com.finalapp.Appkeep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//dealing with all database implementation

public class Equipment123 {
	
	//setting up database variables (final as they will not be changing)
	
	public static final String KEY_ROW_ID = "_id";
	public static final String KEY_SERIAL_NUM = "serial_num";
	public static final String KEY_EQUIP_NAME = "equipment_name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_LAST_DATE = "service_last_date";
	public static final String KEY_DUE_DATE = "service_due_date";
	
	//Setting up the Database name
	
	private static final String DATABASE_NAME = "equipment";
	private static final String DATABASE_TABLE = "full_list";
	private static final int DATABASE_VERSION = 3;
	
	
	
	//creating a database helper reference variable
	private DbHelper appKeepHelper;
	//context for this class
	private final Context appKeepContext;
	// SQLiteDatabase reference variable
	private SQLiteDatabase appKeepDatabase;
	
	//setting up the database helper
	
	private static class DbHelper extends SQLiteOpenHelper{

		
		
		
		//A helper class to manage database creation and version management
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		
		//onCreate method will be called the first time the database is created
		//It creates our database
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//Creating our database according to our column headings, ie. input variables
			//Row id is the primary key and we are saying that we want all fields to have an input, ie. not null
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_SERIAL_NUM + " TEXT NOT NULL" +
					KEY_EQUIP_NAME + " TEXT NOT NULL, " +
					KEY_LOCATION + " TEXT, " +
					KEY_LAST_DATE + " TEXT, " +
					KEY_DUE_DATE + " TEXT);"
					
		    );
			
		}
		
		//ever after it calls the onUpgrade method
		//if the table is already created, then the upgrade method is called
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			//drop the table and create the new updated database
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		}
	
	
	}
	
	//setting up a constructor for the Equipment123 class and passing in the context of our class
	public Equipment123(Context c){
		appKeepContext = c;
	}
	
	//method to open the database through the dbHelper
	public Equipment123 open()throws SQLException {
		//Creating an instance of the dbHelper. Class constructor takes in a Context
		appKeepHelper = new DbHelper(appKeepContext);
		//Creating an instance of the SQLiteDatabase
		appKeepDatabase = appKeepHelper.getWritableDatabase();
		return this;
			
	}
	//method to close the database
	public void close(){
		
		appKeepHelper.close();
	}

	//when we write to our database, we want to write the return from this method
	//ie it will insert into our database, the contentValues that we will set up in this method
	public long createEntry(String serialNum, String name, String location, String lastDate, String dueDate) {
		// TODO Auto-generated method stub
		
		ContentValues cv = new ContentValues();
		
		//putting each of our strings into the instance of the contentValues
		cv.put(KEY_SERIAL_NUM, serialNum);
		cv.put(KEY_EQUIP_NAME, name);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LAST_DATE, lastDate);
		cv.put(KEY_DUE_DATE, dueDate);
		
		//this line will insert all the above puts into the database table using contentValues cv
		return appKeepDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	
	//Returning data from the database in the form of a String
	public String getData() {
		// TODO Auto-generated method stub
		//makes a string array out of our database columnsh
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		
		//reading information from the columns using the cursor
		//this is where we can make queries using selection, having, order by / group by etc
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		//closes the cursor automatically for us when no longer needed. Program seems to need this.
		startManagingCursor();
		//String to hold the return value of all the elements in our database 
		String result = "";
		
		//setting up ints to represent each of the column indices
		int iRow = c.getColumnIndex(KEY_ROW_ID);
		int iSerialNum = c.getColumnIndex(KEY_SERIAL_NUM);
		int iName = c.getColumnIndex(KEY_EQUIP_NAME);
		int iLocation = c.getColumnIndex(KEY_LOCATION);
		int iLastDate= c.getColumnIndex(KEY_LAST_DATE);
		int iDueDate = c.getColumnIndex(KEY_DUE_DATE);
		
		//read all the data from the database in order to make a String out of it to return through the getData() method
		//and back to the Database_View123 class where it was called in the onCreate() method
		//For me, this represents a new way of doing a for loop, ie using c.someMethod()
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			//result here represents one line of our database 
			result = result + c.getString(iRow) + "\t" +
					c.getString(iSerialNum) + "\t" + 
					c.getString(iName) + "\t" +
					c.getString(iLocation) + "\t" +
					c.getString(iLastDate) + "\t" +
					c.getString(iDueDate) + "\n";
		}
		
		//returned result here will be the concatinated result of all the elements of the database, across all lines
		return result;
	}

	private void startManagingCursor() {
		// TODO Auto-generated method stub
		
	}

	public String getNum(long l) throws SQLException{
		// TODO Auto-generated method stub
		//makes a string array out of our database columnsh
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		//reading information from the columns using the cursor
		//this time we make a selection based on the input of the long, l from our onClickListener for the editText Query in the 
		//Homepage, Database_Home123 class
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
		startManagingCursor();
		
		if (c != null){
			//move to the row
			c.moveToFirst();
			//get data from the first column no.1
			String sNum = c.getString(1);
			return sNum;
			
		}
		return null;
	}
	//repeat this method for all of our EditText views
	
	
	public String getEquipName(long l) throws SQLException{
		// TODO Auto-generated method stub
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
		startManagingCursor();
		
		if (c != null){
			//move to the row
			c.moveToFirst();
			//get data from the first column no.1
			String eName = c.getString(2);
			return eName;
			
		}
		return null;
	}

	public String getLocation(long l) throws SQLException{
		// TODO Auto-generated method stub
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
		startManagingCursor();
		
		if (c != null){
			//move to the row
			c.moveToFirst();
			//get data from the first column no.1
			String loc = c.getString(3);
			return loc;
		}
		
		return null;
	}

	public String getLast(long l) throws SQLException{
		// TODO Auto-generated method stub
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
		startManagingCursor();
		
		if (c != null){
			//move to the row
			c.moveToFirst();
			//get data from the first column no.1
			String lDate = c.getString(4);
			return lDate;
		}
		return null;
	}

	public String getDue(long l) throws SQLException{
		// TODO Auto-generated method stub
		String [] columns = new String [] {KEY_ROW_ID, KEY_SERIAL_NUM, KEY_EQUIP_NAME, KEY_LOCATION, KEY_LAST_DATE, KEY_DUE_DATE};
		Cursor c = appKeepDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + l, null, null, null, null);
		startManagingCursor();
		
		if (c != null){
			//move to the row
			c.moveToFirst();
			//get data from the first column no.1
			String dDate = c.getString(5);
			return dDate;
		}
		return null;
	}

	public void updateEntry(long lRow, String editSerialNum, String editName,
			String editLocation, String editLDate, String editDDate) throws SQLException{
		// TODO Auto-generated method stub
		
		//putting each of our strings into the instance of the contentValues
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(KEY_SERIAL_NUM, editSerialNum);
		cvUpdate.put(KEY_EQUIP_NAME, editName);
		cvUpdate.put(KEY_LOCATION, editLocation);
		cvUpdate.put(KEY_LAST_DATE, editLDate);
		cvUpdate.put(KEY_DUE_DATE, editDDate);
		
			//Updating the database
		appKeepDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROW_ID + "=" + lRow, null);
		
	}

	public void deleteEntry(long lRowDelete) throws SQLException{
		// TODO Auto-generated method stub
		appKeepDatabase.delete(DATABASE_TABLE, KEY_ROW_ID + "=" + lRowDelete, null);
		
	}


}

