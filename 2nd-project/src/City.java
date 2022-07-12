import java.text.DecimalFormat;

public class City implements CityInterface, Comparable<City>{

    private int ID;
    private String name;
    private int population;
    private int CovidCases;
    private float  density;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public int getCovidCases() {
        return CovidCases;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public void setCovidCases(int CovidCases) {
        this.CovidCases = CovidCases;
    }


    @Override
    public boolean compareTo(City c2) {
        if (density > c2.density) {
            return true;
        } else if (density == c2.density) {
            if (name.compareTo(c2.name) > 0) {
                return true;
            } else if (name.equals(c2.name)) {
                if (ID < c2.ID) {
                    return true;
                }
            }
        }
        return false;
    }

    public float getDensity() {
        return density;
    }

    public void calculateDensity()
    {
        density = (50000 * CovidCases) / (float) population;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        density = Float.valueOf(twoDForm.format(density));
    }
}
