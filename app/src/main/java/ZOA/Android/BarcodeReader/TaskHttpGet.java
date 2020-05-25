package ZOA.Android.BarcodeReader;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class TaskHttpGet extends AsyncTask<String, Integer, Boolean> {

    private WifiP2pManager.ActionListener listener;

    //private ShelfStatus status;

    public TaskHttpGet() {
        this.listener = null;
    }

    public TaskHttpGet(WifiP2pManager.ActionListener listener) {
        this.listener = listener;
    }

    public boolean setActionListener(WifiP2pManager.ActionListener listener) {
        try {
            this.listener = listener;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Boolean doInBackground(String... itemid) {

        HttpURLConnection httpConn = null;
        String urlfull = Util.BaseURL + itemid[0];
        InputStream in;
        String encoding;
        History his;
        //ShelfStatus.SetNewKey(itemid[0]);

        try {
            // URL設定
            URL url = new URL(urlfull);

            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(1000);
            httpConn.setReadTimeout(10000);
            httpConn.connect();

            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("ああああ");
                // 通信に成功した
                // テキストを取得する
                in = httpConn.getInputStream();
                encoding = httpConn.getContentEncoding();
                if (null == encoding) {
                    encoding = "UTF-8";
                }

                String responseData = "";
                InputStream stream = httpConn.getInputStream();
                StringBuffer sb = new StringBuffer();
                String line = "";
                Charset charset1 = Charset.forName("Shift_JIS");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream, charset1));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                responseData = sb.toString();

                if (responseData.contains("エラー")) {
                    return false;
                } else {
//                    his = new History(itemid[0]);
//
//                    Document document = Jsoup.parse(responseData);


                    return true;
                }
            } else {
                return false;
            }
//            try (// POSTデータ送信処理
//                 OutputStream outStream = httpConn.getOutputStream()) {
//                outStream.write(word.getBytes(StandardCharsets.UTF_8));
//                outStream.flush();
//            } catch (IOException e) {
//                // POST送信エラー
//                e.printStackTrace();
//            }
//
//            final int status = httpConn.getResponseCode();
//            if (status == HttpURLConnection.HTTP_OK) {
//                // レスポンスを受け取る処理等
//                result = "HTTP_OK";
//            } else {
//                result = "status=" + String.valueOf(status);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result) {
            listener.onSuccess();
        } else {
            listener.onFailure(0);
        }
    }
}
