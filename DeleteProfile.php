<?php
$DB = "db_exer12";

$id = $_POST['id'];


$conn = mysqli_connect('localhost', 'root', '', $DB);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

$sql = 'CALL DeleteProfile('. $id .')'; 

if (mysqli_query($conn, $sql)) {
  echo "Profile deleted successfully";
} else {
  echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>