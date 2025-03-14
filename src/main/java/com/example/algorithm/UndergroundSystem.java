package com.example.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ranz
 * @Since: 2025/3/13
 */
public class UndergroundSystem {
    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */
    Map<String, CostTime> costTimeMap;

    Map<Integer, CheckinInfo> checkinPax;

    public UndergroundSystem() {
        costTimeMap = new HashMap<>();
        checkinPax = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkinPax.put(id, new CheckinInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckinInfo checkinInfo = checkinPax.get(id);
        checkinPax.remove(id);
        String costTimeKey = key(checkinInfo.checkinStation, stationName);

        costTimeMap.putIfAbsent(costTimeKey, new CostTime(0, 0));

        CostTime costTime = costTimeMap.get(costTimeKey);
        costTime.setTotalPax(costTime.totalPax + 1);
        costTime.setTotalTime(costTime.totalTime + t - checkinInfo.checkinTimestamp);

    }

    private String key(String fromStation, String toStation) {
        return String.format("%s2%s", fromStation, toStation);
    }

    public double getAverageTime(String startStation, String endStation) {
        CostTime costTime = costTimeMap.get(key(startStation, endStation));
        if(costTime == null){
            return 0.0D;
        }

        return (double)costTime.totalTime / costTime.totalPax;
    }

    static class CheckinInfo {
        String checkinStation;
        int checkinTimestamp;

        CheckinInfo(String stationName, int t) {
            this.checkinStation = stationName;
            this.checkinTimestamp = t;
        }
    }

    static class CostTime {
        long totalPax;
        long totalTime;

        CostTime(long totalPax, long totalTime) {
            this.totalPax = totalPax;
            this.totalTime = totalTime;
        }

        public long getTotalPax() {
            return totalPax;
        }

        public CostTime setTotalPax(long totalPax) {
            this.totalPax = totalPax;
            return this;
        }

        public long getTotalTime() {
            return totalTime;
        }

        public CostTime setTotalTime(long totalTime) {
            this.totalTime = totalTime;
            return this;
        }
    }


}
