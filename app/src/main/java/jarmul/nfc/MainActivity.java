package jarmul.nfc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;
    private UserLogoutTask mAuthsTask = null;
    private UserRegisTask mRegTask = null;

    private TextView li;
    private String aidi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent wewew = getIntent();
        aidi = wewew.getStringExtra("ID");

        li = (TextView)findViewById(R.id.textView);
        li.setText(aidi);

        Button btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mAuthTask = new UserLoginTask(aidi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mAuthTask.execute((Void) null);
            }

        });

        Button btnLogout = (Button) findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mAuthsTask = new UserLogoutTask(aidi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mAuthsTask.execute((Void) null);
            }

        });

        Button btnRegis = (Button) findViewById(R.id.register);
        btnRegis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mRegTask = new UserRegisTask(aidi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mRegTask.execute((Void) null);
            }

        });
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String aidi;
        private final OkHttpClient client = new OkHttpClient();

        private boolean send_aidi() throws Exception {

            RequestBody formBody = new FormBody.Builder()
                    .add("idnfc", aidi)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.75/parkjisung/Parkir/nfclogin")
                    .post(formBody)
                    .build();

            // Execute the request and retrieve the response.
            // TODO: 8/1/2016 add conn error handler
            Response response = client.newCall(request).execute();
//            ResponseBody body = response.body();
//            String json = body.string();
//
//            li.setText(json);

            if (response.isSuccessful()) {
                return true;
            }
            else
                return false;
        }

        UserLoginTask(String aidi) {
            this.aidi = aidi;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                if(send_aidi()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //if sukses true else false

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
                Toast.makeText(MainActivity.this, "Berhasil Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Gagal Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    public class UserLogoutTask extends AsyncTask<Void, Void, Boolean> {

        private final String aidi;
        private final OkHttpClient client = new OkHttpClient();

        private boolean send_aidi() throws Exception {

            RequestBody formBody = new FormBody.Builder()
                    .add("idnfc", aidi)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.75/parkjisung/Parkir/nfclogout")
                    .post(formBody)
                    .build();

            // Execute the request and retrieve the response.
            // TODO: 8/1/2016 add conn error handler
            Response response = client.newCall(request).execute();
//            ResponseBody body = response.body();
//            String json = body.string();
//
//            li.setText(json);

            if (response.isSuccessful()) {
                return true;
            }
            else
                return false;
        }

        UserLogoutTask(String aidi) {
            this.aidi = aidi;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                if(send_aidi()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //if sukses true else false

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthsTask = null;

            if (success) {
                finish();
                Toast.makeText(MainActivity.this, "Berhasil Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Gagal Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthsTask = null;
        }
    }

    public class UserRegisTask extends AsyncTask<Void, Void, Boolean> {

        private final String aidi;
        private final String enefsi;
        private final OkHttpClient client = new OkHttpClient();
        private EditText mEdit   = (EditText)findViewById(R.id.editText);

        private boolean send_regis() throws Exception {

            RequestBody formBody = new FormBody.Builder()
                    .add("idpeng", aidi)
                    .add("idnfc", enefsi)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.75/parkjisung/Parkir/nfcreg")
                    .post(formBody)
                    .build();

            // Execute the request and retrieve the response.
            // TODO: 8/1/2016 add conn error handler
            Response response = client.newCall(request).execute();
//            ResponseBody body = response.body();
//            String json = body.string();
//
//            li.setText(json);

            if (response.isSuccessful()) {
                return true;
            }
            else
                return false;
        }

        UserRegisTask(String aidi) {
            this.enefsi = aidi;
            this.aidi = mEdit.getText().toString();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                if(send_regis()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //if sukses true else false

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegTask = null;

            if (success) {
                finish();
                Toast.makeText(MainActivity.this, "Berhasil Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Gagal Kirim Aidi :)", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mRegTask = null;
        }
    }

}
