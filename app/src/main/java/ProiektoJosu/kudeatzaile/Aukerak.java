package ProiektoJosu.kudeatzaile;

public class Aukerak {
    char aukera;
    String testua;

    public Aukerak(char aukera, String testua) {
        this.aukera = aukera;
        this.testua = testua;
    }

    @Override
    public String toString() {
        return testua;
    }
}
