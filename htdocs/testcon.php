<?php
// Provide more information if something is wrong with your code:
error_reporting(-1);
ini_set('display_errors', 'On');
// Settings used to connect to the database:
$db_host = 'localhost';	//hostname for database
$db_user = 'root';	//username for database
$db_pass = '';		//password for database
$db_name = 'diss'; //Database i wish to connect to
$conn = new mysqli($db_host, $db_user, $db_pass, $db_name);
if ($conn->connect_errno) echo "failed to connect to database";
?>
