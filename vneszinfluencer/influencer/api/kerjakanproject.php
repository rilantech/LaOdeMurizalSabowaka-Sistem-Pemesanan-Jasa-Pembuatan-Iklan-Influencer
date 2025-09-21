<?php
// session_start();
// $_SESSION['notifikasi'] +=1;
// setcookie('id_ku', session_id(), time() + 3600);

include '../../koneksidb.php';
  date_default_timezone_set('Asia/Makassar');
  $time = date('d-M-Y H:i:s');


  if(isset($_POST['id'])){
     
    $result_project = mysqli_query($db, "SELECT * FROM tabel_project where id_project = '".$_POST['id']."'");
    $data_project = mysqli_fetch_array($result_project);

    $query = "INSERT INTO tabel_progress_project (waktu_pengajuan, waktu_posting, logo_brand, nama_brand, nama_project, kategori, lama_kontrak, kriteria_project, gaji_influencer, nama_influencer, status_project) VALUES ('$time', '".$data_project['waktu_posting']."', '".$data_project['logo_brand']."', '".$data_project['nama_brand']."', '".$data_project['nama_project']."', '".$data_project['kategori']."',
     '".$data_project['lama_kontrak']."','".$data_project['kriteria_project']."', '".$data_project['gaji_influencer']."', '".$_POST['nama_influencer']."', 'Sedang Dikerjakan')";
              $result = mysqli_query($db, $query);

    $query =  "DELETE FROM tabel_project WHERE id_project='".$data_project['id_project']."'";
              $result = mysqli_query($db, $query);

    $result5 = mysqli_query($db, "SELECT * FROM tabel_progress_project where waktu_pengajuan = '$time'");
    $dataprogress = mysqli_fetch_array($result5);
    $isi_notif= "Hallo ".$dataprogress['nama_brand'].", influencer atas nama ".$dataprogress['nama_influencer'].
                " mengajukan pengerjaan project ".$dataprogress['nama_project'].".";

    $query2 = "INSERT INTO tabel_notifikasi (id_progress, jenis_notif, waktu_notif, nama_brand, nama_influencer, isi_notif) VALUES ('".$dataprogress['id_progress']."','notif_brand', '".$dataprogress['waktu_pengajuan']."', '".$dataprogress['nama_brand']."',  '".$dataprogress['nama_influencer']."','$isi_notif')";
             $result2 = mysqli_query($db, $query2);
              // periska query apakah ada error
              if($result2){
             
                echo json_encode(array(
              'status' => 'berhasil_tersimpan'
          ));
              } else{
                echo json_encode(array(
                  'status' => 'gagal_tersimpan'
              ));
              }

  }

  
