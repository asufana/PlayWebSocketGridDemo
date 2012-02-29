package controllers;

import static infrastructure.notifier.NotificationType.Order;
import static play.libs.F.Matcher.ClassOf;
import static play.mvc.Http.WebSocketEvent.SocketClosed;
import static play.mvc.Http.WebSocketEvent.TextFrame;
import infrastructure.notifier.*;
import play.*;
import play.libs.*;
import play.mvc.*;

import com.google.gson.*;

public class Notifications extends Controller {
    
    public static class WebSocket extends WebSocketController {
        public static void connect() {
            Logger.info("Connected.");
            F.EventStream<Notification> eventStream = NotificationStream.stream();
            while(inbound.isOpen()) {
                F.Either<Http.WebSocketEvent, Notification> receivedEvent = await(F.Promise.waitEither(inbound.nextEvent(), eventStream.nextEvent()));
                
                // WebSocketから受け取ったメッセージをGrowlや他のWebSocketコネクションにブロードキャストする。
                for (String message : TextFrame.match(receivedEvent._1)) {
                    Notifier.notify(new Notification(Order, message));
                }
                
                // 他のWebSocket接続から受け取ったメッセージを、現在のWebSocket接続先に送る。
                for (Notification notification : ClassOf(Notification.class).match(receivedEvent._2)) {
                    outbound.send(new Gson().toJson(notification));
                }
                
                for (Http.WebSocketClose close : SocketClosed.match(receivedEvent._1)) {
                    Logger.info("Disconnect.");
                    disconnect();
                }
            }
        }
    }
}
