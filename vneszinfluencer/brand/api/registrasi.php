<?php

  include '../../koneksidb.php';

  $nama_brand =$_POST['nama_brand'];
  $username = $_POST['username'];
  $password = $_POST['password'];
  $foto_profil = $_POST['foto_profil'];
  $email = $_POST['email'];
  $nomor_telepon = $_POST['nomor_telepon'];
  
    if(isset($_POST['nama_brand'])){

      $target_path = "../gambar";
      $imageStore = rand()."_".time().".jpeg";
      $target_path = $target_path."/".$imageStore;
      file_put_contents($target_path, base64_decode($foto_profil));

      $select = "INSERT INTO tabel_akun_brand (nama_brand, username, password, foto_profil, email, nomor_telepon) 
                VALUES ('$nama_brand', '$username', '$password', '$imageStore' , '$email', '$nomor_telepon')";
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