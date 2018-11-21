package com.hc.sys.netty.ccalculationcolor;

/**
 * @Description: LCH
 * @Author: fangyong
 * @CreateDate: 2018/11/7 17:58
 * @Version: 1.0.0.0
 */
public class LCH {
    double	l;
    double	c;
    double	h;
  public  static   LCH LabToLCH( LAB  lab)
    {
	 float PI = 3.1415926f;
        LCH lch=null;
        lch.l = lab.getL();
        lch.c = Math.sqrt(lab.getA()*lab.getA() + lab.getB()*lab.getB());
        lch.h = Math.atan2(lab.getB(), lab.getA());	//反正切函数arctan，值为[-pi,+pi]
        lch.h = lch.h / PI * 180;		//转换成角度
        if (lch.h < 0)
            lch.h += 360;
        return lch;
    }
}
