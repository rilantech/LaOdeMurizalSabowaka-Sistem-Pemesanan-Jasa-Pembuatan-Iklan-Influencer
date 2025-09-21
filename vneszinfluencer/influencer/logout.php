<?php
    session_start();    
    unset($_SESSION['is_logged_in_influencer']);
    header('location:login.php');
?>