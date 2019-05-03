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
            userFamilyList: [{
                appellation: '',
                userFamilyName: '',
                age: '',
                politicsStatus: '',
                workUnitAndPosition: '',
                /*createTime: '',
                lastEditTime: '',
                enableStatus: ''*/
            }]
        },
        families: [{
            appellation: '',
            userFamilyName: '',
            age: '',
            politicsStatus: '',
            workUnitAndPosition: ''
        }],
        pass: {
            id: '',
            userPasswd: '',
            repassword: '',
        },
        userId: '',
        changePasswdDialog: false,
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
        document.getElementById("header-user").innerHTML = window.localStorage.getItem("userName") + ",你好";
        this.userId = window.localStorage.userId;
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
        downloadWord() {
            window.open(api.userInfo.downloadWord(this.userId),"_blank");
        },
        //获取当前用户信息
        getUserInfo(userId) {
            this.$http.get(api.userInfo.findByUserId(userId)).then(result => {
                this.userInfo = result.body.data;
            });
            this.$http.get(api.userFamily.findByUserId(userId)).then(result => {
                this.families = result.body.data;
        });
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
            if (this.pass.userPasswd.length < 5) {
                this._notify('请重新输入密码，密码长度在5位及以上', 'warning');
            } else if (this.pass.userPasswd != this.pass.repassword) {
                this._notify('两次输入的密码不一致', 'warning');
            } else {
                this.pass.id = this.userId;
                this.$http.post(api.user.update, JSON.stringify(this.pass)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                        this.changePasswdDialog = false;
                        //执行/logout请求
                        //window.location.href = '/user/logout'; //更改了密码，注销当前登录状态，重新登录
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.clearPass();
                });
            }
        },
        clearPass() {
            this.pass.repassword = '';
            this.pass.userPasswd = '';
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
