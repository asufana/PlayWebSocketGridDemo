package infrastructure.notifier;

/**
 * 通知データクラス
 */
public class Notification {
    
    //通知種別
    public NotificationType type;
    
    //通知情報
    public Object value;

    public Notification(NotificationType type, Object value) {
        this.type = type;
        this.value = value;
    }
}
