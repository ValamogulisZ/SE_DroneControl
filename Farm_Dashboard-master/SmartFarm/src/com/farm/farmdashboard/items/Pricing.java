package com.farm.farmdashboard.items;

public class Pricing extends Visitor{
    private double CPP;
    private double CMP;
    public Pricing()
    {
        CPP=0;
        CMP = 0;
    }
   /* public double CPvalue()
    {
            return CPP;
    }
    public double CMvalue()
    {
        return MP;
    }*/
    @Override
    public void visitItem(ItemLeaf ci) {
        CPP += ci.curPrice();
        CMP += ci.marketPrice();
    }

    @Override
    public void visitItemContainer(ItemContainer cic) {
        CPP += cic.curPrice();
        CMP += cic.marketPrice();
    }
}
