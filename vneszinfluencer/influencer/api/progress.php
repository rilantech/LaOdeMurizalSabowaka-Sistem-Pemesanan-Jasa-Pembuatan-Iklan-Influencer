<?php

  include '../../koneksidb.php';

  $sql = "SELECT * FROM tabel_progress_project WHERE nama_influencer='".$_POST['nama_influencer']."' order by id_progress desc";
  $query = mysqli_query($db, $sql);

    $list_data = array();
    while($data = mysqli_fetch_assoc($query)){
        $list_data[] = array(
            'id' => $data['id_progress'],
            'waktu_pengajuan'=> $data['waktu_pengajuan'],
            'waktu_posting'=> $data['waktu_posting'],
            'waktu_pembayaran'=> $data['waktu_pembayaran'],
            'logo_brand'=> $data['logo_brand'],
            'nama_brand'=> $data['nama_brand'],
            'nama_project'=> $data['nama_project'],
            'kategori'=> $data['kategori'],
            'kriteria_project'=> $data['kriteria_project'],
            'gaji_influencer'=> $data['gaji_influencer'],
            'lama_kontrak'=> $data['lama_kontrak'],
            'nama_influencer'=> $data['nama_influencer'],
            'link_draft'=> $data['link_draft'],
            'link_bukti_post'=> $data['link_bukti_post'],
            'revisi'=> $data['revisi'],
            'status_project'=> $data['status_project'],
            'nomor_rekening'=> $data['nomor_rekening'],
            'struk_bukti_pembayaran'=> $data['struk_bukti_pembayaran']
        );
    }

    echo json_encode(array(
        'data' => $list_data
    ));
  
  ?>