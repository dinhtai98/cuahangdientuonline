<?php
    include "connect.php";
    $tendn = $_POST['tendn'];
    $passmoi = $_POST['password'];
    $query = "UPDATE admin SET password='$passmoi' WHERE username = '$tendn'";
    $data = mysqli_query($conn, $query);
?>