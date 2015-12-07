
<?php
//phpinfo();
//exit;
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

$servername = "localhost";
$database = "chai"; //YOUR DATABASE NAME
$user = "root"; 
$pass = "password"; //FILL IN PASSWORD

$name = $_POST['user'];
$restaurantID = $_POST['restaurantID'];
$rating = $_POST['rating'];
try{
	
	$conn = mysql_connect($servername, $user, $pass);
}
catch( Exception $e ){
	echo "Exception: ". $e->getMessage();
}

if( $conn ){
	mysql_select_db($database);
	$sql = "INSERT INTO reviews (user, restaurantID, rating) values ('".$name."', $restaurantID, $rating)
	ON DUPLICATE KEY UPDATE rating = $rating
	";
	$result = mysql_query($sql);
	
	if(!$result)
		die('Could not enter data: ' . mysql_error());
	else
		echo "Entered data successfully";
	mysql_close($conn);
}
else{
	echo mysql_conn_error()."<br>";
	echo mysql_conn_errormsg()."<br>";
	echo "Connection failed.<br>";
}
?>
