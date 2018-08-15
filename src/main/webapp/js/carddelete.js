(function setData() {
        $.ajax({
            url: "http://demo.steam-steam.cn/card/getCardList.do",
            data: {},
            dataType: 'json',
            type: 'post',
            success: function (resp) {
                $("#dg").datagrid("loadData", {rows: resp.data});
                $("#dg").datagrid("clearSelections");
            }
        })
    }
)()

function getSelected() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        if(confirm("是否确认删除？")){
            $.ajax({
                url: "http://demo.steam-steam.cn/card/updateCard.do",
                data: {"cardid": row.cardid,
                       "cardstate" : 2
                },
                dataType: 'json',
                type: 'post',
                success: function (resp) {
                    if (resp.retCode === 10000) {
                        $.ajax({
                            url: "http://demo.steam-steam.cn/card/getCardList.do",
                            data: {},
                            dataType: 'json',
                            type: 'post',
                            success: function (data) {
                                $("#dg").datagrid("loadData", {rows: data.data});
                                $("#dg").datagrid("clearSelections");
                            }
                        })
                        alert("操作成功！");
                    } else {
                        alert("操作失败！");
                    }
                }
            })
        }
    }
}