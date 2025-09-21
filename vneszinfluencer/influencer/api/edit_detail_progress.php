<?php

  include '../../koneksidb.php';

  $id   = $_POST['id'];
  $link_draft   = $_POST['link_draft'];
  $link_bukti_post   = $_POST['link_bukti_post'];
  $nomor_rekening = $_POST['nomor_rekening'];

      $select = "UPDATE tabel_progress_project SET link_draft= '$link_draft', link_bukti_post= '$link_bukti_post', nomor_rekening='$nomor_rekening'
                WHERE id_progress='$id'"; 
               
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
    

  ?>