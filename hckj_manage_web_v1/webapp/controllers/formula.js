'use strict';
const biz_formula=require('../../base/formula');
/**
 * 查询配方列表
 */
exports.getFormulaList=function*(){
    let model=this.request.body.model;
    let body=yield biz_formula.getFormulaList(model);
    this.body=body;
}
/**
 * 查询配方列表页面
 */
exports.formula=function*(){
    yield this.render('/formula/formula',{req:this});
}
/**
 * 查询浓度列表
 */
exports.getConcentrationList=function*(){
    let model=this.request.body.model;
    let body=yield biz_formula.getConcentrationList(model);
    this.body=body;
}
/**
 * 查询浓度列表页面
 */
exports.concentration=function*(){
    let formulaId=this.request.query.formulaId||0;
    yield this.render('/formula/concentration',{req:this,formulaId:formulaId});
}


