package elevator_lld.models;

import java.util.ArrayList;
import java.util.List;

public class Building {
  private static Integer idCounter = 0;
  private Integer id;
  private List<Floor> floors;
  private List<Integer> elevators;

  public Building(List<Floor> floors, List<Integer> elevators) {
    this.id = generateId();
    this.floors = new ArrayList<>(floors);
    this.elevators = elevators != null ? new ArrayList<>(elevators) : new ArrayList<>();
  }

  // Private method to auto-generate an incrementing ID
  private synchronized Integer generateId() {
    return ++idCounter;
  }

  public List<Floor> getFloors() {
    return floors;
  }

  public void setFloors(List<Floor> floors) {
    this.floors = new ArrayList<>(floors);
  }

  public void addFloor(Floor floor) {
    this.floors.add(floor);
  }

  public List<Integer> getElevators() {
    return elevators;
  }

  public void setElevators(List<Integer> elevators) {
    this.elevators = new ArrayList<>(elevators);
  }

  public void addElevator(Integer elevator) {
    this.elevators.add(elevator);
  }

  public Integer getId() {
    return id;
  }

}
