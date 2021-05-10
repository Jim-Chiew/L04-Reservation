package sg.edu.rp.c346.id20004713.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etNumber;
    EditText etPaxNum;
    DatePicker dpInputDate;
    TimePicker tpInputTime;
    CheckBox cbSmokingRoom;
    Button btnConfirm;
    Button btnReset;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etPaxNum = findViewById(R.id.etPaxNum);
        dpInputDate = findViewById(R.id.dpInputDate);
        tpInputTime = findViewById(R.id.tpInputTime);
        cbSmokingRoom = findViewById(R.id.cbSmokingRoom);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnReset = findViewById(R.id.btnReset);
        tvOutput = findViewById(R.id.tvOutput);

        /*  For ON DATE LISTENER BUT IS NOT WORKING !!!!!!!!!!!!!!!!!. say requires API 24 and above
        int curYear = dpInputDate.getYear();
        int curMonth = dpInputDate.getMonth();
        int curDay = dpInputDate.getDayOfMonth();
         */

        reset();  //Set the default values

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = "";

                //______________________ IF all edit text fields are filled in __________________________
                if(etIsSet(etName) && etIsSet(etNumber) && etIsSet(etPaxNum)){
                    Toast.makeText(MainActivity.this, "Confirmed", Toast.LENGTH_SHORT).show();
                    output += String.format("Name: %s\n", etName.getText().toString());
                    output += String.format("Number: %s\n", etNumber.getText().toString());
                    output += String.format("Group size: %s\n", etPaxNum.getText().toString());
                    output += String.format("Date (y/m/d): %d/%d/%d\n", dpInputDate.getYear(), dpInputDate.getMonth() + 1,
                            dpInputDate.getDayOfMonth());
                    output += String.format("Time: %d%d\n", tpInputTime.getCurrentHour(), tpInputTime.getCurrentMinute());

                    if (cbSmokingRoom.isChecked()){
                        output += String.format("Smoking Room Requested \n");
                    } else {
                        output += String.format("Smoking Room NOT Requested \n");
                    }

                    tvOutput.setText(output);
                } else {
                    Toast.makeText(MainActivity.this, "Missing Field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tpInputTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.i("MyActivity", "Checking");
                if (hourOfDay > 20 || hourOfDay < 8){
                    Toast.makeText(MainActivity.this, "reservation is only from 8am to 8:59pm",
                            Toast.LENGTH_LONG).show();
                    tpInputTime.setCurrentHour(8);
                    tpInputTime.setCurrentMinute(0);
                }
            }
        });

        /*  ON DATE LISTENER NOT WORKING !!!!!!!!!!!!!!!!!!!
        dpInputDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (year < curYear){
                    Toast.makeText(MainActivity.this, "Cannot reserve paste date or today",
                            Toast.LENGTH_LONG).show();
                    dpInputDate.updateDate(curYear, curMonth + 1, curDay + 1);
                } else if (monthOfYear < curMonth){
                    Toast.makeText(MainActivity.this, "Cannot reserve paste date or today",
                            Toast.LENGTH_LONG).show();
                } else if (dayOfMonth <= curDay){
                    Toast.makeText(MainActivity.this, "Cannot reserve paste date or today",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


         */
    }

    private void reset(){
        etName.setText("");
        etNumber.setText("");
        etPaxNum.setText("");
        dpInputDate.updateDate(2020, 0, 1);
        tpInputTime.setCurrentHour(19);
        tpInputTime.setCurrentMinute(30);
        cbSmokingRoom.setChecked(false);
        tvOutput.setText("");
    }

    private boolean etIsSet(EditText input){
        if(input.getText().toString().trim().length() > 0){
            return true;
        } else {
            return false;
        }
    }
}