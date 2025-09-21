<?php

  include '../../koneksidb.php';

  $sql = "SELECT * FROM tabel_progress_project WHERE id_progress= '" .$_POST['id']."'";

  $query = mysqli_query($db, $sql);
 
  $data = mysqli_fetch_assoc($query);
  
  if (!is_null($data)) {
      // Throw some kind of exception
      echo json_encode(array(
            'data' => array(
            'id' => $data['id_progress'],
            'waktu_pengajuan'=> $data['waktu_pengajuan'],
            'waktu_posting'=> $data['waktu_posting'],
            'waktu_pembayaran'=> $data['waktu_pembayaran'],
            'logo_brand'=> $data['logo_brand'],
            'nama_brand'=> $data['nama_brand'],
            'nama_project'=> $data['nama_project'],
            'kategori'=> $data['kategori'],
            'kriteria_project'=> $data['kriteria_project'],
            'gaji_influencer'=> 'Rp '.number_format($data['gaji_influencer'], 0, ',', '.'),
            'nama_influencer'=> $data['nama_influencer'],
            'link_draft'=> $data['link_draft'],
            'link_bukti_post'=> $data['link_bukti_post'],
            'revisi'=> $data['revisi'],
            'status_project'=> $data['status_project'],
            'nomor_rekening'=> $data['nomor_rekening'],
            'struk_bukti_pembayaran'=> $data['struk_bukti_pembayaran']
            )
      ));
 } else{
      echo "datakosong";
 }
  ?>