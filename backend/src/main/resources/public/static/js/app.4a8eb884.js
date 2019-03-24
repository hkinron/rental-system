(function(e){function t(t){for(var r,a,c=t[0],i=t[1],l=t[2],m=0,p=[];m<c.length;m++)a=c[m],n[a]&&p.push(n[a][0]),n[a]=0;for(r in i)Object.prototype.hasOwnProperty.call(i,r)&&(e[r]=i[r]);u&&u(t);while(p.length)p.shift()();return s.push.apply(s,l||[]),o()}function o(){for(var e,t=0;t<s.length;t++){for(var o=s[t],r=!0,c=1;c<o.length;c++){var i=o[c];0!==n[i]&&(r=!1)}r&&(s.splice(t--,1),e=a(a.s=o[0]))}return e}var r={},n={app:0},s=[];function a(t){if(r[t])return r[t].exports;var o=r[t]={i:t,l:!1,exports:{}};return e[t].call(o.exports,o,o.exports,a),o.l=!0,o.exports}a.m=e,a.c=r,a.d=function(e,t,o){a.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:o})},a.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,t){if(1&t&&(e=a(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var o=Object.create(null);if(a.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)a.d(o,r,function(t){return e[t]}.bind(null,r));return o},a.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return a.d(t,"a",t),t},a.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},a.p="/";var c=window["webpackJsonp"]=window["webpackJsonp"]||[],i=c.push.bind(c);c.push=t,c=c.slice();for(var l=0;l<c.length;l++)t(c[l]);var u=i;s.push([0,"chunk-vendors"]),o()})({0:function(e,t,o){e.exports=o("56d7")},"034f":function(e,t,o){"use strict";var r=o("64a9"),n=o.n(r);n.a},"0442":function(e,t,o){"use strict";var r=o("b8f8"),n=o.n(r);n.a},"11eb":function(e,t,o){},1588:function(e,t,o){},"205b":function(e,t,o){"use strict";var r=o("2064"),n=o.n(r);n.a},2064:function(e,t,o){},3487:function(e,t,o){"use strict";var r=o("1588"),n=o.n(r);n.a},"435f":function(e,t,o){"use strict";var r=o("11eb"),n=o.n(r);n.a},"56d7":function(e,t,o){"use strict";o.r(t);o("cadf"),o("551c"),o("097d");var r=o("2b0e"),n=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"app"}},[o("div",{attrs:{id:"nav"}},[o("router-link",{attrs:{to:"/room"}},[e._v("Room")]),e._v(" |\n    "),o("router-link",{attrs:{to:"/user"}},[e._v("User")]),e._v(" |\n    "),o("router-link",{attrs:{to:"/record"}},[e._v("Record")]),e._v(" |\n    "),o("router-link",{attrs:{to:"/bill"}},[e._v("Bill")])],1),o("router-view",{attrs:{hellomsg:e.msg}})],1)},s=[],a={name:"app",data:function(){return{msg:"Welcome to your Vue.js powered Spring Boot App"}}},c=a,i=(o("034f"),o("2877")),l=Object(i["a"])(c,n,s,!1,null,null,null);l.options.__file="App.vue";var u=l.exports,m=o("8c4f"),p=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"hello"},[r("img",{attrs:{src:o("ad5f")}}),r("h1",[e._v(e._s(e.hellomsg))]),r("h2",[e._v("See the sources here: ")]),e._m(0),r("h3",[e._v("This site contains more stuff :)")]),r("ul",[r("li",[e._v("HowTo to interact with user backend:")]),r("li",[r("router-link",{attrs:{to:{name:"User"},exact:"",target:"_blank"}},[e._v("/user")])],1),r("li",[e._v("HowTo to interact with room backend:")]),r("li",[r("router-link",{attrs:{to:{name:"Room"},exact:"",target:"_blank"}},[e._v("/room")])],1)])])},f=[function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("ul",[o("li",[o("a",{attrs:{href:"https://github.com/jonashackt/spring-boot-vuejs",target:"_blank"}},[e._v("github.com/jonashackt/spring-boot-vuejs")])])])}],d={name:"hello",props:{hellomsg:{type:String,required:!0}}},b=d,h=(o("205b"),Object(i["a"])(b,p,f,!1,null,"7d1e3453",null));h.options.__file="Hello.vue";h.exports;var v=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("b-container",{staticClass:"user"},[o("b-form-row",[o("b-col",[o("h1",[e._v("Create User")])])],1),o("b-form-row",[o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"name"},model:{value:e.user.name,callback:function(t){e.$set(e.user,"name",t)},expression:"user.name"}})],1),o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"phone"},model:{value:e.user.phone,callback:function(t){e.$set(e.user,"phone",t)},expression:"user.phone"}})],1),o("b-col",[o("b-form-select",{model:{value:e.user.room,callback:function(t){e.$set(e.user,"room",t)},expression:"user.room"}},[o("template",{slot:"first"},[o("option",{attrs:{disabled:""},domProps:{value:null}},[e._v("-- Please select an option --")])]),e._v("\n        These options will appear after the ones from 'options' prop\n        "),e._l(this.rooms,function(t){return o("option",{key:t.id,domProps:{value:t}},[e._v("\n          "+e._s(t.name)+"\n        ")])})],2)],1),o("b-col",{attrs:{md:"auto"}},[o("b-button",{attrs:{variant:"success"},on:{click:function(t){e.createUser()}}},[e._v("Create User")])],1)],1),o("b-form-row",e._l(e.response,function(t){return o("span",[e._v("id："+e._s(t))])}),0),o("b-form-row",[o("b-table",{attrs:{hover:"",items:e.users}})],1)],1)},_=[],w=o("7338"),g=o.n(w),R=g.a.create({baseURL:"/api"}),y=o("2f62"),k={name:"user",data:function(){return{response:[],errors:[],user:{name:"",phone:"",roomId:""}}},methods:{createUser:function(){var e=this;R.post("/user",this.user).then(function(t){e.response.push(t.data),e.user.id=t.data,e.$store.dispatch("getUsers"),console.log(t.data)}).catch(function(t){e.errors.push(t)})}},computed:Object(y["b"])({rooms:function(e){return e.rooms},users:function(e){return e.users}}),mounted:function(){this.$store.dispatch("getUsers")}},x=k,j=(o("435f"),Object(i["a"])(x,v,_,!1,null,"a0e0e924",null));j.options.__file="User.vue";var U=j.exports,$=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("b-container",{staticClass:"room"},[o("b-form-row",[o("b-col",[o("h1",[e._v("Create Room")])])],1),o("b-form-row",[o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"name"},model:{value:e.room.name,callback:function(t){e.$set(e.room,"name",t)},expression:"room.name"}})],1),o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"price"},model:{value:e.room.price,callback:function(t){e.$set(e.room,"price",t)},expression:"room.price"}})],1),o("b-col",[o("b-button",{attrs:{variant:"success"},on:{click:function(t){e.createRoom()}}},[e._v("Create Room")])],1)],1),o("b-form-row",e._l(e.response,function(t){return o("span",[e._v("id："+e._s(t))])}),0),o("b-form-row",{directives:[{name:"show",rawName:"v-show",value:e.rooms.length>0,expression:"rooms.length > 0 "}]},[o("b-table",{attrs:{hover:"",items:e.rooms,fields:e.fields}})],1)],1)},O=[],C={name:"room",data:function(){return{response:[],errors:[],room:{name:"",price:""},fields:{id:{sortable:!0},name:{sortable:!0},price:{sortable:!0},user:{key:"user.name",label:"Username",sortable:!0}}}},methods:{createRoom:function(){var e=this;R.post("/room",this.room).then(function(t){e.response.push(t.data),e.$store.dispatch("getRooms"),console.log(t.data)}).catch(function(t){e.errors.push(t)})}},computed:Object(y["b"])({rooms:function(e){return e.rooms}}),beforeMount:function(){this.$store.dispatch("getRooms")}},D=C,P=(o("3487"),Object(i["a"])(D,$,O,!1,null,"3b5e7c1e",null));P.options.__file="Room.vue";var S=P.exports,M=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("b-container",{staticClass:"record"},[o("b-form-row",[o("b-col",[o("h1",[e._v("Create Record")])]),o("b-col",[o("b-form-input",{attrs:{type:"date"},on:{change:function(t){e.updateRecordsDate()}},model:{value:e.recordDate,callback:function(t){e.recordDate=t},expression:"recordDate"}})],1)],1),e._l(e.records,function(t){return o("b-form-row",{key:t.room.id},[o("b-col",[o("b-form-input",{attrs:{"aria-disabled":"true"},model:{value:t.room.name,callback:function(o){e.$set(t.room,"name",o)},expression:"record.room.name"}})],1),o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"water"},model:{value:t.water,callback:function(o){e.$set(t,"water",o)},expression:"record.water"}})],1),o("b-col",[o("b-form-input",{attrs:{type:"text",placeholder:"electric"},model:{value:t.electric,callback:function(o){e.$set(t,"electric",o)},expression:"record.electric"}})],1)],1)}),o("b-form-row",[o("b-col",{attrs:{md:"auto"}},[o("b-button",{attrs:{variant:"success"},on:{click:function(t){e.createRecord()}}},[e._v("Create")])],1)],1),o("b-form-row",e._l(e.response,function(t){return o("span",[e._v(e._s(t)+" 插入成功")])}),0)],2)},B=[],E=(o("28a5"),{data:function(){return{response:[],errors:[],recordDate:"",records:[]}},methods:{createRecord:function(){var e=this;R.post("/record",this.records).then(function(t){e.response.push(t.data)}).catch(function(t){e.errors.push(t)})},initRecords:function(){for(var e=0;e<this.rooms.length;e++){var t={room:this.rooms[e],water:"",electric:"",yearMonth:""};this.records.push(t)}},updateRecordsDate:function(){for(var e=0;e<this.records.length;e++)this.records[e].yearMonth=this.recordDate.split("-")[0]+"-"+this.recordDate.split("-")[1]}},mounted:function(){this.initRecords()},computed:Object(y["b"])({rooms:function(e){return e.rooms}})}),T=E,F=(o("6120"),Object(i["a"])(T,M,B,!1,null,"4cf69cfa",null));F.options.__file="Record.vue";var H=F.exports,A=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("b-container",{staticClass:"record"},[r("b-form-row",[r("b-col",[r("h1",[e._v("Create Bill")])]),r("b-col",[r("b-form-input",{attrs:{type:"date"},on:{change:function(t){e.getBills()}},model:{value:e.billDate,callback:function(t){e.billDate=t},expression:"billDate"}})],1)],1),r("b-form-row",{directives:[{name:"show",rawName:"v-show",value:e.bills.length>0,expression:"bills.length > 0"}]},[r("table",{staticClass:"table table-hover"},[r("thead",[r("tr",[r("th",[e._v("Room")]),r("th",[e._v("Description")]),r("th",[e._v("Pay")])])]),r("tbody",e._l(e.bills,function(t){return r("tr",{key:t.nowRecord.id},[r("td",{staticClass:"cell"},[e._v(e._s(t.nowRecord.room.name)),r("br"),e._v("\n                -------------------------"),r("br"),e._v("\n                "+e._s(t.nowRecord.room.user.name)),r("br"),e._v("\n                -------------------------"),r("br"),e._v("\n                "+e._s(t.nowRecord.room.user.phone)+"\n            ")]),r("td",{staticClass:"cell",attrs:{id:"description"}},[e._v("\n                本次收取"+e._s(e.billDate.split("-")[1])+"月份租金及上月份水电"),r("br"),e._v("\n                电：上月示数"+e._s(t.lastRecord.electric)+"度，本月"+e._s(t.nowRecord.electric)+"度，电费："+e._s((t.nowRecord.electric-t.lastRecord.electric)*e.electricFee)+"元"),r("br"),e._v("\n                水：上月示数"+e._s(t.lastRecord.water)+"吨，本月"+e._s(t.nowRecord.water)+"吨，水费："+e._s((t.nowRecord.water-t.lastRecord.water)*e.waterFee)+"元"),r("br"),e._v("\n                房租："+e._s(t.nowRecord.room.price)+"元"),r("br"),e._v("\n                总费用："+e._s(t.sum)+"元\n            ")]),r("td",{staticClass:"cell"},[r("img",{staticClass:"alipay",attrs:{src:o("cdc7")}})])])}),0)])])],1)},J=[],L={data:function(){return{response:[],errors:[],billDate:"",bills:[],waterFee:4,electricFee:1.3,fields:{nowRecord:{key:"nowRecord.room.name",label:"Room",sortable:!0},lastRecord:{key:"lastRecord.room.user.name",label:"User",sortable:!0},sum:{sortable:!0}}}},methods:{getBills:function(){var e=this,t=new URLSearchParams;t.append("yearMonth",this.billDate.split("-")[0]+"-"+this.billDate.split("-")[1]),R.get("/bills",{params:t}).then(function(t){e.response.push(t.data),e.bills=t.data}).catch(function(t){e.errors.push(t)})}},computed:Object(y["b"])({rooms:function(e){return e.rooms}})},N=L,q=(o("0442"),Object(i["a"])(N,A,J,!1,null,"15687cdc",null));q.options.__file="Bill.vue";var I=q.exports;r["a"].use(m["a"]);var V=new m["a"]({routes:[{path:"/",redirect:"room"},{path:"/user",name:"User",component:U,props:!0},{path:"/room",name:"Room",component:S},{path:"/record",name:"Record",component:H},{path:"/bill",name:"Bill",component:I}]});r["a"].use(y["a"]);var W=new y["a"].Store({state:{rooms:[],users:[],records:[],count:1},mutations:{replaceRooms:function(e,t){e.rooms=t},getRooms:function(e,t){e.rooms=t},getUsers:function(e,t){e.users=t},addRoom:function(e,t){e.rooms.push(t)},addUser:function(e,t){e.users.push(t)},increment:function(e){e.count++}},actions:{increment:function(e){e.commit("increment")},addRoom:function(e,t){e.commit("addRoom",t)},addUser:function(e,t){e.commit("addUser",t)},getRooms:function(e){R.get("/rooms").then(function(t){e.commit("getRooms",t.data)}).catch(function(e){console.log(e)})},getUsers:function(e){R.get("/users").then(function(t){e.commit("getUsers",t.data)}).catch(function(e){console.log(e)})}}}),z=o("9f7b"),G=o.n(z);o("f9e3"),o("2dd8");r["a"].config.productionTip=!1,r["a"].use(G.a),new r["a"]({router:V,store:W,render:function(e){return e(u)}}).$mount("#app")},6120:function(e,t,o){"use strict";var r=o("eaf0"),n=o.n(r);n.a},"64a9":function(e,t,o){},ad5f:function(e,t,o){e.exports=o.p+"static/img/spring-boot-vuejs-logo.00da5c74.png"},b8f8:function(e,t,o){},cdc7:function(e,t,o){e.exports=o.p+"static/img/alipay.b76582a9.jpg"},eaf0:function(e,t,o){}});
//# sourceMappingURL=app.4a8eb884.js.map