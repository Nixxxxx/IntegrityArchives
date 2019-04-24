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
    users: {
        findById(id) {
            return '/manage/user/findById?id=' + id
        },
        save: '/manage/user/save',
        update: '/manage/user/update',
    },
    userInfo: {
        findById(id) {
            return '/manage/userInfo/findById?id=' + id
        },
        update: '/manage/userInfo/update',
    },
};