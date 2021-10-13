package com.reactor.demo.util;



public enum HourRange {
    HOUR_0("00", "00:00 - 01:00", "HOUR_0"),
    HOUR_1("01", "01:00 - 02:00", "HOUR_1"),
    HOUR_2("02", "02:00 - 03:00", "HOUR_2"),
    HOUR_3("03", "03:00 - 04:00", "HOUR_3"),
    HOUR_4("04", "04:00 - 05:00", "HOUR_4"),
    HOUR_5("05", "05:00 - 06:00", "HOUR_5"),
    HOUR_6("06", "06:00 - 07:00", "HOUR_6"),
    HOUR_7("07", "07:00 - 08:00", "HOUR_7"),
    HOUR_8("08", "08:00 - 09:00", "HOUR_8"),
    HOUR_9("09", "09:00 - 10:00", "HOUR_9"),
    HOUR_10("10", "10:00 - 11:00", "HOUR_10"),
    HOUR_11("11", "11:00 - 12:00", "HOUR_11"),
    HOUR_12("12", "12:00 - 13:00", "HOUR_12"),
    HOUR_13("13", "13:00 - 14:00", "HOUR_13"),
    HOUR_14("14", "14:00 - 15:00", "HOUR_14"),
    HOUR_15("15", "15:00 - 16:00", "HOUR_15"),
    HOUR_16("16", "16:00 - 17:00", "HOUR_16"),
    HOUR_17("17", "17:00 - 18:00", "HOUR_17"),
    HOUR_18("18", "18:00 - 19:00", "HOUR_18"),
    HOUR_19("19", "19:00 - 20:00", "HOUR_19"),
    HOUR_20("20", "20:00 - 21:00", "HOUR_20"),
    HOUR_21("21", "21:00 - 22:00", "HOUR_21"),
    HOUR_22("22", "22:00 - 23:00", "HOUR_22"),
    HOUR_23("23", "23:00 - 24:00", "HOUR_23");

    private String hour;
    private String rangeBetweenHours;
    private String name;

    HourRange(String hour, String rangeBetweenHours,String name) {
        this.hour = hour;
        this.rangeBetweenHours=rangeBetweenHours;
        this.name=name;
    }

    public String getHour() {
        return hour;
    }

    public String getRangeBetweenHours() {
        return rangeBetweenHours;
    }

    public String getName() {
        return name;
    }
}
