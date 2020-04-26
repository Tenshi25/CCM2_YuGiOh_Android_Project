package master.ccm.entity;
import android.util.Log;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class HttpRequest {
    private String urlAdress = "mon url";
    //private CardJson[] listCardDef = new CardJson[5];

    public void sendPost(CardInGame[] listcard, Monstre monstreAttaquant) {
            CardJson[] tabMonstre = new CardJson[10];
            CardJson monstreAtk = new CardJson();
            monstreAtk.setId(monstreAttaquant.getId());
            monstreAtk.setName(monstreAttaquant.getName());
            monstreAtk.setAtk(monstreAttaquant.getAtk());
            monstreAtk.setDef(monstreAttaquant.getDef());
            monstreAtk.setPosition(monstreAttaquant.getPosition());
            tabMonstre[0] =monstreAtk;

        for (int i = 5; i < 10; i++) {
            CardJson monstreDef = new CardJson();
            if (listcard[i-5] != null){

                monstreDef.setId(listcard[i-5].getId());
                monstreDef.setName(listcard[i-5].getName());
                monstreDef.setAtk(((Monstre) listcard[i-5]).getAtk());
                monstreDef.setDef(((Monstre) listcard[i-5]).getDef());
                monstreDef.setPosition(((Monstre) listcard[i-5]).getPosition());
                tabMonstre[i] = monstreDef;
            }else{
                tabMonstre[i] = null;
            }
        }
        Log.d("JSON", "SendJSON");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("monstreAttaquant", monstreAtk);
                    jsonParam.put("monstresDeffenseur", tabMonstre);

                    Log.d("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
