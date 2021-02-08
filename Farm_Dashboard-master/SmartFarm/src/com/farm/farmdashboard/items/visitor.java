package com.farm.farmdashboard.items;


abstract class Visitor
{
    public abstract void visitItem(ItemLeaf ci);
    public abstract void visitItemContainer(ItemContainer cic);
}
