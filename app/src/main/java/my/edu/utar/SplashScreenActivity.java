package my.edu.utar;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import my.edu.utar.Database.DataInsertionActivity;
import my.edu.utar.Database.SQLiteAdapter;
import my.edu.utar.login.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    //Before importing this file remember to go AndroidManifest.xml to change the main launcher
    //There would be a <intent-filter> on the main launcher, change the android:name to this Activity
    //So that this activity would be the main launcher, then it would be redirected to other activity
    private SQLiteAdapter mySQLiteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //so that it focus on the xml file, easier modifying the view
        setContentView(R.layout.activity_splash_screen);

        //modifying logoText text size
        TextView logoText = findViewById(R.id.logoText);
        logoText.setTextSize(20.0f);

        //calling in the 'fade_out' animation created in the anim directory into 'fade_out'
        Animation fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        //set rootView as root
        View root = findViewById(R.id.rootView);

        //set animation of root
        root.startAnimation(fade_out);


        // If want to make the bus stop at the edge of the screen, go to move_bus.xml under anim directory
        // and change the toXDelta from 100%p to 80%p

        // Load the animation for movingBus
        Animation moveBusAnimation = AnimationUtils.loadAnimation(this, R.anim.move_bus);

        // Get reference to the movingBus ImageView
        ImageView movingBus = findViewById(R.id.movingBus);

        // Start the animation for movingBus
        movingBus.startAnimation(moveBusAnimation);

        //set this to have the same amount as the duration in 'fade_out.xml'
        int splashScreenDuration = 4500;

        //set the handler so that after the animation, it would be redirected to another activity
        new Handler().postDelayed(()-> {
            Intent intent = new Intent(SplashScreenActivity.this, DataInsertionActivity.class);
            startActivity(intent);
            finish();
        },splashScreenDuration); //last part here is the delayed duration to redirect user to another activity
        // this needs to be the same as the duration of the splash screen
        // or else it would redirect too fast or slow

//--------------------------------------------------------------------------------------------
        /*Locale malaysiaLocale = new Locale("ms", "MY");
        Calendar calendar = Calendar.getInstance(malaysiaLocale);

        //get date for the data
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date oneDayAfter = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date twoDayAfter = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date threeDayAfter = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fourDayAfter = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fiveDayAfter = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", malaysiaLocale);
        String formattedCurrentDate = dateFormat.format(currentDate);
        String formattedOneDayAfter = dateFormat.format(oneDayAfter);
        String formattedTwoDayAfter = dateFormat.format(twoDayAfter);
        String formattedThreeDayAfter = dateFormat.format(threeDayAfter);
        String formattedFourDayAfter = dateFormat.format(fourDayAfter);
        String formattedFiveDayAfter = dateFormat.format(fiveDayAfter);

        Date currentTime = new Date();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(currentTime);
        calendar1.add(Calendar.HOUR_OF_DAY, 1);
        Date oneHourLater = calendar1.getTime();
        calendar1.add(Calendar.HOUR_OF_DAY, 2);*/

        //let's insert some data
        /*mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();*/

        //mySQLiteAdapter.deleteAll();

        /*//------------------INSERT USER TABLE-------------------------
        mySQLiteAdapter.insertUserTable("Goodgis123", "wee123@gmail.com", "goodgis123", 100, "driver");
        mySQLiteAdapter.insertUserTable("Kuanchee123", "kuanchee123@gmail.com", "kuanchee123", 100, "driver");
        mySQLiteAdapter.insertUserTable("Jielun123", "jielun123@gmail.com", "jielun123", 100, "driver");

        mySQLiteAdapter.insertUserTable("Ryan123", "ryan123@gmail.com", "ryan123", 100, "student");
        mySQLiteAdapter.insertUserTable("Nicholas123", "nicholas123@gmail.com", "nicholas123", 100, "student");
        mySQLiteAdapter.insertUserTable("Wee123", "wee123@gmail.com", "wee123", 100, "student");
        mySQLiteAdapter.insertUserTable("Ahmad123", "ahmad123@gmail.com", "ahmad123", 100, "student");*/

        /*//------------------INSERT BUS TABLE-------------------------
        //bus to WestLake
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "Block N", "WestLake", "10001");
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "Block G", "WestLake", "10001");
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "Block D", "WestLake", "10001");
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "WestLake", "Block N", "10001");
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "WestLake", "Block G", "10001");
        mySQLiteAdapter.insertBusTable("BTW 123", "Block G", "13:00:00", "WestLake", "Block D", "10001");
        //bus to Harvard
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Block N", "Harvard", "10002");
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Block G", "Harvard", "10002");
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Block D", "Harvard", "10002");
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Harvard", "Block N", "10002");
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Harvard", "Block G", "10002");
        mySQLiteAdapter.insertBusTable("BTH 123", "Block N", "13:00:00", "Harvard", "Block D", "10002");
        //bus to Stanford
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Block N", "Stanford", "10003");
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Block G", "Stanford", "10003");
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Block D", "Stanford", "10003");
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Stanford", "Block N", "10003");
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Stanford", "Block G", "10003");
        mySQLiteAdapter.insertBusTable("BTS 123", "Block D", "13:00:00", "Stanford", "Block D", "10003");

        //------------------------------INSERT SCHEDULE TABLE----------------
        //WestLake Bus Schedule
        //---------------------------
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedCurrentDate, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedCurrentDate, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedCurrentDate, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedCurrentDate, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedCurrentDate, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedCurrentDate, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedCurrentDate, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedCurrentDate, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedCurrentDate, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedCurrentDate, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedCurrentDate, 30, 1006);

        //After one day
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedOneDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedOneDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedOneDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedOneDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedOneDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedOneDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedOneDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedOneDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedOneDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedOneDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedOneDayAfter, 30, 1006);

        //After two days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedTwoDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedTwoDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedTwoDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedTwoDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedTwoDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedTwoDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedTwoDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedTwoDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedTwoDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedTwoDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedTwoDayAfter, 30, 1006);

        //After 3 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedThreeDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedThreeDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedThreeDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedThreeDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedThreeDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedThreeDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedThreeDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedThreeDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedThreeDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedThreeDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedThreeDayAfter, 30, 1006);

        //After 4 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedFourDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedFourDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFourDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFourDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFourDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFourDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFourDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFourDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFourDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFourDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFourDayAfter, 30, 1006);

        //After 5 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedFiveDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedFiveDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFiveDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFiveDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFiveDayAfter, 30, 1006);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFiveDayAfter, 30, 1001);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFiveDayAfter, 30, 1002);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFiveDayAfter, 30, 1003);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFiveDayAfter, 30, 1004);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFiveDayAfter, 30, 1005);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFiveDayAfter, 30, 1006);

        //Harvard Bus Schedule
        //---------------------------
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedCurrentDate, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedCurrentDate, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedCurrentDate, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedCurrentDate, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedCurrentDate, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedCurrentDate, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedCurrentDate, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedCurrentDate, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedCurrentDate, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedCurrentDate, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedCurrentDate, 30, 1012);

        //After 1 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedOneDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedOneDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedOneDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedOneDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedOneDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedOneDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedOneDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedOneDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedOneDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedOneDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedOneDayAfter, 30, 1012);

        //After 2 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedTwoDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedTwoDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedTwoDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedTwoDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedTwoDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedTwoDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedTwoDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedTwoDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedTwoDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedTwoDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedTwoDayAfter, 30, 1012);

        //After 3 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedThreeDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedThreeDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedThreeDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedThreeDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedThreeDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedThreeDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedThreeDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedThreeDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedThreeDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedThreeDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedThreeDayAfter, 30, 1012);

        //After 4 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedFourDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedFourDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFourDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFourDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFourDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFourDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFourDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFourDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFourDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFourDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFourDayAfter, 30, 1012);

        //After 5 days
        mySQLiteAdapter.insertScheduleTable("09:00:00", "09:30:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("09:05:00", "09:35:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("09:10:00", "09:40:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("09:30:00", "10:00:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("09:35:00", "10:05:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("09:40:00", "10:10:00", formattedFiveDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("10:30:00", "11:00:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("10:35:00", "11:05:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("10:40:00", "11:10:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("11:00:00", "11:30:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("11:05:00", "11:35:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("11:10:00", "11:40:00", formattedFiveDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFiveDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFiveDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFiveDayAfter, 30, 1012);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFiveDayAfter, 30, 1007);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFiveDayAfter, 30, 1008);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFiveDayAfter, 30, 1009);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFiveDayAfter, 30, 1010);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFiveDayAfter, 30, 1011);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFiveDayAfter, 30, 1012);

        //Stanford Bus Schedule
        //---------------------------
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedCurrentDate, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedCurrentDate, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedCurrentDate, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedCurrentDate, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedCurrentDate, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedCurrentDate, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedCurrentDate, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedCurrentDate, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedCurrentDate, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedCurrentDate, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedCurrentDate, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedCurrentDate, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedCurrentDate, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedCurrentDate, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedCurrentDate, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedCurrentDate, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedCurrentDate, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedCurrentDate, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedCurrentDate, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedCurrentDate, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedCurrentDate, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedCurrentDate, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedCurrentDate, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedCurrentDate, 30, 1018);

        //After 1 days
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedOneDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedOneDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedOneDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedOneDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedOneDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedOneDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedOneDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedOneDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedOneDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedOneDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedOneDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedOneDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedOneDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedOneDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedOneDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedOneDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedOneDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedOneDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedOneDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedOneDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedOneDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedOneDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedOneDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedOneDayAfter, 30, 1018);

        //After 2 days
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedTwoDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedTwoDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedTwoDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedTwoDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedTwoDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedTwoDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedTwoDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedTwoDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedTwoDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedTwoDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedTwoDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedTwoDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedTwoDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedTwoDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedTwoDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedTwoDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedTwoDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedTwoDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedTwoDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedTwoDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedTwoDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedTwoDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedTwoDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedTwoDayAfter, 30, 1018);

        //After 3 days
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedThreeDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedThreeDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedThreeDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedThreeDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedThreeDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedThreeDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedThreeDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedThreeDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedThreeDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedThreeDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedThreeDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedThreeDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedThreeDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedThreeDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedThreeDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedThreeDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedThreeDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedThreeDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedThreeDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedThreeDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedThreeDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedThreeDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedThreeDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedThreeDayAfter, 30, 1018);

        //After 4 days
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFourDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFourDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFourDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFourDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFourDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFourDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFourDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFourDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFourDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFourDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFourDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFourDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFourDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFourDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFourDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFourDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFourDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFourDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFourDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFourDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFourDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFourDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFourDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFourDayAfter, 30, 1018);

        //After 5 days
        mySQLiteAdapter.insertScheduleTable("12:00:00", "12:30:00", formattedFiveDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("12:05:00", "12:35:00", formattedFiveDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("12:10:00", "12:40:00", formattedFiveDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("12:30:00", "13:00:00", formattedFiveDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("12:35:00", "13:05:00", formattedFiveDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("12:40:00", "13:10:00", formattedFiveDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("14:00:00", "14:30:00", formattedFiveDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("14:05:00", "14:35:00", formattedFiveDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("14:10:00", "14:40:00", formattedFiveDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("14:30:00", "15:00:00", formattedFiveDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("14:35:00", "15:05:00", formattedFiveDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("14:40:00", "15:10:00", formattedFiveDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("16:00:00", "16:30:00", formattedFiveDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("16:05:00", "16:35:00", formattedFiveDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("16:10:00", "16:40:00", formattedFiveDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("16:30:00", "17:00:00", formattedFiveDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("16:35:00", "17:05:00", formattedFiveDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("16:40:00", "17:10:00", formattedFiveDayAfter, 30, 1018);

        mySQLiteAdapter.insertScheduleTable("17:30:00", "17:40:00", formattedFiveDayAfter, 30, 1013);
        mySQLiteAdapter.insertScheduleTable("17:35:00", "17:45:00", formattedFiveDayAfter, 30, 1014);
        mySQLiteAdapter.insertScheduleTable("17:40:00", "17:50:00", formattedFiveDayAfter, 30, 1015);
        mySQLiteAdapter.insertScheduleTable("18:00:00", "18:10:00", formattedFiveDayAfter, 30, 1016);
        mySQLiteAdapter.insertScheduleTable("18:05:00", "18:15:00", formattedFiveDayAfter, 30, 1017);
        mySQLiteAdapter.insertScheduleTable("18:10:00", "18:20:00", formattedFiveDayAfter, 30, 1018);*/

        /*//------------------INSERT BOOKING TABLE-------------------------
        mySQLiteAdapter.insertBookingTable("2022-07-17", "Harvard", "Block G", "past", 10004, 22, "Young Lai Sien");
        mySQLiteAdapter.insertBookingTable("2022-07-17", "WestLake", "Block D", "past", 10004, 17, "Man Rock");
        mySQLiteAdapter.insertBookingTable("2022-07-18", "Harvard", "Block G", "past", 10004, 22, "Young Lai Sien");
        mySQLiteAdapter.insertBookingTable("2022-07-17", "Harvard", "Block N", "past", 10005, 32, "Wee Jeng Kai");
        mySQLiteAdapter.insertBookingTable("2022-07-20", "Harvard", "Block G", "past", 10005, 1, "Wee Jeng Kai");
        mySQLiteAdapter.insertBookingTable("2022-07-14", "Stanford", "Block D", "past", 10006, 42, "Andrew");
        mySQLiteAdapter.insertBookingTable("2022-07-17", "Harvard", "Block N", "past", 10007, 19, "Jabita");*/

        /*mySQLiteAdapter.close();

        mySQLiteAdapter.openToRead();
        ArrayList<String[]> userList = mySQLiteAdapter.readUser();
        ArrayList<String[]> busList = mySQLiteAdapter.readBus();
        ArrayList<String[]> bookingList = mySQLiteAdapter.readBooking();
        ArrayList<String[]> scheduleList = mySQLiteAdapter.readSchedule();
        mySQLiteAdapter.close();*/
    }
}