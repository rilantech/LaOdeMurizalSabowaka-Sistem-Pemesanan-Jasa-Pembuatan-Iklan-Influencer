<?php

  include '../../koneksidb.php';

  $sql = "SELECT * FROM tabel_project WHERE nama_brand='".$_POST['nama_brand']."' AND id= '" .$_POST['id']."'";

  $query = mysqli_query($db, $sql);
 
  $data = mysqli_fetch_assoc($query);
  
  if (!is_null($data)) {
      // Throw some kind of exception
      echo json_encode(array(
            'data' => array(
            'id' => $data['id'],
            'waktu_posting'=> $data['waktu_posting'],
            'logo_brand'=> $data['logo_brand'],
            'nama_brand'=> $data['nama_brand'],
            'nama_project'=> $data['nama_project'],
            'kategori'=> $data['kategori'],
            'kriteria_project'=> $data['kriteria_project'],
            'gaji_influencer'=> $data['gaji_influencer']
            )
      ));
 } else{
      echo "datakosong";
 }
  ?>