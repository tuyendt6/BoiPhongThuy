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
import android.widget.Button;

public class XemNamSinhConActivity extends Activity implements OnClickListener {

	private Button btnNamBo;
	private Button btnNamMe;
	private Button btnNamCon;
	private Button btnXemKetQua;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xemnamsinhcon_actvity);
		btnNamBo = (Button) findViewById(R.id.btn_nambo);
		btnNamBo.setOnClickListener(this);
		btnNamMe = (Button) findViewById(R.id.btn_namme);
		btnNamMe.setOnClickListener(this);
		btnNamCon = (Button) findViewById(R.id.btn_namcon);
		btnNamCon.setOnClickListener(this);
		btnXemKetQua = (Button) findViewById(R.id.btn_xemketqua);
		btnXemKetQua.setOnClickListener(this);
		Calendar now = Calendar.getInstance();
		btnNamBo.setText(now.get(Calendar.YEAR) + "");
		btnNamMe.setText(now.get(Calendar.YEAR) + "");
		btnNamCon.setText(now.get(Calendar.YEAR) + "");
	}

	// nam_bo:1986
	// nam_me:1989
	// nam_con:2017

	private String configData() {
		String data = "";
		try {
			data = URLEncoder.encode("nam_bo", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamBo.getText().toString().trim(),
							"UTF-8");

			data += "&"
					+ URLEncoder.encode("nam_me", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamMe.getText().toString().trim(),
							"UTF-8");

			data += "&"
					+ URLEncoder.encode("nam_con", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamCon.getText().toString().trim(),
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
		case R.id.btn_nambo:
			Calendar now = Calendar.getInstance();
			DatePickerDialog dpd = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamBo.setText(date);
						}
					}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
							.get(Calendar.DAY_OF_MONTH));
			dpd.setThemeDark(true);
			dpd.vibrate(true);
			dpd.dismissOnPause(true);
			dpd.showYearPickerFirst(true);
			dpd.setAccentColor(Color.parseColor("#9C27B0"));
			dpd.setTitle("Chọn Năm Sinh Của Bố ");
			dpd.setCancelable(false);
			dpd.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_namme:
			Calendar nows = Calendar.getInstance();
			DatePickerDialog dpds = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamMe.setText(date);
						}
					}, nows.get(Calendar.YEAR), nows.get(Calendar.MONTH), nows
							.get(Calendar.DAY_OF_MONTH));
			dpds.setThemeDark(true);
			dpds.vibrate(true);
			dpds.dismissOnPause(true);
			dpds.showYearPickerFirst(true);
			dpds.setAccentColor(Color.parseColor("#9C27B0"));
			dpds.setTitle("Chọn Năm Sinh Của Mẹ");
			dpds.setCancelable(false);
			dpds.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_namcon:
			Calendar nowss = Calendar.getInstance();
			DatePickerDialog dpdss = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamCon.setText(date);
						}
					}, nowss.get(Calendar.YEAR), nowss.get(Calendar.MONTH),
					nowss.get(Calendar.DAY_OF_MONTH));
			dpdss.setThemeDark(true);
			dpdss.vibrate(true);
			dpdss.dismissOnPause(true);
			dpdss.showYearPickerFirst(true);
			dpdss.setAccentColor(Color.parseColor("#9C27B0"));
			dpdss.setTitle("Chọn Năm dự kiến sinh con ");
			dpdss.setCancelable(false);
			dpdss.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_xemketqua:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 5);
			i.putExtra("data", data);
			startActivity(i);
			break;

		default:
			break;
		}

	}

}
