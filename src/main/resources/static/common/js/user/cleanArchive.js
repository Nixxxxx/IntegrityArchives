var app = new Vue({
    el: '#app',
    data: {
        cleanArchive: {
            id: '',
            userName: '',
            shoushou: '',       //收受红包、礼金、有价证券及其他受馈赠的情况
            geren: '',          //个人操办婚丧嫁娶报备及执行情况
            peiou: '',          //配偶及成年子女就业及所在国籍情况
            zaiqi: '',          //个人在企业、社会及其酬取情况
            shifou: '',         //个人是否参与涉矿、涉矿企业经营活动或参与分红情况
            niandu: '',         //个人年度科研经费入账使用及财务个人借款情况
            yinsi: '',          //个人因私出入国（境）情况
            createTime: '',
            lstEditTime: '',
        },
        userId: '43',
        defaultActive: '5',
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
        this.getCleanArchive();
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
        getCleanArchive() {
            this.$http.get(api.cleanArchive.findByUserId(this.userId)).then(result => {
                this.cleanArchive = result.body.data;
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
