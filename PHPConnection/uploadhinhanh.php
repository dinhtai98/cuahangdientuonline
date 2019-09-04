<?php
     include "connect.php";
    if($conn){
        $img = $_POST['img'];
        $tenhinhanh = $_POST['tenhinhanh'];
        $sql = "INSERT INTO hinhanh(id,tenhinhanh) VALUE(null,'$tenhinhanh')";
        $upload_path = "../public_html/image/$tenhinhanh.jpg";
            if (strlen($tenhinhanh) > 0) {
            if(mysqli_query($conn,$sql)){
                file_put_contents($upload_path,base64_decode($img));
                echo "thành công";
            }
            else
            {echo "thất bại";}
        }
    }
?>