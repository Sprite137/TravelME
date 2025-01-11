import { createRouter,createWebHistory } from 'vue-router'
import MapContainer from '../components/MapContainer.vue'
import AddTravelLog from '../components/AddTravelLog.vue'


const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:"/",
            component: MapContainer
        },
        {
            path:"/home",
            component: MapContainer
        },
        {
            path:"/addLog",
            component: AddTravelLog
        },
    ]
})

export default router