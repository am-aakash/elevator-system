package elevator_lld.models;

public class Elevator {
  private int id; 
  private State state;
  private int currentFloor;
  private InternalButtons internalButtons;
  
  public Elevator(State state, int currentFloor, InternalButtons internalButtons) {
    this.state = state;
    this.currentFloor = currentFloor;
    this.internalButtons = internalButtons;
  }

  

  // Getters and Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
