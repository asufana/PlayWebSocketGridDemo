package controllers;

import static infrastructure.notifier.NotificationType.Order;
import infrastructure.notifier.*;
import interfaces.facade.assembler.*;
import interfaces.facade.dto.*;
import play.mvc.*;
import domain.model.story.*;
import domain.model.storyorder.*;

public class Application extends Controller {
    
    public static void index() {
        render();
    }
    
    //初期化データ返却
    public static void init() {
        renderJSON(StoryOrderDTOAssembler.toDTO(StoryOrder.getInstance()));
    }
    
    //順序変更
    public static void changeOrder(final String currentOrderIds, final String backlogOrderIds) {
        StoryOrder.getInstance().changeOrder(
                currentOrderIds != null ? currentOrderIds.replace(" ", "").split(",") : null,
                backlogOrderIds != null ? backlogOrderIds.replace(" ", "").split(",") : null);
        notifyUpdated();
    }
    
    //ストーリー追加
    public static void addStory(String title) {
        Story story = new Story(title, false).save();
        StoryOrder.getInstance().addStory(story);
        notifyUpdated();
        redirect("/");
    }
    
    //変更通知
    private static void notifyUpdated() {
        StoryOrderDTO dto = StoryOrderDTOAssembler.toDTO(StoryOrder.getInstance());
        Notifier.notify(new Notification(Order, dto));
    }
    
}