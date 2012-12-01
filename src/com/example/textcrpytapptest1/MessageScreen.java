package com.example.textcrpytapptest1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageScreen extends Activity {
	
	private static final int CONTACT_PICKER_RESULT = 1001;
	private static final String DEBUG_TAG = null;
	private String phoneNumber = "";
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.message_screen);
	
	}
	
	public void pickContact(View view) {
	    Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
	            Contacts.CONTENT_URI);
	    startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
	}
	
	

	
	 public void send(View v)
	 {
		 EditText messageField = (EditText) findViewById(R.id.txtMessage);
		 String message = "";
		 message = messageField.getText().toString();
		 
		 if(phoneNumber.length() > 0 && message.length()>0)
		 {
			 sendSMS(phoneNumber,message);
		 } else
			 Toast.makeText(getApplicationContext(),"Invalid Input", Toast.LENGTH_SHORT).show();
		 MainActivity.addThread(this.phoneNumber);
	 }
	 private void sendSMS(String phoneNumber,String template)
	    {
	        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,new Intent(getApplicationContext(),MainActivity.class), 0);
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, template, pi, null);
	    }

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    if (resultCode == RESULT_OK) {
	        switch (requestCode) {
	        case CONTACT_PICKER_RESULT:
	            Cursor cursor = null;
	            String phone = "";
	            try {
	                Uri result = data.getData();
	                Log.v(DEBUG_TAG, "Got a contact result: "
	                        + result.toString());
	                // get the contact id from the Uri
	                String id = result.getLastPathSegment();
	                // query for everything email
	                cursor = getContentResolver().query(Phone.CONTENT_URI,
	                        null, Phone.CONTACT_ID + "=?", new String[] { id },
	                        null);
	                int phoneId = cursor.getColumnIndex(Phone.DATA);
	                // let's just get the first email
	                if (cursor.moveToFirst()) {
	                    phone = cursor.getString(phoneId);
	                    Log.v(DEBUG_TAG, "Got number: " + phone);
	                } else {
	                    Log.w(DEBUG_TAG, "No results");
	                }
	            } catch (Exception e) {
	                Log.e(DEBUG_TAG, "Failed to get phone number", e);
	            } finally {
	                if (cursor != null) {
	                    cursor.close();
	                }
	                EditText phoneNumber = (EditText) findViewById(R.id.txtPhoneNum);
	                setNumber(phone);
	                phoneNumber.setText(phone);
	                if (phone.length() == 0) {
	                    Toast.makeText(this, "No number found for contact.",
	                            Toast.LENGTH_LONG).show();
	                }
	            }
	            break;
	        }
	    } else {
	        Log.w(DEBUG_TAG, "Warning: activity result not ok");
	    }
	}  
	
	public void setNumber(String number)
	{
		this.phoneNumber = number;
	}

}
