<?php
    include "connect.php";
    $page = $_GET['page'];
    $space = 7;
    $limit = ($page - 1)* $space;
    $mangsanpham = array();
    $query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT $limit,$space ";
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