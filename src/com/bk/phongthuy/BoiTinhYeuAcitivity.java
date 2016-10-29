package com.bk.phongthuy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

public class BoiTinhYeuAcitivity extends Activity implements OnClickListener {

	private Button btnSinhNam;
	private EditText edtTenNam;

	private Button btnSinhNu;
	private EditText edtTenNu;

	private Button btnXemKetQua;
	private String Value = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.boitinhyeu_activity);
		edtTenNam = (EditText) findViewById(R.id.edt_tennam);
		btnSinhNam = (Button) findViewById(R.id.btn_sinhnam);
		btnSinhNam.setOnClickListener(this);

		edtTenNu = (EditText) findViewById(R.id.edt_tennu);
		btnSinhNu = (Button) findViewById(R.id.btn_sinhnu);
		btnSinhNu.setOnClickListener(this);

		btnXemKetQua = (Button) findViewById(R.id.btnketqua);
		btnXemKetQua.setOnClickListener(this);
	}

	private String configData() {
		String data = "";

		String[] ngaysinhtrai = btnSinhNam.getText().toString().split("/");
		String[] ngaysinhtraiGai = btnSinhNu.getText().toString().split("/");

		try {
			data = URLEncoder.encode("tentrai", "UTF-8")
					+ "="
					+ URLEncoder
							.encode(edtTenNam.getText().toString(), "UTF-8");
			data += "&" + URLEncoder.encode("ngaysinhtrai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[0], "UTF-8");

			data += "&" + URLEncoder.encode("thangsinhtrai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[1], "UTF-8");

			data += "&" + URLEncoder.encode("namsinhtrai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[2], "UTF-8");
			data += "&" + URLEncoder.encode("tengai", "UTF-8") + "="
					+ URLEncoder.encode(edtTenNu.getText().toString(), "UTF-8");
			data += "&" + URLEncoder.encode("ngaysinhgai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtraiGai[0], "UTF-8");
			data += "&" + URLEncoder.encode("thangsinhgai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtraiGai[1], "UTF-8");
			data += "&" + URLEncoder.encode("namsinhgai", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtraiGai[2], "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sinhnam:
			Calendar now = Calendar.getInstance();
			DatePickerDialog dpd = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = dayOfMonth + "/" + (++monthOfYear)
									+ "/" + year;
							btnSinhNam.setText(date);
						}
					}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
							.get(Calendar.DAY_OF_MONTH));
			dpd.setThemeDark(true);
			dpd.vibrate(true);
			dpd.dismissOnPause(true);
			dpd.showYearPickerFirst(true);
			dpd.setAccentColor(Color.parseColor("#9C27B0"));
			dpd.setTitle("Chọn Ngày Sinh Bạn Nam");
			dpd.setCancelable(false);
			dpd.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_sinhnu:
			Calendar nows = Calendar.getInstance();
			DatePickerDialog dpds = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = dayOfMonth + "/" + (++monthOfYear)
									+ "/" + year;
							btnSinhNu.setText(date);
						}
					}, nows.get(Calendar.YEAR), nows.get(Calendar.MONTH), nows
							.get(Calendar.DAY_OF_MONTH));
			dpds.setThemeDark(true);
			dpds.vibrate(true);
			dpds.dismissOnPause(true);
			dpds.showYearPickerFirst(true);
			dpds.setAccentColor(Color.parseColor("#9C27B0"));
			dpds.setTitle("Chọn Ngày Sinh Bạn Nữ");
			dpds.setCancelable(false);
			dpds.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btnketqua:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 0);
			i.putExtra("data", data);
			startActivity(i);
			break;

		default:
			break;
		}

	}
}
