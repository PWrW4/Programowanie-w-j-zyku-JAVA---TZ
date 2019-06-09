package lab13;

public class AdvertisementTemplate {
    private String adText;
    private int adTime;

    public AdvertisementTemplate(String adText, int adTime) {
        this.adText = adText;
        this.adTime = adTime;
    }

    public AdvertisementTemplate() {
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public int getAdTime() {
        return adTime;
    }

    public void setAdTime(int adTime) {
        this.adTime = adTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertisementTemplate that = (AdvertisementTemplate) o;

        if (adTime != that.adTime) return false;
        return adText != null ? adText.equals(that.adText) : that.adText == null;
    }

    @Override
    public int hashCode() {
        int result = adText != null ? adText.hashCode() : 0;
        result = 31 * result + adTime;
        return result;
    }

    @Override
    public String toString() {
        return "AdvertisementTemplate{" +
                "adText='" + adText + '\'' +
                ", adTime=" + adTime +
                '}';
    }
}
