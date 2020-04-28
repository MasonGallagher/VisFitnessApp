<?php 
    include_once 'config.php';
    include_once 'db_connect.php';
	
	
	$obj = new GetRoutines();
	$obj->_get($conn);
	
	class GetRoutines{
	
		public function _get($conn){
				$json = array();
				$rID = $_GET["rID"];
				$query = "SELECT workouts.routineID, workouts.exerciseID, routines.routineName, exercises.exerciseName, exercises.defaultSets, exercises.defaultReps
				FROM workouts INNER JOIN exercises ON exercises.exerciseID = workouts.exerciseID INNER JOIN routines ON routines.routineID = workouts.routineID WHERE workouts.routineID='$rID' ";
				$result = mysqli_query($conn->getDb(), $query);
				//get all rows as an array
				$rows = array();
				while ($row = $result->fetch_assoc()) {
					$rows[] = $row;
				}
				//encode into JSON
				$json =  json_encode($rows);
				//decode from JSON to get objects
				$items = json_decode($json);
				$group = array();
				foreach($items as $item) {
					if(!array_key_exists($item->routineID, $group)) {
						 $newObject = new stdClass();
						 // copy the item and create temp item 
						 $newObject->routineID = $item->routineID;
						 $newObject->routineName = $item->routineName;
						 $newObject->exercises = array();
						 // group the new item
						 $group[$item->routineID] = $newObject;
					}
					$object = new stdClass();
					// make a copy of the object
					$object->exerciseID = $item->exerciseID;
					$object->exerciseName = $item->exerciseName;
					$object->defaultSets = $item->defaultSets;
					$object->defaultReps = $item->defaultReps;
					$group[$item->routineID]->exercises[] = $object;
				}
				//remove keys
				$group = array_values($group);
				//convert back to JSON
				$json = json_encode($group);
				echo $json;
		
				mysqli_close($conn->getDb());
		}
	}
	
?>
