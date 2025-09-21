<?php

include '../../koneksidb.php';
  
            $id = $_POST['id'];
            $foto_profil   = $_POST['foto_profil'];
            $nama_brand = $_POST['nama_brand'];
            $username = $_POST['username'];
            $password = $_POST['password'];
            $email = $_POST['email'];
            $nomor_telepon = $_POST['nomor_telepon'];
            
        
            if($foto_profil != ""){
                $target_path = "../gambar";
                $imageStore = rand()."_".time().".jpeg";
                $target_path = $target_path."/".$imageStore;
                file_put_contents($target_path, base64_decode($foto_profil));          

              $query = "UPDATE tabel_akun_brand SET foto_profil='$imageStore', nama_brand='$nama_brand', username='$username', password='$password', email='$email', nomor_telepon='$nomor_telepon' WHERE id_brand='$id'";
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
        $query = "UPDATE tabel_akun_brand SET nama_brand='$nama_brand', username='$username', password='$password', email='$email', nomor_telepon='$nomor_telepon' WHERE id_brand='$id'";
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

