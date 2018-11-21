/**
 * 定义测试环境参数配置，全局变量:env
 */
(function(){
    this.com = this.com || {};
    com.env = {
        redis:{
            port:6379,
            host:"192.168.2.84"
        },
        countNum:[],
        sessionNum:[],
        cdn_url:"static",
        //开发版
        core_path: "http://192.168.2.84:8080",
        //部署版
        //core_path: "http://192.168.2.84:8080/sys_api-1.0.0.0",
        guid:function(){
            return new Date().getTime().toString()+Math.abs((((1+Math.random())*0x10000)|0)).toString();
        }
    };
})();