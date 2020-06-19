package ZOA.Android.BarcodeReader;

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

//        EditText et = findViewById(R.id.etValue);
//        et.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//        Button bt = findViewById(R.id.btnSearch);
//        bt.setOnClickListener((buttonView) -> OpenItemNoSearch());
    }
}
