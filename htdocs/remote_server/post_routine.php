<?php 
    include_once 'config.php';
    include_once 'db_connect.php';
	
	
	$obj = new GetRoutines();
	$obj->_get($conn);
	
	class GetRoutines{
	
		public function _get($conn){
			//get input
		    $json = file_get_contents('php://input');
			//convert to object
			$routineObject = json_decode($json);
			$rRoutineName=$routineObject->routineName;
			//query database
			$query = "INSERT INTO `routines`(`routineName`) VALUES ('$rRoutineName')";
			mysqli_query($conn->getDb(), $query);
			$rRoutineID=mysqli_insert_id($conn->getDb()); 
			//get exercises as array
			$group = array();
			foreach($routineObject->exercises as $item) {
				$rExerciseName = $item->exerciseName;
				$rDefaultSets = $item->defaultSets;
				$rDefaultReps = $item->defaultReps;
				$query = "INSERT INTO `exercises`(`exerciseName`, `defaultSets`, `defaultReps`) VALUES ('$rExerciseName','$rDefaultSets','$rDefaultReps')";
				mysqli_query($conn->getDb(), $query);
				$rExerciseID=mysqli_insert_id($conn->getDb()); 
				$query = "INSERT INTO `workouts`(`routineID`, `exerciseID`) VALUES ('$rRoutineID','$rExerciseID')";
				mysqli_query($conn->getDb(), $query);
			}
			//encrypt the id of the new routine and send it back to the client
			$salt="VIS_FIT";
			$encrypted_id = base64_encode($rRoutineID . $salt);
			print_r($encrypted_id);
			mysqli_close($conn->getDb());

		}
	}
	
?>
