package net.simplifiedcoding.speechtotext;


import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import net.simplifiedcoding.speechtotext.database.AppData;
import net.simplifiedcoding.speechtotext.model.DataReceiveEvent;
import net.simplifiedcoding.speechtotext.model.VoiceToText;
import net.simplifiedcoding.speechtotext.utils.Validation;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class ReceiverNumber extends BroadcastReceiver {
	private static final int MODE_WORLD_READABLE = 1;

	private String incommingNumber;
	// private TelephonyManager telephonyManager;
	private String incommingName=null;
	private SharedPreferences myPrefs; 
String TAG="ReceiverNumber";

	public  static  String PhoneMunber="";
public  static String Name="";
	public  static  String Text="";
	public  static  String StartTime="";
	public  static  String EndTime="";
	public  static  String TotalTime="";
	public  static  String CallType="";
	RecordingService mService;
	boolean isServiceRunning = false;
	boolean isService;
	//private BroadcastReceiver CallBlocker;
	 Context context;
	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		 isService= AppData.getBool(AppData.service,context);
		Log.e(TAG,""+isService);
		this.context=context;
		if(!isService)
			return;
		 String blockingMode="";
		TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		telephony.listen(new PhoneStateListener(){
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				super.onCallStateChanged(state, incomingNumber);
				//System.out.println("incomingNumber1 : "+incomingNumber);
//				Intent in = new Intent(context, RecordingService.class);
//				if(!Validation.isServiceRunning(context,RecordingService.class))
//				{
//					Log.e(TAG, "start sevicek");
//
//					context.startService(in);
//				}
				PhoneMunber=incomingNumber;
				Log.e("incomingNumber1", GetCurrentDateTime()+"   "+incomingNumber);
			}
		},PhoneStateListener.LISTEN_CALL_STATE);

		if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
			Log.e("OUTGOING_CALL"," call");
			CallType="Outgoing";
		}
		else
		{
			CallType="Incomming";

			Log.e("INCOMMING_CALL"," call");
		}

		if(intent.getAction().equals("android.intent.action.PHONE_STATE")){

			String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

			if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
				Log.e(TAG, GetCurrentDateTime()+"   "+"off hook");
				StartTime=	GetCurrentDateTime();
				Intent in = new Intent(context, RecordingService.class);
				if(!Validation.isServiceRunning(context,RecordingService.class))
				{
					Log.e(TAG, "start sevicek");

					context.startService(in);
				}
				else
				{
					Log.e(TAG, "start sevice after stop");
					context.stopService(in);
					context.startService(in);
				}

				//context.startService(intent);
				//	String number = intent.getStringExtra(TelephonyManager.EXTRA_PHONE_NUMBER);
				//Log.e(TAG, "outgoing number : " + number);
			}

			else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
				Log.e(TAG, GetCurrentDateTime()+"   "+"RINGING");
				String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
				Log.e(TAG, "incoming number2 : " + number);


			}
			else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
				EndTime=	GetCurrentDateTime();
				Log.e(TAG, GetCurrentDateTime()+"   "+"IDLE");

				SetDutyTime();
//				if(!isServiceRunning) {
//					Intent in = new Intent(context, RecordingService.class);
//					context.stopService(in);
//				}
			}
			}
			// if(blockingMode.equals("outgoing"))
		 {
			 Bundle b = intent.getExtras();

			 String outGoingNumber = b.getString(Intent.EXTRA_PHONE_NUMBER);
			 outGoingNumber=getContactDisplayNameByNumber(PhoneMunber, context);
			// Log.e("outGoingNumber33",""+outGoingNumber);
			 //blockCall(context, b);

		 }


       }


	public void SetDutyTime()
	{


		Date date1,date2;
		long difference=0,hour=0,min=0,sec=0,h=3600,m=60,s=60;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			date1 = format.parse(StartTime);
			date2 = format.parse(EndTime);
			difference = (date2.getTime() - date1.getTime())/(1000);
			Log.e("difference d 11---",""+difference+" - "+StartTime+"  "+EndTime);
			long hours = difference/ 3600;
			min = (difference / 60)%60;
			sec = (difference) % 60;



			Log.e("difference d---",""+difference+" - ");
		}catch (Exception e)
		{
			Log.e("Exception",""+e.getMessage());
		}
		TotalTime=""+hour+":"+min+":"+sec;
		VoiceToText voiceToText=new VoiceToText();
		voiceToText.text="hello";
		voiceToText.datetime=StartTime;
		voiceToText.time=TotalTime;
		voiceToText.name=Name;
		voiceToText.phone=PhoneMunber;
		voiceToText.callType=CallType;
		//  Deauty_time.setText(""+difference);
		EventBus.getDefault().post(new DataReceiveEvent("data_received", voiceToText));

		 Log.e("sumary of call","--s"+StartTime+" \n e"+ EndTime+"  \n"+TotalTime+"--\n"+PhoneMunber+"--\n"+Name);
	}
	private String  GetCurrentDateTime()
	{

		DateFormat df1=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");//foramt date
		String date=df1.format(Calendar.getInstance().getTime());
		return date;//mYear+"-"+(mMonth+1)+"-"+mDay;
	}

	public String getContactDisplayNameByNumber(String number, Context c) {

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "?";
        String data=null;
        ContentResolver contentResolver =c.getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                data = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                if(data==null||data.equals(""))
				{
					Name="Unknown";
				}else
				Name=data;
                Log.e("name",""+data+"    "+GetCurrentDateTime()+"   ");
                //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
            }
		} finally {
            if (contactLookup != null) {
                contactLookup.close();
               
            }
        }
        
        return data;
    }  
	
	
	
}
