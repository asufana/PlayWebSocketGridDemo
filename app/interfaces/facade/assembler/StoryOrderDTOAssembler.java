package interfaces.facade.assembler;

import java.util.*;

import interfaces.facade.dto.*;
import domain.model.story.*;
import domain.model.storyorder.*;

public class StoryOrderDTOAssembler {

  public static StoryOrderDTO toDTO(final StoryOrder storyOrder) {
      List<StoryDTO> currentStories = new ArrayList<StoryDTO>();
      List<StoryDTO> backlogStories = new ArrayList<StoryDTO>();
      for (Story story : storyOrder.currentOrder()) currentStories.add(new StoryDTO(story.id(), story.title()));
      for (Story story : storyOrder.backlogOrder()) backlogStories.add(new StoryDTO(story.id(), story.title()));
      return new StoryOrderDTO(currentStories, backlogStories);
  }

}
