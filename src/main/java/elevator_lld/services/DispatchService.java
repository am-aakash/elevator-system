package elevator_lld.services;

import java.util.Set;
import java.util.stream.Collectors;

import elevator_lld.data.ElevatorRepository;
import elevator_lld.models.Floor;
import elevator_lld.models.State;

public class DispatchService {
  private ElevatorRepository elevatorRepository = ElevatorRepository.getInstance();

  public String callElevator(Floor floor, State state) throws Exception {
    if (state.equals(State.IDLE) || state.equals(State.NOT_WORKING)) {
      throw new Exception("Invalid state selected");
    }

    // get the list of elevators in building

    // UP clicked
    // if going up, get the list of elevators that have upMax top less than currect
    // floor
    // in eligible list of elevators, get the one with highest upMax
    // add current floor, if already not added to upMax

    // DOWN clicked
    // similar as up

    return "Calling the elevator : {}"; // Return elevator number based on building
  }

  public void pressInternalButton(Integer elevatorId, Integer floor) {

    // add the floor to elevator controller of evelator id depending if its down or
    // up of elevator's current floor

  }
}
