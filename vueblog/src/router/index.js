import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import CateMana from '@/components/CateMana'
import UserMana from '@/components/UserMana'
import DataCharts from '@/components/DataCharts'
import ArticleList from '@/components/ArticleList'
import PostArticle from '@/components/PostArticle'
import BlogDetail from '@/components/BlogDetail'
import UploadHeadTest from '@/components/UploadHeadTest'
import TailoringTest from '@/components/TailoringTest'
import PersonInfo from '@/components/PersonInfo'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, {
      path: '/uploadHead',
      name: '头像上传',
      hidden: true,
      component: UploadHeadTest
    }, {
      path: '/tailoring',
      name: '图片上传',
      hidden: true,
      component: TailoringTest
    }, {
      path: '/home',
      name: '',
      component: Home,
      hidden: true
    }, {
      path: '/home',
      component: Home,
      name: '文章管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/articleList',
          name: '文章列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发表文章',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '博客详情',
          component: BlogDetail,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑博客',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '用户管理',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '栏目管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '栏目管理',
          component: CateMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '数据统计',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/charts',
          iconCls: 'fa fa-bar-chart',
          name: '数据统计',
          component: DataCharts
        }
      ]
    }, {
          path: '/home',
          component: Home,
          name: '个人信息',
          hidden: true,
          children: [
              {
                  path: '/personInfo',
                  name: '个人信息',
                  component: PersonInfo
              }
          ]
      }
  ]
})
