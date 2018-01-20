package net.simplifiedcoding.speechtotext;

import android.Manifest;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.arasthel.asyncjob.AsyncJob;
import com.google.gson.Gson;

import net.simplifiedcoding.speechtotext.database.DBTablesInfo;
import net.simplifiedcoding.speechtotext.database.DatabaseHelper;
import net.simplifiedcoding.speechtotext.model.DataReceiveEvent;
import net.simplifiedcoding.speechtotext.model.VoiceToText;

import org.chalup.microorm.MicroOrm;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Locale;

public class RecordingService extends Service {
   String LOG_TAG="RecordingService";
  public static   SpeechRecognizer mSpeechRecognizer;
    public static   Intent mSpeechRecognizerIntent;
    public static   String TextMessage;
    DatabaseHelper db;
    VoiceToText voiceToText=new VoiceToText();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand","");
        EventBus.getDefault().register(this);
        StartRecording();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","call");
        EventBus.getDefault().unregister(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DataReceiveEvent event) throws ClassNotFoundException {
        Log.e("On data_received", "call--- "+new Gson().toJson(event));

        Log.e("mSpeechRecognizer","stop");
        if (event.isTagMatchWith("data_received")) {
            voiceToText=event.getResponseMessage();
            Save();
            mSpeechRecognizer.stopListening();

         //  data.setText(event.getResponseMessage().getArticles().get(0).getText());
        }

    }
private void StartRecording()
{
    mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());


    mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getApplicationContext().getPackageName());
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
    mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
Log.e("mSpeechRecognizer","start");
    mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Log.i(LOG_TAG, "onReadyForSpeech");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.i(LOG_TAG, "onBeginningOfSpeech");
        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {
            Log.i(LOG_TAG, "onBufferReceived: " + bytes);
        }

        @Override
        public void onEndOfSpeech() {
            Log.i(LOG_TAG, "onEndOfSpeech");
        }

        @Override
        public void onError(int i) {
            Log.d(LOG_TAG, "FAILED " + i);
        }

        @Override
        public void onResults(Bundle bundle) {
            //getting all the matches
            ArrayList<String> matches = bundle
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            //displaying the first match
            if (matches != null)
                TextMessage=matches.get(0);
            voiceToText.text=TextMessage;
Save();
            Log.e("TextMessage.",TextMessage+"");


        }

        @Override
        public void onPartialResults(Bundle bundle) {
            Log.i(LOG_TAG, "onPartialResults");
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
            Log.i(LOG_TAG, "onEvent");
        }
    });
}
    public void Save()
    {

        new AsyncJob.AsyncJobBuilder<Boolean>()
                .doInBackground(new AsyncJob.AsyncAction<Boolean>() {
                    @Override
                    public Boolean doAsync() {
                        ArrayList<ContentValues> contentValues = new ArrayList<>();
                        try {




                                Log.e("SurveyResponseInfo",""+new Gson().toJson(voiceToText));

                                MicroOrm uOrm = new MicroOrm();
                                ContentValues values = uOrm.toContentValues(voiceToText);
                                contentValues.add(values);

                            try {
                                boolean flag;
                                db = new DatabaseHelper(getApplicationContext());
                                flag = db.addItems(DBTablesInfo.MESSAGE_TABLE_NAME, contentValues);
                         Log.e("flag",""+flag);

                            }catch (Exception e)
                            {
                                Log.e("Exception2",""+e.getMessage()+"");
                            }
                            //questionInfos.clear();

                        } catch (Exception e) {

                            Log.e("Exception3",""+e.getMessage()+"");
                            //ToastyMsg.Error(getActivity(),"Something went wrong");

                        }
                        return true;
                    }
                })
                .doWhenFinished(new AsyncJob.AsyncResultAction<Boolean>() {
                    @Override
                    public void onResult(Boolean result) {
Log.e("doWhenFinished","call");
                        stopSelf();
                        // Toast.makeText(context, "Result was: " + result, Toast.LENGTH_SHORT).show();
                    }
                }).create().start();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
