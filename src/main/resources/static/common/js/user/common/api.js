//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const {body} = document;
const WIDTH = 1024;
const RATIO = 3;
const api = {
    common: {
        logout: '/user/logout'
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
        findByUserId(userId) {
            return '/user/userInfo/findByUserId?userId=' + userId
        },
        downloadWord(userId) {
            return '/user/userInfo/print?userId=' + userId
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
            return '/user/personDecla/findByUserId?userId=' + userId
        },
        update: '/user/personDecla/update',
    },
    cleanArchive: {
        findByUserId(userId) {
            return '/user/cleanArchive/findByUserId?userId=' + userId
        },
        update: '/user/cleanArchive/update',
    },
    personConsultations: {
        findByUserId(userId) {
            return '/user/personConsultations/findByUserId?userId=' + userId
        },
        downloadWord(userId,year) {
        	return '/user/personConsultations/downloadWord?userId=' + userId+"&year="+year
        },
    },
};