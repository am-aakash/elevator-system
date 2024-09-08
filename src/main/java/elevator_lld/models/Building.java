package elevator_lld.models;

import java.util.List;

public class Building {
  private List<Floor> floors;
  private List<String> elevators;

  public List<Floor> getFloors() {
    return floors;
  }
  public void setFloors(List<Floor> floors) {
    this.floors = floors;
  }
  public List<String> getElevators() {
    return elevators;
  }
  public void setElevators(List<String> elevators) {
    this.elevators = elevators;
  }
  
}
