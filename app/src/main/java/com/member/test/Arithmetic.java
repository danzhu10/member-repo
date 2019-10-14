package com.member.test;

public class Arithmetic {

    public static int progressToMoney(int progress, int min, int max) {
//        LogUtils.e("借款金额滑动进度:----------->" + progress);
        int money = 0;
        if (progress <= 0) {
            return min;
        } else if (progress > 0 && progress < 50) {
            money = (int) Math.floor(max * progress / 50);
            if (max > 10000) {
                if (money < min) {
                    return min;
                }
                if (money % 1000 != 0) {
                    int a = money / 1000; // 取7
                    a++;
                    money = a * 1000;
                }
            } else {
                if (money <= min) {
                    return min + (int) Math.floor(max * 1 / 50);
                }
            }
            return money;
        } else if (progress >= 50) {
            return max;
        }
        return money;
    }

    public static String progressToWeek(int progress, int min, int max, int minType) {
//        LogUtils.e("借款周期滑动进度:----------->" + progress);
        int week = 0;
        if (progress <= 0) {
            if (minType == 1) {
                return min + "天";
            }
            return min + "周";
        } else if (progress > 0 && progress < 50) {
            if (minType == 1) {
                week = (int) Math.floor(max * progress / 50); // 周
                // 最小是天,最大是周
                if (week * 7 > min) {
                    return week + "周";
                }
                return min + "天";
            } else if (minType == 2) {
                week = (int) Math.floor((max - min) * progress / 50); // 周
                if (min + week <= min) {
                    return min + "周";
                }
                return min + week + "周";
            }
            return week + "周";
        } else if (progress >= 50) {
            return max + "周";
        }
        return week + "周";
    }
}
