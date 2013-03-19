package com.dewald.brent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dewald.brent.ShimCalculator;

public class OEMActivity extends Activity implements OnClickListener{
	
	Spinner diffSpinner;
	Spinner extSpinner;
	EditText et_pinThick;
	EditText et_indRead;
	EditText et_shim;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oem);
        
        Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(this);
        Button calculate = (Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(this);
        
        diffSpinner = (Spinner)findViewById(R.id.diffSpinner);
        extSpinner = (Spinner)findViewById(R.id.extSpinner);
        et_pinThick = (EditText)findViewById(R.id.et_pinThick);
        et_indRead = (EditText)findViewById(R.id.et_indRead);
        et_shim = (EditText)findViewById(R.id.et_shim);
	}
	
	@Override
	public void onClick(View v) {
		try {
		switch(v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.calculate:
			int diff = diffSpinner.getSelectedItemPosition();
			double ph = Double.parseDouble(et_pinThick.getText().toString());
            int ext = extSpinner.getSelectedItemPosition();
            double reading = Double.parseDouble(et_indRead.getText().toString());
            ShimCalculator sc = new ShimCalculator(diff, ph, ext, reading);
            et_shim.setText(sc.getShim());
			break;
		}
		}catch (NumberFormatException nfe){
			Toast toast = Toast.makeText(this, "Enter data in all fields, and try again!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
