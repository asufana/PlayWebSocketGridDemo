package domain.model.story;

import java.util.List;

import ddd.domain.shared.*;

public class StoryRepository extends RepositoryBase<Story> {
    
    public List<Story> findAll() {
        return super.findAll();
    }

    public Story find(final Long personId) {
        return findFirst("id", personId);
    }
}
