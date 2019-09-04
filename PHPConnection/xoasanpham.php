<?php
    include "connect.php";
    $masanpham = $_POST['id'];
    $query = "DELETE FROM sanpham WHERE id = '$masanpham'";
    $data = mysqli_query($conn,$query);
?>