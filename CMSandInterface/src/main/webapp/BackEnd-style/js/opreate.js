/**
 * 导出为excel表格
 * @param fileName 文件名
 * @param tableId jsp中table的id
 * @param type 合并单元个的种类（数字）
 */
function exportExcel(fileName,tableId,type){
    if(confirm("是否确定导出报？")){
        var table = $("#"+tableId).tableToJSON();
        console.log(table);
        var json = JSON.stringify(table);
        var nodes = $("#"+tableId+" thead tr").children();
        var headers = "";
        $.each(nodes,function(i,item){
            headers += item.innerHTML+",";
        });
        //调用post方法
        post('/export.do', {fileName :fileName,headers:headers,json:json,type:type});
    }
    else return false;
}
function post(url, params) {
    var temp = document.createElement("form");
    temp.action = url;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in params) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}