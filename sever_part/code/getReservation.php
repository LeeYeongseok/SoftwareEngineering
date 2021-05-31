<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$hotelName = isset($_POST["hotelname"]) ? $_POST["hotelname"] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($hotelName != "" ){ 

    $query = "SELECT * FROM reservation WHERE hotel_name = '$hotelName' AND status = 0;";

    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    $i = 0;
    $data = array();
    while($i < count($result)){
        $row = $result[$i];
        array_push($data, 
            array('reserNum'=>$row["reservation_num"],
            'roomID'=>$row["roomID"],
            'checkin'=>$row["startTime"],
            'checkout'=>$row["endTime"],
            'meal'=>$row["isMeal"],
            'NumofPeople'=>$row["numGuests"]
            ));
    $i++;
    }

    if (!$android) {
        echo "<pre>"; 
        print_r($data); 
        echo '</pre>';
    }else
    {
        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
        echo $json;
    }
    
}
else {
    echo "검색할 지역을 입력하세요 ";
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
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>