package com.example.aplikasivannes;

import static android.app.Activity.RESULT_OK;

import static com.example.aplikasivannes.R.drawable.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectBrand;
import com.example.aplikasivannes.AdapterProjectBrand.GetProjectBrand;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Tambah_Project_Brand extends Fragment {
    ImageView tombol_kembali, ivLogo_Brand;
    EditText etNama_Project, etKriteria_Project, etGaji_Influencer;
    Spinner spinner_Kategori, spinner_lama_kontrak;
    Button btnbatal, btnsimpan;
    Bitmap bitmap;
    String encodeImage;
    int MY_REQUEST_CODE= 1;
    int nomor = 1;
    String previousText = "";
    int lastIndex= 0;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_project_brand, container, false);
       tombol_kembali = view.findViewById(R.id.tombol_kembali);
       ivLogo_Brand = view.findViewById(R.id.ivLogo_Brand);
       etNama_Project = view.findViewById(R.id.etNama_Project);
       etKriteria_Project = view.findViewById(R.id.etKriteria_Project);
       etGaji_Influencer = view.findViewById(R.id.etGaji_Influencer);
       spinner_Kategori = view.findViewById(R.id.spinner_Kategori);
       spinner_lama_kontrak = view.findViewById(R.id.spinner_lama_kontrak);
       btnbatal = view.findViewById(R.id.btnbatal);
       btnsimpan = view.findViewById(R.id.btnsimpan);

        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Project_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        etKriteria_Project.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if (charSequence.toString().endsWith("\n")) {
                    lastIndex = text.lastIndexOf("\n");
                    if (lastIndex != -1) {
                        String lastLine = text.substring(lastIndex + 1);
                        if (!lastLine.startsWith(nomor + ". ")) {
                            etKriteria_Project.setText(text.substring(0, lastIndex + 1) + nomor + ". ");
                            etKriteria_Project.setSelection(etKriteria_Project.getText().length());
                            nomor++;
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });



        ivLogo_Brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity((Activity) getContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent= new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), MY_REQUEST_CODE);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNama_Project.getText().toString().length() == 0) {
                    etNama_Project.setError("Tidak Boleh Kosong");
                }
                if (etKriteria_Project.getText().toString().length() == 0) {
                    etKriteria_Project.setError("Tidak Boleh Kosong");
                }
//                if (etGaji_Influencer.getText().toString().length() == 0) {
//                    etGaji_Influencer.setError("Tidak Boleh Kosong");
//                }

                else {
                    String url = new Configurasi().baseUrl()+"brand/api/tambah_project.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status =jsonObject.getString("status");
                                if(status.equals("berhasil_tersimpan"))
                                {
                                    // Toast.makeText(HalamanRegistrasi.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Sukses");
                                    builder.setMessage("Project Tersimpan");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.konten, new Fragment_Project_Brand());
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                        }
                                    });

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Anda Sedang Offline!", Toast.LENGTH_LONG).show();
                        }
                    }){

                        @androidx.annotation.Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> myParams = new HashMap<>();
                            myParams.put("logo_brand", encodeImage);
                            myParams.put("nama_brand", Login_Brand.nama_brand);
                            myParams.put("nama_project", etNama_Project.getText().toString());
                            myParams.put("kategori", spinner_Kategori.getSelectedItem().toString());
                            myParams.put("lama_kontrak", spinner_lama_kontrak.getSelectedItem().toString());
                            myParams.put("kriteria_project", etKriteria_Project.getText().toString());
                            myParams.put("gaji_influencer", "0");
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                }

            }

        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLogo_Brand.setImageResource(R.drawable.ic_baseline_file_upload_24);
                etNama_Project.setText("");
                spinner_Kategori.setSelection(0);
                spinner_lama_kontrak.setSelection(0);
                etKriteria_Project.setText("");
                etGaji_Influencer.setText("");
                nomor = 1;
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        if(requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = requireContext().getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                ivLogo_Brand.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageByte = stream.toByteArray();
        encodeImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
    }


}
