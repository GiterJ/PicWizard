<!-- 注册页面 -->
<template>
    <div class="register">
        <!-- 软件图标 -->
        <div class="img">
            <img src="../assets/logo.png">
            <span>PicWizard</span>
        </div>
        <!-- 输入框 -->
        <div class="inputbox">
            <van-form>
                <van-cell-group inset>
                    <van-field v-model="userinfo.useraccount" name="账号" label="账号" placeholder="请输入账号"
                        :rules="[{ required: true, message: '请填写账号' }]" />
                    <van-field v-model="userinfo.password" type="password" name="密码" label="密码" placeholder="请输入密码"
                        :rules="[{ required: true, message: '请填写密码' }]" />
                    <van-field v-model="userinfo.checkpassword" type="password" name="确认密码" label="确认密码"
                        placeholder="请再次输入密码" :rules="[{ required: true, message: '请确认密码' }]" />
                </van-cell-group>
                <div class="register-button">
                    <van-button block type="primary" native-type="submit" @click="Register" color="#5473E8">
                        注册
                    </van-button>
                </div>
            </van-form>
        </div>
    </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import 'vant/es/toast/style'
import { useNetworkStore } from '@/stores/network';


const router = useRouter()
const networkStore = useNetworkStore()
const userinfo = reactive({
    useraccount: "",
    password: "",
    checkpassword: ""
})

// 注册函数
const Register = async () => {
    try {
        let res = await networkStore.pregister(userinfo.useraccount, userinfo.password)
        if (res.code == 200) {
            showToast('注册成功！')
            router.replace('/login')
        }
        else {
            showToast('注册失败！')
            console.log(res.message)
        }
    } catch (err) {
        console.log(err)
        showToast(err)
    }
}

</script>

<style scoped lang="less">
.register {
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
        margin-top: 6vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        .register-button {
            margin: 10vh auto 0px;
            --van-button-default-height: 4.8vh;
            --van-button-radius: 1.8vw;
            width: 80vw;
        }
    }

    .register {
        margin: 2vh auto 0px;

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