import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import JavaHome from '@/views/programming/java/JavaHome.vue'
import BackendHome from '@/views/programming/backend/BackendHome.vue'
import PythonHome from '@/views/programming/python/PythonHome.vue'
import NodejsHome from '@/views/programming/nodejs/NodejsHome.vue'
import Register from '@/views/auth/Register.vue'
import Login from '@/views/auth/Login.vue'
import { useUserStore } from '@/store/user'

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/programming/backend',
    name: 'BackendHome',
    component: BackendHome
  },
  {
    path: '/programming/java',
    name: 'JavaHome',
    component: JavaHome,
    children: [
      {
        path: 'basics',
        name: 'JavaBasics',
        component: () => import('@/views/programming/java/JavaBasics.vue')
      },
      {
        path: 'oop',
        name: 'JavaOOP',
        component: () => import('@/views/programming/java/JavaOOP.vue')
      },
      {
        path: 'advanced',
        name: 'JavaAdvanced',
        component: () => import('@/views/programming/java/JavaAdvanced.vue'),
        meta: {
          requiresAuth: true  // 添加这个标记表示需要认证
        }
      }
    ]
  },
  {
    path: '/programming/python',
    name: 'PythonHome',
    component: PythonHome,
    children: [
      {
        path: 'basics',
        name: 'PythonBasics',
        component: () => import('@/views/programming/python/PythonBasics.vue')
      },
      {
        path: 'advanced',
        name: 'PythonAdvanced',
        component: () => import('@/views/programming/python/PythonAdvanced.vue')
      },
      {
        path: 'web',
        name: 'PythonWeb',
        component: () => import('@/views/programming/python/PythonWeb.vue')
      }
    ]
  },
  {
    path: '/programming/nodejs',
    name: 'NodejsHome',
    component: NodejsHome,
    children: [
      {
        path: 'basics',
        name: 'NodejsBasics',
        component: () => import('@/views/programming/nodejs/NodejsBasics.vue')
      },
      {
        path: 'express',
        name: 'NodejsExpress',
        component: () => import('@/views/programming/nodejs/NodejsExpress.vue')
      },
      {
        path: 'advanced',
        name: 'NodejsAdvanced',
        component: () => import('@/views/programming/nodejs/NodejsAdvanced.vue')
      }
    ]
  },
  {
    path: '/programming/frontend',
    name: 'FrontendHome',
    component: () => import('@/views/programming/frontend/FrontendHome.vue')
  },
  {
    path: '/programming/frontend/html-css',
    name: 'HtmlCss',
    component: () => import('@/views/programming/frontend/HtmlCss.vue')
  },
  {
    path: '/programming/frontend/javascript',
    name: 'JavaScript',
    component: () => import('@/views/programming/frontend/JavaScript.vue')
  },
  {
    path: '/programming/frontend/vue',
    name: 'Vue',
    component: () => import('@/views/programming/frontend/Vue.vue')
  },
  {
    path: '/programming/mobile',
    name: 'MobileHome',
    component: () => import('@/views/programming/mobile/MobileHome.vue')
  },
  {
    path: '/programming/mobile/swiftui',
    name: 'SwiftUI',
    component: () => import('@/views/programming/mobile/SwiftUI.vue')
  },
  {
    path: '/programming/mobile/kotlin',
    name: 'Kotlin',
    component: () => import('@/views/programming/mobile/Kotlin.vue')
  },
  {
    path: '/programming/mobile/flutter',
    name: 'Flutter',
    component: () => import('@/views/programming/mobile/Flutter.vue')
  },
  {
    path: '/programming/algorithm',
    name: 'AlgorithmHome',
    component: () => import('@/views/programming/algorithm/AlgorithmHome.vue')
  },
  {
    path: '/japanese',
    name: 'JlptHome',
    component: () => import('@/views/japanese/JlptHome.vue')
  },
  {
    path: '/japanese/n5',
    name: 'N5Home',
    component: () => import('@/views/japanese/N5Home.vue')
  },
  {
    path: '/japanese/n5/writing',
    name: 'N5Writing',
    component: () => import('@/views/japanese/n5/Writing.vue')
  },
  {
    path: '/japanese/n5/grammar',
    name: 'N5Grammar',
    component: () => import('@/views/japanese/n5/Grammar.vue')
  },
  {
    path: '/japanese/n5/conversation',
    name: 'N5Conversation',
    component: () => import('@/views/japanese/n5/Conversation.vue')
  },
  {
    path: '/japanese/hiragana',
    name: 'Hiragana',
    component: () => import('@/views/japanese/Hiragana.vue')
  },
  {
    path: '/japanese/courses',
    name: 'JapaneseCourses',
    component: () => import('@/views/japanese/Courses.vue')
  },
  {
    path: '/japanese/conversation',
    name: 'JapaneseConversation',
    component: () => import('@/views/japanese/Conversation.vue')
  },
  {
    path: '/japanese/grammar',
    name: 'JapaneseGrammar',
    component: () => import('@/views/japanese/Grammar.vue')
  },
  {
    path: '/japanese/n3',
    name: 'N3Home',
    component: () => import('@/views/japanese/N3Home.vue')
  },
  {
    path: '/japanese/n2',
    name: 'N2Home',
    component: () => import('@/views/japanese/N2Home.vue')
  },
  {
    path: '/japanese/n1',
    name: 'N1Home',
    component: () => import('@/views/japanese/N1Home.vue')
  },
  {
    path: '/about',
    name: 'AboutUs',
    component: () => import('@/views/about/AboutUs.vue')
  },
  {
    path: '/terms',
    name: 'Terms',
    component: () => import('@/views/about/Terms.vue')
  },
  {
    path: '/privacy',
    name: 'Privacy',
    component: () => import('@/views/about/Privacy.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    meta: {
      title: '注册账号'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 修改路由守卫
router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!userStore.isLoggedIn()) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
