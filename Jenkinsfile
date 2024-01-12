pipeline {
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
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
        stage('Set up application.properties') {
            steps {
                sh '''
                    echo "spring.datasource.username=${DB_CREDENTIALS_USR}" >> ./src/main/resources/application.properties
                    echo "spring.datasource.password=${DB_CREDENTIALS_PSW}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CREDENTIALS_USR}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CREDENTIALS_PSW}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.kakao.client-id=${KAKAO_CREDENTIALS_USR}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.kakao.client-secret=${KAKAO_CREDENTIALS_PSW}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.naver.client-id=${NAVER_CREDENTIALS_USR}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.naver.client-secret=${NAVER_CREDENTIALS_PSW}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.github.client-id=${GITHUB_CREDENTIALS_USR}" >> ./src/main/resources/application.properties
                    echo "spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CREDENTIALS_PSW}" >> ./src/main/resources/application.properties
                '''
            }
        }
        stage("Compile") {
            steps {

                sh "./gradlew clean compileJava"
            }
        }
        stage("Build") {
            steps {
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
