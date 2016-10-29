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

public class SimSohopAcitivity extends Activity implements OnClickListener {
	private EditText edtSoSim;
	private Button btnNamSinh;
	private Spinner cbbGioiTinh;
	private Spinner cbbGioSinh;
	private Button btnXemKetQua;

	String[] listGioiTinh = { "Nam", "Nữ" };
	String[] listGioSinh = { "23 giờ đến 1 giờ", "1 giờ đến 3 giờ",
			"5 giờ đến 5 giờ", "5 giờ đến 7 giờ", "7 giờ đến 9 giờ",
			"9 giờ đến 11 giờ", "11 giờ đến 13 giờ", "13 giờ đến 15 giờ",
			"15 giờ đến 17 giờ", "17 giờ đến 19 giờ", "19 giờ đến 21 giờ",
			"21 giờ đến 23 giờ" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simsohop_activity);
		edtSoSim = (EditText) findViewById(R.id.edt_sosim);
		btnNamSinh = (Button) findViewById(R.id.btn_ngaysinh);
		btnNamSinh.setOnClickListener(this);
		btnXemKetQua = (Button) findViewById(R.id.btn_xemketqua);
		btnXemKetQua.setOnClickListener(this);
		cbbGioiTinh = (Spinner) findViewById(R.id.cbb_gioitinh);
		cbbGioSinh = (Spinner) findViewById(R.id.cbb_giosinh);
		Calendar now = Calendar.getInstance();
		btnNamSinh.setText(now.get(Calendar.DAY_OF_MONTH) + "/"
				+ (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR));
		iniCbb();
	}

	public void iniCbb() {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listGioiTinh);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cbbGioiTinh.setAdapter(dataAdapter);
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listGioSinh);
		dataAdapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cbbGioSinh.setAdapter(dataAdapter2);
	}

	private String configData() {
		String data = "";

		String[] ngaysinhtrai = btnNamSinh.getText().toString().split("/");
		try {
			data = URLEncoder.encode("sosim", "UTF-8") + "="
					+ URLEncoder.encode(edtSoSim.getText().toString(), "UTF-8");
			data += "&" + URLEncoder.encode("ngaysinh", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[0], "UTF-8");

			data += "&" + URLEncoder.encode("thangsinh", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[1], "UTF-8");

			data += "&" + URLEncoder.encode("namsinh", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[2], "UTF-8");
			data += "&"
					+ URLEncoder.encode("giosinh", "UTF-8")
					+ "="
					+ URLEncoder.encode(
							cbbGioSinh.getSelectedItem().toString(), "UTF-8");
			data += "&"
					+ URLEncoder.encode("gioitinh", "UTF-8")
					+ "="
					+ URLEncoder.encode(cbbGioiTinh.getSelectedItem()
							.toString(), "UTF-8");

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
							btnNamSinh.setText(date);
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
		case R.id.btn_xemketqua:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 2);
			i.putExtra("data", data);
			startActivity(i);
			break;
		default:
			break;
		}

	}

}
