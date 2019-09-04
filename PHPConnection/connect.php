<?php
    $host = "localhost";
    $username = "id10418846_root";
    $password ="147asdtai";
    $database = "id10418846_thietbi";
    $conn = mysqli_connect($host,$username,$password,$database);
    mysqli_set_charset( $conn, 'utf8');
    if(!$conn){echo "không kết nối được";
    }
?>