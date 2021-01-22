package com.example.appcontacts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ContactsActivity extends AppCompatActivity{
    String ms ="";
    EditText _txtNom,_txtAdr,_txttel1, _txttel2, _txtEntreprise;
    Button _btnAdd;



    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        _txtNom = (EditText) findViewById(R.id.txtNom);
        _txtAdr = (EditText) findViewById(R.id.txtAdr);
        _txttel1 = (EditText) findViewById(R.id.txttel1);
        _txttel2 = (EditText) findViewById(R.id.txttel2);
        _txtEntreprise = (EditText) findViewById(R.id.txtEntreprise);
        _btnAdd = (Button) findViewById(R.id.btnAdd);

    }

            _btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Nom = _txtNom.getText().toString();
                    String Address = _txtAdr.getText().toString();
                    String Tel1 = _txttel1.getText().toString();
                    String Tel2 = _txttel2.getText().toString();
                    String Entreprise = _txtEntreprise.getText().toString();

                        bg_insertion_contact bg = new bg_insertion_contact(ContactsActivity.this);
                        bg.execute(Nom,Address,Tel1,Tel2,Entreprise);
                    }
            }
        }


        private class bg_insertion_contact extends AsyncTask<String, Void, String> {

            AlertDialog dialog;
            Context context;

            public bg_insertion_contact(Context context) {
                this.context = context;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new AlertDialog.Builder(context).create();
                dialog.setTitle("Etat de connexion");
            }

            @Override
            protected String doInBackground(String... strings) {

                String result = "";

                String Nom = strings[0];
                String Adresse = strings[1];
                String Tel1 = strings[2];
                String Tel2 = strings[3];
                String Entreprise = strings[4];

                String connstr = "http://ipaddress/contacts/Contacts.php";
                try {
                    URL url = new URL(connstr);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    OutputStream ops = http.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));

                    String data = URLEncoder.encode("Nom", "UTF-8") + "=" + URLEncoder.encode(Nom, "UTF-8");
                   data = URLEncoder.encode("Adresse", "UTF-8") + "=" + URLEncoder.encode(Adresse, "UTF-8");
                    data = URLEncoder.encode("Tel1", "UTF-8") + "=" + URLEncoder.encode(Tel1, "UTF-8");
                  data = URLEncoder.encode("Tel2", "UTF-8") + "=" + URLEncoder.encode(Tel2, "UTF-8");
                    data = URLEncoder.encode("Entreprise", "UTF-8") + "=" + URLEncoder.encode(Entreprise, "UTF-8");




                    writer.write(data);
                    Log.v("ContactsActivity", data);
                    writer.flush();
                    writer.close();
                    InputStream ips = http.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
                    String ligne = "";
                    while ((ligne = reader.readLine()) != null) {
                        result += ligne;
                    }
                    reader.close();
                    ips.close();
                    http.disconnect();

                    return result;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                dialog.setMessage(s);
                dialog.show();
                if (s.contains("succes insertion")) {
                    Toast.makeText(context, "Contact ajoute ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Problème d'ajout.", Toast.LENGTH_LONG).show();
                }
            }
        }






