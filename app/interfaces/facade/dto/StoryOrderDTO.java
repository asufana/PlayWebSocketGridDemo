package interfaces.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class StoryOrderDTO implements Serializable {

  public final List<StoryDTO> currentOrder;
  public final List<StoryDTO> backlogOrder;

  public StoryOrderDTO(List<StoryDTO> currentOrder, List<StoryDTO> backlogOrder) {
      this.currentOrder = currentOrder;
      this.backlogOrder = backlogOrder;
  }

}
