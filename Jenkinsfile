pipeline {
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        DB_URL = 'your_database_url'
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
	       export DB_URL=$DB_URL
	       export DB_USERNAME=$(echo $DB_CREDENTIALS | cut -d: -f1)
	       export DB_PASSWORD=$(echo $DB_CREDENTIALS | cut -d: -f2)
               export GOOGLE_CLIENT_ID=$(echo $GOOGLE_CREDENTIALS | cut -d: -f1)
               export GOOGLE_CLIENT_SECRET=$(echo $GOOGLE_CREDENTIALS | cut -d: -f2)
               export NAVER_CLIENT_ID=$(echo $NAVER_CREDENTIALS | cut -d: -f1)
               export NAVER_CLIENT_SECRET=$(echo $NAVER_CREDENTIALS | cut -d: -f2)
               export KAKAO_CLIENT_ID=$(echo $KAKAO_CREDENTIALS | cut -d: -f1)
               export KAKAO_CLIENT_SECRET=$(echo $KAKAO_CREDENTIALS | cut -d: -f2)
               export GITHUB_CLIENT_ID=$(echo $GITHUB_CREDENTIALS | cut -d: -f1)
               export GITHUB_CLIENT_SECRET=$(echo $GITHUB_CREDENTIALS | cut -d: -f2)
               sh "./gradlew build"
               sh "cp ./build/libs/dormitory-0.0.1-SNAPSHOT.jar ./docker/dormitory/"
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
