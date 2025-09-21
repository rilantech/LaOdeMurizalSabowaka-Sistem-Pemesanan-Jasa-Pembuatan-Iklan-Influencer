<?php

  include '../../koneksidb.php';
 
  $result3 = mysqli_query($db, "SELECT SUM(gaji_influencer) AS jumlah_pendapatan FROM tabel_progress_project where nama_influencer='".$_POST['nama_influencer']."' AND status_project='Dibayar' ORDER by waktu_pembayaran DESC");
  $row = mysqli_fetch_array($result3);
  $jumlah_pendapatan = $row['jumlah_pendapatan'];

    echo json_encode(array(
        'jumlah_pendapatan' => 'Rp '.number_format($jumlah_pendapatan, 0, ',', '.'),
          
     ));


  ?>