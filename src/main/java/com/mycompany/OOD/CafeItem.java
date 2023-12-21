/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.OOD;

/**
 *
 * @author sravanti
 */
//Using Enum to Hold categories -> this is done so that user input cannot affect stored values
enum Categories {
    BEVERAGE,
    CAKE,
    SHAKE

}

public class CafeItem {

    private String itemName;
    private int itemPrice;
    private String itemCategory;

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public CafeItem(String itemName, int itemPrice, String itemCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    private static class BeverageItem extends CafeItem {

        public BeverageItem(String itemName, int itemPrice, String itemCategoryBeverage) {
            super(itemName, itemPrice, Categories.BEVERAGE.toString());

        }

    }

    private static class CakeItem extends CafeItem {

        public CakeItem(String itemName, int itemPrice, String itemCategoryCake) {
            super(itemName, itemPrice, Categories.CAKE.toString());
        }

    }

    private static class ShakeItem extends CafeItem {

        public ShakeItem(String itemName, int itemPrice, String itemCategoryShake) {
            super(itemName, itemPrice, Categories.SHAKE.toString());
        }

    }

    private interface CafeItemFactoryAPI {

        CafeItem createCafeItemInstance(String name, int price, String category);

    }

    public static class BeverageFactoryLazySingleton implements CafeItemFactoryAPI {

        private static BeverageFactoryLazySingleton instance;

        private BeverageFactoryLazySingleton() {
        }

        ;

		public static BeverageFactoryLazySingleton getInstance() {
            if (instance == null) {
                instance = new BeverageFactoryLazySingleton();
            }
            return instance;
        }

        @Override
        public CafeItem createCafeItemInstance(String name, int price, String category) {
            return new BeverageItem(name, price, category);
        }

    }

    public static class CakeFactoryLazySingleton implements CafeItemFactoryAPI {

        private static CakeFactoryLazySingleton instance;

        private CakeFactoryLazySingleton() {
        }

        ;

		public static CakeFactoryLazySingleton getInstance() {
            if (instance == null) {
                instance = new CakeFactoryLazySingleton();
            }
            return instance;
        }

        @Override
        public CafeItem createCafeItemInstance(String name, int price, String category) {
            return new CakeItem(name, price, category);
        }

    }

    public static class ShakeFactoryEagerSingleton implements CafeItemFactoryAPI {

        private final static ShakeFactoryEagerSingleton instance = new ShakeFactoryEagerSingleton();

        private ShakeFactoryEagerSingleton() {
        }

        ;

		public static ShakeFactoryEagerSingleton getInstance() {
            return instance;
        }

        @Override
        public CafeItem createCafeItemInstance(String name, int price, String category) {
            return new ShakeItem(name, price, category);
        }

    }

    public static void main(String[] args) {

        CakeItem c = new CakeItem("Chocolate Cake", 20, "Cake");

        System.out.println(c.getItemName());

        BeverageFactoryLazySingleton bItem = BeverageFactoryLazySingleton.getInstance();
        CafeItem bev = bItem.createCafeItemInstance("Coffee", 20, "Cake");

        System.out.println(bev.getItemCategory());

    }

}
