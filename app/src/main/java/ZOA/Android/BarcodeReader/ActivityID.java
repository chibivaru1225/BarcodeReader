package ZOA.Android.BarcodeReader;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class ActivityID extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id);

        EditText et = findViewById(R.id.txtID);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(Util.SharedPreference_FileName, this.MODE_PRIVATE);

        EditText txt = findViewById(R.id.txtID);

        if (prefs.contains(Util.SharedPreference_Key) && prefs.getString(Util.SharedPreference_Key, "") != "") {
            Util.ID = prefs.getString(Util.SharedPreference_Key, "");
            txt.setText(Util.ID);
        }
    }

    public void btnSave_OnClick(View v) {
        Util.copyToClipboard(this, "", Util.ID);

        EditText txt = findViewById(R.id.txtID);
        Util.ID = txt.getText().toString();

        SharedPreferences prefs = getSharedPreferences(Util.SharedPreference_FileName, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Util.SharedPreference_Key, Util.ID);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("IDを保存しました。");
        builder.setTitle("ZOA バーコードリーダー");

        builder.show();
    }
}
