package org.d3ifcool.weeaboovocabulary;

import java.util.ArrayList;

/**
 * Created by user on 3/11/2018.
 */

/**
 * Control Kosakata
 */

public class ControlKosakata {

    /**
     * Decklaration Array Kosakata
     */

    private ArrayList<KosakataClass> array_kosakata;

    /**
     * Constructor Control Kosakata
     */

    public ControlKosakata() {
        this.array_kosakata = new ArrayList<>();
        this.array_kosakata.add(new KosakataClass("okiru","bangun"));
        this.array_kosakata.add(new KosakataClass("nemuru","tidur"));
        this.array_kosakata.add(new KosakataClass("abiru","mandi"));
        this.array_kosakata.add(new KosakataClass("arau","mencuci"));
        this.array_kosakata.add(new KosakataClass("naku","menangis"));
    }

    /**
     * Get Array Kosakata
     */

    public ArrayList<KosakataClass> getArray_kosakata() {
        return array_kosakata;
    }
}
