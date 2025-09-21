<?php

  include '../../koneksidb.php';

  $sql = "SELECT * FROM tabel_project WHERE nama_project LIKE '%".$_POST['cari_project']."%' AND nama_brand='".$_POST['nama_brand']."' order by id_project desc";
  $query = mysqli_query($db, $sql);

    $list_data = array();
    while($data = mysqli_fetch_assoc($query)){
        $list_data[] = array(
            'id' => $data['id_project'],
            'waktu_posting'=> $data['waktu_posting'],
            'logo_brand'=> $data['logo_brand'],
            'nama_brand'=> $data['nama_brand'],
            'nama_project'=> $data['nama_project'],
            'kategori'=> $data['kategori'],
            'lama_kontrak'=> $data['lama_kontrak'],
            'kriteria_project'=> $data['kriteria_project'],
            'gaji_influencer'=> $data['gaji_influencer'],
        );
    }

    echo json_encode(array(
        'data' => $list_data
    ));
  
  ?>