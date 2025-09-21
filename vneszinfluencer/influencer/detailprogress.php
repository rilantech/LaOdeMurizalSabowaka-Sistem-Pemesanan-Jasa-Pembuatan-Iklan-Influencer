<?php
session_start();
  include '../koneksidb.php';
  $user = $_SESSION['username_influencer'];
  $pass = $_SESSION['password_influencer'];

  if (!$_SESSION['is_logged_in_influencer']) {
      // Redirect ke halaman login
      header('Location: login.php');
      exit;
  }

  if(isset($_SESSION['notifikasi_influencer'])){
    if(isset($_SESSION['jumlah_notifikasi_influencer'])){
        $_SESSION['jumlah_notifikasi_influencer'] += $_SESSION['notifikasi_influencer'];
        unset($_SESSION['notifikasi_influencer']);
    }else{
        $_SESSION['jumlah_notifikasi_influencer'] =0;
    }
} else{
    $_SESSION['notifikasi_influencer']= 0;
}


  $pesan_status_update_data ='';

  if(isset($_SESSION['status_update_data'])){
    $pesan_status_update_data = 'aktif';
    unset($_SESSION['status_update_data']);
}

  if(isset($_GET['id'])){
    $result_project = mysqli_query($db, "SELECT * FROM tabel_progress_project where id_progress = '".$_GET['id']."'");
    $data_progress_project = mysqli_fetch_array($result_project);
  }

  $result = mysqli_query($db, "SELECT * FROM tabel_akun_influencer where username = '$user' and password = '$pass'");
  $akun_influencer = mysqli_fetch_array($result);

    $pesan ="";

  
    if(isset($_POST['edit'])){
            $id = $_POST['id'];
            $link_draft = $_POST['link_draft'];
            $link_bukti_post = $_POST['link_bukti_post'];
            $nomor_rekening = $_POST['nomor_rekening'];
           
            $query = "UPDATE tabel_progress_project SET link_draft='$link_draft', link_bukti_post='$link_bukti_post',
            nomor_rekening ='$nomor_rekening' WHERE id_progress='$id'";
            $result = mysqli_query($db, $query);

              // periska query apakah ada error
              if (!$result) {
                die("Query gagal dijalankan: " . mysqli_errno($db) .
                  " - " . mysqli_error($db));
              } else {
                //tampil alert dan akan redirect ke halaman index.php
                //silahkan ganti index.php sesuai halaman yang akan dituju
                $_SESSION['status_update_data'] = 'berhasil';
                header('Location:'. $_SERVER['HTTP_REFERER']);
                exit;
              }
        }

  ?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZInfluencer|Detail Progress</title>
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
                    if(isset($_SESSION['jumlah_notifikasi_influencer'])){
                        echo $_SESSION['jumlah_notifikasi_influencer'];
                    }else{
                        echo 0;
                    }
                    ?>
                    </span>
                </a>
                <a href="">
                    <span class="badge text-white"><?= $akun_influencer['nama_influencer'];?></span>
                    <a href="#" class=" dropdown-toggle" data-toggle="dropdown"> <img class="rounded-circle" alt="avatar1" src="gambar/<?= $akun_influencer['foto_profil'];?>" width="50" height="50"/></a>
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
                            <a href="project.php" class="nav-item nav-link">Project</a>
                            <a href="project.php" class="nav-item nav-link active">Progress</a>
                            <a href="daftarbrand.php" class="nav-item nav-link">Daftar Brand</a>
                            <a href="profil.php" class="nav-item nav-link">Profil</a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar End -->


    <!-- Featured Start -->
    <div class="container-fluid pt-3 py-5 col-lg-12 col-small-6">
      
            <?php 
        if($pesan_status_update_data === "aktif"){
                                echo "<div class='alert col-lg-4 alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Progress Project Berhasil Terupdate!
                                </div>";
                            }
                            ?>
            <div class="text-start mb-4">
                <h4>Detail Progress</h4>
            </div>
        
        <div class="card border-0 shadow-lg my-3 mx-auto bg-primary">
            <div class="card-body">
                <!-- Nested Row within Card Body -->
                <div class="row justify-content center">
                <div class="col-lg-12">
            <div class="text-start mb-4">
                <h5><?= $data_progress_project['nama_project'];?> | <?= $data_progress_project['nama_brand'];?></h5>
            </div>
        </div>
                    <!-- <div class="col-lg-6 d-none d-lg-block bg-login-image">p</div> -->
                     <div class="col-lg-3 content-conter">
                        <form class="user" action="detailprogress.php" method="post" enctype="multipart/form-data">
                        <input type="text" class="form-control form-control-user"
                                        id="username" 
                                        name= "id" placeholder="Masukan Nama Project..." value="<?= $data_progress_project['id_progress'];?>" hidden>
                            <div class="form-group">
                            <img class="form-control w-100" src="../brand/gambar/<?= $data_progress_project['logo_brand'];?>" alt="" style="height: 300px;">
                            </div>                               
                            </div>
                    <div class="col-lg-9 content-conter">
                        <div class="p-2 justify-content-center">
                            <div class="row">
                                <div class="col-lg-6">
                                <div class="form-group">
                                <label>Link Draft (Link Google Drive)</label>
                                    <input type="text" class="form-control form-control-user"
                                        id="username" 
                                        name= "link_draft" placeholder="Masukan Link Draft..." value="<?= $data_progress_project['link_draft'];?>">
                                    </div>
                                <div class="form-group">
                                <label>Link Bukti Post</label>
                                    <input type="text" class="form-control form-control-user"
                                        id="username" 
                                        name= "link_bukti_post" placeholder="Masukan Link Bukti Post..." value="<?= $data_progress_project['link_bukti_post'];?>">
                                    </div>
                                <div class="form-group">
                                <label>Status Project</label><br>
                                <div class="row">
                                <?php if($data_progress_project['status_project'] == "Sedang Dikerjakan") { ?>
                                <div class="col-12">
                                <p>-Sedang Dikerjakan-</p>
                                </div>
                                <?php } ?>
                                <?php if($data_progress_project['status_project'] == "ACC") { ?>
                                    <div class="col-1">
                                    <div style="background-color: green; height: 40px; width: 40px;">
                                    </div>
                                </div>
                                <div class="col-11 py-1">
                                   <p class="px-2"><span style="background-color: green; font-weight: bold; color:white">ACC</span> / REVISI / DITOLAK/ DIBAYAR</p>
                                </div>
                                <?php } ?>
                                <?php if($data_progress_project['status_project'] == "Revisi") { ?>
                                    <div class="col-1">
                                    <div style="background-color: yellow; height: 40px; width: 40px;">
                                    </div>
                                </div>
                                <div class="col-11 py-1">
                                   <p class="px-2">ACC / <span style="background-color: yellow; font-weight: bold;">REVISI</span> / DITOLAK/ DIBAYAR</p>
                                </div>
                                <?php } ?>
                                <?php if($data_progress_project['status_project'] == "Ditolak") { ?>
                                    <div class="col-1">
                                    <div style="background-color: red; height: 40px; width: 40px;">
                                    </div>
                                </div>
                                <div class="col-11 py-1">
                                   <p class="px-2">ACC / REVISI / <span style="background-color: red; font-weight: bold;">DITOLAK</span> / DIBAYAR</p>
                                </div>
                                <?php } ?>
                                <?php if($data_progress_project['status_project'] == "Dibayar") { ?>
                                    <div class="col-1">
                                    <div style="background-color: skyblue; height: 40px; width: 40px;">
                                    </div>
                                </div>
                                <div class="col-11 py-1">
                                   <p class="px-2">ACC / REVISI / DITOLAK / <span style="background-color: skyblue; font-weight: bold;">DIBAYAR</span></p>
                                </div>
                                <?php } ?>
                                </div>                 
                                </div>
                                <div class="form-group">
                                <label>Struk Bukti Pembayaran</label>
                                <?php if($data_progress_project['struk_bukti_pembayaran'] != "") { ?>
                                <img class="form-control w-100" src="../brand/gambar/<?= $data_progress_project['struk_bukti_pembayaran'];?>" alt="" style="height: 300px;">
                                <?php } ?>
                                <?php if($data_progress_project['struk_bukti_pembayaran'] == "") { ?>
                                    <p>-Belum Ada-</p>
                                <?php } ?>
                                </div>
                                </div>
                                <div class="col-lg-6">
                                <div class="form-group">
                                <label>Revisi</label>
                                <textarea class="form-control bg-white" rows="9" id="message" placeholder="-Belum Ada-" name="revisi" value="<?= $data_progress_project['revisi'];?>" readonly><?= $data_progress_project['revisi'];?></textarea>  
                                    </div>
                                <div class="form-group">
                                <label>Nomor Rekening (Dana / GoPay)</label>
                                    <input type="text" class="form-control form-control-user"
                                        id="username" 
                                        name= "nomor_rekening" placeholder="Diisi ketika project Telah Di ACC..." value="<?= $data_progress_project['nomor_rekening'];?>">
                                    </div>
                               
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-lg-6">
                    <button type="submit" name="edit" class="btn btn-success rounded" style="font-size: 15px;font-weight:bold;">Simpan</button>
                    <button type="button" class="btn btn-danger rounded" style="font-size: 15px;font-weight:bold;" onclick="window.location='progress.php';">Batal</button>
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