package ZOA.Android.BarcodeReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }


    public void btnScan_OnClick(View v) {
//        Intent i = new Intent(getApplication(), ReaderMain.class);
        startActivity(new Intent(getApplication(), ActivityReaderMain.class));
    }

    public void btnHistory_OnClick(View v){
        startActivity(new Intent(getApplication(), ActivityHistory.class));
    }
}
