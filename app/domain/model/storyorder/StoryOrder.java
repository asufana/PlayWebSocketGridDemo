package domain.model.storyorder;

import java.util.*;

import javax.persistence.*;

import ddd.domain.shared.Entity;
import ddd.infrastructure.persistence.*;
import domain.model.story.*;

/**
 * ストーリー順序エンティティ
 */
@javax.persistence.Entity
public class StoryOrder extends Models implements Entity<StoryOrder> {
    private static final StoryRepository storyRepo = new StoryRepository();
    private static final StoryOrderRepository storyOrderRepo = new StoryOrderRepository();
    
    private static StoryOrder INSTANCE;
    public static StoryOrder getInstance() {
        if(storyOrderRepo.find() != null) {
            INSTANCE = storyOrderRepo.find();
        } else {
            List<Story> currentOrder = new ArrayList<Story>();
            List<Story> backlogOrder = new ArrayList<Story>();
            for(Story story : storyRepo.findAll()) {
                if(story.isCurrent()) {
                    currentOrder.add(story);
                } else {
                    backlogOrder.add(story);
                }
            }
            INSTANCE = new StoryOrder(currentOrder, backlogOrder);
            INSTANCE._save();
        }
        return INSTANCE;
    }
    
    //着手中ストーリーリスト
    @OneToMany
    @JoinColumn(name="currenOrder_fk")
    private static List<Story> currentOrder = new ArrayList<Story>();
    
    //バックログリスト
    @OneToMany
    @JoinColumn(name="backlogOrder_fk")
    private static List<Story> backlogOrder = new ArrayList<Story>();
    
    //コンストラクタ
    private StoryOrder(final List<Story> currentOrder, final List<Story> backlogOrder) {
        this.currentOrder = currentOrder;
        this.backlogOrder = backlogOrder;
    }
    
    public List<Story> currentOrder(){
        return currentOrder;
    }
    
    public List<Story> backlogOrder(){
        return backlogOrder;
    }
    
    /**
     * ストーリー追加
     */
    public StoryOrder addStory(final Story story) {
        List<Story> newBacklogOrder = backlogOrder;
        newBacklogOrder.add(story);
        StoryOrder newOrder = new StoryOrder(currentOrder, newBacklogOrder);
        newOrder._save();
        return newOrder;
    }
    
    /**
     * オーダー順変更
     */
    public StoryOrder changeOrder(final List<Story> currentOrder, final List<Story> backlogOrder) {
        StoryOrder newOrder = new StoryOrder(currentOrder, backlogOrder);
        newOrder._save();
        return newOrder;
    }
    
    /**
     * オーダー順変更
     */
    public StoryOrder changeOrder(final String[] currentIds, final String[] backlogIds) {
        List<Story> currentOrder = new ArrayList<Story>();
        if(currentIds != null) {
            for(String id : currentIds) {
                currentOrder.add(storyRepo.find(Integer.valueOf(id).longValue()));
            }
        }
        List<Story> backlogOrder = new ArrayList<Story>();
        if(backlogIds != null) {
            for(String id : backlogIds) {
                backlogOrder.add(storyRepo.find(Integer.valueOf(id).longValue()));
            }
        }
        return changeOrder(currentOrder, backlogOrder);
    }
    
    @Override
    public StoryOrder save() {
        throw new RuntimeException("You cannot save this object.");
    }
    
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StoryOrder other = (StoryOrder) object;
        return sameIdentityAs(other);
    }

    @Override
    public boolean sameIdentityAs(final StoryOrder other) {
        return other != null && this.id.equals(other.id);
    }
}
