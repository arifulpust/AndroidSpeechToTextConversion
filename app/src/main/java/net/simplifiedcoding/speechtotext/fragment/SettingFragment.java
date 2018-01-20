package net.simplifiedcoding.speechtotext.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import net.simplifiedcoding.speechtotext.MainActivity;
import net.simplifiedcoding.speechtotext.R;
import net.simplifiedcoding.speechtotext.RecordingService;
import net.simplifiedcoding.speechtotext.database.AppData;
import net.simplifiedcoding.speechtotext.model.DataReceiveEvent;
import net.simplifiedcoding.speechtotext.model.VoiceToText;
import net.simplifiedcoding.speechtotext.utils.Validation;

import org.greenrobot.eventbus.EventBus;


public class SettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
ToggleButton toggBtn;
String TAG="SettingFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        toggBtn=(ToggleButton)view.findViewById(R.id.toggBtn);
        boolean isService= AppData.getBool(AppData.service,getActivity());
        Log.e("isService",""+isService);
        if(isService)
        {
            toggBtn.setChecked(true);
        }
        else
        {
            toggBtn.setChecked(false);

        }
        MainActivity.titleBar.setText("Setting");

        MainActivity.shouldShowBackButton(false);
        toggBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), RecordingService.class);

                if(toggBtn.isChecked())
                {
                    AppData.setBool(AppData.service,true,getActivity());

//                    if(!Validation.isServiceRunning(getActivity(),RecordingService.class))
//                    {
//                        Log.e(TAG, "start sevicek");
//
//                        getActivity().startService(in);
//                    }
//                    else
//                    {
//                        Log.e(TAG, "start sevice after stop");
//                        getActivity().stopService(in);
//                        getActivity().startService(in);
//                    }
                }
                else
                {
                    AppData.setBool(AppData.service,false,getActivity());
                   // EventBus.getDefault().post(new DataReceiveEvent("data_received", new VoiceToText()));



                }

            }
        });
        return view;
    }


}
