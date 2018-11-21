/**
 * @author zzhu
 * 系统配置业务
 */
'use strict';
const co = require('co');
const midx = require('./midx');

/**
 * 查询配方列表
 */
exports.getFormulaList = function* (model) {
    return co(function* () {
        try {
            let result = yield midx('/formula/formula/list', model);
            return {
                rows: (result && result.data && result.data.list && result.data.list instanceof Array ? result.data.list : []),
                total: (result && result.data && result.data.page && result.data.page.total && !isNaN(result.data.page.total) ? result.data.page.total : 0)
            };
        } catch (error) {
            console.log(error);
            return {list: [], total: 0, code: -1, message: error};
        }
    })
}
/**
 * 查询浓度列表
 */
exports.getConcentrationList = function* (model) {
    return co(function* () {
        try {
            let result = yield midx('/formula/concentration/list', model);
            return {
                rows: (result && result.data && result.data.list && result.data.list instanceof Array ? result.data.list : []),
                total: (result && result.data && result.data.page && result.data.page.total && !isNaN(result.data.page.total) ? result.data.page.total : 0)
            };
        } catch (error) {
            console.log(error);
            return {list: [], total: 0, code: -1, message: error};
        }
    })
}
