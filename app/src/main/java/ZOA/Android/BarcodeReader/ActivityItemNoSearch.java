package ZOA.Android.BarcodeReader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityItemNoSearch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsearch);
        setTitle("商品番号検索");

        TextView lbl = findViewById(R.id.lblColumnName);
        lbl.setText("商品番号:");

        EditText et = findViewById(R.id.etValue);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);

        Button bt = findViewById(R.id.btnSearch);
        bt.setOnClickListener((buttonView) -> OpenItemNoSearch());
    }

    private void OpenItemNoSearch() {
        EditText et = findViewById(R.id.etValue);
        //Uri uri = Uri.parse(Util.ShohinNoURL_A + et.getText() + Util.ShohinNoURL_B);
        Uri uri = Uri.parse(Util.ShohinNoURL + et.getText());
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}
