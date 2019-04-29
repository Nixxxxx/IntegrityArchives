//设置全局表单提交格式
Vue.http.options.emulateJSON = true;
const api = {
    adminLogin: '/login/admin',
    userLogin: '/login/user'
}
// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            checked: false,
            login: {
                username: '',
                password: '',
                remember: ''
            },
            loginType: '1',
            flag: true,
            loading: {}, //loading动画
        };
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings(){
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        submitForm(login) {
            this.$refs[login].validate((valid) => {
                if (valid) {
                    this.loadings(); //加载动画
                    //提交表单
                    if(this.loginType == '1'){
                            this.$http.post(api.userLogin, {
                                number: this.login.username,
                                password: this.login.password,
                                remember: this.login.remember
                            }).then(result => {
                                if (result.body.code == 200) {
                                window.location.href = "/user/info";
                                this.loading.close(); //关闭动画加载
                            } else {
                                // 弹出错误信息框
                                this.$emit(
                                    'submit-form',
                                    this.$message({
                                        message: result.body.msg,
                                        type: 'warning',
                                        duration: 6000
                                    }),
                                );
                                // 清空表单状态
                                this.$refs[login].resetFields();
                            }
                        });
                    }else{

                        this.$http.post(api.adminLogin, {
                            number: this.login.username,
                            password: this.login.password,
                            remember: this.login.remember
                        }).then(result => {
                            if (result.body.code == 200) {
                                window.location.href = "/admin/admin";
                                this.loading.close(); //关闭动画加载
                            } else {
                                // 弹出错误信息框
                                this.$emit(
                                    'submit-form',
                                    this.$message({
                                        message: result.body.msg,
                                        type: 'warning',
                                        duration: 6000
                                    }),
                                );
                                // 清空表单状态
                                this.$refs[login].resetFields();
                            }
                        });
                    }
                } else {
                    this.$emit(
                        'submit-form',
                        this.$message({
                            message: '输入信息有误！',
                            type: 'warning',
                            duration: 6000
                        }),
                    );
                    return false;
                }
            });
        },
        loginEnter(login){
            this.submitForm(login);
        },

    },
});
