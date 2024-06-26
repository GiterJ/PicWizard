<!-- 登录页面 -->
<!-- TODO 对接后端，完成自动登录，加入router guarden -->
<template>
    <div class="login">
        <!-- 软件图标 -->
        <div class="img">
            <img src="../assets/logo.png">
            <span>PicWizard</span>
        </div>

        <!-- 输入框 -->
        <div class="inputbox">
            <van-form>
                <van-cell-group inset>
                    <van-field v-model="userinfo.useraccount" name="账号" label="账号" placeholder="账号"
                        :rules="[{ required: true, message: '请填写账号' }]" />
                    <van-field v-model="userinfo.password" type="password" name="密码" label="密码" placeholder="密码"
                        :rules="[{ required: true, message: '请填写密码' }]" />
                </van-cell-group>
                <div class="login-button">
                    <van-button block type="primary" native-type="submit" @click="Login" color="#5473E8">
                        <span
                            style="font-family : 'SourceHanSansCN-Regular'">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</span>
                    </van-button>
                </div>
            </van-form>
        </div>

        <!-- 去注册 -->
        <div class="register">
            <RouterLink to="/register">去注册</RouterLink>
        </div>
    </div>
</template>

<script setup>
import 'vant/es/toast/style'
import { showToast } from 'vant';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useNetworkStore } from '@/stores/network';
import { useUserStore } from '@/stores/user';

const router = useRouter()
const networkStore = useNetworkStore()
const userStore = useUserStore()

// 输入框响应式数据
const userinfo = reactive({
    useraccount: "",
    password: ""
})

// TODO 对接后端，完成登录函数
// 登录函数
const Login = async () => {
    try {
        let res = await networkStore.plogin(userinfo.useraccount, userinfo.password)
        console.log("hello",res.code)
        if (res.code == 200) {
            showToast('登录成功！')
            // 修改用户登录状态
            userStore.userInfo.isLogin = true
            userStore.userInfo.userName = userinfo.useraccount
            // 跳转到主页
            router.replace('/pqa')
        }
        else {
            showToast('登录失败！')
            console.log(res.message)
        }
    } catch (err) {
        console.log(err)
        showToast(err)
    }
}

</script>

<style scoped lang="less">
.login {
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 21vh;
    font-family: "Source Han Sans CN Regular";


    .img {
        margin: 0px auto;
        display: flex;
        flex-direction: column;
        align-items: center;

        span {
            font-size: 6.5vw;
            margin: 4vw 0px 0px;
            font-family: Alibaba PuHuiTi;
            font-weight: bold;
            color: #5473E8;
        }

        img {
            width: 22.4vw;
        }
    }

    .inputbox {
        margin-top: 7vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        .login-button {
            margin: 10vh auto 0px;
            --van-button-default-height: 4.8vh;
            --van-button-radius: 1.8vw;
            width: 80vw;
        }
    }

    .register {
        margin: 2.3vh auto 0px;

        a {
            text-decoration: none;
            font-size: 2vh;
            color: #3c55b1;
            font-family: 'Microsoft YaHei';

            &:visited {
                color: #5473E8;
                font-family: 'Microsoft YaHei';
            }
        }


    }
}
</style>