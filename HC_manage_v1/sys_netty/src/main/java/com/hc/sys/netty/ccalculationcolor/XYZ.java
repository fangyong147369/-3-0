package com.hc.sys.netty.ccalculationcolor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: XYZ
 * @Author: fangyong
 * @CreateDate: 2018/11/7 17:56
 * @Version: 1.0.0.0
 */
public class XYZ {

    private float x;
    private float y;
    private float z;
    XYZ(float x1, float y1, float z1) {
        super();
        this.setX(x1);
        this.setY(y1);
        this.setZ(z1);
    }

    public static XYZ RefToXYZ(List<Float> vecRef, String name, Float angle) {
        int illId = getIlluminantIdByName(name);
        if (illId == 0)
            return new XYZ(0, 0, 0);
        List<Float> pRelativeEnergyDistribution = getIlluminantRelativeEnergyDistribution(illId);
        if (pRelativeEnergyDistribution == null)
            return new XYZ(0, 0, 0);
        List<XYZ> obsXYZ = getObserverXYZ(angle);
        if (obsXYZ == null)
            return new XYZ(0, 0, 0);
        List<Float> sx = new ArrayList<Float>();
        List<Float> sy = new ArrayList<Float>();
        List<Float> sz = new ArrayList<Float>();
        System.out.println("pRelativeEnergyDistribution.size=="+pRelativeEnergyDistribution.size());
        System.out.println("obsXYZ.size=="+obsXYZ.size());
        System.out.println("vecRef.size=="+vecRef.size());
        if (pRelativeEnergyDistribution != null && pRelativeEnergyDistribution.size() > 0) {
            for (int i = 0; i < pRelativeEnergyDistribution.size(); ++i) {
                    sx.add((pRelativeEnergyDistribution).get(i) * (obsXYZ).get(i).getX());
                    sy.add((pRelativeEnergyDistribution).get(i) * (obsXYZ).get(i).getY());
                    sz.add((pRelativeEnergyDistribution).get(i) * (obsXYZ).get(i).getZ());
            }
        }
        System.out.println("sz.size=="+sz.size());
        float ssy = 0;
        for (int i = 0; i < sy.size(); ++i) {
            ssy += sy.get(i);
        }
        float srx = 0;
        float sry = 0;
        float srz = 0;
        if (sz != null && sz.size() > 0) {
            for (int i = 0; i < sz.size(); ++i) {
                    srx += sx.get(i) * vecRef.get(i);
                    sry += sy.get(i) * vecRef.get(i);
                    srz += sz.get(i) * vecRef.get(i);
            }
        }
        float k = 100 / (ssy * 10);
        return new XYZ(k * srx * 10, k * sry * 10, k * srz * 10);
    }

    public static int getIlluminantIdByName(String name) {
        if (!m_mapIllIdByName.containsKey(name))
            return 0;
        return m_mapIllIdByName.get(name);
    }

    public static XYZ getIlluminantWhiteXYZ(int illId, float angle) {
        if (!m_mapIlluminantWhiteXYZ.containsKey(illId))
            return null;
        Map<Float, XYZ> map = m_mapIlluminantWhiteXYZ.get(illId);
        if (!map.containsKey(angle))
            return null;
        return map.get(angle);
    }


    public static List<Float> getIlluminantRelativeEnergyDistribution(int illId) {
        if (!m_mapIlluminantRelativeEnergyDistribution.containsKey(illId)) {
            return null;
        }
        return m_mapIlluminantRelativeEnergyDistribution.get(illId);

    }

    public static List<XYZ> getObserverXYZ(float angle) {
        if (!m_mapObserverXYZ.containsKey(angle)) {
            return null;
        }
        return m_mapObserverXYZ.get(angle);
    }

    public static Map<String, Integer> m_mapIllIdByName = DBUtilTool.getInstance().m_mapIllIdByName;
    public static Map<Integer, List<Float>> m_mapIlluminantRelativeEnergyDistribution = DBUtilTool.getInstance().m_mapIlluminantRelativeEnergyDistribution;
    public static Map<Integer, Map<Float, XYZ>> m_mapIlluminantWhiteXYZ = DBUtilTool.getInstance().m_mapIlluminantWhiteXYZ;
    public static Map<Float, List<XYZ>> m_mapObserverXYZ = DBUtilTool.getInstance().m_mapObserverXYZ;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
