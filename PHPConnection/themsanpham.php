
<?php
    include "connect.php";
    $tensanpham = $_POST['tensanpham'];
    $giasanpham = $_POST['giasanpham'];
    $hinhanhsanpham = $_POST['hinhanhsanpham'];
    $motasanpham = $_POST['motasanpham'];
    $idloaisanpham = $_POST['idloaisanpham'];
    if(strlen($tensanpham) > 0 && strlen($giasanpham) > 0 && strlen($hinhanhsanpham) > 0 && strlen($motasanpham) > 0 && strlen($idloaisanpham) > 0){
        $query = "INSERT INTO sanpham(id,tensanpham,giasanpham,hinhanhsanpham,motasanpham,idloaisanpham) 
        VALUE (null,'$tensanpham','$giasanpham','$hinhanhsanpham','$motasanpham','$idloaisanpham')";
        if(mysqli_query($conn,$query)){
            $spm = $conn->insert_id;
            echo $spm;
        }else {
            echo "Thất bại";
        }
    }else{
        echo "Bạn hãy kiểm tra lại dữ liệu";
    }
?>