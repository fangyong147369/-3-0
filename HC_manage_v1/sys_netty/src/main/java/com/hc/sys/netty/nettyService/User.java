package com.hc.sys.netty.nettyService;

import java.util.Date;
import java.util.List;

import com.google.protobuf.MessageLite;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.entity.*;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.*;
import com.hc.sys.netty.protobuf.FormulaProto;
import io.netty.channel.ChannelId;

public class User {
    public User() {

    }

    private String username;
    private String password;
    protected int m_id = 0;

    public boolean create(ChannelId channelId, int accId, String username, String password) {
        m_channelId = channelId;
        this.m_id = accId;
        this.username = username;
        this.password = password;
        return true;
    }

    public int getId() {
        return m_id;
    }

    protected ChannelId m_channelId = null;


    public void sendPacket(FormulaProto.MessageType opcode, MessageLite msg) {
        FormulaInputService.getInstance().sendPacket(m_channelId, opcode, msg);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @description染料列表
     * @author: fangyong
     * @date 2018/10/27 19:35
     */
    public void dyeList(List<DyeModel> dyeModelList, List<DyeType> dyeTypeList, List<DyeManufacturer> dyeManufacturerList, List<DyeColor> dyeColorList, List<DyeGroup> dyeGroupList) {
        FormulaProto.smsg_res_dyes_list.Builder res = FormulaProto.smsg_res_dyes_list.newBuilder();
        long startTime = new Date().getTime();
        if (dyeModelList != null && dyeModelList.size() > 0) {
            for (DyeModel dyeModel : dyeModelList) {
                FormulaProto.dye.Builder dyes = FormulaProto.dye.newBuilder();
                dyes.setId(StringUtil.toInt(String.valueOf(dyeModel.getId())));
                dyes.setName(dyeModel.getName());
                dyes.setStrength(dyeModel.getStrength());
                dyes.setDescription(dyeModel.getDescription());
                dyes.setAlias(dyeModel.getAlias());
                dyes.setExterior(dyeModel.getExterior());
                dyes.setPrice((float) dyeModel.getPrice());
                if (dyeModel.getDyeManufacturerModel() != null) {
                    dyes.setManufacturerId(StringUtil.toInt(String.valueOf(dyeModel.getDyeManufacturerModel().getId())));
                }
                if (dyeModel.getDyeTypeModel() != null) {
                    dyes.setDyeTypeId(StringUtil.toInt(String.valueOf(dyeModel.getDyeTypeModel().getId())));
                }
                if (dyeModel.getDyeColorModel() != null) {
                    dyes.setDyeColorId(StringUtil.toInt(String.valueOf(dyeModel.getDyeColorModel().getId())));
                }
                res.addLstDye(dyes.build());
            }
        } else {
            FormulaProto.dye.Builder dyes = FormulaProto.dye.newBuilder();
            res.addLstDye(dyes.build());
        }
        if (dyeTypeList != null && dyeTypeList.size() > 0) {
            for (DyeType dyeType : dyeTypeList) {
                FormulaProto.dye_type.Builder dye_type = FormulaProto.dye_type.newBuilder();
                dye_type.setId(StringUtil.toInt(String.valueOf(dyeType.getId())));
                dye_type.setName(dyeType.getName());
                dye_type.setAlias(dyeType.getAlias());
                dye_type.setDescription(dyeType.getDescription());
                res.addLstDyeType(dye_type.build());
            }
        } else {
            FormulaProto.dye_type.Builder dye_type = FormulaProto.dye_type.newBuilder();
            res.addLstDyeType(dye_type.build());
        }
        if (dyeColorList != null && dyeColorList.size() > 0) {
            for (DyeColor dyeColor : dyeColorList) {
                FormulaProto.dye_color.Builder dye_color = FormulaProto.dye_color.newBuilder();
                dye_color.setId(StringUtil.toInt(String.valueOf(dyeColor.getId())));
                dye_color.setName(dyeColor.getName());
                dye_color.setAlias(dyeColor.getAlias());
                dye_color.setRgb(dyeColor.getRgb());
                res.addLstDyeColor(dye_color.build());
            }
        } else {
            FormulaProto.dye_color.Builder dye_color = FormulaProto.dye_color.newBuilder();
            res.addLstDyeColor(dye_color.build());
        }
        if (dyeManufacturerList != null && dyeManufacturerList.size() > 0) {
            for (DyeManufacturer dyeManufacturer : dyeManufacturerList) {
                FormulaProto.dye_manufacturer.Builder dye_manufacturer = FormulaProto.dye_manufacturer.newBuilder();
                dye_manufacturer.setId(StringUtil.toInt(String.valueOf(dyeManufacturer.getId())));
                dye_manufacturer.setCompany(dyeManufacturer.getCompany());
                dye_manufacturer.setAddress(dyeManufacturer.getAddress());
                dye_manufacturer.setCity(dyeManufacturer.getCity());
                dye_manufacturer.setContact(dyeManufacturer.getContact());
                dye_manufacturer.setCountry(dyeManufacturer.getCountry());
                dye_manufacturer.setEmail(dyeManufacturer.getEmail());
                dye_manufacturer.setPhoneNumber(dyeManufacturer.getPhoneNumber());
                dye_manufacturer.setFaxNumber(dyeManufacturer.getFaxNumber());
                dye_manufacturer.setZipcode(dyeManufacturer.getZipcode());
                dye_manufacturer.setState(dyeManufacturer.getState());
                res.addLstDyeManufacturer(dye_manufacturer.build());
            }
        } else {
            FormulaProto.dye_manufacturer.Builder dye_manufacturer = FormulaProto.dye_manufacturer.newBuilder();
            res.addLstDyeManufacturer(dye_manufacturer.build());
        }
        if (dyeGroupList != null && dyeGroupList.size() > 0) {
            for (DyeGroup dyeGroup : dyeGroupList) {
                FormulaProto.dye_group.Builder dye_group = FormulaProto.dye_group.newBuilder();
                dye_group.setId(Integer.parseInt(String.valueOf(dyeGroup.getId())));
                dye_group.setName(dyeGroup.getName());
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid1())) > 0)
                    dye_group.addDyeId(Integer.parseInt(String.valueOf(dyeGroup.getDyeid1())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid2())) > 0)
                    dye_group.addDyeId(Integer.parseInt(String.valueOf(dyeGroup.getDyeid2())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid3())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid3())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid4())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid4())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid5())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid5())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid6())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid6())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid7())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid7())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid8())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid8())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid9())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid9())));
                if (Integer.parseInt(String.valueOf(dyeGroup.getDyeid10())) > 0)
                    dye_group.addDyeId(Integer.parseInt(Long.toString(dyeGroup.getDyeid10())));
                res.addLstDyeGroup(dye_group.build());
            }
        } else {
            FormulaProto.dye_group.Builder dye_group = FormulaProto.dye_group.newBuilder();
            res.addLstDyeGroup(dye_group.build());
        }
        long endTime = new Date().getTime();
        LogUtil.info("本次染料相关查询耗时: " + (endTime - startTime)/1000+ "  秒");
        sendPacket(FormulaProto.MessageType.SMSG_RES_DYES_LIST, res.build());
    }

    /**
     * @description 工艺列表
     * @author: fangyong
     * @date 2018/11/15 19:20
     */
    public void technologyModelList(List<TechnologyModel> modelList) {
        FormulaProto.smsg_res_technology_list.Builder res = FormulaProto.smsg_res_technology_list.newBuilder();
        FormulaProto.technology.Builder technology = FormulaProto.technology.newBuilder();
        for (TechnologyModel TechnologyModel : modelList) {
            technology.setId(StringUtil.toInt(String.valueOf(TechnologyModel.getId())));
            technology.setName(TechnologyModel.getName());
            technology.setLiquorRatio(TechnologyModel.getLiquorRatio());
            technology.setHeatingRate(TechnologyModel.getHeatingRate());
            technology.setHeatingTime(TechnologyModel.getHeatingTime());
            technology.setTime(DateUtil.dateStr9(TechnologyModel.getAddTime()));
            technology.setInsulationTime(TechnologyModel.getInsulationTime());
            technology.setDyeingTank(StringUtil.toInt(String.valueOf(TechnologyModel.getDyeingTankModel().getId())));
            technology.setMainPumpSpeed(TechnologyModel.getMainPumpSpeed());
            technology.setCoolDownRate(TechnologyModel.getCoolDownRate());
            technology.setTemperature(TechnologyModel.getTemperature());
            res.addTech(technology.build());
        }
        sendPacket(FormulaProto.MessageType.SMSG_RES_TECHNOLOGY_LIST, res.build());
    }

    /**
     * @染缸厂商列表
     * @author: fangyong
     * @date 2018/11/1 12:38
     */
    public void manutankfacturersList(List<DyeingTankManufacturer> dyeingTankManufacturerList) {
        FormulaProto.smsg_res_dyeing_tank_manufacturer_list.Builder res = FormulaProto.smsg_res_dyeing_tank_manufacturer_list.newBuilder();
        FormulaProto.dyeing_tank_manufacturer.Builder dyeing_tank_manufacturer = FormulaProto.dyeing_tank_manufacturer.newBuilder();
        for (DyeingTankManufacturer dyeingTankManufacturer : dyeingTankManufacturerList) {
            dyeing_tank_manufacturer.setId(StringUtil.toInt(String.valueOf(dyeingTankManufacturer.getId())));
            dyeing_tank_manufacturer.setCompany(dyeingTankManufacturer.getCompany());
            dyeing_tank_manufacturer.setAddress(dyeingTankManufacturer.getAddress());
            dyeing_tank_manufacturer.setCity(dyeingTankManufacturer.getCity());
            dyeing_tank_manufacturer.setContact(dyeingTankManufacturer.getContact());
            dyeing_tank_manufacturer.setCountry(dyeingTankManufacturer.getCountry());
            dyeing_tank_manufacturer.setEmail(dyeingTankManufacturer.getEmail());
            dyeing_tank_manufacturer.setFaxNumber(dyeingTankManufacturer.getFaxNumber());
            dyeing_tank_manufacturer.setZipcode(dyeingTankManufacturer.getZipcode());
            dyeing_tank_manufacturer.setState(dyeingTankManufacturer.getState());
            res.addManu(dyeing_tank_manufacturer.build());
        }
        sendPacket(FormulaProto.MessageType.SMSG_RES_DYEING_TANK_MANUFACTURER_LIST, res.build());
    }

    /**
     * @description@配方列表
     * @author: fangyong
     * @date 2018/11/1 12:48
     */
    public void formulasList(Result result) {
        FormulaProto.smsg_res_formulas_list.Builder res = FormulaProto.smsg_res_formulas_list.newBuilder();
        long startTime = DateUtil.getNow().getTime();
        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
            PageDataList<FormulaModel> pageDataList = (PageDataList<FormulaModel>) result.getData();
            for (FormulaModel formulaModel : pageDataList.getList()) {
                FormulaProto.formula.Builder formula = FormulaProto.formula.newBuilder();
                formula.setId(StringUtil.toInt(String.valueOf(formulaModel.getId())));
                if (formulaModel.getOperatorModel() != null) {
                    formula.setOperatorName(formulaModel.getOperatorModel().getUserName());
                }
                formula.setStatus(formulaModel.getStatus());
                formula.setName(formulaModel.getName());
                formula.setSampleName(formulaModel.getSampleName());
                formula.setType(formulaModel.getType());
                if (formulaModel.getTechnologyModel() != null) {
                    formula.setTechId(StringUtil.toInt(String.valueOf(formulaModel.getTechnologyModel().getId())));
                }
                if (formulaModel.getMaterialModel() != null) {
                    formula.setMaterialId(StringUtil.toInt(String.valueOf(formulaModel.getMaterialModel().getId())));
                }
                if (formulaModel.getDyeGroupModel() != null) {
                    formula.setDyeGroupId(StringUtil.toInt(String.valueOf(formulaModel.getDyeGroupModel().getId())));
                }
                if (formulaModel.getColorantModel() != null) {
                    FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                    colorant.setId(Integer.parseInt(String.valueOf(formulaModel.getColorant().getId())));
                    colorant.setImageFilePath(formulaModel.getColorant().getImagePath());
                    colorant.setIlluminant(formulaModel.getColorant().getIlluminantId());
                    colorant.setIlluminantAngle(formulaModel.getColorant().getAngle());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection400());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection420());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection430());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection440());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection450());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection460());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection470());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection480());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection490());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection500());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection510());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection520());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection530());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection540());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection550());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection560());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection570());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection580());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection590());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection600());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection610());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection620());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection630());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection640());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection650());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection660());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection670());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection680());
                    colorant.addVecReflection(formulaModel.getColorant().getReflection690());
                    formula.setColor(colorant);
                }
                if (formulaModel.getConcentrationModel() != null && formulaModel.getConcentrationModel().size() > 0) {
                    FormulaProto.concentration.Builder concentration = FormulaProto.concentration.newBuilder();
                    for (ConcentrationModel concentrationModel : formulaModel.getConcentrationModel()) {
                        if (concentrationModel.getDyeModel() != null) {
                            concentration.setDyeId(StringUtil.toInt(String.valueOf(concentrationModel.getDyeModel().getId())));
                        }
                        concentration.setConc(concentrationModel.getConcentration());
                        formula.addConc(concentration);
                    }
                }
                formula.setTime(DateUtil.dateStr9(formulaModel.getAddTime()));
                res.addFm(formula.build());
            }

        }
        long endTime = DateUtil.getNow().getTime();
        LogUtil.info("本次查询耗时: " + (endTime - startTime) + "  秒");
        sendPacket(FormulaProto.MessageType.SMSG_RES_FORMULAS_LIST, res.build());
    }

    /**
     * @description@染缸列表
     * @author: fangyong
     * @date 2018/11/1 12:48
     */
    public void manutankList(List<DyeingTank> dyeingTankList) {
        FormulaProto.smsg_res_dyeing_tank_list.Builder res = FormulaProto.smsg_res_dyeing_tank_list.newBuilder();
        FormulaProto.dyeing_tank.Builder dyeing_tank = FormulaProto.dyeing_tank.newBuilder();
        for (DyeingTank dyeingTank : dyeingTankList) {
            dyeing_tank.setId(StringUtil.toInt(String.valueOf(dyeingTank.getId())));
            dyeing_tank.setModel(dyeingTank.getModel());
            dyeing_tank.setName(dyeingTank.getName());
            dyeing_tank.setManufacturerId(StringUtil.toInt(String.valueOf(dyeingTank.getDyeingTankManufacturer().getId())));
            dyeing_tank.setTime(DateUtil.dateStr9(dyeingTank.getAddTime()));
            res.addTanks(dyeing_tank.build());
        }
        sendPacket(FormulaProto.MessageType.SMSG_RES_DYEING_TANK_LIST, res.build());
    }

    /**
     * @description材质列表
     * @author: fangyong
     * @date 2018/10/22 19:35
     */
    public void materialList(List<MaterialModel> modelList, List<MaterialType> materialTypeList, List<MaterialProofingModel> materialProofingModelList) {
        FormulaProto.smsg_res_materials_list.Builder res = FormulaProto.smsg_res_materials_list.newBuilder();
        for (MaterialModel materialModel : modelList) {
            FormulaProto.material.Builder _materials = FormulaProto.material.newBuilder();
            FormulaProto.material_type.Builder material_type = FormulaProto.material_type.newBuilder();
            if (materialModel != null && materialModel.getMaterialType() != null) {
                material_type.setName(materialModel.getMaterialType().getName());
                material_type.setDescription(materialModel.getMaterialType().getDescription());
                material_type.setParentId(materialModel.getMaterialType().getParentId());
                material_type.setId(Integer.parseInt(String.valueOf(materialModel.getMaterialType().getId())));
            }
            _materials.setType(material_type);
            _materials.setId(StringUtil.toInt(String.valueOf(materialModel.getId())));
            _materials.setDipDyeing(materialModel.getDipDyeing());
            _materials.setName(materialModel.getName());
            _materials.setLiquorRatio(materialModel.getLiquorRatio());
            _materials.setAlias(materialModel.getAlias());
            _materials.setWeight(materialModel.getWeight());
            _materials.setDescription(materialModel.getDescription());
            res.addMaterials(_materials.build());
        }
        for (MaterialType materialType : materialTypeList) {
            FormulaProto.material_type.Builder material_type = FormulaProto.material_type.newBuilder();
            material_type.setId(StringUtil.toInt(String.valueOf(materialType.getId())));
            material_type.setDescription(materialType.getDescription());
            material_type.setName(materialType.getName());
            material_type.setParentId(materialType.getParentId());
            res.addTypes(material_type);
        }
        for (MaterialProofingModel materialProofingModel : materialProofingModelList) {
            FormulaProto.material_proofing.Builder material_proofing = FormulaProto.material_proofing.newBuilder();
            FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
            material_proofing.setId(StringUtil.toInt(String.valueOf(materialProofingModel.getId())));
            material_proofing.setConc(materialProofingModel.getConc());
            if (materialProofingModel.getDyeModel() != null) {
                material_proofing.setDyeId(StringUtil.toInt(String.valueOf(materialProofingModel.getDyeModel().getId())));
            }
            if (materialProofingModel.getMaterialModel() != null) {
                material_proofing.setMaterialId(StringUtil.toInt(String.valueOf(materialProofingModel.getMaterialModel().getId())));
            }
            if (materialProofingModel.getColorantModel() != null) {
                colorant.setId(Integer.parseInt(String.valueOf(materialProofingModel.getColorant().getId())));
                colorant.setImageFilePath(materialProofingModel.getColorant().getImagePath());
                colorant.setIlluminant(materialProofingModel.getColorant().getIlluminantId());
                colorant.setIlluminantAngle(materialProofingModel.getColorant().getAngle());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection400());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection420());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection430());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection440());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection450());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection460());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection470());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection480());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection490());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection500());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection510());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection520());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection530());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection540());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection550());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection560());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection570());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection580());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection590());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection600());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection610());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection620());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection630());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection640());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection650());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection660());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection670());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection680());
                colorant.addVecReflection(materialProofingModel.getColorant().getReflection690());
                material_proofing.setColor(colorant);

            }
            res.addProofing(material_proofing);
        }
        sendPacket(FormulaProto.MessageType.SMSG_RES_MATERIALS_LIST, res.build());
    }

    /**
     * @description光源列表
     * @author: fangyong
     * @date 2018/10/22 19:55
     */
    public void illuminantList(List<Illuminant> illuminantList) {
        FormulaProto.smsg_res_illuminant_list.Builder res = FormulaProto.smsg_res_illuminant_list.newBuilder();
        FormulaProto.illuminant.Builder _illuminant = FormulaProto.illuminant.newBuilder();
        for (Illuminant illuminant : illuminantList) {
            _illuminant.setId(Integer.parseInt(String.valueOf(illuminant.getId())));
            _illuminant.setAngle(illuminant.getAngle());
            _illuminant.setName(illuminant.getName());
            if (StringUtil.isNotBlank(illuminant.getDescription())) {
                _illuminant.setDescription(illuminant.getDescription());
            }
            res.addIlluminant(_illuminant.build());
        }
        sendPacket(FormulaProto.MessageType.SMSG_RES_ILLUMINANT_LIST, res.build());
    }

}
