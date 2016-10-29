package com.bk.phongthuy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton btnBoiTinhDuyen;
	private ImageButton btnLichVanNien;
	private ImageButton btnNamSinhCon;
	private ImageButton btnSaoChieuMenh;
	private ImageButton btnSimSoHop;
	private ImageButton btnTuoiVoChong;
	private ImageButton btnTuoiXayNha;
	private ImageButton btnXemThoiVan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnBoiTinhDuyen = (ImageButton) findViewById(R.id.btn_boitinhduyen);
		btnBoiTinhDuyen.setOnClickListener(this);
		btnLichVanNien = (ImageButton) findViewById(R.id.btn_lichvannien);
		btnLichVanNien.setOnClickListener(this);
		btnNamSinhCon = (ImageButton) findViewById(R.id.btn_namsinhcon);
		btnNamSinhCon.setOnClickListener(this);
		btnSaoChieuMenh = (ImageButton) findViewById(R.id.btn_saochieumenh);
		btnSaoChieuMenh.setOnClickListener(this);
		btnSimSoHop = (ImageButton) findViewById(R.id.btn_simsohop);
		btnSimSoHop.setOnClickListener(this);
		btnTuoiVoChong = (ImageButton) findViewById(R.id.btn_tuoivochong);
		btnTuoiVoChong.setOnClickListener(this);
		btnTuoiXayNha = (ImageButton) findViewById(R.id.btn_tuoixaynha);
		btnTuoiXayNha.setOnClickListener(this);
		btnXemThoiVan = (ImageButton) findViewById(R.id.btn_xemthoivan);
		btnXemThoiVan.setOnClickListener(this);

	}

	private boolean checkNetwork() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();

	}

	private void showdialog() {
		AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext())
				.setTitle("Không có kết nối mạng")
				.setMessage("Bạn vui lòng kiểm tra kết nối")
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				}).create();
		alertDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_boitinhduyen:
			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						BoiTinhYeuAcitivity.class));
			}

			break;
		case R.id.btn_lichvannien:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						LichVanNienActivity.class));
			}

			break;
		case R.id.btn_namsinhcon:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						XemNamSinhConActivity.class));
			}

			break;
		case R.id.btn_saochieumenh:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						XemSaoChieuMenh.class));
			}

			break;
		case R.id.btn_simsohop:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						SimSohopAcitivity.class));
			}

			break;
		case R.id.btn_tuoivochong:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						TuoiVoChongActivity.class));
			}

			break;
		case R.id.btn_tuoixaynha:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						XemTuoiXayNhaAcitivity.class));
			}

			break;
		case R.id.btn_xemthoivan:

			if (!checkNetwork()) {
				showdialog();
			} else {
				startActivity(new Intent(getBaseContext(),
						XemThoiVanActivity.class));
			}

			break;
		default:
			break;
		}

	}
}
