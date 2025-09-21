<?php
session_start();
  include '../../koneksidb.php';
  date_default_timezone_set('Asia/Makassar');
  $time = date('d-M-Y H:i:s');


  $id   = $_POST['id'];
  $status_project   = $_POST['status_project'];
  $revisi   = $_POST['revisi'];
  $struk_bukti_pembayaran = $_POST['struk_bukti_pembayaran'];

    if($struk_bukti_pembayaran != ""){
      $target_path = "../gambar";
      $imageStore = rand()."_".time().".jpeg";
      $target_path = $target_path."/".$imageStore;
      file_put_contents($target_path, base64_decode($struk_bukti_pembayaran));

      $select = "UPDATE tabel_progress_project SET waktu_pengeditan='$time', status_project= 'Dibayar', revisi= '$revisi', struk_bukti_pembayaran='$imageStore'
                WHERE id_progress='$id'"; 
               
      $result = mysqli_query($db, $select);

      // $result5 = mysqli_query($db, "SELECT * FROM tabel_progress_project where waktu_pengeditan = '$time'");
      //           $dataprogress = mysqli_fetch_array($result5);
      //           $isi_notif= "Hallo ".$dataprogress['nama_influencer'].", brand ".$dataprogress['nama_brand'].
      //                     " telah mengubah pengerjaan project ".$dataprogress['nama_project']." menjadi ".$dataprogress['status_project'];
          
      //           $select = "INSERT INTO tabel_notifikasi (id_progress, jenis_notif, waktu_notif, nama_brand, nama_influencer, isi_notif) VALUES ('".$dataprogress['id']."','notif_influencer','".$dataprogress['waktu_pengeditan']."', '".$dataprogress['nama_brand']."',  '".$dataprogress['nama_influencer']."','$isi_notif')";
      //           $result = mysqli_query($db, $select);
              
            if($result){
            
              echo json_encode(array(
            'status' => 'berhasil_tersimpan'
        ));
            } else{
              echo json_encode(array(
                'status' => 'gagal_tersimpan'
            ));
          }
    }else{
      $select = "UPDATE tabel_progress_project SET waktu_pengeditan='$time',status_project= '$status_project', revisi= '$revisi'
                WHERE id_progress='$id'"; 
     
      $result = mysqli_query($db, $select);

      // $result5 = mysqli_query($db, "SELECT * FROM tabel_progress_project where waktu_pengeditan = '$time'");
      //           $dataprogress = mysqli_fetch_array($result5);
      //           $isi_notif= "Hallo ".$dataprogress['nama_influencer'].", brand ".$dataprogress['nama_brand'].
      //                     " telah mengubah pengerjaan project ".$dataprogress['nama_project']." menjadi ".$dataprogress['status_project'];
          
      //           $select = "INSERT INTO tabel_notifikasi (id_progress, jenis_notif, waktu_notif, nama_brand, nama_influencer, isi_notif) VALUES ('".$dataprogress['id']."','notif_influencer','".$dataprogress['waktu_pengeditan']."', '".$dataprogress['nama_brand']."',  '".$dataprogress['nama_influencer']."','$isi_notif')";
      //           $result = mysqli_query($db, $select);
               
        if($result){
          // $_SESSION['notifikasi_influencer'] += 1;
          echo json_encode(array(
        'status' => 'berhasil_tersimpan'
    ));
        } else{
          echo json_encode(array(
            'status' => 'gagal_tersimpan'
        ));
        }

    }

  ?>