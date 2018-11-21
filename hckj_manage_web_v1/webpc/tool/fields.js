/**
 * 定义全局变量 : com.biz.fields ,转义type/state等数值
 */

(function () {
    this.com = this.com || {};
    com.biz = com.biz || {};
    com.biz.fields = {
        newsType:{
            "Corporate_News": {v: 1, desc: "网金客—企业新闻"},
            "Platform_Information": {v: 2, desc: "网金客—平台资讯"},
            "Industry_News": {v: 3, desc: "网金客—行业动态"},
            "ZC_News": {v: 4, desc: "再城—新闻资讯"}
        },
        applyType:{
            CZ:{v:2,desc:'存臻申请'},
            WJK:{v:1,desc:'网金客申请'}
        }
    }
    com.biz.fields.getDesc = function (type, v) {
        for (var key in type) {
            if (type[key].v == v)
                return type[key].desc;
        }
        return '';
    }

    if (typeof module !== 'undefined' && module.exports) {

    }

    else {
        this.__fields = com.biz.fields;
    }

})();

