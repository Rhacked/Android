package com.oving3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;


public class Friends extends Activity {
    final Context context = this;
    public ArrayList<String> friendsList = new ArrayList<String>();
    private ListView lv;
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        lv = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,friendsList);
        lv.setAdapter(arrayAdapter);

    }

    public void onResume(){
        super.onResume();
        Log.d("onResume","onResume()");
        if(friendsList.size()>0) {
            Log.d("onResume()","Friendslist > 0");
            lv = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friendsList);
            lv.setAdapter(arrayAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        Log.d("On click", "first one");
        this.onPause();
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.input_dialog);
        final EditText fn = (EditText)dialog.findViewById(R.id.firstName);
        final EditText ln = (EditText)dialog.findViewById(R.id.lastName);
        final DatePicker date = (DatePicker)dialog.findViewById(R.id.date);

        Button okButton = (Button)dialog.findViewById(R.id.dialogOk);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = fn.getText().toString();
                input +=" "+ln.getText().toString();
                input +=" "+date.getDayOfMonth()+"."+date.getMonth()+"."+date.getYear();
                final String finalInput = input;
                Log.d("OnClick()", "Ok button clicked: "+finalInput);
                friendsList.add(finalInput);
                onResume();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void onPause(){
        super.onPause();
        Log.d("Pause", "PAAAAAAAUSE");
    }
}
