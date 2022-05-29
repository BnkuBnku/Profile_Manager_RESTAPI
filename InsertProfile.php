<?php
$DB = "db_exer12";

$name = $_POST['name'];
$age = $_POST['age'];
$gender = $_POST['gender'];



$conn = mysqli_connect('localhost', 'root', '', $DB);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

$sql = 'CALL InsertProfile("'. $name .'",
						'. $age .',
						"'. $gender .'")'; 

if (mysqli_query($conn, $sql)) {
  echo "New profile created successfully";
} else {
  echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>