package infrastructure.notifier;

/**
 * 通知クラス
 */
public class Notifier {

    public static void notify(final Notification notification) {
        NotificationStream.publish(notification);
    }
}