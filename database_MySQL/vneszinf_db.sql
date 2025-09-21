-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2025 at 08:12 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vneszinf_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tabel_akun_brand`
--

CREATE TABLE `tabel_akun_brand` (
  `id_brand` int(11) NOT NULL,
  `nama_brand` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `foto_profil` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nomor_telepon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_akun_brand`
--

INSERT INTO `tabel_akun_brand` (`id_brand`, `nama_brand`, `username`, `password`, `foto_profil`, `email`, `nomor_telepon`) VALUES
(12, 'AXIS', 'axis.id', '12345', '599.png', 'axisnest@gmail.com', '08388000838'),
(13, 'BALENCIAGA', 'balenciaga', '12345', '93.jpg', '+1 646 889 1895', 'us@balenciaga.c'),
(14, 'Depts Goat', 'deptsgoat', '12345', '568.png', 'support@goat.com', '08118999963'),
(15, 'DJOY', 'djoypodmax', '12345', '397.jpg', 'support@djoy9.com', '08119991289'),
(16, 'Deathless Empire', 'deathlessempire', '12345', '802.jpg', 'deathlessempire@gmail.com', '085646739666'),
(17, 'Glad 2 Glow', 'glad2glow', '12345', '142.jpg', 'glad2glow123@gmail.com', '08041871871'),
(18, 'Kahf', 'kahf', '12345', '305.png', 'kahf.official@gmail.com', '08041401123'),
(19, 'NAPOLEON', 'napoleon', '12345', '522.jpg', '@napoleonfashions.com', '08041871871'),
(20, 'Soju Jinro', 'sojujinro', '12345', '158.jpg', 'admin@jakartawines.com', '08041871871'),
(21, 'TOP Coffee', 'topcoffee', '12345', '779.jpg', 'jjcommunicationcorp@gmail.com', ' 08001818818 '),
(26, 'Erafone', 'erafone', '12345', '758.jpg', 'erafone@gmail.com', '082214626718'),
(27, 'Advan', 'advan', '12345', '37.jpg', 'advan@gmail.com', '082214626718'),
(28, 'Bank DKI', 'bankdki', '1234', '234.jpg', 'DKIjakarta@gmail.com', '082214626718');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_akun_influencer`
--

CREATE TABLE `tabel_akun_influencer` (
  `id_influencer` int(11) NOT NULL,
  `nama_influencer` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `foto_profil` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nomor_telepon` varchar(15) NOT NULL,
  `sosmed_tiktok` varchar(30) NOT NULL,
  `pengikut_sosmed_tiktok` varchar(20) NOT NULL,
  `sosmed_instagram` varchar(30) NOT NULL,
  `pengikut_sosmed_instagram` varchar(20) NOT NULL,
  `sosmed_facebook` varchar(30) NOT NULL,
  `pengikut_sosmed_facebook` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_akun_influencer`
--

INSERT INTO `tabel_akun_influencer` (`id_influencer`, `nama_influencer`, `username`, `password`, `foto_profil`, `email`, `nomor_telepon`, `sosmed_tiktok`, `pengikut_sosmed_tiktok`, `sosmed_instagram`, `pengikut_sosmed_instagram`, `sosmed_facebook`, `pengikut_sosmed_facebook`) VALUES
(10, 'Vannes Chriz', 'vanneschriz', '12345', '978.jpg', 'vanneschrizjob@gmail.com', '0822396142', 'vannesvlog', '3430', 'vanneschriz', '19000', 'vanneschrize', '3500'),
(12, 'Asrul', 'asrul', '12345', '575.jpg', 'asrul@gmail.com', '085219941023', 'asrulvlog', '2000', 'asrulngab', '10000', 'asrulji', '1000'),
(13, 'Krisna Azzahra', 'krisnazahra', '12345', '983.jpg', 'azzahrakrisna13@gmail.com', '085341702352', 'zahrakrisna', '13000', 'azzahra.krisna', '5000', 'zahrakrisna', '3000'),
(14, 'Siti Nurmadani', 'sindy', '12345', '313.jpg', 'sitinurmadani123@gmail.com', '085314230121', 'sitinurmadani', '5000', 'sindy123', '10000', 'sitinurmadani', '4000'),
(15, 'Qiran Rajab', 'qiran', '12345', '775.jpg', 'qiranrajab123@gmail.com', '02349156314', 'qiranrajab123', '5000', 'qiranrajab', '10000', 'qiranrajab', '3000');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_notifikasi`
--

CREATE TABLE `tabel_notifikasi` (
  `id_notifikasi` int(11) NOT NULL,
  `id_project` int(11) NOT NULL,
  `id_progress` int(11) NOT NULL,
  `jenis_notif` varchar(20) NOT NULL,
  `waktu_notif` varchar(20) NOT NULL,
  `nama_brand` varchar(30) NOT NULL,
  `nama_influencer` varchar(30) NOT NULL,
  `isi_notif` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_notifikasi`
--

INSERT INTO `tabel_notifikasi` (`id_notifikasi`, `id_project`, `id_progress`, `jenis_notif`, `waktu_notif`, `nama_brand`, `nama_influencer`, `isi_notif`) VALUES
(203, 0, 193, 'notif_brand', '16-May-2025 08:05:46', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo PT.Cinepoli, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Film Komang 2.'),
(204, 0, 193, 'notif_influencer', '16-May-2025 08:06:33', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo Vannes Chriz, brand PT.Cinepoli telah mengubah status project Promosi Film Komang 2 menjadi ACC'),
(205, 0, 193, 'notif_influencer', '16-May-2025 08:06:57', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo Vannes Chriz, brand PT.Cinepoli telah mengubah pengerjaan project Promosi Film Komang 2 menjadi Dibayar'),
(206, 0, 194, 'notif_brand', '16-May-2025 08:10:39', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo PT.Cinepoli, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Film Jumbo.'),
(207, 0, 194, 'notif_influencer', '16-May-2025 08:17:17', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo Vannes Chriz, brand PT.Cinepoli telah mengubah status project Promosi Film Jumbo menjadi Revisi'),
(208, 0, 195, 'notif_brand', '16-May-2025 08:26:53', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo PT.Cinepoli, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Film Jumbo.'),
(209, 0, 195, 'notif_influencer', '16-May-2025 08:27:57', 'PT.Cinepoli', 'Vannes Chriz', 'Hallo Vannes Chriz, brand PT.Cinepoli telah mengubah status project Promosi Film Jumbo menjadi ACC'),
(210, 0, 196, 'notif_brand', '16-May-2025 08:40:21', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project   ih ih ngntuk y.'),
(211, 0, 197, 'notif_brand', '24-May-2025 00:38:46', 'Soju Jinro', 'Vannes Chriz', 'Hallo Soju Jinro, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosikan Produk Soju Jinro Melalui Instagram Reels.'),
(212, 0, 198, 'notif_brand', '24-May-2025 00:42:35', 'Soju Jinro', 'Vannes Chriz', 'Hallo Soju Jinro, influencer atas nama Vannes Chriz mengajukan pengerjaan project  Promosikan Produk Soju Jinro Melalui Foto Feed Instagram.'),
(213, 0, 199, 'notif_brand', '24-May-2025 00:42:46', 'Soju Jinro', 'Vannes Chriz', 'Hallo Soju Jinro, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosikan Produk Soju Jinro melalui Story Instagram.'),
(214, 0, 199, 'notif_influencer', '25-May-2025 20:22:53', 'Soju Jinro', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Soju Jinro telah mengubah status project Promosikan Produk Soju Jinro melalui Story Instagram menjadi ACC'),
(215, 0, 199, 'notif_influencer', '25-May-2025 20:42:19', 'Soju Jinro', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Soju Jinro telah mengubah pengerjaan project Promosikan Produk Soju Jinro melalui Story Instagram menjadi Dibayar'),
(216, 0, 0, 'notif_influencer', '', '', '', 'Hallo , brand  telah mengubah status project  menjadi '),
(217, 0, 191, 'notif_influencer', '16-Jun-2025 06:39:30', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Cas Laptop menjadi Sedang Dikerjakan'),
(218, 0, 191, 'notif_influencer', '16-Jun-2025 06:39:37', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Cas Laptop menjadi Sedang Dikerjakan'),
(219, 0, 201, 'notif_brand', '20-Jun-2025 09:14:51', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Buat Banner.'),
(220, 0, 202, 'notif_brand', '20-Jun-2025 09:17:36', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Buat Banner.'),
(221, 0, 202, 'notif_influencer', '20-Jun-2025 09:18:48', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Buat Banner menjadi Ditolak'),
(222, 0, 203, 'notif_brand', '20-Jun-2025 09:21:25', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Buat Banner.'),
(223, 0, 203, 'notif_influencer', '20-Jun-2025 09:23:37', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Buat Banner menjadi Revisi'),
(224, 0, 203, 'notif_influencer', '20-Jun-2025 09:23:52', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Buat Banner menjadi ACC'),
(225, 0, 203, 'notif_influencer', '20-Jun-2025 09:24:21', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah pengerjaan project Buat Banner menjadi Dibayar'),
(226, 0, 204, 'notif_brand', '24-Jun-2025 20:16:52', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(227, 0, 204, 'notif_influencer', '24-Jun-2025 20:20:10', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi Revisi'),
(228, 0, 204, 'notif_influencer', '24-Jun-2025 20:20:28', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi ACC'),
(229, 0, 204, 'notif_influencer', '24-Jun-2025 20:21:43', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah pengerjaan project Promosi Baju menjadi Dibayar'),
(230, 0, 204, 'notif_influencer', '24-Jun-2025 20:23:52', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi Revisi'),
(231, 0, 204, 'notif_influencer', '24-Jun-2025 20:27:23', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi ACC'),
(232, 0, 204, 'notif_influencer', '24-Jun-2025 20:29:23', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah pengerjaan project Promosi Baju menjadi Dibayar'),
(233, 0, 205, 'notif_brand', '24-Jun-2025 22:27:09', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Video Barber.'),
(234, 0, 206, 'notif_brand', '24-Jun-2025 22:28:04', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Video Barber.'),
(235, 0, 207, 'notif_brand', '24-Jun-2025 22:29:03', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project promosi.'),
(236, 0, 208, 'notif_brand', '24-Jun-2025 22:35:58', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Video Barber.'),
(237, 0, 209, 'notif_brand', '24-Jun-2025 22:36:46', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(238, 0, 210, 'notif_brand', '24-Jun-2025 22:40:32', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(239, 0, 211, 'notif_brand', '24-Jun-2025 22:40:48', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(240, 0, 212, 'notif_brand', '24-Jun-2025 22:41:55', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(241, 0, 213, 'notif_brand', '24-Jun-2025 22:42:28', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Baju.'),
(242, 0, 213, 'notif_influencer', '24-Jun-2025 22:43:45', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi Revisi'),
(243, 0, 213, 'notif_influencer', '24-Jun-2025 22:45:11', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Baju menjadi ACC'),
(244, 0, 213, 'notif_influencer', '24-Jun-2025 22:46:37', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah pengerjaan project Promosi Baju menjadi Dibayar'),
(245, 0, 214, 'notif_brand', '24-Jun-2025 22:47:49', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project   ih ih ngntuk y.'),
(246, 0, 215, 'notif_brand', '24-Jun-2025 22:59:10', 'AXIS', 'Hesti', 'Hallo AXIS, influencer atas nama Hesti mengajukan pengerjaan project   ih ih ngntuk y.'),
(247, 0, 216, 'notif_brand', '25-Jun-2025 07:43:45', 'Soju Jinro', 'Vannes Chriz', 'Hallo Soju Jinro, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Product Jinro Soju.'),
(248, 0, 216, 'notif_influencer', '25-Jun-2025 07:46:49', 'Soju Jinro', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Soju Jinro telah mengubah status project Promosi Product Jinro Soju menjadi ACC'),
(249, 0, 216, 'notif_influencer', '25-Jun-2025 07:47:10', 'Soju Jinro', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Soju Jinro telah mengubah status project Promosi Product Jinro Soju menjadi ACC'),
(250, 0, 217, 'notif_brand', '25-Jun-2025 07:58:01', 'DJOY', 'Vannes Chriz', 'Hallo DJOY, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Produk Djoy Varian Bluberry Groove.'),
(251, 0, 217, 'notif_influencer', '25-Jun-2025 08:00:02', 'DJOY', 'Vannes Chriz', 'Hallo Vannes Chriz, brand DJOY telah mengubah status project Promosi Produk Djoy Varian Bluberry Groove menjadi ACC'),
(252, 0, 218, 'notif_brand', '25-Jun-2025 10:52:33', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Paket Data AXIS.'),
(253, 0, 218, 'notif_influencer', '25-Jun-2025 10:54:17', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Promosi Paket Data AXIS menjadi ACC'),
(254, 0, 219, 'notif_brand', '25-Jun-2025 11:06:33', 'Depts Goat', 'Vannes Chriz', 'Hallo Depts Goat, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Liquid Vape.'),
(255, 0, 219, 'notif_influencer', '25-Jun-2025 11:08:38', 'Depts Goat', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Depts Goat telah mengubah status project Promosi Liquid Vape menjadi ACC'),
(256, 0, 220, 'notif_brand', '25-Jun-2025 11:28:15', 'Erafone', 'Vannes Chriz', 'Hallo Erafone, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Toko Erafone.'),
(257, 0, 220, 'notif_influencer', '25-Jun-2025 11:30:20', 'Erafone', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Erafone telah mengubah status project Promosi Toko Erafone menjadi ACC'),
(258, 0, 221, 'notif_brand', '25-Jun-2025 11:34:27', 'Advan', 'Vannes Chriz', 'Hallo Advan, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop Advan.'),
(259, 0, 221, 'notif_influencer', '25-Jun-2025 11:36:40', 'Advan', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Advan telah mengubah status project Promosi Laptop Advan menjadi ACC'),
(260, 0, 221, 'notif_influencer', '25-Jun-2025 11:37:06', 'Advan', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Advan telah mengubah status project Promosi Laptop Advan menjadi ACC'),
(261, 0, 222, 'notif_brand', '25-Jun-2025 11:42:54', 'Bank DKI', 'Vannes Chriz', 'Hallo Bank DKI, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosikan Konser Pada Jakarta Vest.'),
(262, 0, 222, 'notif_influencer', '25-Jun-2025 11:44:03', 'Bank DKI', 'Vannes Chriz', 'Hallo Vannes Chriz, brand Bank DKI telah mengubah status project Promosikan Konser Pada Jakarta Vest menjadi ACC'),
(263, 0, 223, 'notif_brand', '21-Jul-2025 06:10:17', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Membuat Video Iklan 11 Menit.'),
(264, 0, 223, 'notif_influencer', '21-Jul-2025 06:11:44', 'AXIS', 'Vannes Chriz', 'Hallo Vannes Chriz, brand AXIS telah mengubah status project Membuat Video Iklan 11 Menit menjadi Revisi'),
(265, 0, 224, 'notif_brand', '21-Jul-2025 07:17:35', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop 1.'),
(266, 0, 225, 'notif_brand', '21-Jul-2025 08:05:45', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop 1.'),
(267, 0, 226, 'notif_brand', '22-Jul-2025 05:38:22', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop 1.'),
(268, 0, 227, 'notif_brand', '22-Jul-2025 05:58:45', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop 1.'),
(269, 0, 228, 'notif_brand', '22-Jul-2025 06:03:24', 'AXIS', 'Vannes Chriz', 'Hallo AXIS, influencer atas nama Vannes Chriz mengajukan pengerjaan project Promosi Laptop 11.');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_progress_project`
--

CREATE TABLE `tabel_progress_project` (
  `id_progress` int(11) NOT NULL,
  `waktu_pengajuan` varchar(20) NOT NULL,
  `waktu_posting` varchar(20) NOT NULL,
  `waktu_pembayaran` varchar(20) NOT NULL,
  `waktu_pengeditan` varchar(20) NOT NULL,
  `logo_brand` varchar(30) NOT NULL,
  `nama_brand` varchar(30) NOT NULL,
  `nama_project` varchar(150) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `lama_kontrak` varchar(20) NOT NULL,
  `kriteria_project` text NOT NULL,
  `gaji_influencer` varchar(30) NOT NULL,
  `nama_influencer` varchar(30) NOT NULL,
  `link_draft` text NOT NULL,
  `link_bukti_post` text NOT NULL,
  `revisi` varchar(200) NOT NULL,
  `status_project` varchar(20) NOT NULL,
  `nomor_rekening` varchar(20) NOT NULL,
  `struk_bukti_pembayaran` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_progress_project`
--

INSERT INTO `tabel_progress_project` (`id_progress`, `waktu_pengajuan`, `waktu_posting`, `waktu_pembayaran`, `waktu_pengeditan`, `logo_brand`, `nama_brand`, `nama_project`, `kategori`, `lama_kontrak`, `kriteria_project`, `gaji_influencer`, `nama_influencer`, `link_draft`, `link_bukti_post`, `revisi`, `status_project`, `nomor_rekening`, `struk_bukti_pembayaran`) VALUES
(216, '25-Jun-2025 07:43:45', '25-Jun-2025 07:35:26', '', '25-Jun-2025 07:47:10', '991.jpg', 'Soju Jinro', 'Promosi Product Jinro Soju', 'Food & Drink', '1 Bulan', '1.  Buat Video reel instagram sambil mempromosikan product soju jinro varian apapun\r\n2. Tidak menggunakan VO tapi menggunakan Lagu yang sudah ada di platform\r\n3. di edit sebagus mungkin dan kualitas HD\r\n4. Menggunakan hastag #JinroID #HiteJinroID dan mention @jinro_id', '2000000', 'Vannes Chriz', 'www.instagram.com/reel/C-mUIOMSsCt/', 'www.instagram.com/reel/C-mUIOMSsCt/', '', 'ACC', '082293961424', ''),
(217, '25-Jun-2025 07:58:01', '25-Jun-2025 07:54:27', '', '25-Jun-2025 08:00:02', '655.jpg', 'DJOY', 'Promosi Produk Djoy Varian Bluberry Groove', 'Electronic', '3 Bulan', '\r\n1. Membuat 1x Instagram reels sambil mempromosikan Produk DJOY \r\n2. Menggunakan Voice Over dan Sound yang lagi Viral\r\n3. Hastag dan Caption Menarik\r\n4. Mention akun @djoy9.id\r\n5. menggunakan hastag #BetterTasteBetterDeals', '3000000', 'Vannes Chriz', 'www.instagram.com/reel/DIWAlj2SDG4/', 'www.instagram.com/reel/DIWAlj2SDG4/', '', 'ACC', '', ''),
(218, '25-Jun-2025 10:52:33', '25-Jun-2025 10:50:22', '', '25-Jun-2025 10:54:17', '34.png', 'AXIS', 'Promosi Paket Data AXIS', 'Electronic', '1 Bulan', '\r\n1. membuat 1x video reels instagram \r\n2. menggunakan VO dan Backsound\r\n3. Video di edit semenarik mungkin dan jelas\r\n4. mention akun @axis.id', '2500000', 'Vannes Chriz', 'www.instagram.com/reel/DF7M8Q2SnbB/', 'www.instagram.com/reel/DF7M8Q2SnbB/', '', 'ACC', '', ''),
(219, '25-Jun-2025 11:06:33', '25-Jun-2025 11:06:18', '', '25-Jun-2025 11:08:38', '540.png', 'Depts Goat', 'Promosi Liquid Vape', 'Electronic', '2 Bulan', '\r\n1. Buat 1x Video reels instagram promosi liquid\r\n2.  produk dapat di temukan di Hypermart\r\n3. Tidak menggunakan Voice Over\r\n4. Mention akun @sev7nliquid', '1500000', 'Vannes Chriz', 'www.instagram.com/reel/DKJ7EGey1sm/', 'www.instagram.com/reel/DKJ7EGey1sm/', '', 'ACC', '', ''),
(220, '25-Jun-2025 11:28:15', '25-Jun-2025 11:27:29', '', '25-Jun-2025 11:30:20', '320.jpg', 'Erafone', 'Promosi Toko Erafone', 'Electronic', '3 Bulan', '\r\n1. buat 1x foto instagram promosi toko erafone\r\n2. mention akun  erafone', '1500000', 'Vannes Chriz', 'www.instagram.com/p/C9PcsRjyMyp/', 'www.instagram.com/p/C9PcsRjyMyp/', '', 'ACC', '', ''),
(221, '25-Jun-2025 11:34:27', '25-Jun-2025 11:34:00', '', '25-Jun-2025 11:37:06', '713.jpg', 'Advan', 'Promosi Laptop Advan', 'Electronic', '4 Bulan', '\r\n1. Posting video yang di berikan oleh brand dan copy captionnya pada DM instagram\r\n2. mention akun @advanindonesia', '500000', 'Vannes Chriz', 'www.instagram.com/reel/CvYaG9msZS/', 'www.instagram.com/reel/CvYaG9msZS/', '', 'ACC', '', ''),
(222, '25-Jun-2025 11:42:54', '25-Jun-2025 11:42:34', '', '25-Jun-2025 11:44:03', '342.jpg', 'Bank DKI', 'Promosikan Konser Pada Jakarta Vest', 'Electronic', '1 Bulan', '\r\n1. VIdeo dan foto dikirimkan oleh brand pada DM langsung instagram\r\n2. Mention akun @bankdki', '100000', 'Vannes Chriz', 'www.instagram.com/p/DLJxrsiy5FK/', 'www.instagram.com/p/DLJxrsiy5FK/', '', 'ACC', '', ''),
(223, '21-Jul-2025 06:10:17', '21-Jul-2025 05:46:59', '', '21-Jul-2025 06:11:44', '557.jpg', 'AXIS', 'Membuat Video Iklan 11 Menit', 'Fashion & Accessories', '2 Minggu', '1. video menarik\r\n2. blabla', '0', 'Vannes Chriz', 'https://www.instagram.com/reel/DF7M8Q2SnbB/?igsh=bjZnejA1cG5xejY2', 'https://www.instagram.com/reel/DF7M8Q2SnbB/?igsh=bjZnejA1cG5xejY2', '1. bla\r\n2.bla', 'Revisi', '', ''),
(228, '22-Jul-2025 06:03:24', '21-Jul-2025 06:42:21', '', '22-Jul-2025 06:10:37', '1666498910_1753051341.jpeg', 'AXIS', 'Promosi Laptop 11', 'Fashion & Accessories', '1 Minggu', '\n1. HK\n2. jj', '0', 'Vannes Chriz', 'yt', 'yt', 'j\nk', 'Dibayar', '123', '1224706042_1753135837.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_project`
--

CREATE TABLE `tabel_project` (
  `id_project` int(11) NOT NULL,
  `waktu_posting` varchar(20) NOT NULL,
  `logo_brand` varchar(30) NOT NULL,
  `nama_brand` varchar(30) NOT NULL,
  `nama_project` varchar(150) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `lama_kontrak` varchar(20) NOT NULL,
  `kriteria_project` text NOT NULL,
  `gaji_influencer` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_project`
--

INSERT INTO `tabel_project` (`id_project`, `waktu_posting`, `logo_brand`, `nama_brand`, `nama_project`, `kategori`, `lama_kontrak`, `kriteria_project`, `gaji_influencer`) VALUES
(310, '25-Jun-2025 07:38:51', '578.jpg', 'Soju Jinro', 'Promosi Product Jinro Soju', 'Food & Drink', '1 Minggu', '\r\n1. Membuat Instagram Story sambil memegang Produk soju jinro\r\n2. Foto harus berkualitas HD\r\n3. Mention akun @Jinro_id dan @jinro_Global', '500000'),
(311, '25-Jun-2025 07:42:52', '101.jpg', 'Soju Jinro', 'Promosi Product Jinro Soju', 'Food & Drink', '1 Bulan', '1. Membuat 1x Instagram Foto Sambil memegang Produk Jinro Soju\r\n2. Menggunakan Caption yang bagus dan menarik\r\n3. Mention akun @Jinro_id dan @jinro_global ', '1500000'),
(313, '25-Jun-2025 07:57:11', '309.jpg', 'DJOY', 'Promosi Produk Djoy All Varian', 'Electronic', '1 Minggu', '1. Membuat 1x Instagram Foto sambil mempromosikan Produk DJOY  Semua Varian\r\n2. Hastag dan Caption Menarik\r\n3. Mention akun @djoy9.id\r\n4. menggunakan hastag #BetterTasteBetterDeals', '1500000'),
(314, '25-Jun-2025 08:06:57', '494.jpg', 'DJOY', 'Promosi Produk Djoy Varian Strawberry Zest', 'Electronic', '1 Bulan', '\r\n1. Membuat 1x Foto Instagram Story Menggunakan Produk Djoy Strawberry Zest \r\n2. Menggunakan Caption Yang Menarik\r\n3. Mention Akun @djoy9.id \r\n4. Menggunakan Hastag #BetterTasteBetterDeals', '500000'),
(315, '25-Jun-2025 08:13:29', '468.jpg', 'Glad 2 Glow', 'Promosi 1 Paket Produk Retinol Glad2Glow', 'Beauty', '4 Bulan', '\r\n1. Membuat 1x instagram Reels dan mempromosikan Produk 1 paket Retinol Glad2Glow\r\n2. Menggunakan Caption Semenarik mungkin \r\n3.  Menggunakan Voice Over dan Backsound\r\n4. Mention Akun Ig @glad2glow', '3500000'),
(317, '25-Jun-2025 10:58:35', '240.png', 'AXIS', 'Promosi Paket Kartu Perdana AXIS', 'Electronic', '1 Bulan', '\r\n1. membuat 1x foto instagram\r\n2. menampilkan kartu perdana axis dengan jelas pada foto\r\n3. membuat foto semenarik mungkin\r\n4. mention akun @axis.id', '1000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tabel_akun_brand`
--
ALTER TABLE `tabel_akun_brand`
  ADD PRIMARY KEY (`id_brand`);

--
-- Indexes for table `tabel_akun_influencer`
--
ALTER TABLE `tabel_akun_influencer`
  ADD PRIMARY KEY (`id_influencer`);

--
-- Indexes for table `tabel_notifikasi`
--
ALTER TABLE `tabel_notifikasi`
  ADD PRIMARY KEY (`id_notifikasi`);

--
-- Indexes for table `tabel_progress_project`
--
ALTER TABLE `tabel_progress_project`
  ADD PRIMARY KEY (`id_progress`);

--
-- Indexes for table `tabel_project`
--
ALTER TABLE `tabel_project`
  ADD PRIMARY KEY (`id_project`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tabel_akun_brand`
--
ALTER TABLE `tabel_akun_brand`
  MODIFY `id_brand` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `tabel_akun_influencer`
--
ALTER TABLE `tabel_akun_influencer`
  MODIFY `id_influencer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `tabel_notifikasi`
--
ALTER TABLE `tabel_notifikasi`
  MODIFY `id_notifikasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=270;

--
-- AUTO_INCREMENT for table `tabel_progress_project`
--
ALTER TABLE `tabel_progress_project`
  MODIFY `id_progress` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=229;

--
-- AUTO_INCREMENT for table `tabel_project`
--
ALTER TABLE `tabel_project`
  MODIFY `id_project` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=328;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
