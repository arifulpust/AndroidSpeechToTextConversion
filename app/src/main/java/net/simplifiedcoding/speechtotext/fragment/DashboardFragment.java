package net.simplifiedcoding.speechtotext.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import net.simplifiedcoding.speechtotext.MainActivity;
import net.simplifiedcoding.speechtotext.R;
import net.simplifiedcoding.speechtotext.adapter.VoiceListAdapter;
import net.simplifiedcoding.speechtotext.database.DBTablesInfo;
import net.simplifiedcoding.speechtotext.database.DatabaseHelper;
import net.simplifiedcoding.speechtotext.model.VoiceToText;
import net.simplifiedcoding.speechtotext.utils.Validation;

import org.chalup.microorm.MicroOrm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.lay)
    RelativeLayout lay;
    DatabaseHelper db;
    Unbinder unbinder;
    VoiceListAdapter voiceListAdapter;
    List<VoiceToText> voiceToTexts=new ArrayList<>();
    ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        MainActivity.titleBar.setText("Dashboard");
        unbinder= ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listView.setHasFixedSize(true);
        listView.setLayoutManager(linearLayoutManager);

        db=new DatabaseHelper(getActivity());
        for(int i=0;i<lay.getChildCount();i++)
        {
            if( lay.getChildAt( i ) instanceof EditText)
            {
                Validation.showSoftKeyBoard(lay.getChildAt( i ),getActivity());
            }
        }
        Validation.hideSoftKeyBoard(lay,getActivity());
        return view;
    }

    private void GetDataFromOffline()
    {
        db = new DatabaseHelper(getActivity());
        // Cursor cursor = db.getAllResItems(DBTables.ADDPRESCRIPTION_TABLE_NAME);
        Cursor cursor = db.getAllResItems(DBTablesInfo.MESSAGE_TABLE_NAME,DBTablesInfo.ID_KEY);
        MicroOrm uOrm = new MicroOrm();
        List<VoiceToText> voiceToTexts = uOrm.listFromCursor(cursor, VoiceToText.class);
        if(voiceToTexts!=null&&voiceToTexts.size()>0) {
            Log.e("total size 2", "" + voiceToTexts.size()+new Gson().toJson(voiceToTexts));
            voiceListAdapter=new VoiceListAdapter(getActivity(),voiceToTexts);
            listView.setAdapter(voiceListAdapter);
        }
        else
            Log.e("null","call");

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.titleBar.setText("Dashboard");
        MainActivity.shouldShowBackButton(false);
        GetDataFromOffline();
    }
    }
