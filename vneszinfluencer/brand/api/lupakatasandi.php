<?php

  include '../../koneksidb.php';

  $email = $_POST['email'];
 
    if(isset($_POST['email'])){
        $sql = "SELECT * FROM tabel_akun_brand WHERE email = '$email'"; 
        $query = mysqli_query($db, $sql);

        $data = mysqli_fetch_assoc($query);

        $rows = mysqli_num_rows($query);
            if($rows==1){
                // $_SESSION['username'] = $username;
                echo json_encode(array(
                    'status' => 'berhasil',
                        'id' => $data['id_brand'],
                        'nama_brand' => $data['nama_brand'],
                        'username' => $data['username'],
                        'password' => $data['password'],
                        'foto_profil' => $data['foto_profil'],
                        'email' => $data['email'],
                        'nomor_telepon' => $data['nomor_telepon'],
                        )
                    );

            }else{
       echo json_encode(array(
         'status' => 'gagal'
     ));
    }
    }

  ?>