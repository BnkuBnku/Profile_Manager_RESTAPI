<?php
$DB = "db_exer12";

$id = $_POST['id'];

$conn = mysqli_connect('localhost', 'root', '', $DB);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

$js = '{ "Profile" : [ ';

$sql = 'CALL CheckCount('. $id .')';  

$result = mysqli_query($conn, $sql);

while($row = mysqli_fetch_assoc($result)){
	$js .= '{	"id" : "' . $row['id'] .
			'", "name" : "' . $row['name'] .
			'", "age" : "' . $row['age'] .
			'", "gender" : "' . $row['gender'] .'"}, ';
}

$js .= substr($js, -1) . '] }';
echo $js;
mysqli_close($conn)	
?>