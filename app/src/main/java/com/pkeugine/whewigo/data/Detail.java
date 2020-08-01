package com.pkeugine.whewigo.data;

public class Detail {
    PoiDetailInfo poiDetailInfo;

    public static class PoiDetailInfo {
        private String id;
        private String name;
        private String address;
        private String firstNo;
        private String secondNo;
        private String tel;
        private double lat;
        private double lon;
        private String parkFlag;
        private String additionalInfo;
        private String desc;

        public PoiDetailInfo() {
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setFirstNo(String firstNo) {
            this.firstNo = firstNo;
        }

        public void setSecondNo(String secondNo) {
            this.secondNo = secondNo;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public void setParkFlag(String parkFlag) {
            this.parkFlag = parkFlag;
        }

        public void setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getFirstNo() {
            return firstNo;
        }

        public String getSecondNo() {
            return secondNo;
        }

        public String getTel() {
            return tel;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public String getParkFlag() {
            return parkFlag;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
        }

        public String getDesc() {
            return desc;
        }
    }

    public PoiDetailInfo getPoiDetailInfo() {
        return poiDetailInfo;
    }

}
