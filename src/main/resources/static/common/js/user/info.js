var app = new Vue({
    el: '#app',
    data: {
        userInfo: {
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
        },
        pass: {
            id: '',
            password: '',
            repassword: '',
        },
        userId: '43',
        localUpload: api.user.localUpload,
        avatarDialog: false,
        defaultActive: '2',
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
        this.getUserInfo(this.userId);
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
        getUserInfo(userId) {
            this.$http.get(api.userInfo.findByUserId(userId)).then(result => {
                this.userInfo = result.body.data;
            });
        },

        //触发关闭按钮
        handleClose() {
            this.avatarDialog = false;
        },
        handleEditAvatar() {
            this.$http.get(api.userInfo.avatar).then(response => {
                this.avatarList = response.body;
            });
            this.avatarDialog = true;
        },
        //修改头像
        changeAvatar(url) {
            this.userInfo.avatar = url;
            var data = {
                id: this.userInfo.id,
                avatar: this.userInfo.avatar
            };
            this.$http.post(api.userInfo.update, JSON.stringify(data)).then(response => {
                this.avatarDialog = false;
                if (response.body.code == 200) {
                    this._notify('更换头像成功', 'success')
                    window.location.href = api.common.logout
                } else {
                    this._notify(response.body.msg, 'error')
                }
            })
        },

        changePassword() {
            if (this.pass.password.length < 6) {
                this._notify('请重新输入密码，密码长度在6位及以上', 'warning');
            } else if (this.pass.password != this.pass.repassword) {
                this._notify('两次输入的密码不一致', 'warning');
            } else {
                this.$http.post(api.user.update, JSON.stringify(this.pass)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                        //执行/logout请求
                        window.location.href = '/user/logout'; //更改了密码，注销当前登录状态，重新登录
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.clearPass();
                });
            }
        },
        clearPass() {
            this.pass.repassword = '';
            this.pass.password = '';
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
