package elevator_lld.models;

import java.util.List;

public class Building {
  private static int idCounter = 0;
  private Integer id;
  private List<Floor> floors;
  private List<String> elevators;

  public Building(List<Floor> floors, List<String> elevators) {
    this.id = generateId();
    this.floors = floors;
    this.elevators = elevators;
  }

  // Private method to auto-generate an incrementing ID
  private synchronized Integer generateId() {
    return ++idCounter;
  }

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

  public Integer getId() {
    return id;
  }

}
