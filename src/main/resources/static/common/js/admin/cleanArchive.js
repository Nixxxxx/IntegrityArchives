var app = new Vue({
    el: '#app',
    data: {
        cleanArchives: [{
            id: '',
            userId: '',
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
            enableStatus: ''
        }],
        editor: {
            id: '',
            userId: '',
            userName: '',
            shoushou: '',       //收受红包、礼金、有价证券及其他受馈赠的情况
            geren: '',          //个人操办婚丧嫁娶报备及执行情况
            peiou: '',          //配偶及成年子女就业及所在国籍情况
            zaiqi: '',          //个人在企业、社会及其酬取情况
            shifou: '',         //个人是否参与涉矿、涉矿企业经营活动或参与分红情况
            niandu: '',         //个人年度科研经费入账使用及财务个人借款情况
            yinsi: '',          //个人因私出入国（境）情况
            enableStatus: ''
        },
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
        defaultActive: '5',

        //条件查询单独封装的对象
        searchEntity: {},

        editDialog: false,
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标
    },
    created() {
        window.onload = function() {
            app.changeDiv();
        }
        window.onresize = function() {
            app.changeDiv();
        }
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
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

        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.cleanArchive.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
                this.cleanArchive = result.body.data.rows;
            this.pageConf.totalPage = result.body.data.total;
        });

        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },

        //触发编辑按钮
        handleEdit(id) {
            this.editDialog = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.cleanArchive.findById(id)).then(result => {
                this.editor = result.body.data;
            });
        },
        //关闭编辑窗口
        handleEditClose(key, keyPath) {
            this.editDialog = false;
        },
        edit() {
            this.editDialog = false;
            //查询当前id对应的数据
            this.$http.post(api.cleanArchive.update, JSON.stringify(this.editor)).then(result => {
                this.reloadList();
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
            this.editor = {}
        },

        handlePass(id) {
            this.$http.post(api.users.update, {id: id,enableStatus: '1'}).then(result => {
                this.reloadList();
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
                }
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
