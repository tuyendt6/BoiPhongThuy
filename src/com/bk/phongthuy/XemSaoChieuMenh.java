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

public class XemSaoChieuMenh extends Activity implements OnClickListener {
	private Button btnNamSinh;
	private Spinner cbbGioiTinh;
	private Button btnNamXem;
	private Button btnXemKetQua;

	String[] listGioiTinh = { "Nam", "Nữ" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xemsaochieumenh_activity);
		btnNamSinh = (Button) findViewById(R.id.btn_namsinh);
		btnNamSinh.setOnClickListener(this);
		btnNamXem = (Button) findViewById(R.id.btn_namxem);
		btnNamXem.setOnClickListener(this);
		btnXemKetQua = (Button) findViewById(R.id.btn_xemketqua);
		btnXemKetQua.setOnClickListener(this);
		cbbGioiTinh = (Spinner) findViewById(R.id.cbb_goitinh);
		Calendar now = Calendar.getInstance();
		btnNamSinh.setText(now.get(Calendar.YEAR) + "");
		btnNamXem.setText(now.get(Calendar.YEAR) + "");
		iniCbb();
	}

	public void iniCbb() {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listGioiTinh);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cbbGioiTinh.setAdapter(dataAdapter);
	}

	// namsinh:1986
	// gioitinh:Nam
	// namxem:2014

	private String configData() {
		String data = "";

		try {
			data = URLEncoder.encode("namsinh", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamSinh.getText().toString().trim(),
							"UTF-8");
			data += "&"
					+ URLEncoder.encode("gioitinh", "UTF-8")
					+ "="
					+ URLEncoder.encode(cbbGioiTinh.getSelectedItem()
							.toString(), "UTF-8");

			data += URLEncoder.encode("namxem", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNamXem.getText().toString().trim(),
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
		case R.id.btn_namxem:
			Calendar nows = Calendar.getInstance();
			DatePickerDialog dpds = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNamXem.setText(date);
						}
					}, nows.get(Calendar.YEAR), nows.get(Calendar.MONTH), nows
							.get(Calendar.DAY_OF_MONTH));
			dpds.setThemeDark(true);
			dpds.vibrate(true);
			dpds.dismissOnPause(true);
			dpds.showYearPickerFirst(true);
			dpds.setAccentColor(Color.parseColor("#9C27B0"));
			dpds.setTitle("Chọn Năm Bạn Muốn Xem ");
			dpds.setCancelable(false);
			dpds.show(getFragmentManager(), "Datepickerdialog");
			break;
		case R.id.btn_xemketqua:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 6);
			i.putExtra("data", data);
			startActivity(i);
			break;
		default:
			break;
		}

	}

}
