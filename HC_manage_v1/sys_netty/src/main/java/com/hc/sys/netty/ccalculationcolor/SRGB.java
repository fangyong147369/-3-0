package com.hc.sys.netty.ccalculationcolor;

import com.hc.sys.common.util.ByteDoubleTool;

import java.util.Arrays;
import java.util.List;

import static com.hc.sys.netty.ccalculationcolor.XYZ.RefToXYZ;

/**
 * @Description: SRGB
 * @Author: fangyong
 * @CreateDate: 2018/11/7 17:58
 * @Version: 1.0.0.0
 */
public class SRGB {
    SRGB() {
    }

    SRGB(int r1, int g1, int b1) {
        this.r = r1;
        this.g = g1;
        this.b = b1;
    }

    private int r;
    private int g;
    private int b;

    public static SRGB RefToRGB(List<Float> vecRef, String name, float angle) {
        return XYZToRGB(RefToXYZ(vecRef, name, angle));
    }

    public static SRGB XYZToRGB(XYZ xyz) {
        float x = xyz.getX() / 100;
        float y = xyz.getY() / 100;
        float z = xyz.getZ() / 100;
        float r = (float) (3.240479 * x - 1.537150 * y - 0.498535 * z);
        float g = (float) (-0.969256 * x + 1.875992 * y + 0.041556 * z);
        float b = (float) (0.055648 * x - 0.204043 * y + 1.057311 * z);
        return new SRGB(Math.abs((new Double(Math.min(compareFunc(r) * 255, 255))).intValue()), Math.abs((new Double(Math.min(compareFunc(g) * 255, 255))).intValue()), Math.abs((new Double(Math.min(compareFunc(b) * 255, 255))).intValue()));
    }

    private static double compareFunc(float t) {
        if (t > 0.0031308f)
            return (1.055 * Math.pow(t, (1 / 2.4)) - 0.055);
        else
            return 12.92 * t;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
