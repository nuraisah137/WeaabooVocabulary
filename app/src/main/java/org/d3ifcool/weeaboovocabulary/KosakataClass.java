package org.d3ifcool.weeaboovocabulary;

/**
 * Created by user on 3/11/2018.
 */

/**
 * Kosakata Class
 */

public class KosakataClass {

    /**
     * Deklarasi Variabel
     */

    private String TextJepang,TextIndo;

    /**
     * Constructor
     */

    public KosakataClass(String textJepang, String textIndo) {
        TextJepang = textJepang;
        TextIndo = textIndo;
    }

    /**
     * Get Method
     */

    public String getTextJepang() {
        return TextJepang;
    }

    public String getTextIndo() {
        return TextIndo;
    }
}
