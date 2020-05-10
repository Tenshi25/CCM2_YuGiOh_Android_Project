package master.ccm.entity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class HttpRequest {
    private String urlAdress = "https://ia-yugioh.herokuapp.com/";
    //private CardJson[] listCardDef = new CardJson[5];

    public void sendPost(CardInGame[] listcard, Monstre monstreAttaquant) {
        CardJson[] tabMonstre = new CardJson[10];
        CardJson monstreAtk = new CardJson();
        monstreAtk.setId(monstreAttaquant.getId());
        monstreAtk.setName(monstreAttaquant.getName());
        monstreAtk.setAtk(monstreAttaquant.getAtk());
        monstreAtk.setDef(monstreAttaquant.getDef());
        monstreAtk.setPosition(monstreAttaquant.getPosition());
        tabMonstre[0] = monstreAtk;

        for (int i = 5; i < 10; i++) {
            CardJson monstreDef = new CardJson();
            if (listcard[i - 5] != null) {

                monstreDef.setId(listcard[i - 5].getId());
                monstreDef.setName(listcard[i - 5].getName());
                monstreDef.setAtk(((Monstre) listcard[i - 5]).getAtk());
                monstreDef.setDef(((Monstre) listcard[i - 5]).getDef());
                monstreDef.setPosition(((Monstre) listcard[i - 5]).getPosition());
                tabMonstre[i] = monstreDef;
            } else {
                tabMonstre[i] = null;
            }
        }
        Log.d("JSON", "SendJSON");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    String response = "";

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    //conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    //JSONObject jsonParam = new JSONObject();
                    //jsonParam.put("monstreAttaquant", monstreAtk);
                    //jsonParam.put("tabmonstres", tabMonstre);
                    //jsonParam.put("tabmonstres", tabMonstre);

                    //Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    //os.writeBytes(jsonParam.toString());
                    os.writeBytes(tabMonstre.toString());
                    Log.i("JSON", String.valueOf(tabMonstre.length));
                    Log.i("JSON", tabMonstre.toString());
                    //os.writeBytes(String.valueOf(tabMonstre));
                    //os.write(String.valueOf(tabMonstre));


                    os.flush();
                    os.close();
                    int responseCode=conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            response+=line;
                        }
                    }
                    else {
                        response="";

                    }
                    Log.i("JSON", "res : "+response);
                    Log.i("JSON", "STATUS"+String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    //recuperation de la reponse

                    //result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
