<?php

include '../../koneksidb.php';
  
            $id = $_POST['id'];
            $foto_profil   = $_POST['foto_profil'];
            $nama_influencer = $_POST['nama_influencer'];
            $username = $_POST['username'];
            $password = $_POST['password'];
            $email = $_POST['email'];
            $nomor_telepon = $_POST['nomor_telepon'];
            $sosmed_tiktok = $_POST['sosmed_tiktok'];
          $pengikut_sosmed_tiktok = $_POST['pengikut_sosmed_tiktok'];
          $sosmed_instagram = $_POST['sosmed_instagram'];
          $pengikut_sosmed_instagram = $_POST['pengikut_sosmed_instagram'];
          $sosmed_facebook = $_POST['sosmed_facebook'];
          $pengikut_sosmed_facebook = $_POST['pengikut_sosmed_facebook'];
            
        
            if($foto_profil != ""){
                $target_path = "../gambar";
                $imageStore = rand()."_".time().".jpeg";
                $target_path = $target_path."/".$imageStore;
                file_put_contents($target_path, base64_decode($foto_profil));          

              $query = "UPDATE tabel_akun_influencer SET foto_profil='$imageStore', nama_influencer='$nama_influencer', username='$username', password='$password', email='$email', nomor_telepon='$nomor_telepon',
               sosmed_tiktok='$sosmed_tiktok', pengikut_sosmed_tiktok='$pengikut_sosmed_tiktok', sosmed_instagram='$sosmed_instagram', pengikut_sosmed_instagram='$pengikut_sosmed_instagram', 
                sosmed_facebook='$sosmed_facebook', pengikut_sosmed_facebook='$pengikut_sosmed_facebook' WHERE id_influencer='$id'";
              $result = mysqli_query($db, $query);

                if($result){
                    echo json_encode(array(
                'status' => 'berhasil_tersimpan'
            ));
                } else{
                    echo json_encode(array(
                    'status' => 'gagal_tersimpan'
                ));
                }
        
     } else{
        $query = "UPDATE tabel_akun_influencer SET nama_influencer='$nama_influencer', username='$username', password='$password', email='$email', nomor_telepon='$nomor_telepon',
               sosmed_tiktok='$sosmed_tiktok', pengikut_sosmed_tiktok='$pengikut_sosmed_tiktok', sosmed_instagram='$sosmed_instagram', pengikut_sosmed_instagram='$pengikut_sosmed_instagram', 
                sosmed_facebook='$sosmed_facebook', pengikut_sosmed_facebook='$pengikut_sosmed_facebook' WHERE id_influencer='$id'";
        $result = mysqli_query($db, $query);        

            if($result){
                echo json_encode(array(
            'status' => 'berhasil_tersimpan'
        ));
            } else{
                echo json_encode(array(
                'status' => 'gagal_tersimpan'
            ));
            }
            }
  ?>

