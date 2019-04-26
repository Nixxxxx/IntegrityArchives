var app = new Vue({
    el: '#app',
    data: {
        personDeclas: [{
            id: '',
            userId: '',
            userName: '',
            renmian: '',    //任免情况
            renshi: '',     //人事档案情况
            yinbu: '',      //因不如实报告个人有关事项受到处理的情况
            xunshi: '',     //巡视视察、信访、案件监督管理以及其他方面移交的问题线索和处理情况
            kaizhan: '',    //开展谈话函询、初步核实、审查调查、以及其他工作形成的材料
            dangfeng: '',   //党风廉政意见回复材料
            qita: '',       //其他反映廉政情况的材料
            createTime: '',
            lstEditTime: '',
            enableStatus: ''
        }],
        editor: {
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
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
        defaultActive: '4',

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
            this.$http.post(api.personDecla.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
                this.personDeclas = result.body.data.rows;
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
            this.$http.get(api.personDecla.findById(id)).then(result => {
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
            this.$http.post(api.personDecla.update, JSON.stringify(this.editor)).then(result => {
                this.reloadList();
            if (result.body.code == 200) {
                this._notify(result.body.msg, 'success')
            } else {
                this._notify(result.body.msg, 'error')
            }
        });
            this.editor = {}
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
