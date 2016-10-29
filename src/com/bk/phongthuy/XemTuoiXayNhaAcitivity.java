package com.bk.phongthuy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class XemTuoiXayNhaAcitivity extends Activity implements OnClickListener {
	private Button btnNamSinh;
	private Button btnNamXay;
	private Button btnXemKetQua;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xemtuoixaynha_activity);
		btnNamSinh = (Button) findViewById(R.id.btn_namsinh);
		btnNamSinh.setOnClickListener(this);
		btnNamXay = (Button) findViewById(R.id.btn_namxaynha);
		btnNamXay.setOnClickListener(this);
		btnXemKetQua = (Button) findViewById(R.id.btn_xemketqua);
		btnXemKetQua.setOnClickListener(this);
		Calendar now = Calendar.getInstance();
		btnNamSinh.setText(now.get(Calendar.YEAR) + "");
		btnNamXay.setText(now.get(Calendar.YEAR) + "");
	}

	// year_birth:1986
	// year_build:2015

	private String configData() {
		String data = "";

		try {
			data = URLEncoder.encode("year_birth", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamSinh.getText().toString().trim(),
							"UTF-8");
			data += URLEncoder.encode("year_build", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamXay.getText().toString().trim(),
							"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_namsinh:
			Calendar now = Calendar.getInstance();
			DatePickerDialog dpd = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamSinh.setText(date);
						}
					}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
							.get(Calendar.DAY_OF_MONTH));
			dpd.setThemeDark(true);
			dpd.vibrate(true);
			dpd.dismissOnPause(true);
			dpd.showYearPickerFirst(true);
			dpd.setAccentColor(Color.parseColor("#9C27B0"));
			dpd.setTitle("Chọn Năm Sinh Của Bạn");
			dpd.setCancelable(false);
			dpd.show(getFragmentManager(), "Datepickerdialog");
			break;
		case R.id.btn_namxaynha:
			Calendar nows = Calendar.getInstance();
			DatePickerDialog dpds = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamXay.setText(date);
						}
					}, nows.get(Calendar.YEAR), nows.get(Calendar.MONTH), nows
							.get(Calendar.DAY_OF_MONTH));
			dpds.setThemeDark(true);
			dpds.vibrate(true);
			dpds.dismissOnPause(true);
			dpds.showYearPickerFirst(true);
			dpds.setAccentColor(Color.parseColor("#9C27B0"));
			dpds.setTitle("Chọn Năm Bạn Muốn Xây Nhà ");
			dpds.setCancelable(false);
			dpds.show(getFragmentManager(), "Datepickerdialog");
			break;
		case R.id.btn_xemketqua:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 8);
			i.putExtra("data", data);
			startActivity(i);
			break;
		default:
			break;
		}
	}
}
