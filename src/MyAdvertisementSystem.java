import interfaces.Advertisement;
import interfaces.AdvertisementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//A saját hirdető rendszer osztályom, amely megvalósítja az AdvertisementSystem interface-t
public class MyAdvertisementSystem implements AdvertisementSystem {
    //A rendszerben lévő reklámok
    private List<Advertisement> advertisementList;
    //Az időegység alatt megjeleníthető reklámok. Pl ha A súlya 0.2, B súlya 0.1, akkor a lista: AAB
    private List<Advertisement> availableAdvertisements;

    //Konstruktor, amely inicializálja a listákat
    MyAdvertisementSystem(){
        advertisementList = new ArrayList<>();
        availableAdvertisements = new ArrayList<>();
    }

    @Override
    //Regisztrálunk egy reklámot a rendszerbe
    public void registerAdvertisement(Advertisement ad) {
        advertisementList.add(ad);
    }

    /*
    Megjeleníti a következő reklámot
     */
    @Override
    public void showNextAdvertisement(int dayIndex) {
        boolean end = false;
        //Ha üres az elérhető reklámok listája, akkor kiszámoljuk a listát
        if(availableAdvertisements.size() ==0) calculateAvailable();
        //Ciklus, amíg nem sikerül reklámot megjeleníteni, vagy el nem fogy a lista
        while(!end && availableAdvertisements.size()>0){
            //Véletlen szám generálása, mely meghatározza melyik reklámot próbáljuk meg lejátszani
            Random r = new Random();
            int index = r.nextInt(availableAdvertisements.size());
            //Ha a kiválasztott reklám megjeleníthető, vagyis még nem értük el a maximális megjelenítési keretet, akkor megjelenítjük
            if(availableAdvertisements.get(index).lastAppearence(dayIndex,1) < availableAdvertisements.get(index).getMaxAppearance()){
                //Reklám megjelenítése
                availableAdvertisements.get(index).showAdvertisement(dayIndex);
                //Sikerült megjeleníteni reklámot, véget érhet a ciklus
                end = true;

            }
            //Töröljük a reklámot az elérhető listából
            availableAdvertisements.remove(index);
        }


    }

    /*
    Meghatározza az időegység alatt lejátszandó reklámblokkokot.
    Megkeresi a legkisebb súlyú reklámot, és az alapján meghatározza, hoyg a többi reklámot
    ugyanannyi idő alatt hányszor kell lejátszani.
     */
    private void calculateAvailable(){
        //Az egység, vagyis a legkisebb súly meghatározása
        double unit = 1.0;
        for (Advertisement ad: advertisementList ) {
            if(ad.getWeight()<unit) unit = ad.getWeight();
        }

        //Lista építése az alapján, hogy időegység alatt a legkisebb súlyhoz képest melyik reklámot hányszor kell leadni.
        //Annyiszor kerül be minden reklám a listába, amennyi a súlyának és az egységnek a hányadosa
        for (Advertisement ad: advertisementList ) {
            double count = (ad.getWeight()/unit);
            for (int i=0;i<count; i++){
                availableAdvertisements.add(ad);
            }
        }

    }

    @Override
    //Visszaadja a reklámlistát
    public List<Advertisement> getAdvertisementList() {
        return advertisementList;
    }
}
