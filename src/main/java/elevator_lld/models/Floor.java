package elevator_lld.models;

import java.util.List;

public class Floor {
  private Integer floorNumber;
  private List<State> statesAllowed; // Up or Down

  public Floor(int floorNumber, List<State> statesAllowed) {
    this.floorNumber = floorNumber;
    this.statesAllowed = statesAllowed;
  }

  public Integer getFloorNumber() {
    return floorNumber;
  }

  public void setFloorNumber(Integer floorNumber) {
    this.floorNumber = floorNumber;
  }

  public List<State> getStatesAllowed() {
    return statesAllowed;
  }

  public void setStatesAllowed(List<State> statesAllowed) {
    this.statesAllowed = statesAllowed;
  }
}
