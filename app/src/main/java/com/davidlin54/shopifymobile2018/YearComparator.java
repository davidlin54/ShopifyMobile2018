package com.davidlin54.shopifymobile2018;

import java.util.Comparator;

public class YearComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer integer, Integer t1) {
        if (integer > t1) {
            return -1;
        } else if (integer < t1) {
            return 1;
        }

        return 0;
    }
}
