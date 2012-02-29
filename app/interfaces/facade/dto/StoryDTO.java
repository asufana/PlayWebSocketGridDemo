package interfaces.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class StoryDTO implements Serializable {

  public final Long id;
  public final String title;

  public StoryDTO(Long id, String title) {
      this.id = id;
      this.title = title;
  }

}
