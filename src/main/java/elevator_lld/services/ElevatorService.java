package elevator_lld.services;

import elevator_lld.data.ElevatorRepository;
import elevator_lld.models.Building;
import elevator_lld.models.Elevator;
import elevator_lld.models.ElevatorController;
import elevator_lld.models.Floor;
import elevator_lld.models.InternalButtons;
import elevator_lld.models.State;

public class ElevatorService {
  private ElevatorRepository elevatorRepository = ElevatorRepository.getInstance();
  private DispatchService dispatchService = new DispatchService();

  public void moveElevatorsCron() {

    elevatorRepository.getBuildings().forEach(
        buildingId -> {
          // for each elevatorcontroller and the elevator
          // move it acc to heaps and change state if heap is empty
          // move only if door not open; If open close it
          System.out.println(" ");
          elevatorRepository.getElevators(buildingId).forEach(
              elevator -> {
                ElevatorController elevatorController = elevatorRepository.getElevatorController(elevator.getId());

                if (elevator.getState().equals(State.IDLE) || elevator.getState().equals(State.NOT_WORKING)
                    || (elevatorController.getDownMinHeap().isEmpty() && elevatorController.getUpMaxHeap().isEmpty())) {
                  if (!elevator.getState().equals(State.NOT_WORKING)) {
                    elevator.setState(State.IDLE);
                  }
                  NotifyService.notify("Idle at floor " + elevator.getCurrentFloor() + " elevator "
                      + elevator.getId());
                  // skip below cases
                } else if (elevator.isDoorOpen()) {
                  // close the door and skip other cases
                  NotifyService.notify("The door is closing");
                  elevator.setDoorOpen(false);
                } else if (elevator.getState().equals(State.UP)) {
                  if (!elevatorController.getUpMaxHeap().isEmpty()
                      && elevatorController.getUpMaxHeap().peek() >= elevator.getCurrentFloor()) {
                    // going up
                    elevator.setCurrentFloor(elevatorController.popUpMaxHeap());
                  } else {
                    // going down if invalid
                    elevatorController.getUpMaxHeap().clear();
                    elevator.setCurrentFloor(elevatorController.popDownMinHeap());
                  }

                  // notify and open door for new floor
                  NotifyService.notify("Reached floor " + elevator.getCurrentFloor() + " in elevator "
                      + elevator.getId());
                  openDoor(elevator.getId());

                } else if (elevator.getState().equals(State.DOWN)) {
                  if (!elevatorController.getDownMinHeap().isEmpty()
                      && elevatorController.getDownMinHeap().peek() <= elevator.getCurrentFloor()) {
                    // going down
                    elevator.setCurrentFloor(elevatorController.popDownMinHeap());
                  } else {
                    // going up if invalid
                    elevatorController.getDownMinHeap().clear();
                    elevator.setCurrentFloor(elevatorController.popUpMaxHeap());
                  }

                  // notify and open door for new floor
                  NotifyService.notify("Reached floor " + elevator.getCurrentFloor() + " in elevator "
                      + elevator.getId());
                  openDoor(elevator.getId());

                }

                elevatorRepository.addElevator(elevator);
                elevatorRepository.addElevatorController(elevator.getId(), elevatorController);
              });
        });
  }

  public void addElevator(Integer buildingId, Elevator elevator) {
    elevatorRepository.addElevator(elevator);
    elevatorRepository.addElevatorController(elevator.getId(), new ElevatorController(elevator));
    elevatorRepository.getBuilding(buildingId).addElevator(elevator.getId());
    NotifyService.notify("Elevator added with id " + elevator.getId());
  }

  public void addBuilding(Building building) {
    elevatorRepository.addBuilding(building);
  }

  public void openDoor(Integer elevatorId) {
    NotifyService.notify("The door is open at floor " + elevatorRepository.getElevator(elevatorId).getCurrentFloor());
    elevatorRepository.getElevator(elevatorId).setDoorOpen(true);
  }

  public void callElevator(Floor floor, State state) {
    try {
      NotifyService.notify(dispatchService.callElevator(floor, state));
    } catch (Exception e) {
      NotifyService.notify(e.getMessage(), "error");
    }
  }

  public void pressInternalButton(Integer elevatorId, Integer floor) {
    dispatchService.pressInternalButton(elevatorId, floor);
    NotifyService.notify(floor + " floor selected from elevator " + elevatorId);
    InternalButtons internalButtons = elevatorRepository.getElevator(elevatorId).getInternalButtons();
    internalButtons.addButtonsSelected(floor);
    elevatorRepository.getElevator(elevatorId).setInternalButtons(internalButtons);
  }
}
