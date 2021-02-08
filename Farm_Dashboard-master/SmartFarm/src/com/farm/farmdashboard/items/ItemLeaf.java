package com.farm.farmdashboard.items;

import java.util.ArrayList;

public class ItemLeaf implements Item {
     double marketPrice;
    String name = "";
    double price = 0;
    int posX = 0;
    int posY = 0;
    int length = 0;
    int width = 0;
    int height = 0;
   /* double CPPVAL;
    double CMPVAL;*/
    // ConstructorssetCMPVAL
 /*   public ItemLeaf(double price, double marketPrice) {
        this.price = price;
        this.marketPrice = marketPrice;
    }
*/
    public ItemLeaf(String name, double price,double marketPrice/*,double CMPVAL,double CPPVAL*/, int x, int y, int l, int w, int h) {
        this.name = name;
        this.price = price;
        posX = x;
        posY = y;
        length = l;
        width = w;
        height = h;
        this.marketPrice = marketPrice;
      /*  this.CMPVAL = CMPVAL;
        this.CPPVAL = CPPVAL;*/
    }

    public ItemLeaf() {

    }


    // Setters
    public void setName(String name) {
        this.name = name;
    };

    public void setPrice(double price) {
        this.price = price;
        System.out.println(this.price);
    };

    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    };

    public void setDimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setMarketPrice(double marketPrice) {
        this.marketPrice= marketPrice;
    }
/*
    @Override
    public void setCPPVAL(double CPPVAL) {
        this.CPPVAL = CPPVAL;
    }

    @Override
    public void setCMPVAL(double CMPVAL) {

        this.CMPVAL = CMPVAL;

    }
*/
    ;

    // Getters
    public String getName() {
        return name;
    };

    public double getPrice() {
        return price;
    };

    public int getPosX() {
        return posX;
    };

    public int getPosY() {
        return posY;
    };

    public int getLength() {
        return length;
    };

    public int getWidth() {
        return width;
    };

    public int getHeight() {
        return height;
    };
  /*  public double getCPPVAL() {
        return CPPVAL;
    };
    public double getCMPVAL() {
        return CMPVAL;
    };*/
    // Children
    public void addChild(Item item) { }

    public void removeChild(Item item) { }

    public ArrayList<Item> getChildren() {
        return null;
    }

    // Iteration
    public Item next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    // Utility
    public String toString() {
        return name;
    }
    public double getMarketPrice() {
        return marketPrice;
    }


    public double marketPrice()
    {
        return this.marketPrice ;
    }
    public double curPrice(){

        return this.price;
    }
    ;
    public void accept(Visitor v) {
        v.visitItem(this);
    };
}
