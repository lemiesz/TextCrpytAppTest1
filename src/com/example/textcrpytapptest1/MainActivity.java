package com.example.textcrpytapptest1;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
  	private static ArrayList<String> values = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button next = (Button) findViewById(R.id.btnNewMessage);
            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), MessageScreen.class);
                    startActivityForResult(myIntent, 0);
                }

            });
        
            
            
          	ListView listView = (ListView) findViewById(R.id.mylist);
          //	values.add("Hello World");
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        			  android.R.layout.simple_list_item_1, android.R.id.text1, values);
            listView.setAdapter(adapter); 
            
            listView.setOnItemClickListener(new OnItemClickListener() {
            	  public void onItemClick(AdapterView<?> parent, 
            			  View view, int position, long id) {
            	    Toast.makeText(getApplicationContext(),
            	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
            	      .show();
            	  }
            	}); 

        
    }
    public void contactList(View v)
    {
    	ListView listView = (ListView) findViewById(R.id.mylist);
    	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
    			  "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
    			  "Linux", "OS/2" };
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    			  android.R.layout.simple_list_item_1, android.R.id.text1, values);
    	listView.setAdapter(adapter); 

    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public static void addThread(String phoneNumber)
    {
    	values.add(phoneNumber);
    }
}
