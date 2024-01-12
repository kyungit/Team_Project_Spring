pipeline {
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        DB_URL = 'jdbc:mariadb://mariadb.cls8a6uamjkk.ap-northeast-2.rds.amazonaws.com:3306/mariadb'
        DB_CREDENTIALS = credentials('db-credentials')
        GOOGLE_CREDENTIALS = credentials('google-credentials')
        KAKAO_CREDENTIALS = credentials('kakao-credentials')
        NAVER_CREDENTIALS = credentials('naver-credentials')
        GITHUB_CREDENTIALS = credentials('github-credentials')
    }
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Build") {
            steps {
                withCredentials([
                    usernamePassword(credentialsId: 'db-credentials', passwordVariable: 'DB_PASSWORD', usernameVariable: 'DB_USERNAME'),
                    usernamePassword(credentialsId: 'google-credentials', passwordVariable: 'GOOGLE_CLIENT_SECRET', usernameVariable: 'GOOGLE_CLIENT_ID'),
                    usernamePassword(credentialsId: 'naver-credentials', passwordVariable: 'NAVER_CLIENT_SECRET', usernameVariable: 'NAVER_CLIENT_ID'),
                    usernamePassword(credentialsId: 'kakao-credentials', passwordVariable: 'KAKAO_CLIENT_SECRET', usernameVariable: 'KAKAO_CLIENT_ID'),
                    usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'GITHUB_CLIENT_SECRET', usernameVariable: 'GITHUB_CLIENT_ID')
                ]) {
                    withEnv([
                        "DB_URL=$DB_URL",
                        "DB_USERNAME=$DB_USERNAME",
                        "DB_PASSWORD=$DB_PASSWORD",
                        "GOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID",
                        "GOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET",
                        "NAVER_CLIENT_ID=$NAVER_CLIENT_ID",
                        "NAVER_CLIENT_SECRET=$NAVER_CLIENT_SECRET",
                        "KAKAO_CLIENT_ID=$KAKAO_CLIENT_ID",
                        "KAKAO_CLIENT_SECRET=$KAKAO_CLIENT_SECRET",
                        "GITHUB_CLIENT_ID=$GITHUB_CLIENT_ID",
                        "GITHUB_CLIENT_SECRET=$GITHUB_CLIENT_SECRET"
                    ]) {
                        sh "./gradlew build"
                        sh "cp ./build/libs/dormitory-0.0.1-SNAPSHOT.jar ./docker/dormitory/"
                    }
                }
            }
        }
        stage("Docker Login") {
           steps {
               sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin" 
           }
        }
        stage("Docker Image Build") {
           steps {
               sh "docker build -t jingom368/dormitory_dormitory:${BUILD_NUMBER} ./docker/dormitory/"
           }
        }
        stage("Docker Image Push") {
           steps {
               sh "docker push jingom368/dormitory_dormitory:${BUILD_NUMBER}" 
           } 
        }
        stage("Docker Image Clean up") {
           steps {
               sh "docker image rm jingom368/dormitory_dormitory:${BUILD_NUMBER}" 
           }
        }
        stage("Minikube start") {
           steps {
               sh "minikube start --driver=docker --cni=calico"
           }
        }
        stage("Deploy") {
           steps {
               sh "sed -i 's/{{VERSION}}/${BUILD_NUMBER}/g' ./kubernetes/dormitory.yml"
               sh "kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission"
               sh "kubectl apply -f ./kubernetes/dormitory.yml"
               sh "kubectl apply -f ./kubernetes/ingress.yml"
           } 
           post {
                success {
                    slackSend (
                        channel: "#jenkins",
                        color: "#2C953C",
                        message: "smboard 배포가 성공하였습니다."
                    )
                    echo "Completed Server Deploy"
                }
                failure {
                    slackSend (
                        channel: "#jenkins",
                        color: "#FF3232",
                        message: "smboard 배포가 실패하였습니다."
                    )
                    echo "Fail Server Deploy"
                }
          }
       }  
    }
}
