package com.pkeugine.whewigo.data;

import java.io.Serializable;
import java.util.ArrayList;

public class HorizonRepo implements Serializable {
    private int firstCategory;
    private int stableId;
    private String secondCategory;
    private ArrayList<PoiRepo> poiList;
    private int vIndex;
    private int hIndex = 0;

    @Override
    public String toString() {
        return "HorizonRepo{" +
                "firstCategory=" + firstCategory +
                ", secondCategory='" + secondCategory + '\'' +
                ", poiList=" + poiList +
                ", vIndex=" + vIndex +
                ", hIndex=" + hIndex +
                '}';
    }



    public int getStableId() {
        return stableId;
    }

    public void setStableId(int stableId) {
        this.stableId = stableId;
    }

    public int getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(int firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public ArrayList<PoiRepo> getPoiList() {
        return poiList;
    }

    public void setPoiList(ArrayList<PoiRepo> poiList) {
        this.poiList = poiList;
    }

    public int getvIndex() {
        return vIndex;
    }

    public void setvIndex(int vIndex) {
        this.vIndex = vIndex;
    }

    public int gethIndex() {
        return hIndex;
    }

    public void sethIndex(int hIndex) {
        this.hIndex = hIndex;
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
        switch (secondCategory) {
            case "숙박":
                this.stableId = 0;
                break;
            case "호텔":
                this.stableId = 1;
                break;
            case "양식":
                this.stableId = 2;
                break;
            case "중식":
                this.stableId = 3;
                break;
            case "TV맛집":
                this.stableId = 4;
                break;
            case "치킨":
                this.stableId = 5;
                break;
            case "한식":
                this.stableId = 6;
                break;
            case "놀거리":
                this.stableId = 7;
                break;
            case "노래방":
                this.stableId = 8;
                break;
            case "영화관":
                this.stableId = 9;
                break;
            case "레저":
                this.stableId = 10;
                break;
        }
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList, int vIndex) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
        this.vIndex = vIndex;
        switch (secondCategory) {
            case "숙박":
                this.stableId = 0;
                break;
            case "호텔":
                this.stableId = 1;
                break;
            case "양식":
                this.stableId = 2;
                break;
            case "중식":
                this.stableId = 3;
                break;
            case "TV맛집":
                this.stableId = 4;
                break;
            case "치킨":
                this.stableId = 5;
                break;
            case "한식":
                this.stableId = 6;
                break;
            case "놀거리":
                this.stableId = 7;
                break;
            case "노래방":
                this.stableId = 8;
                break;
            case "영화관":
                this.stableId = 9;
                break;
            case "레저":
                this.stableId = 10;
                break;
        }
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList, int vIndex, int hIndex) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
        this.vIndex = vIndex;
        this.hIndex = hIndex;
        switch (secondCategory) {
            case "숙박":
                this.stableId = 0;
                break;
            case "호텔":
                this.stableId = 1;
                break;
            case "양식":
                this.stableId = 2;
                break;
            case "중식":
                this.stableId = 3;
                break;
            case "TV맛집":
                this.stableId = 4;
                break;
            case "치킨":
                this.stableId = 5;
                break;
            case "한식":
                this.stableId = 6;
                break;
            case "놀거리":
                this.stableId = 7;
                break;
            case "노래방":
                this.stableId = 8;
                break;
            case "영화관":
                this.stableId = 9;
                break;
            case "레저":
                this.stableId = 10;
                break;
        }
    }
}