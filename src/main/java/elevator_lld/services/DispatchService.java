package elevator_lld.services;

import elevator_lld.models.Floor;
import elevator_lld.models.State;

public class DispatchService {

  public String callElevator(Floor floor, State state) throws Exception {
    if (state.equals(State.IDLE) || state.equals(State.NOT_WORKING)) {
      throw new Exception("Invalid state selected");
    }

    // get the list of elevators in building

    // UP clicked
    // if going up, get the list of elevators that have upMax top less than currect floor
    // in eligible list of elevators, get the one with highest upMax
    // add current floor, if already not added to upMax

    // DOWN clicked
    // similar as up
    
    return "Calling the elevator : {}"; // Return elevator number based on building
  }
}
