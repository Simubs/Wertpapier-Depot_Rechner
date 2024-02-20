package Objekte;

import java.util.Objects;

public class Wertpapier {

    private String name;

    private String ISIN;

    //Intelij Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertpapier that = (Wertpapier) o;
        return Objects.equals(ISIN, that.ISIN);
    }


    public Wertpapier(String name, String ISIN) {
        this.name = name;
        this.ISIN = ISIN;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
