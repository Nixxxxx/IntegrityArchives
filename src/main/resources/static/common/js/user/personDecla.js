var app = new Vue({
    el: '#app',
    data: {
        personDecla: {
            id: '',
            userName: '',
            renmian: '',
            renshi: '',
            yinbu: '',
            xunshi: '',
            kaizhan: '',
            dangfeng: '',
            qita: '',
            enableStatus: ''
        },
        userId: '',
        defaultActive: '3',
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function () {
            app.changeDiv();
        }
        window.onresize = function () {
            app.changeDiv();
        }
        document.getElementById("header-user").innerHTML = window.localStorage.getItem("userName") + ",你好";
        this.userId = window.localStorage.userId;
        this.getPersonDecla();
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        _notify(message, type) {
            this.$message({
                message: message,
                type: type
            })
        },
        //获取当前用户信息
        getPersonDecla() {
            this.$http.get(api.personDecla.findByUserId(this.userId)).then(result => {
                this.personDecla = result.body.data;
            });
        },

        edit() {
            //查询当前id对应的数据
            this.$http.post(api.personDecla.update, JSON.stringify(this.personDecla)).then(result => {
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success');
                    this.getPersonDecla();
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
        },

        /**
         * 监听窗口改变UI样式（区别PC和Phone）
         */
        changeDiv() {
            let isMobile = this.isMobile();
            if (isMobile) {
                //手机访问
                this.sidebarFlag = ' hideSidebar mobile ';
                this.sidebarStatus = false;
                this.mobileStatus = true;
            } else {
                this.sidebarFlag = ' openSidebar';
                this.sidebarStatus = true;
                this.mobileStatus = false;
            }
        },
        isMobile() {
            let rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },
        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;

            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            let isMobile = this.isMobile();
            if (isMobile) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        //蒙版
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
});
