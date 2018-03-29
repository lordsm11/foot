package com.egencia.enumeration;

public enum WorldCupGroup {

    GROUP_A("GROUP A"),
    GROUP_B("GROUP B"),
    GROUP_C("GROUP C"),
    GROUP_D("GROUP D"),
    GROUP_E("GROUP E"),
    GROUP_F("GROUP F"),
    GROUP_G("GROUP G"),
    GROUP_H("GROUP H"),
    ROUND_16("8 Final"),
    ROUND_8("4 Final"),
    ROUND_4("2 Final"),
    ROUND_2_LOOSER("Final Looser"),
    ROUND_2("Final");

    private String label;

    WorldCupGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
