package com.example.eoyeoga_placeblock;

import java.util.ArrayList;

public class Recycler_item extends ArrayList<Recycler_item> {
    private String tname, tstar, tprice, treview;

    //기본 생성자와 사용자 지정 생성자 : 이름, 별점, 가격, 리뷰수를 설정
    public Recycler_item(){}
    public Recycler_item(String tname, String tstar, String tprice, String treview){
        this.tname = tname;
        this.tstar = tstar;
        this.tprice = tprice;
        this.treview = treview;
    }

    public String getTname(){
        return this.tname;
    }
    public void setTname(String tname){ this.tname = tname; }

    public String getTstar(){
        return this.tstar;
    }
    public void setTstar(String tstar){ this.tstar = tstar; }

    public String getTprice(){
        return this.tprice;
    }
    public void setTprice(String tprice){ this.tstar = tprice; }

    public String getTreview(){
        return this.treview;
    }
    public void setTreview(String treview){ this.treview = treview; }

}
