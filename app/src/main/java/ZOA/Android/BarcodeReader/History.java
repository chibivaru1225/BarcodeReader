package ZOA.Android.BarcodeReader;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class History {

    private String itembarcode;
    private Date createtime;

    public History(String code) {
        this.itembarcode = code;
        this.createtime = new Date();
    }

    public String GetItemBarcode() {
        return this.itembarcode;
    }

    public String GetCreateTime()
    {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(createtime);
    }


    private static ArrayList<History> histories = new ArrayList<>();
    private static History latest;

    public static void AddHistory(History his) {
        histories.add(his);
    }

    public static void ClearHistory() {
        histories.clear();
    }

    public static ArrayList<History> GetHistories() {
        return histories;
    }

    public static History GetLatest() {
        return latest;
    }
}
