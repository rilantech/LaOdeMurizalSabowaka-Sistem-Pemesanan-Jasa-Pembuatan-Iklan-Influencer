<?php
session_start();
  include '../koneksidb.php';
  date_default_timezone_set('Asia/Makassar');
  $time = date('d-M-Y H:i:s');

  $user = $_SESSION['username'];
  $pass = $_SESSION['password'];
  
  if (!$_SESSION['is_logged_in']) {
      // Redirect ke halaman login
      header('Location: login.php');
      exit;
  }

  if(isset($_SESSION['notifikasi'])){
    if(isset($_SESSION['jumlah_notifikasi'])){
        $_SESSION['jumlah_notifikasi'] += $_SESSION['notifikasi'];
        unset($_SESSION['notifikasi']);
    }else{
        $_SESSION['jumlah_notifikasi'] =0;
    }
} else{
    $_SESSION['notifikasi']= 0;
}

  $result = mysqli_query($db, "SELECT * FROM tabel_akun_brand where username = '$user' and password = '$pass'");
  $akun_brand = mysqli_fetch_array($result);

    // variabel penampung
    $error_nama_project = array(); // menyimpan error
    $error_kategori = array(); // menyimpan error
    $error_lama_kontrak = array(); // menyimpan error
    $error_kriteria_project = array(); // menyimpan error
    $error_gaji_influencer = array(); // menyimpan error
    $userInput = array(); // menyimpan inputan user
    $valid = false; //nilai awal false.. belum valid
  
    $pesan ="";

  
    if(isset($_POST['simpan'])){
            $logo_brand   = $_FILES['logo_brand']['name'];
            $file_tmp = $_FILES['logo_brand']['tmp_name'];
            $nama_project = $_POST['nama_project'];
            $kategori = $_POST['kategori'];
            $lama_kontrak = $_POST['lama_kontrak'];
            $kriteria_project = $_POST['kriteria_project'];
            $gaji_influencer = 0;
        
            $ekstensi_diperbolehkan = array('png', 'jpg'); //ekstensi file gambar yang bisa diupload 
            $x = explode('.', $logo_brand); //memisahkan nama_brand file dengan ekstensi yang diupload
            $ekstensi = strtolower(end($x));
            $logo_brand= rand(1, 999).".".$ekstensi;

            $userInput = array(
                'nama_project' => $nama_project,
                'kategori' => $kategori,
                'lama_kontrak' => $lama_kontrak,
                'kriteria_project' => $kriteria_project,
                'gaji_influencer' => $gaji_influencer
            );
 
              if($nama_project == "" and $kategori == "" and $lama_kontrak == ""  and $kriteria_project == "" and $gaji_influencer == "") {
                  $error_nama_project[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Nama Project tidak boleh kosong!</p>";
                  $error_kategori[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Kategori tidak boleh kosong!</p>";
                  $error_lama_kontrak[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Lama Kontrak tidak boleh kosong!</p>";
                  $error_kriteria_project[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Kriteria Project tidak boleh kosong!</p>";
                  $error_gaji_influencer[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Gaji Influencer tidak boleh kosong!</p>";
        
                  $userInput['nama_project'] = "";
                  $userInput['kategori'] = "";
                  $userInput['lama_kontrak'] = "";
                  $userInput['kriteria_project'] = "";
                  $userInput['gaji_influencer'] = "";
                } 
               
                elseif($nama_project =="") {
                    $error_nama_project[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Nama Project tidak boleh kosong!</p>";
                    $error_kategori[] = "";
                    $error_lama_kontrak[] = "";
                    $error_kriteria_project[] = "";
                    $error_gaji_influencer[] = "";
                    $userInput['nama_project'] = "";
                } elseif($kategori == "") {
                    $error_nama_project[] = "";
                    $error_kategori[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Kategori tidak boleh kosong!</p>";
                    $error_lama_kontrak[] = "";
                    $error_kriteria_project[] = "";
                    $error_gaji_influencer[] = "";
                    $userInput['kategori'] = "";
                  } 
                   elseif($lama_kontrak == "") {
                    $error_nama_project[] = "";
                    $error_kategori[] = "";
                    $error_lama_kontrak[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Lama Kontrak tidak boleh kosong!</p>";
                    $error_kriteria_project[] = "";
                    $error_gaji_influencer[] = "";
                    $userInput['lama_kontrak'] = "";
                  } 
                  elseif($kriteria_project =="") {
                    $error_nama_project[] = "";
                    $error_kategori[] = "";
                    $error_lama_kontrak[] = "";
                    $error_kriteria_project[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Kriteria Project tidak boleh kosong!</p>";
                    $error_gaji_influencer[] = "";
                    $userInput['kriteria_project'] = "";
                  } elseif($gaji_influencer == "") {
                    $error_nama_project[] = "";
                    $error_kategori[] = "";
                    $error_lama_kontrak[] = "";
                    $error_kriteria_project[] = "";
                    $error_gaji_influencer[] = "<p style='color:black; font-size:12px; -webkit-text-stroke: 0.4px red';>Gaji Influencer tidak boleh kosong!</p>";
                    $userInput['gaji_influencer'] = "";
                  } 
                  
                  
                else{
                  $valid = true;
                  }
              
              if($valid === false) {
                  //jika tidak valid isi variabel $user dan $nama dengan data yg sebelumnya sudah diinputkan..
                  $nama_project = $userInput['nama_project'];
                  $kategori = $userInput['kategori'];
                  $lama_kontrak = $userInput['lama_kontrak'];
                  $kriteria_project = $userInput['kriteria_project'];
                  $gaji_influencer = $userInput['gaji_influencer'];
                  
              }      

            if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
              move_uploaded_file($file_tmp, 'gambar/' . $logo_brand); //memindah file gambar ke folder gambar
              // jalankan query INSERT untuk menambah data ke database pastikan sesuai urutan (id tidak perlu karena dibikin otomatis)
              $query = "INSERT INTO tabel_project (waktu_posting, logo_brand, nama_brand, nama_project, kategori, lama_kontrak, kriteria_project, gaji_influencer) VALUES ('$time', '$logo_brand', '".$akun_brand['nama_brand']."', '$nama_project', '$kategori', '$lama_kontrak', '$kriteria_project', '$gaji_influencer')";
              $result = mysqli_query($db, $query);

              
              // periska query apakah ada error
              if (!$result) {
                die("Query gagal dijalankan: " . mysqli_errno($db) .
                  " - " . mysqli_error($db));
              } else {
                //tampil alert dan akan redirect ke halaman index.php
                //silahkan ganti index.php sesuai halaman yang akan dituju
                $_SESSION['status_penyimpanan_data'] = 'berhasil';
                echo "<script>window.location='project.php';</script>";
              }
        }
    }
  ?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZBrand|Tambah Project</title>
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
    <style>
        .profile-picture {
  opacity: 0.75;
  height: 100px;
  width: 100px;
  position: relative;
  overflow: hidden;

  /* default image */
  background: url(../iconfotoprofil.png);

  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  box-shadow: 0 8px 6px -6px black;
}
  .file-uploader {
  /* make it invisible */
  opacity: 0;
  /* make it take the full height and width of the parent container */
  height: 100%;
  width: 100%;
  cursor: pointer;
  /* make it absolute */
  position: absolute;
  top: 0%;
  left: 0%;
}

.upload-icon {
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* initial icon state */
  opacity: 0;
  transition: opacity 0.3s ease;
  color: #ccc;
  -webkit-text-stroke-width: 2px;
  -webkit-text-stroke-color: #bbb;
}

.profile-picture:hover .upload-icon {
  opacity: 1;
}

#numbered-textarea {
  counter-reset: list-item; /* Reset counter for each textarea */
  white-space: pre-wrap; /* Maintain line breaks */
}

#numbered-textarea + .list-group {
  display: none; /* Initially hide the list */
}

/* For each line, generate a number */
#numbered-textarea::before {
  content: counter(list-item) ". ";
  counter-increment: list-item; /* Increment counter for each line */
  position: relative;
  left: -1.5em;
  display: inline-block;
  width: 1.5em;
  text-align: right;
  padding-right: 0.5em;
  color: #6c757d; /* Color for the numbers */
}

/* Show the list on focus or click */
#numbered-textarea:focus + .list-group,
#numbered-textarea:hover + .list-group {
  display: block;
}
    </style>
</head>

<body>
    <!-- Topbar Start -->
    
    <div class="container-fluid" style="background-color: #009688;">
        <div class="row align-items-center py-1 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><img src="logoaplikasi.png" alt="" width="100" height="80"></h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <!-- <form action="" >
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Cari Project...">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form> -->
            </div>
            <div class="col-lg-3 col-6 text-right">
            <a href="notifikasi.php" class="mr-5">
                    <i class="fas fa-bell text-white"></i>
                    <span class="badge text-white">
                    <?php 
                    if(isset($_SESSION['jumlah_notifikasi'])){
                        echo $_SESSION['jumlah_notifikasi'];
                    }else{
                        echo 0;
                    }
                    ?>
                    </span>
                </a>
                <a href="">
                    <span class="badge text-white"><?= $akun_brand['nama_brand'];?></span>
                    <a href="#" class=" dropdown-toggle" data-toggle="dropdown"> <img class="rounded-circle" alt="avatar1" src="gambar/<?= $akun_brand['foto_profil'];?>" width="50" height="50"/></a>
                    <div class="dropdown-menu rounded-0 m-0 text-center">
                            <a href="logout.php" class="dropdown-item"><i class="fa fa-arrow-left"></i>   Logout</a>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid mb-1">
        <div class="row border-top px-0">
            <div class="col-lg-12 px-0">
                <nav class="navbar navbar-expand-lg navbar-light py-lg-0 px-0" style="background-color:rgba(185, 228, 175, 0.82);">
                    <a href="" class="text-decoration-none d-block d-lg-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><img src="logoaplikasi.png" alt="" width="100" height="80"></h1>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="project.php" class="nav-item nav-link active">Project</a>
                            <a href="progress.php" class="nav-item nav-link">Progress</a>
                            <a href="daftarinfluencer.php" class="nav-item nav-link">Daftar Influencer</a>
                            <a href="profil.php" class="nav-item nav-link">Profil</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar End -->


    <!-- Featured Start -->
    <div class="container-fluid pt-5 py-5 col-lg-6 col-small-6">
        <div class="col-lg-12">
            <div class="text-center mb-4">
                <h4><span class="px-2">Tambah Project</span></h4>
            </div>
        </div>
        <div class="card border-0 shadow-lg my-5 mx-auto bg-primary">
            <div class="card-body">
                <!-- Nested Row within Card Body -->
                <div class="row justify-content center">
                    <!-- <div class="col-lg-6 d-none d-lg-block bg-login-image">p</div> -->
                     <div class="col-lg-4 content-conter">
                        <form class="user" action="tambahproject.php" method="post" enctype="multipart/form-data">
                             <div class="form-group" >
                                <label class="mt-2">Logo Brand</label>
                                <div class="profile-picture w-100 form-control">
                                <h1 class="upload-icon">
                                    <i class="fa fa-plus fa-2x" aria-hidden="true"></i>
                                </h1>
                                    <input class="file-uploader" type="file" onchange="upload()" accept="image/*" name="logo_brand"/>
                                    <?php
                                    // jika terdapat error
                                    if(!empty($error_logo_brand)) {
                                        foreach ($error_logo_brand as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>
                                </div>
                            
                                </div>
                                
                            </div>
                    <div class="col-lg-8 content-conter">
                        <div class="p-2 justify-content-center">
                                <div class="form-group">
                                <label>Nama Project</label>
                                    <input type="text" class="form-control form-control-user"
                                        id="username" 
                                        name= "nama_project" placeholder="Masukan Nama Project..." value="<?= isset($nama_project) ? $nama_project : '';?>">
                                        <?php
                                    // jika terdapat error
                                    if(!empty($error_nama_project)) {
                                        foreach ($error_nama_project as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>
                                    </div>

                               
                                    <div class="form-group">
                                    <label>Kategori</label>
                                        <select class="form-control" name="kategori">
                                            <option selected value="<?= isset($kategori) ? $kategori : '' ?>"><?= isset($kategori) ? $kategori : "Pilih Kategori" ?></option>
                                            <option>Fashion & Accessories</option>
                                            <option>Food & Drink</option>
                                            <option>Electronic</option>
                                            <option>Beauty</option>
                                            <option>Tech</option>
                                        </select>
                                        <?php
                                    // jika terdapat error
                                    if(!empty($error_kategori)) {
                                        foreach ($error_kategori as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>
                                    </div>
                                    
                                     <div class="form-group">
                                    <label>Lama Kontrak</label>
                                        <select class="form-control" name="lama_kontrak">
                                            <option selected value="<?= isset($lama_kontrak) ? $lama_kontrak : '' ?>"><?= isset($lama_kontrak) ? $lama_kontrak : "Pilih Lama Kontrak" ?></option>
                                            <option>1 Minggu</option>
                                            <option>2 Minggu</option>
                                            <option>3 Minggu</option>
                                            <option>1 Bulan</option>
                                            <option>2 Bulan</option>
                                            <option>3 Bulan</option>
                                            <option>4 Bulan</option>
                                            <option>6 Bulan</option>
                                            <option>7 Bulan</option>
                                            <option>8 Bulan</option>
                                            <option>9 Bulan</option>
                                            <option>10 Bulan</option>
                                            <option>11 Bulan</option>
                                            <option>12 Bulan</option>
                                        </select>
                                        <?php
                                    // jika terdapat error
                                    if(!empty($error_lama_kontrak)) {
                                        foreach ($error_lama_kontrak as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>
                                    </div>
                           
                                     <div class="form-group">
                                <label>Keterangan Project</label>
                                <textarea class="form-control "  rows="6" id="myTextarea" placeholder="Masukan Keterangan Project..." name="kriteria_project" value="<?= isset($kriteria_project) ? $kriteria_project : '' ?>"><?= isset($kriteria_project) ? $kriteria_project : '' ?></textarea>
                                <?php
                                    // jika terdapat error
                                    if(!empty($error_kriteria_project)) {
                                        foreach ($error_kriteria_project as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>    
                            </div>
                            
                            <script>
                               const textarea = document.getElementById('myTextarea');
                               var number = 1;
                                 

                                textarea.addEventListener('keydown', function(e) {
                                   if(e.key === "Enter"){
                                     
                                     var text= this.value;
                                       var lines = text.split("\n");
                                       var lastLine =lines[lines.length - 1];
                                       
                                           this.value += "\n" +  number + ". ";
                                           number++;
                                           e.preventDefault();
                                           this.selectionStart= this.value.length;
                                           this.selectionEnd = this.value.length;
                                       
                                   } 
                                   
                                 
                                   
                            
                                });
                                
                            </script>
                               
                                
                                <div class="form-group" hidden>
                                <label>Gaji Influencer</label>
                                    <input type="number" class="form-control form-control-user"
                                        id="username"
                                        name= "gaji_influencer" placeholder="Masukan Gaji Inluencer..." value="<?php echo isset($gaji_influencer) ? $gaji_influencer : '' ?>">
                                        <?php
                                    // jika terdapat error
                                    if(!empty($error_gaji_influencer)) {
                                        foreach ($error_gaji_influencer as $value) {
                                            echo $value;
                                        }
                                    }
                                ?>    
                                    </div>
                                <!-- <a href="dashboard.php" class="btn btn-primary btn-user btn-block" style="font-size: 20px;font-weight:bold;">
                                    Login
                                </a> -->
                               
                          
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-lg-6">
                    <button type="submit" name="simpan" class="btn btn-success rounded" style="font-size: 15px;font-weight:bold;">Simpan</button>
                    <button type="button" class="btn btn-danger rounded" style="font-size: 15px;font-weight:bold;" onclick="window.location='project.php';">Batal</button>
                    </div>
                </div>
                </form>
                
            </div>
        </div>
    </div>

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
    function upload() {

const fileUploadInput = document.querySelector('.file-uploader');

/// Validations ///

if (!fileUploadInput.value) {
  return;
}

// using index [0] to take the first file from the array
const image = fileUploadInput.files[0];

// check if the file selected is not an image file
if (!image.type.includes('image')) {
  return alert('Only images are allowed!');
}

// check if size (in bytes) exceeds 10 MB
if (image.size > 10_000_000) {
  return alert('Maximum upload size is 10MB!');
}

/// Display the image on the screen ///

const fileReader = new FileReader();
fileReader.readAsDataURL(image);

fileReader.onload = (fileReaderEvent) => {
  const profilePicture = document.querySelector('.profile-picture');
  profilePicture.style.backgroundImage = `url(${fileReaderEvent.target.result})`;
}

// upload image to the server or the cloud
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