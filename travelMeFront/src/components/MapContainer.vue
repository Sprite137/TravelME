
<template>
    <div id="container"></div>
    <!-- <div>
      <div>出发站：南京</div>
      <div>终点站：北京</div>
      <div>出行时间：2024-12-17</div>
    </div> -->
</template>


<script setup>
  import { onMounted, onUnmounted } from "vue";
  import AMapLoader from "@amap/amap-jsapi-loader";
  import GDKey from "../assets/json/GDKey.json"
  import {test} from "../api/travelLog"
  import {TravelLog} from "../base"

  let map = null;
  var infoWindow;

  
  
  onMounted(() => {
    window._AMapSecurityConfig = {
      securityJsCode: GDKey.securityJsCode,
    };
    AMapLoader.load({
      key: GDKey.key, // 申请好的Web端开发者Key，首次调用 load 时必填
      version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
      plugins: ['AMap.Scale', 'AMap.ToolBar', 'AMapBezierCurve','AMap.DistrictLayer'], //需要使用的的插件列表，如比例尺'AMap.Scale'，支持添加多个如：['...','...']
    })
      .then((AMap) => {

        var colors = {};
      var GDPSpeed = {
          '520000':10,//贵州
          '540000':10,//西藏
          '530000':8.5,//云南 
          '500000':8.5,//重庆
          '360000':8.5,//江西
          '340000':8.0,//安徽
          '510000':7.5,//四川
          '350000':8.5,//福建
          '430000':8.0,//湖南
          '420000':7.5, //湖北
          '410000':7.5,//河南
          '330000':7.0,//浙江
          '640000':7.5,//宁夏
          '650000':7.0,//新疆
          '440000':7.0,//广东
          '370000':7.0,//山东
          '450000':7.3,//广西
          '630000':7.0,//青海
          '320000':7.0,//江苏
          '140000':6.5,//山西
          '460000':7,// 海南
          '310000':6.5,//上海
          '110000':6.5, // 北京
          '130000':6.5, // 河北
          '230000':6, // 黑龙江
          '220000':6,// 吉林
          '210000':6.5, //辽宁
          '150000':6.5,//内蒙古
          '120000':5,// 天津
          '620000':6,// 甘肃
          '610000':8.5,// 甘肃
          '710000':6.64, //台湾
          '810000':6.0, //香港
          '820000':6.7 //澳门

      }
      var getColorByDGP = function(adcode){
          if(!colors[adcode]){
              var gdp = GDPSpeed[adcode];
              if(!gdp){
                  colors[adcode] = 'rgb(227,227,227)'
              }else{   
                  var rg = 255-Math.floor((gdp-5)/5*255);
                  colors[adcode] = 'rgb('+ 255 +','+ 228 +',255)';
              }
          }
          return colors[adcode]
      }
        var disCountry = new AMap.DistrictLayer.Country({
          zIndex:10,
          SOC:'CHN',
          depth:2,
          styles:{
              'nation-stroke':'#22ffff',
              'coastline-stroke':[0.8, 0.63, 0.94, 1],
              'province-stroke':'white',
              'city-stroke': 'rgba(255,255,255,0.5)',//中国特有字段
              'fill':function(props){//中国特有字段
                return getColorByDGP(props.adcode_pro)
              }
          }
        })

        map = new AMap.Map("container", {
          viewMode: "3D", // 是否为3D地图模式
          zoom: 5, // 初始化地图级别
          layers: [disCountry,new AMap.TileLayer.RoadNet(),new AMap.TileLayer()],
          center: [113.397428, 33.90923], // 初始化地图中心点位置
        });

        


        
        var getViaPoint = function(a,b,c,d){
          return [(a+c)/2-(d-b)/3,(b+d)/2+(c-a)/3]
        }

        function openInfo(e) {
          //构建信息窗体中显示的内容
          var info = [];
          // info.push("<div class='input-card content-window-card'><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
          // info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>高德软件</h4>"+Object.values(travelLog));
          // info.push("<p class='input-item'></p>");
          // info.push("<p class='input-item'>地址 :北京市朝阳区望京阜荣街10号首开广场4层</p></div></div>");
          info.push("<div> 出发站： "+ e.target.getExtData()["startStation"]+" </div>")
          info.push("<div> 终点站：" + e.target.getExtData()["endStation"]+" </div>")
          info.push("<div> 出行时间： "+ e.target.getExtData()["travelTime"]+" </div>")

          infoWindow = new AMap.InfoWindow({
              content: info.join("")  //使用默认信息窗体框样式，显示信息内容
          });

          infoWindow.open(map, [e.lnglat.getLng(),e.lnglat.getLat()]);
        }

       

      // var points = [
          // [116.379064, 39.864462, 118.798225,  32.088036],
          // [114.028356, 22.607177,  104.068544, 30.605364]
      // ]  

      var points = new Array();
      var isAirPlanes = new Array();
      var travelLogs = new Array();
      {
            
          }
      async function testAxios() {
        try{
            const response = await test();
            for (const key in response.data){
              var point = new Array();
              point[0] = Number(response.data[key].startStationLng);
              point[1] =  Number(response.data[key].startStationLat);
              point[2] =  Number(response.data[key].endStationLng);
              point[3] =  Number(response.data[key].endStationLat);
              points[key] = point;
              var travelLog = {
                  "startStation":response.data[key].startStation,
                  "endStation":response.data[key].endStation,
                  "travelTime":response.data[key].travelTime.slice(0, 10),
              }
              travelLogs[key] = travelLog;
              isAirPlanes[key] = response.data[key].isAirPlane;
            }
          }
          catch(error){
              console.error('Failed to fetch data:', error);
          }
        };

      function showTravelLog(e){
        var x = e.target.getExtData();
        // console.log()
        // alert("hello!"+Object.values(x))
        openInfo(e)
      }

      function hideTravelLog(e){
        var x = e.target.getExtData();
        // console.log(x.key["startStation"])
        // alert("hello!"+Object.values(x))
        infoWindow.close()
      }

      testAxios();

      setTimeout(() => {
        
        for(var i = 0; i<points.length; i++){
          var startPoint = [[points[i][0], points[i][1]]]; //起点
          var endPoint = [ //一次贝塞尔曲线
            getViaPoint(points[i][0], points[i][1],points[i][2], points[i][3]), //控制点
            [points[i][2], points[i][3]] //途径点
          ];


          //曲线路径配置
          var path = [
            startPoint,
            endPoint,
          ];

          var bezierCurve = new AMap.BezierCurve({
            path: path, //曲线路径
            strokeWeight: 2, //线条宽度
            strokeColor: "#87CEFA", //线条颜色
            isOutline: true, //是否描边
            outlineColor: isAirPlanes[i]===1?"blue":"green", //描边颜色
            borderWeight: 2, //描边宽度
            showDir:true,
            cursor:"help"
          });

          bezierCurve.on("mouseover",showTravelLog)
          bezierCurve.on("mouseout",hideTravelLog)
          
          bezierCurve.setExtData(travelLogs[i])

          map.add(bezierCurve);
          map.add(new AMap.Circle({
            center: new AMap.LngLat(points[i][0], points[i][1]),  // 圆心位置
            radius: 15000, // 圆半径
            fillColor: 'blue',   // 圆形填充颜色
            strokeColor: '#fff', // 描边颜色
            strokeWeight: 2, // 描边宽度
            })
          );

          map.add(new AMap.Circle({
            center: new AMap.LngLat(points[i][2], points[i][3]),  // 圆心位置
            radius: 15000, // 圆半径
            fillColor: 'red',   // 圆形填充颜色
            strokeColor: '#fff', // 描边颜色
            strokeWeight: 2, // 描边宽度
            })
          );
        }
      }, 500);  //1秒后执行代码
      
       

        

      // var travelLog = [
      //     ["北京南","南京"],
      //     ["深圳北","成都"]
      // ]

      




      })
      .catch((e) => {
        console.log(e);
      });
  });

  

  onUnmounted(() => {
    map?.destroy();
  });


  
</script>

<style scoped>
  #container {
    position: absolute;
    margin: auto;
    right: 0px;
    top: 0px;
    width: 100%;
    height: 100%;
    z-index: 10;
    
  }

</style>
