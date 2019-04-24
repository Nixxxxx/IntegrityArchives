var app = new Vue({
    el: '#app',
    data: {
        users: [{
            id: '',
            userNumber: '',
            name: '',
            createTime: '',
            lstEditTime: '',
            enableStatus: ''
        }],
        addEditor: {
            id: '',
            userNumber: '',
            userPasswd: '',
            name: ''
        },
        editor: {
            id: '',
            name: '',                                //教工姓名
            gender: '',                              //性别
            dateOfBirth: '',                         //出生年月
            nation: '',                              //民族
            nativePlace: '',                         //籍贯
            placeOfBirth: '',                        //出生地
            dateOfJoinParty: '',                     //入党时间
            dateOfJoinWork: '',                      //参加工作时间
            physicalCondition: '',                   //健康情况
            technicalPosition: '',                   //专业技术职务
            familiarMajorAndSpecialty: '',           //熟悉专业有何专长
            fullTimeDegree: '',                      //全日制学历学位
            fullTimeGraduatedUniversityAndMajor: '', //全日制毕业院校系及专业
            partTimeDegree: '',                      //在职学历学位
            partTimeGraduatedUniversityAndMajor: '', //在职毕业院校系及专业
            currentPosition: '',                     //现任职务
            resume: '',                              //简历
            rewardsAndPunishment: '',                //奖惩情况
            annualAssessmentResults: '',             //年度考核结果
            createTime: '',
            lastEditTime: '',
            enableStatus: '',
        },
        passwdEditor: {
            id: '',
            userNumber: '',
            userPasswd: '',
            name: '',
            enableStatus: ''
        },
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
        defaultActive: '3',

        //条件查询单独封装的对象
        searchEntity: {},

        editDialog: false,
        addDialog: false,
        changePasswdDialog: false,
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
            this.$http.post(api.users.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
                this.users = result.body.data.rows;
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
                this.$http.post(api.users.delete, JSON.stringify(ids)).then(result => {
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
        //关闭窗口
        handleAddClose(key, keyPath) {
            this.addDialog = false;
        },
        add() {
            if (this.addEditor.userNumber == null || this.addEditor.userNumber == '' || this.addEditor.userPasswd == null || this.addEditor.userPasswd == '') {
                this.reloadList();
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else {
                this.$http.post(api.users.save, JSON.stringify(this.addEditor)).then(result => {
                    this.reloadList();
                if (result.body.code == 200) {
                    this.addEditor = {};
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
            }
            this.editor = {};
            this.addDialog = false;
        },

        //触发修改密码按钮
        handleChangePasswd(id) {
            this.changePasswdDialog = true;
            this.passwdEditor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.users.findById(id)).then(result => {
                this.passwdEditor = result.body.data;
            });
        },
        //关闭窗口
        handleChangePasswdClose(key, keyPath) {
            this.changePasswdDialog = false;
        },
        changePasswd() {
            this.changePasswdDialog = false;
            //查询当前id对应的数据
            this.$http.post(api.users.update, JSON.stringify(this.passwdEditor)).then(result => {
                this.reloadList();
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
            this.passwdEditor = {}
        },

        //触发编辑按钮
        handleEdit(id) {
            this.editDialog = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.userInfo.findById(id)).then(result => {
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
            this.$http.post(api.userInfo.update, JSON.stringify(this.editor)).then(result => {
                this.reloadList();
            if (result.body.code == 200) {
                this._notify(result.body.msg, 'success')
            } else {
                this._notify(result.body.msg, 'error')
            }
        });
            this.editor = {}
        },

        //日期显示格式化
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
