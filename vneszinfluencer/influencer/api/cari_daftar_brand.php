<?php

  include '../../koneksidb.php';
 
  $sql = "SELECT * FROM tabel_akun_brand WHERE nama_brand LIKE '%".$_POST['cari_brand']."%' ORDER by id_brand DESC";
  $query = mysqli_query($db, $sql);

    $list_data = array();
    while($data = mysqli_fetch_assoc($query)){
        $list_data[] = array(
            'id' => $data['id_brand'],
            'nama_brand'=> $data['nama_brand'],
            'foto_profil'=> $data['foto_profil'],
            'email'=> $data['email'],
            'nomor_telepon'=> $data['nomor_telepon']
        );
    }

    echo json_encode(array(
        'data' => $list_data
    ));
  
  ?>