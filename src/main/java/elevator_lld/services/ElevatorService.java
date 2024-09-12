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
    // for each elevatorcontroller and the elevator
    // move it acc to heaps and change state if heap is empty
    // move only if door not open; If open close it
  }

  public void addElevator(Integer buildingId, Elevator elevator) {
    elevatorRepository.addElevator(elevator);
    elevatorRepository.addElevatorController(buildingId, new ElevatorController(elevator));
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
