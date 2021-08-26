public class Main {

    public static void main(String[] args) {

        //Létrehozunk egy példányt a rendszerből
        MyAdvertisementSystem system = new MyAdvertisementSystem();
        //Hozzáadok reklámokat a rendszerhez
        system.registerAdvertisement(new MyAdvertisement(15,0.4,"A"));
        system.registerAdvertisement(new MyAdvertisement(10,0.2,"B"));
        system.registerAdvertisement(new MyAdvertisement(5,0.1,"C"));
        //30 reklám kijátszása
        for(int i=0;i<30;i++)
            system.showNextAdvertisement(100);


    }
}
