<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$hotelname  = isset($_POST['hotelname']) ? $_POST['hotelname'] : '';
$roomID = isset($_POST['roomID']) ? $_POST['roomID'] : '';


if ($hotelname != "" AND $roomID != ""){ 

    $query = "SELECT * FROM hotelInfo WHERE name = '$hotelname';";

    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if(!$result){
        echo 'no hotel';
        exit;
    }
    $hotelId = $result[0]['hotelID'];

    $query = "DELETE FROM roomInfo WHERE hotelID = $hotelId AND roomID = $roomID;";

    $stmt = $con->prepare($query);
    $stmt->execute();

}
else {
    echo "삭제할 방 정보를 입력하세요";
}

?>



<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         <p><label>해당 호텔이름 : <input type="text" name="hotelname"></label></p>
         <p><label>해당 방 호수 : <input type="text" name="roomID"></label></p>
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>