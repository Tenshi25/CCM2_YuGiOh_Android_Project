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
    private ArrayList<CardJson> listCardDef = new ArrayList<CardJson>();

    public void sendPost(CardInGame[] listcard, Monstre monstreAttaquant) {
        CardJson monstreAtk = new CardJson();
        monstreAtk.setId(monstreAttaquant.getIdNumberInGame());
        monstreAtk.setName(monstreAttaquant.getName());
        monstreAtk.setAtk(monstreAttaquant.getAtk());
        monstreAtk.setDef(monstreAttaquant.getDef());
        monstreAtk.setPosition(monstreAttaquant.getPosition());

        for (CardInGame card : listcard) {
            CardJson monstreDef = new CardJson();
            monstreAtk.setId(card.getIdNumberInGame());
            monstreAtk.setName(card.getName());
            monstreAtk.setAtk(((Monstre) card).getAtk());
            monstreAtk.setDef(((Monstre) card).getDef());
            monstreAtk.setPosition(((Monstre) card).getPosition());
            listCardDef.add(monstreDef);
        }

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
                    jsonParam.put("monstresDeffenseur", listCardDef);

                    Log.i("JSON", jsonParam.toString());
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
