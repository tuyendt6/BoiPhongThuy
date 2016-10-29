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

public class XemThoiVanActivity extends Activity implements OnClickListener {

	private Button btnNgaySinh;
	private Button btnNam;
	private Button btnXemKetQua;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xemthoivan_activity);
		btnNgaySinh = (Button) findViewById(R.id.btn_ngaysinh);
		btnNgaySinh.setOnClickListener(this);
		btnNam = (Button) findViewById(R.id.btn_nam);
		btnNam.setOnClickListener(this);
		btnXemKetQua = (Button) findViewById(R.id.btn_xem);
		btnXemKetQua.setOnClickListener(this);
		Calendar now = Calendar.getInstance();
		btnNgaySinh.setText(now.get(Calendar.DAY_OF_MONTH) + "/"
				+ (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR));
		btnNam.setText(now.get(Calendar.YEAR) + "");
	}

	// mDay:24
	// mMonth:9
	// mYear:1986
	// testYear:2014

	private String configData() {
		String data = "";

		String[] ngaysinhtrai = btnNgaySinh.getText().toString().split("/");
		try {
			data = URLEncoder.encode("mDay", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[0], "UTF-8");
			data += "&" + URLEncoder.encode("mMonth", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[1], "UTF-8");

			data += "&" + URLEncoder.encode("mYear", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[2], "UTF-8");

			data += "&"
					+ URLEncoder.encode("testYear", "UTF-8")
					+ "="
					+ URLEncoder.encode(btnNam.getText().toString().trim(),
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
		case R.id.btn_ngaysinh:
			Calendar now = Calendar.getInstance();
			DatePickerDialog dpd = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = dayOfMonth + "/" + (++monthOfYear)
									+ "/" + year;
							btnNgaySinh.setText(date);
						}
					}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
							.get(Calendar.DAY_OF_MONTH));
			dpd.setThemeDark(true);
			dpd.vibrate(true);
			dpd.dismissOnPause(true);
			dpd.showYearPickerFirst(true);
			dpd.setAccentColor(Color.parseColor("#9C27B0"));
			dpd.setTitle("Chọn Ngày Sinh Của Bạn ");
			dpd.setCancelable(false);
			dpd.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_nam:
			Calendar nows = Calendar.getInstance();
			DatePickerDialog dpds = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = year + "";
							btnNam.setText(date);
						}
					}, nows.get(Calendar.YEAR), nows.get(Calendar.MONTH), nows
							.get(Calendar.DAY_OF_MONTH));
			dpds.setThemeDark(true);
			dpds.vibrate(true);
			dpds.dismissOnPause(true);
			dpds.showYearPickerFirst(true);
			dpds.setAccentColor(Color.parseColor("#9C27B0"));
			dpds.setTitle("Chọn Năm Bạn Muốn Xem");
			dpds.setCancelable(false);
			dpds.show(getFragmentManager(), "Datepickerdialog");

			break;
		case R.id.btn_xem:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 4);
			i.putExtra("data", data);
			startActivity(i);
			break;

		default:
			break;
		}

	}

}
