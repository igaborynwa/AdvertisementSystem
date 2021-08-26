import interfaces.Advertisement;

import java.util.Hashtable;

public class MyAdvertisement implements Advertisement {
    //A reklám címe
    private String title;
    //A maximális megjelenítések száma
    private int maxAppearance;
    //A megjelenítési súly
    private double weight;
    //A megjelenítések száma naponként
    private Hashtable<Integer, Integer> allAppearances;

    //Konstruktor, amely inicializálja a tagváltozókat.
    MyAdvertisement(int app, double w, String t){
        maxAppearance = app;
        weight = w;
        title = t;
        allAppearances = new Hashtable<>();
    }

    @Override
    //Visszaadja a maximális megjelenítési számot
    public int getMaxAppearance() {
        return maxAppearance;
    }

    @Override
    //Visszaadja a reklám megjelenítési súlyát
    public double getWeight() {
        return weight;
    }

    @Override
    //Megadja, hogy a reklámot hányszor jelenítettük meg az utóbbi x napban.
    public int lastAppearence(int dayIndex, int numberOfDays) {
        int sum =0;
        //Végigmegyünk a táblán a megadott naptól vissza numberOfDays napig
        for(int i = dayIndex-numberOfDays+1; i<= dayIndex; i++){
            //Ha a táblában benne van az adott nap (tehát meg lett aznap jelenítve)
            if(allAppearances.containsKey(dayIndex)){
                //Növeljük a sum változót a megjelenítések számával
                sum += allAppearances.get(dayIndex);
            }
        }
        return sum;
    }

    @Override
    //Reklám megjelenítése az adott napon
    public void showAdvertisement(int dayIndex) {
        if(allAppearances.containsKey(dayIndex)){
            int apps = allAppearances.get(dayIndex);
            apps++;
            allAppearances.replace(dayIndex, apps);
        }
        else{
            allAppearances.put(dayIndex,1);
        }
        System.out.println(title);

    }

    @Override
    //Visszaadja a reklám minden megjelenítését
    public Hashtable<Integer, Integer> getAllAppearences() {
        return allAppearances;
    }
}
