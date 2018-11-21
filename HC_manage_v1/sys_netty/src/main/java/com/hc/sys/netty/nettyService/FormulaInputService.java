package com.hc.sys.netty.nettyService;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.entity.*;
import com.hc.sys.core.dye.model.*;
import com.hc.sys.core.dye.service.*;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.manage.service.OperatorService;
import com.hc.sys.core.material.entity.*;
import com.hc.sys.core.material.model.*;
import com.hc.sys.core.material.service.*;
import com.hc.sys.netty.ccalculationcolor.DBUtilTool;
import com.hc.sys.netty.ccalculationcolor.LAB;
import com.hc.sys.netty.netty.NettyServer;
import com.hc.sys.netty.netty.ProtoMessage;
import com.hc.sys.netty.protobuf.FormulaProto;
import com.hc.sys.netty.thread.EventThread;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class FormulaInputService {
    @Resource
    OperatorService operatorService;
    @Resource
    DyeTypeService dyeTypeService;
    @Resource
    private DyeManufacturerService dyeManufacturerService;
    @Resource
    private DyeingTankManufacturerService dyeingTankManufacturerService;
    @Resource
    private DyeingTankService dyeingTankService;
    @Resource
    private MaterialService materialService;
    @Resource
    private FormulaService formulaService;
    @Resource
    private MaterialTypeService materialTypeService;
    @Resource
    private MaterialProofingService materialProofingService;
    @Resource
    private IlluminantService illuminantService;
    @Resource
    private DyeGroupService dyeGroupService;
    @Resource
    private DyeColorService dyeColorService;
    @Resource
    private ColorantService colorantService;
    @Resource
    private ConcentrationService concentrationService;
    @Resource
    private DyeService dyeService;
    public static final int EVENT_USER_RECEIVE = 1;
    public static final int EVENT_USER_DISCONNECT = 2;
    private int m_nettyPort = 0;
    protected NettyServer m_nettyServer = null;
    protected EventThread m_eventThread = null;
    static protected FormulaInputService s_formulaInputService = null;

    public FormulaInputService() {

    }

    @Bean
    public FormulaInputService builder() {
        s_formulaInputService = this;
        return this;
    }

    public static FormulaInputService getInstance() {
        return s_formulaInputService;
    }

    public void create() {
        m_eventThread = new EventThread() {
            @Override
            protected void onInitThread() {
                UserManager.threadInstance().create();
            }

            @Override
            protected void onUninitThread() {
                UserManager.threadInstance().destroy();
            }

            @Override
            protected void handleEvent(Event evt) {
                FormulaInputService.this.handleEvent(evt);
            }
        };
        m_nettyServer = new NettyServer() {
            //接收客户端消息，注意，可能有多个线程调用这个函数
            @Override
            protected void onReceive(ChannelId id, ProtoMessage msg) {
                //需要覆盖
                EventThread.Event evt = new EventThread.Event();
                evt.id = EVENT_USER_RECEIVE;
                evt.channelId = id;
                evt.obj = msg;
                FormulaInputService.this.m_eventThread.pushEvent(evt);
            }

            @Override
            protected void onDisconnected(Channel channel) {
                EventThread.Event evt = new EventThread.Event();
                evt.id = EVENT_USER_DISCONNECT;
                evt.channelId = channel.id();
                evt.obj = null;
                FormulaInputService.this.m_eventThread.pushEvent(evt);
            }
        };
        m_eventThread.create();
        m_nettyServer.create(getM_nettyPort());
        m_eventThread.start();
        m_nettyServer.start();
    }

    /**
     * @description 获取端口
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    public int getM_nettyPort() {
        return m_nettyPort;
    }

    /**
     * @description 设置端口
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    public void setM_nettyPort(int m_nettyPort) {
        this.m_nettyPort = m_nettyPort;
    }

    public void stop() {
        m_eventThread.stop();
        m_eventThread.join();
        m_nettyServer.close();
    }

    public void sendPacket(ChannelId id, FormulaProto.MessageType opcode, MessageLite msg) {
        m_nettyServer.send(id, opcode.getNumber(), msg);
    }

    public void sendPacket(ChannelId id, FormulaProto.MessageType opcode, long resData, MessageLite msg) {
        m_nettyServer.send(id, opcode.getNumber(), resData, msg);
    }

    public void sendPacktToUserList(ChannelId id, FormulaProto.MessageType opcode, long resData, MessageLite msg) {
        UserManager.threadInstance().m_mapUser.forEach((k, v) -> {
            if (k == id && UserManager.threadInstance().m_mapUser.containsKey(id)) {
                sendPacket(k, opcode, resData, msg);
            } else {
                sendPacket(k, opcode, 0, msg);
            }
        });


    }

    protected void handleEvent(EventThread.Event evt) {
        switch (evt.id) {
            case EVENT_USER_RECEIVE:
                handlePacket(evt.channelId, (ProtoMessage) evt.obj);
                break;
            case EVENT_USER_DISCONNECT:
                onLogout(evt.channelId);
                break;
        }
    }

    protected void handlePacket(ChannelId id, ProtoMessage msg) {
        switch (FormulaProto.MessageType.forNumber(msg.msgType)) {
            case CMSG_LOGIN:
                onLogin(id, msg);//客户端请求登录
                break;
            case CMSG_REQ_DYES_LIST:
                dyeList(id, msg);//客户端请求染料列表
                break;
            case CMSG_ADD_DYES:
                dyeAdd(id, msg);//客户端请求染料添加
                break;
            case CMSG_UPDATE_DYE:
                dyeUpdateById(id, msg);//客户端请求染料修改
                break;
            case CMSG_DEL_DYE:
                deleteDeyById(id, msg);//客户端请求染料删除
                break;
            case CMSG_ADD_DYE_TYPE:
                dyeAddType(id, msg);//客户端请求染料类型添加
                break;
            case CMSG_UPDATE_DYE_TYPE:
                updateDyeTypeById(id, msg);//客户端请求染料类型修改
                break;
            case CMSG_DEL_DYE_TYPE:
                deleteDyeTypeById(id, msg);//客户端请求染料类型删除
                break;
            case CMSG_ADD_DYE_GROUPS:
                dyeGroupAdd(id, msg);//客户端添加染料组
                break;
            case CMSG_UPDATE_DYE_GROUP:
                updateDyeGroupById(id, msg);//客户端修改染料组
                break;
            case CMSG_DEL_DYE_GROUP:
                deleteDyeGroupById(id, msg);//客户端删除染料组
                break;
            case CMSG_ADD_DYE_COLOR:
                dyeColorAdd(id, msg);//客户端添加染料颜色
                break;
            case CMSG_UPDATE_DYE_COLOR:
                updateDyeColorById(id, msg);//客户端修改染料颜色
                break;
            case CMSG_DEL_DYE_COLOR:
                deleteDyeColorById(id, msg);//客户端删除染料颜色
                break;
            case CMSG_REQ_ILLUMINANT_LIST:
                illuminantList(id, msg);//客户端请求光源列表
                break;
            case CMSG_ADD_ILLUMINANT:
                illuminantAdd(id, msg);//客户端请求光源添加
                break;
            case CMSG_UPDATE_ILLUMINANT:
                illuminantUpdate(id, msg);//客户端请求光源修改
                break;
            case CMSG_DEL_ILLUMINANT:
                illuminantDelete(id, msg);//客户端请求光源删除
                break;
            case CMSG_REQ_MATERIALS_LIST:
                materialList(id, msg);//客户端请求材质列表
                break;
            case CMSG_ADD_MATERIALS:
                materialAdd(id, msg);//客户端请求添加材质
                break;
            case CMSG_UPDATE_MATERIALS:
                updateMaterialById(id, msg);//客户端请求修改材质
                break;
            case CMSG_DEL_MATERIALS:
                deleteMaterialById(id, msg);//客户端请求删除材质
                break;
            case CMSG_ADD_MATERIAL_TYPE:
                materialTypeAdd(id, msg);//客户端请求添加材质类型
                break;
            case CMSG_UPDATE_MATERIAL_TYPE:
                updateMaterialTypeById(id, msg);//客户端请求修改材质类型
                break;
            case CMSG_DEL_MATERIAL_TYPE:
                deleteMaterialTypeById(id, msg);//客户端请求删除材质类型
                break;
            case CMSG_ADD_MATERIALS_PROOFING:
                materialProofingAdd(id, msg);//客户端请求添加材质打样
                break;
            case CMSG_DEL_MATERIALS_PROOFING:
                deleteMaterialProofingById(id, msg);//客户端请求删除材质打样
                break;
            case CMSG_ADD_MANUFACTURERS:
                manufacturersAdd(id, msg);//客户端添加染料厂商
                break;
            case CMSG_UPDATE_MANUFACTURER:
                updateManufacturerById(id, msg);//客户端修改染料厂商
                break;
            case CMSG_DEL_MANUFACTURER:
                deleteManufacturerById(id, msg);//客户端删除染料厂商
                break;
            case CMSG_REQ_DYEING_TANK_MANUFACTURER_LIST:
                manutankfacturersList(id, msg);//客户端请求染缸厂商列表
                break;
            case CMSG_ADD_DYEING_TANK_MANUFACTURER:
                manutankfacturersAdd(id, msg);//客户端添加染缸厂商
                break;
            case CMSG_REQ_DYEING_TANK_LIST:
                manutankList(id, msg);//客户端请求染缸列表
                break;
            case CMSG_ADD_DYEING_TANK:
                manutankAdd(id, msg);//客户端添加染缸
                break;
            case CMSG_UPDATE_DYEING_TANK:
                updateManutankById(id, msg);//客户更新染缸
                break;
            case CMSG_DEL_DYEING_TANK:
                deleteManutankById(id, msg);//客户删除染缸
                break;

            case CMSG_REQ_FORMULAS_LIST:
                formulasList(id, msg);//客户端配方列表
                break;
            case CMSG_ADD_FORMULAS:
                formulasAdd(id, msg);//客户端添加配方
                break;
            case CMSG_UPDATE_FORMULA:
                updateFormulasById(id, msg);//客户端修改配方
                break;
            case CMSG_DEL_FORMULA:
                deleteFormulasById(id, msg);//客户端删除配方
                break;
            default:
                LogUtil.info("----------default---------");
                break;
        }
    }

    protected void onLogin(ChannelId channelId, ProtoMessage msg) {
        OperatorModel model = new OperatorModel();
        try {
            FormulaProto.cmsg_login login = FormulaProto.cmsg_login.parseFrom(msg.msgValue);
            model.setUserName(login.getUsername());
            model.setPassword(login.getPassword());
            FormulaProto.smsg_login_ack.Builder ack = FormulaProto.smsg_login_ack.newBuilder();
            Result result = operatorService.signInByNetty(model);//查询数据库是否存在
            LogUtil.info("用户----------:" + login.getUsername() + (result.getCode() == 1 && !StringUtil.isBlank(result.getData()) ? "-----------连接成功---" : "连接失败"));
            if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                Operator operator = (Operator) result.getData();
                LogUtil.info(result.getMessage());
                //从数据库中查找用户名和密码是否正确
                //如果正确，判断内存中是否存在这个用户
                //如果内存中不存在，那么创建这个用户
                User user = UserManager.threadInstance().find(Integer.parseInt(String.valueOf(operator.getId())));
                if (user == null) {
                    user = new User();
                    //用户登录成功之后把他的通道channelId方式内存中
                    boolean f = user.create(channelId, Integer.parseInt(String.valueOf(operator.getId())), operator.getUserName(), operator.getPassword());
                    if (f)
                        LogUtil.info(operator.getUserName() + "   在内存中创建成功--");
                    else LogUtil.info(operator.getUserName() + "   在内存中创建失败--");

                    //user.onLogin();
                    //初始化这个user
                    //添加到容器里
                    UserManager.threadInstance().add(channelId, user);
                    ack.setErrValue(result.getMessage());
                    ack.setResult(0);
                    ack.setAccId(Integer.parseInt(String.valueOf(operator.getId())));
                } else {
                    LogUtil.warn(" 对不起，您已结登录过了 ");
                    ack.setErrValue("对不起，您已结登录过了！");
                    ack.setResult(0);
                }

            } else if (result.getCode() == -1) {
                LogUtil.info(result.getMessage());
                ack.setResult(1);
                ack.setErrValue(result.getMessage());
            }
            FormulaInputService.getInstance().sendPacket(channelId, FormulaProto.MessageType.SMSG_LOGIN_ACK, ack.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    protected void onLogout(ChannelId id) {
        if (id == null) {
            return;
        }
        User user = UserManager.threadInstance().find(id);
        LogUtil.info("用户----------:" + user.getUsername() + "-----------已退出连接");
        if (user == null)
            return;
        UserManager.threadInstance().remove(id);
    }


    /**
     * @description染料列表
     * @author: fangyong
     * @date 2018/10/23 12:15
     */
    protected void dyeList(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_req_dyes_list req = FormulaProto.cmsg_req_dyes_list.parseFrom(msg.msgValue);
            req.getDefaultInstanceForType();
            User user = UserManager.threadInstance().find(channelId);
            //查询内存中是否存在此用户；
            if (null != user) {
                List<DyeModel> dyeModelList = dyeService.findAll();
                List<DyeType> dyeTypeList = dyeTypeService.findAll();
                List<DyeGroup> dyeGroupList = dyeGroupService.findAll();
                List<DyeColor> dyeColorList = dyeColorService.findAll();
                List<DyeManufacturer> dyeManufacturerList = dyeManufacturerService.findAll();
                user.dyeList(dyeModelList, dyeTypeList, dyeManufacturerList, dyeColorList, dyeGroupList);
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description客户端配方列表
     * @author: fangyong
     * @date 2018/10/23 12:15
     */
    protected void formulasList(ChannelId channelId, ProtoMessage msg) {
        try {

            FormulaProto.cmsg_req_formulas_list req = FormulaProto.cmsg_req_formulas_list.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            //查询内存中是否存在此用户；
            if (null != user) {
                FormulaModel formulaModel = new FormulaModel();
                formulaModel.setPageNo(req.getFrom());
                formulaModel.setPageSize(req.getCount());
                user.formulasList(formulaService.list(formulaModel));
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 添加配方
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void formulasAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_formulas cmsg_add_formulas = FormulaProto.cmsg_add_formulas.parseFrom(msg.msgValue);
            FormulaProto.formula_result.Builder formula_result = FormulaProto.formula_result.newBuilder();
            FormulaProto.smsg_add_formulas.Builder smsg_add_formulas = FormulaProto.smsg_add_formulas.newBuilder();
            int formulaSize = cmsg_add_formulas.getFmList().size();
            User user = UserManager.threadInstance().find(channelId);
            long startTime = new Date().getTime();
            if (null != user) {
                if (formulaSize > 0) {
                    FormulaModel formulaModel = new FormulaModel();
                    for (int i = 0; i < formulaSize; i++) {
                        FormulaProto.formula.Builder formula = FormulaProto.formula.newBuilder();
                        FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                        formulaModel.setName(cmsg_add_formulas.getFmList().get(i).getName());
                        formulaModel.setSampleName(cmsg_add_formulas.getFmList().get(i).getSampleName());
                        formulaModel.setStatus(cmsg_add_formulas.getFmList().get(i).getStatus());
                        formulaModel.setOperatorId(user.getId());
                        formulaModel.setType(cmsg_add_formulas.getFmList().get(i).getType());
                        formulaModel.setMaterialId(cmsg_add_formulas.getFmList().get(i).getMaterialId());
                        formulaModel.setTechnologyId(cmsg_add_formulas.getFmList().get(i).getTechId());
                        formulaModel.setDyeGroupId(cmsg_add_formulas.getFmList().get(i).getDyeGroupId());
                        LAB lab = DBUtilTool.getInstance().RefToLab(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList(), cmsg_add_formulas.getFmList().get(i).getColor().getName(), cmsg_add_formulas.getFmList().get(i).getColor().getIlluminantAngle());
                        //判断配方名称是否不占用
                        boolean isexit = formulaService.getByName(cmsg_add_formulas.getFmList().get(i).getName());
                         if (!isexit&&lab!=null){
                             ColorantModel colorantModel = new ColorantModel();
                             colorantModel.setL(lab.getL());
                             colorantModel.setA(lab.getA());
                             colorantModel.setB(lab.getB());
                             colorantModel.setSpecularMode(cmsg_add_formulas.getFmList().get(i).getColor().getSpecularMode());
                             colorantModel.setIlluminantId(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminant());
                             colorantModel.setAngle(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminantAngle());
                             colorantModel.setRgb(DBUtilTool.getInstance().rgb(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList(), cmsg_add_formulas.getFmList().get(i).getColor().getName(), cmsg_add_formulas.getFmList().get(i).getColor().getIlluminantAngle()));
                             colorantModel.setReflection400(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(0));
                             colorantModel.setReflection410(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(1));
                             colorantModel.setReflection420(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(2));
                             colorantModel.setReflection430(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(3));
                             colorantModel.setReflection440(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(4));
                             colorantModel.setReflection450(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(5));
                             colorantModel.setReflection460(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(6));
                             colorantModel.setReflection470(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(7));
                             colorantModel.setReflection480(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(8));
                             colorantModel.setReflection490(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(9));
                             colorantModel.setReflection500(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(10));
                             colorantModel.setReflection510(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(11));
                             colorantModel.setReflection520(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(12));
                             colorantModel.setReflection530(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(13));
                             colorantModel.setReflection540(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(14));
                             colorantModel.setReflection550(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(15));
                             colorantModel.setReflection560(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(16));
                             colorantModel.setReflection570(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(17));
                             colorantModel.setReflection580(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(18));
                             colorantModel.setReflection590(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(19));
                             colorantModel.setReflection600(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(20));
                             colorantModel.setReflection610(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(21));
                             colorantModel.setReflection620(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(22));
                             colorantModel.setReflection630(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(23));
                             colorantModel.setReflection640(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(24));
                             colorantModel.setReflection650(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(25));
                             colorantModel.setReflection660(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(26));
                             colorantModel.setReflection670(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(27));
                             colorantModel.setReflection680(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(28));
                             colorantModel.setReflection690(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(29));
                             colorantModel.setReflection700(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList().get(30));
                             colorantModel.setImagePath(cmsg_add_formulas.getFmList().get(i).getColor().getImageFilePath());
                             Result resultColorant = colorantService.add(colorantModel);
                             if (resultColorant != null && resultColorant.getCode() == 1 && resultColorant.getData() != null) {
                                 Colorant colorantEntity = (Colorant) resultColorant.getData();
                                 colorant.setId(Integer.parseInt(String.valueOf(colorantEntity.getId())));
                                 formulaModel.setColorantId(colorantEntity.getId());
                             }
                             formulaModel.setAddTime(DateUtil.valueOf(DateUtil.formatDateStr(cmsg_add_formulas.getFmList().get(i).getTime())));
                             Result result = formulaService.add(formulaModel);
                             if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                                 Formula formulaEntity = (Formula) result.getData();
                                 ConcentrationModel concentrationModel = new ConcentrationModel();
                                 FormulaProto.concentration.Builder concentrationss = FormulaProto.concentration.newBuilder();
                                 for (int j = 0; j < cmsg_add_formulas.getFmList().get(i).getConcList().size(); j++) {
                                     concentrationModel.setConcentration(cmsg_add_formulas.getFmList().get(i).getConcList().get(j).getConc());
                                     concentrationModel.setDyeId(cmsg_add_formulas.getFmList().get(i).getConcList().get(j).getDyeId());
                                     concentrationModel.setFormulaId(formulaEntity.getId());
                                     Result resultConc = concentrationService.add(concentrationModel);
                                     if (resultConc.getCode() == 1 && resultConc.getData() != null) {
                                         concentrationss.setConc(cmsg_add_formulas.getFmList().get(i).getConcList().get(j).getConc());
                                         concentrationss.setDyeId(cmsg_add_formulas.getFmList().get(i).getConcList().get(j).getDyeId());
                                         formula.addConc(concentrationss);
                                     }
                                 }
                                 formula.setId(Integer.parseInt(String.valueOf(formulaEntity.getId())));
                                 formula_result.setResult(0);
                             } else {
                                 formula_result.setResult(1);
                             }
                             colorant.setSpecularMode(cmsg_add_formulas.getFmList().get(i).getColor().getSpecularMode());
                             colorant.setImageFilePath(cmsg_add_formulas.getFmList().get(i).getColor().getImageFilePath());
                             colorant.setIlluminant(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminant());
                             colorant.setIlluminantAngle(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminantAngle());
                             colorant.addAllVecReflection(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList());
                             formula.setName(cmsg_add_formulas.getFmList().get(i).getName());
                             formula.setSampleName(cmsg_add_formulas.getFmList().get(i).getSampleName());
                             formula.setStatus(cmsg_add_formulas.getFmList().get(i).getStatus());
                             formula.setOperatorName(cmsg_add_formulas.getFmList().get(i).getOperatorName());
                             formula.setType(cmsg_add_formulas.getFmList().get(i).getType());
                             formula.setMaterialId(cmsg_add_formulas.getFmList().get(i).getMaterialId());
                             formula.setTechId(cmsg_add_formulas.getFmList().get(i).getTechId());
                             formula.addAllConc(cmsg_add_formulas.getFmList().get(i).getConcList());
                             formula.setDyeGroupId(cmsg_add_formulas.getFmList().get(i).getDyeGroupId());
                             formula.setColor(colorant);
                             formula_result.setManu(formula);
                             formula.setTime(cmsg_add_formulas.getFmList().get(i).getTime());
                             formula_result.setErrMessage(result.getMessage());
                             smsg_add_formulas.addResult(formula_result);
                         }else{
                             colorant.setSpecularMode(cmsg_add_formulas.getFmList().get(i).getColor().getSpecularMode());
                             colorant.setImageFilePath(cmsg_add_formulas.getFmList().get(i).getColor().getImageFilePath());
                             colorant.setIlluminant(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminant());
                             colorant.setIlluminantAngle(cmsg_add_formulas.getFmList().get(i).getColor().getIlluminantAngle());
                             colorant.addAllVecReflection(cmsg_add_formulas.getFmList().get(i).getColor().getVecReflectionList());
                             formula.setName(cmsg_add_formulas.getFmList().get(i).getName());
                             formula.setSampleName(cmsg_add_formulas.getFmList().get(i).getSampleName());
                             formula.setStatus(cmsg_add_formulas.getFmList().get(i).getStatus());
                             formula.setOperatorName(cmsg_add_formulas.getFmList().get(i).getOperatorName());
                             formula.setType(cmsg_add_formulas.getFmList().get(i).getType());
                             formula.setMaterialId(cmsg_add_formulas.getFmList().get(i).getMaterialId());
                             formula.setTechId(cmsg_add_formulas.getFmList().get(i).getTechId());
                             formula.addAllConc(cmsg_add_formulas.getFmList().get(i).getConcList());
                             formula.setDyeGroupId(cmsg_add_formulas.getFmList().get(i).getDyeGroupId());
                             formula.setColor(cmsg_add_formulas.getFmList().get(i).getColor());
                             formula_result.setManu(formula);
                             formula.setTime(cmsg_add_formulas.getFmList().get(i).getTime());
                             formula_result.setResult(1);
                             formula_result.setErrMessage("此配方名称已经存在或因LAB转换异常！  配方名字"+cmsg_add_formulas.getFmList().get(i).getName());
                             smsg_add_formulas.addResult(formula_result);
                         }
                    }
                }
                long endTime = new Date().getTime();
                LogUtil.info("本次配方录入耗时: " + (endTime - startTime)/1000 + "  秒");
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_FORMULAS, msg.resData, smsg_add_formulas.build());

            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 修改配方
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void updateFormulasById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_formula cmsg_update_formula = FormulaProto.cmsg_update_formula.parseFrom(msg.msgValue);
            FormulaProto.formula_result.Builder formula_result = FormulaProto.formula_result.newBuilder();
            FormulaProto.smsg_update_formula.Builder smsg_update_formula = FormulaProto.smsg_update_formula.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                FormulaModel formulaModel = new FormulaModel();
                FormulaProto.formula.Builder formula = FormulaProto.formula.newBuilder();
                FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                formulaModel.setName(cmsg_update_formula.getFm().getName());
                formulaModel.setSampleName(cmsg_update_formula.getFm().getSampleName());
                formulaModel.setStatus(cmsg_update_formula.getFm().getStatus());
                formulaModel.setOperatorId(user.getId());
                formulaModel.setType(cmsg_update_formula.getFm().getType());
                formulaModel.setMaterialId(cmsg_update_formula.getFm().getMaterialId());
                formulaModel.setTechnologyId(cmsg_update_formula.getFm().getTechId());
                formulaModel.setDyeGroupId(cmsg_update_formula.getFm().getDyeGroupId());
                ColorantModel colorantModel = new ColorantModel();
                LAB lab = DBUtilTool.getInstance().RefToLab(cmsg_update_formula.getFm().getColor().getVecReflectionList(), cmsg_update_formula.getFm().getColor().getName(), cmsg_update_formula.getFm().getColor().getIlluminantAngle());
                colorantModel.setL(lab.getL());
                colorantModel.setA(lab.getA());
                colorantModel.setB(lab.getB());
                colorantModel.setSpecularMode(cmsg_update_formula.getFm().getColor().getSpecularMode());
                colorantModel.setIlluminantId(cmsg_update_formula.getFm().getColor().getIlluminant());
                colorantModel.setAngle(cmsg_update_formula.getFm().getColor().getIlluminantAngle());
                colorantModel.setRgb(DBUtilTool.getInstance().rgb(cmsg_update_formula.getFm().getColor().getVecReflectionList(), cmsg_update_formula.getFm().getColor().getName(), cmsg_update_formula.getFm().getColor().getIlluminantAngle()));
                colorantModel.setReflection400(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(0));
                colorantModel.setReflection410(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(1));
                colorantModel.setReflection420(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(2));
                colorantModel.setReflection430(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(3));
                colorantModel.setReflection440(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(4));
                colorantModel.setReflection450(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(5));
                colorantModel.setReflection460(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(6));
                colorantModel.setReflection470(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(7));
                colorantModel.setReflection480(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(8));
                colorantModel.setReflection490(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(9));
                colorantModel.setReflection500(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(10));
                colorantModel.setReflection510(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(11));
                colorantModel.setReflection520(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(12));
                colorantModel.setReflection530(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(13));
                colorantModel.setReflection540(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(14));
                colorantModel.setReflection550(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(15));
                colorantModel.setReflection560(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(16));
                colorantModel.setReflection570(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(17));
                colorantModel.setReflection580(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(18));
                colorantModel.setReflection590(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(19));
                colorantModel.setReflection600(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(20));
                colorantModel.setReflection610(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(21));
                colorantModel.setReflection620(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(22));
                colorantModel.setReflection630(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(23));
                colorantModel.setReflection640(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(24));
                colorantModel.setReflection650(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(25));
                colorantModel.setReflection660(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(26));
                colorantModel.setReflection670(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(27));
                colorantModel.setReflection680(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(28));
                colorantModel.setReflection690(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(29));
                colorantModel.setReflection700(cmsg_update_formula.getFm().getColor().getVecReflectionList().get(30));
                colorantModel.setImagePath(cmsg_update_formula.getFm().getColor().getImageFilePath());
                Result resultColorant = colorantService.add(colorantModel);
                if (resultColorant != null && resultColorant.getCode() == 1 && resultColorant.getData() != null) {
                    Colorant colorantEntity = (Colorant) resultColorant.getData();
                    formulaModel.setColorantId(colorantEntity.getId());
                }
                formulaModel.setAddTime(DateUtil.valueOf(DateUtil.formatDateStr(cmsg_update_formula.getFm().getTime())));
                Result result = formulaService.add(formulaModel);
                if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                    Formula formulaEntity = (Formula) result.getData();
                    ConcentrationModel concentrationModel = new ConcentrationModel();
                    FormulaProto.concentration.Builder concentrationss = FormulaProto.concentration.newBuilder();
                    if (cmsg_update_formula.getFm().getConcList().size() > 0) {
                        for (int j = 0; j < cmsg_update_formula.getFm().getConcList().size(); j++) {
                            concentrationModel.setConcentration(cmsg_update_formula.getFm().getConcList().get(j).getConc());
                            concentrationModel.setDyeId(cmsg_update_formula.getFm().getConcList().get(j).getDyeId());
                            concentrationModel.setFormulaId(formulaEntity.getId());
                            Result resultConc = concentrationService.add(concentrationModel);
                            if (resultConc.getCode() == 1 && resultConc.getData() != null) {
                                Concentration con = (Concentration) resultConc.getData();
                                concentrationss.setDyeId(Integer.valueOf(String.valueOf(con.getDye().getId())));
                                concentrationss.setConc(con.getConcentration());
                                formula.addConc(concentrationss);
                            }
                        }
                    }
                    formula.setId(Integer.parseInt(String.valueOf(formulaEntity.getId())));
                    if (formulaEntity.getColorant() != null) {
                        colorant.setId(Integer.parseInt(String.valueOf(formulaEntity.getColorant().getId())));
                    }
                    formula_result.setResult(0);
                } else {
                    formula_result.setResult(1);
                }
                colorant.setImageFilePath(cmsg_update_formula.getFm().getColor().getImageFilePath());
                colorant.setIlluminant(cmsg_update_formula.getFm().getColor().getIlluminant());
                colorant.setIlluminantAngle(cmsg_update_formula.getFm().getColor().getIlluminantAngle());
                colorant.addAllVecReflection(cmsg_update_formula.getFm().getColor().getVecReflectionList());
                formula.setColor(colorant);
                formula.setName(cmsg_update_formula.getFm().getName());
                formula.setSampleName(cmsg_update_formula.getFm().getSampleName());
                formula.setStatus(cmsg_update_formula.getFm().getStatus());
                formula.setOperatorName(user.getUsername());
                formula.setType(cmsg_update_formula.getFm().getType());
                formula.setMaterialId(cmsg_update_formula.getFm().getMaterialId());
                formula.setTechId(cmsg_update_formula.getFm().getTechId());
                formula.setDyeGroupId(cmsg_update_formula.getFm().getDyeGroupId());
                formula.setTime(cmsg_update_formula.getFm().getTime());
                formula_result.setErrMessage(result.getMessage());
                formula_result.setManu(formula);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_FORMULA, msg.resData, smsg_update_formula.build());


        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 删除配方
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void deleteFormulasById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_formula cmsg_del_formula = FormulaProto.cmsg_del_formula.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                FormulaProto.smsg_del_formula.Builder smsg_del_formula = FormulaProto.smsg_del_formula.newBuilder();
                FormulaModel formulaModel = new FormulaModel();
                formulaModel.setId(cmsg_del_formula.getId());
                Result result = formulaService.deleteById(formulaModel);
                if (result.getCode() == 1) {
                    smsg_del_formula.setResult(0);
                } else {
                    smsg_del_formula.setResult(1);
                }
                smsg_del_formula.setErrMessage(result.getMessage());

                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_FORMULA, msg.resData, smsg_del_formula.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 添加染料厂商
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void manufacturersAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_manufacturers cmsg_add_manufacturers = FormulaProto.cmsg_add_manufacturers.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_manufacturers.Builder smsg_add_manufacturers = FormulaProto.smsg_add_manufacturers.newBuilder();
            FormulaProto.dye_manufacturer.Builder dye_manufacturer = FormulaProto.dye_manufacturer.newBuilder();
            FormulaProto.dye_manufacturer_result.Builder dye_manufacturer_result = FormulaProto.dye_manufacturer_result.newBuilder();
            int add_manufacturersListSize = cmsg_add_manufacturers.getManufacturersList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (add_manufacturersListSize > 0) {
                    DyeManufacturerModel dyeManufacturerModel = new DyeManufacturerModel();
                    for (int i = 0; i < add_manufacturersListSize; i++) {
                        dyeManufacturerModel.setCompany(cmsg_add_manufacturers.getManufacturersList().get(i).getCompany());
                        dyeManufacturerModel.setAddress(cmsg_add_manufacturers.getManufacturersList().get(i).getAddress());
                        dyeManufacturerModel.setZipcode(cmsg_add_manufacturers.getManufacturersList().get(i).getZipcode());
                        dyeManufacturerModel.setCountry(cmsg_add_manufacturers.getManufacturersList().get(i).getCountry());
                        dyeManufacturerModel.setCity(cmsg_add_manufacturers.getManufacturersList().get(i).getCity());
                        dyeManufacturerModel.setState(cmsg_add_manufacturers.getManufacturersList().get(i).getState());
                        dyeManufacturerModel.setContact(cmsg_add_manufacturers.getManufacturersList().get(i).getContact());
                        dyeManufacturerModel.setPhoneNumber(cmsg_add_manufacturers.getManufacturersList().get(i).getPhoneNumber());
                        dyeManufacturerModel.setFaxNumber(cmsg_add_manufacturers.getManufacturersList().get(i).getFaxNumber());
                        dyeManufacturerModel.setEmail(cmsg_add_manufacturers.getManufacturersList().get(i).getEmail());
                        Result result = dyeManufacturerService.add(dyeManufacturerModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            DyeManufacturer dyeManufacturer = (DyeManufacturer) result.getData();
                            dye_manufacturer.setId(Integer.parseInt(String.valueOf(dyeManufacturer.getId())));
                            dye_manufacturer_result.setResult(0);
                        } else {
                            dye_manufacturer_result.setResult(1);
                        }
                        dye_manufacturer.setCompany(cmsg_add_manufacturers.getManufacturersList().get(i).getCompany());
                        dye_manufacturer.setAddress(cmsg_add_manufacturers.getManufacturersList().get(i).getAddress());
                        dye_manufacturer.setZipcode(cmsg_add_manufacturers.getManufacturersList().get(i).getZipcode());
                        dye_manufacturer.setCountry(cmsg_add_manufacturers.getManufacturersList().get(i).getCountry());
                        dye_manufacturer.setCity(cmsg_add_manufacturers.getManufacturersList().get(i).getCity());
                        dye_manufacturer.setState(cmsg_add_manufacturers.getManufacturersList().get(i).getState());
                        dye_manufacturer.setContact(cmsg_add_manufacturers.getManufacturersList().get(i).getContact());
                        dye_manufacturer.setPhoneNumber(cmsg_add_manufacturers.getManufacturersList().get(i).getPhoneNumber());
                        dye_manufacturer.setFaxNumber(cmsg_add_manufacturers.getManufacturersList().get(i).getFaxNumber());
                        dye_manufacturer.setEmail(cmsg_add_manufacturers.getManufacturersList().get(i).getEmail());
                        dye_manufacturer_result.setErrValue(result.getMessage());
                        dye_manufacturer_result.setManufacturers(dye_manufacturer);
                        smsg_add_manufacturers.addResult(i, dye_manufacturer_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_MANUFACTURERS, msg.resData, smsg_add_manufacturers.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染缸厂商列表
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void manutankfacturersList(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.smsg_res_dyeing_tank_manufacturer_list smsg_res_dyeing_tank_manufacturer_list = FormulaProto.smsg_res_dyeing_tank_manufacturer_list.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            //查询内存中是否存在此用户；
            if (null != user) {
                List<DyeingTankManufacturer> dyeingTankManufacturerList = dyeingTankManufacturerService.findAll();
                user.manutankfacturersList(dyeingTankManufacturerList);
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 添加染缸厂商
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void manutankfacturersAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dyeing_tank_manufacturer cmsg_add_manufacturers = FormulaProto.cmsg_add_dyeing_tank_manufacturer.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dyeing_tank_manufacturer.Builder smsg_add_manufacturers = FormulaProto.smsg_add_dyeing_tank_manufacturer.newBuilder();
            FormulaProto.dyeing_tank_manufacturer.Builder dye_manufacturer = FormulaProto.dyeing_tank_manufacturer.newBuilder();
            FormulaProto.manufacturer_result.Builder dye_manufacturer_result = FormulaProto.manufacturer_result.newBuilder();
            int add_manufacturersListSize = cmsg_add_manufacturers.getManuList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (add_manufacturersListSize > 0) {
                    DyeingTankManufacturerModel dyeingTankManufacturerModel = new DyeingTankManufacturerModel();
                    for (int i = 0; i < add_manufacturersListSize; i++) {
                        dyeingTankManufacturerModel.setCompany(cmsg_add_manufacturers.getManuList().get(i).getCompany());
                        dyeingTankManufacturerModel.setAddress(cmsg_add_manufacturers.getManuList().get(i).getAddress());
                        dyeingTankManufacturerModel.setZipcode(cmsg_add_manufacturers.getManuList().get(i).getZipcode());
                        dyeingTankManufacturerModel.setCountry(cmsg_add_manufacturers.getManuList().get(i).getCountry());
                        dyeingTankManufacturerModel.setCity(cmsg_add_manufacturers.getManuList().get(i).getCity());
                        dyeingTankManufacturerModel.setState(cmsg_add_manufacturers.getManuList().get(i).getState());
                        dyeingTankManufacturerModel.setContact(cmsg_add_manufacturers.getManuList().get(i).getContact());
                        dyeingTankManufacturerModel.setPhoneNumber(cmsg_add_manufacturers.getManuList().get(i).getPhoneNumber());
                        dyeingTankManufacturerModel.setFaxNumber(cmsg_add_manufacturers.getManuList().get(i).getFaxNumber());
                        dyeingTankManufacturerModel.setEmail(cmsg_add_manufacturers.getManuList().get(i).getEmail());
                        Result result = dyeingTankManufacturerService.add(dyeingTankManufacturerModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            DyeingTankManufacturer dyeingTankManufacturer = (DyeingTankManufacturer) result.getData();
                            dye_manufacturer.setId(Integer.parseInt(String.valueOf(dyeingTankManufacturer.getId())));
                            dye_manufacturer_result.setResult(0);
                        } else {
                            dye_manufacturer_result.setResult(1);
                        }
                        dye_manufacturer.setCompany(cmsg_add_manufacturers.getManuList().get(i).getCompany());
                        dye_manufacturer.setAddress(cmsg_add_manufacturers.getManuList().get(i).getAddress());
                        dye_manufacturer.setZipcode(cmsg_add_manufacturers.getManuList().get(i).getZipcode());
                        dye_manufacturer.setCountry(cmsg_add_manufacturers.getManuList().get(i).getCountry());
                        dye_manufacturer.setCity(cmsg_add_manufacturers.getManuList().get(i).getCity());
                        dye_manufacturer.setState(cmsg_add_manufacturers.getManuList().get(i).getState());
                        dye_manufacturer.setContact(cmsg_add_manufacturers.getManuList().get(i).getContact());
                        dye_manufacturer.setPhoneNumber(cmsg_add_manufacturers.getManuList().get(i).getPhoneNumber());
                        dye_manufacturer.setFaxNumber(cmsg_add_manufacturers.getManuList().get(i).getFaxNumber());
                        dye_manufacturer.setEmail(cmsg_add_manufacturers.getManuList().get(i).getEmail());
                        dye_manufacturer_result.setErrMessage(result.getMessage());
                        dye_manufacturer_result.setManu(dye_manufacturer);
                        smsg_add_manufacturers.addResult(i, dye_manufacturer_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYEING_TANK_MANUFACTURER, msg.resData, smsg_add_manufacturers.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染缸列表
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void manutankList(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.smsg_add_dyeing_tank smsg_add_dyeing_tank = FormulaProto.smsg_add_dyeing_tank.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            //查询内存中是否存在此用户；
            if (null != user) {
                List<DyeingTank> dyeingTankList = dyeingTankService.findAll();
                user.manutankList(dyeingTankList);
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());    }
    }

    /**
     * @description 添加染缸
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void manutankAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dyeing_tank cmsg_add_dyeing_tank = FormulaProto.cmsg_add_dyeing_tank.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dyeing_tank.Builder smsg_add_dyeing_tank = FormulaProto.smsg_add_dyeing_tank.newBuilder();
            FormulaProto.dyeing_tank.Builder dyeing_tank = FormulaProto.dyeing_tank.newBuilder();
            FormulaProto.dyeing_tank_result.Builder dyeing_tank_result = FormulaProto.dyeing_tank_result.newBuilder();
            int addDyeingTankSize = cmsg_add_dyeing_tank.getTanksList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (addDyeingTankSize > 0) {
                    DyeingTankModel dyeingTankModel = new DyeingTankModel();
                    for (int i = 0; i < addDyeingTankSize; i++) {
                        dyeingTankModel.setModel(cmsg_add_dyeing_tank.getTanksList().get(i).getModel());
                        dyeingTankModel.setName(cmsg_add_dyeing_tank.getTanksList().get(i).getName());
                        dyeingTankModel.setDyeingTankManufacturerId(cmsg_add_dyeing_tank.getTanksList().get(i).getManufacturerId());
                        dyeingTankModel.setAddTime(DateUtil.valueOf(DateUtil.formatDateStr(cmsg_add_dyeing_tank.getTanksList().get(i).getTime())));
                        Result result = dyeingTankService.add(dyeingTankModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            DyeingTank dyeingTank = (DyeingTank) result.getData();
                            dyeing_tank.setId(Integer.parseInt(String.valueOf(dyeingTank.getId())));
                            dyeing_tank_result.setResult(0);
                        } else {
                            dyeing_tank_result.setResult(1);
                        }
                        dyeing_tank.setModel(cmsg_add_dyeing_tank.getTanksList().get(i).getModel());
                        dyeing_tank.setName(cmsg_add_dyeing_tank.getTanksList().get(i).getName());
                        dyeing_tank.setManufacturerId(cmsg_add_dyeing_tank.getTanksList().get(i).getManufacturerId());
                        dyeing_tank.setTime(cmsg_add_dyeing_tank.getTanksList().get(i).getTime());
                        dyeing_tank_result.setErrMessage(result.getMessage());
                        dyeing_tank_result.setTank(dyeing_tank);
                        smsg_add_dyeing_tank.addResult(i, dyeing_tank_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYEING_TANK, msg.resData, smsg_add_dyeing_tank.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 更新染缸
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void updateManutankById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_dyeing_tank cmsg_update_dyeing_tank = FormulaProto.cmsg_update_dyeing_tank.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_dyeing_tank.Builder smsg_update_dyeing_tank = FormulaProto.smsg_update_dyeing_tank.newBuilder();
            FormulaProto.dyeing_tank.Builder dyeing_tank = FormulaProto.dyeing_tank.newBuilder();
            FormulaProto.dyeing_tank_result.Builder dyeing_tank_result = FormulaProto.dyeing_tank_result.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeingTankModel dyeingTankModel = new DyeingTankModel();
                dyeingTankModel.setId(cmsg_update_dyeing_tank.getTank().getId());
                dyeingTankModel.setModel(cmsg_update_dyeing_tank.getTank().getModel());
                dyeingTankModel.setName(cmsg_update_dyeing_tank.getTank().getName());
                dyeingTankModel.setDyeingTankManufacturerId(cmsg_update_dyeing_tank.getTank().getManufacturerId());
                dyeingTankModel.setAddTime(DateUtil.valueOf(DateUtil.formatDateStr(cmsg_update_dyeing_tank.getTank().getTime())));
                Result result = dyeingTankService.add(dyeingTankModel);
                if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                    dyeing_tank_result.setResult(0);
                } else {
                    dyeing_tank_result.setResult(1);
                }
                dyeing_tank.setId(cmsg_update_dyeing_tank.getTank().getId());
                dyeing_tank.setModel(cmsg_update_dyeing_tank.getTank().getModel());
                dyeing_tank.setName(cmsg_update_dyeing_tank.getTank().getName());
                dyeing_tank.setManufacturerId(cmsg_update_dyeing_tank.getTank().getManufacturerId());
                dyeing_tank.setTime(cmsg_update_dyeing_tank.getTank().getTime());
                dyeing_tank_result.setErrMessage(result.getMessage());
                dyeing_tank_result.setTank(dyeing_tank);
                smsg_update_dyeing_tank.setResult(dyeing_tank_result);
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_DYEING_TANK, msg.resData, smsg_update_dyeing_tank.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 删除染缸
     * @author: fangyong
     * @date 2018/11/1 9:29
     */
    protected void deleteManutankById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_dyeing_tank cmsg_del_dyeing_tank = FormulaProto.cmsg_del_dyeing_tank.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_dyeing_tank.Builder smsg_del_dyeing_tank = FormulaProto.smsg_del_dyeing_tank.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                FormulaProto.dyeing_tank.Builder dyeing_tank = FormulaProto.dyeing_tank.newBuilder();
                FormulaProto.dyeing_tank_result.Builder dyeing_tank_result = FormulaProto.dyeing_tank_result.newBuilder();
                DyeingTankModel dyeingTankModel = new DyeingTankModel();
                dyeingTankModel.setId(cmsg_del_dyeing_tank.getId());
                Result result = dyeingTankService.deleteById(dyeingTankModel);
                if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                    dyeing_tank_result.setResult(0);
                } else {
                    dyeing_tank_result.setResult(1);
                }
                dyeing_tank.setId(cmsg_del_dyeing_tank.getId());
                dyeing_tank_result.setErrMessage(result.getMessage());
                dyeing_tank_result.setTank(dyeing_tank);
                smsg_del_dyeing_tank.setErrMessage(result.getMessage());
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_DYEING_TANK, msg.resData, smsg_del_dyeing_tank.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description染料类型添加
     * @author: fangyong
     * @date 2018/10/23 11:05
     */
    protected void dyeAddType(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dye_type add_dyeList = FormulaProto.cmsg_add_dye_type.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dye_type.Builder smsg_add_dye_type = FormulaProto.smsg_add_dye_type.newBuilder();
            int add_dyeListSize = add_dyeList.getTypeList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (add_dyeListSize > 0) {
                    DyeTypeModel dyeTypeModel = new DyeTypeModel();
                    FormulaProto.dye_type.Builder dye_type = FormulaProto.dye_type.newBuilder();
                    FormulaProto.add_dye_type_result.Builder add_dye_type_result = FormulaProto.add_dye_type_result.newBuilder();
                    for (int i = 0; i < add_dyeListSize; i++) {
                        dyeTypeModel.setName(add_dyeList.getTypeList().get(i).getName());
                        dyeTypeModel.setDescription(add_dyeList.getTypeList().get(i).getDescription());
                        dyeTypeModel.setAlias(add_dyeList.getTypeList().get(i).getAlias());
                        Result result = dyeTypeService.add(dyeTypeModel);
                        if (result.getCode() == 1 && null != result.getData()) {
                            DyeType dyeType = (DyeType) result.getData();
                            dye_type.setId(Integer.parseInt(String.valueOf(dyeType.getId())));
                            add_dye_type_result.setResult(0);//结果，0：成功，1：失败
                        } else {
                            add_dye_type_result.setResult(1);//结果，0：成功，1：失败
                        }
                        dye_type.setName(add_dyeList.getTypeList().get(i).getName());
                        dye_type.setAlias(add_dyeList.getTypeList().get(i).getAlias());
                        dye_type.setDescription(add_dyeList.getTypeList().get(i).getDescription());
                        add_dye_type_result.setType(dye_type);
                        add_dye_type_result.setErrValue(result.getMessage());
                        smsg_add_dye_type.addResult(i, add_dye_type_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYE_TYPE, msg.resData, smsg_add_dye_type.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description染料添加
     * @author: fangyong
     * @date 2018/10/23 11:05
     */
    protected void dyeAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dyes cmsg_add_dyes = FormulaProto.cmsg_add_dyes.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dyes.Builder smsg_add_dye = FormulaProto.smsg_add_dyes.newBuilder();
            FormulaProto.add_dye_result.Builder add_dye_result = FormulaProto.add_dye_result.newBuilder();
            FormulaProto.dye.Builder _dye = FormulaProto.dye.newBuilder();
            int add_dyeListSize = cmsg_add_dyes.getLstDyeList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (add_dyeListSize > 0) {
                    DyeModel dyeModel = new DyeModel();
                    for (int i = 0; i < add_dyeListSize; i++) {
                        dyeModel.setName(cmsg_add_dyes.getLstDyeList().get(i).getName());
                        dyeModel.setDescription(cmsg_add_dyes.getLstDyeList().get(i).getDescription());
                        dyeModel.setAlias(cmsg_add_dyes.getLstDyeList().get(i).getAlias());
                        dyeModel.setOperatoId(user.getId());
                        dyeModel.setDyeTypeId(cmsg_add_dyes.getLstDyeList().get(i).getDyeTypeId());
                        dyeModel.setCloudId(cmsg_add_dyes.getLstDyeList().get(i).getDyeColorId());
                        dyeModel.setStrength(cmsg_add_dyes.getLstDyeList().get(i).getStrength());
                        dyeModel.setPrice(cmsg_add_dyes.getLstDyeList().get(i).getPrice());
                        dyeModel.setDyeManufacturerId(cmsg_add_dyes.getLstDyeList().get(i).getManufacturerId());
                        dyeModel.setDyeColorId(cmsg_add_dyes.getLstDyeList().get(i).getDyeColorId());
                        dyeModel.setExterior(cmsg_add_dyes.getLstDyeList().get(i).getExterior());
                        Result result = dyeService.add(dyeModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            Dye dye = (Dye) result.getData();
                            _dye.setId(Integer.parseInt(String.valueOf(dye.getId())));
                            add_dye_result.setResult(0);
                        } else {
                            add_dye_result.setResult(1);

                        }
                        _dye.setAlias(cmsg_add_dyes.getLstDyeList().get(i).getAlias());
                        _dye.setName(cmsg_add_dyes.getLstDyeList().get(i).getName());
                        _dye.setDescription(cmsg_add_dyes.getLstDyeList().get(i).getDescription());
                        _dye.setStrength(cmsg_add_dyes.getLstDyeList().get(i).getStrength());
                        _dye.setDyeTypeId(Integer.parseInt(String.valueOf(cmsg_add_dyes.getLstDyeList().get(i).getDyeTypeId())));
                        _dye.setDyeColorId(Integer.parseInt(String.valueOf(cmsg_add_dyes.getLstDyeList().get(i).getId())));
                        _dye.setPrice(cmsg_add_dyes.getLstDyeList().get(i).getPrice());
                        _dye.setExterior(cmsg_add_dyes.getLstDyeList().get(i).getExterior());
                        _dye.setManufacturerId(Integer.parseInt(String.valueOf(cmsg_add_dyes.getLstDyeList().get(i).getManufacturerId())));
                        add_dye_result.setErrValue(result.getMessage());
                        add_dye_result.setAddDye(_dye);
                        smsg_add_dye.addResult(i, add_dye_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYES, msg.resData, smsg_add_dye.build());
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description染料修改
     * @author: fangyong
     * @date 2018/10/23 14:05
     */
    protected void dyeUpdateById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_dye cmsg_update_dye = FormulaProto.cmsg_update_dye.parseFrom(msg.msgValue);
            FormulaProto.dye.Builder _dye = FormulaProto.dye.newBuilder();
            FormulaProto.smsg_update_dye.Builder smsg_update_dye = FormulaProto.smsg_update_dye.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeModel dyeModel = new DyeModel();
                dyeModel.setId(cmsg_update_dye.getData().getId());
                dyeModel.setName(cmsg_update_dye.getData().getName());
                dyeModel.setAlias(cmsg_update_dye.getData().getAlias());
                dyeModel.setStrength(cmsg_update_dye.getData().getStrength());
                dyeModel.setDyeTypeId(cmsg_update_dye.getData().getDyeTypeId());
                dyeModel.setDyeColorId(cmsg_update_dye.getData().getDyeColorId());
                dyeModel.setPrice(cmsg_update_dye.getData().getPrice());
                dyeModel.setExterior(cmsg_update_dye.getData().getExterior());
                dyeModel.setDyeManufacturerId(cmsg_update_dye.getData().getManufacturerId());
                dyeModel.setDescription(cmsg_update_dye.getData().getDescription());
                Result result = dyeService.update(dyeModel);
                if (result != null && result.getCode() == 1 && result.getData() != null) {
                    smsg_update_dye.setResult(0);
                } else {
                    smsg_update_dye.setResult(1);
                }
                smsg_update_dye.setErrValue(result.getMessage());
                _dye.setId(cmsg_update_dye.getData().getId());
                _dye.setName(cmsg_update_dye.getData().getName());
                _dye.setAlias(cmsg_update_dye.getData().getAlias());
                _dye.setStrength(cmsg_update_dye.getData().getStrength());
                _dye.setDyeTypeId(cmsg_update_dye.getData().getDyeTypeId());
                _dye.setDyeColorId(cmsg_update_dye.getData().getDyeColorId());
                _dye.setPrice(cmsg_update_dye.getData().getPrice());
                _dye.setExterior(cmsg_update_dye.getData().getExterior());
                _dye.setManufacturerId(cmsg_update_dye.getData().getManufacturerId());
                _dye.setDescription(cmsg_update_dye.getData().getDescription());
                smsg_update_dye.setData(_dye);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_DYE, msg.resData, smsg_update_dye.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料删除
     * @author: fangyong
     * @date 2018/10/23 14:33
     */
    protected void deleteDeyById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_dye cmsg_del_dye = FormulaProto.cmsg_del_dye.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_dye.Builder smsg_del_dye = FormulaProto.smsg_del_dye.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeModel dyeModel = new DyeModel();
                dyeModel.setId(cmsg_del_dye.getDyeId());
                Result result = dyeService.deleteById(dyeModel);
                if (result != null && result.getCode() == 1) {
                    smsg_del_dye.setResult(0);
                } else {
                    smsg_del_dye.setResult(1);
                }
                smsg_del_dye.setErrValue(result.getMessage());
                smsg_del_dye.setDyeId(cmsg_del_dye.getDyeId());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_DYE, msg.resData, smsg_del_dye.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @descriptio材质列表
     * @author: fangyong
     * @date 2018/10/23 14:35
     */
    protected void materialList(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_req_materials_list req = FormulaProto.cmsg_req_materials_list.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                List<MaterialModel> modelList = materialService.findList();
                List<MaterialType> materialTypeList = materialTypeService.findList();
                List<MaterialProofingModel> materialProofingModelList = materialProofingService.findList();
                user.materialList(modelList, materialTypeList, materialProofingModelList);
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 添加材质
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void materialAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_materials cmsg_add_materials = FormulaProto.cmsg_add_materials.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_materials.Builder smsg_add_materials = FormulaProto.smsg_add_materials.newBuilder();
            FormulaProto.add_material_result.Builder add_material_result = FormulaProto.add_material_result.newBuilder();
            int materialSize = cmsg_add_materials.getMaterialsList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (materialSize > 0) {
                    MaterialModel materialModel = new MaterialModel();
                    for (int i = 0; i < materialSize; i++) {
                        FormulaProto.material.Builder material = FormulaProto.material.newBuilder();
                        FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                        int vecReflectionSize = cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().size();
                        if (vecReflectionSize > 0) {
                            ColorantModel colorantModel = new ColorantModel();
                            LAB lab = DBUtilTool.getInstance().RefToLab(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList(), cmsg_add_materials.getMaterialsList().get(i).getColor().getName(), cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminantAngle());
                            colorantModel.setL(lab.getL());
                            colorantModel.setA(lab.getA());
                            colorantModel.setB(lab.getB());
                            colorantModel.setSpecularMode(cmsg_add_materials.getMaterialsList().get(i).getColor().getSpecularMode());
                            colorantModel.setIlluminantId(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminant());
                            colorantModel.setAngle(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminantAngle());
                            colorantModel.setRgb(DBUtilTool.getInstance().rgb(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList(), cmsg_add_materials.getMaterialsList().get(i).getColor().getName(), cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminantAngle()));
                            colorantModel.setReflection400(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(0));
                            colorantModel.setReflection410(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(1));
                            colorantModel.setReflection420(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(2));
                            colorantModel.setReflection430(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(3));
                            colorantModel.setReflection440(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(4));
                            colorantModel.setReflection450(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(5));
                            colorantModel.setReflection460(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(6));
                            colorantModel.setReflection470(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(7));
                            colorantModel.setReflection480(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(8));
                            colorantModel.setReflection490(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(9));
                            colorantModel.setReflection500(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(10));
                            colorantModel.setReflection510(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(11));
                            colorantModel.setReflection520(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(12));
                            colorantModel.setReflection530(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(13));
                            colorantModel.setReflection540(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(14));
                            colorantModel.setReflection550(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(15));
                            colorantModel.setReflection560(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(16));
                            colorantModel.setReflection570(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(17));
                            colorantModel.setReflection580(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(18));
                            colorantModel.setReflection590(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(19));
                            colorantModel.setReflection600(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(20));
                            colorantModel.setReflection610(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(21));
                            colorantModel.setReflection620(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(22));
                            colorantModel.setReflection630(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(23));
                            colorantModel.setReflection640(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(24));
                            colorantModel.setReflection650(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(25));
                            colorantModel.setReflection660(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(26));
                            colorantModel.setReflection670(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(27));
                            colorantModel.setReflection680(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(28));
                            colorantModel.setReflection690(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(29));
                            colorantModel.setReflection700(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList().get(30));
                            colorantModel.setImagePath(cmsg_add_materials.getMaterialsList().get(i).getColor().getImageFilePath());
                            Result resultColorant = colorantService.add(colorantModel);
                            if (resultColorant != null && resultColorant.getCode() == 1 && resultColorant.getData() != null) {
                                Colorant colorantEntity = (Colorant) resultColorant.getData();
                                materialModel.setColorantId(colorantEntity.getId());
                                colorant.setId(Integer.parseInt(String.valueOf(colorantEntity.getId())));
                                colorant.setSpecularMode(cmsg_add_materials.getMaterialsList().get(i).getColor().getSpecularMode());
                                colorant.setIlluminant(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminant());
                                colorant.setIlluminantAngle(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminantAngle());
                                colorant.setImageFilePath(cmsg_add_materials.getMaterialsList().get(i).getColor().getImageFilePath());
                                colorant.setName(cmsg_add_materials.getMaterialsList().get(i).getColor().getName());
                                colorant.addAllVecReflection(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList());
                            } else {
                                colorant.setSpecularMode(cmsg_add_materials.getMaterialsList().get(i).getColor().getSpecularMode());
                                colorant.setIlluminant(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminant());
                                colorant.setIlluminantAngle(cmsg_add_materials.getMaterialsList().get(i).getColor().getIlluminantAngle());
                                colorant.setImageFilePath(cmsg_add_materials.getMaterialsList().get(i).getColor().getImageFilePath());
                                colorant.setName(cmsg_add_materials.getMaterialsList().get(i).getColor().getName());
                                colorant.addAllVecReflection(cmsg_add_materials.getMaterialsList().get(i).getColor().getVecReflectionList());
                            }
                        }
                        materialModel.setName(cmsg_add_materials.getMaterialsList().get(i).getName());
                        materialModel.setDescription(cmsg_add_materials.getMaterialsList().get(i).getDescription());
                        materialModel.setWeight(cmsg_add_materials.getMaterialsList().get(i).getWeight());
                        materialModel.setOperatorId(user.getId());
                        materialModel.setAlias(cmsg_add_materials.getMaterialsList().get(i).getAlias());
                        materialModel.setDipDyeing(cmsg_add_materials.getMaterialsList().get(i).getDipDyeing());
                        materialModel.setLiquorRatio(cmsg_add_materials.getMaterialsList().get(i).getLiquorRatio());
                        materialModel.setMaterialTypeId(cmsg_add_materials.getMaterialsList().get(i).getType().getId());
                        Result result = materialService.add(materialModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            Material materialEntity = (Material) result.getData();
                            material.setId(Integer.parseInt(String.valueOf(materialEntity.getId())));
                            add_material_result.setResult(0);
                        } else {
                            add_material_result.setResult(1);
                        }
                        material.setName(cmsg_add_materials.getMaterialsList().get(i).getName());
                        material.setDescription(cmsg_add_materials.getMaterialsList().get(i).getDescription());
                        material.setWeight(cmsg_add_materials.getMaterialsList().get(i).getWeight());
                        material.setAlias(cmsg_add_materials.getMaterialsList().get(i).getAlias());
                        material.setDipDyeing(cmsg_add_materials.getMaterialsList().get(i).getDipDyeing());
                        material.setLiquorRatio(cmsg_add_materials.getMaterialsList().get(i).getLiquorRatio());
                        material.setType(cmsg_add_materials.getMaterialsList().get(i).getType());
                        material.setColor(colorant);
                        add_material_result.setErrValue(result.getMessage());
                        add_material_result.setAddMaterial(material);
                        smsg_add_materials.addResult(add_material_result);
                    }
                }

            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_MATERIALS, msg.resData, smsg_add_materials.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 修改材质
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void updateMaterialById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_materials cmsg_update_materials = FormulaProto.cmsg_update_materials.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_materials.Builder smsg_update_materials = FormulaProto.smsg_update_materials.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            int vecReflectionSize = cmsg_update_materials.getMat().getColor().getVecReflectionList().size();

            if (null != user) {
                MaterialModel materialModel = new MaterialModel();
                FormulaProto.material.Builder material = FormulaProto.material.newBuilder();
                materialModel.setId(cmsg_update_materials.getMat().getId());
                FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                if (vecReflectionSize > 0) {
                    ColorantModel colorantModel = new ColorantModel();
                    LAB lab = DBUtilTool.getInstance().RefToLab(cmsg_update_materials.getMat().getColor().getVecReflectionList(), cmsg_update_materials.getMat().getColor().getName(), cmsg_update_materials.getMat().getColor().getIlluminantAngle());
                    colorantModel.setL(lab.getL());
                    colorantModel.setA(lab.getA());
                    colorantModel.setB(lab.getB());
                    colorantModel.setSpecularMode(cmsg_update_materials.getMat().getColor().getSpecularMode());
                    colorantModel.setIlluminantId(cmsg_update_materials.getMat().getColor().getIlluminant());
                    colorantModel.setAngle(cmsg_update_materials.getMat().getColor().getIlluminantAngle());
                    colorantModel.setRgb(DBUtilTool.getInstance().rgb(cmsg_update_materials.getMat().getColor().getVecReflectionList(), cmsg_update_materials.getMat().getColor().getName(), cmsg_update_materials.getMat().getColor().getIlluminantAngle()));
                    colorantModel.setReflection400(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(0));
                    colorantModel.setReflection410(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(1));
                    colorantModel.setReflection420(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(2));
                    colorantModel.setReflection430(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(3));
                    colorantModel.setReflection440(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(4));
                    colorantModel.setReflection450(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(5));
                    colorantModel.setReflection460(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(6));
                    colorantModel.setReflection470(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(7));
                    colorantModel.setReflection480(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(8));
                    colorantModel.setReflection490(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(9));
                    colorantModel.setReflection500(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(10));
                    colorantModel.setReflection510(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(11));
                    colorantModel.setReflection520(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(12));
                    colorantModel.setReflection530(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(13));
                    colorantModel.setReflection540(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(14));
                    colorantModel.setReflection550(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(15));
                    colorantModel.setReflection560(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(16));
                    colorantModel.setReflection570(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(17));
                    colorantModel.setReflection580(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(18));
                    colorantModel.setReflection590(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(19));
                    colorantModel.setReflection600(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(20));
                    colorantModel.setReflection610(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(21));
                    colorantModel.setReflection620(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(22));
                    colorantModel.setReflection630(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(23));
                    colorantModel.setReflection640(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(24));
                    colorantModel.setReflection650(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(25));
                    colorantModel.setReflection660(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(26));
                    colorantModel.setReflection670(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(27));
                    colorantModel.setReflection680(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(28));
                    colorantModel.setReflection690(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(29));
                    colorantModel.setReflection700(cmsg_update_materials.getMat().getColor().getVecReflectionList().get(30));
                    colorantModel.setImagePath(cmsg_update_materials.getMat().getColor().getImageFilePath());
                    Result resultColorant = colorantService.add(colorantModel);
                    if (resultColorant != null && resultColorant.getCode() == 1 && resultColorant.getData() != null) {
                        Colorant colorantEntity = (Colorant) resultColorant.getData();
                        materialModel.setColorantId(colorantEntity.getId());
                        colorant.setId(Integer.parseInt(String.valueOf(colorantEntity.getId())));
                        colorant.setSpecularMode(cmsg_update_materials.getMat().getColor().getSpecularMode());
                        colorant.setIlluminant(cmsg_update_materials.getMat().getColor().getIlluminant());
                        colorant.setIlluminantAngle(cmsg_update_materials.getMat().getColor().getIlluminantAngle());
                        colorant.setImageFilePath(cmsg_update_materials.getMat().getColor().getImageFilePath());
                        colorant.setName(cmsg_update_materials.getMat().getColor().getName());
                        colorant.addAllVecReflection(cmsg_update_materials.getMat().getColor().getVecReflectionList());
                    } else {
                        colorant.setSpecularMode(cmsg_update_materials.getMat().getColor().getSpecularMode());
                        colorant.setIlluminant(cmsg_update_materials.getMat().getColor().getIlluminant());
                        colorant.setIlluminantAngle(cmsg_update_materials.getMat().getColor().getIlluminantAngle());
                        colorant.setImageFilePath(cmsg_update_materials.getMat().getColor().getImageFilePath());
                        colorant.setName(cmsg_update_materials.getMat().getColor().getName());
                        colorant.addAllVecReflection(cmsg_update_materials.getMat().getColor().getVecReflectionList());
                    }
                    materialModel.setColorantId(cmsg_update_materials.getMat().getColor().getId());
                }
                materialModel.setName(cmsg_update_materials.getMat().getName());
                materialModel.setDescription(cmsg_update_materials.getMat().getDescription());
                materialModel.setWeight(cmsg_update_materials.getMat().getWeight());
                materialModel.setAlias(cmsg_update_materials.getMat().getAlias());
                materialModel.setDipDyeing(cmsg_update_materials.getMat().getDipDyeing());
                materialModel.setLiquorRatio(cmsg_update_materials.getMat().getLiquorRatio());
                materialModel.setMaterialTypeId(cmsg_update_materials.getMat().getType().getId());
                Result result = materialService.update(materialModel);
                if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                    smsg_update_materials.setResult(0);
                } else {
                    smsg_update_materials.setResult(1);
                }
                material.setId(cmsg_update_materials.getMat().getId());
                material.setColor(colorant);
                material.setName(cmsg_update_materials.getMat().getName());
                material.setDescription(cmsg_update_materials.getMat().getDescription());
                material.setWeight(cmsg_update_materials.getMat().getWeight());
                material.setAlias(cmsg_update_materials.getMat().getAlias());
                material.setDipDyeing(cmsg_update_materials.getMat().getDipDyeing());
                material.setLiquorRatio(cmsg_update_materials.getMat().getLiquorRatio());
                material.setType(cmsg_update_materials.getMat().getType());
                smsg_update_materials.setMat(material);
                smsg_update_materials.setErrValue(result.getMessage());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_MATERIALS, msg.resData, smsg_update_materials.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 删除材质
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void deleteMaterialById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_materials cmsg_del_materials = FormulaProto.cmsg_del_materials.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_materials.Builder smsg_del_materials = FormulaProto.smsg_del_materials.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                MaterialModel materialModel = new MaterialModel();
                materialModel.setId(cmsg_del_materials.getMaterialId());
                Result result = materialService.deleteById(materialModel);
                if (result.getCode() == 1) {
                    smsg_del_materials.setResult(0);
                } else {
                    smsg_del_materials.setResult(1);
                }
                smsg_del_materials.setErrValue(result.getMessage());
                smsg_del_materials.setMaterialId(cmsg_del_materials.getMaterialId());

            }

            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_MATERIALS, msg.resData, smsg_del_materials.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }


    /**
     * @description 材质类型添加
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void materialTypeAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_material_type cmsg_add_material_type = FormulaProto.cmsg_add_material_type.parseFrom(msg.msgValue);
            FormulaProto.material_type.Builder material_type = FormulaProto.material_type.newBuilder();
            FormulaProto.add_material_type_result.Builder add_material_type_result = FormulaProto.add_material_type_result.newBuilder();
            FormulaProto.smsg_add_material_type.Builder smsg_add_material_type = FormulaProto.smsg_add_material_type.newBuilder();
            int materialTypeSize = cmsg_add_material_type.getTypesList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (materialTypeSize > 0) {
                    MaterialTypeModel materialTypeModel = new MaterialTypeModel();
                    for (int i = 0; i < materialTypeSize; i++) {
                        materialTypeModel.setName(cmsg_add_material_type.getTypesList().get(i).getName());
                        materialTypeModel.setDescription(cmsg_add_material_type.getTypesList().get(i).getDescription());
                        materialTypeModel.setParentId(cmsg_add_material_type.getTypesList().get(i).getParentId());
                        Result result = materialTypeService.add(materialTypeModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            MaterialType materialTypeEntity = (MaterialType) result.getData();
                            material_type.setId(Integer.parseInt(String.valueOf(materialTypeEntity.getId())));
                            material_type.setName(materialTypeEntity.getName());
                            material_type.setDescription(materialTypeEntity.getDescription());
                            material_type.setParentId(cmsg_add_material_type.getTypesList().get(i).getParentId());
                            add_material_type_result.setResult(0);
                        } else {
                            material_type.setName(cmsg_add_material_type.getTypesList().get(i).getName());
                            material_type.setDescription(cmsg_add_material_type.getTypesList().get(i).getDescription());
                            material_type.setParentId(cmsg_add_material_type.getTypesList().get(i).getParentId());
                            add_material_type_result.setResult(1);
                        }
                        add_material_type_result.setErrValue(result.getMessage());
                        add_material_type_result.setAddType(material_type);
                        smsg_add_material_type.addResult(add_material_type_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_MATERIAL_TYPE, msg.resData, smsg_add_material_type.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }


    /**
     * @description 材质类型修改
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void updateMaterialTypeById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_material_type cmsg_update_material_type = FormulaProto.cmsg_update_material_type.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_material_type.Builder smsg_update_material_type = FormulaProto.smsg_update_material_type.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                FormulaProto.material_type.Builder material_type = FormulaProto.material_type.newBuilder();
                MaterialTypeModel materialTypeModel = new MaterialTypeModel();
                materialTypeModel.setId(cmsg_update_material_type.getType().getId());
                materialTypeModel.setName(cmsg_update_material_type.getType().getName());
                materialTypeModel.setDescription(cmsg_update_material_type.getType().getDescription());
                materialTypeModel.setParentId(cmsg_update_material_type.getType().getParentId());
                Result result = materialTypeService.update(materialTypeModel);
                if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                    MaterialType materialTypeEntity = (MaterialType) result.getData();
                    material_type.setId(Integer.parseInt(String.valueOf(materialTypeEntity.getId())));
                    smsg_update_material_type.setResult(0);
                } else {
                    smsg_update_material_type.setResult(1);
                }
                material_type.setName(cmsg_update_material_type.getType().getName());
                material_type.setDescription(cmsg_update_material_type.getType().getDescription());
                material_type.setParentId(cmsg_update_material_type.getType().getParentId());
                smsg_update_material_type.setType(material_type);
                smsg_update_material_type.setErrValue(result.getMessage());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_MATERIAL_TYPE, msg.resData, smsg_update_material_type.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }


    /**
     * @description 材质类型删除
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void deleteMaterialTypeById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_material_type cmsg_del_material_type = FormulaProto.cmsg_del_material_type.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_material_type.Builder smsg_del_material_type = FormulaProto.smsg_del_material_type.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                MaterialTypeModel materialTypeModel = new MaterialTypeModel();
                materialTypeModel.setId(cmsg_del_material_type.getId());
                Result result = materialTypeService.deleteById(materialTypeModel);
                if (result.getCode() == 1) {
                    smsg_del_material_type.setResult(0);
                } else {
                    smsg_del_material_type.setResult(1);
                }
                smsg_del_material_type.setErrValue(result.getMessage());
                smsg_del_material_type.setId(cmsg_del_material_type.getId());
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_MATERIAL_TYPE, msg.resData, smsg_del_material_type.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }


    /**
     * @description 材质打样添加
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void materialProofingAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_materials_proofing cmsg_add_materials_proofing = FormulaProto.cmsg_add_materials_proofing.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_materials_proofing.Builder smsg_add_materials_proofing = FormulaProto.smsg_add_materials_proofing.newBuilder();
            int materialsProofingSize = cmsg_add_materials_proofing.getProofingList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (materialsProofingSize > 0) {
                    MaterialProofingModel materialProofingModel = new MaterialProofingModel();
                    for (int i = 0; i < materialsProofingSize; i++) {
                        FormulaProto.material_proofing.Builder material_proofing = FormulaProto.material_proofing.newBuilder();
                        FormulaProto.add_material_proofing_result.Builder add_material_proofing_result = FormulaProto.add_material_proofing_result.newBuilder();
                        materialProofingModel.setConc(cmsg_add_materials_proofing.getProofingList().get(i).getConc());
                        materialProofingModel.setDyeId(cmsg_add_materials_proofing.getProofingList().get(i).getDyeId());
                        FormulaProto.colorant.Builder colorant = FormulaProto.colorant.newBuilder();
                        ColorantModel colorantModel = new ColorantModel();
                        LAB lab = DBUtilTool.getInstance().RefToLab(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList(), cmsg_add_materials_proofing.getProofingList().get(i).getColor().getName(), cmsg_add_materials_proofing.getProofingList().get(i).getColor().getIlluminantAngle());
                        colorantModel.setL(lab.getL());
                        colorantModel.setA(lab.getA());
                        colorantModel.setB(lab.getB());
                        colorantModel.setSpecularMode(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getSpecularMode());
                        colorantModel.setIlluminantId(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getIlluminant());
                        colorantModel.setAngle(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getIlluminantAngle());
                        colorantModel.setRgb(DBUtilTool.getInstance().rgb(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList(), cmsg_add_materials_proofing.getProofingList().get(i).getColor().getName(), cmsg_add_materials_proofing.getProofingList().get(i).getColor().getIlluminantAngle()));
                        colorantModel.setReflection400(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(0));
                        colorantModel.setReflection410(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(1));
                        colorantModel.setReflection420(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(2));
                        colorantModel.setReflection430(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(3));
                        colorantModel.setReflection440(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(4));
                        colorantModel.setReflection450(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(5));
                        colorantModel.setReflection460(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(6));
                        colorantModel.setReflection470(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(7));
                        colorantModel.setReflection480(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(8));
                        colorantModel.setReflection490(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(9));
                        colorantModel.setReflection500(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(10));
                        colorantModel.setReflection510(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(11));
                        colorantModel.setReflection520(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(12));
                        colorantModel.setReflection530(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(13));
                        colorantModel.setReflection540(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(14));
                        colorantModel.setReflection550(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(15));
                        colorantModel.setReflection560(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(16));
                        colorantModel.setReflection570(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(17));
                        colorantModel.setReflection580(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(18));
                        colorantModel.setReflection590(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(19));
                        colorantModel.setReflection600(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(20));
                        colorantModel.setReflection610(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(21));
                        colorantModel.setReflection620(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(22));
                        colorantModel.setReflection630(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(23));
                        colorantModel.setReflection640(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(24));
                        colorantModel.setReflection650(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(25));
                        colorantModel.setReflection660(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(26));
                        colorantModel.setReflection670(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(27));
                        colorantModel.setReflection680(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(28));
                        colorantModel.setReflection690(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(29));
                        colorantModel.setReflection700(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getVecReflectionList().get(30));
                        colorantModel.setImagePath(cmsg_add_materials_proofing.getProofingList().get(i).getColor().getImageFilePath());
                        Result resultColorant = colorantService.add(colorantModel);
                        if (resultColorant != null && resultColorant.getCode() == 1 && resultColorant.getData() != null) {
                            Colorant colorantEntity = (Colorant) resultColorant.getData();
                            materialProofingModel.setColorantId(colorantEntity.getId());
                        }
                        materialProofingModel.setMaterialId(cmsg_add_materials_proofing.getProofingList().get(i).getMaterialId());
                        Result result = materialProofingService.add(materialProofingModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            MaterialProofing materialProofing = (MaterialProofing) result.getData();
                            material_proofing.setId(Integer.parseInt(String.valueOf(materialProofing.getId())));
                            material_proofing.setConc(materialProofing.getConc());
                            if (materialProofing.getDye() != null) {
                                material_proofing.setDyeId(Integer.parseInt(String.valueOf(materialProofing.getDye().getId())));

                            }
                            if (materialProofing.getColorant() != null) {
                                colorant.setId(Integer.parseInt(String.valueOf(materialProofing.getColorant().getId())));
                                material_proofing.setColor(colorant);

                            }
                            if (materialProofing.getMaterial() != null) {
                                material_proofing.setMaterialId(Integer.parseInt(String.valueOf(materialProofing.getMaterial().getId())));

                            }
                            add_material_proofing_result.setResult(0);
                            add_material_proofing_result.setErrValue(result.getMessage());
                        } else {
                            material_proofing.setConc(cmsg_add_materials_proofing.getProofingList().get(i).getConc());
                            material_proofing.setDyeId(cmsg_add_materials_proofing.getProofingList().get(i).getDyeId());
                            material_proofing.setColor(cmsg_add_materials_proofing.getProofingList().get(i).getColor());
                            material_proofing.setMaterialId(cmsg_add_materials_proofing.getProofingList().get(i).getMaterialId());
                            add_material_proofing_result.setResult(1);
                            add_material_proofing_result.setErrValue(result.getMessage());
                        }
                        add_material_proofing_result.setAddProofing(material_proofing);
                        smsg_add_materials_proofing.addResult(add_material_proofing_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_MATERIALS_PROOFING, msg.resData, smsg_add_materials_proofing.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 材质打样删除
     * @author: fangyong
     * @date 2018/11/2 11:04
     */
    protected void deleteMaterialProofingById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_materials_proofing cmsg_del_materials_proofing = FormulaProto.cmsg_del_materials_proofing.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_materials_proofing.Builder smsg_del_materials_proofing = FormulaProto.smsg_del_materials_proofing.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                MaterialProofingModel model = new MaterialProofingModel();
                model.setId(cmsg_del_materials_proofing.getId());
                Result result = materialProofingService.deleteById(model);
                if (result.getCode() == 1) {
                    smsg_del_materials_proofing.setResult(0);
                } else {
                    smsg_del_materials_proofing.setResult(1);
                }
                smsg_del_materials_proofing.setErrValue(result.getMessage());
                smsg_del_materials_proofing.setId(cmsg_del_materials_proofing.getId());
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_MATERIALS_PROOFING, msg.resData, smsg_del_materials_proofing.build());
            }

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 光源列表
     * @author: fangyong
     * @date 2018/10/23 14:55
     */
    protected void illuminantList(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_illuminant req = FormulaProto.cmsg_add_illuminant.parseFrom(msg.msgValue);
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                List<Illuminant> illuminantList = illuminantService.findAll();
                user.illuminantList(illuminantList);
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 光源添加
     * @author: fangyong
     * @date 2018/10/23 14:35
     */
    protected void illuminantAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_illuminant cmsg_add_illuminant = FormulaProto.cmsg_add_illuminant.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_illuminant.Builder smsg_add_illuminant = FormulaProto.smsg_add_illuminant.newBuilder();
            FormulaProto.add_illuminant_result.Builder add_illuminant_result = FormulaProto.add_illuminant_result.newBuilder();
            FormulaProto.illuminant.Builder _illuminant = FormulaProto.illuminant.newBuilder();
            int illuminantListSize = cmsg_add_illuminant.getIlluminantList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (illuminantListSize > 0) {
                    IlluminantModel illuminantModel = new IlluminantModel();
                    for (int i = 0; i < illuminantListSize; i++) {
                        illuminantModel.setId(cmsg_add_illuminant.getIlluminantList().get(i).getId());
                        illuminantModel.setName(cmsg_add_illuminant.getIlluminantList().get(i).getName());
                        illuminantModel.setDescription(cmsg_add_illuminant.getIlluminantList().get(i).getDescription());
                        illuminantModel.setAngle(cmsg_add_illuminant.getIlluminantList().get(i).getAngle());
                        Result result = illuminantService.add(illuminantModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            Illuminant illuminant = (Illuminant) result.getData();
                            _illuminant.setId(Integer.parseInt(String.valueOf(illuminant.getId())));
                            _illuminant.setAngle(illuminant.getAngle());
                            _illuminant.setName(illuminant.getName());
                            _illuminant.setDescription(illuminant.getDescription());
                            add_illuminant_result.setResult(0);
                            add_illuminant_result.setErrValue(result.getMessage());
                        } else {
                            _illuminant.setName(cmsg_add_illuminant.getIlluminantList().get(i).getName());
                            _illuminant.setDescription(cmsg_add_illuminant.getIlluminantList().get(i).getDescription());
                            _illuminant.setAngle(cmsg_add_illuminant.getIlluminantList().get(i).getAngle());
                            add_illuminant_result.setResult(1);
                            add_illuminant_result.setErrValue(result.getMessage());
                        }
                        add_illuminant_result.setIlluminant(_illuminant);
                        smsg_add_illuminant.addResult(i, add_illuminant_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_ILLUMINANT, msg.resData, smsg_add_illuminant.build());
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 光源修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void illuminantUpdate(ChannelId channelId, ProtoMessage msg) {

    }

    /**
     * @description 光源删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void illuminantDelete(ChannelId channelId, ProtoMessage msg) {

    }

    /**
     * @description 染料组添加
     * @author: fangyong
     * @date 2018/10/23 14:35
     */
    protected void dyeGroupAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dye_groups cmsg_add_dye_groups = FormulaProto.cmsg_add_dye_groups.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dye_groups.Builder smsg_add_dye_groups = FormulaProto.smsg_add_dye_groups.newBuilder();
            FormulaProto.add_dye_groups_result.Builder add_dye_groups_result = FormulaProto.add_dye_groups_result.newBuilder();
            int add_dye_groupListSize = cmsg_add_dye_groups.getLstGroupList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (add_dye_groupListSize > 0) {
                    DyeGroupModel dyeGroupModel = new DyeGroupModel();
                    for (int i = 0; i < add_dye_groupListSize; i++) {
                        String ids = "";
                        for (int dyeId : cmsg_add_dye_groups.getLstGroupList().get(i).getDyeIdList()) {
                            ids += dyeId + ",";
                        }
                        FormulaProto.dye_group.Builder dye_group = FormulaProto.dye_group.newBuilder();
                        dyeGroupModel.setName(cmsg_add_dye_groups.getLstGroupList().get(i).getName());
                        dyeGroupModel.setDescription(cmsg_add_dye_groups.getLstGroupList().get(i).getDescription());
                        dyeGroupModel.setDyeIds(ids);
                        Result result = dyeGroupService.add(dyeGroupModel);
                        if (result.getCode() == 1 && !StringUtil.isBlank(result.getData())) {
                            DyeGroup dyeGroup = (DyeGroup) result.getData();
                            dye_group.setId(Integer.parseInt(String.valueOf(dyeGroup.getId())));
                            dye_group.setName(dyeGroup.getName());
                            dye_group.addAllDyeId(dye_group.getDyeIdList());
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
                            add_dye_groups_result.setResult(0);
                            add_dye_groups_result.setErrValue(result.getMessage());
                            add_dye_groups_result.setDyeGroup(dye_group);
                        } else {
                            dye_group.setName(cmsg_add_dye_groups.getLstGroupList().get(i).getName());
                            dye_group.setDescription(cmsg_add_dye_groups.getLstGroupList().get(i).getDescription());
                            for (int j = 0; j < cmsg_add_dye_groups.getLstGroupList().get(i).getDyeIdList().size(); j++) {
                                dye_group.setDyeId(j, cmsg_add_dye_groups.getLstGroupList().get(i).getDyeIdList().get(j));
                            }
                            add_dye_groups_result.setResult(1);
                            add_dye_groups_result.setErrValue(result.getMessage());
                            add_dye_groups_result.setDyeGroup(dye_group);
                        }
                        add_dye_groups_result.setDyeGroup(dye_group);
                        smsg_add_dye_groups.addResult(i, add_dye_groups_result);
                    }
                }
                FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYE_GROUPS, msg.resData, smsg_add_dye_groups.build());
            }
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料组修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void updateDyeGroupById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_dye_group cmsg_update_dye_group = FormulaProto.cmsg_update_dye_group.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_dye_group.Builder smsg_update_dye_group = FormulaProto.smsg_update_dye_group.newBuilder();
            FormulaProto.dye_group.Builder dye_group = FormulaProto.dye_group.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeGroupModel groupModel = new DyeGroupModel();
                groupModel.setId(cmsg_update_dye_group.getGroup().getId());
                groupModel.setName(cmsg_update_dye_group.getGroup().getName());
                String ids = "";
                for (int dyeId : cmsg_update_dye_group.getGroup().getDyeIdList()) {
                    ids += dyeId + ",";
                }
                groupModel.setDyeIds(ids);
                groupModel.setDescription(cmsg_update_dye_group.getGroup().getDescription());
                groupModel.setName(cmsg_update_dye_group.getGroup().getName());
                Result result = dyeGroupService.update(groupModel);
                if (result != null && result.getCode() == 1 && result.getData() != null) {
                    smsg_update_dye_group.setResult(0);
                } else {
                    smsg_update_dye_group.setResult(1);
                }
                smsg_update_dye_group.setErrValue(result.getMessage());
                dye_group.setId(cmsg_update_dye_group.getGroup().getId());
                dye_group.addAllDyeId(cmsg_update_dye_group.getGroup().getDyeIdList());
                dye_group.setName(cmsg_update_dye_group.getGroup().getName());
                dye_group.setDescription(cmsg_update_dye_group.getGroup().getDescription());
                smsg_update_dye_group.setGroup(dye_group);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_DYE_GROUP, msg.resData, smsg_update_dye_group.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料组删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void deleteDyeGroupById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_dye_group cmsg_del_dye_group = FormulaProto.cmsg_del_dye_group.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_dye_group.Builder smsg_del_dye_group = FormulaProto.smsg_del_dye_group.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeGroupModel dyeGroupModel = new DyeGroupModel();
                dyeGroupModel.setId(cmsg_del_dye_group.getDyeGroupId());
                Result result = dyeGroupService.deleteById(dyeGroupModel);
                if (result != null && result.getCode() == 1) {
                    smsg_del_dye_group.setResult(0);
                } else {
                    smsg_del_dye_group.setResult(1);
                }
                smsg_del_dye_group.setErrValue(result.getMessage());
                smsg_del_dye_group.setDyeGroupId(cmsg_del_dye_group.getDyeGroupId());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_DYE_GROUP, msg.resData, smsg_del_dye_group.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }

    }

    /**
     * @description 染料类型修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void updateDyeTypeById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_dye_type cmsg_update_dye_type = FormulaProto.cmsg_update_dye_type.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_dye_type.Builder smsg_update_dye_type = FormulaProto.smsg_update_dye_type.newBuilder();
            FormulaProto.dye_type.Builder dye_type = FormulaProto.dye_type.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeTypeModel model = new DyeTypeModel();
                model.setId(cmsg_update_dye_type.getType().getId());
                model.setName(cmsg_update_dye_type.getType().getName());
                model.setAlias(cmsg_update_dye_type.getType().getAlias());
                model.setParentType(cmsg_update_dye_type.getType().getParentType().getId());
                model.setDescription(cmsg_update_dye_type.getType().getDescription());
                Result result = dyeTypeService.update(model);
                if (result != null && result.getCode() == 1 && result.getData() != null) {
                    smsg_update_dye_type.setResult(0);
                } else {
                    smsg_update_dye_type.setResult(1);
                }
                smsg_update_dye_type.setErrValue(result.getMessage());
                dye_type.setId(cmsg_update_dye_type.getType().getId());
                dye_type.setName(cmsg_update_dye_type.getType().getName());
                dye_type.setAlias(cmsg_update_dye_type.getType().getAlias());
                dye_type.setParentType(cmsg_update_dye_type.getType().getParentType());
                dye_type.setDescription(cmsg_update_dye_type.getType().getDescription());
                smsg_update_dye_type.setType(dye_type);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_DYE_TYPE, msg.resData, smsg_update_dye_type.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料类型删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void deleteDyeTypeById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_dye_type cmsg_del_dye_type = FormulaProto.cmsg_del_dye_type.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_dye_type.Builder smsg_del_dye_type = FormulaProto.smsg_del_dye_type.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeTypeModel model = new DyeTypeModel();
                model.setId(cmsg_del_dye_type.getDyeTypeId());
                Result result = dyeTypeService.deleteById(model);
                if (result != null && result.getCode() == 1) {
                    smsg_del_dye_type.setResult(0);
                } else {
                    smsg_del_dye_type.setResult(1);
                }
                smsg_del_dye_type.setErrValue(result.getMessage());
                smsg_del_dye_type.setDyeTypeId(cmsg_del_dye_type.getDyeTypeId());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_DYE_TYPE, msg.resData, smsg_del_dye_type.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }

    }

    /**
     * @description 染料厂商修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void updateManufacturerById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_manufacturer cmsg_update_manufacturer = FormulaProto.cmsg_update_manufacturer.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_manufacturer.Builder smsg_update_manufacturer = FormulaProto.smsg_update_manufacturer.newBuilder();
            FormulaProto.dye_manufacturer.Builder dye_manufacturer = FormulaProto.dye_manufacturer.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeManufacturerModel dyeManufacturerModel = new DyeManufacturerModel();
                dyeManufacturerModel.setId(cmsg_update_manufacturer.getManufacturer().getId());
                dyeManufacturerModel.setCompany(cmsg_update_manufacturer.getManufacturer().getCompany());
                dyeManufacturerModel.setAddress(cmsg_update_manufacturer.getManufacturer().getAddress());
                dyeManufacturerModel.setZipcode(cmsg_update_manufacturer.getManufacturer().getZipcode());
                dyeManufacturerModel.setCountry(cmsg_update_manufacturer.getManufacturer().getCountry());
                dyeManufacturerModel.setCity(cmsg_update_manufacturer.getManufacturer().getCity());
                dyeManufacturerModel.setState(cmsg_update_manufacturer.getManufacturer().getState());
                dyeManufacturerModel.setContact(cmsg_update_manufacturer.getManufacturer().getContact());
                dyeManufacturerModel.setPhoneNumber(cmsg_update_manufacturer.getManufacturer().getPhoneNumber());
                dyeManufacturerModel.setFaxNumber(cmsg_update_manufacturer.getManufacturer().getFaxNumber());
                dyeManufacturerModel.setEmail(cmsg_update_manufacturer.getManufacturer().getEmail());
                Result result = dyeManufacturerService.update(dyeManufacturerModel);
                if (result.getCode() == 1 && result.getData() != null) {
                    smsg_update_manufacturer.setResult(0);
                } else {
                    smsg_update_manufacturer.setResult(1);
                }
                dye_manufacturer.setId(cmsg_update_manufacturer.getManufacturer().getId());
                dye_manufacturer.setCompany(cmsg_update_manufacturer.getManufacturer().getCompany());
                dye_manufacturer.setAddress(cmsg_update_manufacturer.getManufacturer().getAddress());
                dye_manufacturer.setZipcode(cmsg_update_manufacturer.getManufacturer().getZipcode());
                dye_manufacturer.setCountry(cmsg_update_manufacturer.getManufacturer().getCountry());
                dye_manufacturer.setCity(cmsg_update_manufacturer.getManufacturer().getCity());
                dye_manufacturer.setState(cmsg_update_manufacturer.getManufacturer().getState());
                dye_manufacturer.setContact(cmsg_update_manufacturer.getManufacturer().getContact());
                dye_manufacturer.setPhoneNumber(cmsg_update_manufacturer.getManufacturer().getPhoneNumber());
                dye_manufacturer.setFaxNumber(cmsg_update_manufacturer.getManufacturer().getFaxNumber());
                dye_manufacturer.setEmail(cmsg_update_manufacturer.getManufacturer().getEmail());
                smsg_update_manufacturer.setErrValue(result.getMessage());
                smsg_update_manufacturer.setManufacturer(dye_manufacturer);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_MANUFACTURER, msg.resData, smsg_update_manufacturer.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料厂商删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void deleteManufacturerById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_manufacturer cmsg_del_manufacturer = FormulaProto.cmsg_del_manufacturer.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_manufacturer.Builder smsg_del_manufacturer = FormulaProto.smsg_del_manufacturer.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeManufacturerModel model = new DyeManufacturerModel();
                model.setId(cmsg_del_manufacturer.getManufacturerId());
                Result result = dyeManufacturerService.deleteById(model);
                if (result != null && result.getCode() == 1) {
                    smsg_del_manufacturer.setResult(0);
                } else {
                    smsg_del_manufacturer.setResult(1);
                }
                smsg_del_manufacturer.setErrValue(result.getMessage());
                smsg_del_manufacturer.setManufacturerId(cmsg_del_manufacturer.getManufacturerId());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_MANUFACTURER, msg.resData, smsg_del_manufacturer.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }

    }


    /**
     * @description 染料颜色添加
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void dyeColorAdd(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_add_dye_color cmsg_add_dye_color = FormulaProto.cmsg_add_dye_color.parseFrom(msg.msgValue);
            FormulaProto.smsg_add_dye_color.Builder smsg_add_dye_color = FormulaProto.smsg_add_dye_color.newBuilder();
            int dye_colorSize = cmsg_add_dye_color.getColorsList().size();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                if (cmsg_add_dye_color.getColorsList() != null && dye_colorSize > 0) {
                    for (int i = 0; i < dye_colorSize; i++) {
                        FormulaProto.dye_color_result.Builder dye_color_result = FormulaProto.dye_color_result.newBuilder();

                        FormulaProto.dye_color.Builder dye_color = FormulaProto.dye_color.newBuilder();
                        DyeColorModel model = new DyeColorModel();
                        model.setId(cmsg_add_dye_color.getColorsList().get(i).getId());
                        model.setName(cmsg_add_dye_color.getColorsList().get(i).getName());
                        model.setAlias(cmsg_add_dye_color.getColorsList().get(i).getAlias());
                        model.setRgb(cmsg_add_dye_color.getColorsList().get(i).getRgb());
                        Result result = dyeColorService.add(model);
                        if (result != null && result.getCode() == 1 && result.getData() != null) {
                            dye_color_result.setResult(0);
                        } else {
                            dye_color_result.setResult(1);
                        }
                        dye_color.setId(cmsg_add_dye_color.getColorsList().get(i).getId());
                        dye_color.setName(cmsg_add_dye_color.getColorsList().get(i).getName());
                        dye_color.setAlias(cmsg_add_dye_color.getColorsList().get(i).getAlias());
                        dye_color.setRgb(cmsg_add_dye_color.getColorsList().get(i).getRgb());
                        dye_color_result.setErrValue(result.getMessage());
                        dye_color_result.setColor(dye_color);
                        smsg_add_dye_color.setResult(i, dye_color_result);
                    }
                }

            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_ADD_DYE_COLOR, msg.resData, smsg_add_dye_color.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料颜色修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void updateDyeColorById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_update_dye_color cmsg_update_dye_color = FormulaProto.cmsg_update_dye_color.parseFrom(msg.msgValue);
            FormulaProto.smsg_update_dye_color.Builder smsg_update_dye_color = FormulaProto.smsg_update_dye_color.newBuilder();
            FormulaProto.dye_color_result.Builder dye_color_result = FormulaProto.dye_color_result.newBuilder();
            FormulaProto.dye_color.Builder dye_color = FormulaProto.dye_color.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeColorModel model = new DyeColorModel();
                model.setId(cmsg_update_dye_color.getColors().getId());
                model.setName(cmsg_update_dye_color.getColors().getName());
                model.setAlias(cmsg_update_dye_color.getColors().getAlias());
                model.setRgb(cmsg_update_dye_color.getColors().getRgb());
                Result result = dyeColorService.update(model);
                if (result != null && result.getCode() == 1 && result.getData() != null) {
                    dye_color_result.setResult(0);
                } else {
                    dye_color_result.setResult(1);
                }
                dye_color.setId(cmsg_update_dye_color.getColors().getId());
                dye_color.setName(cmsg_update_dye_color.getColors().getName());
                dye_color.setAlias(cmsg_update_dye_color.getColors().getAlias());
                dye_color.setRgb(cmsg_update_dye_color.getColors().getRgb());
                dye_color_result.setErrValue(result.getMessage());
                dye_color_result.setColor(dye_color);
                smsg_update_dye_color.setResult(dye_color_result);
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_UPDATE_DYE_COLOR, msg.resData, smsg_update_dye_color.build());
        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }
    }

    /**
     * @description 染料颜色删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void deleteDyeColorById(ChannelId channelId, ProtoMessage msg) {
        try {
            FormulaProto.cmsg_del_dye_color cmsg_del_dye_color = FormulaProto.cmsg_del_dye_color.parseFrom(msg.msgValue);
            FormulaProto.smsg_del_dye_color.Builder smsg_del_dye_color = FormulaProto.smsg_del_dye_color.newBuilder();
            User user = UserManager.threadInstance().find(channelId);
            if (null != user) {
                DyeTypeModel model = new DyeTypeModel();
                model.setId(cmsg_del_dye_color.getId());
                Result result = dyeTypeService.deleteById(model);
                if (result != null && result.getCode() == 1) {
                    smsg_del_dye_color.setResult(0);
                } else {
                    smsg_del_dye_color.setResult(1);
                }
                smsg_del_dye_color.setErrValue(result.getMessage());
                smsg_del_dye_color.setId(cmsg_del_dye_color.getId());
            }
            FormulaInputService.getInstance().sendPacktToUserList(channelId, FormulaProto.MessageType.SMSG_DEL_DYE_COLOR, msg.resData, smsg_del_dye_color.build());

        } catch (InvalidProtocolBufferException e) {
            LogUtil.error(" 错误信息:  "+e.getMessage()+"       栈信息："+e.getStackTrace());
        }

    }


    /**
     * @description 浓度列表
     * @author: fangyong
     * @date 2018/10/23 14:55
     */
    protected void concentrationList(ChannelId channelId, ProtoMessage msg) {

    }

    /**
     * @description 浓度添加
     * @author: fangyong
     * @date 2018/10/23 14:35
     */
    protected void concentrationAdd(ChannelId channelId, ProtoMessage msg) {

    }

    /**
     * @description 浓度修改
     * @author: fangyong
     * @date 2018/10/23 14:39
     */
    protected void concentrationUpdate(ChannelId channelId, ProtoMessage msg) {

    }

    /**
     * @description 浓度删除
     * @author: fangyong
     * @date 2018/10/23 14:48
     */
    protected void concentrationDelete(ChannelId channelId, ProtoMessage msg) {


    }
}
