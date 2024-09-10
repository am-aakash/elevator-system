package elevator_lld.models;

public class Elevator {
  private static int idCounter = 0;
  private int id;
  private State state;
  private int currentFloor;
  private InternalButtons internalButtons;

  public Elevator(State state, int currentFloor, InternalButtons internalButtons) {
    id = generateId();
    this.state = state;
    this.currentFloor = currentFloor;
    this.internalButtons = internalButtons;
  }

  // Private method to auto-generate an incrementing ID
  private synchronized Integer generateId() {
    return ++idCounter;
  }

  // Getters and Setters

  public int getId() {
    return id;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  public InternalButtons getInternalButtons() {
    return internalButtons;
  }

  public void setInternalButtons(InternalButtons internalButtons) {
    this.internalButtons = internalButtons;
  }
}
