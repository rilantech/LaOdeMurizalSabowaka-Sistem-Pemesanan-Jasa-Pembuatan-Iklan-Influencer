package com.example.aplikasivannes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationService extends Service {
    static final /* synthetic */ boolean $assertionsDisabled = false;
   public static String isi_notif, isi_notif2, id_progress;
    String channelid = "default";
    String channelid2 = "default1";
    String channelnotif = "channelku";
    String channelnotif2 = "channelku1";
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            if (Login_Brand.username != null) {
                NotificationService.this.handler.postDelayed(this, 1000);
                NotificationService.this.tampilkanNotifikasi();
            }
        }
    };

    public void onCreate() {
        try {
            this.handler.postDelayed(this.runnable, 2000);
            super.onCreate();
        } catch (NumberFormatException e) {
            Log.e("Error", e.getMessage());
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: private */
    public void tampilkanNotifikasi() {
        Volley.newRequestQueue(this).add(new StringRequest(1, new Configurasi().baseUrl() + "brand/api/notifikasi_brand.php", new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String jenis_notif = jsonObject.getString("jenis_notif");
//                    id_progress = jsonObject.getString("id_progress");
//                    isi_notif2 = jsonObject.getString("isi_notif");
                    if (jenis_notif.equals("notif_brand")) {

//                        NotificationService.agenda = jsonObject.getString("agenda");
//                        NotificationService.jam = jsonObject.getString("jam");
//                        NotificationService.tanggal = jsonObject.getString("tanggal");
                        NotificationService.this.notifhmin();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form= new HashMap<String, String>();
                form.put("nama_brand", Login_Brand.nama_brand);
                return form;
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifhmin() {
        Intent intent =new Intent(this, Halaman_Utama_Brand.class);
                intent.putExtra("fragment", "fragment_notifikasi_brand");
                isi_notif2 = "Hallo " + Login_Brand.nama_brand + ",\n" + "Ada Notif Baru";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder((Context) this, this.channelid).setSmallIcon((int) R.drawable.logoapk2).setContentText(isi_notif2).setContentTitle("Notifikasi").addAction(R.drawable.logo_kembali, "Detail", PendingIntent.getActivity(this, 0, intent, 0)).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.channelnotif, "contoh channel",  NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationBuilder.setChannelId(this.channelnotif);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            } else {
                throw new AssertionError();
            }
        }
        if (notificationManager != null) {
            notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
}
