package com.hc.sys.netty.ccalculationcolor;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @Description: CCalculationColor
 * @Author: fangyong
 * @CreateDate: 2018/11/7 18:00
 * @Version: 1.0.0.0
 */
public class CCalculationColor {
    public  static double DeltaECMC(LAB lab1, LAB lab2, float pl, float pc) {
        float PI = 3.1415926f;
        float L = lab1.getL();
        float a = lab1.getA();
        float b = lab1.getB();
        double C_1 = Math.sqrt(a * a + b * b);
        double C_2 = Math.sqrt(lab2.getA() * lab2.getA() + lab2.getB() * lab2.getB());
        float delta_L = lab1.getL() - lab2.getL();
        double delta_C = C_1 - C_2;
        double H_1 = Math.atan2(b, a) / PI * 180;
        if (H_1 < 0)
            H_1 += 360;

        double F = Math.sqrt(Math.pow(C_1, 4) / (Math.pow(C_1, 4) + 1900.0));

        //	noinspection PyChainedComparisons
        double T;
        if (164 <= H_1 && H_1 <= 345)
            T = 0.56 + Math.abs(0.2 * Math.cos((H_1 + 168) / 180 * PI));
        else
            T = 0.36 + Math.abs(0.4 * Math.cos((H_1 + 35) / 180 * PI));

        double S_L;
        if (L < 16)
            S_L = 0.511;
        else
            S_L = (0.040975 * L) / (1 + 0.01765 * L);

        double S_C = ((0.0638 * C_1) / (1 + 0.0131 * C_1)) + 0.638;
        double S_H = S_C * (F * T + 1 - F);

        delta_C = C_1 - C_2;

        double delta_H_sq = -delta_C * delta_C + Math.pow(lab1.getA() - lab2.getA(), 2) + Math.pow(lab1.getB() - lab2.getB(), 2);
        double delta_H = Math.sqrt(delta_H_sq);

        return Math.sqrt(Math.pow(delta_L / (pl * S_L), 2) + Math.pow(delta_C / (pc * S_C), 2) + Math.pow(delta_H / S_H, 2));
    }

    public  static double DeltaECIE2000(LAB lab1, LAB lab2, float Kl, float Kc, float Kh) {
        float PI = 3.1415926f;
        float L = lab1.getL();
        float a = lab1.getA();
        float b = lab1.getB();

        double avg_Lp = (L + lab2.getL()) / 2.0;

        double C1 = Math.sqrt(a * a + b * b);
        double C2 = Math.sqrt(lab2.getA() * lab2.getA() + lab2.getB() * lab2.getB());

        double avg_C1_C2 = (C1 + C2) / 2.0;

        double G = 0.5 * (1 - Math.sqrt(Math.pow(avg_C1_C2, 7.0) / (Math.pow(avg_C1_C2, 7.0) + Math.pow(25.0, 7.0))));

        double a1p = (1.0 + G) * a;
        double a2p = (1.0 + G) * lab2.getA();

        double C1p = Math.sqrt(Math.pow(a1p, 2) + Math.pow(b, 2));
        double C2p = Math.sqrt(Math.pow(a2p, 2) + Math.pow(lab2.getB(), 2));

        double avg_C1p_C2p = (C1p + C2p) / 2.0;

        double h1p = Math.atan2(b, a1p) / PI * 180;
        if (h1p < 0)
            h1p += 360;

        double h2p = Math.atan2(lab2.getB(), a2p) / PI * 180;
        if (h2p < 0)
            h2p += 360;

        double avg_Hp = (((Math.abs(h1p - h2p) > 180 ? 1 : 0) * 360) + h1p + h2p) / 2.0;
        double T = 1 - 0.17 * Math.cos((avg_Hp - 30) / 180 * PI) +
                0.24 * Math.cos((2 * avg_Hp) / 180 * PI) +
                0.32 * Math.cos((3 * avg_Hp + 6) / 180 * PI) -
                0.2 * Math.cos((4 * avg_Hp - 63) / 180 * PI);

        double diff_h2p_h1p = h2p - h1p;
        double delta_hp = diff_h2p_h1p + (Math.abs(diff_h2p_h1p) > 180 ? 1 : 0) * 360;
        if (h2p > h1p)
            delta_hp -= 720;

        float delta_Lp = lab2.getL() - L;
        double delta_Cp = C2p - C1p;
        double delta_Hp = 2 * Math.sqrt(C2p * C1p) * Math.sin(delta_hp / 180 * PI / 2.0);

        double S_L = 1 + ((0.015 * Math.pow(avg_Lp - 50, 2)) / Math.sqrt(20 + Math.pow(avg_Lp - 50, 2.0)));
        double S_C = 1 + 0.045 * avg_C1p_C2p;
        double S_H = 1 + 0.015 * avg_C1p_C2p * T;

        double delta_ro = 30 * Math.exp(-(Math.pow(((avg_Hp - 275) / 25), 2.0)));
        double R_C = Math.sqrt((Math.pow(avg_C1p_C2p, 7.0)) / (Math.pow(avg_C1p_C2p, 7.0) + Math.pow(25.0, 7.0)));
        double R_T = -2 * R_C * Math.sin(2 * delta_ro / 180 * PI);

        return Math.sqrt(
                Math.pow(delta_Lp / (S_L * Kl), 2) +
                        Math.pow(delta_Cp / (S_C * Kc), 2) +
                        Math.pow(delta_Hp / (S_H * Kh), 2) +
                        R_T * (delta_Cp / (S_C * Kc)) * (delta_Hp / (S_H * Kh)));
    }
}

