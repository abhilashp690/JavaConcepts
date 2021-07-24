package JavaConcept.LambdaExpression;

import java.util.ArrayList;
import java.util.List;

class Country {
    String continent;
    int population;

    public Country(String continent, int population) {
        this.continent = continent;
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "continent='" + continent + '\'' +
                ", population=" + population +
                '}';
    }

    public static int getPopulation(List<Country> countries, String continent){
        Country c = countries.stream().filter(continentName -> {
            return (continentName.continent).equals(continent);
        }).findFirst().orElse(null);

        return c.getPopulation();
    }
}


public class LambdaDemonstration {
    public static void main(String[] args) {
        System.out.println("Lambda Demonstration....");
        List<Country> countryList = new ArrayList<>();

        countryList.add(new Country("India" , 10000));
        countryList.add(new Country("Pakistan" , 20000));
        countryList.add(new Country("USA" , 30000));
        countryList.add(new Country("Bangladesh" , 40000));

        System.out.println(Country.getPopulation(countryList , "USA"));

    }
}
