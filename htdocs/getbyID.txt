<?php
    
    include_once 'config.php';
    
	$exerciseName = "mason";

	$testObject = new DbConnect();

	$testObject->__construct();
	$testObject->_post($exerciseName);

    class DbConnect{
        
        private $connect;
		private $db_table = "exercises";

        public function __construct(){
            
            $this->connect = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
            
            if (mysqli_connect_errno($this->connect)){
                echo "Unable to connect to MySQL Database: " . mysqli_connect_error();
            }
        }
        
        public function getDb(){
            return $this->connect;
        }
		
		public function _post($exerciseName){
			#$query = "insert into ".$this->db_table." (exerciseID, exerciseName) values (NULL,'$exerciseName')";
			$rID = $_GET["rID"];
			$query = "SELECT workouts.routineID, workouts.exerciseID, routines.routineName, exercises.exerciseName, exercises.defaultSets, exercises.defaultReps FROM workouts INNER JOIN exercises ON exercises.exerciseID = workouts.exerciseID INNER JOIN routines ON routines.routineID = workouts.routineID WHERE workouts.routineID='$rID'";
			$result = mysqli_query($this->getDb(), $query);
			$i=1;
			while ($row = $result->fetch_assoc()) {
				if($i<2){
					echo "Workout: ".$row['routineName']."<br>";
					$i++;
				}
				echo $row['exerciseName']." Sets:".$row['defaultSets']." Reps:".$row['defaultReps']."<br>";
			}
			/*if($inserted == 1){
                    
                    $json['success'] = 1;
                    $json['message'] = "Successfully registered the user";
					echo "connected to database";
                    
                }else{
                    
                    $json['success'] = 0;
                    $json['message'] = "Error in registering. Probably the username/email already exists";
                    
                }
                */
                mysqli_close($this->getDb());
		}
    }
?>

