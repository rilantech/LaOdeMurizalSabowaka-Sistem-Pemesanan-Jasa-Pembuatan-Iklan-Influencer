package com.example.aplikasivannes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Halaman_Utama_Influencer extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tvNama_Influencer, tvNAMAINFLUENCER;
    ImageView foto_profil, buttonlogout, buttonnotifikasi;
    PreferenceHelper preferenceHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama_influencer);
        preferenceHelper = new PreferenceHelper(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tvNama_Influencer = findViewById(R.id.tvNama_Influencer);
        tvNAMAINFLUENCER = findViewById(R.id.tvNAMAINFLUENCER);
        foto_profil = findViewById(R.id.foto_profil);
        buttonlogout = findViewById(R.id.buttonlogout);
        buttonnotifikasi = findViewById(R.id.buttonnotifikasi);

        String urlgambar = new Configurasi().baseUrl()+"influencer/gambar/"+Login_Influencer.foto_profil;

        Glide.with(Halaman_Utama_Influencer.this)
                .load(urlgambar)
                .override(80, 80)
                .circleCrop()
//                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(foto_profil);

        tvNama_Influencer.setText(Login_Influencer.nama_influencer);
        tvNAMAINFLUENCER.setText("NAMAINFLUENCER : "+ preferenceHelper.getNama());

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Halaman_Utama_Influencer.this);
                builder.setMessage("Yakin Ingin Logout ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        preferenceHelper.putIsLogin(false);
                        Intent intent = new Intent(Halaman_Utama_Influencer.this, Login_Influencer.class);
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
                Fragment fragment = new Fragment_Notifikasi_Influencer();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.konten, fragment).commit();
            }
        });
        
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.project)
                {
                    buka_fragment(new Fragment_Project_Influencer());
                    return true;
                }
                if(menuItem.getItemId() == R.id.progress)
                {
                    buka_fragment(new Fragment_Progress_Influencer());
                    return true;
                }
                if(menuItem.getItemId() == R.id.profil)
                {
                    buka_fragment(new Fragment_Profil_Influencer());
                    return true;
                }
                return false;
            }
        });
        buka_fragment(new Fragment_Project_Influencer());

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
}