<template>
    <div>
        <!-- <h1>hello</h1> -->
        <!-- <label>出发站：</label> -->
        <!-- <el-button :plain="true" @click="open2">Success</el-button> -->
        
        <div>
            <span class="demonstration">出行方式：</span>
            <el-autocomplete
                v-model="travelMethod"
                :fetch-suggestions="querySearch"
                clearable
                class="inline-input w-50"
                placeholder="Please Input"
                @select="selectTravelMethod"
            />
        </div>
        <br/>
        <div class="searchStartStation">
            <span class="demonstration">出发站：</span>
            <el-autocomplete 
                v-model="startStation"
                :fetch-suggestions="querySearchAsync"
                placeholder="Please input start station"
                @select="selectStartStation"
            />
        </div>


        <br/>
        <!-- <label>终点站：</label> -->
        <div class="searchEndStation">
            <span class="demonstration">终点站：</span>
            <el-autocomplete
                v-model="endStation"
                :fetch-suggestions="querySearchAsync"
                placeholder="Please input end station"
                @select="selectEndStation"
            />
        </div>

        <br/>
        <div class="block">
            <span class="demonstration">出行时间：</span>
            <el-date-picker
                v-model=travelTime
                type="datetime"
                placeholder="Select date and time"
            />
        </div>

        
        <br/>
        <el-button type="success" @click="submit"class="submitButton" :disabled=disabledValue :loading=disabledValue round>提交出行记录</el-button>
        <!-- <button class="submitButton" @click="submit">提交出行记录</button> -->
    </div>
</template>

<script setup lang="ts">
    import {onMounted, ref} from 'vue'
    import { addTravelLog } from '../api/travelLog';
    import { ElMessage } from 'element-plus'
    import { Sleep } from '../utils/util';
    import { Loading } from 'element-plus/es/components/loading/src/service.mjs';
    import { findRecommendStationsByName } from '../api/stationInfo';
    

    const startStation = ref('');
    const endStation = ref('');
    const travelTime = ref('')
    const disabledValue = ref(false);
    const travelMethod = ref()
    
    interface LinkItem {
        value: string
        gcity: string
    }

    const links = ref<LinkItem[]>([])

    const restaurants = ref<LinkItem[]>([])

    const loadAll = () => {
        return [
            { value: '北京', gcity: 'https://github.com/vuejs/vue' },
            { value: '南京', gcity: 'https://github.com/ElemeFE/element' },
            { value: '天津', gcity: 'https://github.com/ElemeFE/cooking' },
        ]
    }

    const loadTravelMEthod = () => {
        return [
            { value: '铁路出行', gcity: 'https://github.com/vuejs/vue' },
            { value: '飞机出行', gcity: 'https://github.com/ElemeFE/element' },
        ]
    }

    const querySearch = (queryString: string, cb: any) => {
        const results = queryString
            ? restaurants.value.filter(createFilter(queryString))
            : restaurants.value
        // call callback function to return suggestions
        cb(results)
    }

    let timeout: ReturnType<typeof setTimeout>
    async function querySearchAsync(queryString:string, cb: (arg: any) => void) {
        const response = await findRecommendStationsByName({"stationName":queryString,"isAirStation":travelMethod.value=='铁路出行'?0:1});

        links.value = response.data;

        const results = queryString
            ? links.value.filter(createFilter(queryString))
            : links.value

        clearTimeout(timeout)
            timeout = setTimeout(() => {
                cb(results)
            }, 200 * Math.random())
    }

    const createFilter = (queryString: string) => {
        return (restaurant: LinkItem) => {
            return (
                restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
            )
        }
    }

    const selectStartStation = (item: Record<string, any>) => {
        startStation.value =item.value
    }

    const selectEndStation = (item: Record<string, any>) => {
        endStation.value =item.value;
        
    }

    

    async function submit(){
        var travelLog = {
            "startStation":startStation.value,
            "endStation":endStation.value,
            "travelTime":travelTime.value,
            "isAirPlane":0,
            "userId":1
        }

        if(isEmptyStr(startStation.value) || isEmptyStr(endStation.value) || isEmptyStr(travelTime.value)){
            ElMessage({
                message: 'Warning! Exists empty input!',
                type: 'warning',
                plain: true,
            })
            return
        }
        

        disabledValue.value = true

        await submitTravelLog(travelLog)


        disabledValue.value = false

        ElMessage({
            showClose: true,
            message: 'Success! Add travel log success!',
            type: 'success',
        })

        
        
        
        
    }

    async function submitTravelLog(travelLog) {
        try{
            const response = await addTravelLog(travelLog);
            
            console.log(response)
        }
        catch(error){
            console.error('Failed to submit TravelLog:', error);
        }
    }
    
    function selectTravelMethod(item: LinkItem){
        travelMethod.value = item.value;
    }

    function isEmptyStr(s) {
        if (s == null || s === '') {
            return true;
        }
        return false;
    }   


    onMounted(() => {
        links.value = loadAll()
        restaurants.value = loadTravelMEthod()
    })

</script>

<style >


    body, html {
        margin: 0;
        padding: 0;
    } 

    .app{
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;

        margin: 0;
        padding: 0;

    }

    /* .searchStartStation{
        position: absolute;
        top:15%;
        left: 20%;
        right: 20%;
        background-color: aqua;

    } */

    .my-autocomplete li {
        line-height: normal;
        padding: 7px;
    }
    .my-autocomplete li .name {
        text-overflow: ellipsis;
        overflow: hidden;
    }
    .my-autocomplete li .addr {
        font-size: 12px;
        color: #b4b4b4;
    }
    .my-autocomplete li .highlighted .addr {
        color: #ddd;
    }


    .demo-datetime-picker {
        display: flex;
        width: 100%;
        padding: 0;
        flex-wrap: wrap;
    }

    .demo-datetime-picker .block {
        padding: 30px 0;
        text-align: center;
        border-right: solid 1px var(--el-border-color);
        flex: 1;
    }
    .demo-datetime-picker .block:last-child {
        border-right: none;
    }
    .demo-datetime-picker .demonstration {
        display: block;
        color: var(--el-text-color-secondary);
        font-size: 14px;
        margin-bottom: 20px;
    }

    .el-alert {
        margin: 20px 0 0;
    }

    .el-alert:first-child {
        margin: 0;
    }
</style>