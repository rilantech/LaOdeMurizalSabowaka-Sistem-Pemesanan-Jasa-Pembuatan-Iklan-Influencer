<?php

  include '../../koneksidb.php';
  
  $select = "DELETE FROM tabel_project WHERE id='".$_POST['id']."'"; 
               
      $result = mysqli_query($db, $select);

            if($result){
              echo json_encode(array(
            'status' => 'berhasil_terhapus'
        ));
            } else{
              echo json_encode(array(
                'status' => 'gagal_terhapus'
            ));
          
    }

  ?>