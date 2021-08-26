public class Main {

    public static void main(String[] args) {

        MyAdvertisementSystem system = new MyAdvertisementSystem();
        system.registerAdvertisement(new MyAdvertisement(10,0.6,"abc"));
        system.registerAdvertisement(new MyAdvertisement(10,0.2,"def"));
        for(int i=0;i<30;i++)
            system.showNextAdvertisement(100);


    }
}
