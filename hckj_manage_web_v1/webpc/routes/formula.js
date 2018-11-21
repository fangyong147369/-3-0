'use strict';
const formula = require('../controllers/formula');
const session = require('../filters/session-filter');
const koaBody = require('koa-body')();
module.exports = function (router) {
    router.get('/formula/formula',koaBody,session.auth,formula.formula);
    router.post('/formula/getFormulaList',koaBody,session.auth,formula.getFormulaList);
    router.get('/formula/concentration',koaBody,session.auth,formula.concentration);
    router.post('/formula/getConcentrationList',koaBody,session.auth,formula.getConcentrationList);
};