package logbook3.entry.logbook.com.logbook3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import logbook3.entry.logbook.com.logbook3.helper.DatabaseHelper;
import logbook3.entry.logbook.com.logbook3.model.Kiddy;

public class MainActivity extends AppCompatActivity {

    private EditText textActivityName;
    private EditText textLocation;
    private EditText textDate;
    private EditText textTime;
    private EditText textReporterName;
    private Button btnSubmit;
    private String activityName ;
    private String location;
    private String date;
    private String time;
    private String reporterName ;
    private Boolean validate = false;
    private TextView txtResult ;

    DatabaseHelper sqldb;

    private void showList(TextView txtResult,DatabaseHelper sqldb){
        List<Kiddy> listKiddies = sqldb.getAllKiddys();
        String result = "";
        for(Kiddy k : listKiddies){
            result+= k.getActivityName() + " " + k.getDate() + " \r\n";
        }
        txtResult.setText(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get Result Text
        txtResult = (TextView)findViewById(R.id.txtResult);
        //Get Text Actitity Name
        textActivityName = (EditText) findViewById(R.id.textActivityName);
        //Get Text Location
        textLocation = (EditText) findViewById(R.id.textLocation);
        //Get Text Location
        textDate = (EditText) findViewById(R.id.textDate);
        //Get Text Location
        textTime = (EditText) findViewById(R.id.textTime);
        //Get Text Location
        textReporterName = (EditText) findViewById(R.id.textReporterName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        // Get connection from sqldb
        sqldb = new DatabaseHelper(this);
        showList(txtResult,sqldb);
        textActivityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                activityName = textActivityName.getText().toString();
                if(activityName.trim().length() < 1 && textActivityName.isEnabled()){
                    textActivityName.setError("Activity Name is not blank");
                    validate = false;
                }else {
                    textActivityName.setError(null);
                    validate = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                activityName = textActivityName.getText().toString();
                if(activityName.trim().length() > 0){
                    textActivityName.setError(null);
                    validate = true;
                }
            }
        });


        textLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                location = textLocation.getText().toString();
                if(location.trim().length() < 1 && textLocation.isEnabled()){
                    textLocation.setError("Location is not blank");
                    validate =false;
                }else {
                    textLocation.setError(null);
                    validate = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                location = textLocation.getText().toString();
                if(location.trim().length() > 0){
                    textLocation.setError(null);
                    validate = true;
                }
            }
        });

        textDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                date = textDate.getText().toString();
                if(date.trim().length() < 1 && textDate.isEnabled()){
                    textDate.setError("Date is not blank");
                    validate = false;
                }else {
                    textDate.setError(null);
                    validate = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String regex = "^\\d{2}-\\d{2}-\\d{4}$";
                date = textDate.getText().toString();
                if(date.trim().length() > 0){
                    if(!date.matches(regex)){
                        textDate.setError("Date is wrong format");
                        validate = false;
                    }else {
                        validate = true;
                    }
                }else {
                    validate = false;
                }
            }
        });

        textTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                time = textTime.getText().toString();
                if(time.trim().length() < 1 && textTime.isEnabled()){
                    textTime.setError("Time is not blank");
                    validate = false;
                }else {
                    textTime.setError(null);
                    validate = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                time = textTime.getText().toString();
                String regex = "^\\d{2}:\\d{2}$";
                if(!time.matches(regex)){
                    textTime.setError("Time is wrong format");
                    validate = false;
                }else {
                    validate = true;
                }
            }
        });

        textReporterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reporterName = textReporterName.getText().toString();
                if(reporterName.trim().length() < 1 && textReporterName.isEnabled()){
                    textReporterName.setError("Reporter Name is not blank");
                    validate = false;
                }else {
                    textReporterName.setError(null);
                    validate = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                reporterName = textReporterName.getText().toString();
                if(reporterName.trim().length() > 0){
                    textReporterName.setError(null);
                    validate = false;
                }else {
                    validate = true;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = null;
                activityName = textActivityName.getText().toString();
                reporterName = textReporterName.getText().toString();
                time = textTime.getText().toString();
                date = textDate.getText().toString();
                String regexTime = "^\\d{2}:\\d{2}$";
                String regexDate = "^\\d{2}-\\d{2}-\\d{4}$";
                location = textLocation.getText().toString();

                if(activityName.equalsIgnoreCase("") || reporterName.equalsIgnoreCase("") || time.equalsIgnoreCase("") || date.equalsIgnoreCase("") || time.equalsIgnoreCase("")){
                    validate = false;
                }else if(!time.matches(regexTime) || !date.matches(regexDate)){
                    validate = false;
                }else {
                    validate = true;
                }


                if(validate){
                    txtResult.setText("");
                    Kiddy kiddy = new Kiddy();
                    kiddy.setActivityName(activityName);
                    kiddy.setReporterName(reporterName);
                    kiddy.setTime(time);
                    kiddy.setDate(date);
                    kiddy.setLocation(location);
                    sqldb.insertKiddy(kiddy);
                    List<Kiddy> listKiddies = sqldb.getAllKiddys();
                    String result = "";
                    for(Kiddy k : listKiddies){
                        result+= k.getActivityName() + " " + k.getDate() + " \r\n";
                    }
                    txtResult.append(result);
                    textActivityName.setText("");
                    textDate.setText("");
                    textLocation.setText("");
                    textReporterName.setText("");
                    textTime.setText("");

                    toast = Toast.makeText(getApplicationContext(),"Success Validation and save into database !", Toast.LENGTH_LONG);
                }else {
                    toast = Toast.makeText(getApplicationContext(),"Fail Validation", Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });
    }
}