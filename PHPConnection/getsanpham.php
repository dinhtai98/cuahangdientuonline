<?php
    include "connect.php";
    $page = $_GET['page'];
    $idsp = 1;
    $space = 5;
    $limit = ($page - 1)* $space;
    $mangsanpham = array();
    $query = "SELECT * FROM sanpham WHERE idloaisanpham = '1' LIMIT $limit,$space";
    $data = mysqli_query($conn,$query);
    while ($row = mysqli_fetch_assoc($data)) {
        array_push($mangsanpham, new Sanpham(
        $row['id'],
        $row['tensanpham'],
        $row['giasanpham'],
        $row['hinhanhsanpham'],
        $row['motasanpham'],
        $row['idloaisanpham']));
    }
    echo json_encode($mangsanpham);
    class Sanpham{
        function Sanpham($id,$tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idloaisanpham){
            $this->id=$id;
            $this->tensanpham=$tensanpham;
            $this->giasanpham=$giasanpham;
            $this->hinhanhsanpham=$hinhanhsanpham;
            $this->motasanpham=$motasanpham;
            $this->idloaisanpham=$idloaisanpham;
        }
    }
?>