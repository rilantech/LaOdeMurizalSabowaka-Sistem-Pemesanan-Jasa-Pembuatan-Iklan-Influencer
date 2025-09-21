<?php

  include '../../koneksidb.php';

  $username = $_POST['username'];
  $password = $_POST['password'];

    if(isset($_POST['username'])){
        $sql = "SELECT * FROM tabel_akun_influencer WHERE username = '$username' and password ='$password'"; 
        $query = mysqli_query($db, $sql);

        $data = mysqli_fetch_assoc($query);

        $rows = mysqli_num_rows($query);
            if($rows==1){
                $_SESSION['username'] = $username;
                echo json_encode(array(
                    'status' => 'berhasil_login',
                        'id' => $data['id_influencer'],
                        'nama_influencer' => $data['nama_influencer'],
                        'username' => $data['username'],
                        'password' => $data['password'],
                        'foto_profil' => $data['foto_profil'],
                        'email' => $data['email'],
                        'nomor_telepon' => $data['nomor_telepon'],
                        'sosmed_tiktok'=> $data['sosmed_tiktok'],
                        'pengikut_sosmed_tiktok'=> $data['pengikut_sosmed_tiktok'],
                        'sosmed_instagram'=> $data['sosmed_instagram'],
                        'pengikut_sosmed_instagram'=> $data['pengikut_sosmed_instagram'],
                        'sosmed_facebook'=> $data['sosmed_facebook'],
                        'pengikut_sosmed_facebook'=> $data['pengikut_sosmed_facebook']
                        )
                    );

            }else{
       echo json_encode(array(
         'status' => 'gagal_login'
     ));
    }
    }

  ?>