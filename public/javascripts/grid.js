
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.dd.*'
]);

Ext.define('DataObject', {
    extend: 'Ext.data.Model',
    fields: ['ID', 'STORY']
});

Ext.gridpanel = function(){
    var currentGridStore = Ext.create('Ext.data.Store', {
        model: 'DataObject',
    });
    var backlogGridStore = Ext.create('Ext.data.Store', {
        model: 'DataObject'
    });

    var columns = [
        {text: "ID", flex: 1, sortable: true, dataIndex: 'id'},
        {text: "STORY", width: 300, sortable: true, dataIndex: 'title'},
    ];

    var currentGrid = Ext.create('Ext.grid.Panel', {
        multiSelect: true,
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'currentGridDDGroup',
                dropGroup: 'backlogGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var i;
                    currentIds = new Array(currentGridStore.data.items.length);
                    for(i=0; i<currentGridStore.data.items.length; i++){
                        currentIds[i] = currentGridStore.data.items[i].get("id");
                    }
                    backlogIds = new Array(backlogGridStore.data.items.length);
                    for(i=0; i<backlogGridStore.data.items.length; i++){
                        backlogIds[i] = backlogGridStore.data.items[i].get("id");
                    }
                    Ext.server.update(currentIds, backlogIds);
                }
            }
        },
        store            : currentGridStore,
        columns          : columns,
        stripeRows       : true,
        title            : 'Current Stories',
        margins          : '0 2 0 0'
    });

    var backlogGrid = Ext.create('Ext.grid.Panel', {
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'backlogGridDDGroup',
                dropGroup: 'currentGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var i;
                    currentIds = new Array(currentGridStore.data.items.length);
                    for(i=0; i<currentGridStore.data.items.length; i++){
                        currentIds[i] = currentGridStore.data.items[i].get("id");
                    }
                    backlogIds = new Array(backlogGridStore.data.items.length);
                    for(i=0; i<backlogGridStore.data.items.length; i++){
                        backlogIds[i] = backlogGridStore.data.items[i].get("id");
                    }
                    Ext.server.update(currentIds, backlogIds);
                }
            }
        },
        store            : backlogGridStore,
        columns          : columns,
        stripeRows       : true,
        title            : 'Backlog Storeis',
        margins          : '0 0 0 3'
    });

    var displayPanel = Ext.create('Ext.Panel', {
        width        : 650,
        height       : 200,
        layout       : {
            type: 'hbox',
            align: 'stretch',
            padding: 5
        },
        renderTo     : 'panel',
        defaults     : { flex : 1 }, //auto stretch
        items        : [
            currentGrid,
            backlogGrid
        ],
//        dockedItems: {
//            xtype: 'toolbar',
//            dock: 'bottom',
//            items: ['->', // Fill
//            {
//                //text: 'Reset both grids',
//                handler: function(){
//                    //refresh source grid
//                    currentGridStore.loadData(myData);
//
//                    //purge destination grid
//                    backlogGridStore.removeAll();
//                }
//            }]
//        }
    });
    
    //初期データ登録
    Ext.server.init(currentGridStore, backlogGridStore);
    
    //更新監視
    Ext.server.watch(currentGridStore, backlogGridStore);
};

Ext.onReady(function(){
    Ext.gridpanel();
});

