package lab13;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Advertisement extends NotificationBroadcasterSupport implements AdvertisementMBean {

    private String addvString;
    private String old_addvString;
    private int addvTime;
    private int old_addvTime;

    private long sequenceNumber = 1;
    private boolean sendNotify = false;

    public Advertisement(int time, String adv) {
        super();
        addvString =adv;
        old_addvString = addvString;
        addvTime = time;
        old_addvTime = addvTime;
    }

    @Override
    public String getAddvString() {
        return addvString;
    }

    @Override
    public void setAddvString(String addvString) {
        this.old_addvString = this.addvString;
        this.addvString = addvString;
        if (sendNotify) {
            Notification n = new AttributeChangeNotification(this,
                    sequenceNumber++, System.currentTimeMillis(),
                    "String changed", "addvString", "String",
                    old_addvString, this.addvString);

            sendNotification(n);
        }
        sendNotify = false;
    }

    public void sendNotify(){
        sendNotify = true;
    }

    @Override
    public int getAddvTime() {
        return addvTime;
    }

    @Override
    public void setAddvTime(int addTime) {
        this.old_addvTime = this.addvTime;
        this.addvTime = addTime;
        if (sendNotify) {
            Notification n = new AttributeChangeNotification(this,
                    sequenceNumber++, System.currentTimeMillis(),
                    "String changed", "addvTime", "int",
                    old_addvTime, this.addvTime);

            sendNotification(n);
        }
        sendNotify = false;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
