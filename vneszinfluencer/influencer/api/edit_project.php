<?php

  include '../../koneksidb.php';

  $id   = $_POST['id'];
  $logo_brand   = $_POST['logo_brand'];
  $nama_project = $_POST['nama_project'];
  $kategori = $_POST['kategori'];
  $kriteria_project = $_POST['kriteria_project'];
  $gaji_influencer = $_POST['gaji_influencer'];

    if($logo_brand != ""){
      $target_path = "../gambar";
      $imageStore = rand()."_".time().".jpeg";
      $target_path = $target_path."/".$imageStore;
      file_put_contents($target_path, base64_decode($logo_brand));

      $select = "UPDATE tabel_project SET logo_brand='$imageStore', nama_project ='$nama_project', kategori ='$kategori', kriteria_project = '$kriteria_project', gaji_influencer= '$gaji_influencer'
                WHERE id='$id'"; 
               
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
      $select = "UPDATE tabel_project SET nama_project ='$nama_project', kategori ='$kategori', kriteria_project = '$kriteria_project', gaji_influencer= '$gaji_influencer'
      WHERE id='$id'"; 
     
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

    }

  ?>