<?php
session_start();
include '../koneksidb.php';

$user = $_SESSION['username_influencer'];
$pass = $_SESSION['password_influencer'];
$nomor = 1;
$cari_project = "";
if (!$_SESSION['is_logged_in_influencer']) {
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

$result = mysqli_query($db, "SELECT * FROM tabel_akun_influencer where username = '$user' and password = '$pass'");
$akun_influencer = mysqli_fetch_array($result);

if(isset($_POST['cari_project'])){
    $cari_project = $_POST['cari_project'];
    $result2 = mysqli_query($db, "SELECT * FROM tabel_project where nama_project LIKE '%".$_POST['cari_project']."%' ORDER by id_project DESC limit 8");
}else{
    $result2 = mysqli_query($db, "SELECT * FROM tabel_project ORDER by id_project DESC LIMIT 8");
}


if(isset($_GET['id_hapus'])){
    $query = "DELETE FROM tabel_project WHERE id_project='".$_GET['id_hapus']."'";
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
    <title>VnesZInfluencer|Project</title>
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
                <form action="project.php" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Cari Project..." name="cari_project" value="<?= $cari_project;?>">
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
                            <a href="project.php" class="nav-item nav-link active">Project</a>
                            <a href="progress.php" class="nav-item nav-link">Progress</a>
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
            <div class="text-start">
                <h3><span class="px-2">Kategori</span></h3>
            </div>
        </div>
        <div class="container-fluid pt-3">
        <div class="row px-xl-5 pb-3">
            
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <?php $id = "Fashion & Accessories";
                    $url="kategoriproject.php?id=".urlencode($id);
                ?>
            <a href="kategoriproject.php?id=Fashion+%26+Accessories" class="text-decoration-none">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <i class="m-0 mr-2"><img src="iconfashionandaccessories.png" width="50" height="50"/></i>
                    <h5 class="font-weight-semi-bold m-0">Fashion & Accessories</h5>
                </div>
             </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
            <a href="kategoriproject.php?id=Food+%26+Drink" class="text-decoration-none">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <i class="m-0 mr-2"><img src="iconfoodanddrink.png" width="50" height="50"/></i>
                    <h5 class="font-weight-semi-bold m-0">Food & Drink</h5>
                </div>
            </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
            <a href="kategoriproject.php?id=Electronic" class="text-decoration-none">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <i class="m-0 mr-2"><img src="iconelectronic.png" width="50" height="50"/></i>
                    <h5 class="font-weight-semi-bold m-0">Electronic</h5>
                </div>
            </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
            <a href="kategoriproject.php?id=Beauty" class="text-decoration-none">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <i class="m-0 mr-2"><img src="iconbeauty.png" width="50" height="50"/></i>
                    <h5 class="font-weight-semi-bold m-0">Beauty</h5>
                </div>
            </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
            <a href="kategoriproject.php?id=Tech" class="text-decoration-none">
                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                    <i class="m-0 mr-2"><img src="icontech.png" width="50" height="50"/></i>
                    <h5 class="font-weight-semi-bold m-0">Tech</h5>
                </div>
            </a>
            </div>
        </div>
    </div>
    <div class="container-fluid pt-3">
        <div class="text-center mb-4">
            <h4 class="section-title px-5"><span class="px-2">Project Terbaru</span></h4>
        </div>
        <div class="row px-xl-5 pb-3">
            <?php while($data_project = mysqli_fetch_array($result2)) { ?>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="card product-item border-0 mb-4">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <p class="font-italic mt-2 ml-1" style="font-size: 13px;">Waktu Post :<?= $data_project['waktu_posting'];?></p>
                        <img class="img-fluid w-100" src="../brand/gambar/<?= $data_project['logo_brand'];?>" alt="" style="height: 150px;">
                        <p class="font-italic mt-3 ml-1" style="font-size: 13px;"><?= $data_project['nama_brand'];?></p>
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        
                        <h6 class="mb-3" style="font-weight: bold;"><?= $data_project['nama_project'];?></h6>
                        <div class="d-flex justify-content-center">
                            <h6><?= $data_project['lama_kontrak'];?></h6>
                            <h6 hidden>Rp <?= number_format($data_project['gaji_influencer'], 0, ',', '.'); ?></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="detailproject.php?id=<?= $data_project['id_project'];?>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Detail</a>
                        <a href="kerjakanproject.php?id=<?= $data_project['id_project'];?>" class="btn btn-sm text-dark p-0"><i class="fas fa-tasks text-primary mr-1"></i>Kerjakan</a>
                    </div>
                </div>
            </div>
            <?php } ?>
        </div>
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