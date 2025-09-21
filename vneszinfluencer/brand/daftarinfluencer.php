<?php
session_start();
include '../koneksidb.php';

$user = $_SESSION['username'];
$pass = $_SESSION['password'];
$cari_project = "";

if (!$_SESSION['is_logged_in']) {
    // Redirect ke halaman login
    header('Location: login.php');
    exit;
}

$pesan_status_penyimpanan_data ='';
$pesan_status_update_data ='';
$pesan_status_hapus_data ='';

    if(isset($_SESSION['status_penyimpanan_data'])){
        $pesan_status_penyimpanan_data = 'aktif';
        unset($_SESSION['status_penyimpanan_data']);
    }
    if(isset($_SESSION['status_update_data'])){
        $pesan_status_update_data = 'aktif';
        unset($_SESSION['status_update_data']);
    }
    if(isset($_SESSION['status_hapus_data'])){
        $pesan_status_hapus_data = 'aktif';
        unset($_SESSION['status_hapus_data']);
    }
    if(isset($_SESSION['notifikasi'])){
        if(isset($_SESSION['jumlah_notifikasi'])){
            $_SESSION['jumlah_notifikasi'] += $_SESSION['notifikasi'];
            unset($_SESSION['notifikasi']);
        }else{
            $_SESSION['jumlah_notifikasi'] =0;
        }
    } else{
       
    }
    
     if(isset($_COOKIE['id_ku'])){
          session_id($_COOKIE['id_ku']);
            session_start();
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

if(isset($_POST['cari_project'])){
    $cari_project = $_POST['cari_project'];
    $batas = 10;
	$halaman = isset($_GET['halaman'])?(int)$_GET['halaman'] : 1;
	$halaman_awal = ($halaman>1) ? ($halaman * $batas) - $batas : 0;	
 
	$previous = $halaman - 1;
	$next = $halaman + 1;

    $result2 = mysqli_query($db, "SELECT * FROM tabel_akun_influencer");
    $jumlah_data = mysqli_num_rows($result2);
    $total_halaman = ceil($jumlah_data / $batas);

    $datainfluencer = mysqli_query($db, "SELECT * FROM tabel_akun_influencer where nama_influencer LIKE '%".$_POST['cari_project']."%' ORDER by id_influencer DESC limit $halaman_awal, $batas");
    $nomor = $halaman_awal+1;
}else{
    $batas = 10;
	$halaman = isset($_GET['halaman'])?(int)$_GET['halaman'] : 1;
	$halaman_awal = ($halaman>1) ? ($halaman * $batas) - $batas : 0;	
 
	$previous = $halaman - 1;
	$next = $halaman + 1;
    $result2 = mysqli_query($db, "SELECT * FROM tabel_akun_influencer");
    $jumlah_data = mysqli_num_rows($result2);
	$total_halaman = ceil($jumlah_data / $batas);

    $datainfluencer = mysqli_query($db, "SELECT * FROM tabel_akun_influencer ORDER by id_influencer DESC limit $halaman_awal, $batas");
    $nomor = $halaman_awal+1;
}


if(isset($_GET['id_hapus'])){
    $query = "DELETE FROM tabel_project WHERE id='".$_GET['id_hapus']."'";
    $result = mysqli_query($db, $query);
    // periska query apakah ada error
    if (!$result) {
      die("Query gagal dijalankan: " . mysqli_errno($db) .
        " - " . mysqli_error($db));
    } else {
      //tampil alert dan akan redirect ke halaman index.php
      //silahkan ganti index.php sesuai halaman yang akan dituju
      $_SESSION['status_hapus_data'] = 'berhasil';
      echo "<script>window.location='project.php';</script>";
    }
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>VnesZBrand|Daftar Influencer</title>
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

<body>
    <!-- Topbar Start -->
    
    <div class="container-fluid" style="background-color:#009688;">
        <div class="row align-items-center py-1 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><img src="logoaplikasi.png" alt="" width="100" height="80"></h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="daftarinfluencer.php" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Cari Influencer..." name="cari_project" value="<?= $cari_project;?>">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="notifikasi.php" class="mr-5">
                    <i class="fas fa-bell text-white"></i>
                    <span class="badge text-white"><?php 
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
                            <a href="project.php" class="nav-item nav-link">Project</a>
                            <a href="progress.php" class="nav-item nav-link">Progress</a>
                            <a href="daftarinfluencer.php" class="nav-item nav-link active">Daftar Influencer</a>
                            <a href="profil.php" class="nav-item nav-link">Profil</a>
                           
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar End -->


    <!-- Featured Start -->
    <div class="container-fluid pt-5 py-5">
        <div class="row px-xl-5 pb-3">
        <div class="col-lg-12">
            <?php
             if($pesan_status_penyimpanan_data === "aktif"){
                                echo "<div class='alert col-lg-4 alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Project Berhasil Tersimpan!
                                </div>";
                            }
             if($pesan_status_update_data === "aktif"){
                                echo "<div class='alert col-lg-4 alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Project Berhasil Terupdate!
                                </div>";
                            }
             if($pesan_status_hapus_data === "aktif"){
                                echo "<div class='alert col-lg-4 alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>
                                    Project Berhasil Terhapus!
                                </div>";
                            }
            ?>
            <div class="text-center mb-6">
                <h3><span class="px-2">Daftar Influencer</span></h3>
            </div>
            <div class="row px-xl-5 pb-3 pt-5">
            <?php while($data_influencer = mysqli_fetch_array($datainfluencer)) { ?>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="card product-item border-0 mb-4">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <!--<p class="font-italic mt-2 ml-1" style="font-size: 13px;">Waktu Post :<?= $data_influencer['username'];?></p>-->
                        <img class="img-fluid w-100" src="../influencer/gambar/<?= $data_influencer['foto_profil'];?>" alt="" style="height: 150px;">
                        <p class="font-italic mt-3 ml-1" style="font-size: 13px;">Follower IG: <?= $data_influencer['pengikut_sosmed_instagram'];?></p>
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-1 pb-1">
                        
                        <h6 style="font-weight: bold;"><?= $data_influencer['nama_influencer'];?></h6>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="detailinfluencer.php?id=<?= $data_influencer['id_influencer'];?>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Detail</a>
                    </div>
                </div>
            </div>
            <?php } ?>
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