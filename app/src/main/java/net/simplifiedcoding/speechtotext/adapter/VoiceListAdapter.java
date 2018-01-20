package net.simplifiedcoding.speechtotext.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.simplifiedcoding.speechtotext.MainActivity;
import net.simplifiedcoding.speechtotext.R;
import net.simplifiedcoding.speechtotext.fragment.DetailsFragment;
import net.simplifiedcoding.speechtotext.model.VoiceToText;

import java.util.List;



/**
 * Created by Ariful Islam on 12/30/2017.
 */

public class VoiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
 List<VoiceToText>voiceToTexts;
   //int menuImages[];
    private static LayoutInflater inflater = null;
    //ImageLoader imageLoader;

    public VoiceListAdapter(Context context, List<VoiceToText> voiceToTexts) {
        this.context = context;
        this.voiceToTexts = voiceToTexts;
       // this.menuImages = menuImages;

        this.inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public VoiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.voicetotext_list, parent, false);

        return  new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView duration;

        TextView Phone_Number;
        TextView time;
        TextView name;
        TextView type;


        CardView data_holder;
        public ViewHolder(View itemView) {
            super(itemView);
           // this.ivMenuIcon = (ImageView) itemView.findViewById(R.id.ivMenuIcon);
            this.duration = (TextView) itemView.findViewById(R.id.duration);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.type = (TextView) itemView.findViewById(R.id.type);
            this.Phone_Number = (TextView) itemView.findViewById(R.id.Phone_Number);
            this.data_holder = (CardView) itemView.findViewById(R.id.data_holder);

            // this.imageView=(ImageView)itemView.findViewById(R.id.imageview);

            //this.lay = (LinearLayout) itemView.findViewById(R.id.lay);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        if(voiceToTexts!=null)
        {
           // Log.e("requsitionObjects"," size"+surveyResponseInfos.size());

            final ViewHolder holder=(ViewHolder)holder1;
          //  holder.ivMenuIcon.setImageResource(menuImages[position]);
            holder.time.setText((voiceToTexts.get(position)).datetime+"");
            holder.Phone_Number.setText((voiceToTexts.get(position)).phone+"");
            holder.duration.setText((voiceToTexts.get(position)).time+"");
            holder.name.setText((voiceToTexts.get(position)).name+"");
            holder.type.setText((voiceToTexts.get(position)).callType+"");

            holder.data_holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment=null;
                    fragment = new DetailsFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("Name",""+voiceToTexts.get(position).text);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = MainActivity.FragmentManagerDashBoard.beginTransaction();
                  //  transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
                    transaction.add(R.id.content_drawer, fragment,getClass().getName());
                    MainActivity.FragmentStack.lastElement().onPause();
                    transaction.hide(MainActivity.FragmentStack.lastElement());
                    MainActivity.FragmentStack.push(fragment);
                    transaction.commit();
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return voiceToTexts.size();
    }


}