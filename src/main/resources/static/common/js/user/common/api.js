//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {
    common: {
        logout: '/admin/logout'
    },
    index: {
        articleCount: '/article/findAllCount',
        commentsCount: '/comments/findAllCount',
        tagsCount: '/tags/findAllCount',
        linksCount: '/users/findAllCount',
        allArticle: '/article/findAll'
    },
    user: {
        findById(id) {
            return '/user/user/findById?id=' + id
        },
        save: '/user/user/save',
        update: '/user/user/update',
        localUpload: '/user/user/localUpload',
    },
    userInfo: {
        findById(id) {
            return '/user/userInfo/findById?id=' + id
        },
        update: '/user/userInfo/update',
    },
    userFamily: {
        findByUserId(userId) {
            return '/user/userFamily/findByUserId?userId=' + userId
        },
        update: '/user/userFamily/update',
    },
    personDecla: {
        findByUserId(userId) {
            return '/manage/personDecla/findByUserId?userId=' + userId
        },
        update: '/manage/personDecla/update',
    },
    cleanArchive: {
        findByUserId(userId) {
            return '/manage/cleanArchive/findByUserId?userId=' + userId
        },
        update: '/manage/cleanArchive/update',
    },
};