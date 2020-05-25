package ZOA.Android.BarcodeReader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.asreader.asbarcode.AsBarcode;
import com.asreader.asbarcode.AsBarcodeScanFragment;
import com.asreader.asbarcode.AsFocusPointer;
import com.asreader.asbarcode.AsPointer;
import com.asreader.asbarcode.AsPointerInterface;
import com.asreader.asbarcode.AsPointerManager;
import com.asreader.asbarcode.AsPointerManagerInterface;
import com.asreader.asbarcode.CertifiedSDKInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static ZOA.Android.BarcodeReader.R.*;

public class ActivityReaderMain extends AppCompatActivity implements AsPointerManagerInterface, AsPointerInterface, CertifiedSDKInterface, WifiP2pManager.ActionListener {

    private AsBarcode asBarcode;
    private AsPointerManager mAsPointerManager;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private AsBarcodeScanFragment mAsBarcodeScanView;
    private Button mScanButton;
    private boolean open;
    private boolean createview;
    private TaskHttpGet task;
    private String barcodekey;

    private SharedPreferences pre;
    private SharedPreferences.Editor edi;

    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.readermain);

        asBarcode = AsBarcode.getInstance();

        mAsBarcodeScanView = asBarcode.getScanFragment();
        mAsPointerManager = AsPointerManager.getInstance();
        open = false;
        createview = false;

        pre = getSharedPreferences(getString(R.string.app_result_data), MODE_PRIVATE);
        edi = pre.edit();
        sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");

        //task = new TaskHttpGet(this);
        barcodekey = "";
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION);
//            return;
//        }
//        mScanButton = findViewById(R.id.button);
//        mScanButton.setOnTouchListener((view, motionEvent) -> {
//
//            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP) {
//
//                asBarcode.startScan();
//            }
//
//            return false;
//        });
    }

    @Override
    public void receivedAsPointerIsConnected(Boolean aBoolean) {

    }

    @Override
    public void receivedAsPointerLaserOn(Boolean aBoolean) {

    }

    @Override
    public void receivedAsPointerBattery(RemainingBatteryPower remainingBatteryPower) {

    }

    @Override
    public void whenAsPointerLEDIsCanUsed() {

    }

    @Override
    public void whenAsPointerManufacturerNameReceived() {

    }

    @Override
    public void whenAsPointerHardwareVersionReceived() {

    }

    @Override
    public void whenAsPointerFirmwareVersionReceived() {

    }

    @Override
    public void whenSearchedAsPointerList(ArrayList<AsPointer> arrayList) {

    }

    @Override
    public synchronized Boolean continueScanWhenReceivedScanData(HashMap<String, String> hashMap, Boolean aBoolean) throws CameraAccessException {
        try {
            //Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
            if (hashMap != null) {
                for (String key : hashMap.keySet()) {
                    //asBarcode.stopScan();
                    Uri uri = Uri.parse(Util.BaseURL + key);
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);

//                    this.barcodekey = key;
//                    task = new TaskHttpGet(this);
//                    task.execute(key);

                    break;
                    //return false;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
//        return null;
        // Log.d(LOG_TAG, "continueScanWhenReceivedScanData 1 results: " + mapToString(results) + ", isTimeout: " + isTimeout);

        return true;
    }

    @Override
    public void whenCertifySucceeded(Boolean aBoolean, CertifiedType certifiedType) {

    }

    @Override
    public void whenGetCapabilities() {

    }

    @Override
    public void onResume() {
        super.onResume();

        setInitConfig();

        if (createview == false) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
                return;
            }
            android.app.FragmentManager manager = this.getFragmentManager();

            android.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(id.frameLayout, mAsBarcodeScanView, "fragment");
            transaction.commit();

            createview = true;
        }

        try {
            if (open == false) {
                //Toast.makeText(this, "g", Toast.LENGTH_SHORT).show();
                open = true;
                asBarcode.openCamera(this);
            } else {
                asBarcode.reloadCamera(this);
            }
            asBarcode.setTimeout(0);
            asBarcode.startScan();
//
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
//        Toast.makeText(this, "そろそろ寝よう", Toast.LENGTH_SHORT).show();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
//            return;
//        }
//        android.app.FragmentManager manager = this.getFragmentManager();
//
//        android.app.FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frameLayout, mAsBarcodeScanView, "fragment");
//        transaction.commit();

//        AsBarcode.CurrentCameraType currentCameraType = asBarcode.getCurrentCameraType(this);
//        if (currentCameraType != AsBarcode.CurrentCameraType.CameraType_BackFacing) {
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.axa0100000_no_camera)
//                    .setPositiveButton(getResources().getString(R.string.common_msg_btn01), (dialog, which) -> {
//                        // OKボタン押下時の処理
//                        finish();
//                    })
//                    // キャンセルイベント
//                    .setOnCancelListener(dialog -> {
//                        // キャンセルの処理W
//                        finish();
//                    })
//                    .show();
//
//            return;
//        }

        try {
//            if (open == false) {
//                open = true;
//                asBarcode.openCamera(this);
//            }
            asBarcode.setTimeout(0);
            asBarcode.startScan();

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // 復帰時に取り直し動かせるようにするため共通化
    private void setInitConfig() {
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        asBarcode.setSymbogogiesArray(null);

        asBarcode.setDouble1Dcode(false);
        asBarcode.setCheckComposite(false);

        asBarcode.addSymbogogies(AsBarcode.Symbogogie.Code39);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.Code93);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.Code128);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.EAN2);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.EAN5);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.EAN8);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.EAN13);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.ITF);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.ISBN10);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.ISBN13);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.UPCA);
        asBarcode.addSymbogogies(AsBarcode.Symbogogie.NW7);

//        int position = 100;
//        int height = 100;
        AsFocusPointer asFocusPointer = new AsFocusPointer();
        asFocusPointer.setY(400);

        asBarcode.setTargetDistanceType(AsBarcode.TargetDistanceType.TargetDistanceType_Normal);

        asBarcode.setScanningPosition(asFocusPointer);
        asBarcode.setScanningAreaHeight(100);

        asBarcode.setFullScreenScan(false);

//        asBarcode.setVerifyCount(2);
//        asBarcode.setFrequencyLimit((float)(2 * 10));

        //mTimeout = Float.parseFloat(sharedPreferences.getString(AsCameraConstants.pref_key_timeout, AsCameraConstants.default_key_timeout));
//        asBarcode.setTimeout(0);
//        asBarcode.setAutoExposure(true);

        asBarcode.setReportBNR(true);
    }

    @Override
    public void onSuccess() {
        History his = new History(barcodekey);
        History.AddHistory(his);

        Uri uri = Uri.parse(Util.BaseURL + barcodekey);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    @Override
    public void onFailure(int reason) {
        Toast.makeText(this, "読取失敗", Toast.LENGTH_SHORT).show();
        if (open == false) {
            //Toast.makeText(this, "g", Toast.LENGTH_SHORT).show();
            open = true;
            asBarcode.openCamera(this);
        } else {
            asBarcode.reloadCamera(this);
        }
        asBarcode.setTimeout(0);
        asBarcode.startScan();

        //task
    }
}
