package ZOA.Android.BarcodeReader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Util.MACAddress = Util.getMacAddr();

        PackageManager pm = this.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(this.getPackageName(), 0);
            Util.Version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(Util.SharedPreference_FileName, this.MODE_PRIVATE);

//        Button btnA = findViewById(R.id.btnScan);
//        Button btnB = findViewById(R.id.btnItemNoSearch);
//        Button btnC = findViewById(R.id.btnItemCodeSearch);

        Util.ID = prefs.getString(Util.SharedPreference_Key, "");
        //if (!prefs.contains(Util.SharedPreference_Key) || prefs.getString(Util.SharedPreference_Key, "") == "") {
//            btnA.setEnabled(false);
//            btnB.setEnabled(false);
//            btnC.setEnabled(false);
        //} else {
//            btnA.setEnabled(true);
//            btnB.setEnabled(true);
//            btnC.setEnabled(true);
            //Util.ID = prefs.getString(Util.SharedPreference_Key, "");
        //}
    }

    public void btnScan_OnClick(View v) {
//        Intent i = new Intent(getApplication(), ReaderMain.class);
        if (Util.ID != "")
            startActivity(new Intent(getApplication(), ActivityReaderMain.class));
        else
            IDnoneMessage();
    }

    public void btnCode_OnClick(View v) {
//        Intent i = new Intent(getApplication(), ReaderMain.class);
        if (Util.ID != "")
            startActivity(new Intent(getApplication(), ActivityItemCodeSearch.class));
        else
            IDnoneMessage();
    }

    public void btnNo_OnClick(View v) {
//        Intent i = new Intent(getApplication(), ReaderMain.class);
        if (Util.ID != "")
            startActivity(new Intent(getApplication(), ActivityItemNoSearch.class));
        else
            IDnoneMessage();
    }

    public void btnGUIDOpen_OnClick(View v) {
        startActivity(new Intent(getApplication(), ActivityID.class));
    }

    public void btnHistory_OnClick(View v) {
        startActivity(new Intent(getApplication(), ActivityHistory.class));
    }

    private void IDnoneMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ID表示ボタンをタップして、担当者番号を入力、保存してください。");
        builder.setTitle("ZOA バーコードリーダー");

        builder.show();
    }
}
