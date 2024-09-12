package elevator_lld.models;

import java.util.List;

public class Building {
  private static Integer idCounter = 0;
  private Integer id;
  private List<Floor> floors;
  private List<Integer> elevators;

  public Building(List<Floor> floors, List<Integer> elevators) {
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

  public void addFloor(Floor floor) {
    this.floors.add(floor);
  }

  public List<Integer> getElevators() {
    return elevators;
  }

  public void setElevators(List<Integer> elevators) {
    this.elevators = elevators;
  }

  public void addElevator(Integer elevator) {
    this.elevators.add(elevator);
  }

  public Integer getId() {
    return id;
  }

}
