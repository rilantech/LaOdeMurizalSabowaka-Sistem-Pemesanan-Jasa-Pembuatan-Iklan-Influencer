package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jetbrains.annotations.Nullable;

public class Fragment_Profil_Influencer extends Fragment {
    ImageView ivLogo_Influencer;
    TextView tvNama_Influencer, tvUsername, tvPassword, tvEmail, tvNomor_Telepon, tvSosmed_Tiktok, tvPengikut_Sosmed_Tiktok, tvSosmed_Instagram, tvPengikut_Sosmed_Instagram, tvSosmed_Facebook, tvPengikut_Sosmed_Facebook;
    public static String foto_profil, nama_influencer, username, password, email, nomor_telepon, sosmed_tiktok, pengikut_sosmed_tiktok,sosmed_instagram, pengikut_sosmed_instagram, sosmed_facebook, pengikut_sosmed_facebook;
    Button btnEdit, btnRiwayat, btnAbout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_influencer, container, false);
           if(view != null){
               ivLogo_Influencer = view.findViewById(R.id.ivLogo_Influencer);
               tvNama_Influencer = view.findViewById(R.id.tvNama_Influencer);
               tvUsername = view.findViewById(R.id.tvUsername);
               tvPassword = view.findViewById(R.id.tvPassword);
               tvEmail = view.findViewById(R.id.tvEmail);
               tvNomor_Telepon = view.findViewById(R.id.tvNomor_Telepon);
               tvSosmed_Tiktok = view.findViewById(R.id.tvSosmed_Tiktok);
               tvPengikut_Sosmed_Tiktok = view.findViewById(R.id.tvPengikut_Sosmed_Tiktok);
               tvSosmed_Instagram = view.findViewById(R.id.tvSosmed_Instagram);
               tvPengikut_Sosmed_Instagram = view.findViewById(R.id.tvPengikut_Sosmed_Instagram);
               tvSosmed_Facebook = view.findViewById(R.id.tvSosmed_Facebook);
               tvPengikut_Sosmed_Facebook = view.findViewById(R.id.tvPengikut_Sosmed_Facebook);
               btnEdit = view.findViewById(R.id.btnEdit);
               btnRiwayat = view.findViewById(R.id.btnRiwayat);
               btnAbout = view.findViewById(R.id.btnAbout);

               nama_influencer = Login_Influencer.nama_influencer;
               username = Login_Influencer.username;
               password = Login_Influencer.password;
               email = Login_Influencer.email;
               nomor_telepon = Login_Influencer.nomor_telepon;

               sosmed_tiktok=  Login_Influencer.sosmed_tiktok;
               pengikut_sosmed_tiktok=  Login_Influencer.pengikut_sosmed_tiktok;
               sosmed_instagram=  Login_Influencer.sosmed_instagram;
               pengikut_sosmed_instagram=  Login_Influencer.pengikut_sosmed_instagram;
               sosmed_facebook=  Login_Influencer.sosmed_facebook;
               pengikut_sosmed_facebook = Login_Influencer.pengikut_sosmed_facebook;
               foto_profil = Login_Influencer.foto_profil;

               String urlgambar = new Configurasi().baseUrl()+"influencer/gambar/"+ foto_profil;

               Glide.with(getContext())
                       .load(urlgambar)
                       .diskCacheStrategy(DiskCacheStrategy.NONE)
                       .skipMemoryCache(true)
                       .into(ivLogo_Influencer);

               tvNama_Influencer.setText(nama_influencer);
               tvUsername.setText(username);
               tvPassword.setText(password);
               tvEmail.setText(email);
               tvNomor_Telepon.setText(nomor_telepon);
               tvSosmed_Tiktok.setText(sosmed_tiktok);
               tvPengikut_Sosmed_Tiktok.setText("Follower : "+pengikut_sosmed_tiktok);
               tvSosmed_Instagram.setText(sosmed_instagram);
               tvPengikut_Sosmed_Instagram.setText("Follower : "+pengikut_sosmed_instagram);
               tvSosmed_Facebook.setText(sosmed_facebook);
               tvPengikut_Sosmed_Facebook.setText("Follower : "+pengikut_sosmed_facebook);

               btnEdit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.konten, new Fragment_Edit_Profil_Influencer());
                       transaction.addToBackStack(null);
                       transaction.commit();
                   }
               });

               btnRiwayat.setOnClickListener(new View.OnClickListener() {
                   @SuppressLint("ResourceType")
                   @Override
                   public void onClick(View view) {
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.konten, new Fragment_Riwayat_Transaksi_Influencer());
                       transaction.addToBackStack(null);
                       transaction.commit();
                   }
               });

               btnAbout.setOnClickListener(new View.OnClickListener() {
                   @SuppressLint("ResourceType")
                   @Override
                   public void onClick(View view) {
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.konten, new Fragment_About_Influencer());
                       transaction.addToBackStack(null);
                       transaction.commit();
                   }
               });



               if(Fragment_Edit_Profil_Influencer.status_edit_profil == true){
                   nama_influencer = Fragment_Edit_Profil_Influencer.nama_influencer;
                   username = Fragment_Edit_Profil_Influencer.username;
                   password = Fragment_Edit_Profil_Influencer.password;
                   email = Fragment_Edit_Profil_Influencer.email;
                   nomor_telepon = Fragment_Edit_Profil_Influencer.nomor_telepon;

                   sosmed_tiktok=  Fragment_Edit_Profil_Influencer.sosmed_tiktok;
                   pengikut_sosmed_tiktok=  Fragment_Edit_Profil_Influencer.pengikut_sosmed_tiktok;
                   sosmed_instagram=  Fragment_Edit_Profil_Influencer.sosmed_instagram;
                   pengikut_sosmed_instagram=  Fragment_Edit_Profil_Influencer.pengikut_sosmed_instagram;
                   sosmed_facebook=  Fragment_Edit_Profil_Influencer.sosmed_facebook;
                   pengikut_sosmed_facebook = Fragment_Edit_Profil_Influencer.pengikut_sosmed_facebook;

                   String urlgambar2 = new Configurasi().baseUrl()+"influencer/gambar/"+ foto_profil;

                   Glide.with(getContext())
                           .load(urlgambar2)
                           .diskCacheStrategy(DiskCacheStrategy.NONE)
                           .skipMemoryCache(true)
                           .into(ivLogo_Influencer);
                   tvNama_Influencer.setText( nama_influencer);
                   tvUsername.setText( username);
                   tvPassword.setText( password);
                   tvEmail.setText( email);
                   tvNomor_Telepon.setText( nomor_telepon);
                   tvSosmed_Tiktok.setText( sosmed_tiktok);
                   tvPengikut_Sosmed_Tiktok.setText("Follower : "+ pengikut_sosmed_tiktok);
                   tvSosmed_Instagram.setText( sosmed_instagram);
                   tvPengikut_Sosmed_Instagram.setText("Follower : "+ pengikut_sosmed_instagram);
                   tvSosmed_Facebook.setText( sosmed_facebook);
                   tvPengikut_Sosmed_Facebook.setText("Follower : "+ pengikut_sosmed_facebook);
               }


           }

        return view;
    }


}
