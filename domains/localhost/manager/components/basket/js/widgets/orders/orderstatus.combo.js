// 
Shop.combo.OrderStatus = function(config) {
    config = config || {};
    Ext.applyIf(config,{
        url: Shop.config.connector_url + 'orderstatus.php'
        ,forceSelection: true
        ,fields: ['id','status']
        ,displayField: 'status',
    });
    Shop.combo.OrderStatus.superclass.constructor.call(this,config);
};
Ext.extend(Shop.combo.OrderStatus ,MODx.combo.ComboBox);
Ext.reg('shop-combo-orderstatus', Shop.combo.OrderStatus);