<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$hotelname  = isset($_POST['hotelname']) ? $_POST['hotelname'] : '';
$roomID = isset($_POST['roomID']) ? $_POST['roomID'] : '';
$price = isset($_POST['price']) ? $_POST['price'] : '';
$maxGuest = isset($_POST["maxGuest"]) ? $_POST["maxGuest"] : '';
$picture = isset($_POST["picture"]) ? $_POST["picture"] : '';
$roomtype = isset($_POST["roomtype"]) ? $_POST["roomtype"] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if ($hotelname != "" AND $roomID != "" AND $price != "" AND $maxGuest != "" AND $picture != "" AND $roomtype != ""){ 

    $query = "SELECT * FROM hotelInfo WHERE name = '$hotelname';";

    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if(!$result){
        echo 'MySQL Error: ';
        exit;
    }
    $hotelId = $result[0]['hotelID'];

    $query = "UPDATE roomInfo SET costPerday = $price, maxGuests = $maxGuest, photo_path = '$picture', roomType = '$roomtype' WHERE hotelID = $hotelId AND roomID = $roomID;";
    $stmt = $con->prepare($query);
    $stmt->execute();
}
else {
    echo "수정할 방 정보를 입력하세요";
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
         <p><label>가격 : <input type="text" name="price"></label></p>
         <p><label>최대인원수 : <input type="text" name="maxGuest"></label></p>
         <p><label>사진 링크 : <input type="text" name="picture"></label></p>
         <p><label>방타입 : <input type="text" name="roomtype"></label></p>
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>