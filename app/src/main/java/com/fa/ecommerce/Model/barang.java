package com.fa.ecommerce.Model;

public class barang {

    public String pkey,pdescription,pprice,pname;
    public String pdate,ptime,pimage,pcategory;
    //public int fotobarang;

    public barang(){

    }

    public barang(String pkey, String pdescription, String pprice, String pname, String pdate, String ptime, String pimage, String pcategory) {
        this.pkey = pkey;
        this.pdescription = pdescription;
        this.pprice = pprice;
        this.pname = pname;
        this.pdate = pdate;
        this.ptime = ptime;
        this.pimage = pimage;
        this.pcategory = pcategory;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPcategory() {
        return pcategory;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }
}
