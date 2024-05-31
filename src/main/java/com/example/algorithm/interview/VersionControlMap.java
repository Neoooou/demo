package com.example.algorithm.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author neo.zr
 * @since 2024/4/12
 */

public class VersionControlMap {
    Map<Integer, List<VersionEntry>> map = new HashMap<Integer, List<VersionEntry>>();

    AtomicInteger version = new AtomicInteger(0);

    public void put(int key, int val) {
        // lock

        if (map.containsKey(key)) {
            map.get(key).add(new VersionEntry(val, version.getAndIncrement()));
        } else {
            List<VersionEntry> versionEntries = new ArrayList<>();
            versionEntries.add(new VersionEntry(val, version.getAndIncrement()));
            map.put(key, versionEntries);
        }
    }

    public Integer get(int key) {
        if (map.containsKey(key)) {
            List<VersionEntry> versionEntries = map.get(key);
            return versionEntries.get(versionEntries.size() - 1).getVal();
        }
        return null;
    }

    public Integer get(int key, int version) {
        if (map.containsKey(key)) {
            List<VersionEntry> versionEntries = map.get(key);

            if (version < versionEntries.get(0).getVersion()) {
                // 小于第一个版本号
                return null;
            }

            for (int i = 0; i < versionEntries.size() - 1; ++i) {
                VersionEntry versionEntry = versionEntries.get(i);
                if (version < versionEntries.get(i + 1).getVersion()) {
                    return versionEntry.getVal();
                }
            }

            return versionEntries.get(versionEntries.size()).getVal();
        }

        return null;
    }

    public void remove(int key) {
        map.remove(key);
        version.getAndIncrement();
    }


    @Data
    @AllArgsConstructor
    static class VersionEntry {
        int version;
        int val;
    }
}
