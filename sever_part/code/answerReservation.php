<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$hotelName = isset($_POST["hotelname"]) ? $_POST["hotelname"] : '';
$num = isset($_POST["reservationNum"]) ? $_POST["reservationNum"] : '';
$status = isset($_POST["status"]) ? $_POST["status"] : '';

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($hotelName != "" AND $num != "" AND $status != ""){ 

    $query = "UPDATE reservation SET status = $status WHERE hotel_name = '$hotelName' AND reservation_num = '$num' AND status = 0;";

    $stmt = $con->prepare($query);
    $stmt->execute();
}
else {
    echo "예약의 결과를 입력하세요 ";
}

?>



<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         <p><label>호텔이름 : <input type="text" name="hotelname"></label></p>
         <p><label>예약번호 : <input type="text" name="reservationNum"></label></p>
         <p><label>confirm : <input type="text" name="status"></label></p>
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>