<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$hotelname  = isset($_POST['hotelID']) ? $_POST['hotelID'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($hotelname != "" ){ 

    $query = "SELECT * FROM hotelInfo WHERE name = '$hotelname';";

    $stmt = $con->prepare($query);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    if(!$result){
        $data=FALSE;
    }
    else{
        $data=TRUE;
    }
    if (!$android) {
        echo "<pre>"; 
        print_r($data?'true':'false'); 
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
         <p><label>호텔이름 : <input type="hotelID" name="hotelID"></label></p>
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>