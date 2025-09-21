<?php

  include '../../koneksidb.php';
  date_default_timezone_set('Asia/Makassar');

  $waktu_posting = date('d-M-Y H:i:s');
  $logo_brand   = $_POST['logo_brand'];
  $nama_brand   = $_POST['nama_brand'];
  $nama_project = $_POST['nama_project'];
  $kategori = $_POST['kategori'];
  $lama_kontrak = $_POST['lama_kontrak'];
  $kriteria_project = $_POST['kriteria_project'];
  $gaji_influencer = $_POST['gaji_influencer'];

    if(isset($_POST['nama_brand'])){

      $target_path = "../gambar";
      $imageStore = rand()."_".time().".jpeg";
      $target_path = $target_path."/".$imageStore;
      file_put_contents($target_path, base64_decode($logo_brand));

      $select = "INSERT INTO tabel_project (waktu_posting, logo_brand, nama_brand, nama_project, kategori, lama_kontrak, kriteria_project, gaji_influencer) 
                VALUES ('$waktu_posting', '$imageStore', '$nama_brand', '$nama_project' , '$kategori', '$lama_kontrak', '$kriteria_project', '$gaji_influencer')";
      $result = mysqli_query($db, $select);

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
      echo json_encode(array(
        'status' => 'gagal_tersimpan'
    ));
    }

  ?>