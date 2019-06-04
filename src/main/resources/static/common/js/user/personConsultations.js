var app = new Vue({
    el: '#app',
    data: {
    	years:[],
    	infos: [{
            id: '',
            userId: '',
            year: '',
            path: ''
        }],
        userId: '',
        changePasswdDialog: false,
        defaultActive: '2',
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
        uploaddata: {
        	userId: '',
        	year: ''
        },
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
        uploadWord(year) {
        	this.uploaddata.userId = window.localStorage.userId;
            this.uploaddata.year = year;
        	
        },
        downloadWord(year) {
            //window.open(api.personConsultations.downloadWord(this.userId),"_blank");
        	window.open(api.personConsultations.downloadWord(this.userId,year));
        },
        //获取当前用户信息
        getUserInfo(userId) {
            this.$http.get(api.personConsultations.findByUserId(userId)).then(result => {
                this.infos = result.body.data;
            	for(var i=0;i<this.infos.length;i++){
                	var info = this.infos[i];
                	this.years.push(info.year);
                }
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
        },
        upload(year) {
            if(this.years.indexOf(year) == -1){
            	return true;
            }
        },
        download(year) {
            if(this.years.indexOf(year) != -1){
            	return true;
            }
        },
        beforeRemove(file, fileList) {
        	return this.$confirm(`确定移除 ${ file.name }？`);
        },
        beforeUpload(file) {
        	const isJPG = file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' || file.type === 'application/msword';
            if (!isJPG) {
              this.$message.error('上传必须是word格式!');
            }
            return isJPG;
        }
    },
});
