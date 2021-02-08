package com.farm.farmdashboard.items;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class ItemContainer extends ItemLeaf {
    double  Mktprice;
    ArrayList<Item> children;
    private int index = 0;
   /* double CPPVAL;
    double CMPVAL;*/
    TreeItem<Item> selectedItem;
    public ItemContainer(String name, double price,double Mktprice/*,double CMPVAL,double CPPVAL*/, int x, int y, int l, int w, int h, ArrayList<Item> children) {
        super();
        this.name = name;
        this.price = price;
        posX = x;
        posY = y;
        length = l;
        width = w;
        height = h;
        this.children = children;
        this.Mktprice = Mktprice;
     /*  this.CMPVAL = CMPVAL;
        this.CPPVAL = CPPVAL;*/
    }

 /*   public ItemContainer(double price, double marketPrice, TreeItem<Item> selectedItem) {
        super();
        this.price = price;
        this.marketPrice = marketPrice;
        this.selectedItem = selectedItem;
    }
*/
    public void addChild(Item item) {
        children.add(item);
    };
    
    public void removeChild(Item item) {
    	children.remove(item);
    }

    public ArrayList<Item> getChildren() {
        return children;
    }

    public boolean hasNext() {
        if (index == children.size()) {
            index = 0;
            return false;
        }
        return index < children.size();
    }

    public Item next() {
        Item nextItem = this.children.get(index);
        index++;
        return nextItem;
    }
    @Override
   /* public void setCPPVAL(double CPPVAL) {
        this.CPPVAL = CPPVAL;
    }

    @Override
    public void setCMPVAL(double CMPVAL) {

        this.CMPVAL = CMPVAL;

    }*/
    public void accept(Visitor v) {
        v.visitItemContainer(this);
    };

    public double curPrice()
    {
        double price = 0;
        System.out.println(children.size());
        System.out.println(children.size());
        System.out.println(children.size());
        for(int i=0;i<children.size();i++)
        {
            price+= children.get(i).curPrice();
        }
    /*    for (int i=0; i<selectedItem.getParent().getChildren().size(); i++) {
            System.out.println(selectedItem.getParent().getChildren().get(i).getValue());
           if (selectedItem != null && selectedItem.getChildren() == null) {
               price += selectedItem.getChildren().get(i).getValue().getPrice();
               System.out.println(selectedItem.getParent().getChildren().get(i).getValue().getPrice());
           }

            //price += selectedItem.getParent().getChildren().get(i).getValue().getMarketPrice();


        }*/
        price += this.getPrice();


        return price;
    }
    public double marketPrice()
    {   System.out.println("lllll");

       double mktprice = 0;
        for(int i=0;i<children.size();i++)
        {
            mktprice+= children.get(i).marketPrice();
        }

        System.out.println("mktprice");
        System.out.println(mktprice);
        return mktprice;
    }
}
