package elevator_lld.services;

import java.util.List;
import java.util.Map;

import elevator_lld.data.ElevatorRepository;
import elevator_lld.models.Elevator;
import elevator_lld.models.ElevatorController;
import elevator_lld.models.Floor;
import elevator_lld.models.InternalButtons;
import elevator_lld.models.State;

public class DispatchService {
  private ElevatorRepository elevatorRepository = ElevatorRepository.getInstance();

  public String callElevator(Integer buildingId, Floor floor, State state) throws Exception {
    if (state.equals(State.IDLE) || state.equals(State.NOT_WORKING)) {
      throw new Exception("Invalid state selected");
    }

    // get the list of elevators in building
    List<Elevator> elevators = elevatorRepository.getBuilding(buildingId).getElevators().stream()
        .map(e -> elevatorRepository.getElevator(e)).toList();

    if (state.equals(State.UP)) {
      List<Elevator> upElevators = elevators.stream()
          .filter(e -> (e.getState().equals(State.UP) && e.getCurrentFloor() <= floor.getFloorNumber()))
          .sorted((e1, e2) -> e2.getCurrentFloor() - e1.getCurrentFloor()).toList();
      // gets and sorts all up going elevators which is below current floor in
      // descending order

      if (!upElevators.isEmpty()) {
        Elevator elevator = upElevators.get(0);
        // add floor to internal buttons of elevator
        InternalButtons internalButtons = elevator.getInternalButtons();
        internalButtons.addButtonsSelected(floor.getFloorNumber());
        elevator.setInternalButtons(internalButtons);

        // add to upMaxHeap of controller
        ElevatorController controller = elevatorRepository.getElevatorController(elevator.getId());
        controller.addToUpMaxHeap(floor.getFloorNumber());

        elevatorRepository.addElevator(elevator);
        elevatorRepository.addElevatorController(elevator.getId(), controller);
        return "Calling the elevator " + elevator.getId() + " for going UP to floor " + floor.getFloorNumber();
      } else {
        // select nearest idle elevators
        List<Elevator> idleElevators = elevators.stream()
            .filter(e -> e.getState().equals(State.IDLE))
            .sorted((e1, e2) -> Math.abs(e1.getCurrentFloor() - floor.getFloorNumber())
                - Math.abs(e2.getCurrentFloor() - floor.getFloorNumber()))
            .toList();
        // if call is for floor 16, we get list of 17, 12, 28, -1, 60 (nearest idle
        // first)
        if (!idleElevators.isEmpty()) {
          Elevator elevator = idleElevators.get(0);
          // add floor to internal buttons of elevator
          InternalButtons internalButtons = elevator.getInternalButtons();
          internalButtons.addButtonsSelected(floor.getFloorNumber());
          elevator.setInternalButtons(internalButtons);
          if (elevator.getCurrentFloor() < floor.getFloorNumber())
            elevator.setState(State.UP);
          else
            elevator.setState(State.DOWN);

          // add to upMaxHeap of controller
          ElevatorController controller = elevatorRepository.getElevatorController(elevator.getId());
          controller.addToUpMaxHeap(floor.getFloorNumber());

          elevatorRepository.addElevator(elevator);
          elevatorRepository.addElevatorController(elevator.getId(), controller);
          return "Calling the idle elevator " + elevator.getId() + " for going UP to floor " + floor.getFloorNumber();
        } else {
          return "Tap once any down elevator is idle (Auto matic feature is not implemented yet)";
        }
      }
    }

    // UP clicked
    // if going up, get the list of elevators that have upMax top less than currect
    // floor
    // in eligible list of elevators, get the one with highest upMax
    // add current floor, if already not added to upMax
    // *** if all upMax is empty -> select nearest idol -> if no idols -> select
    // from Downpq having minimum waiting time

    // DOWN clicked
    if (state.equals(State.DOWN)) {
      List<Elevator> downElevators = elevators.stream()
          .filter(e -> (e.getState().equals(State.DOWN) && e.getCurrentFloor() >= floor.getFloorNumber()))
          .sorted((e1, e2) -> e1.getCurrentFloor() - e2.getCurrentFloor()).toList();
      // gets and sorts all down going elevators which is above current floor in
      // ascending order

      if (!downElevators.isEmpty()) {
        Elevator elevator = downElevators.get(0);
        // add floor to internal buttons of elevator
        InternalButtons internalButtons = elevator.getInternalButtons();
        internalButtons.addButtonsSelected(floor.getFloorNumber());
        elevator.setInternalButtons(internalButtons);

        // add to upMaxHeap of controller
        ElevatorController controller = elevatorRepository.getElevatorController(elevator.getId());
        controller.addToDownMinHeap(floor.getFloorNumber());

        elevatorRepository.addElevator(elevator);
        elevatorRepository.addElevatorController(elevator.getId(), controller);
        return "Calling the elevator " + elevator.getId() + " for going DOWN to floor " + floor.getFloorNumber();
      } else {
        // select nearest idle elevators
        List<Elevator> idleElevators = elevators.stream()
            .filter(e -> e.getState().equals(State.IDLE))
            .sorted((e1, e2) -> Math.abs(e1.getCurrentFloor() - floor.getFloorNumber())
                - Math.abs(e2.getCurrentFloor() - floor.getFloorNumber()))
            .toList();
        // if call is for floor 16, we get list of 17, 12, 28, -1, 60 (nearest idle
        // first)
        if (!idleElevators.isEmpty()) {
          Elevator elevator = idleElevators.get(0);
          // add floor to internal buttons of elevator
          InternalButtons internalButtons = elevator.getInternalButtons();
          internalButtons.addButtonsSelected(floor.getFloorNumber());
          elevator.setInternalButtons(internalButtons);
          if (elevator.getCurrentFloor() < floor.getFloorNumber())
            elevator.setState(State.UP);
          else
            elevator.setState(State.DOWN);

          // add to upMaxHeap of controller
          ElevatorController controller = elevatorRepository.getElevatorController(elevator.getId());
          controller.addToDownMinHeap(floor.getFloorNumber());

          elevatorRepository.addElevator(elevator);
          elevatorRepository.addElevatorController(elevator.getId(), controller);
          return "Calling the idle elevator " + elevator.getId() + " for going DOWN to floor " + floor.getFloorNumber();
        } else {
          return "Tap once any down elevator is idle (Auto matic feature is not implemented yet)";
        }
      }
    }

    return "Unable to Call the Elevator"; // Return elevator number based on building
  }

  public void pressInternalButton(Integer elevatorId, Integer floor) {

    // add the floor to elevator controller of evelator id depending if its down or
    // up of elevator's current floor

  }
}
