package net.simplifiedcoding.speechtotext;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import net.simplifiedcoding.speechtotext.adapter.MenuAdapter;
import net.simplifiedcoding.speechtotext.database.DatabaseHelper;
import net.simplifiedcoding.speechtotext.fragment.DashboardFragment;
import net.simplifiedcoding.speechtotext.fragment.SettingFragment;
import net.simplifiedcoding.speechtotext.fragment.TestVoiceToTextFragment;
import net.simplifiedcoding.speechtotext.fragment.VoiceRecordFragment;
import net.simplifiedcoding.speechtotext.utils.FragmentClearBackStack;

import org.chalup.microorm.MicroOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    public static Toolbar toolbar;
    public static ImageButton drawer_menu;
    public static ImageButton back_button;
    public static TextView titleBar;
    public static FragmentManager FragmentManagerDashBoard;
    public static Stack<Fragment> FragmentStack;
    Unbinder unbinder;
    //"Orders","Delivery",R.drawable.orders,R.drawable.delivery,
    private String[] menuNames= {"Dashboard","Test","Voice Recorder","Setting"};
    private int[] menuImages= {R.drawable.dashboard,R.drawable.survey,R.drawable.dashboard,R.drawable.settings
    };

    //imgProfile
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.lst_menu_items)
    ListView menuList;
    @BindView(R.id.content_drawer)
    FrameLayout frameLayout;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.upload)
    ImageView upload;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtDesgination)
    TextView txtDesgination;
    Activity activity;
    boolean isdashboard=true;
    DatabaseHelper db;
    long total=0;

    //  public static int displayPosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManagerDashBoard = getSupportFragmentManager();
        FragmentStack = new Stack<>();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_drawer,new DashboardFragment(),this.toString()).commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_menu = (ImageButton) findViewById(R.id.drawer_menu);
        back_button = (ImageButton) findViewById(R.id.back_button);
        titleBar = (TextView) findViewById(R.id.titleBar);
        //  upload = (ImageView) findViewById(R.id.upload);
        unbinder=   ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        db=new DatabaseHelper(this);
        activity=this;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        MenuAdapter adapter = new MenuAdapter(this, menuNames, menuImages);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("pressed", position+"");

                if(drawer!=null)
                    drawer.closeDrawers();
                setMenu(position);
            }
        });


        setMenu(0);
        //total= db. getAllSurveyItem();


    }
    void checkStoragePermission(){
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.RECORD_AUDIO,Manifest.permission.PROCESS_OUTGOING_CALLS,Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE,Manifest.permission.CALL_PHONE};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        try{

            // Toast.makeText(LoginActivity.this, "We got the permission.Thanks", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            //  Toast.makeText(LoginActivity.this, "Sorry some portion of the application may not work", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private void getData()
    {

    }
    @Override
    protected void onResume() {
        super.onResume();
        checkStoragePermission();
        getData();
        // if(total==0)
        {
            // upload.setVisibility(View.GONE);
        }
        //  else
        {
            upload.setVisibility(View.VISIBLE);

        }
    }


    void setMenu(int position){

        FragmentClearBackStack.clearBackStack(FragmentManagerDashBoard);
        FragmentManagerDashBoard.popBackStack();
        FragmentStack.clear();
        Fragment fragment = null;
        FragmentTransaction transaction = FragmentManagerDashBoard.beginTransaction();
        switch (position){
            case 0:
                fragment = new DashboardFragment();
                break;
            case 1:
                fragment = new TestVoiceToTextFragment();
                break;
                //VoiceRecordFragment
            case 2:
                fragment = new VoiceRecordFragment();
                break;
            case 3:
                fragment = new SettingFragment();
                break;

            default:
                break;
        }
        if(fragment == null)
            return;
        transaction.replace(R.id.content_drawer, fragment);
        FragmentStack.push(fragment);
        transaction.commit();
    }
    public static void shouldShowBackButton(boolean flag){
        if(flag){

            back_button.setVisibility(View.VISIBLE);
            drawer_menu.setVisibility(View.GONE);
        }else{

            back_button.setVisibility(View.GONE);
            drawer_menu.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.drawer_menu)
    void menuPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        unbinder.unbind();
    }
    @OnClick(R.id.back_button)
    void btnBackPressed(){
        Log.w("fragment", " fragment stack size" + FragmentStack.size());
        if(FragmentStack.size()>0){
            try {
                FragmentTransaction transaction;
                transaction = FragmentManagerDashBoard
                        .beginTransaction();
                //transaction.setCustomAnimations(R.anim.slide_enter,R.anim.slide_exit);
                FragmentStack.lastElement().onPause();
                transaction.remove(FragmentStack.pop());
                FragmentStack.lastElement().onResume();
                transaction.show(FragmentStack.lastElement());
                transaction.commit();

            }catch (Exception e)

            {
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        }
        else
        {
            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            // transaction.setCustomAnimations(R.anim.slide_enter,R.anim.slide_exit);
        }
    }

    @Override
    public void onBackPressed() {
        Log.e("onBackPressed",""+FragmentStack.size());

        if (FragmentStack.size() >= 2) {

            FragmentTransaction ft = FragmentManagerDashBoard
                    .beginTransaction().setCustomAnimations(R.anim.pull_in_left, R.anim.push_out_right);
            FragmentStack.lastElement().onPause();
            ft.remove(FragmentStack.pop());
            FragmentStack.lastElement().onResume();
            ft.show(FragmentStack.lastElement());
            ft.commit();


        } else {

            if(!isdashboard)
            {
                isdashboard=true;
                //  quit=false;
                FragmentClearBackStack.clearBackStack(FragmentManagerDashBoard);
                FragmentManagerDashBoard.popBackStack();
                FragmentStack.clear();
                Fragment fragment = new DashboardFragment();
                FragmentTransaction transaction = FragmentManagerDashBoard.beginTransaction().setCustomAnimations(R.anim.pull_in_left, R.anim.push_out_right);;
                transaction.replace(R.id.content_drawer, fragment);
                FragmentStack.push(fragment);
                transaction.commit();

            }
            else {

                //  quit=true;
//                quitAlertDialog(MainActivity.this, "Quit Alert",
//                        "Do you want to quit?", "Yes", "No", "quit_alert");

                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                finish();
            }

        }
//        quitAlertDialog(MainActivity.this, "Quit Alert",
//                "Do you want to quit?", "Yes", "No", "quit_alert");
    }


}
