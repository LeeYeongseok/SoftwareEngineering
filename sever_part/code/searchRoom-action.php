<meta charset="utf-8"> 
<?php 
$host = 'qmdlrhdfyd.synology.me:3307';
$user = 'hotelDB88k';
$pw = 'bC4-5F2GDq';
$dbName = 'hotelDB';
$mysqli = new mysqli($host, $user, $pw, $dbName);
/*mysqli_query($mysqli, "set session character_set_connection=utf8;");
mysqli_query($conn, "set session character_set_results=utf8;");
mysqli_query($conn, "set session character_set_client=utf8;");*/

if( $mysqli -> connect_errno ) {
    echo "Failed to connect to MySql: " . $mysqli -> connect_error ;
    exit();
}

$loc = $_POST['location'];

$query = "SELECT hotelID FROM hotelInfo WHERE location = '$loc';";

$result = mysqli_query($mysqli, $query);

if(!$result){
    echo 'MySQL Error: ' . mysqli_error($mysqli);
    exit;
}

$hotelId_array = array();

while ($row = mysqli_fetch_assoc($result)) { 
    $hotelId_array[] = $row['hotelID'];
}
/*print_r($hotelId_array);*/
echo "<br>";
$max = $_POST['maxGuest'];
$i = 0;

$roomcode_array = array();
while ($i < count($hotelId_array)){ 

    $query = "SELECT * FROM roomInfo WHERE hotelID = $hotelId_array[$i] AND maxGuests >= $max;";
    $result = mysqli_query($mysqli, $query);
    if(!$result){
        echo 'MySQL Error: ' . mysqli_error($mysqli);
        exit;
    }

    while ($row = mysqli_fetch_assoc($result)) { 
        $roomcode_array[] = $row['roomID'];
    }
    $i++;
 }
/*print_r($roomcode_array);*/
echo "<br>";
$roomcode_reserved = array();
$date1 = $_POST['date1'];
$date2 = $_POST['date2'];

$i = 0 ;
while ($i < count($roomcode_array)){ 

    $query = "SELECT * FROM reservation WHERE roomID = $roomcode_array[$i] AND startTime <= str_to_date('$date2','%Y-%m-%d') AND endTime > str_to_date('$date1','%Y-%m-%d');";
    $result = mysqli_query($mysqli, $query);
  
    if(!$result){
        echo 'MySQL Error: ' . mysqli_error($mysqli);
        exit;
    }

    while ($row = mysqli_fetch_assoc($result)) { 
        $roomcode_reserved[] = $row['roomID'];
    }
    $i++;
 }
 
$roomcode_result = array_diff($roomcode_array, $roomcode_reserved);
 
$roomcode_result = array_values($roomcode_result);

$i = 0;
while ($i < count($roomcode_result)){ 
    $query = "SELECT * FROM roomInfo WHERE roomID = $roomcode_result[$i];";
    $result = mysqli_query($mysqli, $query);
    while($roominfo = mysqli_fetch_assoc($result)){
        print_r($roominfo);
    }
    echo "</br>";
    $i++;
}
?>