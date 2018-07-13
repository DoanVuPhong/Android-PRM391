Ext.define('DemoCrud.view.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    requires: [
        'Ext.TitleBar',
        'Ext.Video'
    ],
    config: {
        tabBarPosition: 'bottom',
        items: [
            {
                title: 'Insert',
                iconCls: 'action',
                items: [
                    {
                        docked: 'top',
                        xtype: 'titlebar',
                        title: 'Create new Account'

                    },
                    {
                        xtype: 'textfield',
                        label: 'Username',
                        id: 'username'
                    },
                    {
                        xtype: 'passwordfield',
                        label: 'Password',
                        id: 'password'
                    },
                    {
                        xtype: 'textfield',
                        label: 'Full Name',
                        id: 'fullName'
                    },
                    {
                        xtype: 'textfield',
                        label: 'Role',
                        id: 'role'
                    },
                    {
                        xtype: 'button',
                        ui: 'Normal',
                        text: 'Insert',
                        handler: function () {
                            var username = Ext.getCmp("username").getValue();
                            var password = Ext.getCmp("password").getValue();
                            var fullname = Ext.getCmp("fullName").getValue();
                            var role = Ext.getCmp("role").getValue();
                            console.log("to handle")
                            Ext.Ajax.request(
                                    {
                                        url: './InsertServlet',
                                        params: {username: username, password: password, role: role, fullname: fullname},
                                        method: 'GET',
                                        success: function (response) {
                                            Ext.Msg.alert("Successfullys Inserted " + response.status);
                                        },
                                        failure: function (response) {
                                            Ext.Msg.alert("Error  " + response.status);
                                        }
                                    });
                        }
                    }


                ]
            },
            {
                title: 'Filter Account',
                xtype: 'nestedlist',
                iconCls: 'star',
                id: 'NestedListAllAccount',
                display: 'username',
                style: 'text-align:center',
                items: [
                    {
                        xtype: 'toolbar',
                        docked: 'top',
                        layout: 'vbox',
                        items: [
                            {xtype: 'searchfield',
                                placeHolder: 'Full name...',
                                itemId: 'searchBox',
                                listeners: {
                                    keyup: function (searchfield, e) {
                                        if (e.event.keyCode == 13) {
                                            var queryString = searchfield.getValue();
                                            var store = Ext.getStore("StartGetAllAccount");
                                            store.clearFilter();
                                            var dataFilter = "";
                                            if (queryString) {
                                                var thisRegEx = new RegExp(queryString, "i");
                                                store.filterBy(function (record) {

                                                    if (thisRegEx.test(record.get('fullname'))) {
                                                        dataFilter += '{"role":"' + record.get("role") + '","fullname":"' + record.get("fullname") + '","username":"' + record.get("username") + '"},';
                                                        return true;

                                                    }
                                                    ;
                                                    return false;

                                                });
                                                var data = dataFilter.substring(0, dataFilter.length - 1);
                                                data = "[" + data + "]";
                                                console.log(data);
                                            }
                                            var filterResultStore = Ext.create('Ext.data.TreeStore', {
                                                data: data,
                                                fields: [
                                                    {
                                                        name: 'username',
                                                        type: 'string'
                                                    },
                                                    {
                                                        name: 'role',
                                                        type: 'string'
                                                    },
                                                    {
                                                        name: 'fullname',
                                                        type: 'string'
                                                    }



                                                ]

                                            });
                                            Ext.getCmp("NestedListAllAccount").setStore(filterResultStore);
                                            Ext.getCmp("NestedListAllAccount").show();


                                        }// end 13 key code

                                    }

                                }
                            }
                        ]
                    }
                ],
                store: {
                    type: 'tree',
                    id: 'StartGetAllAccount',
                    fields: [
                        {
                            name: "username",
                            type: 'string'
                        },
                        {
                            name: "password",
                            type: 'string'
                        },
                        {
                            name: "role",
                            type: 'string'
                        },
                        {
                            name: "fullname",
                            type: 'string'
                        }
                    ],
                    proxy: {
                        type: 'ajax',
                        url: 'GetAllAccountServlet'
                    }
                },
                listConfig: {
                    itemTpl: '<div class="myContent">' +
                            '<div>Username is <b>{username}</b></div>' +
                            '<div>Fullname is <b>{fullname}</b></div>' +
                            '<div>Role is <b>{role}</b></div>' + '</div>',
                    emptyText: '<div class="myContent">No Record match</div>'



                },
                detailCard: {
                    xtype: 'panel',
                    scrollable: true,
                    styleHtmlContent: true,
                    layout: 'vbox'
                },
                listeners: {
                    leafitemtap: function (nestedList, list, index, target, record) {
                        console.log("go inside clicked tab");
                        var detailCard = nestedList.getDetailCard();
                        detailCard.setHtml('<div>You select: <b>' + record.get('username') + '</b></div>');
                        Ext.Msg.alert('You clicked on the row...', 'The username selected is ' + record.get('username'));
                        var tabPanel = Ext.create("Ext.Panel", {
                            xtype: 'panel',
                            layout: 'vbox',
                            items: [
                                {xtype: 'textfield',
                                    label: 'User Name',
                                    id: 'Username',
                                    value: record.get('username')
                                },
                                {xtype: 'passwordfield',
                                    label: 'Password',
                                    id: 'password',
                                },
                                {xtype: 'textfield',
                                    label: 'Full Name',
                                    id: 'fullname',
                                    value: record.get('fullname')
                                },
                                {xtype: 'textfield',
                                    label: 'Role',
                                    id: 'role',
                                    value: record.get('role')
                                },
                                {xtype: 'button',
                                    ui: 'normal',
                                    text: 'Update',
                                    handler: function () {
                                        var username = record.get('username');
                                        var password = Ext.getCmp('password').getValue();
                                        var fullname = Ext.getCmp('fullname').getValue();
                                        var role = Ext.getCmp('role').getValue();
                                        Ext.Ajax.request(
                                                {
                                                    url: './UpdateServlet',
                                                    params: {username: username, password: password, role: role, fullname: fullname},
                                                    method: 'GET',
                                                    success: function (response) {
                                                        Ext.Msg.alert("Successfullys Updateed " + response.status);
                                                        window.location.reload();
                                                    },
                                                    failure: function (response) {
                                                        Ext.Msg.alert("Error  " + response.status);
                                                    }
                                                });

                                    }

                                },
                                {xtype: 'button',
                                    ui: 'normal',
                                    text: 'Delete',
                                    handler: function () {
                                        var username = record.get('username');
                                        var password = Ext.getCmp('password').getValue();
                                        var fullname = Ext.getCmp('fullname').getValue();
                                        var role = Ext.getCmp('role').getValue();
                                        Ext.Ajax.request(
                                                {
                                                    url: './DeleteServlet',
                                                    params: {username: username, password: password, role: role, fullname: fullname},
                                                    method: 'GET',
                                                    success: function (response) {
                                                        Ext.Msg.alert("Successfullys Updateed " + response.status);
                                                        window.location.reload();
                                                    },
                                                    failure: function (response) {
                                                        Ext.Msg.alert("Error  " + response.status);
                                                    }
                                                });

                                    }

                                }



                            ]




                        });
                        detailCard.add(tabPanel);
                    },

                }
            },
            {
                title: 'Get Started',
                iconCls: 'action',
                items: [
                    {
                        docked: 'top',
                        xtype: 'titlebar',
                        title: 'Getting Started'
                    },
                    {
                        xtype: 'video',
                        url: 'http://av.vimeo.com/64284/137/87347327.mp4?token=1330978144_f9b698fea38cd408d52a2393240c896c',
                        posterUrl: 'http://b.vimeocdn.com/ts/261/062/261062119_640.jpg'
                    }
                ]
            }
        ]
    }
});
