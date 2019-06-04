//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {
    common: {
        logout: '/admin/logout'
    },
    admin: {
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
    user: {
        findByPage(pageSize, pageCode) {
            return '/manage/user/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        findPersonConsultationsByPage(pageSize, pageCode) {
            return '/manage/user/findPersonConsultationsByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
        },
        downloadWord(userId,year) {
        	return '/manage/user/downloadWord?userId=' + userId+"&year="+year
        },
        findById(id) {
            return '/manage/user/findById?id=' + id
        },
        personConsultationsdelete(id) {
            return '/manage/user/personConsultationsdelete?id='+id
        },
        save: '/manage/user/save',
        delete: '/manage/user/delete',
        update: '/manage/user/update',
    },
    userInfo: {
        findByUserId(userId) {
            return '/manage/userInfo/findByUserId?userId=' + userId
        },
        downloadWord(userId) {
            return '/manage/userInfo/print?userId=' + userId
        },
        update: '/manage/userInfo/update',
    },
    userFamily: {
        findById(id) {
            return '/manage/userFamily/findById?id=' + id
        },
        findByUserId(userId) {
            return '/manage/userFamily/findByUserId?userId=' + userId
        },
        save: '/manage/userFamily/save',
        delete: '/manage/userFamily/delete',
        update: '/manage/userFamily/update',
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