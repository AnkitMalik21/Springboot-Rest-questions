package com.hcl.entity;

import java.util.ArrayList;

public class Restaurant {
    private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
    public ArrayList<MenuItem> getMenuList(){
    	return menuList;
    }
    
    public void setMenuList(ArrayList<MenuItem>menuItem) {
    	this.menuList = menuList;
    }
    
    public Restaurant() {
    		MenuItem menuItem1 = new MenuItem("Pizza margheria","Italian",10.99);
    		MenuItem menuItem2 = new MenuItem("Vegan Burger","American",8.99);
    		MenuItem menuItem3 = new MenuItem("Sushi Platter","Japanese",15.99);
    		
    		menuList.add(menuItem1);
    		menuList.add(menuItem2);
    		menuList.add(menuItem3);
    }
}
