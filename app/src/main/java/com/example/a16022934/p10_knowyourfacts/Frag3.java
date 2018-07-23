package com.example.a16022934.p10_knowyourfacts;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {

    int reqCode = 0003;
    LinearLayout ll;
    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag3, container, false);
        Button btnColorChange = view.findViewById(R.id.btnChangeColor);
        Button btnReadLater = view.findViewById(R.id.btnReadLater);
        ll = view.findViewById(R.id.frag3Linear);

        TextView tvFacts = view.findViewById(R.id.tvFact);
        String txt = "The six official languages of the United Nations are: English, French, Arabic, Chinese, Russian and Spanish.\n\n" +
                "Earth is the only planet not named after a god.\n\n\n" +
                "The worlds oldest piece of chewing gum is 9000 years old!";
        tvFacts.setText(txt);

        btnColorChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randInt = new Random().nextInt(4);
                if(randInt == 0){
                    ll.setBackgroundColor(getResources().getColor(R.color.white));
                }else if(randInt == 1){
                    ll.setBackgroundColor(getResources().getColor(R.color.blue));
                }else if(randInt == 2){
                    ll.setBackgroundColor(getResources().getColor(R.color.red));
                }else if(randInt == 3){
                    ll.setBackgroundColor(getResources().getColor(R.color.green));
                }


            }
        });

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 300); //amount 5 means, 5 seconds later, it pop up

                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(getActivity(), ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getActivity().getSystemService(Activity.ALARM_SERVICE);

                //Set the alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });
        return view;
    }

}
