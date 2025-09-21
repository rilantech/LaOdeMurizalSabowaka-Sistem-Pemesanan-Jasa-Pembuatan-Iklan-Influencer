<?php

  include '../../koneksidb.php';
 
  $sql = "SELECT * FROM tabel_akun_influencer WHERE nama_influencer LIKE '%".$_POST['cari_influencer']."%' ORDER by id DESC";
  $query = mysqli_query($db, $sql);

    $list_data = array();
    while($data = mysqli_fetch_assoc($query)){
        $list_data[] = array(
            'id' => $data['id'],
             'nama_influencer'=> $data['nama_influencer'],
            'foto_profil'=> $data['foto_profil'],
            'email'=> $data['email'],
            'nomor_telepon'=> $data['nomor_telepon'],
            'sosmed_tiktok'=> $data['sosmed_tiktok'],
            'pengikut_sosmed_tiktok'=> $data['pengikut_sosmed_tiktok'],
            'sosmed_instagram'=> $data['sosmed_instagram'],
            'pengikut_sosmed_instagram'=> $data['pengikut_sosmed_instagram'],
            'sosmed_facebook'=> $data['sosmed_facebook'],
            'pengikut_sosmed_facebook'=> $data['pengikut_sosmed_facebook']
        );
    }

    echo json_encode(array(
        'data' => $list_data
    ));
  
  ?>