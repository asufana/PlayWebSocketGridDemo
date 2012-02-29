
Ext.server = function(){
    return {
        //初期化
        init : function(currentGridStore, backlogGridStore){
            Ext.Ajax.request({
                url: '/init',
                success: function (result, request) {
                    var obj = JSON.parse(result.responseText);
                    currentGridStore.loadData(obj.currentOrder);
                    backlogGridStore.loadData(obj.backlogOrder);
                },
            });
        },
        //更新検知
        watch : function(currentGridStore, backlogGridStore){
            var ws = new WebSocket("ws://" + location.host + "/ws");
            ws.onmessage = function(event) {
                var obj = JSON.parse(event.data);
                if(obj.type === "Order"){
                    currentGridStore.loadData(obj.value.currentOrder);
                    backlogGridStore.loadData(obj.value.backlogOrder);
                }
            };
        },
        //更新
        update : function(currentOrderIds, backlogOrderIds){
            Ext.Ajax.request({
                url: '/order',
                method: 'POST',
                params: {
                    currentOrderIds: currentOrderIds,
                    backlogOrderIds: backlogOrderIds,
                }
            });
        },
        
    };
}();

