import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/tenant',
    component: Layout,
    redirect: '/tenant/audit',
    name: 'Tenant',
    meta: { title: '租户服务', icon: 'example' },
    children: [
      {
        path: 'tenant',
        name: 'tenant',
        component: () => import('@/views/rent/index'),
        meta: { title: '租户管理', icon: 'peoples' },
        children: [
          {
            path: 'list',
            name: 'User',
            component: () => import('@/views/rent/tenant/list'),
            meta: { title: '租户列表', icon: '' }
          },
          {
            path: 'audit',
            name: 'Role',
            component: () => import('@/views/rent/tenant/audit'),
            meta: { title: '租户审核', icon: '' }
          },

        ]
      },
      {
        path: 'service',
        name: 'Service',
        component: () => import('@/views/rent/index'),
        meta: { title: '服务管理', icon: 'shopping' },
        children: [
          {
            path: 'list',
            name: 'ServiceList',
            component: () => import('@/views/rent/service/list'),
            meta: { title: '服务列表', icon: '' }
          },
          {
            path: 'add',
            name: 'ServiceAdd',
            component: () => import('@/views/rent/service/add'),
            meta: { title: '服务添加', icon: '' }
          },
        ]
      }
    ]
  },

  {
    path: '/organize',
    component: Layout,
    redirect: '/organize/user',
    name: 'Organize',
    meta: { title: '组织管理', icon: 'tree-table' },
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/organize/user/index'),
        meta: { title: '用户管理', icon: 'peoples' },
        children: [
          {
            path: 'list',
            name: 'User',
            component: () => import('@/views/organize/user/list'),
            meta: { title: '用户列表', icon: '' }
          },
          {
            path: 'add',
            name: 'Role',
            component: () => import('@/views/organize/user/add'),
            meta: { title: '添加用户', icon: '' }
          },

        ]
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/organize/role'),
        meta: { title: '角色管理', icon: 'people' }
      },
      {
        path: 'permission',
        name: 'Permission',
        component: () => import('@/views/organize/permission/index'),
        meta: { title: '权限管理', icon: 'tree' },
        children: [
          {
            path: 'api',
            name: 'PermissionApi',
            component: () => import('@/views/organize/permission/api'),
            meta: { title: '接口权限', icon: '' }
          },
          {
            path: 'menu',
            name: 'PermissionMenu',
            component: () => import('@/views/organize/permission/menu'),
            meta: { title: '菜单权限', icon: '' }
          },
        ]
      }
    ]
  },

  {
    path: '/service',
    component: Layout,
    redirect: '/service/list',
    name: 'Service',
    meta: { title: '服务管理', icon: 'shopping' },
    children: [
      {
        path: 'list',
        name: 'ServiceList',
        component: () => import('@/views/service/list'),
        meta: { title: '订阅列表', icon: '' }
      },
      {
        path: 'order',
        name: 'ServiceOrder',
        component: () => import('@/views/service/order'),
        meta: { title: '服务大厅', icon: '' }
      },
    ]
  },

  {
    path: '/system',
    component: Layout,
    redirect: '/system/index',
    name: 'system',
    meta: { title: '系统管理', icon: 'international' },
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('@/views/system/index'),
        meta: { title: '系统设置', icon: '' }
      },
      {
        path: 'log',
        name: 'log',
        component: () => import('@/views/system/log/syslog'),
        meta: { title: '系统日志', icon: '' }
      }
    ]
  },

  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/info',
    name: 'Profile',
    meta: { title: '个人管理', icon: 'user' },
    children: [
      {
        path: 'info',
        name: 'ProfileInfo',
        component: () => import('@/views/profile/info'),
        meta: { title: '修改信息', icon: 'user' }
      },
      {
        path: 'password',
        name: 'ProfilePassword',
        component: () => import('@/views/profile/password'),
        meta: { title: '修改密码', icon: 'password' }
      },
      {
        path: 'avatar',
        name: 'ProfileAvatar',
        component: () => import('@/views/profile/avatar'),
        meta: { title: '修改头像', icon: 'people' }
      }
    ]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'example' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
