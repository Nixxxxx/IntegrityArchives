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
    admins: {
        findByPage(pageSize, pageCode) {
            return '/manage/admin/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/manage/admin/findById?id=' + id
        },
        save: '/manage/admin/save',
        delete: '/manage/admin/delete',
        update: '/manage/admin/update',
    },
    users: {
        findByPage(pageSize, pageCode) {
            return '/manage/user/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/manage/user/findById?id=' + id
        },
        save: '/manage/user/save',
        delete: '/manage/user/delete',
        update: '/manage/user/update',
    },
    userInfo: {
        findById(id) {
            return '/manage/userInfo/findById?id=' + id
        },
        save: '/manage/userInfo/save',
        update: '/manage/userInfo/update',
    },
    personDecla: {
        findByPage(pageSize, pageCode) {
            return '/manage/personDecla/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/manage/personDecla/findById?id=' + id
        },
        update: '/manage/personDecla/update',
    },
    cleanArchive: {
        findByPage(pageSize, pageCode) {
            return '/manage/cleanArchive/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findById(id) {
            return '/manage/cleanArchive/findById?id=' + id
        },
        update: '/manage/cleanArchive/update',
    },
};