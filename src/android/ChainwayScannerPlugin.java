package cordova_plugin_chainway_scanner_id;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.content.Broadcast.Receiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.rscja.barcode.BarcodeUtility;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class ChainwayScannerPlugin extends CordovaPlugin implements IBarcodeResult{

    private String TAG = "ChainwayLog";
    private Barcode2D barcode2D;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if ("startScan".equals(action)) {
            start();
        } else if ("stopScan".equals(action)) {
            stop();
        } else if ("open".equals(action)) {
            open();
        } else if ("close".equals(action)) {
            close();
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        close();
        super.onDestroy();
        android.os.Process.killProcess(Process.myPid());
    }

    @Override
    public void getBarcode(String barcode) {
        Log.d(TAG, barcode);
        // if (tvData.getLineCount() > 25) {
            // tvData.setText("");
        // }
        // tvData.append(barcode);
        // tvData.append("\n");
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

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
            mypDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mypDialog = new ProgressDialog(MainActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.setCancelable(false);
            mypDialog.show();
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
