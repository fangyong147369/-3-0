package com.hc.sys.netty.ccalculationcolor;
import com.hc.sys.common.util.log.LogUtil;

import java.util.List;

import static com.hc.sys.netty.ccalculationcolor.XYZ.RefToXYZ;

/**
 * @Description: LAB
 * @Author: fangyong
 * @CreateDate: 2018/11/7 17:57
 * @Version: 1.0.0.0
 */
public class LAB {
    LAB() {}
    LAB(float x1, float y1, float z1){
        this.l=x1;
        this.a=y1;
        this.b=z1;
    }
    private   float	l;
    private   float	a;
    private   float	b;
    public static  LAB RefToLab(List<Float> vecRef,String  name, float angle)
    {
        try {
            return XYZToLab(RefToXYZ(vecRef, name, angle), name, angle);
        }catch (Exception e){
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
        return XYZToLab(RefToXYZ(vecRef, name, angle), name, angle);
    }

    public static LAB XYZToLab( XYZ  xyz,String name, float angle)
    {
        int illId = XYZ.getIlluminantIdByName(name);
        if (illId == 0)
            return new  LAB(0, 0, 0);

	 XYZ XYZ0 = XYZ.getIlluminantWhiteXYZ(illId, angle);
        if (XYZ0 == null) {
            return new LAB(116 * compareFunc(0) - 16,500 * (compareFunc(0) - compareFunc(0)),200 * (compareFunc(0) - compareFunc(0)));
        }
        float X0 = XYZ0.getX();
        float Y0 = XYZ0.getY();
        float Z0 = XYZ0.getZ();
        return new LAB(116 * compareFunc(xyz.getY() / Y0) - 16,500 * (compareFunc(xyz.getX()/ X0) - compareFunc(xyz.getY() / Y0)),200 * (compareFunc(xyz.getY() / Y0) - compareFunc(xyz.getZ() / Z0)));
    }
    private static  float compareFunc (float t)
    {
        double theta = (float)Math.pow(6.0 / 29.0, 3);
        if (t > theta)
            return (float)Math.pow(t, 1.0 / 3.0);
        else
            return (float)(1.0 / 3.0 * Math.sqrt(29.0 / 6.0) * t + 16.0 / 116.0);
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

}
