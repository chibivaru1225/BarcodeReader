package ZOA.Android.BarcodeReader;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class Util {
    public static String BaseURL = "http://webhn.local.zoa.co.jp:60001/magic94Scripts/mgrqispi94.dll";

    //public static String BarcodeURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByJAN02&ARGUMENTS=-N";
    public static String BarcodeURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByJAN03&ARGUMENTS=-N";

    public static String IDURL = ",-A";
    public static String MACURL = ",-A";
    public static String VERURL = ",-A";

    //public static String ShohinNoURL_A = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=%8F%A4%95i%8C%9F%8D%F5%BB%CC%DE&ARGUMENTS=tenpo%2CshohinNo%2CshohinJan%2CshohinCode&shohinNo=";
    //public static String ShohinNoURL_B = "&tenpo=&shohinJan=&shohinCode=&kensaku=";
    //public static String ShohinNoURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByNum&ARGUMENTS=-N";
    public static String ShohinNoURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByNum&ARGUMENTS=-N";

    //public static String ShohinCodeURL_A = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=%8F%A4%95i%8C%9F%8D%F5%BB%CC%DE&ARGUMENTS=tenpo%2CshohinNo%2CshohinJan%2CshohinCode&shohinNo=&tenpo=&shohinJan=&shohinCode=";
    //public static String ShohinCodeURL_B = "&kensaku=";
    //public static String ShohinCodeURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByCode&ARGUMENTS=-A";
    public static String ShohinCodeURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByCode&ARGUMENTS=-A";

    public static String MACAddress;
    public static String Version;
    public static String ID;

    public static String SharedPreference_Key = "ZOA_ID";
    public static String SharedPreference_FileName = "ZOA";

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public static void copyToClipboard(Context context, String label, String text) {
        // copy to clipboard
        ClipboardManager clipboardManager =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (null == clipboardManager) {
            return;
        }
        clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text));
    }
}
