package com.hc.sys.netty.ccalculationcolor;

import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.common.global.BeanUtil;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.entity.Illuminantrelativeenergydistribution;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import com.hc.sys.core.dye.service.IlluminantService;
import com.hc.sys.core.dye.service.IlluminantobserverxyzService;
import com.hc.sys.core.dye.service.IlluminantrelativeenergydistributionService;
import com.hc.sys.core.dye.service.IlluminantwhitexyzService;

import java.util.*;

/**
 * @Description: DBUtilTool
 * @Author: fangyong
 * @CreateDate: 2018/11/7 16:43
 * @Version: 1.0.0.0
 */
public class DBUtilTool {
    private static DBUtilTool dbUtilTool = new DBUtilTool();
    public static Map<String, Integer> m_mapIllIdByName = new HashMap<String, Integer>();
    public static Map<Integer, List<Float>> m_mapIlluminantRelativeEnergyDistribution = new HashMap<Integer, List<Float>>();
    public static Map<Integer, Map<Float, XYZ>> m_mapIlluminantWhiteXYZ = new HashMap<Integer, Map<Float, XYZ>>();
    public static Map<Float, List<XYZ>> m_mapObserverXYZ = new HashMap<Float, List<XYZ>>();

    public DBUtilTool() {

    }

    public static DBUtilTool getInstance() {
        return dbUtilTool;
    }

    public void getIlluminant() {
        IlluminantService illuminantService = BeanUtil.getBean(IlluminantService.class);
        List<Illuminant> illuminantList = illuminantService.findAll();
        if (illuminantService.findAll() != null && illuminantService.findAll().size() > 0) {
            for (Illuminant illuminant : illuminantList) {
                m_mapIllIdByName.put(illuminant.getName(), illuminant.getId());

            }
        }

    }

    public void getIlluminantObserverXYZ() {
        IlluminantobserverxyzService illuminantobserverxyzService = BeanUtil.getBean(IlluminantobserverxyzService.class);
        List<Illuminantobserverxyz> illuminantobserverxyzList = illuminantobserverxyzService.findAll();
        if (illuminantobserverxyzService.findAll() != null && illuminantobserverxyzService.findAll().size() > 0) {
            for (Illuminantobserverxyz illuminantobserverxyz : illuminantobserverxyzList) {
                List arrayList = new ArrayList<>();
                if (!m_mapObserverXYZ.isEmpty() && m_mapObserverXYZ.containsKey(illuminantobserverxyz.getAngle())) {
                    m_mapObserverXYZ.get(illuminantobserverxyz.getAngle()).add(new XYZ(illuminantobserverxyz.getX(), illuminantobserverxyz.getY(), illuminantobserverxyz.getZ()));
                } else {
                    arrayList.add(new XYZ(illuminantobserverxyz.getX(), illuminantobserverxyz.getY(), illuminantobserverxyz.getZ()));
                    m_mapObserverXYZ.put(illuminantobserverxyz.getAngle(), arrayList);
                }
            }

        }
    }

    public void getIlluminantrelativeenergydistribution() {
        IlluminantrelativeenergydistributionService illuminantrelativeenergydistributionService = BeanUtil.getBean(IlluminantrelativeenergydistributionService.class);
        List<Illuminantrelativeenergydistribution> illuminantrelativeenergydistributionList = illuminantrelativeenergydistributionService.findAll();
        if (illuminantrelativeenergydistributionList != null && illuminantrelativeenergydistributionList.size() > 0) {
            for (Illuminantrelativeenergydistribution illuminantrelativeenergydistribution : illuminantrelativeenergydistributionList) {
                List valueList = new ArrayList<>();
                if (!m_mapIlluminantRelativeEnergyDistribution.isEmpty() && m_mapIlluminantRelativeEnergyDistribution.containsKey(illuminantrelativeenergydistribution.getId())) {
                    m_mapIlluminantRelativeEnergyDistribution.get(illuminantrelativeenergydistribution.getId()).add(illuminantrelativeenergydistribution.getValue());
                } else {
                    valueList.add(illuminantrelativeenergydistribution.getValue());
                    m_mapIlluminantRelativeEnergyDistribution.put(illuminantrelativeenergydistribution.getId(), valueList);
                }
            }
        }
    }

    public void getIlluminantwhitexyzService() {
        IlluminantwhitexyzService illuminantwhitexyzService = BeanUtil.getBean(IlluminantwhitexyzService.class);
        List<Illuminantwhitexyz> illuminantwhitexyzList = illuminantwhitexyzService.findAll();
        if (illuminantwhitexyzList != null && illuminantwhitexyzList.size() > 0) {
            for (Illuminantwhitexyz illuminantwhitexyz : illuminantwhitexyzList) {
                Map<Float, XYZ> map = new HashMap();
                map.put(illuminantwhitexyz.getAngle(), new XYZ(illuminantwhitexyz.getX(), illuminantwhitexyz.getY(), illuminantwhitexyz.getZ()));
                if (!m_mapIlluminantWhiteXYZ.isEmpty() && m_mapIlluminantWhiteXYZ.containsKey(illuminantwhitexyz.getId())) {
                    m_mapIlluminantWhiteXYZ.get(illuminantwhitexyz.getId()).put(illuminantwhitexyz.getAngle(), new XYZ(illuminantwhitexyz.getX(), illuminantwhitexyz.getY(), illuminantwhitexyz.getZ()));
                } else {
                    m_mapIlluminantWhiteXYZ.put(illuminantwhitexyz.getId(), map);
                }

            }
        }
    }

    public void getAll() {
        getIlluminantrelativeenergydistribution();
        getIlluminantwhitexyzService();
        getIlluminantObserverXYZ();
        getIlluminant();
    }

    /**
     * @description /CMC(2:1)色差计算
     * @author: fangyong
     * @date 2018/11/7 20:34
     */
    public  double getCMC(LAB lab1, LAB lab2, float pl, float pc) {
        return CCalculationColor.DeltaECMC(lab1, lab2, pl, pc);
    }

    /**
     * @description /CIE2000色差计算
     * @author: fangyong
     * @date 2018/11/7 20:34
     */
    public  double DeltaECIE2000(LAB lab1, LAB lab2, float Kl, float Kc, float Kh) {
        return CCalculationColor.DeltaECIE2000(lab1, lab2, Kl, Kc, Kh);
    }

    /**
     * @description RefToXYZ
     * @author: fangyong
     * @date 2018/11/8 9:09
     */
    public  XYZ RefToXYZ(List<Float> vecRef, String name, float angle) {
        return XYZ.RefToXYZ(vecRef, name, angle);
    }


    /**
     * @description RefToRGB
     * @author: fangyong
     * @date 2018/11/8 9:13
     */
    public  SRGB RefToRGB(List<Float> vecRef, String name, float angle) {
        return SRGB.RefToRGB(vecRef, name, angle);
    }
    /**
     * @description RefToRGB
     * @author: fangyong
     * @date 2018/11/8 9:13
     */
    public  int rgb(List<Float> vecRef, String name, float angle) {
        SRGB srgb=SRGB.RefToRGB(vecRef, name, angle);
        String bgr=Integer.toHexString(srgb.getB())+Integer.toHexString(srgb.getG())+Integer.toHexString(srgb.getR());
        if (StringUtil.isBlank(bgr))
             new Exception("rgb转换异常！");
        return  Integer.parseInt(bgr,16);
    }
    /**
     * @description XYZToRGB
     * @author: fangyong
     * @date 2018/11/8 9:15
     */
    public  SRGB XYZToRGB(XYZ xyz) {
        return SRGB.XYZToRGB(xyz);
    }

    /**
     * @description RefToLab
     * @author: fangyong
     * @date 2018/11/8 9:14
     */
    public  LAB RefToLab(List<Float> vecRef, String name, float angle) {
        return LAB.RefToLab(vecRef, name, angle);
    }

    /**
     * @description XYZToLab
     * @author: fangyong
     * @date 2018/11/8 9:13
     */
    public  LAB XYZToLab(XYZ xyz, String name, float angle) {
        return LAB.XYZToLab(xyz, name, angle);
    }

    /**
     * @description LabToLCH
     * @author: fangyong
     * @date 2018/11/8 9:17
     */
    public  LCH LabToLCH(LAB lab) {
        return LCH.LabToLCH(lab);
    }
}
