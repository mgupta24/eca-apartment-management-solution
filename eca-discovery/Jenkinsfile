pipeline {
    environment {
        REGISTRY_NAME = "mgupta25/eca-discovery"
        APP_NAME = "eca-discovery"
     }

    agent any
    tools {
            maven "MAVEN_HOME"
        }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                     git branch: 'main', url: 'https://github.com/mgupta24/eca-apartment-management-system.git'
                }
            }
        stage('Build') {
            steps {
                sh "mvn -f $APP_NAME/pom.xml clean install -DskipTests"
            }
        }

        stage('Sonar Analysis') {
                    steps {
                       sh "mvn -f $APP_NAME/pom.xml clean package"
                        dir("eca-discovery"){
                        sh "mvn sonar:sonar \
                        -Dsonar.projectKey=eca-discovery: \
                        -Dsonar.projectName=eca-discovery \
                        -Dsonar.java.binaries=."
                    }
                }
        }



        stage('Building image') {
          steps{
            sh 'docker build -t $REGISTRY_NAME:$BUILD_NUMBER ./eca-discovery'
          }
        }

        stage('Docker Build & Push') {
            steps {
                withDockerRegistry(credentialsId: 'DOCKER_HUB_USER_PS', url: '') {
                    sh 'docker push $REGISTRY_NAME:$BUILD_NUMBER'
                }
            }
            post {
                success {
                    sh 'docker rmi $REGISTRY_NAME:$BUILD_NUMBER'
                }
            }
        }
    }

    post {
       always {
           deleteDir()
       }
   }
}
