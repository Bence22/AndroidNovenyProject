import java.util.List;
import android.os.Bundle;
public class FavouritePlant {
    private String commonName;
    private String scientificName;

    public FavouritePlant(String commonName, String scientificName) {
        this.commonName = commonName;
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }
}
