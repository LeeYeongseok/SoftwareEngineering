<?php
error_reporting(E_ALL); 
ini_set('display_errors',1); 
include('dbcon.php');

$roomID  = isset($_POST['roomID']) ? $_POST['roomID'] : '';
$img_url = isset($_POST['img_url']) ? $_POST['img_url'] : '';

if($roomID != ""){
    $query = 'SELECT * FROM roomInfo WHERE roomID = '.$roomID;


    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if(!$result){
        echo 'MySQL Error: ';
        exit;
    }

    $query = 'UPDATE roomInfo SET photo_path = "'.$img_url.'" WHERE roomID = '.$roomID;

    $stmt = $con->prepare($query);
    $stmt->execute();


}
else{
    echo "방번호를 다시 입력해주세요." ;
}

?>