package my.edu.utar.BookingPage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import my.edu.utar.R;
import my.edu.utar.Database.SQLiteAdapter;

public class BookingPageModel extends AppCompatActivity implements ScheduleAdapter.OnBookClickListener {

    private ArrayList<String[]> scheduleListByCondition, busList, busListByCondition, userList;
    private TableLayout tableLayout1, tableLayout2;
    private TableRow headerTableLayout;
    private List<ScheduleItem> scheduleItemsList;
    private RecyclerView recyclerView, scheduleRecyclerView;
    private ScheduleAdapter scheduleAdapter;
    private SQLiteAdapter mySQLiteAdapter;
    private String pickUpPoint, dropOffPoint, dateStr, timeStr, dateStrConverted, paxStr, uid, booking_name, busID;
    private ImageButton dateButton;
    private TextView dateTextView;
    private int count = 0, pax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_schedule);

        // Get data passed from BookingPage activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pickUpPoint = extras.getString("pickUpPoint");
            dropOffPoint = extras.getString("dropOffPoint");
            dateStr = extras.getString("dateStr");
            timeStr = extras.getString("timeStr");
            paxStr = extras.getString("paxStr");
            pax = Integer.parseInt(paxStr);
            uid = extras.getString("uid");
            int count = 0;
            // Find your dateButton by its ID
            dateButton = findViewById(R.id.dateButton);

            // Set an OnClickListener for the dateButton
            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDatePickerDialog();
                }
            });
            showSchedule();
        }
    }

    // Function to show available schedule
    private void showSchedule() {
        // Initialize SQLiteAdapter
        mySQLiteAdapter = new SQLiteAdapter(this);

        // Recycle view setup
        recyclerView = findViewById(R.id.recyclerViewSchedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookingPageModel.this));

        // Title of the page
        TextView title = findViewById(R.id.title);
        title.setText("(" + pickUpPoint + " ---> " + dropOffPoint + ")\n");

        //Date of the bus schedule
        dateTextView = findViewById(R.id.dateTextView);
        dateStrConverted = convertToDateWord(dateStr);
        dateTextView.setText(dateStrConverted);

        // Initialize scheduleItemsList and scheduleAdapter
        scheduleItemsList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(scheduleItemsList, BookingPageModel.this);
        recyclerView.setAdapter(scheduleAdapter);

        // Your code to read data from the database and populate the schedule goes here
        // Use pickUpPoint, dropOffPoint, dateStr, and timeStr to filter the schedules
        // Populate scheduleItemsList with the relevant schedule data
        //read data from db
        mySQLiteAdapter.openToRead();
        busList = mySQLiteAdapter.readBus();
        String busID = "";
        boolean noBus = true;
        scheduleListByCondition = mySQLiteAdapter.readScheduleByCondition("scheduleDate", dateStr);

        //check if there bus available
        for (int i = 0; i < busList.size(); i++) {
            if (scheduleListByCondition.size() != 0 && pickUpPoint.equals(busList.get(i)[4])
                    && dropOffPoint.equals(busList.get(i)[5])) {
                for (int j = 0; j < scheduleListByCondition.size(); j++) {
                    if (Time.valueOf(scheduleListByCondition.get(j)[1]).compareTo(Time.valueOf(timeStr)) > 0 &&
                            Integer.parseInt(scheduleListByCondition.get(j)[4]) > 0) {
                        busID = busList.get(i)[0];
                        scheduleListByCondition = mySQLiteAdapter.readScheduleByTwoCondition("busID", busID, "scheduleDate", dateStr);
                        noBus = false;
                        break;
                    }
                }
            }
            if (noBus == false) {
                break;
            }
        }

        //show all the data in recycle view
        if (noBus == false) {
            for (int i = 0; i < scheduleListByCondition.size(); i++) {
                //get the schedule record after the time user choose
                if (Time.valueOf(scheduleListByCondition.get(i)[1]).compareTo(Time.valueOf(timeStr)) > 0) {
                    String scheduleID = scheduleListByCondition.get(i)[0];
                    String departureTime = scheduleListByCondition.get(i)[1];
                    String arrivalTime = scheduleListByCondition.get(i)[2];
                    String scheduleDate = scheduleListByCondition.get(i)[3];
                    String seatAvailable = scheduleListByCondition.get(i)[4];
                    busList = mySQLiteAdapter.readBusByCondition("busID", scheduleListByCondition.get(i)[5]);

                    // Create a ScheduleItem object and add it to the list
                    ScheduleItem scheduleItem = new ScheduleItem(scheduleID, departureTime,
                            arrivalTime, scheduleDate, seatAvailable, busList.get(0)[1]);
                    scheduleItemsList.add(scheduleItem);
                }
            }
            // Notify the adapter that the data has changed
            scheduleAdapter.notifyDataSetChanged();
        } else {
            //booking failed message
            Toast.makeText(this, "There is no bus available from " + pickUpPoint + " to "
                            + dropOffPoint + " onwards this time",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Handle booking operation
    @Override
    public void onBookClick(ScheduleItem scheduleItem) {
        setContentView(R.layout.booking_detail_input);

        userList = mySQLiteAdapter.readUserByCondition("userID", uid);
        EditText userName = findViewById(R.id.userName);
        TextView userEmail = findViewById(R.id.userEmail);
        Button registerButton = findViewById(R.id.registerButton);
        RelativeLayout layout = findViewById(R.id.layout);

        userName.setText(userList.get(0)[1]);
        userEmail.setText(userList.get(0)[2]);

        //handle booking process
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingPageModel.this);
                builder.setTitle("Confirm Booking");
                builder.setMessage("Are you sure?");

                // Add buttons to the dialog
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //increase the pax
                        count += 1;
                        boolean bookingSuccess = false;

                        //insert booking data into database
                        for(int i=0; i<pax ; i++){
                            bookingSuccess = mySQLiteAdapter.insertBookingTable(
                                    dateStr, pickUpPoint, dropOffPoint, "current", Integer.parseInt(uid), Integer.parseInt(scheduleItem.getScheduleId())+i,
                                    userName.getText().toString());
                        }

                        scheduleListByCondition = mySQLiteAdapter.readScheduleByCondition("scheduleID", scheduleItem.getScheduleId());
                        mySQLiteAdapter.updateScheduleTableByInt("seatAvailable",
                                Integer.parseInt(scheduleListByCondition.get(0)[4]) - pax, "scheduleID", scheduleItem.getScheduleId());

                        if (bookingSuccess) {
                            mySQLiteAdapter.updateUserPoint(uid, Integer.parseInt(userList.get(0)[4])+5);
                            Toast.makeText(BookingPageModel.this, "Booking successful! Now redirecting you to booking page....",
                                    Toast.LENGTH_SHORT).show();

                            showNotification();


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // This code will run after 3 seconds
                                    Intent intent = new Intent(BookingPageModel.this, my.edu.utar.BookingHistory.MyTicketActivity.class);
                                    intent.putExtra("uid", uid);
                                    busID = scheduleListByCondition.get(0)[5];
                                    intent.putExtra("busID", busID);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 3000); // 3000 milliseconds (3 seconds)
                        } else {
                            // Booking failed, show an error message
                            Toast.makeText(BookingPageModel.this, "Booking failed. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked No, do nothing
                    }
                });

                // Show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    private void openDatePickerDialog() {
        // Create a DatePickerDialog to allow the user to select a new date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingPageModel.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String formattedMonth, formattedDay;
                        if (monthOfYear < 10) {
                            formattedMonth = String.format("%02d", monthOfYear + 1);
                        } else {
                            formattedMonth = String.valueOf(monthOfYear);
                        }
                        if (dayOfMonth < 10) {
                            formattedDay = String.format("%02d", dayOfMonth);
                        } else {
                            formattedDay = String.valueOf(dayOfMonth);
                        }
                        dateStr = formattedDay + "-" + formattedMonth + "-" + year;
                        showSchedule();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private String convertToDateWord(String inputDateStr) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());

            // Parse the input date string
            Date date = inputFormat.parse(inputDateStr);

            // Format the date into the desired output format
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return inputDateStr; // Return the original string if there's an error
        }
    }

    private void showNotification() {
        // Create an intent that opens your app when the user taps the notification
        Intent intent = new Intent(this, my.edu.utar.SplashScreenActivity.class); // Replace YourMainActivity with your actual main activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        //set channel id
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channel_id",                // Unique ID for the channel
                    "Channel Name",              // Display name of the channel
                    NotificationManager.IMPORTANCE_DEFAULT // Importance level
            );
            channel.setDescription("Channel Description"); // Optional description

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id") // Replace "channel_id" with your channel ID
                .setSmallIcon(R.drawable.logo) // Replace with your notification icon
                .setContentTitle("Notification from Bus.IO")
                .setContentText("Your booking has been done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // Set the PendingIntent here
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build()); // You can use a unique ID (1 in this example) to update or cancel the notification later
    }

}