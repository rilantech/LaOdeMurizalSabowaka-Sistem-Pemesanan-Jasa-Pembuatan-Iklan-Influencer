package com.example.aplikasivannes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Halaman_Utama_Brand extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tvNama_Brand, tvNAMABRAND;
    ImageView foto_profil, buttonlogout, buttonnotifikasi;
    PreferenceHelper preferenceHelper;
    public static int width, height;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama_brand);
        preferenceHelper = new PreferenceHelper(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tvNama_Brand = findViewById(R.id.tvNama_Brand);
        tvNAMABRAND = findViewById(R.id.tvNAMABRAND);
        foto_profil = findViewById(R.id.foto_profil);
        buttonlogout = findViewById(R.id.buttonlogout);
        buttonnotifikasi = findViewById(R.id.buttonnotifikasi);


        String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+Login_Brand.foto_profil;

        Glide.with(Halaman_Utama_Brand.this)
                .load(urlgambar)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
//                        width = resource.getIntrinsicWidth();
//                        height= resource.getIntrinsicHeight();
//                        return false;
//                    }
//                })
//                .override(width, height)
                .override(50, 50)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(foto_profil);

        tvNama_Brand.setText(Login_Brand.nama_brand);
        tvNAMABRAND.setText("NAMABRAD : "+ preferenceHelper.getNama());

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Halaman_Utama_Brand.this);
                builder.setMessage("Yakin Ingin Logout ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        preferenceHelper.putIsLogin(false);
                        Intent intent = new Intent(Halaman_Utama_Brand.this, Login_Brand.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        buttonnotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Fragment fragment = new Fragment_Notifikasi_Brand();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.konten, fragment).commit();
            }
        });






        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.project)
                {
                    buka_fragment(new Fragment_Project_Brand());
                    return true;
                }
                if(menuItem.getItemId() == R.id.progress)
                {
                    buka_fragment(new Fragment_Progress_Brand());
                    return true;
                }
                if(menuItem.getItemId() == R.id.profil)
                {
                    buka_fragment(new Fragment_Profil_Brand());
                    return true;
                }
                return false;
            }
        });

        if(getIntent().hasExtra("fragment")){
            Fragment fragment1 = new Fragment_Notifikasi_Brand();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.konten, fragment1).commit();
        } else{
            buka_fragment(new Fragment_Project_Brand());
        }


    }

    Boolean buka_fragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.konten, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        startService(new Intent(this, NotificationService.class));
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK){
            startService(new Intent(this, NotificationService.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        startService(new Intent(this, NotificationService.class));
        super.onBackPressed();
    }
}