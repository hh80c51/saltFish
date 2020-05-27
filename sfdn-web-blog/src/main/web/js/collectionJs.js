/**
 * Created by dzc on 17.11.24.
 * 公共js合集
 */
// 此版本号后期可剔除
var version = 201711301;

document.write('<script language="javascript" src="${project_base_path}/bootstrap-3.3.7/js/tests/vendor/jquery.min.js" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/bootstrap-3.3.7/dist/js/bootstrap.min.js" > <\/script>');
// // custom js util
document.write('<script language="javascript" src="${project_base_path}/js/constants.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/pageUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/cookieUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/extendUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/containerUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/dateUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/ssoUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/validUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/searchChooseUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/renderUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/dialogUtil.js?_v=' + version + '"> <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/ajaxUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/securityUtil.js?_v=' + version + '" > <\/script>');
//含有$(function(){})加载及运行的工具类尽量放在后面
document.write('<script language="javascript" src="${project_base_path}/js/utils/commonUtil.js?_v=' + version + '" > <\/script>');
document.write('<script language="javascript" src="${project_base_path}/js/utils/moneyUtil.js?_v=' + version + '" > <\/script>');
// document.write('<script language="javascript" src="../js/validform/js/Validform_v5.3.2_min.js?_v=' + version + '" > <\/script>');
// document.write('<script language="javascript" src="../js/validform/js/Validform_Datatype.js?_v=' + version + '" > <\/script>');