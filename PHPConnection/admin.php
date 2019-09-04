<?php
    include "connect.php";
    $query = "SELECT * FROM admin";
    $data = mysqli_query($conn,$query);
    $mangadmin = array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($mangadmin,new Admin(
        $row['username'],
        $row['password']));
    }
    echo json_encode($mangadmin);
    class Admin{
        function Admin($username,$password){
            $this->username = $username;
            $this->password = $password;
        }
    }
?>