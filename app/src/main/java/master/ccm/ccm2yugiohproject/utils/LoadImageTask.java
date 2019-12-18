package master.ccm.ccm2yugiohproject.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import master.ccm.ccm2yugiohproject.DeckBuilder_Activity;

public class LoadImageTask extends AsyncTask<Void,Void,Void>
{

    private String url;
    private ImageView imageView;
    private static Map<String, Bitmap> urlAlreadyChecked;

    public LoadImageTask() {

    }

    public LoadImageTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    protected void onPreExecute() {
        //display progress dialog.

    }
    protected Void doInBackground(Void... params) {
        if (urlAlreadyChecked == null) {
            urlAlreadyChecked = new HashMap<>();
        }

        Bitmap bitmapAlreadyObtained = urlAlreadyChecked.get(this.url);

        if (bitmapAlreadyObtained == null) {
            try {
                Log.i("LoadImageTask", "load image from url  : " + this.url);

                URL url = new URL(getUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                urlAlreadyChecked.put(this.url, myBitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("LoadImageTask", "Error when load image from url  : " + this.url);
            }
        } else {
            Log.i("LoadImageTask", "use bitmap already obtained when load image from url  : " + this.url);
        }

        return null;
    }

    protected void onPostExecute(Void result) {
        Bitmap bitmapAlreadyObtained = urlAlreadyChecked.get(this.url);

        if (bitmapAlreadyObtained != null) {
            DeckBuilder_Activity.setImg(bitmapAlreadyObtained, this.imageView);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
