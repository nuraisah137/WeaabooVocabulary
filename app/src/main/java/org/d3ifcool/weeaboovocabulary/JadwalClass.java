package org.d3ifcool.weeaboovocabulary;

import java.io.Serializable;

/**
 * Created by user on 3/4/2018.
 */

/**
 * Jadwal Class
 */

public class JadwalClass implements Serializable{

    /**
     * Deklarasi Variable
     */

    private int mImg;
    private String jam,menit;
    private int status;

    /**
     * Constructor Jadwal Class
     */

    public JadwalClass(String jam, String menit) {
        this.jam = jam;
        this.menit = menit;
        if (Integer.parseInt(this.jam )>=18){
            this.mImg = R.mipmap.ic_month;
        }else{
            this.mImg = R.mipmap.ic_sun;
        }
        status = 0;

    }

    /**
     * Get Method Jadwal Class
     */

    public int getmImg() {
        return mImg;
    }

    public String getJam() {
        return jam;
    }

    public String getMenit() {
        return menit;
    }

    public int getStatus() {
        return status;
    }

    /**
     * Set Method Status
     */

    public void setStatus(int status) {
        this.status = status;
    }
}

