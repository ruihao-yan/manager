import {createRouter, createWebHashHistory} from 'vue-router';
import tokenApi from '@/utils/auth';

// 路由规则
// 路由仅仅进行路径匹配
// 如果需要展示使用<router-view><router-view>组件展示

const routes = [
    {
        path:'/',
        name:'main',
        component: () => import('@/views/Main.vue'),
        // 直接定向到home路径
        redirect: '/home',
        children: [
            {
                path:'home',
                name:'home',
                component: () => import('@/views/Home.vue'),
            }
        ],
    },
    {
        path:'/login',
        name:'login',
        component: ()=> import('@/views/login.vue'),
        meta: {requiresAuth: true}
    }
];

const router = createRouter({
    // 设置路由模式
    history: createWebHashHistory(),
    routes
});

router.beforeEach(async (to) => {
    const token = tokenApi().getToken();
    if (!to.meta.requiresAuth && !token && to.name != 'login') {
        return {name: "login"}
    } else {
    }
})

// 暴露给外部使用
export default router;