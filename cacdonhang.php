<?php
    include "connect.php";
    $kt = $_POST['tenkh'];
    // $khachhang = array();
    $idkhachhang=array();
    $query = "SELECT * FROM donhang WHERE tenkhachhang = '$kt'";
    $data = mysqli_query($conn,$query);
    while ($row = mysqli_fetch_assoc($data)) {
        // array_push($khachhang, new KhachHang(
        // $row['id'],
        // $row['tenkhachhang'],
        // $row['sodienthoai'],
        // $row['email']));
        array_push($idkhachhang,$row['id']);
    }
    // echo json_encode($khachhang);
    // echo json_encode($idkhachhang);
    // class KhachHang{
    //     function KhachHang($id,$tenkhachhang,$sodienthoai,$email){
    //         $this->id=$id;
    //         $this->tenkhachhang=$tenkhachhang;
    //         $this->sodienthoai=$sodienthoai;
    //         $this->email=$email;
    //     }
    // }
    $donhang = array();
    foreach($idkhachhang as $key => $value)
    {
        $query2 = "SELECT * FROM chitietdonhang WHERE madonhang ='$value'";
        $data2 = mysqli_query($conn,$query2);
        while ($row = mysqli_fetch_assoc($data2)) {
            array_push($donhang, new DonHang(
            $row['id'],
            $row['madonhang'],
            $row['masanpham'],
            $row['tensanpham'],
            $row['giasanpham'],
            $row['soluongsanpham']));
        }
    }
    echo json_encode($donhang);
    class DonHang{
        function DonHang($id,$madonhang,$masanpham,$tensanpham,$giasanpham,$soluongsanpham){
            $this->id=$id;
            $this->madonhang=$madonhang;
            $this->masanpham=$masanpham;
            $this->tensanpham=$tensanpham;
            $this->giasanpham=$giasanpham;
            $this->soluongsanpham=$soluongsanpham;
        }
    }
?>