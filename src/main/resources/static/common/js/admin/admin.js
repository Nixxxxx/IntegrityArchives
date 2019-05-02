var app = new Vue({
    el: '#app',
    data: {
        admins: [{
            id: '',
            adminNumber: '',
            createTime: '',
            lstEditTime: '',
            enableStatus: ''
        }],
        addEditor: {
            adminNumber: '',
            adminPasswd: '',
            enableStatus: ''
        },
        editor: {
            id: '',
            adminNumber: '',
            adminPasswd: '',
            enableStatus: ''
        },
        changeStatusEditor: {
            id: '',
            adminNumber: '',
            enableStatus: ''
        },
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
        defaultActive: '2',

        //条件查询单独封装的对象
        searchEntity: {},

        editDialog: false,
        addDialog: false,
        changeStatusDialog: false,
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
        document.getElementById("header-admin").innerHTML = window.localStorage.getItem("adminNumber") + ",你好";
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
            this.$http.post(api.admin.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
                this.admins = result.body.data.rows;
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


        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },
        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post(api.admin.delete, JSON.stringify(ids)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success')
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        this._notify(result.body.msg, 'error')
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this._notify('已取消删除', 'info')
            });
        },

        //触发添加管理员按钮
        handleAdd(id) {
            this.addDialog = true;
        },
        //关闭编辑窗口
        handleAddClose(key, keyPath) {
            this.addDialog = false;
        },
        add() {
            if (this.addEditor.adminNumber == null || this.addEditor.adminNumber == '' || this.addEditor.adminPasswd == null || this.addEditor.adminPasswd == '') {
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else if (this.addEditor.adminPasswd.length < 5) {
                this._notify('请重新输入密码，密码长度在5位及以上', 'warning');
            } else {
                this.$http.post(api.admin.save, JSON.stringify(this.addEditor)).then(result => {
                    this.reloadList();
                    if (result.body.code == 200) {
                        this.addEditor = {};
                        this._notify(result.body.msg, 'success')
                    } else {
                        this._notify(result.body.msg, 'error')
                    }
                });
                this.editor = {};
                this.addDialog = false;
            }
        },

        //触发编辑按钮
        handleEdit(id, adminNumber) {
            this.editDialog = true;
            this.editor = {}; //清空表单
            this.editor.id = id;
            this.editor.adminNumber = adminNumber;
        },
        //关闭编辑窗口
        handleEditClose(key, keyPath) {
            this.editDialog = false;
        },
        edit() {
            if (this.editor.adminNumber == null || this.editor.adminNumber == '' || this.editor.adminPasswd == null || this.editor.adminPasswd == '') {
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else if (this.editor.adminPasswd.length < 5) {
                this._notify('请重新输入密码，密码长度在5位及以上', 'warning');
            } else{
                this.editDialog = false;
                //查询当前id对应的数据
                this.$http.post(api.admin.update, JSON.stringify(this.editor)).then(result => {
                    this.reloadList();
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success')
                    } else {
                        this._notify(result.body.msg, 'error')
                    }
                });
                this.editor = {}
            }
        },

        //触发更新状态按钮
        handleChangeStatus(id) {
            this.changeStatusDialog = true;
            this.changeStatusEditor = {}; //清空表单
            this.$http.get(api.admin.findById(id)).then(result => {
                this.changeStatusEditor = result.body.data;
            });
        },
        //关闭编辑窗口
        handleChangeStatusClose(key, keyPath) {
            this.changeStatusDialog = false;
        },
        changeStatus() {
            this.changeStatusDialog = false;
            this.$http.post(api.admin.update, JSON.stringify(this.changeStatusEditor)).then(result => {
                this.reloadList();
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
            this.changeStatusEditor = {}
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
