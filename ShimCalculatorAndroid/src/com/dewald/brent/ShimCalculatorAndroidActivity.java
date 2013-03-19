package com.dewald.brent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class ShimCalculatorAndroidActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button oemButton = (Button)findViewById(R.id.OEMButton);
        oemButton.setOnClickListener(this);
        Button aftmButton = (Button)findViewById(R.id.aftmButton);
        aftmButton.setOnClickListener(this);
        Button blockButton = (Button)findViewById(R.id.blockButton);
        blockButton.setOnClickListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.OEMButton:
			Intent i1 = new Intent(ShimCalculatorAndroidActivity.this, OEMActivity.class);
			startActivity(i1);
			break;
		case R.id.aftmButton:
			Intent i2 = new Intent(ShimCalculatorAndroidActivity.this, AftmActivity.class);
			startActivity(i2);
			break;
		case R.id.blockButton:
			Intent i3 = new Intent(ShimCalculatorAndroidActivity.this, BlockActivity.class);
			startActivity(i3);
			break;
		}
	}
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.about:
    		Toast aboutToast = Toast.makeText(this, "Ring and Pinion Shim Calculator \n Brent Dewald \n bdewald@hot.rr.com \n Version 1.0", Toast.LENGTH_LONG);
			aboutToast.show();
    		return true;
    	case R.id.help:
    		Toast helpToast = Toast.makeText(this, "Select the calaculator you need for your set up and enter all the data in the fields provided. Press " +
    				"Calculate and the shim size will be displayed in the Shim field.", Toast.LENGTH_LONG);
			helpToast.show();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
}