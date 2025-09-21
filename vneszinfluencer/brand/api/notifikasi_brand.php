<?php

  include '../../koneksidb.php';
  date_default_timezone_set('Asia/Makassar');
  $time = date('d-M-Y H:i:s');

  $sql = "SELECT * FROM tabel_notifikasi where nama_brand='".$_POST['nama_brand']."' AND jenis_notif='notif_brand' ORDER by waktu_notif DESC LIMIT 1";
  $query = mysqli_query($db, $sql);
  $list_data = array();
  if(mysqli_num_rows($query) > 0){
      $data = mysqli_fetch_assoc($query);
      $isi_notif = $data['isi_notif'];
      $id   =  $data['id_notifikasi'];
      $id_project = $data['id_project'];
      $id_progress = $data['id_progress'];
      $jenis_notif = $data['jenis_notif'];
      $waktu_notif = $data['waktu_notif'];
      $nama_brand = $data['nama_brand'];
      $nama_influencer = $data['nama_influencer'];
      
      if($data['waktu_notif'] == $time){
          echo json_encode(array(
            'jenis_notif' => 'notif_brand',
            // 'id' => $id,
            // 'id_project'=> $id_project,
            // 'id_progress'=> $id_progress,
            // 'jenis_notif'=> $jenis_notif,
            // 'waktu_notif'=> $waktu_notif,
            // 'nama_brand'=> $nama_brand,
            // 'nama_influencer'=> $nama_influencer,
            // 'isi_notif'=> $isi_notif
        ));
      }
       
        
        
  }



  
  ?>