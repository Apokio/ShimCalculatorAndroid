package com.dewald.brent;

import com.dewald.brent.ShimCalculator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AftmActivity extends Activity implements OnClickListener{
	
	Spinner extSpinner;
	EditText et_scrDepth;
	EditText et_indRead;
	EditText et_shim;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aftermarket);
        
        Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(this);
        Button calculate = (Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(this);
        
        extSpinner = (Spinner)findViewById(R.id.extSpinner);
        et_scrDepth = (EditText)findViewById(R.id.et_scrDepth);
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
			int ext = extSpinner.getSelectedItemPosition();
            double reading = Double.parseDouble(et_indRead.getText().toString());
            double sd = Double.parseDouble(et_scrDepth.getText().toString());
            ShimCalculator sc = new ShimCalculator(ext, reading, sd);
            et_shim.setText(sc.getShim());
            break;
		}
		}catch (NumberFormatException nfe){
			Toast toast = Toast.makeText(this, "Enter data in all fields, and try again!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
