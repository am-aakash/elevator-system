package elevator_lld.models;

import java.util.HashSet;
import java.util.Set;

public class InternalButtons {
  Set<Integer> totalFloors;
  Set<Integer> buttonsSelected;

  public InternalButtons(Set<Integer> totalFloors) {
    this.totalFloors = new HashSet<>(totalFloors);
    this.buttonsSelected = new HashSet<>();
  }

  public Set<Integer> getTotalFloors() {
    return totalFloors;
  }

  public void addFloor(Integer floor) {
    this.totalFloors.add(floor);
  }

  public Set<Integer> getButtonsSelected() {
    return buttonsSelected;
  }

  public void addButtonsSelected(Integer button) {
    this.buttonsSelected.add(button);
  }
}
