<?php

  include '../../koneksidb.php';

  $sql = "SELECT * FROM tabel_notifikasi where nama_brand='".$_POST['nama_brand']."' AND jenis_notif='notif_brand' ORDER by waktu_notif DESC";
  $query = mysqli_query($db, $sql);

    $list_data = array();
    while($data = mysqli_fetch_assoc($query)){
        $list_data[] = array(
            'id' => $data['id_notifikasi'],
            'id_project'=> $data['id_project'],
            'id_progress'=> $data['id_progress'],
            'jenis_notif'=> $data['jenis_notif'],
            'waktu_notif'=> $data['waktu_notif'],
            'nama_brand'=> $data['nama_brand'],
            'nama_influencer'=> $data['nama_influencer'],
            'isi_notif'=> $data['isi_notif']
        );
    }

    echo json_encode(array(
        'data' => $list_data
    ));
  
  ?>