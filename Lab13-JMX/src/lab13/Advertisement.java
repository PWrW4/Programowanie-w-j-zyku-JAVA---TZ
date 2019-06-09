package lab13;

public class Advertisement implements AdvertisementMBean {

    private String addvString;
    private int addvTime;

    @Override
    public String getAddvString() {
        return addvString;
    }

    @Override
    public void setAddvString(String addvString) {
        this.addvString = addvString;
    }

    @Override
    public int getAddvTime() {
        return addvTime;
    }

    @Override
    public void setAddvTime(int addTime) {
        this.addvTime = addTime;
    }
}
