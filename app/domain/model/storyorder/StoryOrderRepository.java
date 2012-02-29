package domain.model.storyorder;

import java.util.List;

import ddd.domain.shared.RepositoryBase;

/**
 * リポジトリ実装
 */
public class StoryOrderRepository extends RepositoryBase<StoryOrder> {
    
    public StoryOrder find() {
        List<StoryOrder> storyOrders = findAll();
        return storyOrders.size() != 0 ? storyOrders.get(0) : null;
    }

}
