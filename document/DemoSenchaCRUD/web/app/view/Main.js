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
                                {   xtype: 'searchfield',
                                        placeHolder: 'Full name...',
                                        itemId: 'searchBox',
                                        listeners:{
                                        keyup:function(searchfield, e){
                                        if (e.event.keyCode == 13)
                                          var queryString = searchfield.getValue();
                                          var store= Ext.getStore("StartGetAllAccount");
                                          store.clearFilter();
                                          var dataFilter="";
                                          if(queryString){
                                              var thisRegEx= new  RegExp(queryString,"i");
                                              store.filterBy(function (record){

                                                  if(thisRegEx.test(record.get('fullname'))){
                                                        dataFilter+='{"role":"'+record.get("role")+'","role":"'+record.get("fullname")+'"}';
                                                        return true;

                                                  }
                                                  ;
                                                  return false;

                                              });
                                              var data= dataFilter.substring(0, dataFilter.lenght-1);
                                              data="{"+data+"}";  

                                          }

                                        }

                                        }
                                }
                                ]
                        }
                        ],
                        store:{
                           type:'tree',
                           id:'StartGetAllAccount',
                           fields:[
                               {
                                name:"username",
                                type:'string'
                               },
                               {
                                name:"password",
                                type:'string'
                               },
                               {
                                name:"role",
                                type:'string'
                               },
                               {
                                name:"fullname",
                                type:'string'
                               }
                           ],
                           proxy:{
                               type:'ajax',
                               url:'GetAllAccountServlet'
                           }
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
