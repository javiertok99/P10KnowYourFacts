package com.example.a16022934.p10_knowyourfacts;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag4 extends Fragment {

    ImageView ivTrivia;
    Button btnChangeImage, btnReadLater;
    int reqCode = 0004;
    int randInt;

    String[] images = {"https://78.media.tumblr.com/59174c6ecd883dab416b59676c30b2fe/tumblr_pcaw1cXox31roqv59o1_500.png",
    "https://78.media.tumblr.com/59174c6ecd883dab416b59676c30b2fe/tumblr_pcaw1cXox31roqv59o1_500.png",
    "https://78.media.tumblr.com/032b8e72d8887fc67b39afe1117ac451/tumblr_pca72zjZjc1roqv59o1_500.png",
    "https://78.media.tumblr.com/f4d4770acaec8a37164a52c23adf780a/tumblr_pc8vvlo9NR1roqv59o1_500.png",
    "https://78.media.tumblr.com/55dcb820efb7945a23a49c2be74729c7/tumblr_pc8nvwh3If1roqv59o1_500.png"};

    public Frag4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag4, container, false);
        ivTrivia = view.findViewById(R.id.ivTrivia);
        btnChangeImage = view.findViewById(R.id.btnChangeImage);
        btnReadLater = view.findViewById(R.id.btnReadLater);
        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randInt = new Random().nextInt(images.length);
                String imageUrl = images[randInt];
                Picasso.with(getActivity()).load(imageUrl).into(ivTrivia);
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

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("image",randInt);
        prefEdit.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int image = prefs.getInt("image", -1);
        String url = images[image];
        Picasso.with(getActivity()).load(url).into(ivTrivia);

    }
}
