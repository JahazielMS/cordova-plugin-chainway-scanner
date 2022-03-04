package cordova_plugin_chainway_scanner_id;

import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.webkit.WebView;

public class MainActivity extends CordovaActivity implements IBarcodeResult {
    private Barcode2D barcode2D;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();

        barcode2D = new Barcode2D(this);
        // jsInterface = new JsInterface(this);

        new InitTask().execute();
        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // INJECT OUR JS INTERFACE HERE!
        // WebView webView = (WebView) this.appView.getEngine().getView();
        // webView.getSettings().setJavaScriptEnabled(true);
        // webView.addJavascriptInterface(new JsInterface(webView.getContext()), "android");

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    public void initScanner() {
        Log.d(TAG,"initScanner");
    }

    // @Override
    public void getBarcode(String barcode) {
        Log.d(TAG,"BarcodeResult:" + barcode);
        // WebView webView = null;
        // webView.addJavascriptInterface(barcode), "android");
    }

    public void coolMethod() {
        Log.d(TAG,"coolMethod Test");
    }


    public void execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "coolMethod":
                // localNotification(args, callbackContext);
                coolMethod();
                break;
            case "localNotificationSchedule":
                // localNotificationSchedule(args.getJSONObject(1), callbackContext);
                break;
            case "cancelAllNotifications":
                // cancelAllNotifications(callbackContext);
                break;
            case "cancelNotifications":
                // cancelNotifications(callbackContext);
                break;
            case "cancelScheduledNotifications":
                // cancelScheduledNotifications(callbackContext);
                break;
            case "cancelNotificationsWithId":
                // cancelNotificationsWithId(args.getJSONArray(1), callbackContext);
                break;
        }
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                open();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG,"onPostExecute:" + result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"onPreExecute: init..." );
    }
    }

    private void start() {
        barcode2D.startScan(this);
    }

    private void stop() {
        barcode2D.stopScan(this);
    }

    private void open() {
        barcode2D.open(this, this);
    }

    private void close() {
        barcode2D.stopScan(this);
        barcode2D.close(this);
    }
}
