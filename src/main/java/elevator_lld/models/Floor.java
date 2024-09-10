package elevator_lld.models;

import java.util.List;

public class Floor {
  private int floorNumber;
  private List<State> statesAllowed; // Up or Down

  public Floor(int floorNumber, List<State> statesAllowed) {
    this.floorNumber = floorNumber;
    this.statesAllowed = statesAllowed;
  }
}
