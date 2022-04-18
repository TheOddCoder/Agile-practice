<template>
    <el-row v-loading="loading">
        <el-col>
            <div class="infoTop">
                <span class="introduction">{{user.sign}}</span>
            </div>
        </el-col>
        <el-col>
            <div class="divideLine"></div>
        </el-col>
        <el-col>
            <div class="infoBottom">
                <div class="infoTitle">
                    <span class="introduction">
                    个人信息
                </span>
                </div>
                <div style="padding: 24px">
                    <el-row :gutter="20">
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">姓名</span>
                            <span class="infoContent">{{user.nickname}}</span>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">性别</span>
                            <span class="infoContent">{{user.sex === 1 ? '男' : '女' }}</span>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">生日</span>
                            <span class="infoContent">{{user.birthday}}</span>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">电话</span>
                            <span class="infoContent">{{user.tel}}</span>
                        </div></el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">头像</span>
                            <el-form :model="formValidate" :rules="ruleValidate" ref="formValidate" class="demo-ruleForm">
                                <el-form-item prop="mainImage">
                                    <div class="upload-btn" style="width: 80px;height: 80px;border-radius: 50%" @click="uploadPicture('flagImg')">
                                        <img :src="user.userface" :alt="user.nickname"  style="width: 80px;height: 80px;border-radius: 50%">
                                    </div>
                                </el-form-item>
                            </el-form>
                            <!-- 剪裁组件弹窗 -->
                            <el-dialog
                                    title="裁切头像"
                                    :visible.sync="cropperModel"
                                    width="950px"
                                    center
                            >
                                <cropper-image
                                        :Name="cropperName"
                                        @uploadImgSuccess = "handleUploadSuccess"
                                        ref="child">
                                </cropper-image>
                            </el-dialog>
                            <!--查看大封面-->
                            <el-dialog
                                    title="查看大头像"
                                    :visible.sync="imgVisible"
                                    center>
                                <img :src="imgName" v-if="imgVisible" style="width: 100%" alt="查看">
                            </el-dialog>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">部门</span>
                            <span class="infoContent">{{user.dept}}</span>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">地址</span>
                            <span class="infoContent">{{user.address}}</span>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <span class="infoItem">邮箱</span>
                            <span class="infoContent">{{user.email}}</span>
                        </div></el-col>
                    </el-row>
                </div>
            </div>
        </el-col>
    </el-row>
</template>
<script>
    import CropperImage from "@/components/CropperImage";
    import {getRequest} from '../utils/api'
    export default{
        name: "PersonInfo",
        components: {CropperImage},
        mounted: function () {
            var userId = this.$route.query.userId;
            var _this = this;
            this.loading = true;
            getRequest("/user/" + userId).then(resp=> {
                if (resp.status == 200) {
                    _this.user = resp.data;
                }
                _this.loading = false;
            }, resp=> {
                _this.loading = false;
                _this.$message({type: 'error', message: '页面加载失败!'});
            });
        },
        data(){
            return {
                user: {},
                loading: false,
                formValidate: {
                    mainImage: '',
                },
                ruleValidate: {
                    mainImage: [
                        {required: true, message: '请上传头像', trigger: 'blur'}
                    ],
                },
                //裁切图片参数
                cropperModel:false,
                cropperName:'',
                imgName: '',
                imgVisible: false
            }
        },
        methods: {
            //头像设置
            uploadPicture(name){
                this.cropperName = name;
                this.cropperModel = true;
            },
            //图片上传成功后
            handleUploadSuccess (data){
                this.cropperModel = false;
            }
        }
    }
</script>
<style>
    .infoTop {
        text-align: left;
        margin: 30px auto;
        padding: 30px 30px;
        width: auto;
        height: 30px;
        background-color: #F8F8FF;
    }

    .introduction {
        font-size: 20px;
        height: 30px;
        width: auto;
        line-height: 30px;
    }

    .divideLine {
        position: relative;
        margin: 0 auto;
        width: auto;
        height: 1px;
        background-color: #d4d4d4;
        text-align: center;
        font-size: 16px;
        color: rgba(101, 101, 101, 1);
    }

    .infoBottom {
        margin-top: 30px;
        width: auto;
        height: 350px;
        background-color: #F8F8FF;
    }
    .infoTitle {
        padding: 24px;
        text-align: left;
        width: auto;
        height: 30px;
    }

    .infoItem, .infoContent{
        display: inline-block;
        min-height: 40px;
        width: 100%;
    }

    .infoItem{
        color: #909399;
    }

    .infoContent {
        line-height: 40px;
    }

    .el-row {
        margin-bottom: 20px;
    &:last-child {
         margin-bottom: 0;
     }
    }

    .el-col {
        border-radius: 4px;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 80px;
        font-size: 18px;
        text-align: left;
    }
    .row-bg {
        padding: 10px 0;
    }

    /*start*/
    .upload-list-cover{
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        padding: 0 40px;
        align-items: center;
        background: rgba(0,0,0,.6);
        opacity: 0;
        transition: opacity 1s;
    }
    .cover_icon {
        font-size: 30px;
    }
    .upload-btn{
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        border: 1px solid #cccccc;
        border-radius: 5px;
        overflow: hidden;
        box-shadow: 0 0 1px #cccccc;
    }
    .upload-btn:hover {
        border: 1px solid #69b7ed;
    }
    .upload-btn i{
        margin: 5px;
    }
</style>
