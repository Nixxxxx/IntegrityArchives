var app = new Vue({
    el: '#app',
    data: {
        families: [{
            id: '',
            appellation: '',
            name: '',
            age: '',
            politicsStatus: '',
            workUnitAndPosition: '',
            createTime: '',
            lastEditTime: ''
        }],
        userId: '43',
        editor: {
            id: '',
            appellation: '',
            name: '',
            age: '',
            politicsStatus: '',
            workUnitAndPosition: ''
        },
        defaultActive: '3',
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function() {
            app.changeDiv();
        }
        window.onresize = function() {
            app.changeDiv();
        }
        this.search();
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        //刷新列表
        reloadList() {
            this.search();
        },
        //条件查询
        search() {
            this.$http.get(api.userFamily.findByUserId(this.userId)).then(result => {
                this.families = result.body.data;
            });
        },

        dateFormat:function(row, column) {
            var date = row[column.property];
            if (date == undefined) {
                return "";
            }
            return moment(date).format("YYYY-MM-DD HH:mm:ss");
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
