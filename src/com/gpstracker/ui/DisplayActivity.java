package com.gpstracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.gpstracker.GPSTracker;
import com.gpstracker.R;

/**
 * 
 * @author Sanjoy Barman
 *
 */
public class DisplayActivity extends Activity {

	private TextView txt_Latitude;
	private TextView txt_Longitude;
	private TextView txt_CountryName;
	private TextView txt_Locality;
	private TextView txt_PostalCode;
	private TextView txt_Address;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlocation);
		initView();
	}

	/**
	 * Initialize view
	 */
	private void initView() {
		txt_Latitude = (TextView) findViewById(R.id.txt_latitude);
		txt_Longitude = (TextView) findViewById(R.id.txt_longitude);
		txt_CountryName = (TextView) findViewById(R.id.txt_countryname);
		txt_Locality = (TextView) findViewById(R.id.txt_locality);
		txt_PostalCode = (TextView) findViewById(R.id.txt_postal_code);
		txt_Address = (TextView) findViewById(R.id.txt_address);
		gpsTracker();
	}
	
	/**
	 * get current location using GPS 
	 */
	private void gpsTracker(){
		// check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            String stringLatitude = String.valueOf(gpsTracker.getLatitude());
            txt_Latitude.setText(stringLatitude);

            String stringLongitude = String.valueOf(gpsTracker.getLongitude());
            txt_Longitude.setText(stringLongitude);

            String country = gpsTracker.getCountryName(this);
            txt_CountryName.setText(country);

            String city = gpsTracker.getLocality(this);
            txt_Locality.setText(city);

            String postalCode = gpsTracker.getPostalCode(this);
            txt_PostalCode.setText(postalCode);

            String addressLine = gpsTracker.getAddressLine(this);
            txt_Address.setText(addressLine);
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }
	}

}
