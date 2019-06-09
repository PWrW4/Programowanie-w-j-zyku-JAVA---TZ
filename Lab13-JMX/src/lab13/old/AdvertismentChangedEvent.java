package lab13.old;

import java.util.EventObject;

public class AdvertismentChangedEvent extends EventObject {

    private boolean wasTurnedOn;
    private boolean textWasChanged;
    private boolean timeWasChanged;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AdvertismentChangedEvent(Integer source, boolean wasDisplayedOn, boolean textWasChanged, boolean timeWasChanged) {
        super(source);
        this.wasTurnedOn = wasDisplayedOn;
        this.textWasChanged = textWasChanged;
        this.timeWasChanged = timeWasChanged;
    }


    public Integer getSource() {
        return (Integer) super.getSource();
    }

    public boolean isTextWasChanged(){
        return this.textWasChanged;
    }

    public boolean isWasTurnedOn() {
        return wasTurnedOn;
    }

    public boolean isTimeWasChanged() {
        return timeWasChanged;
    }
}
