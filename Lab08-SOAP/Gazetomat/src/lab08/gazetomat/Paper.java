package lab08.gazetomat;

public class Paper {

    private Integer count = 0;
    private String name = "";

    public Paper() {
    }

    public Paper(Integer count, String name) {
        this.count = count;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
