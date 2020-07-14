package ZOA.Android.BarcodeReader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityItemCodeSearch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsearch);
        setTitle("商品コード検索");

        TextView lbl = findViewById(R.id.lblColumnName);
        lbl.setText("商品コード:");

        EditText et = findViewById(R.id.etValue);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
//        et.setInputType(InputType.TYPE_CLASS_NUMBER);
//
        Button bt = findViewById(R.id.btnSearch);
        bt.setOnClickListener((buttonView) -> OpenItemCodeSearch());
    }

    private void OpenItemCodeSearch() {
        EditText et = findViewById(R.id.etValue);
        //Uri uri = Uri.parse(Util.ShohinCodeURL_A + et.getText() + Util.ShohinCodeURL_B);
        Uri uri = Uri.parse(Util.ShohinCodeURL + et.getText());
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}
