pipeline {
    agent any
    parameters {
        string(name: 'APP_VER', defaultValue: 'v1.0', description: '版本号')
    }
    stages { //所有阶段
        stage('构建') { //stage定义一个阶段
            steps {
                // 该步骤通常不应该在您的脚本中使用。请参考帮助查看详情。
                withDockerContainer(
                        args: '-v mvn-conf:/usr/share/maven/conf -v mvn-rep:/root/.m2',
                        image: 'maven:3.8.6-openjdk-18-slim'
                ) {
                    // some block
                    sh 'pwd'
                    sh 'mvn clean package'
                    sh 'ls -al'
                    sh 'ls -al target/'
                }
            }
        }
        stage('质量分析') {
            steps {
                echo '质量ok....'
            }
        }
        stage('单元测试') {
            steps {
                withDockerContainer(
                        args: '-v mvn-conf:/usr/share/maven/conf -v mvn-rep:/root/.m2',
                        image: 'maven:3.8.6-openjdk-18-slim'
                ) {
                    sh 'mvn test'
                }
            }
        }
        stage('打包制品') {
            steps {
                //把之前产生的target目录下的jar包进行归档存储
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
        }
        stage('制作镜像') {
            steps {
                sh "docker build -f Dockerfile -t devops-demo:${APP_VER} ."
            }
        }
        stage('推送镜像') {
            //把镜像推送到镜像仓库，方便别人获取
            steps {
                //改名
                sh "docker tag devops-demo:${APP_VER} leifengyang/devops-demo:${APP_VER}"
                //以指定的账号密码环境运行; 引用指定的账号密码
                withCredentials([usernamePassword(credentialsId: 'dockerhub-lfy-id',
                        passwordVariable: 'USER_PWD',
                        usernameVariable: 'USER_NAME')]) {
                    // some block
                    sh "docker login -u ${USER_NAME} -p ${USER_PWD}"
                    sh "docker push leifengyang/devops-demo:${APP_VER}"
                }
            }
        }
        stage('部署到测试环境') {
            steps {
                sh 'docker rm -f devops-demo'
                sh "docker run -d -p 88:8080 --name devops-demo leifengyang/devops-demo:${APP_VER}"
            }
        }
        stage('发送邮件') {
            //curl
            steps{
                emailext body: '构建ok了，特此通知', subject: "${env.JOB_NAME}-第${env.BUILD_NUMBER}构建完成-", to: '534096094@qq.com'
            }

        }
    }
}