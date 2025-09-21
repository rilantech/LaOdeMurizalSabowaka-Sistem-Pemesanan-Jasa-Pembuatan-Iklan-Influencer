<?php
session_start();
  include '../koneksidb.php';

    // variabel penampung
    $error1     = array(); // menyimpan error
    $error2     = array(); // menyimpan error
    $InputUser = array(); // menyimpan inputan user
    $valid     = false; //nilai awal false.. belum valid

    $pesan ="";
    $pesan_berhasil_registrasi ='';
    $pesan_berhasil_reset ='';

    if(isset($_SESSION['status_registrasi'])){
        $pesan_berhasil_registrasi = 'aktif';
        unset($_SESSION['status_registrasi']);
    }
    
     if(isset($_SESSION['status_reset'])){
        $pesan_berhasil_reset = 'aktif';
        unset($_SESSION['status_reset']);
        unset($_SESSION['email']);
    }


    if(isset($_POST['Submit'])){
        $username = $_POST['username'];
        $password = $_POST['password'];

        $sql = "SELECT * FROM tabel_akun_brand WHERE username = '$username' and password ='$password'"; 
        $query = mysqli_query($db, $sql);

        $rows = mysqli_num_rows($query);

         // simpan inputan user ke variabel
    $userInput = array('username' => $username, 'password' => $password);
 
    if($username == "" and $password == "") {
        //tambah kan error
        $error1[] = "<p style='color:red';>Username tidak boleh kosong!</p>";
        $error2[] = "<p style='color:red';>Password tidak boleh kosong!</p>";

        // karena username tidak valid maka kosongkan
        $userInput['username'] = "";
        $userInput['password'] = "";
      }  elseif($username == "") {
        //tambah kan error
        $error2[]="";
        $error1[] = "<p style='color:red; font-size: 15px'';>Username tidak boleh kosong!</p>";    
        $userInput['username'] = "";
        // karena username tidak valid maka kosongkan
      }  elseif($password == ""){
        $error1[]="";
        $error2[] = "<p style='color:red; font-size: 15px;'>Password tidak boleh kosong!</p>";   
        $userInput['password'] = ""; 
      }      
      else{
        $valid = true;
        }
    

    if($valid === false) {
        //jika tidak valid isi variabel $user dan $nama dengan data yg sebelumnya sudah diinputkan..
        $username = $userInput['username'];
        $password = $userInput['password'];
        
    }else{
        if($valid === false) {
            //jika tidak valid isi variabel $user dan $nama dengan data yg sebelumnya sudah diinputkan..
            $username = $userInput['username'];
            $password = $userInput['password'];
            
        }
    //    die("DATA VALID");
       // code ...
       if($rows == 1){
         $_SESSION['is_logged_in'] = true;
         $_SESSION['username'] = $username;
         $_SESSION['password'] = $password;
         header('location: project.php');
         echo "<script>alert('Berhasil Login!');</script>";
        } else{
            $pesan = "salah";
        }
       
    }
    }
  ?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZBrand|Login</title>
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
            <h1 class="h4 text-900" style="color: white; font-weight:bold;">Brand</h1>
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
                                <h1 class="h4 text-gray-900 mb-4">Login</h1>
                            </div>
                            <?php

                            if($pesan === "salah"){
                                echo "<div class='alert alert-danger'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Username / Password Salah!
                                </div>";
                            }
                            if($pesan_berhasil_registrasi === "aktif"){
                                echo "<div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Registrasi Akun Berhasil!
                                </div>";
                            }
                            
                             if($pesan_berhasil_reset === "aktif"){
                                echo "<div class='alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Password Anda Berhasil DiReset!
                                </div>";
                            }
                       
                            ?>
                            <form class="user" action="login.php" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user"
                                        id="username"
                                        name= "username" placeholder="Masukan Username..." value="<?php echo isset($username) ? $username : '' ?>">
                                </div>
                                <?php
                                    // jika terdapat error
                                    if(!empty($error1)) {
                                        foreach ($error1 as $value) {
                                            echo $value;
                                        }
                                    }
                                    ?>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user"
                                       name="password" id="password" placeholder="Masukan Password..." value="<?php echo isset($password) ? $password : '' ?>">
                                </div>
                                <?php
                                    // jika terdapat error
                                    if(!empty($error2)) {
                                        foreach ($error2 as $value) {
                                            echo $value . "<br>";
                                        }
                                    }
                                    ?>
                                    
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xl-8 col-small-8">
                                        <div class="custom-control custom-checkbox small">
                                        <input type="checkbox" class="custom-control-input" id="customCheck" onclick="showHide()">
                                        <label class="custom-control-label" for="customCheck">
                                            Tampilkan Password</label>
                                        </div>
                                        </div>
                                         <div class="col-xl-4 col-small-4">
                                          <!-- Simple link -->
                                           <a class="small" href="lupakatasandi.php">Lupa Kata Sandi?</a>
                                        </div>
                                        
                                    </div>
                                  
                                    
                                </div>
                                
                                <button type="submit" name="Submit" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">Login</button>
                                <!-- <a href="dashboard.php" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">
                                    Login
                                </a> -->
                               
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="registrasi.php">Belum Punya Akun? Registrasi</a>
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