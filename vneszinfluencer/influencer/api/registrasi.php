<?php

  include '../../koneksidb.php';

  $nama_influencer =$_POST['nama_influencer'];
  $username = $_POST['username'];
  $password = $_POST['password'];
  $foto_profil = $_POST['foto_profil'];
  $email = $_POST['email'];
  $nomor_telepon = $_POST['nomor_telepon'];
  $sosmed_tiktok = $_POST['sosmed_tiktok'];
  $pengikut_sosmed_tiktok = $_POST['pengikut_sosmed_tiktok'];
  $sosmed_instagram = $_POST['sosmed_instagram'];
  $pengikut_sosmed_instagram = $_POST['pengikut_sosmed_instagram'];
  $sosmed_facebook = $_POST['sosmed_facebook'];
  $pengikut_sosmed_facebook = $_POST['pengikut_sosmed_facebook'];
  
    if(isset($_POST['nama_influencer'])){

      $target_path = "../gambar";
      $imageStore = rand()."_".time().".jpeg";
      $target_path = $target_path."/".$imageStore;
      file_put_contents($target_path, base64_decode($foto_profil));

      $select = "INSERT INTO tabel_akun_influencer (nama_influencer, username, password, foto_profil, email, nomor_telepon, sosmed_tiktok, pengikut_sosmed_tiktok, 
                sosmed_instagram, pengikut_sosmed_instagram, sosmed_facebook, pengikut_sosmed_facebook) 
                VALUES ('$nama_influencer', '$username', '$password', '$imageStore' , '$email', '$nomor_telepon', '$sosmed_tiktok', '$pengikut_sosmed_tiktok', '$sosmed_instagram', '$pengikut_sosmed_instagram', '$sosmed_facebook', '$pengikut_sosmed_facebook')";
      $result = mysqli_query($db, $select);

            if($result){
              echo json_encode(array(
            'status' => 'berhasil_registrasi'
        ));
            } else{
              echo json_encode(array(
                'status' => 'registrasi_gagal'
            ));
            }
      
    }else{
      echo json_encode(array(
        'status' => 'gagal_registrasi'
    ));
    }

  ?>