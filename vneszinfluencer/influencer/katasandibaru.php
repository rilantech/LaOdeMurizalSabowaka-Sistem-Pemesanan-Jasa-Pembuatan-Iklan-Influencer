<?php
  session_start();
  include '../koneksidb.php';
  
   $error1     = array(); // menyimpan error
    $InputUser = array(); // menyimpan inputan user
    $valid     = false; //nilai awal false.. belum valid

    if(isset($_POST['Submit'])){
        $password = $_POST['password'];

         // simpan inputan user ke variabel
    $userInput = array('password' => $password);
 
    if($password == "") {
        //tambah kan error
        $error1[] = "<p style='color:red';>Password tidak boleh kosong!</p>";
        // karena username tidak valid maka kosongkan
        $userInput['password'] = "";
      }   
      else{
        $valid = true;
        }
    

    if($valid === false) {
        //jika tidak valid isi variabel $user dan $nama dengan data yg sebelumnya sudah diinputkan..
        $password = $userInput['password'];
     
    }else{
         $select = "UPDATE tabel_akun_influencer SET password= '$password' WHERE email='".$_SESSION['email']."'";
       $result = mysqli_query($db, $select);

            if($result){
                $_SESSION['status_reset'] ="berhasil";
                echo "<script>window.location='login.php';</script>";

            } else{
                echo "<script>alert('Gagal Registrasi!');window.location='katasandibaru.php';</script>";

            }
    //    die("DATA VALID");
       // code ...
       
    }
    }
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZBrand|Lupa Kata Sandi</title>
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
            <h1 class="h4 text-900" style="color: white; font-weight:bold;">Influncer</h1>
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
                                        <h1 class="h4 text-gray-900 mb-4">Reset Kata Sandi</h1>
                                    </div>
                                     <?php

                                        if($pesan === "salah"){
                                            echo "<div class='alert alert-danger'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                                Email Tidak Valid!
                                            </div>";
                                        }
                                        ?>
                                    <form class="user" action="katasandibaru.php" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="nama" name="password"
                                                placeholder="Masukan Password Baru..."  value="<?php echo isset($password) ? $password : '' ?>">
                                        </div>
                                        <?php
                                    // jika terdapat error
                                    if(!empty($error1)) {
                                        foreach ($error1 as $value) {
                                            echo $value;
                                        }
                                    }
                                    ?>
                                        <button type="submit" name="Submit" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">Reset</button>
                                        <!-- <a href="dashboard.php" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">
                                            Daftar
                                        </a> -->
                                       
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="lupakatasandi.php">Kembali</a>
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
    <div class="container-fluid text-white mt-5 pt-5 pb-5" style="background-color:rgba(11, 39, 11, 0.88);">
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