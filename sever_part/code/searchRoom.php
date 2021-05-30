<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$date1  = isset($_POST['date1']) ? $_POST['date1'] : '';
$date2  = isset($_POST['date2']) ? $_POST['date2'] : '';
$loc = isset($_POST['location']) ? $_POST['location'] : '';
$max = isset($_POST["maxGuest"]) ? $_POST["maxGuest"] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($loc != "" ){ 

    $query = "SELECT hotelID FROM hotelInfo WHERE location = '$loc';";

    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    if(!$result){
        echo 'MySQL Error: ';
        exit;
    }
    $hotelId_array = array();
   
    for($i = 0; $i < count($result); $i++) { 
        $hotelId_array[] = $result[$i]['hotelID'];
    }

#    print_r($hotelId_array);

    $roomcode_array = array();
    $i = 0;
    while ($i < count($hotelId_array)){ 

        $query = "SELECT * FROM roomInfo WHERE hotelID = $hotelId_array[$i] AND maxGuests >= $max;";
        $stmt = $con->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        if(!$result){
            echo 'MySQL Error: ';
            exit;
        }

        for($j = 0; $j < count($result); $j++) { 
            $roomcode_array[] = $result[$j]['roomID'];
        }
        $i++;
    }
    
    #print_r($roomcode_array);

    $roomcode_reserved = array();

    $i = 0 ;
    while ($i < count($roomcode_array)){ 

        $query = "SELECT * FROM reservation WHERE roomID = $roomcode_array[$i] AND startTime <= str_to_date('$date2','%Y-%m-%d') AND endTime > str_to_date('$date1','%Y-%m-%d');";
        $stmt = $con->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

        for($j = 0; $j < count($result); $j++) { 
            $roomcode_reserved[] = $result[$j]['roomID'];
        }
        $i++;
    }
    
    $roomcode_result = array_diff($roomcode_array, $roomcode_reserved);
    
    $roomcode_result = array_values($roomcode_result);
    
    $data = array(); 
    $i = 0;
    while ($i < count($roomcode_result)){ 
        $query = "SELECT * FROM roomInfo WHERE roomID = $roomcode_result[$i];";
        $stmt = $con->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo '</pre>';
        $row = $result[0];
        array_push($data, 
        array('hotelID'=>$row["hotelID"],
        'roomID'=>$row["roomID"],
        'costPerDay'=>$row["costPerDay"]
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
         <p><label>시작날짜 : <input type="date" name="date1"></label></p>
         <p><label>끝날짜 : <input type="date" name="date2"></label></p>
         <p><label>위치 : <input type="text" name="location"></label></p>
         <p><label>인원수 : <input type="text" name="maxGuest"></label></p>
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>