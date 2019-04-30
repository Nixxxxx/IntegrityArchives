var app = new Vue({
    el: '#app',
    data: {
        img: '',
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
            avater: '',
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
            enableStatus: '',
        },
        passwdEditor: {
            id: '',
            userPasswd: '',
            repasswd: '',
        },
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },

        localUpload: api.user.localUpload,
        defaultActive: '3',
        //条件查询单独封装的对象
        searchEntity: {},

        editDialog: false,
        addDialog: false,
        changePasswdDialog: false,
        userFamilyDialog: false,
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
            this.$http.post(api.user.findByPage(pageSize, pageCode), this.searchEntity).then(result => {
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
                this.$http.post(api.user.delete, JSON.stringify(ids)).then(result => {
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
            if (this.addEditor.userNumber == null || this.addEditor.userNumber == '' || this.addEditor.userPasswd == null || this.addEditor.userPasswd == '' || this.addEditor.name == null || this.addEditor.name == '') {
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else if (this.addEditor.userPasswd.length < 5) {
                this._notify('请重新输入密码，密码长度在5位及以上', 'warning');
            } else {
                this.$http.post(api.user.save, JSON.stringify(this.addEditor)).then(result => {
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

        //触发修改密码按钮
        handleChangePasswd(id, userNumber) {
            this.changePasswdDialog = true;
            this.passwdEditor = {}; //清空表单
            this.passwdEditor.id = id;
            this.passwdEditor.userNumber = userNumber;
        },
        //关闭窗口
        handleChangePasswdClose(key, keyPath) {
            this.changePasswdDialog = false;
        },
        changePasswd() {
            if (this.passwdEditor.userNumber == null || this.passwdEditor.userNumber == '' || this.passwdEditor.userPasswd == null || this.passwdEditor.userPasswd == '') {
                this._notify('输入的信息不能为空', 'warning')
                return;
            } else if (this.passwdEditor.userPasswd.length < 5) {
                this._notify('请重新输入密码，密码长度在5位及以上', 'warning');
            } else{
                this.changePasswdDialog = false;
                this.$http.post(api.user.update, JSON.stringify(this.passwdEditor)).then(result => {
                    if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success');
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.passwdEditor = {}
                });
            }
        },

        //触发编辑按钮
        handleEdit(userId) {
            this.editDialog = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.userInfo.findByUserId(userId)).then(result => {
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

        imgPreview (file) {
            // 看支持不支持FileReader
            if (!file || !window.FileReader) return;

            if (/^image/.test(file.type)) {
                // 创建一个reader
                var reader = new FileReader();
                // 将图片将转成 base64 格式
                reader.readAsDataURL(file);
                // 读取成功后的回调
                reader.onloadend = function () {
                    this.img = this.result;
                }
            }
        },
        handleFileChange (e) {
            this.file = this.$refs.inputer.files[0];
            // 在获取到文件对象进行预览就行了！
            this.imgPreview(this.file);
        },
        changeAvatar(url){
            this.user.avatar = url;
            var data = {
                id: this.user.id,
                avatar: this.user.avatar
            };
            this.$http.post(api.user.update, JSON.stringify(data)).then(response => {
                this.avatarDialog = false;
                if (response.body.code == 200) {
                    this._notify('更换头像成功', 'success')
                } else {
                    this._notify(response.body.msg, 'error')
                }
            })
        },
        /**
         * 图片上传
         * @param res
         * @param file
         * @param fileList
         */
        //文件上传成功的钩子函数
        handleAvatarSuccess(res, file, fileList) {
            this._notify('图片上传成功', 'success');
            if (res.code == 200) {
                this.user.avatar = res.data.url;
                this.avatarDialog = false;
            }
        },
        //文件上传前的前的钩子函数
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isGIF = file.type === 'image/gif';
            const isPNG = file.type === 'image/png';
            const isBMP = file.type === 'image/bmp';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
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
