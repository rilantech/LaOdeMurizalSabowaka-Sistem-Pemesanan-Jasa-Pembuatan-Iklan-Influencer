<?php
  session_start();
  include '../koneksidb.php';

  if(isset($_POST['Submit'])){
    $nama_influencer = $_POST['nama_influencer'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $foto_profil   = $_FILES['foto_profil']['name'];
    $file_tmp = $_FILES['foto_profil']['tmp_name'];
    $email = $_POST['email'];
    $nomor_telepon = $_POST['nomor_telepon'];
    $sosmed_tiktok = $_POST['sosmed_tiktok'];
    $pengikut_sosmed_tiktok = $_POST['pengikut_sosmed_tiktok'];
    $sosmed_instagram = $_POST['sosmed_instagram'];
    $pengikut_sosmed_instagram = $_POST['pengikut_sosmed_instagram'];
    $sosmed_facebook = $_POST['sosmed_facebook'];
    $pengikut_sosmed_facebook = $_POST['pengikut_sosmed_facebook'];

  if ($foto_profil!= "") {
    $ekstensi_diperbolehkan = array('png', 'jpg'); //ekstensi file gambar yang bisa diupload 
    $x = explode('.', $foto_profil); //memisahkan nama_influencer file dengan ekstensi yang diupload
    $ekstensi = strtolower(end($x));
    $foto_profil= rand(1, 999).".".$ekstensi;
    if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
      move_uploaded_file($file_tmp, 'gambar/' . $foto_profil); //memindah file gambar ke folder gambar
      // jalankan query INSERT untuk menambah data ke database pastikan sesuai urutan (id tidak perlu karena dibikin otomatis)
      $query = "INSERT INTO tabel_akun_influencer (nama_influencer, username, password, foto_profil, email, nomor_telepon, sosmed_tiktok, pengikut_sosmed_tiktok, 
                sosmed_instagram, pengikut_sosmed_instagram, sosmed_facebook, pengikut_sosmed_facebook) VALUES ('$nama_influencer', '$username', '$password', '$foto_profil', '$email', '$nomor_telepon',
                '$sosmed_tiktok', '$pengikut_sosmed_tiktok', '$sosmed_instagram', '$pengikut_sosmed_instagram', '$sosmed_facebook', '$pengikut_sosmed_facebook')";
      $result = mysqli_query($db, $query);
      // periska query apakah ada error
      if (!$result) {
        die("Query gagal dijalankan: " . mysqli_errno($db) .
          " - " . mysqli_error($db));
      } else {
        //tampil alert dan akan redirect ke halaman index.php
        //silahkan ganti index.php sesuai halaman yang akan dituju
        $_SESSION['status_registrasi'] = 'berhasil';
        echo "<script>window.location='login.php';</script>";
      }
    } else {
      //jika file ekstensi tidak jpg dan png maka alert ini yang tampil
      echo "<script>alert('Ekstensi gambar yang boleh hanya jpg atau png.');window.location='registrasi.php';</script>";
    }
  } else{

      $select = "INSERT INTO tabel_akun_influencer (nama_influencer, username, password, email, nomor_telepon, sosmed_tiktok, pengikut_sosmed_tiktok, 
                sosmed_instagram, pengikut_sosmed_instagram, sosmed_facebook, pengikut_sosmed_facebook) VALUES ('$nama_influencer', '$username', '$password', '$email', '$nomor_telepon',
                '$sosmed_tiktok', '$pengikut_sosmed_tiktok', '$sosmed_instagram', '$pengikut_sosmed_instagram', '$sosmed_facebook', '$pengikut_sosmed_facebook')";
      $result = mysqli_query($db, $select);

            if($result){
                echo "<script>alert('Berhasil Registrasi!');window.location='login.php';</script>";

            } else{
                echo "<script>alert('Gagal Registrasi!');window.location='registrasi.php';</script>";

            }
      
    }
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZInfluencer|Registrasi</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="../img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
</head>

<body style="background-color:#009688;">
   
    <div class="container-fluid mb-5">
        <div class="row border-top px-0">
            <div class="col-lg-12 px-0">
            <div class="container">

<!-- Outer Row -->
<div class="row justify-content-center">
    <div class="col-xl-6 col-lg-4 col-md-9">
    <div class="text-center">
            <h1 class="h4 text-900 mb-1 mt-3" style="color: white; font-weight:bold;">VnesZInfluencer</h1>
    </div>
    <div class="text-center">
            <h1 class="h4 text-900" style="color: white; font-weight:bold;">Influencer</h1>
    </div>
    <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-3">
                        <!-- Nested Row within Card Body -->
                        <div class="row justify-content center">
                            <!-- <div class="col-lg-6 d-none d-lg-block bg-login-image">p</div> -->
                            <div class="col-lg-12 content-conter">
                                <div class="p-2 justify-content-center">
                                <div class="text-center mb-3">
                                    </div>
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Registrasi</h1>
                                    </div>
                                    <form class="user" action="registrasi.php" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="nama" name="nama_influencer"
                                                placeholder="Masukan Nama influencer...">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="username" name="username"
                                                placeholder="Masukan Username...">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="password" name="password" placeholder="Masukan Password...">
                                        </div>

                                     <div class="form-group">
                                     <label for="foto" class="form-label">Upload Foto Profil</label>
                                    <input type="file" class="form-control" id="foto" name="foto_profil" placeholder="Upload Foto" required>
                                    </div>
                                    <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="password" name="email" placeholder="Masukan Email...">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="password" name="nomor_telepon" placeholder="Masukan Nomor Telepon...">
                                        </div>
                                         <div class="form-group">
                                              <label for="foto" class="form-label">Akun Sosial Media</label>
                                             <div class="row">
                                                 <div class="col-xl-7">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="sosmed_instagram" placeholder="Masukan Akun Instagram...">
                                                 </div>
                                                 <div class="col-xl-5">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="pengikut_sosmed_instagram" placeholder="Jumlah Pengikut...">
                                                 </div>
                                             </div>
                                             
                                        </div>
                                         <div class="form-group">
                                             <div class="row">
                                                 <div class="col-xl-7">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="sosmed_tiktok" placeholder="Masukan Akun TikTok...">
                                                 </div>
                                                 <div class="col-xl-5">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="pengikut_sosmed_tiktok" placeholder="Jumlah Pengikut...">
                                                 </div>
                                             </div>
                                             
                                        </div>
                                        
                                         <div class="form-group">
                                             <div class="row">
                                                 <div class="col-xl-7">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="sosmed_facebook" placeholder="Masukan Akun Facebook...">
                                                 </div>
                                                 <div class="col-xl-5">
                                                <input type="text" class="form-control form-control-user"
                                                id="password" name="pengikut_sosmed_facebook" placeholder="Jumlah Pengikut...">
                                                 </div>
                                             </div>
                                             
                                        </div>
                                        
                                        <button type="submit" name="Submit" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">Daftar</button>
                                        <!-- <a href="dashboard.php" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">
                                            Daftar
                                        </a> -->
                                       
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="login.php">Sudah Punya Akun? Login</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    </div>

</div>

</div>

            
            </div>
        </div>
    </div>

    <!-- Navbar End -->


    <!-- Featured Start -->
   
    <!-- Footer Start -->
    <div class="container-fluid text-white mt-5 pt-5 pb-5" style="background-color:rgba(94, 97, 94, 0.82);">
        <div class="row mx-xl-12 py-0">
            <div class="col-md-12 px-xl-0">
                <p class="mb-md-0 text-center text-white">
                    Copyright &copy; Teknik Informatika Unidayan 2025
                </p>
            </div>
        </div>
    </div>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <script>
function showHide() {
  var inputan = document.getElementById("password");
  if (inputan.type === "password") {
    inputan.type = "text";
  } else {
    inputan.type = "password";
  }
} 
</script>
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="../lib/easing/easing.min.js"></script>
    <script src="../lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="../mail/jqBootstrapValidation.min.js"></script>
    <script src="../mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="../js/main.js"></script>
</body>

</html>