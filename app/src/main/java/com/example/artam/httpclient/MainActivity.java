package com.example.artam.httpclient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button button;
    TextView text;
    EditText editText;
    URL url;
    String site;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  text   = (TextView) findViewById(R.id.textView);

        //  isOnline();


   /* public void  isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            text.setText("Cool!!");
        }

        text.setText("Error");
    }*/


        //   URLConnection openConnection();
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                site = editText.getText().toString();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(site)
                        .build();

                client.newCall(request).enqueue(new Callback() { // http://stackoverflow.com/questions/35541733/okhttp-android-download-a-html-page-and-display-this-content-in-a-view
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {

                            final String aFinalString = response.body().string();
                            //final Spanned htmlaFinalString = Html.fromHtml(aFinalString);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    text = (TextView) findViewById(R.id.textView);
                                    text.setMovementMethod(new ScrollingMovementMethod());
                                   // text.setText(htmlaFinalString);
                                    //text.setText(aFinalString);
                                    //text.setText(Html.fromHtml(aFinalString));
                                    mWebView = (WebView) findViewById(R.id.activity_main_webview);
                                    //text.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
                                }
                            });
                        }
                    }
                });

                //text.setText("Button pressed!");
            }
        });

       // String url = "http://square.github.io/okhttp/";






       /*new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(site)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "doInBackground() called with: voids = [" + response.body().string() + "]");
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null; }
            }.execute();
*/


    }
}