package ProiektoJosu.kudeatzaile;

public class Diskak {
    private String izena;
    private double tamaina;
    private double erabilita;
    private String neurria;
    private String mounted;

    public Diskak(String izena, double tamaina, double erabilita, String neurria, String mounted) {
        this.izena = izena;
        this.tamaina = tamaina;
        this.erabilita = erabilita;
        this.neurria = neurria;
        this.mounted = mounted;
    }

    @Override
    public String toString() {
        return izena;
    }

    public double getTamaina() {
        return tamaina;
    }

    public double getErabilita() {
        return erabilita;
    }
}
