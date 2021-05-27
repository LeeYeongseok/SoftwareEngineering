<meta charset="utf-8"> 
<?php 
$host = 'qmdlrhdfyd.synology.me:3307';
$user = 'hotelDB88k';
$pw = 'bC4-5F2GDq';
$dbName = 'hotelDB';
$mysqli = new mysqli($host, $user, $pw, $dbName);


if( $mysqli -> connect_errno ) {
    echo "Failed to connect to MySql: " . $mysqli -> connect_error ;
    exit();
}
$query = "SELECT hotelID FROM hotelInfo WHERE location={$_POST['location']}";
$result = mysqli_query($mysqli, $query);

if(!$result){
    echo 'MySQL Error: ' . mysqli_error($mysqli);
    exit;
}

$row = mysqli_fetch_assoc($result);

while ($row = mysqli_fetch_assoc($result)) { 
    print_r($row); 
}

mysql_close($mysqli); 
?>
