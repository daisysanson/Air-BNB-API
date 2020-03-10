public class Names {
    private int identificationNumber;
    private String name;

    public Names(){

    }

    public Names(int identificationNumber, String name) {
        this.identificationNumber = identificationNumber;
        this.name = name;
    }

    public int getidentificationNumber() {
        return identificationNumber;
    }

    public void setidentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
