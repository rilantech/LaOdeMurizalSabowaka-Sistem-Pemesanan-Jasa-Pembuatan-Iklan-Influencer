<?php

  include '../../koneksidb.php';

  $username = $_POST['username'];
  $password = $_POST['password'];

    if(isset($_POST['username'])){
        $sql = "SELECT * FROM tabel_akun_brand WHERE username = '$username' and password ='$password'"; 
        $query = mysqli_query($db, $sql);

        $data = mysqli_fetch_assoc($query);

        $rows = mysqli_num_rows($query);
            if($rows==1){
                $_SESSION['username'] = $username;
                echo json_encode(array(
                    'status' => 'berhasil_login',
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
         'status' => 'gagal_login'
     ));
    }
    }

  ?>