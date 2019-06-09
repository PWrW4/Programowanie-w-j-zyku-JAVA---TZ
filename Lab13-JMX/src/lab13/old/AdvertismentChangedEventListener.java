package lab13.old;

import java.util.EventListener;

public interface AdvertismentChangedEventListener extends EventListener {
    void onBillboardChangedEvent(AdvertismentChangedEvent e);
}
