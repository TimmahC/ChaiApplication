
<?php
//phpinfo();
//exit;
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");
header('content-type: application/json; charset=utf-8');

$servername = "localhost";
$database = "chai"; //YOUR DATABASE NAME
$user = "root"; 
$pass = "password"; //FILL IN PASSWORD

try{
	
	$conn = mysql_connect($servername, $user, $pass);
}
catch( Exception $e ){
	echo "Exception: ". $e->getMessage();
}

if( $conn ){
	mysql_select_db($database);
	$sql = "SELECT *, avg(rating) as average FROM chai.restaurant
left JOIN chai.reviews
on restaurant.id = reviews.restaurantID
group by id";
	$result = mysql_query($sql);
	$jsonData = array();
	while ($array = mysql_fetch_assoc($result)) {
		$jsonData[] = $array;
	}
	echo json_encode($jsonData);
	mysql_close($conn);
}
else{
	echo mysql_conn_error()."<br>";
	echo mysql_conn_errormsg()."<br>";
	echo "Connection failed.<br>";
}
?>
