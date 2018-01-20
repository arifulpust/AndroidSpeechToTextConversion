package net.simplifiedcoding.speechtotext.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.simplifiedcoding.speechtotext.MainActivity;
import net.simplifiedcoding.speechtotext.R;


public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
String text;
TextView details;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details, container, false);
        details=(TextView)view.findViewById(R.id.details);
        MainActivity.titleBar.setText("Detail");
        Bundle args = getArguments();
        if (args != null)
            text=   args.getString("Name");
        Log.e("text",""+text);
        details.setText(text);

        MainActivity.titleBar.setText("Details");
        MainActivity.shouldShowBackButton(true);
        return view;
    }


}
