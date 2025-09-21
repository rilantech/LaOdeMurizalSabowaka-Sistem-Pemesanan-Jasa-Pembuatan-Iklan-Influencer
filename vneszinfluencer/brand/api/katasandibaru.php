<?php

  include '../../koneksidb.php';

   $password = $_POST['password'];
   $email = $_POST['email'];
   
   if(isset($_POST['password'])){
        $query = "UPDATE tabel_akun_brand SET password='$password' WHERE email='$email'";
        $result = mysqli_query($db, $query);        

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