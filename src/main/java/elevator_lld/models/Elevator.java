package elevator_lld.models;

public class Elevator {
  private static Integer idCounter = 0;
  private Integer id;
  private State state;
  private Integer currentFloor;
  private InternalButtons internalButtons;
  private boolean doorOpen;

  public Elevator(State state, Integer currentFloor, InternalButtons internalButtons) {
    id = generateId();
    this.state = state;
    this.currentFloor = currentFloor;
    this.internalButtons = internalButtons;
    this.doorOpen = false;
  }

  // Private method to auto-generate an incrementing ID
  private synchronized Integer generateId() {
    return ++idCounter;
  }

  // Getters and Setters

  public Integer getId() {
    return id;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Integer getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(Integer currentFloor) {
    this.currentFloor = currentFloor;
  }

  public InternalButtons getInternalButtons() {
    return internalButtons;
  }

  public void setInternalButtons(InternalButtons internalButtons) {
    this.internalButtons = internalButtons;
  }

  public boolean isDoorOpen() {
    return doorOpen;
  }

  public void setDoorOpen(boolean doorOpen) {
    this.doorOpen = doorOpen;
  }
}
