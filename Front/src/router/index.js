import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layout/index.vue'

const routes=[
    {
        path: "/admin",
        redirect: "/admin/dashboard"
    },
    {
        path: "/admin",
        component: Layout,
        name: 'admin',
        children: [
            {
                path: '/admin/dashboard',
                name: 'dashboard',
                component: () => import('@/view/admin/dashboard/dashboard.vue'),
                meta: {
                    title: '仪表盘'
                }
            },
            {
                path: '/admin/allarticle',
                name: 'allArticle',
                component: () => import('@/view/admin/Article/allArticle.vue'),
                meta: {
                    title: '所有页面'
                }
            },
            {
                path: '/admin/tablearticle',
                name: 'tableArticle',
                component: () => import('@/view/admin/Article/tableArticle.vue'),
                meta: {
                    title: '分类目录'
                }
            },
            {
                path: '/admin/writerarticle',
                name: 'tagArticle',
                component: () => import('@/view/admin/Article/writerArticle.vue'),
                meta: {
                    title: '写文章'
                }
            },
            {
                path: '/admin/attached',
                name: 'attached',
                component: () => import('@/view/admin/attached/attached.vue'),
                meta: {
                    title: '附件'
                }
            },
            {
                path: '/admin/comment',
                name: 'comment',
                component: () => import('@/view/admin/comment/comment.vue'),
                meta: {
                    title: '评论'
                }
            },
            {
                path: '/admin/profile',
                name: 'Profile',
                component: () => import('@/view/admin/Profile/Profile.vue'),
                meta: {
                    title: '个人资料'
                }
            },
        ],
    },
    {
        path: "/login",
        component: () => import('@/view/admin/login.vue'),
        meta: {
            title: '登录'
        }
    },
    {
        path: "/register",
        component: () => import('@/view/admin/register.vue'),
        meta: {
            title: '注册'
        }
    }

]
const router = createRouter({
    history: createWebHistory(),
    routes
})
//路由守卫
router.beforeEach((to, from, next) => {
    document.title = to.meta.title
    if (to.path === '/login') return next()
    let token = window.sessionStorage.getItem('token')
    if (!token) return next("/login")
    next()
})

//导出路由
export default router