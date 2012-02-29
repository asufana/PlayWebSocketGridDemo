package infrastructure.notifier;

import play.libs.*;

import com.google.gson.*;

public class NotificationStream {
    final static NotificationStream instance = new NotificationStream();
    final F.ArchivedEventStream<Notification> notificationStream = new F.ArchivedEventStream<Notification>(20);

    public static F.EventStream<Notification> stream() {
        return instance.notificationStream.eventStream();
    }

    public static void publish(final Notification notification) {
        instance.notificationStream.publish(notification);
    }
}
